package com.milotnt.controller;

import com.milotnt.pojo.Member;
import com.milotnt.service.MemberService;
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
@RequestMapping("/api/members") // 修改路径前缀为/api
public class MemberController {

    @Autowired
    private MemberService memberService;

    // 查询会员
    @GetMapping
    public List<Member> selectMember() {
        return memberService.findAll();
    }

    // 新增会员
    @PostMapping
    public Member addMember(@RequestBody Member member) {
        // 会员账号&卡号随机生成
        Random random = new Random();
        String account1 = "2021";
        for (int i = 0; i < 5; i++) {
            account1 += random.nextInt(10);
        }
        Integer account = Integer.parseInt(account1);
        // 初始密码
        String password = "123456";
        // 获取当前日期
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String nowDay = simpleDateFormat.format(date);
        Integer nextClass = member.getCardClass();
        member.setMemberAccount(account);
        member.setMemberPassword(password);
        member.setCardTime(nowDay);
        member.setCardNextClass(nextClass);
        memberService.insertMember(member);
        return member;
    }

    // 删除会员
    @DeleteMapping("/{memberAccount}")
    public void deleteMember(@PathVariable Integer memberAccount) {
        memberService.deleteByMemberAccount(memberAccount);
    }

    // 修改会员信息
    @PutMapping
    public Member updateMember(@RequestBody Member member) {
        memberService.updateMemberByMemberAccount(member);
        return member;
    }

    // 根据会员卡号查询
    @GetMapping("/{memberAccount}")
    public List<Member> selectByCardId(@PathVariable Integer memberAccount) {
        return memberService.selectByMemberAccount(memberAccount);
    }
}