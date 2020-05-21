package com.github.qichengjian.tcc.core.bootstrap;

import com.github.qichengjian.tcc.common.config.TccConfig;
import com.github.qichengjian.tcc.core.service.TccInitService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 启动类
 */
public class TccTransactionBootstrap extends TccConfig implements ApplicationContextAware {

    private TccInitService tccInitService;

    @Autowired
    public TccTransactionBootstrap(final TccInitService tccInitService) {
        this.tccInitService = tccInitService;
    }
    @Override
    public void setApplicationContext(final ApplicationContext applicationContext) throws BeansException {
        start(this);
    }

    private void start(final TccConfig tccConfig) {
        tccInitService.initialization(tccConfig);
    }
}
