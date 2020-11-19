package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class PaymentController {
	
	@Autowired
	PaymentService paymentService;
	
	@Value("${server.port}")
	private String serverport;
	
	//discoveryClient可以获取到注册中心的服务信息,该类是springcloud的注解,不是Netflix的
	@Autowired
	private DiscoveryClient discoveryClient;
	
	@GetMapping("/payment/{id}")
	public CommonResult<Payment> get(@PathVariable("id")long id){
		Payment payment = paymentService.get(id);
		if (payment != null){
			return new CommonResult<>(200,"查询成功,端口:" + serverport,payment);
		} else {
			return new CommonResult<>(404,"找不到该条数据",null);
		}
	}
	
	@PostMapping("/payment")
	public CommonResult create(@RequestBody Payment payment){
		int i = paymentService.create(payment);
		if (i == 1){
			log.info("插入");
			return new CommonResult(200,"插入数据成功,端口:" + serverport,payment);
		} else {
			return new CommonResult(500,"插入失败",null);
		}
	}
	
	@GetMapping("/paymentall")
	public CommonResult getAll(){
		List<Payment> all = paymentService.getAll();
		if (all != null) {
			return new CommonResult(200,"查询成功,端口:" + serverport,all);
		} else {
			return new CommonResult(500,"查询失败",null);
		}
	}
	
	@GetMapping("/payment/discovery")
	public Object discovery(){
		//获取所有注册的服务
		List<String> services = discoveryClient.getServices();
		services.stream().forEach(log::info);
		
		//获取指定服务的所有实例
		List<ServiceInstance> instances = discoveryClient.getInstances("cloud-payment-service");
		instances.stream().forEach(a -> {
			log.info("serverid:" + a.getServiceId() + ", host:" + a.getHost() + ", port:" + a.getPort() + ", uri:" + a.getUri());
		});
		return discoveryClient;
	}
}
