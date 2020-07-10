package com.atguigu.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
//@Data注解自动生成set和get方法
@AllArgsConstructor
//生成全参构造器
@NoArgsConstructor
//生成无参构造器
public class Payment implements Serializable {
    private long id;
    private String serial;

}
