package com.etoak;

import com.etoak.service.impl.HelloServiceImpl;

import javax.xml.ws.Endpoint;

public class jdkWsServer {

    public static void main(String[] args) {
        /*
        * 两个参数
        * 参数一：发布地址
        * 参数二：暴露的服务
        * */
        Endpoint.publish("http://localhost:8080/hello",new HelloServiceImpl());

        System.out.println("Server hahaha");


    }
}

