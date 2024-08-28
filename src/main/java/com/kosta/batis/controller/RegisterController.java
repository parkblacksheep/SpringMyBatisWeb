package com.kosta.batis.controller;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kosta.batis.dao.UserDao;
import com.kosta.batis.domain.User;
import com.kosta.batis.domain.UserValidator;

import jakarta.validation.Valid;



@Controller
@RequestMapping("/register")
public class RegisterController {
	@Autowired
	UserDao userDao;
	
	final int FAIL=0;
	
	//@RequestMapping(value = "/register/add" , method = {RequestMethod.GET,RequestMethod.POST})
	@GetMapping(value = "/add")
	public String register() {
		return "registerForm";	// /WEB-INF/views/registerForm.jsp
	}
	@InitBinder	//WebDataBinder에 Validator  등록함(WebDataBinder에 데이터 검증기를 등록함)
	public void registerValidate(WebDataBinder binder) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(df, false));	//날짜 형식을 엄격하게 처리 
		binder.setValidator(new UserValidator());	 //UserValidator를 WebDataBinder의 validator로 등록
		
		List<Validator> validatorList= binder.getValidators();
		System.out.println("validatorList ="+validatorList);			//검증기 리스트 출력 
	}
	
	@PostMapping("/add")
	public String save(@Valid User user,BindingResult result, Model m) {
		System.out.println("result=" + result);
		System.out.println("user=" + user);
		
		//DB에 회원 정보 저장 
		if(!result.hasErrors()) {
			int rowCnt =userDao.insertUser(user);
			if(rowCnt !=FAIL) return "registerinfo";	//	/WEB-INF/views/resterinfo.jsp
		}
		return "registerinfo";	//오류가 있을 경우 폼 페이지로 다시 돌아감 
	}
}
