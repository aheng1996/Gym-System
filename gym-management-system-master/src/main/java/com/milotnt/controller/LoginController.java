package com.milotnt.controller;

import com.milotnt.pojo.Admin;
import com.milotnt.pojo.Member;
import com.milotnt.service.AdminService;
import com.milotnt.service.EmployeeService;
import com.milotnt.service.EquipmentService;
import com.milotnt.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * @author MiloTnT
 */
@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    private MemberService memberService;
    @Autowired
    private AdminService adminService;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private EquipmentService equipmentService;

    // 管理员登录
    @PostMapping("/adminLogin")
    public ResponseEntity<String> adminLogin(@RequestBody Admin admin, HttpSession session) {
        Admin admin1 = adminService.adminLogin(admin);
        if (admin1 != null) {
            // 设置会话属性
            Integer memberTotal = memberService.selectTotalCount();
            session.setAttribute("memberTotal", memberTotal);
            Integer employeeTotal = employeeService.selectTotalCount();
            session.setAttribute("employeeTotal", employeeTotal);
            Integer humanTotal = memberTotal + employeeTotal;
            session.setAttribute("humanTotal", humanTotal);
            Integer equipmentTotal = equipmentService.selectTotalCount();
            session.setAttribute("equipmentTotal", equipmentTotal);
            return ResponseEntity.ok("adminMain"); // 返回视图名称
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("账号或密码错误，请重新输入！");
    }

    // 会员登录
    @PostMapping("/userLogin")
    public ResponseEntity<String> userLogin(@RequestBody Member member, HttpSession session) {
        Member member1 = memberService.userLogin(member);
        if (member1 != null) {
            session.setAttribute("user", member1);
            return ResponseEntity.ok("登录成功"); // 返回成功消息
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("账号或密码错误，请重新输入！");
    }

    // 跳转管理员主页
    @GetMapping("/toAdminMain")
    public ResponseEntity<String> toAdminMain(HttpSession session) {
        Integer memberTotal = (Integer) session.getAttribute("memberTotal");
        Integer employeeTotal = (Integer) session.getAttribute("employeeTotal");
        Integer humanTotal = (Integer) session.getAttribute("humanTotal");
        Integer equipmentTotal = (Integer) session.getAttribute("equipmentTotal");
        // 返回相应的视图或数据
        return ResponseEntity.ok("adminMain");
    }

    // 跳转会员主页
    @GetMapping("/toUserMain")
    public ResponseEntity<Member> toUserMain(HttpSession session) {
        Member user = (Member) session.getAttribute("user");
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }
}
