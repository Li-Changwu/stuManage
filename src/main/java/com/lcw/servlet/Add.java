package com.lcw.servlet;

import com.lcw.dao.IStudentDao;
import com.lcw.domain.Student;
import com.lcw.util.jdbcUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
@WebServlet("/add")
public class Add extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置编码
        req.setCharacterEncoding("utf-8");
        //获取参数
        String name = req.getParameter("name");
        String sex = req.getParameter("gender");
        String age = req.getParameter("age");
        String sid = req.getParameter("stdNumber");
        String major = req.getParameter("major");

        Student student = new Student(name, age, sex, sid, major);

        IStudentDao studentDao = jdbcUtil.getIStudentDao();

        //判断该学生是否存在
        if(studentDao.findById(sid).isEmpty()){
            System.out.println(student);
            studentDao.insertStudent(student);
        }else{
            resp.getWriter().print("该学号已存在！");
            //该学号已存在
        }
        //提交事务
        jdbcUtil.commit();
        req.getRequestDispatcher("/add.html").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
