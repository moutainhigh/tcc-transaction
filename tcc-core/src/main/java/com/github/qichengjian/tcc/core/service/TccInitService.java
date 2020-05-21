package com.github.qichengjian.tcc.core.service;

import com.github.qichengjian.tcc.common.config.TccConfig;

@FunctionalInterface
public interface TccInitService {

    /**
     * tcc init
     * @param tccConfig
     */
    void initialization(TccConfig tccConfig);
}
