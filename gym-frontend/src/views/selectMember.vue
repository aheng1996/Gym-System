<template>
  <div class="sb-nav-fixed">
    <nav-bar></nav-bar> <!-- 导航栏组件 -->
    <div id="layoutSidenav">
      <side-nav role="admin"></side-nav> <!-- 侧边栏组件 -->
      <div id="layoutSidenav_content">
        <main>
          <div class="container-fluid px-4">
            <h1 class="mt-4">会员管理</h1>
            <ol class="breadcrumb mb-4">
              <li class="breadcrumb-item">
                <router-link to="/toAdminMain">主页</router-link>
              </li>
              <li class="breadcrumb-item active">会员管理</li>
            </ol>
            <div class="card mb-4">
              <div class="card-header">
                <i class="fas fa-table me-1"></i>
                会员信息表
              </div>
              <div class="card-body">
                <table id="datatablesSimple" class="table table-hover">
                  <thead>
                  <tr>
                    <th>会员账号/卡号</th>
                    <th>姓名</th>
                    <th>办卡时间</th>
                    <th>性别</th>
                    <th>年龄</th>
                    <th>联系方式</th>
                    <th>身高</th>
                    <th>体重</th>
                    <th>购买课时</th>
                    <th>剩余课时</th>
                    <th>操作</th>
                  </tr>
                  </thead>
                  <tbody>
                  <tr v-for="mem in memberList" :key="mem.memberAccount">
                    <td>{{ mem.memberAccount }}</td>
                    <td>{{ mem.memberName }}</td>
                    <td>{{ mem.cardTime }}</td>
                    <td>{{ mem.memberGender }}</td>
                    <td>{{ mem.memberAge }}</td>
                    <td>{{ mem.memberPhone }}</td>
                    <td>{{ mem.memberHeight }}</td>
                    <td>{{ mem.memberWeight }}</td>
                    <td>{{ mem.cardClass }}</td>
                    <td>{{ mem.cardNextClass }}</td>
                    <td>
                      <router-link :to="`/member/toUpdateMember/${mem.memberAccount}`" style="text-decoration: none">
                        <input type="button" class="btn btn-sm btn-outline-primary" value="编辑" />
                      </router-link>
                      <button @click="deleteMember(mem.memberAccount)" class="btn btn-sm btn-outline-danger">删除</button>
                    </td>
                  </tr>
                  </tbody>
                </table>
              </div>
            </div>
            <div style="text-align:center; margin-top:20px; margin-bottom:20px;">
              <router-link to="/member/toAddMember">
                <input type="button" class="btn btn-outline-success" value="添加会员信息" />
              </router-link>
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
//import { useRouter } from 'vue-router';
import NavBar from '@/components/NavBar.vue'; // 导入 Vue 组件
import SideNav from '@/components/SideNav.vue'; // 导入 Vue 组件
import FooterComponent from '@/components/FooterComponent.vue'; // 导入 Vue 组件
import CommonScripts from '@/components/CommonScripts.vue'; // 导入 Vue 组件
import axios from 'axios';

const memberList = ref([]);
//const router = useRouter();

onMounted(() => {
  fetchMembers();
});

function fetchMembers() {
  axios.get('/api/members')
      .then(response => {
        memberList.value = response.data;
      })
      .catch(error => {
        console.error('Error fetching member data:', error);
      });
}

function deleteMember(memberAccount) {
  if (confirm("确定要删除吗？")) {
    axios.delete(`/api/members/${memberAccount}`)
        .then(() => {
          fetchMembers(); // 重新获取会员列表
        })
        .catch(error => {
          console.error('Error deleting member:', error);
        });
  }
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
