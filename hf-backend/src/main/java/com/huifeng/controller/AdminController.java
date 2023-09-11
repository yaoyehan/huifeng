package com.huifeng.controller;

import com.huifeng.vo.AdminVO;
import com.huifeng.vo.LoginResultVO;
import com.huifeng.service.AdminService;
import com.huifeng.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController{
    @Autowired
    private AdminService adminService;

    @PostMapping("/login")
    public LoginResultVO login(@RequestBody AdminVO admin){
        LoginResultVO result = new LoginResultVO();
        Integer adminId = adminService.login(admin.getLoginName(),admin.getPassword());
        if(adminId < 0){
            result.setLoginSuccess(false);
            return result;
        }
        result.setAdminId(adminId);
        String token = JwtUtil.createJWT(adminId);
        result.setToken(token);
        result.setLoginSuccess(true);

        return result;
    }
}
