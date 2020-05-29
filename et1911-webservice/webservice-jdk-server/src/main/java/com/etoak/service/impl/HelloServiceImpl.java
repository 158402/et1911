package com.etoak.service.impl;

import javax.jws.WebService;

@WebService
public class HelloServiceImpl implements com.etoak.service.HelloService {


    @Override
    public String sayHello(String name) {
        System.out.println("sayHello invoke");
        return "Hello" +name;
    }
}
