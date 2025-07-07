package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.OperateLogMapper;
import com.itheima.pojo.OperateLog;
import com.itheima.pojo.PageResult;
import com.itheima.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogServiceImpl implements LogService {

    @Autowired
    OperateLogMapper operateLogMapper;


    @Override
    public PageResult page(Integer page, Integer pageSize) {
        // 设置分页参数
        PageHelper.startPage(page, pageSize);
        // 执行查询
        List<OperateLog> logList = operateLogMapper.list(page, pageSize);

        // 封装结果
        Page<OperateLog> logPage = (Page<OperateLog>) logList;
        return new PageResult<>(logPage.getTotal(), logPage.getResult());
    }
}
