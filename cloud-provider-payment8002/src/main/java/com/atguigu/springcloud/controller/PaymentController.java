package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class PaymentController {
	
	@Autowired
	PaymentService paymentService;
	
	@Value("${server.port}")
	private String serverport;
	
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
}
