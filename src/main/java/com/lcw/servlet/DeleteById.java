package com.lcw.servlet;

import com.lcw.dao.IStudentDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.lcw.util.jdbcUtil;

@WebServlet("/delete")
public class DeleteById extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置编码
        req.setCharacterEncoding("utf-8");
        //获取参数
        String sid = req.getParameter("stdNumber");

        IStudentDao studentDao = jdbcUtil.getIStudentDao();

        studentDao.deleteById(sid);
        jdbcUtil.commit();
        req.getRequestDispatcher("/delete.html").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
