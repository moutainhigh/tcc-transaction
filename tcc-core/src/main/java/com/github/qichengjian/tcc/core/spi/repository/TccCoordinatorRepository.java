package com.github.qichengjian.tcc.core.spi.repository;

import com.github.qichengjian.tcc.common.annotation.TccSPI;

@TccSPI
public interface TccCoordinatorRepository {

    int remove(String id);
}
