package com.huifeng.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.base.Strings;
import com.huifeng.entity.AdminEntity;
import com.huifeng.mapper.AdminMapper;
import com.huifeng.service.AdminService;
import com.huifeng.util.SecurityUtils;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper,AdminEntity> implements AdminService{
    @Override
    public Integer login(String loginName, String password) {
        if(Strings.isNullOrEmpty(loginName) || Strings.isNullOrEmpty(password)){
            return -1;
        }
        QueryWrapper<AdminEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .lambda()
                .eq(AdminEntity::getLoginName,loginName);
        AdminEntity adminEntity = this.getOne(queryWrapper);
        if(adminEntity == null)
            return -1;

        if(SecurityUtils.matchesPassword(password,adminEntity.getPassword())){
            return adminEntity.getId();
        }

        return -1;
    }
}
