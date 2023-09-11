package com.huifeng.dto;

import lombok.Data;
import org.influxdb.annotation.Column;
import org.influxdb.annotation.Measurement;

import java.io.Serializable;

/**
 * @author : yyh
 * @create : 2022-7-12 - 21:53
 * @describe: 趋势指标点
 */
@Data
@Measurement(name = "quota")
public class TrendPoint implements Serializable {

    /**
     * 时间
     */
    @Column(name = "time")
    private String time;

    /**
     * 时间点数据
     */
    @Column(name = "pointValue")
    private Integer pointValue;
}
