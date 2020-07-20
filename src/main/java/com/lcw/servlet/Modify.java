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
import java.util.HashMap;
import java.util.Map;

@WebServlet("/modify")
public class Modify extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置编码
        req.setCharacterEncoding("utf-8");
        //存储返回的json数据
        Map<String, Object> map = new HashMap<>();
        map.put("status","200");
        //获取参数
        String name = req.getParameter("name");
        String sex = req.getParameter("gender");
        String age = req.getParameter("age");
        String sid = req.getParameter("stdNumber");
        String major = req.getParameter("major");

        Student student = new Student(name, age, sex, sid, major);

        IStudentDao studentDao = jdbcUtil.getSqlSession().getMapper(IStudentDao.class);

        //判断该学生是否存在
        if(studentDao.findById(sid).isEmpty()){
            //该学号不存在
            map.put("userExist",false);
            map.put("updateCorrect",false);
            map.put("msg","该用户不存在");
        }else{
            map.put("userExist",true);
            if (studentDao.updateStudent(student) == 1){
                map.put("updateCorrect",true);
                map.put("msg","更新用户成功");
            }else{
                map.put("updateCorrect",false);
                map.put("msg","更新用户失败");
            }
        }
        resp.setContentType("text/html;charset=utf-8");
        resp.getWriter().write(JSON.toJSONString(map));
        //req.getRequestDispatcher("/modify.html").forward(req,resp);
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
