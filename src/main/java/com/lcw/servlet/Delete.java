package com.lcw.servlet;

import com.lcw.dao.IStudentDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.lcw.util.jdbcUtil;

@WebServlet("/deleteStudent")
public class Delete extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置编码
        req.setCharacterEncoding("utf-8");
        //获取参数
        String stdNumber = req.getParameter("stdNumber");
        String name = req.getParameter("name");

        IStudentDao studentDao = jdbcUtil.getIStudentDao();

        studentDao.deleteById(stdNumber);
        jdbcUtil.commit();
        req.getRequestDispatcher("/search?stdNumber="+""+"&name="+name).forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
