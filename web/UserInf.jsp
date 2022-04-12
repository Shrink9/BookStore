<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>个人中心</title>
        <link href="<%=request.getContextPath()%>/css/BtnAndSubmit.css" rel="stylesheet"/>
        <script src="<%=request.getContextPath()%>/jQuery.js"></script>
        <script>
            function preChangePassword(ele){
                //让changePasswordDiv显示
                let changePasswordDiv=document.getElementById("changePasswordDiv");
                $(changePasswordDiv).show();
                //让此按钮隐藏
                $(ele).hide();
            }
            function confirmChangePassword(){
                let oldPswd=document.getElementById("oldPswd").value;
                let newPswd=document.getElementById("newPswd").value;
                if(oldPswd!="${user.password}"){
                    alert("密码错误");
                }
                else{
                    if(newPswd==""){
                        alert("新密码不能为空");
                    }
                    else{
                        alert("密码修改成功");
                        location="<%=request.getContextPath()%>/MyServlet.action?operation=changePassword&username=${user.username}&newPassword="+newPswd;
                    }
                }
            }
            function regulateAmount(amountInput){
                let amount=amountInput.value;
                //如果amount不是>=0的数字,则将其修正为0.
                if(amount==''|| !(amount>=0)){
                    amountInput.value=0;
                }
            }
            function preRecharge(ele){
                //让rechargePasswordDiv显示
                let rechargePasswordDiv=document.getElementById("rechargePasswordDiv");
                $(rechargePasswordDiv).show();
                //让此按钮隐藏
                $(ele).hide();
            }
            function confirmRecharge(){
                let amount=document.getElementById("amountInput").value;
                if(amount!=0){
                    let url="<%=request.getContextPath()%>/MyServlet.action?operation=recharge&amount="+amount;
                    $.get(url,function(newBalance){
                        document.getElementById("balance").innerText=newBalance;
                        //充值完成后让rechargePasswordDiv隐藏
                        $("#rechargePasswordDiv").hide();
                        //让preRechargeBtn显示
                        $("#preRechargeBtn").show();
                    },"json");
                }
            }
        </script>
    </head>
    <body>
        <div align="center">
            用户名:<input value="${user.username}" readonly="readonly"/><a href="<%=request.getContextPath()%>/MyServlet.action?operation=logout" style="float: right;">注销</a><br/>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;密&nbsp;&nbsp;&nbsp;码:<input type="password" value="${user.password}" readonly="readonly"/>
            <input style="float:right"
                    type="button" value="修改密码" onclick="preChangePassword(this);"/>
            <br/>
            余额:&nbsp;<span id="balance">${user.balance}</span>
            <input id="preRechargeBtn" type="button" value="充值" onclick="preRecharge(this);"/>
            <br/>
            <br/>
            <div id="changePasswordDiv" hidden="hidden">
                原密码:<input id="oldPswd" type="password"/><br/>
                新密码:<input id="newPswd" type="password"/><br/>
                <input type="button" value="确定" onclick="confirmChangePassword();"/><br/><br/>
            </div>
            <div id="rechargePasswordDiv" hidden="hidden">
                充值额:<input id="amountInput" type="number" onblur="regulateAmount(this);" min="0"/><br/>
                <input type="button" value="充值" onclick="confirmRecharge();"/><br/><br/>
            </div>
            <div>
                您一共进行了${trades.size()}次交易
                <table>
                    <tr>
                        <th>时&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;间</th>
                        <th>消费金额</th>
                    </tr>
                    <core:forEach items="${trades}" var="trade">
                        <tr>
                            <td>${trade.time}</td>
                            <td>${trade.costAmount}</td>
                            <td>
                                <a href="<%=request.getContextPath()%>/MyServlet.action?operation=showTradeItem&tradeId=${trade.id}"
                                   target="_blank">查看交易详情</a>
                            </td>
                        </tr>
                    </core:forEach>
                </table>
            </div>
            <a href="<%=request.getContextPath()%>/MyServlet.action?operation=getPage&minPrice=${param.minPrice}&maxPrice=${param.maxPrice}&currentPageNo=${param.currentPageNo}&pageSize=${param.pageSize}">继续购物</a>
        </div>
        <script src="<%=request.getContextPath()%>/javascript/AlignThAndTd.js">
        </script>
    </body>
</html>
