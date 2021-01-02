package com.itmuch.usercenter.domain.entity.user;

import com.itmuch.usercenter.dao.user.UserMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author GEYP
 * @version 1.0
 * @date 2021/1/2 0002 19:58
 * @description
 */
@RestController
public class TestController {

    @Resource
    private UserMapper userMapper;

    @GetMapping("/test")
    public User inster(){
        User user = new User();
        user.setWxId("sdfvcs");
        user.setWxNickname("scdvs");
        user.setRoles("dfs");
        user.setAvatarUrl("fvdvd");
        user.setBonus(100);
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        userMapper.insertSelective(user);
        return user;
    }
}
