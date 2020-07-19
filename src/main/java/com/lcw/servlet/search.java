package com.lcw.servlet;

import com.alibaba.fastjson.JSON;
import com.lcw.dao.IStudentDao;
import com.lcw.domain.Student;
import com.lcw.util.jdbcUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

@WebServlet("/search")
public class search extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        //获取参数
        String userName = req.getParameter("name");
        String stdNumber = req.getParameter("stdNumber");

        IStudentDao studentDao = jdbcUtil.getIStudentDao();

        List<Student> students = null;
        if(!userName.isEmpty() && !stdNumber.isEmpty()){
            students = studentDao.findByIdAndName(stdNumber, userName);
        }
        else if(!userName.isEmpty()){
            students = studentDao.findByName(userName);
        }
        else if (!stdNumber.isEmpty()){
            students = studentDao.findById(stdNumber);

        }
        else {
            students = studentDao.findAll();
        }
        req.setAttribute("list",students);
        req.getRequestDispatcher("/search.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
