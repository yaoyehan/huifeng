package com.huifeng.dto;

import lombok.Data;
import org.influxdb.annotation.Column;
import org.influxdb.annotation.Measurement;

/**
 * @author : yyh
 * @create : 2022-11-29 - 1:29
 * @describe:
 */
@Data
@Measurement(name = "quota")
public class QuotaAllInfo extends QuotaInfo{
    @Column(name = "time")
    private String time;
}
