@rem curl .bat file

@rem --output <file>

@echo.
@echo ---------- getAll() ---------- 
C:\Progra~1\curl\bin\curl.exe -i -s GET http://localhost:8080/topjava/rest/meals

@echo.
@echo  ---------- get() ---------- 
C:\Progra~1\curl\bin\curl.exe -i -s GET http://localhost:8080/topjava/rest/meals/100002

@echo.
@echo  ---------- get() Not found ---------- 
C:\Progra~1\curl\bin\curl.exe -i -s GET http://localhost:8080/topjava/rest/meals/100010

@echo.
@echo  ---------- delete ---------- 
C:\Progra~1\curl\bin\curl.exe -i -s -X DELETE http://localhost:8080/topjava/rest/meals/100002

@echo.
@echo  ---------- Create new meal ---------- 
@rem Запарился с bad request с кавычками пока не нашел https://stackoverflow.com/questions/7172784/how-to-post-json-data-with-curl-from-terminal-commandline-to-test-spring-rest
C:\Progra~1\curl\bin\curl.exe -i --header "Content-Type: application/json;charset=UTF-8" --request POST --data "{\"dateTime\":\"2018-11-26T16:50\",\"description\":\"New Meal\",\"calories\":501}" http://localhost:8080/topjava/rest/meals

@echo.
@echo  ---------- between ---------- 
C:\Progra~1\curl\bin\curl.exe -i -s "http://localhost:8080/topjava/rest/meals/between?startDate=2015-05-30&endDate=2015-05-30&endTime=13:00:00"

@echo.
@echo  ---------- update ---------- 
C:\Progra~1\curl\bin\curl.exe -i --header "Content-Type: application/json" --request PUT --data "{\"dateTime\":\"2015-05-30T13:00\",\"description\":\"Update\",\"calories\":1500}" http://localhost:8080/topjava/rest/meals/100003






