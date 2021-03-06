package com.etoak.controller;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.etoak.bean.Dict;
import com.etoak.service.DictService;

import ch.qos.logback.classic.Logger;


@RestController  //等于 ResponseBody + controller
@RequestMapping("/dict")
public class DictController {
	
	private static final Logger log = (Logger) LoggerFactory.getLogger(DictController.class);
	
	@Autowired 
	DictService dictService;
	
	/**   
	 * spring mvc 提供了一个注解@PathVariable：把请求路径当做参数
	 * **/ 
	@GetMapping("/{groupId}")//groupId:相当于对应数据库的任意字段 都可以进行查询字典
	public List<Dict> queryList(@PathVariable String groupId){
		log.info("param groupId - {}",groupId);
		return dictService.queryList(groupId);
	}
	
}






















