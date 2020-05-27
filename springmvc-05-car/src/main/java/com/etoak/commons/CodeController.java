package com.etoak.commons;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CodeController {
	@GetMapping("/code")
	public void code(HttpServletRequest request,HttpServletResponse response) throws IOException{
		//创建VerifyCode对象 获得表达式结果 放到session中
		VerifyCode code = new VerifyCode();
		BufferedImage image = code.createImage();
		request.getSession().setAttribute("code",code.getResult()+"");
		
		//向前端写图片
		response.setHeader("Pragma","no-cache");
		response.setHeader("Cache-Control","no-cache");
		response.setDateHeader("Expries",0);
		response.setContentType("image/jpeg");
		ImageIO.write(image, "JPEG", response.getOutputStream());
	}
	
}
