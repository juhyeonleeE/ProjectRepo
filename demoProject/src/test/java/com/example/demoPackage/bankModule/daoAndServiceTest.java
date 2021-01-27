package com.example.demoPackage.bankModule;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//import org.springframework.beans.factory.annotation.Autowired;
import com.example.demoPackage.bankModule.dto.dto;
import com.example.demoPackage.bankModule.dao.JdbcDao;
import com.example.demoPackage.bankModule.service.service;;

public class daoAndServiceTest {
	
//	@Autowired
	private JdbcDao JdbcDao;
	private service service;
	
	private dto dtoOne = null;
	private dto dtoTwo = null;
	
	@Before
	public void setUp() {
		JdbcDao = new JdbcDao();
		service = new service();
	}
	
	@Test
	public void getService() {
		List<dto> goalVariable = new ArrayList<dto>();
		
		dtoOne.setYear(2018);
		dtoOne.setName("테드");
		dtoOne.setAcctNo(11111114);
		Long sumAmt1 = new Long(28992000);
		dtoOne.setSumAmt(sumAmt1); 
		
		dtoTwo.setYear(2019);
		dtoTwo.setName("에이스");
		dtoTwo.setAcctNo(11111112);
		Long sumAmt2 = new Long(28992000);
		dtoTwo.setSumAmt(sumAmt2); 
		
		
		goalVariable.add(dtoOne);
		goalVariable.add(dtoTwo);
		
		assertEquals(goalVariable, JdbcDao.ListFind());
	}
	
	@Test
	public void getAnswer4() {
		HashMap<String, Object> senderMap = new HashMap<String, Object>();
		
		senderMap.put("BrName", "분당점");
		senderMap.put("year", 1234);
		senderMap.put("name", "dfdf");
		senderMap.put("acctNo", 5555);
		
		HashMap<String, Object> testMap = new HashMap<String, Object>();
		
		testMap.put("brName", "분당점");
		testMap.put("brCode", "B");
		testMap.put("sumAmt", 83880700);
		
		
		assertEquals(testMap, service.answer4(senderMap));
	}
	
	@Test
	public void getAnswer4_2() {
		HashMap<String, Object> senderMap = new HashMap<String, Object>();
		
		senderMap.put("BrName", "!분당점!");
		senderMap.put("year", 1234);
		senderMap.put("name", "dfdf");
		senderMap.put("acctNo", 5555);
		
		HashMap<String, Object> testMap = new HashMap<String, Object>();
		
		testMap.put("code", "404");
		testMap.put("메세지", "br cod not found error");
		
		assertEquals(testMap, service.answer4(senderMap));
	}
	
}
