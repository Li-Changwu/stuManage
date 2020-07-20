package com.lcw.servlet;

import com.alibaba.fastjson.JSON;
import com.lcw.dao.IStudentDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.lcw.util.jdbcUtil;

@WebServlet("/delete")
public class DeleteById extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置编码
        req.setCharacterEncoding("utf-8");

        //存储返回的json数据
        Map<String, Object> map = new HashMap<>();
        map.put("status","200");

        //获取参数
        String sid = req.getParameter("stdNumber");

        //IStudentDao studentDao = jdbcUtil.getIStudentDao();
        IStudentDao studentDao = jdbcUtil.getSqlSession().getMapper(IStudentDao.class);

        int result = studentDao.deleteById(sid);
        if(result == 0){
            map.put("userExist",false);
            map.put("deleteCorrect",false);
            map.put("msg","该用户已经不存在");
        }else if (result == 1){
            map.put("userExist",true);
            map.put("deleteCorrect",true);
            map.put("msg","删除成功");
            map.put("status","204");//将状态码改为204
        }else {
            map.put("userExist",true);
            map.put("deleteCorrect",false);
            map.put("msg","删除失败");
        }
        resp.setContentType("text/html;charset=utf-8");
        resp.getWriter().write(JSON.toJSONString(map));
        //req.getRequestDispatcher("/delete.html").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
