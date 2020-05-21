package com.github.qichengjian.tcc.core.service.executor;

import com.github.qichengjian.tcc.common.annotation.Tcc;
import com.github.qichengjian.tcc.common.bean.context.TccTransactionContext;
import com.github.qichengjian.tcc.common.bean.entity.TccInvocation;
import com.github.qichengjian.tcc.common.bean.entity.TccParticipant;
import com.github.qichengjian.tcc.common.bean.entity.TccTransaction;
import com.github.qichengjian.tcc.common.enums.TccActionEnum;
import com.github.qichengjian.tcc.common.enums.TccRoleEnum;
import com.github.qichengjian.tcc.core.concurrent.threadlocal.TccTransactionContextLocal;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.util.Objects;
import java.util.Optional;

/**
 * 事务执行器
 */
@Component
public class TccTransactionExecutor {

    private static final ThreadLocal<TccTransaction> CURRENT = new ThreadLocal<>();

    public TccTransaction preTry(ProceedingJoinPoint point) {
        // 构建tcc-transaction
        TccTransaction tccTransaction = buildTccTransaction(point, TccRoleEnum.START.getCode(), null);
        // 将tcc-transaction保存到threadLocal中
        CURRENT.set(tccTransaction);
        //发布事件
        //设置tcc-transactionContext上下文
        TccTransactionContext context = new TccTransactionContext();
        context.setAction(TccActionEnum.TRYING.getCode());
        context.setRole(TccRoleEnum.START.getCode());
        context.setTransId(tccTransaction.getTransId());
        TccTransactionContextLocal.getInstance().set(context);
        return tccTransaction;
    }



    private TccTransaction buildTccTransaction(ProceedingJoinPoint point, int role, String transId) {
        TccTransaction tccTransaction;
        if (!StringUtils.isEmpty(transId)) {
            tccTransaction = new TccTransaction(transId);
        } else {
            tccTransaction = new TccTransaction();
        }
        tccTransaction.setStatus(TccActionEnum.PRE_TRY.getCode());
        tccTransaction.setRole(role);
        MethodSignature methodSignature = (MethodSignature) point.getSignature();
        Method method = methodSignature.getMethod();
        Class<?> clazz = point.getTarget().getClass();
        Object[] args = point.getArgs();
        Tcc tcc = method.getAnnotation(Tcc.class);
        tccTransaction.setTargetClass(clazz.getName());
        tccTransaction.setTargetMethod(method.getName());

        TccInvocation confirmInvocation = null;
        String confirmMethod = tcc.confirmMethod();

        if (!StringUtils.isEmpty(confirmMethod)) {
            tccTransaction.setConfirmMethod(confirmMethod);
            confirmInvocation = new TccInvocation(clazz, confirmMethod, method.getParameterTypes(), args);
        }

        String cancelMethod = tcc.cancelMethod();
        TccInvocation cancelInvocation = null;
        if (!StringUtils.isEmpty(cancelMethod)) {
            tccTransaction.setCancelMethod(cancelMethod);
            cancelInvocation = new TccInvocation(clazz, cancelMethod, method.getParameterTypes(), args);
        }

        TccParticipant tccParticipant = new TccParticipant(tccTransaction.getTransId(), confirmInvocation, cancelInvocation);
        return tccTransaction;

    }

    /**
     * 嵌套注册tcc调用方法
     * @param transId
     * @param participant
     */
    public void registerByNested(String transId, TccParticipant participant) {
        if (Objects.isNull(participant)
        || Objects.isNull(participant.getConfirmTccInvocation())
        || Objects.isNull(participant.getCancelTccInvocation())) {
            return;
        }
        TccTransaction tccTransaction = new TccTransaction(transId);
        Optional.ofNullable(tccTransaction)
                .ifPresent(transaction -> {
                    tccTransaction.registerParticipant(participant);
                    updateParticipant(transaction);
                });
    }

    /**
     * 通过spring event来更新事务调用链
     * @param transaction
     */
    private void updateParticipant(TccTransaction transaction) {

    }

    /**
     *  非嵌套时注册tcc调用方法（通过role不是local）
     * @param participant
     */
    public void enlistParticipant(TccParticipant participant) {
        if (Objects.isNull(participant)) {
            return;
        }
        Optional.ofNullable(getCurrentTransaction())
                .ifPresent(tccTransaction -> {
                    tccTransaction.registerParticipant(participant);
                    updateParticipant(tccTransaction);
                });
    }

    public TccTransaction getCurrentTransaction() {
        return CURRENT.get();
    }
}
