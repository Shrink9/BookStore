<%--
  Created by IntelliJ IDEA.
  User: wangjiale
  Date: 2021/06/30
  Time: 22:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>查看购物车</title>
        <link href="<%=request.getContextPath()%>/css/BtnAndSubmit.css" rel="stylesheet"/>
        <script src="<%=request.getContextPath()%>/jQuery.js"></script>
        <script>
            function removeFromShoppingCart(itemName){
                let isDelete=confirm("您确定要删除《"+itemName+"》吗?");
                if(isDelete){
                    let tableElement=document.getElementsByTagName("table")[0];
                    if(tableElement.firstElementChild.childElementCount==2){
                        clearShoppingCart(false);
                    }
                    url="<%=request.getContextPath()%>/MyServlet.action?operation=removeFromShoppingCart&itemName="+itemName;
                    $.get(url,function(shoppingCartTotalMoney){
                        document.getElementById("shoppingCartTotalMoney").innerText=shoppingCartTotalMoney;
                    },"json");
                    let trEle=$("td:contains('"+itemName+"')").parent();
                    trEle.remove();
                }
            }
            /**
             * 清空购物车
             * @param isDirect 是否是直接清空购物车
             */
            function clearShoppingCart(isDirect){
                let isDelete=null;
                //如果isDirect是true,则是用户手动选择清空购物车,则询问一下,否则则是被removeFromShoppingCart()调用了,则不需要再询问用户.
                if(isDirect){
                    isDelete=confirm("您确定要清空购物车吗?");
                }
                if(!(isDirect&& !isDelete)){
                    location="<%=request.getContextPath()%>/MyServlet.action?operation=clearShoppingCart&minPrice=${param.minPrice}&maxPrice=${param.maxPrice}&currentPageNo=${param.currentPageNo}&pageSize=${param.pageSize}";
                }
            }
            function checkout(){
                url="<%=request.getContextPath()%>/MyServlet.action?operation=checkout";
                let tip=null;
                $.get(url,function(tips){
                    if(tips[0]=="余额不足,请先充值!"){
                        tip=tips[0]
                    }
                    else{
                        if(tips.length==1){
                            tip="结算成功!";
                        }
                        else{
                            tip="";
                            for(let i=0; i<tips.length-1; i++){
                                tip+="《"+tips[i]+"》库存不足,"
                            }
                            tip+="真实花费为"+tips[tips.length-1]+"元";
                        }
                    }
                    alert(tip);
                    location="<%=request.getContextPath()%>/MyServlet.action?operation=showShoppingCart&minPrice=${param.minPrice}&maxPrice=${param.maxPrice}&currentPageNo=${param.currentPageNo}&pageSize=${param.pageSize}";
                },"json");
            }
        </script>
    </head>
    <body>
        <core:if test="${empty shoppingCart.itemHashMap}">
            <div align="center">
                <br/>
                <br/>
                <br/>
                <br/>
                <br/>
                <br/>
                <br/>
                <h1 style="color:turquoise">
                    您的购物车空空如也,快去购物吧~
                </h1>
                <br/>
                <br/>
                <br/>
                <br/>
            </div>
        </core:if>
        <core:if test="${! empty shoppingCart.itemHashMap}">
            <div align="center" style="font-size:30px">
                <table style="font-size:40px">
                    <tr>
                        <th>书&nbsp;&nbsp;&nbsp;名</th>
                        <th>单价</th>
                        <th>数量</th>
                        <th>总计</th>
                    </tr>
                    <core:forEach items="${shoppingCart.getItems()}" var="item" varStatus="status">
                        <tr>
                            <td align="center">${item.getContent().getName()}</td>
                            <td align="center">&nbsp;&nbsp;${item.getContent().getPrice()}</td>
                            <td align="center">&nbsp;&nbsp;${item.getAmount()}</td>
                            <td align="center">&nbsp;&nbsp;${item.getTotalMoney()}</td>
                            <td align="center">
                                &nbsp;<input type="button" value="从购物车中移除"
                                             onclick="removeFromShoppingCart('${item.getContent().getName()}')"/>
                            </td>
                        </tr>
                    </core:forEach>
                </table>
                <br/>
                您的购物车总计<span id="shoppingCartTotalMoney">${shoppingCart.getShoppingCartTotalMoney()}</span>元.
                    <%--用ajax做--%>
                <a href="#" onclick="checkout();">结账</a><br/>
                <input type="button" align="center" value="清空购物车" onclick="clearShoppingCart(true)"/><br/>
            </div>
        </core:if>
        <a style="float:right"
           href="<%=request.getContextPath()%>/MyServlet.action?operation=getPage&minPrice=${param.minPrice}&maxPrice=${param.maxPrice}&currentPageNo=${param.currentPageNo}&pageSize=${param.pageSize}">继续购物</a>
    </body>
</html>
