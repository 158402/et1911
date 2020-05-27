package com.etoak.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.ArrayUtils;

import com.etoak.bean.Car;
import com.etoak.bean.CarVo;
import com.etoak.bean.PageVo;
import com.etoak.mapper.CarMapper;
import com.etoak.service.CarService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
@Service
public class CarServiceImpl implements CarService {

	@Autowired
	CarMapper carMapper;
	
	@Override
	public int addCar(Car car) {
		return carMapper.addCar(car);
	}

	@Override
	public PageVo<CarVo> queryList(int pageNum, int pageSize,CarVo carVo,String[] priceList) {
		//处理价格区间
		List<Map<String,Integer>>priceMapList = this.handlePrice(priceList);
		carVo.setPriceMapList(priceMapList);
		
		//处理车龄范围
		this.handleVehicleAge(carVo);
		System.out.println(carVo.getStartYear());
		System.out.println(carVo.getEndYear());
		
		
		//设置分页条件
		PageHelper.startPage(pageNum,pageSize);
		//查询列表
		List<CarVo> carList = carMapper.queryList(carVo);
		System.out.println(carList);
		//创建PageInfo对象 用于获取分页数据
		PageInfo<CarVo>pageInfo = new PageInfo<>(carList);
			
		return new PageVo<CarVo>(pageInfo.getPageNum(),
				pageInfo.getPageSize(),carList,
				pageInfo.getTotal(),
				pageInfo.getPages(),
				pageInfo.isIsFirstPage(),
				pageInfo.isIsLastPage());
	}
	
	
	
	

	private void handleVehicleAge(CarVo carVo) {
		
		// 前端传来的值： 0-1或1-3
				String vehicleAge = carVo.getVehicleAge();
				if(!StringUtil.isEmpty(vehicleAge)){
					// [0,1] 或[1,3] 或 [10,0]
					String[] vehicleAgeAyyay = vehicleAge.split("-");
				if(!("0".equals(vehicleAgeAyyay[0]))){
					carVo.setEndYear(Integer.parseInt(vehicleAgeAyyay[0]));
				}
					
				if(!("0".equals(vehicleAgeAyyay[1]))){
					carVo.setStartYear(Integer.parseInt(vehicleAgeAyyay[1]));
				}
					
			}
	}

	//处理价格区间问题
		private List<Map<String,Integer>> handlePrice(String[] priceList){
			List<Map<String,Integer>>priceMapList = new ArrayList<>();
				if(!ArrayUtils.isEmpty(priceList)){
					for(String priceStr:priceList){
						String[] prices = priceStr.split("-");
						Map<String,Integer>priceMap = new HashMap<>();
						priceMap.put("start",Integer.parseInt(prices[0]));
						priceMap.put("end",Integer.parseInt(prices[1]));
						priceMapList.add(priceMap);
					}
				}
				return priceMapList;
			}
			

	@Override
	public List<String> getAllBrand() {
		return carMapper.getAllBrand();
	}

	@Override
	public List<String> getSeriesByBrand(String brand) {
		return carMapper.getSeriesByBrand(brand);
	}
	
	

}
