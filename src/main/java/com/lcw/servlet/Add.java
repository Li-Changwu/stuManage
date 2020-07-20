package com.lcw.servlet;

import com.alibaba.fastjson.JSON;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/add")
public class Add extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置编码
        req.setCharacterEncoding("utf-8");

        //存储返回的json数据
        Map<String, Object> map = new HashMap<>();
        map.put("status","200");
        //获取代理对象
        IStudentDao studentDao = jdbcUtil.getSqlSession().getMapper(IStudentDao.class);

        //获取参数
        String name = req.getParameter("name");
        String sex = req.getParameter("gender");
        String age = req.getParameter("age");
        String sid = req.getParameter("stdNumber");
        String major = req.getParameter("major");

        Student student = new Student(name, age, sex, sid, major);

        //判断该学生是否存在
        if(studentDao.findById(sid).isEmpty()){
            map.put("userExist",true);
            try {
                studentDao.insertStudent(student);
                map.put("addCorrect",true);
                map.put("msg","插入成功");
                map.put("status","201");
            }catch (Exception e){
                map.put("addCorrect",false);
                map.put("msg","插入失败");
            }

        }else{
            //该学号已存在
            map.put("userExist",true);
            map.put("addCorrect",false);
            map.put("msg","用户已存在");
        }

        resp.setContentType("text/html;charset=utf-8");
        resp.getWriter().write(JSON.toJSONString(map));
        //req.getRequestDispatcher("/add.html").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
