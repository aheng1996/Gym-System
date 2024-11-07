package com.milotnt.controller;

import com.milotnt.pojo.ClassOrder;
import com.milotnt.pojo.ClassTable;
import com.milotnt.pojo.Member;
import com.milotnt.service.ClassOrderService;
import com.milotnt.service.ClassTableService;
import com.milotnt.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author MiloTnT
 */
@RestController
@RequestMapping("/api/user") // 修改路径前缀为/api
public class UserController {

    @Autowired
    private ClassTableService classTableService;
    @Autowired
    private MemberService memberService;
    @Autowired
    private ClassOrderService classOrderService;

    // 获取个人信息
    @GetMapping("/info")
    public Member getUserInformation(HttpSession session) {
        return (Member) session.getAttribute("user");
    }

    // 修改个人信息
    @PutMapping("/info")
    public Member updateUserInformation(HttpSession session, @RequestBody Member member) {
        Member currentMember = (Member) session.getAttribute("user");
        member.setMemberAccount(currentMember.getMemberAccount());
        member.setCardClass(currentMember.getCardClass());
        member.setCardTime(currentMember.getCardTime());
        member.setCardNextClass(currentMember.getCardNextClass());
        memberService.updateMemberByMemberAccount(member);
        return member;
    }

    // 获取我的课程
    @GetMapping("/classes")
    public List<ClassOrder> getUserClasses(HttpSession session) {
        Member member = (Member) session.getAttribute("user");
        Integer memberAccount = member.getMemberAccount();
        return classOrderService.selectClassOrderByMemberAccount(memberAccount);
    }

    // 退课
    @DeleteMapping("/classes/{classOrderId}")
    public void deleteUserClass(@PathVariable Integer classOrderId) {
        classOrderService.deleteByClassOrderId(classOrderId);
    }

    // 获取所有课程
    @GetMapping("/all-classes")
    public List<ClassTable> getAllClasses() {
        return classTableService.findAll();
    }

    // 报名选课
    @PostMapping("/classes/apply")
    public void applyClass(@RequestParam Integer classId, HttpSession session) {
        ClassTable classTable = classTableService.selectByClassId(classId);
        Member member = (Member) session.getAttribute("user");
        Integer classId1 = classTable.getClassId();
        String className = classTable.getClassName();
        String coach = classTable.getCoach();
        String classBegin = classTable.getClassBegin();
        String memberName = member.getMemberName();
        Integer memberAccount = member.getMemberAccount();
        ClassOrder classOrder = new ClassOrder(classId1, className, coach, memberName, memberAccount, classBegin);
        ClassOrder classOrder1 = classOrderService.selectMemberByClassIdAndMemberAccount(classId1, memberAccount);
        if (classOrder1 == null) {
            classOrderService.insertClassOrder(classOrder);
        }
    }
}
