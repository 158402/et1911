package com.etoak.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.etoak.action.UserAction;
import com.etoak.service.UserService;

@Configuration  //相当于xml的根元素<beans> 表示这是一个springIOC容器 
public class UserConfig {
	//使用@bean注册spring bean
	//返回类型相当于<bean>标签的class属性 
	//方法名被认为<bean>标签id属性
	@Bean 
	public UserService userService(){
		return new UserService();		
	}
	
	@Bean						//相当于ref 将service方法注入到Action
	public UserAction userAction(@Qualifier("userService")
	UserService userService){
		UserAction userAction = new UserAction();
		//配合@Qualifier使用
		userAction.setUserService(userService);
		return userAction;
	}
}
