<%@ page import="Entity.Book, java.io.FileInputStream, jakarta.servlet.ServletContext, java.util.Scanner, java.io.File" %><%--
  Created by IntelliJ IDEA.
  User: wangjiale
  Date: 2021/06/30
  Time: 15:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>查看图书详细信息</title>
        <style>
            a:not(a[href*="downloadMarkupFile"]){
                float:right;
            }
        </style>
    </head>
    <body>
        <a name="top"></a>
        <a href="<%=request.getContextPath()%>/MyServlet.action?operation=downloadMarkupFile&virtualFilePath=${book.markupLink}">点击下载此文章</a>
        <a title="回到购物页面"
           href="<%=request.getContextPath()%>/MyServlet.action?operation=getPage&minPrice=${param.minPrice}&maxPrice=${param.maxPrice}&currentPageNo=${param.currentPageNo}&pageSize=${param.pageSize}">继续购物</a>
        <h1 align="center">${book.name}</h1>
        <h4 align="center">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${book.author}</h4>

        <br/>
        <div>
            <%
                Book book=(Book)request.getAttribute("book");
                String realPath=request.getServletContext().getRealPath(book.getMarkupLink());
                Scanner scanner=new Scanner(new File(realPath),"utf-8");
                StringBuilder stringBuilder=new StringBuilder();
                while(scanner.hasNextLine()){
                    stringBuilder.append(scanner.nextLine()+"<br/>");
                }
                String markup=stringBuilder.toString();
            %>
            <%=markup%>
            <br/>
            <a href="#top">回到顶部</a>
        </div>
    </body>
</html>
