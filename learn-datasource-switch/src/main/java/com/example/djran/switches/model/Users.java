package com.example.djran.switches.model;

import lombok.Data;

import java.util.Date;

@Data
public class Users {
    private String userid;
    private String username;
    private String loginid;
    private String sex;
    private String mobile;
    private String orgid;
    private String passwd;
    private String createuserid;
    private Date createtime;
    private String failno;

}
