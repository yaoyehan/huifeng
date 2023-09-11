package com.huifeng.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author : yyh
 * @create : 2022-7-12 - 23:09
 * @describe: 面板指标数据
 */
@Data
public class BoardQuotaData implements Serializable {
    /**
     * 名称（设备编号）
     */
    private String name;
    /**
     * 指标数据
     */
    private List<Double> data;
}
