<%--
  Created by IntelliJ IDEA.
  GsonTest.User: wangjiale
  Date: 2021/06/13
  Time: 15:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Title</title>
    </head>
    <body>
        <h1 style="color:red;" align="center">禁止非法访问此页面!!!</br>
            <span id="span01" align="center"></span>秒后跳转到登录页面
        </h1>
    </body>
    <script src="<%=request.getContextPath()%>/jQuery.js"></script>
    <script>
        let time=3;
        $(function(){
            $("#span01")[0].innerHTML=time;
        });
        setInterval(function(){
            if(--time!=0){
                $("#span01")[0].innerHTML=time;
            }
            else{
                location="<%=request.getContextPath()%>/login.jsp";
            }
        },1000);
    </script>
</html>
