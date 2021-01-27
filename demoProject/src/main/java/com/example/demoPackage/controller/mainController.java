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
	
	@RequestMapping(value="/fourthList", method= {RequestMethod.POST}, headers = "Accept=application/json" )
	@ResponseBody
	private dto singleFind(@RequestBody HashMap<String,Object> map) {		
			return service.answer4(map);
	}
	
}