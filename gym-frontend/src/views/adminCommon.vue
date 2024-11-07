<template>
  <div class="sb-nav-fixed">
    <nav-bar></nav-bar> <!-- 导航栏组件 -->
    <div id="layoutSidenav">
      <side-nav></side-nav> <!-- 侧边栏组件 -->
      <div id="layoutSidenav_content">
        <main>
          <div class="container-fluid px-4">
            <h1 class="mt-4">添加会员</h1>
            <ol class="breadcrumb mb-4">
              <li class="breadcrumb-item"><router-link to="/toAdminMain">主页</router-link></li>
              <li class="breadcrumb-item"><router-link to="/member/selMember">会员管理</router-link></li>
              <li class="breadcrumb-item active">添加会员</li>
            </ol>
            <div class="card mb-4">
              <form @submit.prevent="beforeSubmit">
                <table class="table table-hover">
                  <tbody>
                  <tr>
                    <td text-align="center">会员账号/卡号：</td>
                    <td><input type="text" class="form-control" placeholder="卡号由系统自动生成" v-model="memberAccount" disabled></td>
                  </tr>
                  <tr>
                    <td text-align="center">姓名：</td>
                    <td><input type="text" class="form-control" placeholder="姓名" v-model="memberName"></td>
                  </tr>
                  <tr>
                    <td text-align="center">密码：</td>
                    <td><input type="text" class="form-control" placeholder="初始密码123456" v-model="memberPassword" disabled></td>
                  </tr>
                  <tr>
                    <td text-align="center">性别：</td>
                    <td><input type="text" class="form-control" placeholder="性别" v-model="memberGender"></td>
                  </tr>
                  <tr>
                    <td text-align="center">年龄：</td>
                    <td><input type="text" class="form-control" placeholder="年龄" v-model="memberAge"></td>
                  </tr>
                  <tr>
                    <td text-align="center">联系方式：</td>
                    <td><input type="text" class="form-control" placeholder="11位手机号" v-model="memberPhone"></td>
                  </tr>
                  <tr>
                    <td text-align="center">身高：</td>
                    <td><input type="text" class="form-control" placeholder="cm" v-model="memberHeight"></td>
                  </tr>
                  <tr>
                    <td text-align="center">体重：</td>
                    <td><input type="text" class="form-control" placeholder="kg" v-model="memberWeight"></td>
                  </tr>
                  <tr>
                    <td text-align="center">购买课时：</td>
                    <td><input type="text" class="form-control" placeholder="购买课时" v-model="cardClass"></td>
                  </tr>
                  </tbody>
                </table>
                <div style="text-align:center;margin-top:20px;margin-bottom:20px">
                  <input type="submit" class="btn btn-outline-success" value="添加">
                </div>
              </form>
            </div>
          </div>
        </main>
        <footer-component></footer-component> <!-- 页脚组件 -->
      </div>
    </div>
    <common-scripts></common-scripts> <!-- 公共脚本组件 -->
  </div>
</template>

<script setup>
import { ref } from 'vue';
import NavBar from '@/components/NavBar.vue'; // 导入 Vue 组件
import SideNav from '@/components/SideNav.vue'; // 导入 Vue 组件
import FooterComponent from '@/components/FooterComponent.vue'; // 导入 Vue 组件
import CommonScripts from '@/components/CommonScripts.vue'; // 导入 Vue 组件

// 定义表单数据
const memberAccount = ref(''); // 会员账号由系统生成
const memberName = ref('');
const memberPassword = ref('123456'); // 初始密码
const memberGender = ref('');
const memberAge = ref('');
const memberPhone = ref('');
const memberHeight = ref('');
const memberWeight = ref('');
const cardClass = ref('');

function beforeSubmit() {
  const phonePattern = /^[1][3,4,5,7,8,9][0-9]{9}$/; // 手机号正则表达式
  if (memberName.value === '') {
    alert("请输入姓名！");
    return false;
  }
  if (memberGender.value === '') {
    alert("请输入性别！");
    return false;
  }
  if (memberAge.value === '') {
    alert("请输入年龄！");
    return false;
  }
  if (!phonePattern.test(memberPhone.value)) {
    alert("手机号码错误！");
    return false;
  }
  if (memberHeight.value === '') {
    alert("请输入身高！");
    return false;
  }
  if (memberWeight.value === '') {
    alert("请输入体重！");
    return false;
  }
  if (cardClass.value === '') {
    alert("请输入购买课时！");
    return false;
  }

  // 模拟提交表单逻辑
  console.log('提交表单', {
    memberAccount: memberAccount.value,
    memberName: memberName.value,
    memberPassword: memberPassword.value,
    memberGender: memberGender.value,
    memberAge: memberAge.value,
    memberPhone: memberPhone.value,
    memberHeight: memberHeight.value,
    memberWeight: memberWeight.value,
    cardClass: cardClass.value
  });

  // 提交成功后，清空表单
  memberName.value = '';
  memberGender.value = '';
  memberAge.value = '';
  memberPhone.value = '';
  memberHeight.value = '';
  memberWeight.value = '';
  cardClass.value = '';
}
</script>

<style scoped>
/* 添加必要的样式 */
.sb-nav-fixed {
  position: relative;
}
#layoutSidenav {
  display: flex;
}
#layoutSidenav_content {
  flex: 1;
  padding: 20px;
}
.container-fluid {
  padding: 20px;
}
.card {
  margin-bottom: 20px;
}
</style>
