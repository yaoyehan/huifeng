package com.huifeng.controller;

import com.google.common.collect.Lists;
import com.huifeng.dto.HeapPoint;
import com.huifeng.dto.TrendPoint;
import com.huifeng.es.ESRepository;
import com.huifeng.service.ReportService;
import com.huifeng.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @author : yyh
 * @create : 2022-7-12 - 21:44
 * @describe:
 */
@RestController
@RequestMapping("/report")
public class ReportController {
    @Autowired
    private ReportService reportService;
    @Autowired
    private ESRepository esRepository;

    /**
     * 设备状态分布
     * @return
     */
    @GetMapping("/statusCollect")
    public List<PieVO> getStatusCollect(){
        return  reportService.getStatusCollect();
    }


    /**
     * 获取实时监控数据
     * @return
     */
    @GetMapping("/monitor")
    public MonitorVO getMonitorData(){
        MonitorVO monitor = new MonitorVO();
        monitor.setDeviceCount(esRepository.getAllDeviceCount());
        monitor.setAlarmCount(esRepository.getAlarmCount());
        return monitor;
    }

    /**
     * 获取告警趋势
     * @return
     */
    @GetMapping("/trend/{startTime}/{endTime}/{type}")
    public LineVO getQuotaTrendCollect(@PathVariable String startTime, @PathVariable String endTime, @PathVariable Integer type){
        List<TrendPoint> trendPointList = reportService.getAlarmTrend(startTime, endTime, type);
        LineVO lineVO=new LineVO();
        lineVO.setXdata(Lists.newArrayList());
        lineVO.setSeries(Lists.newArrayList());
        trendPointList.forEach( t->{
            //返回的日期格式是 2020-09-01T00:00:00Z，这里需要根据type显示为对应日期格式
            lineVO.getXdata().add( formatTime(t.getTime(),type) );
            lineVO.getSeries().add( t.getPointValue().longValue());
        });
        return lineVO;
    }

    /**
     * 格式化日期串
     * @param time
     * @param type
     * @return
     */
    private String formatTime(String time,int type){
        LocalDateTime localTime = LocalDateTime.parse(time, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        if(type == 1){
            return  localTime.getMinute()+"";
        }else if(type == 2){
            return localTime.getHour()+"";
        }else if(type == 3){
            return localTime.getMonthValue()+"月"+localTime.getDayOfMonth()+"日";
        }
        return time;
    }

    /**
     * 获取一定时间范围之内的告警次数前10最多的设备指标
     * @param startTime
     * @param endTime
     * @return
     */
    @GetMapping("/top10Alarm/{startTime}/{endTime}")
    public List<HeapPoint> getTop10Alarm(@PathVariable String startTime, @PathVariable String endTime){
        return reportService
                .getTop10Alarm(startTime,endTime);
    }

    /**
     * 通过指标查询设备列表
     * @param quotaId
     * @return
     */
    @GetMapping("/devices")
    public Pager<String> getDeviceByQuota(
            @RequestParam(value = "page",required = false,defaultValue = "1") Long page,
            @RequestParam(value = "pageSize",required = false,defaultValue = "10") Long pageSize,
            @RequestParam(value = "quotaId")String quotaId){
        return reportService.getDeviceByQuota(page,pageSize,quotaId);
    }

    /**
     * 报表预览
     * @param previewVO
     * @return
     */
    @PostMapping("/preview")
    public BoardQuotaVO getPreviewData(@RequestBody PreviewVO previewVO ){
        BoardQuotaVO boardData = reportService.getBoardData(
                previewVO.getQuotaId(), previewVO.getDeviceIdList(), previewVO.getStart(), previewVO.getEnd(), previewVO.getType());

        //时间处理
        List<String> xdata=Lists.newArrayList();
        for(String x:boardData.getXdata()){
            xdata.add(formatTime(x,previewVO.getType() ))  ;
        }
        boardData.setXdata(xdata);
        boardData.setName("设备预览面板");
        return boardData;
    }
}
