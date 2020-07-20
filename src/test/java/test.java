import com.lcw.dao.IStudentDao;
import com.lcw.domain.Student;


import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;


/**
 * @program: my_first_mvn
 * @description: d
 * @author: 李长武
 * @create: 2020-07-03 11:03
 **/
public class test {
    InputStream in;
    SqlSessionFactory factory;
    SqlSession session;
    IStudentDao studentDao;
    @Before
    public void init()throws Exception{
        //1.读取配置文件
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.创建sqlSessionFactory工厂
        factory = new SqlSessionFactoryBuilder().build(in);
        //3.使用工厂生产sqlSession对象
        session = factory.openSession();
        //4.使用sqlSession创建dao接口的代理对象
        studentDao = session.getMapper(IStudentDao.class);
    }
    @After
    public void destroy() throws IOException {
        session.commit();
        //6.释放资源
        session.close();
        in.close();
    }
    @Test
    public void findAllTest() {
        //5.使用代理对象执行方法
        List<Student> students = studentDao.findAll();
        for(Student student: students){
            System.out.println(student);
        }

    }

    @Test
    public void findByIdTest(){
        List<Student> students = studentDao.findById("2018210400");
        for(Student s:students){
            System.out.println(s);
        }
    }
    @Test
    public void findByNameTest(){
        List<Student> students ;

        students = studentDao.findByName("龟");
        for(Student s:students){
            System.out.println(s);
        }
    }

    @Test
    public void findByIdAndNameTest(){
        List<Student> students = studentDao.findByIdAndName("23333","乌龟");
        for(Student s:students){
            System.out.println(s);
        }
    }

    @Test
    public void insertStudentTest(){
        int r = 0;
        try {
            r = studentDao.insertStudent(new Student("李555","17","男","2018210500","信息工程"));
        }catch (Exception e){
            r = -1;
        }
        finally {
            System.out.println(r);
        }
    }
    @Test
    public void deleteByIdTest(){
        int result = 0;
        try {
            result = studentDao.deleteById("2018210379");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(result);
    }

    @Test
    public void updateStudentTest(){
        int result = studentDao.updateStudent(new Student("upp","20","男", "2018210381","信息工程"));
        System.out.println(result);
    }
}
