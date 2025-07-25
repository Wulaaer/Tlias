package com.itheima.mapper;

import com.itheima.pojo.EmpExpr;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EmpExprMapper {

    /** 批量插入员工工作经历*/
    void insertBatch(List<EmpExpr> exprList);

    void deleteByEmpIds(List<Integer> empIds);
}
