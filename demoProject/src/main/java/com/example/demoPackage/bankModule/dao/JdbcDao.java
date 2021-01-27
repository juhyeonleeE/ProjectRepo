package com.example.demoPackage.bankModule.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.dao.support.DataAccessUtils;
import com.example.demoPackage.bankModule.dto.dto;

@Repository
public class JdbcDao implements dao{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<dto> ListFind() {
		return jdbcTemplate.query(
				"SELECT YEAR, ACCOUNT_NAME, ACCOUNT_NUM, TOTAL_AMOUNT\r\n" + 
				"FROM (\r\n" + 
				"              SELECT TH.YEAR, AI.ACCOUNT_NAME, TH.ACCOUNT_NUM, TH.TOTAL_AMOUNT, RANK() OVER (PARTITION BY YEAR ORDER BY TOTAL_AMOUNT DESC) AS RANKING\r\n" + 
				"              FROM (\r\n" + 
				"                            SELECT LEFT(TRAN_DATE, 4) AS YEAR, ACCOUNT_NUM, ( SUM(AMOUNT) - SUM(FEES) ) AS TOTAL_AMOUNT\r\n" + 
				"                            FROM TRAN_HISTORY \r\n" + 
				"                            GROUP BY LEFT(TRAN_DATE, 4), ACCOUNT_NUM, CANCEL_FLAGE\r\n" + 
				"                            HAVING CANCEL_FLAGE = 'N'\r\n" + 
				"                          ) AS TH\r\n" + 
				"                         JOIN ACCOUNT_INFO AS AI\r\n" + 
				"                         ON TH.ACCOUNT_NUM = AI.ACCOUNT_NUM \r\n" + 
				"              WHERE YEAR = 2018 OR YEAR = 2019\r\n" + 
				"            )\r\n" + 
				"WHERE RANKING = 1;"
				, (rs, rowNum) -> new dto( 
						rs.getInt("YEAR"), 
						rs.getString("ACCOUNT_NAME"), 
						rs.getInt("ACCOUNT_NUM"), 
						rs.getLong("TOTAL_AMOUNT") )
				);
	}
	
	@Override
	public List<dto> ListFind2() {
		return jdbcTemplate.query(
				"SELECT *\r\n" + 
				"FROM (\r\n" + 
				"              SELECT LEFT(TH.TRAN_DATE, 4) AS YEAR, AI.ACCOUNT_NAME, TH.ACCOUNT_NUM \r\n" + 
				"              FROM TRAN_HISTORY AS TH JOIN ACCOUNT_INFO AS AI ON TH.ACCOUNT_NUM = AI.ACCOUNT_NUM\r\n" + 
				"              GROUP BY LEFT(TH.TRAN_DATE, 4), TH.ACCOUNT_NUM, TH.CANCEL_FLAGE\r\n" + 
				"              HAVING TH.CANCEL_FLAGE = 'Y'\r\n" + 
				"            )\r\n" + 
				"WHERE YEAR = 2018 OR YEAR = 2019;"
				, (rs, rowNum) -> new dto( 
						rs.getInt("YEAR"), 
						rs.getString("ACCOUNT_NAME"), 
						rs.getInt("ACCOUNT_NUM") )
				);
	}
	
	@Override
	public List<dto> ListFind3() {
		return jdbcTemplate.query(
				"SELECT LEFT(TH.TRAN_DATE, 4) AS YEAR, B.BRANCH_NAME,  AI.BRANCH_CODE, SUM(TH.AMOUNT) AS TOTAL_AMOUNT\r\n" + 
				"FROM (\r\n" + 
				"              SELECT TRAN_DATE, ACCOUNT_NUM, AMOUNT, FEES\r\n" + 
				"              FROM TRAN_HISTORY \r\n" + 
				"              WHERE CANCEL_FLAGE = 'N'\r\n" + 
				"            ) AS TH\r\n" + 
				"            JOIN ACCOUNT_INFO AS AI\r\n" + 
				"            ON TH.ACCOUNT_NUM = AI.ACCOUNT_NUM\r\n" + 
				"            JOIN BRANCH AS B\r\n" + 
				"            ON AI.BRANCH_CODE= B.BRANCH_CODE\r\n" + 
				"GROUP BY LEFT(TH.TRAN_DATE, 4), AI.BRANCH_CODE, B.BRANCH_NAME\r\n" + 
				"ORDER BY YEAR, TOTAL_AMOUNT DESC;"
				, (rs, rowNum) -> new dto( 
						rs.getInt("YEAR"), 
						rs.getString("BRANCH_NAME"), 
						rs.getString("BRANCH_CODE"),
						rs.getLong("TOTAL_AMOUNT") )
				);
	}

	@Override
	public dto singleFind(HashMap<String,Object> map) {
		
		String BRANCH_NAME = (String) map.get("BrName");
		
		List<dto> TempList = jdbcTemplate.query(
				"SELECT B.BRANCH_NAME, B.BRANCH_CODE, SUM(TH.AMOUNT) AS TOTAL_AMOUNT\r\n" + 
				"FROM (\r\n" + 
				"              SELECT BRANCH_CODE, BRANCH_NAME \r\n" + 
				"              FROM (\r\n" + 
				"                           SELECT (CASE  BRANCH_CODE WHEN 'B' THEN 'A' ELSE BRANCH_CODE END) AS BRANCH_CODE,  \r\n" + 
				"                                  (CASE  BRANCH_NAME WHEN '분당점' THEN '판교점' ELSE BRANCH_NAME END) AS BRANCH_NAME\r\n" + 
				"                           FROM BRANCH\r\n" + 
				"                           GROUP BY BRANCH_CODE, BRANCH_NAME\r\n" + 
				"                          )\r\n" + 
				"              WHERE  BRANCH_NAME = ? \r\n" + 
				"            ) AS B\r\n" + 
				"            JOIN \r\n" + 
				"            (\r\n" + 
				"              SELECT ACCOUNT_NUM,  ACCOUNT_NAME, \r\n" + 
				"                     (CASE  BRANCH_CODE WHEN 'B' THEN 'A' ELSE BRANCH_CODE END) AS BRANCH_CODE\r\n" + 
				"              FROM ACCOUNT_INFO \r\n" + 
				"            ) AS AI\r\n" + 
				"            ON B.BRANCH_CODE = AI.BRANCH_CODE \r\n" + 
				"            JOIN\r\n" + 
				"            (\r\n" + 
				"              SELECT ACCOUNT_NUM, AMOUNT, FEES\r\n" + 
				"              FROM TRAN_HISTORY \r\n" + 
				"              WHERE CANCEL_FLAGE = 'N'\r\n" + 
				"            ) AS TH\r\n" + 
				"            ON AI.ACCOUNT_NUM = TH.ACCOUNT_NUM\r\n" + 
				"GROUP BY B.BRANCH_NAME, B.BRANCH_CODE;"
				, new Object[] {BRANCH_NAME}
				, (rs, rowNum) -> new dto(
						rs.getString("BRANCH_NAME"), 
						rs.getString("BRANCH_CODE"),
						rs.getLong("TOTAL_AMOUNT") ) 
				);
		System.out.println("TempList:");
		System.out.println(TempList);
		dto singleDto = DataAccessUtils.singleResult(TempList);
		
		return singleDto;
	}

}
