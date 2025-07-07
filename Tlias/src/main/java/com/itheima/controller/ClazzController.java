package com.itheima.controller;

import com.itheima.anno.LogOperation;
import com.itheima.pojo.*;
import com.itheima.service.ClazzService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/clazzs")
@RestController
public class ClazzController {

    @Autowired
    private ClazzService clazzService;

    @GetMapping
    Result page(ClazzQueryParam clazzQueryParam) {
        log.info("查询班级： {}", clazzQueryParam);
        PageResult<Clazz> pageResult =  clazzService.page(clazzQueryParam);
        return Result.success(pageResult);
    }

    @LogOperation
    @PostMapping
    public Result save(@RequestBody Clazz clazz) {
        log.info("增加班级： {}", clazz);
        clazzService.save(clazz);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id) {
        log.info("按照id查询班级： {}", id);
        return Result.success(clazzService.getInfo(id));
    }

    @LogOperation
    @PutMapping
    public Result update(@RequestBody Clazz clazz) {
        log.info("按照id修改班级： {}", clazz);
        clazzService.update(clazz);
        return Result.success();
    }

    @LogOperation
    /** 删除员工*/
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        log.info("按照id删除班级： {}", id);
        clazzService.deleteById(id);
        return Result.success();
    }

    /** 查询所有班级*/
    @GetMapping("/list")
    public Result getAll() {
        log.info("查询所有班级");
        List<Clazz> clazzList = clazzService.getAll();
        return Result.success(clazzList);
    }
}
