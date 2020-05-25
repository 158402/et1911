package com.etoak.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.etoak.bean.Car;
import com.etoak.bean.CarVo;
import com.etoak.bean.PageVo;
import com.etoak.exception.ParamException;
import com.etoak.service.CarService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/car")
@Slf4j
public class CarController {
	
	@Autowired
	CarService carService;
	
	//跳转到车辆添加页面
	@RequestMapping("/toAdd")
	public String toAdd(){
		return "car/add";
	}
	
	@RequestMapping("/toList")
	public String toList() {
		return "car/list";
	}

	/**
	 * 跳转到车辆列表页面：webapp/car/list.html
	 * 
	 * @return
	 */
	
	@GetMapping("/list")
	@ResponseBody //@ResponseBody:写@ResponseBody相当于给前端一个值 不写就相当于跳转页面 
	public PageVo<CarVo> queryList(@RequestParam(required = false,defaultValue = "1")int pageNum,
			@RequestParam(required = false,defaultValue="8")int pageSize,
				CarVo carVo){
		return carService.queryList(pageNum, pageSize, carVo);
	}
	
	
	@RequestMapping("/add")
	public String add(MultipartFile file,@Valid Car car, BindingResult bindingResult,HttpServletRequest request)
			throws IllegalStateException, IOException{//@Valid 让写在Car里面注解生效
		
		String originalFilename = file.getOriginalFilename();
		
		
		log.info("文件名称 - {}",file.getOriginalFilename());
		log.info("param car - {}",car);
		
		//先校验Car
		//获取所有校验结果
		List<ObjectError> allErrors = bindingResult.getAllErrors();
		//如果校验结果集不为空取出校验信息
		if(!CollectionUtils.isEmpty(allErrors)){
			StringBuffer errorBuf = new StringBuffer();
			
			for(ObjectError error:allErrors){	
				String errorMsg = error.getDefaultMessage();
				errorBuf.append(errorMsg).append(";");
				
			}
			//把错误信息写到request域，使用请求转发跳转到车辆添加页面
			
			throw new ParamException(errorBuf.toString());
			/*request.setAttribute("paramError",errorBuf.toString());
			return "forward:/car/toAdd";
			*/
		}
		
		//新文件名
		String uuid = UUID.randomUUID().toString().replaceAll("-","");
		
		String newFilename = uuid +"_" + originalFilename;
		//目标文件d:/upload/uuid_老文件名.jpg
		File destFile = new File("d:/upload",newFilename);
		//执行上传
		file.transferTo(destFile);
		//设置图片地址
		car.setPic("/pic/"+newFilename);
		//调用
		carService.addCar(car);
		
		//重定向方式返回页面
		return "redirect:/car/toAdd";
	}
	
	


	/**
	 * 查询品牌列表
	 * 
	 * @return
	 */
	@GetMapping("/getBrand")
	@ResponseBody
	public List<String> queryBrandList() {
		return carService.getAllBrand();
	}

	/**
	 * 查询品牌对应的车系列表 如果品牌为空随机获得10个车系
	 * 
	 * @param brand:非必填参数
	 * @return
	 */
	@GetMapping("/getSeries")
	@ResponseBody
	public List<String> getSeries(String brand) {
		return carService.getSeriesByBrand(brand);
	}

	
	
}
