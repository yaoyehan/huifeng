package com.huifeng.vo;

import com.huifeng.dto.QuotaInfo;
import lombok.Data;

import java.util.List;

/**
 * @author : yyh
 * @create : 2022-11-29 - 2:10
 * @describe: 设备指标详情vo
 */
@Data
public class DeviceQuotaVO {

    private String deviceId;//设备编号

    private Boolean online;//在线状态

    private Integer level;//告警级别

    private List<QuotaInfo> quotaList;//指标列表

}
