package com.etoak;


import com.etoak.service.HelloService;
import com.etoak.service.HelloServiceWS;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

public class CxfClient {

    public static void main(String[] args) {
        //
        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        //设置wsdl地址
        factory.setAddress("http://localhost:8000/hello?wsdl");
        //设置服务接口
        factory.setServiceClass(HelloService.class);
        //创建远程服务代理
        HelloService service = (HelloService) factory.create();
        //调用远程方法
        System.out.println(factory);
        System.out.println("CXF");
    }

}
