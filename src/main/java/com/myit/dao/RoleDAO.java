package com.myit.dao;


import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface RoleDAO {
    /**
     * 查找所有的角色
     *
     * @return
     */
    List<String> getAllRoleSn();

    /**
     * 通过用户id获取角色表达式
     *
     * @param userId
     * @return
     */
    List<String> getRoleSnByUserId(Long userId);
}
