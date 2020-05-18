package com.etoak;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.etoak.action.UserAction;
import com.etoak.bean.User;
import com.etoak.config.UserConfig;

public class JavaConfigTest {

	public static void main(String[] args) {
		ApplicationContext ioc = new 
		AnnotationConfigApplicationContext(UserConfig.class);
		UserAction UserAction = ioc.getBean(UserAction.class);
		User User = UserAction.getById(100);
		System.out.println(User.getId()+"==="+User.getName());
	}

}
