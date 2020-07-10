package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PaymentController {
	
	@Autowired
	PaymentService paymentService;
	
	@GetMapping("/payment/{id}")
	public CommonResult<Payment> get(@PathVariable("id")long id){
		Payment payment = paymentService.get(id);
		if (payment != null){
			return new CommonResult<>(200,"查询成功",payment);
		} else {
			return new CommonResult<>(404,"找不到该条数据",null);
		}
	}
	
	@PostMapping("/payment")
	public CommonResult create(Payment payment){
		int i = paymentService.create(payment);
		if (i == 1){
			return new CommonResult(200,"插入数据成功",payment);
		} else {
			return new CommonResult(500,"插入失败",null);
		}
	}
	
	@GetMapping("/paymentall")
	public CommonResult getAll(){
		List<Payment> all = paymentService.getAll();
		if (all != null) {
			return new CommonResult(200,"查询成功",all);
		} else {
			return new CommonResult(500,"查询失败",null);
		}
	}
}
