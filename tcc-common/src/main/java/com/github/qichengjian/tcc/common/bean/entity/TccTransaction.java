package com.github.qichengjian.tcc.common.bean.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class TccTransaction implements Serializable {

    public TccTransaction(String transId) {
        TccTransaction tccTransaction = new TccTransaction();

    }

    private String transId;
    private String nodeTransId;
    /**
     * status {@linkplain com.github.qichengjian.tcc.common.enums.TccActionEnum}
     */
    private int status;

    /**
     * role {@linkplain com.github.qichengjian.tcc.common.enums.TccRoleEnum}
     */
    private int role;

    /**
     * 重试次数
     */
    private int retriedCount;

    private Date createTime;
    private Date lastTime;
    private int version;
    private String targetClass;
    private String targetMethod;
    private String confirmMethod;
    private String cancelMethod;

    private List<TccParticipant> tccParticipantList;

    public void registerParticipant(TccParticipant participant) {
        tccParticipantList.add(participant);
    }
}
