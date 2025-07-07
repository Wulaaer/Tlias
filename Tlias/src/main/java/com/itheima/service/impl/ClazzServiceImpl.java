package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.exception.BusinessException;
import com.itheima.mapper.ClazzMapper;
import com.itheima.mapper.StudentMapper;
import com.itheima.pojo.Clazz;
import com.itheima.pojo.ClazzQueryParam;
import com.itheima.pojo.PageResult;
import com.itheima.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClazzServiceImpl implements ClazzService {

    @Autowired
    private ClazzMapper clazzMapper;

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public PageResult<Clazz> page(ClazzQueryParam clazzQueryParam) {
        // 使用插件PageHelper实现按条件分页查询
        PageHelper.startPage(clazzQueryParam.getPage(), clazzQueryParam.getPageSize());
        // 执行查询
        List<Clazz> clazzList = clazzMapper.list(clazzQueryParam);

        // 封装结果
        Page<Clazz> clazzPage = (Page<Clazz>) clazzList;
        return new PageResult<>(clazzPage.getTotal(), clazzPage.getResult());
    }

    @Override
    public void save(Clazz clazz) {
        // 补全参数
        clazz.setCreateTime(LocalDateTime.now());
        clazz.setUpdateTime(LocalDateTime.now());

        // 在员工信息表中插入数据
        clazzMapper.insert(clazz);
    }

    @Override
    public Clazz getInfo(Integer id) {
        return clazzMapper.getById(id);
    }

    @Override
    public void update(Clazz clazz) {
        clazz.setUpdateTime(LocalDateTime.now());
        clazzMapper.update(clazz);
    }

    @Override
    public void deleteById(Integer id) {
        /** 删除id指定的班级 如果还有学生在班级中则报错*/
        if (studentMapper.countByClazzId(id) > 0) {
            throw new BusinessException("不能删除还有学生的班级喵");
        }

        clazzMapper.deleteById(id);
    }

    @Override
    public List<Clazz> getAll() {
        return clazzMapper.getAll();
    }
}
