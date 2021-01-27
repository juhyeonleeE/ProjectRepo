package com.example.demoPackage.bankModule.dao;
import com.example.demoPackage.bankModule.dto.dto;

import java.util.HashMap;
import java.util.List;

public interface dao {
	List<dto> ListFind();
	List<dto> ListFind2();
	List<dto> ListFind3();
	dto singleFind(HashMap<String,Object> map);
}
