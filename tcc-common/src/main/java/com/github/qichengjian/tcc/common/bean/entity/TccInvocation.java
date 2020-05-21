package com.github.qichengjian.tcc.common.bean.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TccInvocation implements Serializable {

    @Getter
    private Class targetClass;

    @Getter
    private String methodName;

    @Getter
    private Class[] parameterTypes;

    @Getter
    private Object[] args;

}
