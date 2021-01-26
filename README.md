# ProjectRepo
목차
개발 프레임워크
빌드 및 실행방법
문제해결 방법

개발 프레임워크
eclipse / spring boot / data-jdbc / h2

문제해결 방법
1. 2018년, 2019년 각 연도별 합계 금액이 가장 많은 고객을 추출 / http://localhost:8000/firstList
response
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
문제해결 방법
쿼리문 이용 - 연도별로 그룹화 한 후, 년도별 합계금액이 가장 높은 고객을 추출 (2018, 2019년도 데이터만 추출)
2. 2018년 또는 2019년에 거래가 없는 고객을 추출 / http://localhost:8000/secondList
response
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
문제해결 방법
쿼리문 이용 - 연도별로 그룹화 한 후 거래가 없는 고객을 추출 (2018, 2019년도 데이터만 추출)
3.연도별 관리점별 거래금액 합계를 구하고 합계금액이 큰 순서로 출력 / http://localhost:8000/thirdList
response
[
    {
        "year": 2018,
        "dataList": [
            {
                "brName": "분당점",
                "brCode": "B",
                "sumAmt": 38484000
            },
            {
                "brName": "판교점",
                "brCode": "A",
                "sumAmt": 20505700
            },
            {
                "brName": "강남점",
                "brCode": "C",
                "sumAmt": 20232867
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
                "sumAmt": 66795100
            },
            {
                "brName": "분당점",
                "brCode": "B",
                "sumAmt": 45396700
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
문제해결 방법
쿼리문 이용 - 연도별로 그룹화 한 후 거래금액이 큰 순서로 추출 
서비스 로직 이용 - 쿼리 결과를 (년도별)json 구조에 맞게 변경
4. 분당점과 판교점 통폐합하여 판교점으로 이관 지점명을 입력하면 해당 지점에 대한 거래금액 합계를 출력하는 API개발하시오
4-1) http://localhost:8000/fourthList / 입력데이터: { "BrName": "분당점" }
4-2) http://localhost:8000/fourthList / 입력데이터:{ "BrName": "★분당점" }
response

문제해결 방법
쿼리문 이용 - 관리점 이름을 받아 ~~
서비스 로직 이용 - 쿼리결과가 없으면 404에러 발생

빌드 및 실행방법
git clone(https://github.com/juhyeonleeE/ProjectRepo) -> maven build -> DemoProjectApplication.javav 실행(Spring Boot App으로 실행) -> http://localhost:8000/ 접속
