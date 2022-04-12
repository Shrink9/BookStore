<%@ page import="jakarta.servlet.http.Cookie, java.util.Arrays, java.util.Date" %><%--
  Created by IntelliJ IDEA.
  GsonTest.User: wangjiale
  Date: 2021/06/05
  Time: 13:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <title>登录</title>
        <link href="<%=request.getContextPath()%>/css/BtnAndSubmit.css" rel="stylesheet"/>
        <core:if test="${!empty param.lang}">
            <fmt:setLocale value="${param.lang}"/>
        </core:if>
        <fmt:setBundle basename="LoginI18N"/>
    </head>
    <body>
        <%
            String username="", password="", isChecked="";
            Cookie[] cookies=request.getCookies();
            if(cookies!=null){
                for(int i=0;i<cookies.length;i
                        ++){
                    if(cookies[i].getName().equals("username")){
                        username=cookies[i].getValue();
                        isChecked="checked";
                    }
                    else{
                        if(cookies[i].getName().equals("password")){
                            password=cookies[i].getValue();
                        }
                    }
                }
            }
        %>
        <div style="float:right">
            <select onchange="toggleLang(this)">
                <option
                        <core:if
                                test='${(param.lang==null and headerValues["Accept-Language"][0].indexOf("zh-CN,zh")==0) or param.lang.equals("zh_CN")}'>
                            selected
                        </core:if>
                >中文
                </option>
                <option
                        <core:if
                                test='${(param.lang==null and headerValues["Accept-Language"][0].indexOf("en-US,en")==0) or param.lang.equals("en_US")}'>
                            selected
                        </core:if>
                >English
                </option>
            </select>
        </div>
        <div align="center">
        <form  onsubmit="return checkCorrection();"
              action="<%=request.getContextPath()%>/MyServlet.action?operation=getPage&minPrice=0&maxPrice=99999&currentPageNo=1&pageSize=10" method="post">
            <fmt:message key="username"/>:<input type="text" name="username" value="<%=username%>"
                                                 onfocus="clearText()"/><br/>
            <fmt:message key="password"/>:<input type="password" name="password" value="<%=password%>"
                                                 onfocus="clearText()"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br/>
            <input type="checkbox" name="rememberMe" value="yes" <%=isChecked%>><fmt:message
                    key='rememberMeForTenDay'/></input>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br/>
            <input type="submit" style="alignment:left" value="<fmt:message key='login'/>">
            <input type="button" value="<fmt:message key='register'/>"
                   onclick="window.location='<%=request.getContextPath()%>/register.jsp'">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        </form>
        <div id="errorMsgDiv"></div>
        </div>
        <br/><br/><br/>
    </body>
    <script src="<%=request.getContextPath()%>/jQuery.js"></script>
    <script>
        function toggleLang(ele){
            let lang=null;
            if(ele.value=="中文"){
                lang="zh_CN"
            }
            else{
                lang="en_US";
            }
            location="${pageContext.request.contextPath}/login.jsp?lang="+lang;
        }
        function checkCorrection(){
            let params=$("form").serialize();
            let url="<%=request.getContextPath()%>/MyServlet.action?operation=login&"+params;
            let res=null;
            $.ajax({
                type:"get",
                url:url,
                success:function(tempRes){
                    res=tempRes;
                },
                dataType:"json",
                async:false
            });
            if(res==false){
                document.getElementById("errorMsgDiv").innerHTML="用户名或密码错误&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
            }
            return res;
        }
        function clearText(){
            document.getElementById("errorMsgDiv").innerText="";
        }
    </script>
</html>
