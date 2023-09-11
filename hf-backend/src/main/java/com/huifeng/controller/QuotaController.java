package com.huifeng.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.huifeng.emq.EmqClient;
import com.huifeng.entity.AlarmEntity;
import com.huifeng.exception.BussinessException;
import com.huifeng.service.AlarmService;
import com.huifeng.vo.Pager;
import com.huifeng.vo.QuotaVO;
import com.huifeng.entity.QuotaEntity;
import com.huifeng.service.QuotaService;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/quota")
@Slf4j
public class QuotaController{

    @Autowired
    private QuotaService quotaService;
    @Autowired
    private AlarmService alarmService;
    @Autowired
    private EmqClient emqClient;

    /**
     * 创建指标
     * @param vo
     * @return
     */
    @PostMapping
    public boolean create(@RequestBody QuotaVO vo){
        try {
            QuotaEntity quotaEntity = new QuotaEntity();
            BeanUtils.copyProperties(vo,quotaEntity);
            //执行创建指标后对指标进行订阅
            emqClient.subscribe("$queue/"+quotaEntity.getSubject());//添加这句！
            return quotaService.save(quotaEntity);
        }catch (DuplicateKeyException e){
            throw new BussinessException("已存在该名称");
        } catch (MqttException e) {
            log.error("订阅主题失败",e);
            return false;
        }
    }

    /**
     * 分页获取所有指标
     * @param page
     * @param pageSize
     * @param quotaName
     * @return
     */
    @GetMapping
    public Pager<QuotaEntity> queryPage(@RequestParam(value = "page",required = false,defaultValue = "1") Long page,
                                        @RequestParam(value = "pageSize",required = false,defaultValue = "10") Long pageSize,
                                        @RequestParam(value = "quotaName",required = false) String quotaName){
        return new Pager<>(quotaService.queryPage(page,pageSize,quotaName));
    }


    /**
     * 更新指标
     * @param vo
     * @return
     */
    @PutMapping
    public Boolean update(@RequestBody QuotaVO vo){
        try {
            QuotaEntity entity = new QuotaEntity();
            BeanUtils.copyProperties(vo,entity);

            return quotaService.updateById(entity);
        }catch (DuplicateKeyException e){
            throw new BussinessException("已存在该名称");
        }

    }

    /**
     * 删除指标
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable Integer id){
        QueryWrapper<AlarmEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(AlarmEntity::getQuotaId,id);
        Integer count = alarmService.count(queryWrapper);
        if(count>0)
            throw new BussinessException("该指标使用中");
        return quotaService.removeById(id);
    }

    /**
     * 分页获取数值型指标
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping("/numberQuota")
    public Pager<QuotaEntity> queryNumberQuota(@RequestParam(value = "page",required = false,defaultValue = "1") Long page,
                                               @RequestParam(value = "pageSize",required = false,defaultValue = "10") Long pageSize){
        return new Pager<>(quotaService.queryNumberQuota(page,pageSize));
    }
}
