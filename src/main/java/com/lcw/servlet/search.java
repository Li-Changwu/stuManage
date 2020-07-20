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
import java.util.List;
import java.util.Map;

@WebServlet("/search")
public class search extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        //获取参数
        String userName = req.getParameter("name");
        String stdNumber = req.getParameter("stdNumber");

        //存储返回的json数据
        Map<String, Object> map = new HashMap<>();
        map.put("status","200");

        IStudentDao studentDao = jdbcUtil.getSqlSession().getMapper(IStudentDao.class);

        List<Student> students = null;
        if(!userName.isEmpty() && !stdNumber.isEmpty()){
            try {
                students = studentDao.findByIdAndName(stdNumber, userName);
                map.put("searchCorrect",true);
                map.put("msg","查询成功");
            } catch (Exception e) {
                map.put("searchCorrect",false);
                map.put("msg","查询失败");
            }
        }
        else if(!userName.isEmpty()){
            try {
                students = studentDao.findByName(userName);
                map.put("searchCorrect",true);
                map.put("msg","查询成功");
            } catch (Exception e) {
                map.put("searchCorrect",false);
                map.put("msg","查询失败");
            }
        }
        else if (!stdNumber.isEmpty()){
            try {
                students = studentDao.findById(stdNumber);
                map.put("searchCorrect",true);
                map.put("msg","查询成功");
            } catch (Exception e) {
                map.put("searchCorrect",false);
                map.put("msg","查询失败");
            }
        }
        else {
            map.put("searchCorrect",false);
            map.put("msg","查询失败,未填写查询信息");
            //students = studentDao.findAll();
        }

        map.put("data",JSON.toJSON(students));
        resp.setContentType("text/html;charset=utf-8");
        resp.getWriter().write(JSON.toJSONString(map));
        //req.setAttribute("list",students);
        //req.getRequestDispatcher("/search.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
