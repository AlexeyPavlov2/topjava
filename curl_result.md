
---------- getAll() ---------- 

[m[32m]9;8;"USERNAME"\@]9;8;"COMPUTERNAME"\ [92mD:\git\topjava[90m
[90m$[m ]9;12\C:\Progra~1\curl\bin\curl.exe -i -s GET http://localhost:8080/topjava/rest/meals 
HTTP/1.1 200 
Content-Type: application/json;charset=UTF-8
Transfer-Encoding: chunked
Date: Mon, 26 Nov 2018 20:33:39 GMT

[{"id":100007,"dateTime":"2015-05-31T20:00:00","description":"Ужин","calories":510,"excess":true},{"id":100006,"dateTime":"2015-05-31T13:00:00","description":"Обед","calories":1000,"excess":true},{"id":100005,"dateTime":"2015-05-31T10:00:00","description":"Завтрак","calories":500,"excess":true},{"id":100004,"dateTime":"2015-05-30T20:00:00","description":"Ужин","calories":500,"excess":false},{"id":100003,"dateTime":"2015-05-30T13:00:00","description":"Обед","calories":1000,"excess":false},{"id":100002,"dateTime":"2015-05-30T10:00:00","description":"Завтрак","calories":500,"excess":false}]
 ---------- get() ---------- 

[m[32m]9;8;"USERNAME"\@]9;8;"COMPUTERNAME"\ [92mD:\git\topjava[90m
[90m$[m ]9;12\C:\Progra~1\curl\bin\curl.exe -i -s GET http://localhost:8080/topjava/rest/meals/100002 
HTTP/1.1 200 
Content-Type: application/json;charset=UTF-8
Transfer-Encoding: chunked
Date: Mon, 26 Nov 2018 20:33:42 GMT

{"id":100002,"dateTime":"2015-05-30T10:00:00","description":"Завтрак","calories":500,"user":null}
 ---------- get() Not found ---------- 

[m[32m]9;8;"USERNAME"\@]9;8;"COMPUTERNAME"\ [92mD:\git\topjava[90m
[90m$[m ]9;12\C:\Progra~1\curl\bin\curl.exe -i -s GET http://localhost:8080/topjava/rest/meals/100010 
HTTP/1.1 500 
Content-Type: text/html;charset=utf-8
Content-Language: en
Content-Length: 4787
Date: Mon, 26 Nov 2018 20:33:44 GMT
Connection: close

<!doctype html><html lang="en"><head><title>HTTP Status 500 – Internal Server Error</title><style type="text/css">h1 {font-family:Tahoma,Arial,sans-serif;color:white;background-color:#525D76;font-size:22px;} h2 {font-family:Tahoma,Arial,sans-serif;color:white;background-color:#525D76;font-size:16px;} h3 {font-family:Tahoma,Arial,sans-serif;color:white;background-color:#525D76;font-size:14px;} body {font-family:Tahoma,Arial,sans-serif;color:black;background-color:white;} b {font-family:Tahoma,Arial,sans-serif;color:white;background-color:#525D76;} p {font-family:Tahoma,Arial,sans-serif;background:white;color:black;font-size:12px;} a {color:black;} a.name {color:black;} .line {height:1px;background-color:#525D76;border:none;}</style></head><body><h1>HTTP Status 500 – Internal Server Error</h1><hr class="line" /><p><b>Type</b> Exception Report</p><p><b>Message</b> Request processing failed; nested exception is ru.javawebinar.topjava.util.exception.NotFoundException: Not found entity with id=100010</p><p><b>Description</b> The server encountered an unexpected condition that prevented it from fulfilling the request.</p><p><b>Exception</b></p><pre>org.springframework.web.util.NestedServletException: Request processing failed; nested exception is ru.javawebinar.topjava.util.exception.NotFoundException: Not found entity with id=100010
	org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:1006)
	org.springframework.web.servlet.FrameworkServlet.doGet(FrameworkServlet.java:890)
	javax.servlet.http.HttpServlet.service(HttpServlet.java:634)
	org.springframework.web.servlet.FrameworkServlet.service(FrameworkServlet.java:875)
	javax.servlet.http.HttpServlet.service(HttpServlet.java:741)
	org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:53)
	org.springframework.web.filter.CharacterEncodingFilter.doFilterInternal(CharacterEncodingFilter.java:200)
	org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)
</pre><p><b>Root Cause</b></p><pre>ru.javawebinar.topjava.util.exception.NotFoundException: Not found entity with id=100010
	ru.javawebinar.topjava.util.ValidationUtil.checkNotFound(ValidationUtil.java:27)
	ru.javawebinar.topjava.util.ValidationUtil.checkNotFound(ValidationUtil.java:21)
	ru.javawebinar.topjava.util.ValidationUtil.checkNotFoundWithId(ValidationUtil.java:10)
	ru.javawebinar.topjava.service.MealServiceImpl.get(MealServiceImpl.java:26)
	ru.javawebinar.topjava.web.meal.AbstractMealController.get(AbstractMealController.java:30)
	ru.javawebinar.topjava.web.meal.MealRestController.get(MealRestController.java:32)
	java.base&#47;jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	java.base&#47;jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	java.base&#47;jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	java.base&#47;java.lang.reflect.Method.invoke(Method.java:566)
	org.springframework.web.method.support.InvocableHandlerMethod.doInvoke(InvocableHandlerMethod.java:215)
	org.springframework.web.method.support.InvocableHandlerMethod.invokeForRequest(InvocableHandlerMethod.java:142)
	org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod.invokeAndHandle(ServletInvocableHandlerMethod.java:102)
	org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.invokeHandlerMethod(RequestMappingHandlerAdapter.java:895)
	org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.handleInternal(RequestMappingHandlerAdapter.java:800)
	org.springframework.web.servlet.mvc.method.AbstractHandlerMethodAdapter.handle(AbstractHandlerMethodAdapter.java:87)
	org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:1038)
	org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:942)
	org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:998)
	org.springframework.web.servlet.FrameworkServlet.doGet(FrameworkServlet.java:890)
	javax.servlet.http.HttpServlet.service(HttpServlet.java:634)
	org.springframework.web.servlet.FrameworkServlet.service(FrameworkServlet.java:875)
	javax.servlet.http.HttpServlet.service(HttpServlet.java:741)
	org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:53)
	org.springframework.web.filter.CharacterEncodingFilter.doFilterInternal(CharacterEncodingFilter.java:200)
	org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)
</pre><p><b>Note</b> The full stack trace of the root cause is available in the server logs.</p><hr class="line" /><h3>Apache Tomcat/9.0.13</h3></body></html>
 ---------- delete ---------- 

[m[32m]9;8;"USERNAME"\@]9;8;"COMPUTERNAME"\ [92mD:\git\topjava[90m
[90m$[m ]9;12\C:\Progra~1\curl\bin\curl.exe -i -s -X DELETE http://localhost:8080/topjava/rest/meals/100002 
HTTP/1.1 204 
Date: Mon, 26 Nov 2018 20:33:44 GMT


 ---------- Create new meal ---------- 

[m[32m]9;8;"USERNAME"\@]9;8;"COMPUTERNAME"\ [92mD:\git\topjava[90m
[90m$[m ]9;12\C:\Progra~1\curl\bin\curl.exe -i --header "Content-Type: application/json;charset=UTF-8" --request POST --data "{\"dateTime\":\"2018-11-26T16:50\",\"description\":\"New Meal\",\"calories\":501}" http://localhost:8080/topjava/rest/meals 
HTTP/1.1 201 
Location: http://localhost:8080/topjava/rest/meals/100010
Content-Type: application/json;charset=UTF-8
Transfer-Encoding: chunked
Date: Mon, 26 Nov 2018 20:33:44 GMT

{"id":100010,"dateTime":"2018-11-26T16:50:00","description":"New Meal","calories":501,"user":null}
 ---------- between ---------- 

[m[32m]9;8;"USERNAME"\@]9;8;"COMPUTERNAME"\ [92mD:\git\topjava[90m
[90m$[m ]9;12\C:\Progra~1\curl\bin\curl.exe -i -s "http://localhost:8080/topjava/rest/meals/between?startDate=2015-05-30&endDate=2015-05-30&endTime=13:00:00" 
HTTP/1.1 200 
Content-Type: application/json;charset=UTF-8
Transfer-Encoding: chunked
Date: Mon, 26 Nov 2018 20:33:44 GMT

[{"id":100003,"dateTime":"2015-05-30T13:00:00","description":"Обед","calories":1000,"excess":false}]
 ---------- update ---------- 

[m[32m]9;8;"USERNAME"\@]9;8;"COMPUTERNAME"\ [92mD:\git\topjava[90m
[90m$[m ]9;12\C:\Progra~1\curl\bin\curl.exe -i --header "Content-Type: application/json" --request PUT --data "{\"dateTime\":\"2015-05-30T13:00\",\"description\":\"Update\",\"calories\":1500}" http://localhost:8080/topjava/rest/meals/100003 
HTTP/1.1 204 
Date: Mon, 26 Nov 2018 20:33:44 GMT

