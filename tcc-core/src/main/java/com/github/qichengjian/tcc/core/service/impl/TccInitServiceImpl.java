package com.github.qichengjian.tcc.core.service.impl;

import com.github.qichengjian.tcc.common.config.TccConfig;
import com.github.qichengjian.tcc.common.serializer.ObjectSerializer;
import com.github.qichengjian.tcc.common.utils.extension.ExtensionLoader;
import com.github.qichengjian.tcc.core.service.TccInitService;
import com.github.qichengjian.tcc.core.spi.repository.TccCoordinatorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TccInitServiceImpl implements TccInitService {

    /**
     * tcc初始化
     * @param tccConfig
     */
    @Override
    public void initialization(final TccConfig tccConfig) {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> log.info("tcc-transaction showdown now")));


    }

    /**
     * load spi
     * @param tccConfig
     */
    private void loadSpiSupport(final TccConfig tccConfig) {
        //spi serialize
        final ObjectSerializer serializer = ExtensionLoader.getExtensionLoader(ObjectSerializer.class)
                .getActivateExtension(tccConfig.getSerializer());

        //spi repository
        final TccCoordinatorRepository repository = ExtensionLoader.getExtensionLoader(TccCoordinatorRepository.class)
                .getActivateExtension(tccConfig.getRepositorySupport());
    }
}
