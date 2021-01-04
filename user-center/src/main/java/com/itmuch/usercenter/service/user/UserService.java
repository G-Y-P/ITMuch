package com.itmuch.usercenter.service.user;

import com.itmuch.usercenter.dao.user.UserMapper;
import com.itmuch.usercenter.domain.entity.user.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author GEYP
 * @version 1.0
 * @date 2021/1/3 0003 16:04
 * @description
 */
@Service
public class UserService {

    @Resource
    private UserMapper userMapper;

    public User findById(Integer id){
        return userMapper.selectByPrimaryKey(id);
    }
}
