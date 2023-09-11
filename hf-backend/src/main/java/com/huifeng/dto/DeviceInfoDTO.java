package com.huifeng.dto;

import lombok.Data;

import java.util.List;

/**
 * @author : yyh
 * @create : 2022-10-08 - 22:21
 * @describe:
 */
@Data
public class DeviceInfoDTO {

    private DeviceDTO device;//设备

    private List<QuotaDTO> quotaList; //指标列表

}
