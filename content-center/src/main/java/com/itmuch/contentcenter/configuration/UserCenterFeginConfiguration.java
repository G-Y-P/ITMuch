package com.itmuch.contentcenter.configuration;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author GEYP
 * @version 1.0
 * @date 2021/1/12 0012 21:41
 * @description
 * fegin的配置类
 * 这个类不加@Configuration注解
 * 否则会出现上下问扫描包的情况
 * 需要把这个config放到MapperScan以外
 */
public class UserCenterFeginConfiguration {

    @Bean
    public Logger.Level level(){
        //让fegin 打印所有请求细节
        return Logger.Level.FULL;
    }

}
