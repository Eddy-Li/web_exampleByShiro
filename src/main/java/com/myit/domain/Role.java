package com.myit.domain;


import lombok.Data;

@Data
public class Role {
    private Long id;
    private String name;  //角色名称
    private String sn;  //角色表达式： empMgr
}
