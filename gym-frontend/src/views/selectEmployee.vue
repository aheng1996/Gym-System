<template>
  <div class="sb-nav-fixed">
    <nav-bar></nav-bar> <!-- 导航栏组件 -->
    <div id="layoutSidenav">
      <side-nav></side-nav> <!-- 侧边栏组件 -->
      <div id="layoutSidenav_content">
        <main>
          <div class="container-fluid px-4">
            <h1 class="mt-4">员工管理</h1>
            <ol class="breadcrumb mb-4">
              <li class="breadcrumb-item">
                <router-link to="/toAdminMain">主页</router-link>
              </li>
              <li class="breadcrumb-item active">员工管理</li>
            </ol>
            <div class="card mb-4">
              <div class="card-header">
                <i class="fas fa-table me-1"></i>
                员工信息表
              </div>
              <div class="card-body">
                <table id="datatablesSimple" class="table table-hover">
                  <thead>
                  <tr>
                    <th>工号</th>
                    <th>姓名</th>
                    <th>性别</th>
                    <th>年龄</th>
                    <th>入职时间</th>
                    <th>职务</th>
                    <th>备注信息</th>
                    <th>操作</th>
                  </tr>
                  </thead>
                  <tbody>
                  <tr v-for="emp in employeeList" :key="emp.employeeAccount">
                    <td>{{ emp.employeeAccount }}</td>
                    <td>{{ emp.employeeName }}</td>
                    <td>{{ emp.employeeGender }}</td>
                    <td>{{ emp.employeeAge }}</td>
                    <td>{{ emp.entryTime }}</td>
                    <td>{{ emp.staff }}</td>
                    <td>{{ emp.employeeMessage }}</td>
                    <td>
                      <router-link :to="`/employee/toUpdateEmployee/${emp.employeeAccount}`" style="text-decoration: none">
                        <input type="button" class="btn btn-sm btn-outline-primary" value="编辑" />
                      </router-link>
                      <button @click="deleteEmployee(emp.employeeAccount)" class="btn btn-sm btn-outline-danger">解雇</button>
                    </td>
                  </tr>
                  </tbody>
                </table>
              </div>
            </div>
            <div style="text-align:center;margin-top:20px;margin-bottom:20px">
              <router-link to="/employee/toAddEmployee">
                <input type="button" class="btn btn-outline-success" value="添加员工信息" />
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
import {onMounted, ref} from 'vue';
//import { useRouter } from 'vue-router';
import NavBar from '@/components/NavBar.vue'; // 导入 Vue 组件
import SideNav from '@/components/SideNav.vue'; // 导入 Vue 组件
import FooterComponent from '@/components/FooterComponent.vue'; // 导入 Vue 组件
import CommonScripts from '@/components/CommonScripts.vue'; // 导入 Vue 组件
import axios from 'axios';

const employeeList = ref([]);
//const router = useRouter();

onMounted(() => {
  fetchEmployees();
});

function fetchEmployees() {
  axios.get('/api/employees')
      .then(response => {
        employeeList.value = response.data;
      })
      .catch(error => {
        console.error('Error fetching employee data:', error);
      });
}

function deleteEmployee(employeeAccount) {
  if (confirm("确定要解雇吗？")) {
    axios.delete(`/api/employees/${employeeAccount}`)
        .then(() => {
          fetchEmployees(); // 重新获取员工列表
        })
        .catch(error => {
          console.error('Error deleting employee:', error);
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
