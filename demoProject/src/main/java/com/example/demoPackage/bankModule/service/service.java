package com.example.demoPackage.bankModule.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

import com.example.demoPackage.bankModule.dao.*;
import com.example.demoPackage.bankModule.dto.dto;
import com.example.demoPackage.exception.NotFoundException;

@Service
public class service {
	
	@Autowired
    dao dao;
	
	public List<Map<String, Object>> answer3(){
		
		List<Map<String, Object>> resultLsit = new ArrayList<>();
		List<dto> responseData = dao.ListFind3();
		
		Map<Integer, Object> yaersMap = new HashMap<Integer, Object>();
		Map<Integer, List<dto>> dataListMap = new HashMap<Integer, List<dto>>();
		
		for(int i=0; i < responseData.size(); i++) {
			Integer yearKey = responseData.get(i).getYear();
			
			if( dataListMap.get(yearKey) == null ) {      // 년도별 HashMap구조체 생성
				yaersMap.put(yearKey, yearKey);
				List<dto> dataList = new ArrayList<dto>();
				dataListMap.put(yearKey, dataList);
			}
			
			// 생성된 구조체중 Map<Integer, List<dto>>의 value(List<dto>)에 dto넣어 리스트 만들기
			dto singleDto = responseData.get(i);
			List<dto> getlsit = dataListMap.get( singleDto.getYear() );
			getlsit.add( new dto( singleDto.getBrName(), singleDto.getBrCode(), singleDto.getSumAmt() ) );
			
			
			if(  i != 0  ) {
				Integer currentYear = responseData.get(i).getYear();
				Integer previousYear = responseData.get(i-1).getYear();
				
				if( !currentYear.equals(previousYear) ) {            // 년도가 바뀌면 출력할 구조체에 삽입
					Map<String, Object> TotalYaersMap = new HashMap<String, Object>();
					TotalYaersMap.put( "year", yaersMap.get(previousYear) );
					TotalYaersMap.put( "dataList", dataListMap.get(previousYear) );
					resultLsit.add(TotalYaersMap);
				}
				if( i == responseData.size() - 1 ) {          // 마지막행 처리
					Map<String, Object> TotalYaersMap = new HashMap<String, Object>();
					TotalYaersMap.put( "year", yaersMap.get(currentYear) );
					TotalYaersMap.put( "dataList", dataListMap.get(currentYear) );
					resultLsit.add(TotalYaersMap);
				}
			}
		}
		
		return resultLsit;
	}
	
	public dto answer4(HashMap<String,Object> map){
				
		dto responseData = dao.singleFind(map);
		
		if(responseData == null) {
			throw new NotFoundException("404", "br cod not found error");
		}
		
		return responseData;
	}	
	
}
