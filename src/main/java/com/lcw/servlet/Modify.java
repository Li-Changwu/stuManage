package com.lcw.servlet;

import com.lcw.dao.IStudentDao;
import com.lcw.domain.Student;
import com.lcw.util.jdbcUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/modify")
public class Modify extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置编码
        req.setCharacterEncoding("utf-8");
        //获取参数
        String name = req.getParameter("name");
        String sex = req.getParameter("gender");
        String age = req.getParameter("age");
        String sid = req.getParameter("stdNumber");
        String major = req.getParameter("major");


        Student student = new Student(name, age, sex, sid, major);
        System.out.println(student);

        IStudentDao studentDao = jdbcUtil.getIStudentDao();

        //判断该学生是否存在
        if(studentDao.findById(sid).isEmpty()){
            resp.getWriter().print("该学号不存在！");
            //该学号不存在
        }else{
            studentDao.updateStudent(student);
        }
        jdbcUtil.commit();
        req.getRequestDispatcher("/modify.html").forward(req,resp);
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
