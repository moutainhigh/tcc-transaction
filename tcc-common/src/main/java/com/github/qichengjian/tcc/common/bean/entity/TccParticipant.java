package com.github.qichengjian.tcc.common.bean.entity;

import lombok.Data;

import java.io.Serializable;

@Data

public class TccParticipant implements Serializable {

    private String transId;
    private int status;
    private TccInvocation confirmTccInvocation;
    private TccInvocation cancelTccInvocation;

    public TccParticipant(String transId, TccInvocation confirmTccInvocation, TccInvocation cancelTccInvocation) {
        this.transId = transId;
        this.confirmTccInvocation = confirmTccInvocation;
        this.cancelTccInvocation = cancelTccInvocation;
    }

}
