<%--
  Created by IntelliJ IDEA.
  GsonTest.User: wangjiale
  Date: 2021/06/09
  Time: 16:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="java.text.SimpleDateFormat, java.util.Date, java.util.Calendar" %>
<html>
    <head>
        <title>注册</title>
        <link href="<%=request.getContextPath()%>/css/BtnAndSubmit.css" rel="stylesheet"/>
    </head>
    <script src="<%=request.getContextPath()%>/jQuery.js"></script>
    <script>
        function checkUsernameIsExists(){
            // get/post方法基本一样,只是对于post来说,data中的数据不会追加在url后.
            let username=$("#username").val();
            let url="<%=request.getContextPath()%>/MyServlet.action?operation=checkUsernameIsExists&username="+username;
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
            if(res==true){
                document.getElementById("duplicateErrorMsgDiv").innerText='用户名已存在';
            }
            return res;
        }
        function clearText(ele){
            if(ele==document.getElementById("username")){
                document.getElementById('duplicateErrorMsgDiv').innerText='';
            }
            else{
                document.getElementById('notIdenticalErrorMsgDiv').innerText='';
            }
        }
        function checkPswdIsIdentical(){
            let pswd=document.getElementById("pswd").value;
            let confirmPswd=document.getElementById("confirmPswd").value;
            if(pswd!=confirmPswd){
                document.getElementById("notIdenticalErrorMsgDiv").innerText="两次输入密码不一致!";
                return false;
            }
            else{
                return true;
            }
        }
    </script>
    <body>
        <div align="center">
            <form action="<%=request.getContextPath()%>/MyServlet.action?operation=register" method="post"
                  onsubmit="return checkPswdIsIdentical()&&!checkUsernameIsExists();">
                用户名:<input id="username" type="text" name="username" onfocus="clearText(this)"
                           onblur="checkUsernameIsExists();"/>
                <span id="duplicateErrorMsgDiv"></span><br/>
                密码:<input id="pswd" type="password" name="password" onfocus="clearText(this)"/><br/>
                确认密码:<input id="confirmPswd" type="password" name="confirmPassword" onblur="checkPswdIsIdentical();"
                            onfocus="clearText(this);"/>
                <span id="notIdenticalErrorMsgDiv"></span><br/>
                <input type="submit"/>
            </form>
        </div>
    </body>


</html>
