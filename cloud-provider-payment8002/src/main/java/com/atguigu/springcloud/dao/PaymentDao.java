package com.atguigu.springcloud.dao;

import com.atguigu.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PaymentDao {
	@Options(useGeneratedKeys = true,keyProperty = "id")
	@Insert("insert into payment(serial) values(#{serial})")
	int create(Payment payment);
	
	@Select("select * from payment where id = #{id}")
	Payment get(@Param("id") Long id);
	
	List<Payment> getAll();
}
