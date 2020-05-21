package com.github.qichengjian.tcc.common.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Objects;

@Getter
@RequiredArgsConstructor
public enum TccActionEnum {

    PRE_TRY(0, "开始执行try"),
    TRYING(1, "try阶段完成"),
    CONFIRMING(2, "confirm阶段"),
    CANCELING(3, "cancel阶段");

    private final int code;
    private final String desc;

    public static TccActionEnum acquireByCode(String code) {
        return Arrays.stream(TccActionEnum.values())
                .filter(v -> Objects.equals(v.getCode(), code))
                .findFirst().orElse(TccActionEnum.TRYING);
    }
}
