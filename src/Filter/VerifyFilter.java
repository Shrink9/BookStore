package Filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter({"*.jsp","/MyServlet.action"})
public class VerifyFilter implements Filter{
    @Override
    public void doFilter(ServletRequest servletRequest,ServletResponse servletResponse,FilterChain filterChain) throws IOException, ServletException{
        HttpServletRequest request=(HttpServletRequest)servletRequest;
        HttpServletResponse response=(HttpServletResponse)servletResponse;
        String path=request.getRequestURL().toString();
        //访问的页面为servlet
        if(path.endsWith("MyServlet.action")){
            //如果没有operation参数则认为是手动访问的
            if(request.getParameter("operation")==null){
                request.getRequestDispatcher("/warning.jsp").forward(request,response);
            }
            else{
                filterChain.doFilter(servletRequest,servletResponse);
            }
        }
        //访问的页面为jsp
        else{
            //访问的是注册或登录或警告页面则不需要检查
            if(path.endsWith("register.jsp")||path.endsWith("login.jsp")||path.endsWith("warning.jsp")){
                filterChain.doFilter(servletRequest,servletResponse);
            }
            //其他页面则需要检查
            else{
                //如果从未登录过则转发到警告页面
                if(request.getSession().getAttribute("user")==null){
                    request.getRequestDispatcher("/warning.jsp").forward(request,response);
                }
                //否则正常进行
                else{
                    filterChain.doFilter(servletRequest,servletResponse);
                }
            }
        }
    }
}
