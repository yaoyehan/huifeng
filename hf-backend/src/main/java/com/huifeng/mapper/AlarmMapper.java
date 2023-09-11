package com.huifeng.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huifeng.config.MybatisRedisCache;
import com.huifeng.entity.AlarmEntity;
import org.apache.ibatis.annotations.*;

@Mapper
@CacheNamespace(implementation= MybatisRedisCache.class,eviction=MybatisRedisCache.class)
public interface AlarmMapper extends BaseMapper<AlarmEntity>{
    @Results(id="alarmMap",value = {
            @Result(property = "quota",column = "quota_id",one = @One(select = "com.huifeng.mapper.QuotaMapper.selectById")),
            @Result(property = "quotaId",column = "quota_id")
    })
    @Select("select * from tb_alarm where id=#{id}")
    Page<AlarmEntity> queryPage(Page<AlarmEntity> page, Integer id);
}
