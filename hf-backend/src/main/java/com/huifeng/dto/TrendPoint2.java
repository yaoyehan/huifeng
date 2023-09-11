package com.huifeng.dto;

import lombok.Data;
import org.influxdb.annotation.Column;
import org.influxdb.annotation.Measurement;

import java.io.Serializable;

/**
 * @author : yyh
 * @create : 2022-7-12 - 23:01
 * @describe: 封装折线图类结果的数据
 */
@Data
@Measurement(name = "quota")
public class TrendPoint2 implements Serializable {

    @Column(name = "time")
    private String time;//时间


    @Column(name = "pointValue")
    private Double pointValue;//时间点数据
}
