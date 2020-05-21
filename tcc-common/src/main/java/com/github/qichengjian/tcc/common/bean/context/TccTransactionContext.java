package com.github.qichengjian.tcc.common.bean.context;

import lombok.Data;

import java.io.Serializable;

@Data
public class TccTransactionContext implements Serializable {

    /**
     * transId事务id
     */
    private String transId;

    /**
     * 事务操作类型
     * action {@linkplain com.github.qichengjian.tcc.common.enums.TccActionEnum}
     */
    private int action;

    /**
     * 事务参与的角色
     * role {@linkplain com.github.qichengjian.tcc.common.enums.TccRoleEnum}
     */
    private int role;


}
