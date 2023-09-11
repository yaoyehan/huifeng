package com.huifeng.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author : yyh
 * @create : 2022-7-12 - 22:06
 * @describe: 折线图封装类
 */
@Data
public class LineVO implements Serializable {

    private List<String> xdata;//x轴

    private List<Long> series;//数据
}
