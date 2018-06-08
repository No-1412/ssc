package com.pcy.ssc.entity.demo;

import java.io.Serializable;

/**
 * @author 彭长云
 * @Description:查询输血申请单参数
 * @date 2018/6/8 10:09
 */
public class IhPatientBloodListParam implements Serializable{
    private static final long serialVersionUID = 7510880651887009887L;
    private String inpCode;

    public String getInpCode() {
        return inpCode;
    }

    public void setInpCode(String inpCode) {
        this.inpCode = inpCode;
    }
}
