package com.huifeng.controller;

import com.huifeng.dto.DeviceFullInfo;
import com.huifeng.entity.GPSEntity;
import com.huifeng.service.GpsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/gps")
public class GpsController{

    @Autowired
    private GpsService gpsService;

    /**
     * 修改gps指标
     * @param gpsEntity
     * @return
     */
    @PutMapping
    public boolean update(@RequestBody GPSEntity gpsEntity){
        return gpsService.update(gpsEntity);
    }

    /**
     * 获取gps指标
     * @return
     */
    @GetMapping
    public GPSEntity get(){
        return gpsService.getGps();
    }


    /**
     * 根据经纬度获取设备信息
     * @param lat
     * @param lon
     * @param distance
     * @return
     */
    @GetMapping("/deviceList/{lat}/{lon}/{distance}")
    public List<DeviceFullInfo> getDeviceFullInfo(
            @PathVariable Double lat,
            @PathVariable Double lon,
            @PathVariable Integer distance){
        return gpsService.getDeviceFullInfo(lat,lon,distance);
    }

}
