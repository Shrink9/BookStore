<%@ page import="java.text.SimpleDateFormat, java.util.Date, Entity.Book, TemporaryEntity.Page" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>图书商城</title>
        <link href="<%=request.getContextPath()%>/css/BtnAndSubmit.css" rel="stylesheet"/>
        <style type="text/css">
            .textField{
                width:60px;
            }
            tr{
                height:194.66px;
            }
        </style>
        <script src="<%=request.getContextPath()%>/jQuery.js"></script>
        <script>
            function queryAllBooks(){
                location="<%=request.getContextPath()%>/MyServlet.action?operation=getPage&minPrice=0&maxPrice=99999&currentPageNo=1&pageSize=10";
            }
            function regulateAmount(seq){
                //seq!=-1则代表为purchaseAmount失焦,则修正一下.
                if(seq!= -1){
                    let purchaseAmount=document.getElementById("purchaseAmount"+seq).value;
                    //如果purchaseAmount不是>=0的数字,则将其修正为0.
                    if(purchaseAmount==''|| !(purchaseAmount>=0)){
                        document.getElementById("purchaseAmount"+seq).value=0;
                    }
                }
                //seq为-1则代表为maxPrice或者minPrice失焦,则两个都修正一下.
                else{
                    let maxPrice=document.getElementById("maxPrice").value;
                    //如果maxPrice不是>=0的数字,则将其修正为99999.
                    if(maxPrice==''|| !(maxPrice>=0)){
                        document.getElementById("maxPrice").value=99999;
                    }
                    let minPrice=document.getElementById("minPrice").value;
                    //如果minPrice不是>=0的数字,则将其修正为0.
                    if(minPrice==''|| !(minPrice>=0)){
                        document.getElementById("minPrice").value=0;
                    }
                }
            }
            function addBookToShoppingCart(seq,bookName){
                let purchaseAmount=document.getElementById('purchaseAmount'+seq).value;
                if(purchaseAmount==0){
                    return;
                }
                let url="<%=request.getContextPath()%>/MyServlet.action?operation=addBookToShoppingCart&purchaseAmount="+purchaseAmount+"&bookName="+bookName;
                $.get(url,function(res){
                    document.getElementById('purchaseAmount'+seq).value=0;
                    document.getElementById('havePurchasedAmount'+seq).innerText='总共添加了'+res.amount+'本';
                },"json");
            }
        </script>
    </head>
    <body>
        <a name="top"></a>
        <span style="float:left;">
            <a href="<%=request.getContextPath()%>/MyServlet.action?operation=showUserInf&minPrice=${page.minPrice}&maxPrice=${page.maxPrice}&currentPageNo=${page.currentPageNo}&pageSize=${page.pageSize}">个人中心</a>&nbsp;&nbsp;&nbsp;<a href="<%=request.getContextPath()%>/MyServlet.action?operation=showShoppingCart&minPrice=${page.minPrice}&maxPrice=${page.maxPrice}&currentPageNo=${page.currentPageNo}&pageSize=${page.pageSize}">我的购物车</a>
        </span>
        <div align="right">
            尊敬的${user.username},您好!<br/>
            您的余额为${user.balance}元
            <core:if test="${user.balance<10}">
                建议您尽快充值!
            </core:if>
        </div>
        <div align="center">
            <form action="<%=request.getContextPath()%>/MyServlet.action?operation=getPage&currentPageNo=1&pageSize=10"
                  method="post">
                价格:<input name="minPrice" id="minPrice" class="textField" value="${page.minPrice}"
                          onblur="regulateAmount(-1);"/>~<input
                    name="maxPrice" id="maxPrice"
                    class="textField"
                    value="${page.maxPrice}" onblur="regulateAmount(-1);"/>
                <input type="submit" value="查询">
                <input type="button" value="查询全部图书" onclick="queryAllBooks()">
            </form>
        </div>
        <div align="center">
            <hr/>
            <core:if test="${!empty page.contentList}">
                <table border="1px" style="width:1200px">
                    <tr style="height:97.33px">
                        <th>书名</th>
                        <th>作者</th>
                        <th>出版日期</th>
                        <th>价格</th>
                        <th>封面预览</th>
                    </tr>
                    <core:set var="simpleDateFormat" value='<%=new SimpleDateFormat("yyyy年MM月dd日")%>'></core:set>
                    <core:forEach items="${page.contentList}" var="book" varStatus="status">
                        <tr>
                            <td align="center">
                                <a title="点击查看&quot;${book.name}&quot;的更多信息"
                                   href="<%=request.getContextPath()%>/MyServlet.action?operation=queryDetailOfBook&bookName=${book.name}&minPrice=${page.minPrice}&maxPrice=${page.maxPrice}&currentPageNo=${page.currentPageNo}&pageSize=${page.pageSize}">${book.name}</a>
                            </td>
                            <td align="center">${book.author}</td>
                            <td align="center">${simpleDateFormat.format(book.getPublishTime())}
                            </td>
                            <td align="center">${book.price}</td>
                            <td style="width:30%">
                                <core:set var="coverLink" value="${book.coverLink}" scope="request"></core:set>
                                <img src='<%=request.getContextPath()+request.getAttribute("coverLink")%>'
                                     style="width:100%;height:100%;"/>
                            </td>
                            <td>添加进购物车<input id="purchaseAmount${status.count}" class="textField" type="number" min="0"
                                             onblur="regulateAmount(${status.count});" value="0">本
                                <input type="button" value="添加"
                                       onclick="addBookToShoppingCart(${status.count},'${book.name}');"><br/>
                                <br/>
                                <span id="havePurchasedAmount${status.count}"></span>
                            </td>
                        </tr>
                    </core:forEach>
                </table>
                <a href="<%=request.getContextPath()%>/MyServlet.action?operation=getPage&minPrice=${page.minPrice}&maxPrice=${page.maxPrice}&currentPageNo=1&pageSize=${page.pageSize}">首页</a>
                <a  <core:if test="${page.hasPrePage()}">
                    href="<%=request.getContextPath()%>/MyServlet.action?operation=getPage&minPrice=${page.minPrice}&maxPrice=${page.maxPrice}&currentPageNo=${page.prePageNo}&pageSize=${page.pageSize}"
                </core:if>
                >上一页</a>
                ${page.currentPageNo}/${page.totalPageAmount}
                <a  <core:if test="${page.hasNextPage()}">
                    href="<%=request.getContextPath()%>/MyServlet.action?operation=getPage&minPrice=${page.minPrice}&maxPrice=${page.maxPrice}&currentPageNo=${page.nextPageNo}&pageSize=${page.pageSize}"
                </core:if>
                >下一页</a>
                <a href="<%=request.getContextPath()%>/MyServlet.action?operation=getPage&minPrice=${page.minPrice}&maxPrice=${page.maxPrice}&currentPageNo=${page.totalPageAmount}&pageSize=${page.pageSize}">尾页</a>&nbsp;
                转到第<input id="targetPageNo" value="${page.currentPageNo}" class="textField"/>页
                <input type="button" value="Go" onclick="checkGoInput()"/><br/>
                <a href="#top" align="center">回到顶部</a>
                <script>
                    function checkGoInput(){
                        let element=document.getElementById("targetPageNo");
                        let pageNo=element.value;
                        if(pageNo>=1&&pageNo<=${page.totalPageAmount}){
                            location="<%=request.getContextPath()%>/MyServlet.action?operation=getPage&minPrice=${page.minPrice}&maxPrice=${page.maxPrice}&currentPageNo="+pageNo+"&pageSize=${page.pageSize}";
                        }
                        else{
                            alert("页数必须位于1~${page.totalPageAmount}内!");
                        }
                    }
                </script>
            </core:if>
            <core:if test="${empty page.contentList}">
                <table border="1px">
                    <tr style="height:30px">
                        <th>暂未查询到图书!</th>
                    </tr>
                </table>
            </core:if>
        </div>
    </body>
</html>
