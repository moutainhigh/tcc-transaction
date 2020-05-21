package com.github.qichengjian.tcc.springcloud.feign;

import com.github.qichengjian.tcc.common.annotation.Tcc;
import com.github.qichengjian.tcc.common.bean.context.TccTransactionContext;
import com.github.qichengjian.tcc.common.bean.entity.TccParticipant;
import com.github.qichengjian.tcc.common.enums.TccRoleEnum;
import com.github.qichengjian.tcc.core.concurrent.threadlocal.TccTransactionContextLocal;
import com.github.qichengjian.tcc.core.helper.SpringBeanUtils;
import com.github.qichengjian.tcc.core.service.executor.TccTransactionExecutor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * 扩展feign拦截器中的逻辑
 */
public class TccFeignHandler implements InvocationHandler {

    private InvocationHandler delegate;

    public void setDelegate(InvocationHandler delegate) {
        this.delegate = delegate;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //object类的方法直接调用
        if (Object.class.equals(method.getDeclaringClass())) {
            return method.invoke(this, args);
        }
        try {
            //只处理带有tcc注解的方法
            Tcc tcc = method.getAnnotation(Tcc.class);
            if (Objects.isNull(tcc)) {
                return delegate.invoke(proxy, method, args);
            }
            //处理threadLocal的事务上下文
            TccTransactionContext tccTransactionContext = TccTransactionContextLocal.getInstance().get();
            if (Objects.nonNull(tccTransactionContext)) {
                if (tccTransactionContext.getRole() == TccRoleEnum.LOCAL.getCode()) {
                    tccTransactionContext.setRole(TccRoleEnum.INLINE.getCode());
                }
            }
            TccTransactionExecutor tccTransactionExecutor = SpringBeanUtils.getInstance().getBean(TccTransactionExecutor.class);
            Object invoke = delegate.invoke(proxy, method, args);
            TccParticipant participant = buildParticipant();
            if (tccTransactionContext.getRole() == TccRoleEnum.INLINE.getCode()) {
                tccTransactionExecutor.registerByNested(tccTransactionContext.getTransId(), participant);
            } else {
                tccTransactionExecutor.enlistParticipant(participant);
            }
            return invoke;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            throw throwable;
        }
    }

    private TccParticipant buildParticipant() {
        return new TccParticipant();
    }
}
