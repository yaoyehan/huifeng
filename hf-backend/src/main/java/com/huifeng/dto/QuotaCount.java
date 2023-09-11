package com.huifeng.dto;

import lombok.Data;
import org.influxdb.annotation.Column;
import org.influxdb.annotation.Measurement;


/**
 * @author : yyh
 * @create : 2022-11-29 - 1:29
 * @describe: 封装统计记录数
 */
@Data
@Measurement(name = "quota")
public class QuotaCount {

    @Column(name = "count")
    private Long count;

}
