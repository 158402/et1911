package com.etoak.bean;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor //全参构造方法
public class PageVo<T> {

	private int pageNum;// 页码 当前页

	private int pageSize; // 每页显示记录数

	private List<T> rows; // 数据

	private long total; // 数据总量

	private int pageCount; // 总页数
	
	private boolean first; //是否第一页
	
	private boolean last; //是否最后一页

}
