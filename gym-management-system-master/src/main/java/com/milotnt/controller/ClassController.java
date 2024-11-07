package com.milotnt.controller;

import com.milotnt.pojo.ClassOrder;
import com.milotnt.pojo.ClassTable;
import com.milotnt.service.ClassOrderService;
import com.milotnt.service.ClassTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author MiloTnT
 */
@RestController
@RequestMapping("/api/classes") // 修改路径前缀为 /api
public class ClassController {

    @Autowired
    private ClassTableService classTableService;

    @Autowired
    private ClassOrderService classOrderService;

    // 查询课程
    @GetMapping
    public List<ClassTable> selectClass() {
        return classTableService.findAll();
    }

    // 新增课程
    @PostMapping
    public ClassTable addClass(@RequestBody ClassTable classTable) {
        classTableService.insertClass(classTable);
        return classTable;
    }

    // 删除课程
    @DeleteMapping("/{classId}")
    public void deleteClass(@PathVariable Integer classId) {
        classTableService.deleteClassByClassId(classId);
        classTableService.deleteOrderByClassId(classId);
    }

    // 查询课程报名信息
    @GetMapping("/orders/{classId}")
    public List<ClassOrder> selectClassOrder(@PathVariable Integer classId) {
        return classOrderService.selectMemberOrderList(classId);
    }
}
