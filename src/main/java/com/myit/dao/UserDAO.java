package com.myit.dao;


import com.myit.domain.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDAO {
    /**
     * 通过用户名查找用户对象
     *
     * @param username
     * @return
     */
    User getUserByUsername(@Param("username") String username);
}
