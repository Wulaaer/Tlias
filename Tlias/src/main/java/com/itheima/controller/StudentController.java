package com.itheima.controller;

import com.itheima.anno.LogOperation;
import com.itheima.pojo.*;
import com.itheima.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequestMapping("/students")
@RestController
public class StudentController {

    @Autowired
    StudentService studentService;

    @GetMapping
    public Result page(StudentQueryParam studentQueryParam) {
        log.info("查询请求参数： {}", studentQueryParam);
        PageResult<Student> pageResult = studentService.page(studentQueryParam);
        return Result.success(pageResult);
    }

    /** 增加学生*/
    @LogOperation
    @PostMapping
    public Result save(@RequestBody Student student) {
        log.info("增加学生： {}", student);
        studentService.save(student);
        return Result.success();
    }

    /** 根据id查询学生*/
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id) {
        log.info("按照id查询学生： {}", id);
        Student student = studentService.getInfo(id);
        return Result.success(student);
    }

    /** 根据id修改学生*/
    @LogOperation
    @PutMapping
    public Result update(@RequestBody Student student) {
        log.info("按照id修改学生： {}", student);
        studentService.update(student);
        return Result.success();
    }

    /** 批量删除学生*/
    @LogOperation
    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable String ids) {
        log.info("批量删除学生，ids = {}", ids);
        // 把逗号分隔的字符串转换为 List<Integer>
        List<Integer> idList = Arrays.stream(ids.split(","))
                .map(Integer::valueOf)
                .collect(Collectors.toList());
        studentService.deleteByIds(idList);
        return Result.success();
    }

    /** 对指定id学生进行违纪处理*/
    @LogOperation
    @PutMapping("/violation/{id}/{score}")
    public Result handleViolation(@PathVariable Integer id, @PathVariable Integer score) {
        log.info("处理违纪学生id: {}, 扣除{}分", id, score);
        studentService.handleViolation(id, score);
        return Result.success();
    }
}
