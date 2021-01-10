package com.itmuch.contentcenter.configuration;

import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import com.alibaba.nacos.client.naming.core.Balancer;
import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.BaseLoadBalancer;
import com.netflix.loadbalancer.Server;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.alibaba.nacos.NacosDiscoveryProperties;
import org.springframework.cloud.alibaba.nacos.ribbon.NacosServer;
import org.springframework.util.CollectionUtils;
import sun.security.jca.GetInstance;

import javax.annotation.Resource;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author GEYP
 * @version 1.0
 * @date 2021/1/10 0010 10:36
 * @description
 */
@Slf4j
public class NacosSameClusterWeightedRule extends AbstractLoadBalancerRule {

    @Resource
    private NacosDiscoveryProperties nacosDiscoveryProperties;

    @Override
    public void initWithNiwsConfig(IClientConfig iClientConfig) {

    }

    @Override
    public Server choose(Object o) {
        try {
            //拿到配置文件中的集群名称 NJ
            String clusterName = nacosDiscoveryProperties.getClusterName();

            BaseLoadBalancer loadBalancer = (BaseLoadBalancer)this.getLoadBalancer();
            //log.info("lb={}",loadBalancer);
            String name = loadBalancer.getName();
            //拿到服务发现的相关API
            NamingService namingService=nacosDiscoveryProperties.namingServiceInstance();


            //1.找到指定服务的所有实例A
            List<Instance> instances = namingService.selectInstances(name,true);

            //过滤出相同集群下的所有实例B
            List<Instance> sameClustInstances=instances.stream()
                    .filter(instance ->  Objects.equals(instance.getClusterName(),clusterName))
                    .collect(Collectors.toList());
            //如果B为空，则用B
            List<Instance> instancesToBeChosen=new ArrayList<>();
            if(CollectionUtils.isEmpty(sameClustInstances)){
                instancesToBeChosen=instances;
                log.warn("发生跨集群的调用,name={}，clusterName={},instances={}",
                        name,clusterName,instances);
            }else{
                instancesToBeChosen=sameClustInstances;
            }
            //基于权重的负载均衡算法，返回一个实例
            Instance instance = ExtendBalancer.getHostByRandomWeight2(instancesToBeChosen);
            log.info("选择的实例是 port={},instance={}",instance.getPort(),instance.getClusterName());
            return new NacosServer(instance);
        } catch (NacosException e) {
            log.error("发生异常了：",e);
            e.printStackTrace();
            return null;
        }
    }
}

class ExtendBalancer extends Balancer {
    public static Instance getHostByRandomWeight2(List<Instance> hosts){
        return getHostByRandomWeight(hosts);
    }
}
