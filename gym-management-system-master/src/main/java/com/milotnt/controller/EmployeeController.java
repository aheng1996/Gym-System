package com.milotnt.controller;

import com.milotnt.pojo.Employee;
import com.milotnt.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * @author MiloTnT
 */
@RestController
@RequestMapping("/api/employees") // 修改路径前缀为 /api
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    // 查询员工
    @GetMapping
    public List<Employee> selectEmployee() {
        return employeeService.findAll();
    }

    // 新增员工
    @PostMapping
    public Employee addEmployee(@RequestBody Employee employee) {
        // 工号随机生成
        Random random = new Random();
        String account1 = "1010";
        for (int i = 0; i < 5; i++) {
            account1 += random.nextInt(10);
        }
        Integer account = Integer.parseInt(account1);
        // 获取当前日期
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String nowDay = simpleDateFormat.format(date);
        employee.setEmployeeAccount(account);
        employee.setEntryTime(nowDay);
        employeeService.insertEmployee(employee);
        return employee;
    }

    // 删除员工
    @DeleteMapping("/{employeeAccount}")
    public void deleteEmployee(@PathVariable Integer employeeAccount) {
        employeeService.deleteByEmployeeAccount(employeeAccount);
    }

    // 修改员工信息
    @PutMapping
    public Employee updateEmployee(@RequestBody Employee employee) {
        employeeService.updateMemberByEmployeeAccount(employee);
        return employee;
    }

    // 根据员工账号查询
    @GetMapping("/{employeeAccount}")
    public List<Employee> selectByEmployeeAccount(@PathVariable Integer employeeAccount) {
        return employeeService.selectByEmployeeAccount(employeeAccount);
    }
}
