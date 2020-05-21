package com.github.qichengjian.tcc.common.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TccRoleEnum {

    START(1, "发起者"),
    CONSUMER(2, "消费者"),
    PROVIDER(3, "提供者"),
    LOCAL(4, "本地调用"),
    INLINE(5, "内嵌rpc调用"),
    SPRING_CLOUD(6, "SpringCloud");

    private final int code;

    private final String desc;

}
