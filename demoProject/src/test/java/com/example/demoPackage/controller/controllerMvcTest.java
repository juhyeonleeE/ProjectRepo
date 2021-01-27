package com.example.demoPackage.controller;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;

@RunWith(MockitoJUnitRunner.class)
public class controllerMvcTest {
    @InjectMocks
    private mainController mainController;
    private MockMvc MockMvc;
    
    @Before
	public void setUp() {
	}
    
    @Test
    public void listTest() throws Exception {
    	
    	HashMap<String, Object> senderMap = new HashMap<String, Object>();
		senderMap.put("BrName", "분당점");
		senderMap.put("year", 1234);
    	
    	MockMvc.perform(
    			MockMvcRequestBuilders
    			.get("/firstList")
    			.accept(MediaType.APPLICATION_JSON)
    			)
    	.andDo(MockMvcResultHandlers.print()) // 응답 데이터 출력
    	.andExpect(status().isOk())
    	.andExpect(content().contentType(MediaType.APPLICATION_JSON)) // Content Type을 검사
    	.andExpect(jsonPath("$.$.year").value(2018)) // Json property, value 검사
    	.andExpect(content().json( new ObjectMapper().writeValueAsString(senderMap) )); // 동일한 Json 인지 검사
    }
  //{"year":2018,"name":"테드","acctNo":11111114,"sumAmt":28992000}
    
    @Test
    public void list2Test() throws Exception {
    	MockMvc.perform(
    			MockMvcRequestBuilders
    			.get("/secondList")
    			.accept(MediaType.APPLICATION_JSON)
    			)
    	.andDo(MockMvcResultHandlers.print()) // 응답 데이터 출력
    	.andExpect(status().isOk())
    	.andExpect(content().contentType(MediaType.APPLICATION_JSON)); // Content Type을 검사
    }
    
    @Test
    public void list3Test() throws Exception {
    	MockMvc.perform(
    			MockMvcRequestBuilders
    			.get("/thirdList")
    			.accept(MediaType.APPLICATION_JSON)
    			)
    	.andDo(MockMvcResultHandlers.print()) // 응답 데이터 출력
    	.andExpect(status().isOk())
    	.andExpect(content().contentType(MediaType.APPLICATION_JSON)); // Content Type을 검사
    }
    
    @Test
    public void list4Test() throws Exception {
    	
    	HashMap<String, Object> senderMap = new HashMap<String, Object>();
		senderMap.put("BrName", "분당점");
		senderMap.put("year", 1234);
    	
    	MockMvc.perform(
    			MockMvcRequestBuilders
    			.post("/fourthList")
    			.accept(MediaType.APPLICATION_JSON)
    			.content(new ObjectMapper().writeValueAsString(senderMap)) //Body 삽입
    			)
    	.andDo(MockMvcResultHandlers.print()) // 응답 데이터 출력
    	.andExpect(status().isOk())
    	.andExpect(content().contentType(MediaType.APPLICATION_JSON)) // Content Type을 검사
    	.andExpect(content().json( new ObjectMapper().writeValueAsString(senderMap) )); // 동일한 Json 인지 검사
    }
}
