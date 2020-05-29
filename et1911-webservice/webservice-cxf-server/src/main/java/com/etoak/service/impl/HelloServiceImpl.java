package com.etoak.service.impl;

import com.etoak.service.helloService;

import javax.jws.WebService;

@WebService(serviceName = "HelloServiceWS",portName = "HelloServiceWSPort")

public class HelloServiceImpl implements helloService {
    @Override
    public String sayHello(String name) {
        System.out.println("服务端被调用了");
        return "Hello " + name;
    }
}
