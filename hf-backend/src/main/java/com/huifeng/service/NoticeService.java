package com.huifeng.service;

import com.huifeng.dto.DeviceLocation;
import com.huifeng.dto.QuotaDTO;

import java.util.List;

/**
 * @author : yyh
 * @create : 2022-7-12 - 22:07
 * @describe: 通知(透传)服务
 */
public interface NoticeService {

    /**
     * 指标数据透传
     * @param quotaDTOList
     */
    void quotaTransfer(List<QuotaDTO> quotaDTOList);

    /**
     * 断连透传
     * @param deviceId
     * @param online
     */
    void onlineTransfer(String deviceId,Boolean online  );

    /**
     * gps透传
     * @param deviceLocation
     */
    void gpsTransfer(DeviceLocation deviceLocation);
}
