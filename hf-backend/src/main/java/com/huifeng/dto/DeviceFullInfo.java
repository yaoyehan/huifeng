package com.huifeng.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author : yyh
 * @create : 2022-7-12 - 21:46
 * @describe:
 */
@Data
public class DeviceFullInfo implements Serializable {
    private List<QuotaInfo> quotaList;
    private String location;
    private String deviceId;
    private Boolean online;
    private Boolean alarm;
}
