<template>
  <div class="sb-nav-fixed">
    <nav-bar></nav-bar> <!-- 导航栏组件 -->
    <div id="layoutSidenav">
      <side-nav></side-nav> <!-- 侧边栏组件 -->
      <div id="layoutSidenav_content">
        <main>
          <div class="container-fluid px-4">
            <h1 class="mt-4">个人信息</h1>
            <ol class="breadcrumb mb-4">
              <li class="breadcrumb-item">
                <router-link to="/toUserMain">主页</router-link>
              </li>
              <li class="breadcrumb-item active">个人信息</li>
            </ol>
            <div class="card mb-4">
              <table class="table table-hover">
                <tbody>
                <tr>
                  <td text-align="center">会员账号/卡号：</td>
                  <td>{{ member.memberAccount }}</td>
                </tr>
                <tr>
                  <td text-align="center">姓名：</td>
                  <td>{{ member.memberName }}</td>
                </tr>
                <tr>
                  <td text-align="center">性别：</td>
                  <td>{{ member.memberGender }}</td>
                </tr>
                <tr>
                  <td text-align="center">年龄：</td>
                  <td>{{ member.memberAge }}</td>
                </tr>
                <tr>
                  <td text-align="center">联系方式：</td>
                  <td>{{ member.memberPhone }}</td>
                </tr>
                <tr>
                  <td text-align="center">身高：</td>
                  <td>{{ member.memberHeight }}</td>
                </tr>
                <tr>
                  <td text-align="center">体重：</td>
                  <td>{{ member.memberWeight }}</td>
                </tr>
                <tr>
                  <td text-align="center">办卡时间：</td>
                  <td>{{ member.cardTime }}</td>
                </tr>
                <tr>
                  <td text-align="center">剩余课时：</td>
                  <td>{{ member.cardNextClass }}</td>
                </tr>
                </tbody>
              </table>
              <div style="text-align:center;margin-bottom:15px;">
                <router-link to="/user/toUpdateInfo">
                  <input type="button" class="btn btn-outline-primary" value="编辑个人信息" />
                </router-link>
              </div>
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
import { ref, onMounted } from 'vue';
import NavBar from '@/components/NavBar.vue'; // 导入 Vue 组件
import SideNav from '@/components/SideNav.vue'; // 导入 Vue 组件
import FooterComponent from '@/components/FooterComponent.vue'; // 导入 Vue 组件
import CommonScripts from '@/components/CommonScripts.vue'; // 导入 Vue 组件
import axios from 'axios';

const member = ref({});

onMounted(() => {
  fetchMemberInfo();
});

function fetchMemberInfo() {
  axios.get('/api/user/info')
      .then(response => {
        member.value = response.data;
      })
      .catch(error => {
        console.error('Error fetching member info:', error);
      });
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
.card-header {
  font-weight: bold;
}
</style>
