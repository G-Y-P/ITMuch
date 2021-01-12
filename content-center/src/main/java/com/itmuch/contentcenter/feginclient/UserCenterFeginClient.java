package com.itmuch.contentcenter.feginclient;

import com.itmuch.contentcenter.configuration.UserCenterFeginConfiguration;
import com.itmuch.contentcenter.domain.dto.user.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author GEYP
 * @version 1.0
 * @date 2021/1/12 0012 21:16
 * @description
 */
@FeignClient(name="user-center",configuration = UserCenterFeginConfiguration.class)
public interface UserCenterFeginClient {

    @GetMapping("users/{id}")
    UserDTO findById(@PathVariable Integer id);
}
