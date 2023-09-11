package com.huifeng.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.huifeng.entity.AdminEntity;

public interface AdminService extends IService<AdminEntity>{
    /**
     * 登录
     * @param loginName
     * @param password
     * @return
     */
    Integer login(String loginName,String password);
}
