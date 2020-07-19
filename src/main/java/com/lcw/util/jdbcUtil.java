package com.lcw.util;

import com.lcw.dao.IStudentDao;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class jdbcUtil {
    private static InputStream in;
    private static SqlSession session;
    public static IStudentDao getIStudentDao(){
        try {
            in = Resources.getResourceAsStream("SqlMapConfig.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        session = factory.openSession();
        return session.getMapper(IStudentDao.class);
    }
    public static void commit(){
        session.commit();
    }

}
