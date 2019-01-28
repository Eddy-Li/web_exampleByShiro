package com.myit.domain;


import lombok.Data;

@Data
public class Permission {
    private Long id;
    private String name;  //权限名称
    private String resource; //资源表达式xx:xx  比如：employee:list

}
