package com.huifeng.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.huifeng.config.MybatisRedisCache;
import com.huifeng.entity.GPSEntity;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Mapper;

@Mapper
@CacheNamespace(implementation= MybatisRedisCache.class,eviction=MybatisRedisCache.class)
public interface GpsMapper extends BaseMapper<GPSEntity>{
}
