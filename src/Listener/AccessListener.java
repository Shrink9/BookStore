package Listener;

import jakarta.servlet.ServletRequestEvent;
import jakarta.servlet.ServletRequestListener;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpServletRequest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Date;
import java.util.Enumeration;
import java.util.Iterator;

@WebListener()
public class AccessListener implements ServletRequestListener{
    @Override
    public void requestInitialized(ServletRequestEvent sre){
        HttpServletRequest request=(HttpServletRequest)sre.getServletRequest();
        String remoteAddr=request.getRemoteAddr();
        String requestURL=request.getRequestURL().toString();
        String queryString=request.getQueryString();
        File file=new File(sre.getServletRequest().getRealPath("/WEB-INF/log/AccessLog.txt"));
        PrintStream printStream=null;
        try{
            file.createNewFile();
            printStream=new PrintStream(new FileOutputStream(file,true));
        }
        catch(Exception e){
            e.printStackTrace();
        }
        String res=new Date().toLocaleString()+"   "+remoteAddr+"访问了 "+requestURL;
        Enumeration<String> names=request.getParameterNames();
        Iterator<String> iterator=names.asIterator();
        if(iterator.hasNext()){
            String paraName=iterator.next();
            res=res+"?"+paraName+"="+request.getParameter(paraName);
            while(iterator.hasNext()){
                paraName=iterator.next();
                res=res+"&"+paraName+"="+request.getParameter(paraName);
            }
        }
        printStream.println(res);
    }
}
