package com.github.qichengjian.tcc.common.exception;

/**
 * 自定义运行时异常
 */
public class TccException extends RuntimeException{

    /**
     * 实例化一个新的tcc异常
     */
    public TccException() {

    }

    /**
     *
     * @param message
     */
    public TccException(final String message) {
        super(message);
    }

    /**
     *
     * @param message
     * @param cause
     */
    public TccException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     *
     * @param caues
     */
    public TccException(final Throwable caues) {
        super(caues);
    }

}
