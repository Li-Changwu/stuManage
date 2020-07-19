package com.lcw.servlet;

import com.alibaba.fastjson.JSON;
import com.lcw.dao.IUserDao;
import com.lcw.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/login")
public class Login extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        //获取参数
        String userName = req.getParameter("uuid");
        String passWd = req.getParameter("passwd");
        User loginUser = new User(userName, passWd);

        //获取代理对象
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        SqlSession session = factory.openSession();
        IUserDao userDao = session.getMapper(IUserDao.class);

        //调用登陆函数并返回对象
        User login = userDao.login(loginUser);

        //关闭资源
        session.close();
        in.close();

        Map<String, Object> map = new HashMap<>();

        if(login ==null){
            map.put("userExist",false);
            map.put("msg","用户名或者密码错误！");
            resp.setContentType("text/html;charset=utf-8");
            resp.getWriter().write(JSON.toJSONString(map));
        }else{
            req.getRequestDispatcher("/add.html").forward(req,resp);
            //resp.sendRedirect("/add.html");
            //登陆成功
        }

    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
