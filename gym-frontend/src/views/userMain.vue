<template>
  <div id="layoutAuthentication">
    <nav-bar></nav-bar> <!-- 替换为 Vue 组件 -->
    <div id="layoutSidenav">
      <side-nav :role="user"></side-nav> <!-- 替换为 Vue 组件 -->
      <div id="layoutSidenav_content">
        <main>
          <div class="container-fluid px-4">
            <div v-if="member">
              <h1 class="mt-4">{{ member.memberName }} 的主页</h1> <!-- 使用 Vue 绑定 -->
              <ol class="breadcrumb mb-4">
                <li class="breadcrumb-item active">主页</li>
              </ol>
              <p><strong>会员账号/卡号:</strong> {{ member.memberAccount }}</p>
              <p><strong>姓名:</strong> {{ member.memberName }}</p>
              <p><strong>性别:</strong> {{ member.memberGender }}</p>
              <p><strong>年龄:</strong> {{ member.memberAge }}</p>
              <p><strong>联系方式:</strong> {{ member.memberPhone }}</p>
              <p><strong>身高:</strong> {{ member.memberHeight }}</p>
              <p><strong>体重:</strong> {{ member.memberWeight }}</p>
              <p><strong>办卡时间:</strong> {{ member.registerDate }}</p>
              <p><strong>剩余课时:</strong> {{ member.remainingClasses }}</p>
              <button @click="editInfo" class="btn btn-primary">编辑个人信息</button>
            </div>
            <div v-else>
              <p>加载中...</p>
            </div>
          </div>
        </main>
        <footer-component></footer-component> <!-- 替换为 Vue 组件 -->
      </div>
    </div>
    <common-scripts></common-scripts> <!-- 替换为 Vue 组件 -->
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import axios from 'axios';
import NavBar from '@/components/NavBar.vue'; // 导入 Vue 组件
import SideNav from '@/components/SideNav.vue'; // 导入 Vue 组件
import FooterComponent from '@/components/FooterComponent.vue'; // 导入 Vue 组件
import CommonScripts from '@/components/CommonScripts.vue'; // 导入 Vue 组件

const member = ref(null);

const fetchUserInfo = async () => {
  try {
    const response = await axios.get('/api/toUserMain');
    member.value = response.data;
  } catch (error) {
    console.error('Failed to fetch user info:', error);
  }
};

onMounted(fetchUserInfo);

const editInfo = () => {
  // 跳转到编辑个人信息页面或显示编辑表单
  console.log('编辑个人信息');
};
</script>

<style scoped>
.container-fluid {
  margin-top: 20px;
}
</style>
