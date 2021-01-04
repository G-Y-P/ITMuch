package com.itmuch.contentcenter.service.content;

import com.itmuch.contentcenter.dao.content.ShareMapper;
import com.itmuch.contentcenter.domain.dto.content.ShareDTO;
import com.itmuch.contentcenter.domain.dto.user.UserDTO;
import com.itmuch.contentcenter.domain.entity.content.Share;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import sun.plugin.com.DispatchClient;

import java.util.List;

/**
 * @author GEYP
 * @version 1.0
 * @date 2021/1/3 0003 16:14
 * @description
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ShareService {

    private final ShareMapper shareMapper;
    //调用用户微服务请求用户
    private final RestTemplate restTemplate;

    private final DiscoveryClient discoveryClient;

    public ShareDTO findById(Integer id){
        //获取分享详情
        Share share = shareMapper.selectByPrimaryKey(id);
        //拿到用户ID
        Integer userId =share.getUserId();

        //用户中心所有实例
        //了解Stream
        //Lambda表达式
        //functional->函数式编程
        List<ServiceInstance> instances= discoveryClient.getInstances("user-center");
        String targetUrl = instances.stream()
                //数据变换
                .map(instance->instance.getUri().toString()+"/users/{id}")
                .findFirst()
                .orElseThrow(()->new IllegalArgumentException("当前没有实例"));
        log.info("打印URL：{}",targetUrl);
        //使用GEt方法请求
        UserDTO userDto = restTemplate.getForObject(
                targetUrl,
                UserDTO.class,userId
        );
        ShareDTO shareDTO = new ShareDTO();
        //消息装配
        BeanUtils.copyProperties(share,shareDTO);
        shareDTO.setWxNickName(userDto.getWxNickname());
        return shareDTO;
    }
}
