<%--
  Created by IntelliJ IDEA.
  User: wangjiale
  Date: 2021/07/03
  Time: 14:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>交易详情</title>
    </head>
    <body>
        <div align="center">
            <table>
                <tr>
                    <th>书名</th>
                    <th>购买数量</th>
                    <th>消费金额</th>
                </tr>
                <core:forEach items="${tradeItems}" var="tradeItem">
                    <tr>
                        <td>${tradeItem.bookName}</td>
                        <td>${tradeItem.purchaseAmount}</td>
                        <td>${tradeItem.costAmount}</td>
                    </tr>
                </core:forEach>
            </table>
            共计${tradeItems.size()}条
        </div>
        <script src="<%=request.getContextPath()%>/javascript/AlignThAndTd.js">
        </script>
    </body>
</html>
