<template>
  <div class="sb-nav-fixed">
    <nav-bar></nav-bar> <!-- 导航栏组件 -->
    <div id="layoutSidenav">
      <side-nav></side-nav> <!-- 侧边栏组件 -->
      <div id="layoutSidenav_content">
        <main>
          <div class="container-fluid px-4">
            <h1 class="mt-4">编辑器材</h1>
            <ol class="breadcrumb mb-4">
              <li class="breadcrumb-item">
                <router-link to="/toAdminMain">主页</router-link>
              </li>
              <li class="breadcrumb-item">
                <router-link to="/equipment/selEquipment">器材管理</router-link>
              </li>
              <li class="breadcrumb-item active">编辑器材</li>
            </ol>
            <div class="card mb-4">
              <form @submit.prevent="submitForm">
                <div v-for="equ in equipmentList" :key="equ.equipmentId">
                  <table class="table table-hover">
                    <tbody>
                    <tr>
                      <td text-align="center">器材id：</td>
                      <td><input type="text" class="form-control" readonly v-model="equ.equipmentId" name="equipmentId"></td>
                    </tr>
                    <tr>
                      <td text-align="center">器材名称：</td>
                      <td><input type="text" class="form-control" v-model="equ.equipmentName" name="equipmentName"></td>
                    </tr>
                    <tr>
                      <td text-align="center">器材位置：</td>
                      <td><input type="text" class="form-control" v-model="equ.equipmentLocation" name="equipmentLocation"></td>
                    </tr>
                    <tr>
                      <td text-align="center">器材状态：</td>
                      <td><input type="text" class="form-control" v-model="equ.equipmentStatus" name="equipmentStatus"></td>
                    </tr>
                    <tr>
                      <td text-align="center">器材备注信息：</td>
                      <td><input type="text" class="form-control" v-model="equ.equipmentMessage" name="equipmentMessage"></td>
                    </tr>
                    </tbody>
                  </table>
                  <div style="text-align:center; margin-bottom:15px;">
                    <button type="submit" class="btn btn-outline-success">确认修改</button>
                  </div>
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
import { ref, onMounted } from 'vue';
import { useRouter, useRoute } from 'vue-router';
import NavBar from '@/components/NavBar.vue'; // 导入 Vue 组件
import SideNav from '@/components/SideNav.vue'; // 导入 Vue 组件
import FooterComponent from '@/components/FooterComponent.vue'; // 导入 Vue 组件
import CommonScripts from '@/components/CommonScripts.vue'; // 导入 Vue 组件
import axios from 'axios';

const equipmentList = ref([]);
const router = useRouter();
const route = useRoute();

onMounted(() => {
  fetchEquipment();
});

function fetchEquipment() {
  const equipmentId = route.params.id;
  axios.get(`/api/equipment/${equipmentId}`)
      .then(response => {
        equipmentList.value = [response.data];
      })
      .catch(error => {
        console.error('Error fetching equipment data:', error);
      });
}

function submitForm() {
  axios.put('/api/equipment', equipmentList.value[0])
      .then(() => {
        router.push('/equipment/selEquipment');
      })
      .catch(error => {
        console.error('Error updating equipment:', error);
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
