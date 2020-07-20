package com.lcw.dao;

import com.lcw.domain.Student;
import org.apache.ibatis.annotations.Param;


import java.util.List;

public interface IStudentDao {
    List<Student> findAll();

    List<Student> findById(@Param("studentNo") String studentNo);

    List<Student> findByName(@Param("name") String name);

    List<Student> findByIdAndName(@Param("studentNo") String studentNo,@Param("name") String name);

    int insertStudent(Student student);

    int deleteById(@Param("studentNo") String studentNo);

    int updateStudent(Student student);
}
