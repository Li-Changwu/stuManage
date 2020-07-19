import com.alibaba.fastjson.JSON;
import com.lcw.domain.Student;
import org.junit.Test;

public class testJson {
    @Test
    public void testJson(){
        Student student = new Student("李长武","12","男","2302", "信息工程");
        System.out.println(student);
        System.out.println(JSON.toJSONString(student));
    }
}
