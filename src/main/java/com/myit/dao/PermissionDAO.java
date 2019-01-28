package com.myit.dao;


import com.myit.domain.Permission;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissionDAO {

    /**
     * 保存权限对象
     *
     * @param permission
     */
    void save(Permission permission);

    /**
     * 获取员工的权限表达式
     *
     * @param userId
     * @return
     */
    List<String> getPermissionResourceByUserId(Long userId);


    List<String> getAllResources();
}
