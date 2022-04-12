package Servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.lang.reflect.Method;

public class BaseServlet extends HttpServlet{
    Class clazz=this.getClass();
    Method method;
    @Override
    protected void service(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
            try{
                method=clazz.getMethod(request.getParameter("operation"),HttpServletRequest.class,HttpServletResponse.class);
                method.invoke(this,request,response);
            }
            catch(Exception e){
                e.printStackTrace();
            }
    }
}

