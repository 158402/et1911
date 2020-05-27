package com.etoak.bean;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarVo extends Car {
	//级别名称
	private String levelName;
	//变速箱名称
	private String gearboxName;
	//排量
	private String outputVolumeName;
	 //传到sql中的条件
	@JsonIgnore //springmvc再返回结果是不封装被@JsonIgnore注解的属性
	private List<Map<String,Integer>>priceMapList;

	// 时间范围 接收前段车龄条件 0-1或1-3
	@JsonIgnore //这个字段只能在后端使用 不会返回到前段
	private String vehicleAge;

	//传到sql中的条件 
	@JsonIgnore
	private Integer startYear;
	//传到sql中的条件 
	@JsonIgnore
	private Integer endYear;

}
