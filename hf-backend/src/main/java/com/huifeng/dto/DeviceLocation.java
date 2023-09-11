package com.huifeng.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author : yyh
 * @create : 2022-7-12 - 20:59
 * @describe:
 */
@Data
public class DeviceLocation implements Serializable {
    private String deviceId;//设备编号
    private String location;//经纬度 用"."分割，维度在前
}
