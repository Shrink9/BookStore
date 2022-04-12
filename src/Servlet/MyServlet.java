package Servlet;

import Entity.Book;
import Entity.Trade;
import Entity.TradeItem;
import Entity.User;
import ServiceConcept.Service.*;
import TemporaryEntity.BookQueryPredicate;
import TemporaryEntity.Page;
import TemporaryEntity.ShoppingCart;
import TemporaryEntity.ShoppingCartItem;
import com.google.gson.Gson;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/MyServlet.action")
public class MyServlet extends BaseServlet{
    private BookService bookService=new BookService();
    private UserService userService=new UserService();
    private TradeService tradeService=new TradeService();
    private TradeItemService tradeItemService=new TradeItemService();
    private ComplexService complexService=new ComplexService();
    public void getPage(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
        String minPrice=request.getParameter("minPrice");
        String maxPrice=request.getParameter("maxPrice");
        String currentPageNo=request.getParameter("currentPageNo");
        String pageSize=request.getParameter("pageSize");
        //使用上述四个参数生成BookQueryPredicate对象
        BookQueryPredicate predicate=new BookQueryPredicate(minPrice,maxPrice,currentPageNo,pageSize);
        //使用predicate作为参数去查找指定的页面
        Page<Book> page=bookService.queryPageWithPredicate(predicate);
        //将page设为req的属性以便在BookShow.jsp中使用
        request.setAttribute("page",page);
        //转发到BookShow.jsp
        request.getRequestDispatcher("/BookShow.jsp").forward(request,response);
    }
    public void queryDetailOfBook(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
        String bookName=request.getParameter("bookName");
        //使用临时book去查找指定名字的book
        Book temporaryBook=new Book();
        temporaryBook.setName(bookName);
        //查找指定名字的book
        Book book=bookService.queryBookByName(temporaryBook);
        //将book和predicate设为req的属性以便在DetailOfBook.jsp中使用
        request.setAttribute("book",book);
        //转发到DetailOfBook.jsp
        request.getRequestDispatcher("/DetailOfBook.jsp").forward(request,response);
    }
    public void login(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
        ServletContext servletContext=getServletContext();
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        User temporaryUser=new User(username,password);
        //查找是否有此用户
        User user=userService.login(temporaryUser);
        if(user!=null){
            //设置Session
            request.getSession().setAttribute("user",user);
            request.getSession().setAttribute("level","user");
            ShoppingCart<Book> shoppingCart=new ShoppingCart<>();
            request.getSession().setAttribute("shoppingCart",shoppingCart);
            Cookie usernameCookie=new Cookie("username",username);
            Cookie passwordCookie=new Cookie("password",password);
            usernameCookie.setPath(request.getContextPath());
            passwordCookie.setPath(request.getContextPath());
            //如果选择记住我则Cookie时间设为10天
            if(request.getParameter("rememberMe")!=null){
                usernameCookie.setMaxAge(10*24*60*60);
                passwordCookie.setMaxAge(10*24*60*60);
            }
            //否则Cookie时间置0(即立刻失效)
            else{
                usernameCookie.setMaxAge(0);
                passwordCookie.setMaxAge(0);
            }
            //添加新Cookie
            response.addCookie(usernameCookie);
            response.addCookie(passwordCookie);
            //登录成功则返回true
            response.getWriter().println(new Gson().toJson(true));
        }
        else{
            //本次登录失败则令以前的Session也失效
            request.getSession().invalidate();
            //登录失败则返回false
            response.getWriter().println(new Gson().toJson(false));
        }
    }
    public void checkUsernameIsExists(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
        String username=request.getParameter("username");
        User user=new User();
        user.setUsername(username);
        //如果用户名已经存在则返回true
        if(userService.checkDuplicateUsername(user)){
            response.getWriter().print(new Gson().toJson(true));
        }
        //否则返回false
        else{
            response.getWriter().print(new Gson().toJson(false));
        }
    }
    public void register(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        User user=new User(username,password);
        userService.register(user);
        response.sendRedirect(request.getContextPath()+"/login.jsp");
    }
    public void logout(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
        //使Session失效
        request.getSession().invalidate();
        response.sendRedirect(request.getContextPath()+"/login.jsp");
    }
    public void changePassword(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
        String username=request.getParameter("username");
        String newPassword=request.getParameter("newPassword");
        User user=new User(username,newPassword);
        userService.changePassword(user);
        //退出用户账号
        logout(request,response);
    }
    public void queryAllUsers(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
        ArrayList<User> users=userService.queryAllUsers();
        request.setAttribute("users",users);
        request.getRequestDispatcher("/QueryUsers.jsp").forward(request,response);
    }
    public void addBookToShoppingCart(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
        String purchaseAmount=request.getParameter("purchaseAmount");
        String bookName=request.getParameter("bookName");
        Book temporaryBook=new Book();
        temporaryBook.setName(bookName);
        Book book=bookService.queryBookByName(temporaryBook);
        ShoppingCartItem<Book> item=new ShoppingCartItem<Book>(book,purchaseAmount);
        ShoppingCart<Book> shoppingCart=(ShoppingCart<Book>)request.getSession().getAttribute("shoppingCart");
        shoppingCart.addShoppingCartItem(bookName,item);
        ShoppingCartItem<Book> newItem=shoppingCart.getShoppingCartItem(bookName);
        response.getWriter().println(new Gson().toJson(newItem));
    }
    public void removeFromShoppingCart(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
        String bookName=request.getParameter("itemName");
        ShoppingCart<Book> shoppingCart=(ShoppingCart<Book>)request.getSession().getAttribute("shoppingCart");
        shoppingCart.removeShoppingCartItem(bookName);
        response.getWriter().println(shoppingCart.getShoppingCartTotalMoney());
    }
    public void downloadMarkupFile(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
        String virtualFilePath=request.getParameter("virtualFilePath");
        String realPath=request.getServletContext().getRealPath(virtualFilePath);
        int beginIndex=realPath.lastIndexOf('\\')+1;
        String fileName=realPath.substring(beginIndex);
        fileName=new String(fileName.getBytes("GBK"),"ISO8859-1");
        response.setHeader("Content-Disposition","attachment;filename="+fileName);
        FileInputStream inputStream=new FileInputStream(realPath);
        ServletOutputStream outputStream=response.getOutputStream();
        IOUtils.copy(inputStream,outputStream);
    }
    public void showShoppingCart(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
        request.getRequestDispatcher("/ShowShoppingCart.jsp").forward(request,response);
    }
    public void clearShoppingCart(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
        ShoppingCart<Book> shoppingCart=(ShoppingCart<Book>)request.getSession().getAttribute("shoppingCart");
        shoppingCart.clearShoppingCart();
        request.getRequestDispatcher("/ShowShoppingCart.jsp").forward(request,response);
    }
    public void checkout(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
        ShoppingCart<Book> shoppingCart=(ShoppingCart<Book>)request.getSession().getAttribute("shoppingCart");
        User user=(User)request.getSession().getAttribute("user");
        ArrayList<String> tips=complexService.checkout(shoppingCart,user);
        response.getWriter().println(new Gson().toJson(tips));
    }
    public void showUserInf(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
        Trade trade=new Trade();
        trade.setUserId(((User)request.getSession().getAttribute("user")).getId());
        ArrayList<Trade> trades=tradeService.queryTradesByUserId(trade);
        request.setAttribute("trades",trades);
        request.getRequestDispatcher("/UserInf.jsp").forward(request,response);
    }
    public void recharge(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
        String amount=request.getParameter("amount");
        User user=(User)request.getSession().getAttribute("user");
        userService.recharge(user,amount);
        response.getWriter().println(new Gson().toJson(user.getBalance()));
    }
    public void showTradeItem(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
        String tradeIdStr=request.getParameter("tradeId");
        TradeItem tradeItem=new TradeItem();
        int tradeId=Integer.parseInt(tradeIdStr);
        tradeItem.setTradeId(tradeId);
        ArrayList<TradeItem> tradeItems=tradeItemService.queryTradeItemsByTradeId(tradeItem);
        request.setAttribute("tradeItems",tradeItems);
        request.getRequestDispatcher("/ShowTradeItem.jsp").forward(request,response);
    }
}

