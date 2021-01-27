package com.example.demoPackage.controller;

import com.example.demoPackage.bankModule.dao.*;
import com.example.demoPackage.bankModule.dto.*;
import com.example.demoPackage.bankModule.service.*;

import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
//@RequestMapping("")
public class mainController {
	
	@Autowired
	service service;
	@Autowired
	JdbcDao JdbcDao;

	@RequestMapping(value="/firstList", method= {RequestMethod.GET} )
	@ResponseBody
	private List<dto> list() {
		return JdbcDao.ListFind();
	}
	
	@RequestMapping(value="/secondList", method= {RequestMethod.GET} )
	@ResponseBody
	private List<dto> list2() {
		return JdbcDao.ListFind2();
	}
	
	@RequestMapping(value="/thirdList", method= {RequestMethod.GET} )
	@ResponseBody
	private List<Map<String, Object>> list3() {
		return service.answer3();
	}
	
//	http://localhost:8000/fourthList?year=3&BrName=분당점       :  분당점  :으로 출력
//	@RequestMapping(value="/fourthList", method= {RequestMethod.GET})
//	@ResponseBody
//	private dto singleFind(@RequestParam(value = "year") Integer msg, @RequestParam(value = "BrName") String map) {
//		System.out.println("----------------------------");
//		System.out.println(map);
//		return service.answer4();
//	}
	
//	http://localhost:8000/fourthList/3 일시 동작    {year=3}으로 전달
//	@RequestMapping(value="/fourthList/{year}", method= {RequestMethod.GET})
//	@ResponseBody
//	private dto singleFind(@PathVariable HashMap<String,Object> map) {
//		System.out.println("----------------------------");
//		System.out.println(map);
//		return service.answer4();
//	}
	
	@RequestMapping(value="/fourthList", method= {RequestMethod.POST}, headers = "Accept=application/json" )
	@ResponseBody
	private dto singleFind(@RequestBody HashMap<String,Object> map) {		
			return service.answer4(map);
	}
	
}