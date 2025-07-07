package com.itheima.service;

import com.itheima.pojo.Dept;

import java.util.List;

public interface DeptService {

    /** 返回所有部门的信息*/
    List<Dept> findAll();

    /** 删除指定Id的部门信息*/
    void deleteById(Integer id);

    /** 增加新部门*/
    void save(Dept dept);

    /** 根据id查询*/
    Dept getById(Integer id);

    /** 将数据库中数据更新*/
    void update(Dept dept);
}
