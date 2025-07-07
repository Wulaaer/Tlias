package com.itheima.controller;

import com.itheima.anno.LogOperation;
import com.itheima.pojo.Emp;
import com.itheima.pojo.EmpQueryParam;
import com.itheima.pojo.PageResult;
import com.itheima.pojo.Result;
import com.itheima.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * 员工管理
 */
@Slf4j
@RequestMapping("/emps")
@RestController
public class EmpController {

    @Autowired
    private EmpService empService;

    /** 按照分页查询员工*/
    @GetMapping
    public Result page(EmpQueryParam empQueryParam) {
        log.info("查询请求参数： {}", empQueryParam);
        PageResult<Emp> pageResult = empService.page(empQueryParam);
        return Result.success(pageResult);
    }

    /** 增加员工*/
    @LogOperation
    @PostMapping
    public Result save(@RequestBody Emp emp) {
        log.info("增加员工： {}", emp);
        empService.save(emp);
        return Result.success();
    }

    /** 删除员工*/
    @LogOperation
    @DeleteMapping
    public Result delete(@RequestParam List<Integer> ids) {
        log.info("按照id删除员工： {}", ids);
        empService.deleteByIds(ids);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id) {
        log.info("按照id查询员工： {}", id);
        return Result.success(empService.getInfo(id));
    }

    @LogOperation
    @PutMapping
    public Result update(@RequestBody Emp emp) {
        log.info("按照id修改员工： {}", emp);
        empService.update(emp);
        return Result.success();
    }

    @GetMapping("/list")
    public Result getAll() {
        log.info("查询全部员工");
        List<Emp> empList = empService.getAll();
        return Result.success(empList);
    }
}