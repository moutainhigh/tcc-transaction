package com.github.qichengjian.tcc.core.coordinator;

import com.github.qichengjian.tcc.common.config.TccConfig;

/**
 * 协调者服务：用于保存事务日志
 */
public interface TccCoordinatorService {

    void start(TccConfig tccConfig) throws Exception;

}
