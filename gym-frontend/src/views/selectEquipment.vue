<template>
  <div class="sb-nav-fixed">
    <nav-bar></nav-bar> <!-- 导航栏组件 -->
    <div id="layoutSidenav">
      <side-nav></side-nav> <!-- 侧边栏组件 -->
      <div id="layoutSidenav_content">
        <main>
          <div class="container-fluid px-4">
            <h1 class="mt-4">器材管理</h1>
            <ol class="breadcrumb mb-4">
              <li class="breadcrumb-item">
                <router-link to="/toAdminMain">主页</router-link>
              </li>
              <li class="breadcrumb-item active">器材管理</li>
            </ol>
            <div class="card mb-4">
              <div class="card-header">
                <i class="fas fa-table me-1"></i>
                器材信息表
              </div>
              <div class="card-body">
                <table id="datatablesSimple" class="table table-hover">
                  <thead>
                  <tr>
                    <th>器材id</th>
                    <th>名称</th>
                    <th>位置</th>
                    <th>状态</th>
                    <th>备注信息</th>
                    <th>操作</th>
                  </tr>
                  </thead>
                  <tbody>
                  <tr v-for="equ in equipmentList" :key="equ.equipmentId">
                    <td>{{ equ.equipmentId }}</td>
                    <td>{{ equ.equipmentName }}</td>
                    <td>{{ equ.equipmentLocation }}</td>
                    <td>{{ equ.equipmentStatus }}</td>
                    <td>{{ equ.equipmentMessage }}</td>
                    <td>
                      <router-link :to="`/equipment/toUpdateEquipment/${equ.equipmentId}`" style="text-decoration: none">
                        <input type="button" class="btn btn-sm btn-outline-primary" value="编辑" />
                      </router-link>
                      <button @click="deleteEquipment(equ.equipmentId)" class="btn btn-sm btn-outline-danger">删除</button>
                    </td>
                  </tr>
                  </tbody>
                </table>
              </div>
            </div>
            <div style="text-align:center;margin-top:20px;margin-bottom:20px">
              <router-link to="/equipment/toAddEquipment">
                <input type="button" class="btn btn-outline-success" value="添加器材信息" />
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
import { ref } from 'vue';
import { onMounted } from 'vue';
//import { useRouter } from 'vue-router';
import NavBar from '@/components/NavBar.vue'; // 导入 Vue 组件
import SideNav from '@/components/SideNav.vue'; // 导入 Vue 组件
import FooterComponent from '@/components/FooterComponent.vue'; // 导入 Vue 组件
import CommonScripts from '@/components/CommonScripts.vue'; // 导入 Vue 组件
import axios from 'axios';

const equipmentList = ref([]);
//const router = useRouter();

onMounted(() => {
  fetchEquipment();
});

function fetchEquipment() {
  axios.get('/api/equipment')
      .then(response => {
        equipmentList.value = response.data;
      })
      .catch(error => {
        console.error('Error fetching equipment data:', error);
      });
}

function deleteEquipment(equipmentId) {
  if (confirm("确定要删除吗？")) {
    axios.delete(`/api/equipment/${equipmentId}`)
        .then(() => {
          fetchEquipment(); // 重新获取器材列表
        })
        .catch(error => {
          console.error('Error deleting equipment:', error);
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
