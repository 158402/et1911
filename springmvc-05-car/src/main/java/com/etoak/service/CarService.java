package com.etoak.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.etoak.bean.Car;
import com.etoak.bean.CarVo;
import com.etoak.bean.PageVo;

public interface CarService {
	//添加车辆信息
	int addCar(Car car);
	
	//车辆列表查询
	PageVo<CarVo> queryList(int pageNum, int pageSize,CarVo carVo, String[] priceList);

	//获取所有品牌
	List<String>getAllBrand();
	//根据品牌查询车系 品牌可能为空 品牌为空随机查询10条车系
	List<String>getSeriesByBrand(@Param("brand")String brand);
}
