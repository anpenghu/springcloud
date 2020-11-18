package com.atguigu.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationContextConfig {
	
	@Bean
	//配置的项目名称不能找到某个具体的服务器来进行服务,
	// 需要开启RestTemplate的负载均衡,使用默认的负载均衡机制来找到具体的某个服务器进行处理
	@LoadBalanced
	public RestTemplate getRestTemplate(){
		return new RestTemplate();
	}
}
