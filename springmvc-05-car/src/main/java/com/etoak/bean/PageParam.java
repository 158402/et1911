package com.etoak.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 为了测试pageHelper的params属性而增加的类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageParam {

	private int pageNumber = 1;

	private int pages = 10;

}
