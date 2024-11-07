<template>
  <div class="sb-nav-fixed">
    <nav-bar></nav-bar> <!-- 导航栏组件 -->
    <div id="layoutSidenav">
      <side-nav></side-nav> <!-- 侧边栏组件 -->
      <div id="layoutSidenav_content">
        <main>
          <div class="container-fluid px-4">
            <h1 class="mt-4">我的课程</h1>
            <ol class="breadcrumb mb-4">
              <li class="breadcrumb-item">
                <router-link to="/toUserMain">主页</router-link>
              </li>
              <li class="breadcrumb-item active">我的课程</li>
            </ol>
            <div class="card mb-4">
              <div class="card-header">
                <i class="fas fa-table me-1"></i>
                课程信息表
              </div>
              <div class="card-body">
                <table id="datatablesSimple" class="table table-hover">
                  <thead>
                  <tr>
                    <th>编号</th>
                    <th>名称</th>
                    <th>时间</th>
                    <th>教练</th>
                    <th>操作</th>
                  </tr>
                  </thead>
                  <tbody>
                  <tr v-for="classOrder in classOrderList" :key="classOrder.classId">
                    <td>{{ classOrder.classId }}</td>
                    <td>{{ classOrder.className }}</td>
                    <td>{{ classOrder.classBegin }}</td>
                    <td>{{ classOrder.coach }}</td>
                    <td>
                      <button @click="deleteClass(classOrder.classOrderId)" class="btn btn-sm btn-outline-danger">退课</button>
                    </td>
                  </tr>
                  </tbody>
                </table>
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
//import { useRouter } from 'vue-router';
import NavBar from '@/components/NavBar.vue'; // 导入 Vue 组件
import SideNav from '@/components/SideNav.vue'; // 导入 Vue 组件
import FooterComponent from '@/components/FooterComponent.vue'; // 导入 Vue 组件
import CommonScripts from '@/components/CommonScripts.vue'; // 导入 Vue 组件
import axios from 'axios';

const classOrderList = ref([]);
//const router = useRouter();

onMounted(() => {
  fetchClasses();
});

function fetchClasses() {
  axios.get('/api/classes')
      .then(response => {
        classOrderList.value = response.data;
      })
      .catch(error => {
        console.error('Error fetching class data:', error);
      });
}

function deleteClass(classOrderId) {
  if (confirm("确定要退课吗？")) {
    axios.delete(`/api/classes/${classOrderId}`)
        .then(() => {
          fetchClasses(); // 重新获取课程列表
        })
        .catch(error => {
          console.error('Error deleting class:', error);
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
