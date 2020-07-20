package com.lcw.dao;

import com.lcw.domain.User;
import org.apache.ibatis.annotations.Param;

public interface IUserDao {

    User login(@Param("userName") String userName);
}
