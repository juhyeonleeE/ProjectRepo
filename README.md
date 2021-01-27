# ProjectRepo

## 목차
 - 개발 프레임워크 및 데이터베이스
 - 문제해결 방법
 - 빌드 및 실행방법
 
## 개발 프레임워크 및 데이터베이스
 - eclipse / spring boot / data-jdbc / h2
     
## 문제해결 방법
### 1. 2018년, 2019년 각 연도별 합계 금액이 가장 많은 고객을 추출 
해결 방식
쿼리문 이용 - 연도별로 그룹화 한 후, 년도별 합계금액이 가장 높은 고객을 추출 (2018, 2019년도 데이터만 추출)
 - requset URL
 http://localhost:8000/firstList
 - 출력
 ```json
 [
     {
         "year": 2018,
         "name": "테드",
         "acctNo": 11111114,
         "sumAmt": 28992000
     },
     {
         "year": 2019,
         "name": "에이스",
         "acctNo": 11111112,
         "sumAmt": 40998400
     }
 ]
 ```

### 2. 2018년 또는 2019년에 거래가 없는 고객을 추출 <br/>    requset URL = http://localhost:8000/secondList 
해결 방식
쿼리문 이용 - 연도별로 그룹화 한 후 거래가 없는 고객을 추출 (2018, 2019년도 데이터만 추출)
  - 출력
 ```json
 [
     {
         "year": 2018,
         "name": "제이",
         "acctNo": 11111111
     },
     {
         "year": 2018,
         "name": "에이스",
         "acctNo": 11111112
     },
     {
         "year": 2018,
         "name": "리노",
         "acctNo": 11111113
     },
     {
         "year": 2018,
         "name": "테드",
         "acctNo": 11111114
     },
     {
         "year": 2018,
         "name": "린",
         "acctNo": 11111116
     },
     {
         "year": 2018,
         "name": "제임스",
         "acctNo": 11111118
     },
     {
         "year": 2018,
         "name": "로이",
         "acctNo": 11111120
     },
     {
         "year": 2019,
         "name": "제이",
         "acctNo": 11111111
     },
     {
         "year": 2019,
         "name": "에이스",
         "acctNo": 11111112
     },
     {
         "year": 2019,
         "name": "리노",
         "acctNo": 11111113
     },
     {
         "year": 2019,
         "name": "제임스",
         "acctNo": 11111118
     }
 ]
 ```
 
 ### 3.연도별 관리점별 거래금액 합계를 구하고 합계금액이 큰 순서로 출력 ( requset URL = http://localhost:8000/thirdList ) 
 해결 방식
 쿼리문 이용 - 연도별로 그룹화 한 후 거래금액이 큰 순서로 추출
 서비스 로직 이용 - 쿼리 결과를 연도별로 구조에 맞게 변경 (map, list이용)
 - 출력
 ```json
 [
     {
         "year": 2018,
         "dataList": [
             {
                 "brName": "분당점",
                 "brCode": "B",
                 "sumAmt": 38500000
             },
             {
                 "brName": "판교점",
                 "brCode": "A",
                 "sumAmt": 20510000
             },
             {
                 "brName": "강남점",
                 "brCode": "C",
                 "sumAmt": 20234567
             },
             {
                 "brName": "잠실점",
                 "brCode": "D",
                 "sumAmt": 14000000
             }
         ]
     },
     {
         "year": 2019,
         "dataList": [
             {
                 "brName": "판교점",
                 "brCode": "A",
                 "sumAmt": 66800000
             },
             {
                 "brName": "분당점",
                 "brCode": "B",
                 "sumAmt": 45400000
             },
             {
                 "brName": "강남점",
                 "brCode": "C",
                 "sumAmt": 19500000
             },
             {
                 "brName": "잠실점",
                 "brCode": "D",
                 "sumAmt": 6000000
             }
         ]
     },
     {
         "year": 2020,
         "dataList": [
             {
                 "brName": "을지로점",
                 "brCode": "E",
                 "sumAmt": 1000000
             }
         ]
     }
 ]
 ```
 
### 4. 분당점과 판교점 통폐합하여 판교점으로 이관 지점명을 입력하면 해당 지점에 대한 거래금액 합계를 출력 ( requset URL = http://localhost:8000/fourthList )
해결 방식
쿼리문 이용 - 분당점의 지점코드/지점이름을 Inline view를 통해 변경, 이후 입력된 관리점 이름에 따른 데이터만 선택 후 출력
서비스 로직 이용 - 쿼리결과가 없으면 404에러 발생시킨 후, 정해진 json문구로 출력해줌
 - 입력
 ```
 { "BrName": "판교점"	}
 ```
 - 출력
 ```json
 {
     "brName": "판교점",
     "brCode": "A",
     "sumAmt": 171210000
 }
 ```
  - 입력
 ```
 { "BrName": "분당점"	}
 ```
 - 출력
 ```json
 {
     "code": "404",
     "메세지": "br cod not found error"
 }
 ```
 
 ## 빌드 및 실행방법
 - 이클립스에서 https://github.com/juhyeonleeE/ProjectRepo.git 에서 clone -> maven build -> DemoProjectApplication.java를 "Spring Boot App"으로 실행
 - https://drive.google.com/drive/u/1/folders/1lSK4k0iZQG-vwOeBbq-TL5YxO575C3L1 에서 KakaoProject.zip 파일 다운로드 및 압축풀기 -> 이클립스에서 Project를 Import -> maven build -> DemoProjectApplication.java를 "Spring Boot App"으로 실행
 - maven과 Spring Boot App 잘 작동되지 못하는 경우가 있습니다. 여러번 시도하면 해결되었습니다.
