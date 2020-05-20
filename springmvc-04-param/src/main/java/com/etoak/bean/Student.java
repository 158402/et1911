package com.etoak.bean;
import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor //无参构造
@AllArgsConstructor //按照参数顺序生成有参构造方法
public class Student {
	
	private Integer id;
	private String name;
	private Integer age;
	private List<String> hobbyList;
	// 存储id和name两个
	private Map<String,Object> stuMap;
	
	

}
