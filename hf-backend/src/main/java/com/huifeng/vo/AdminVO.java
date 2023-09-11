package com.huifeng.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class AdminVO implements Serializable{
    private String loginName;
    private String password;
}
