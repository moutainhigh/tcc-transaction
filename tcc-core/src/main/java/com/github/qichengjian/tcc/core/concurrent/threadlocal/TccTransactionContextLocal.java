package com.github.qichengjian.tcc.core.concurrent.threadlocal;

import com.github.qichengjian.tcc.common.bean.context.TccTransactionContext;

/**
 * 将tcc事务上下文保存到threadLocal中
 */
public final class TccTransactionContextLocal {

    private static final ThreadLocal<TccTransactionContext> CURRENT_LOCAL = new ThreadLocal<>();

    private static final TccTransactionContextLocal TRANSACTION_CONTEXT_LOCAL = new TccTransactionContextLocal();

    private TccTransactionContextLocal() {

    }

    /**
     * 单例模式获取实例
     * @return
     */
    public static TccTransactionContextLocal getInstance() {
        return TRANSACTION_CONTEXT_LOCAL;
    }

    /**
     * 设置threadlLocal的事务上下文
     * @param context
     */
    public void set(final TccTransactionContext context) {
        CURRENT_LOCAL.set(context);
    }

    /**
     * 获取threadLocal的事务上下文
     * @return
     */
    public TccTransactionContext get() {
        return CURRENT_LOCAL.get();
    }

    /**
     * 移除threadLocal对象，防止内存泄漏
     */
    public void remove() {
        CURRENT_LOCAL.remove();
    }
}
