package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderController {
	//private final static String PAYMENT_URL = "http://localhost:8001";//不集群
	private final static String PAYMENT_URL = "http://cloud-payment-service";//集群
	
	@Resource
	private RestTemplate restTemplate;
	
	@PostMapping("/consumer/payment")
	private CommonResult<Payment> create(Payment payment){
		log.info(payment.getSerial());
		return restTemplate.postForObject(PAYMENT_URL + "/payment",payment, CommonResult.class);
	}
	
	@GetMapping("/consumer/payment/{id}")
	public CommonResult<Payment> getPaymentById(@PathVariable("id") long id){
		log.info("查询3");
		return restTemplate.getForObject(PAYMENT_URL + "/payment/"+id,CommonResult.class);
	}
}
