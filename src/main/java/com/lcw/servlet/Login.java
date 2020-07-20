package com.lcw.servlet;

import com.alibaba.fastjson.JSON;
import com.lcw.dao.IUserDao;
import com.lcw.domain.User;
import com.lcw.util.jdbcUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/login")
public class Login extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        //存储返回的json数据
        Map<String, Object> map = new HashMap<>();
        map.put("status","200");
        //获取参数
        String userName = req.getParameter("uuid");
        String passWd = req.getParameter("passwd");

        //获取代理对象
        IUserDao userDao = jdbcUtil.getSqlSession().getMapper(IUserDao.class);

        //调用登陆函数并返回对象
        User login = userDao.login(userName);

        //判断用户是否存在
        if(login == null){
            map.put("userExist",false);
            map.put("passWordCorrect",false);
            map.put("msg","用户名错误");
        }else{
            map.put("userExist",true);
            //判断密码是否正确
            if(passWd.equals(login.getPassWd())){
                map.put("passWordCorrect",true);
                map.put("msg","用户验证成功");
                //req.getRequestDispatcher("/add.html").forward(req,resp);
                //登陆成功
            }
            else{
                map.put("passWordCorrect",false);
                map.put("msg","密码错误");
            }

        }

        resp.setContentType("text/html;charset=utf-8");
        resp.getWriter().write(JSON.toJSONString(map));

    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
