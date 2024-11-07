<template>
  <div class="sb-nav-fixed">
    <nav-bar></nav-bar> <!-- 导航栏组件 -->
    <div id="layoutSidenav">
      <side-nav></side-nav> <!-- 侧边栏组件 -->
      <div id="layoutSidenav_content">
        <main>
          <div class="container-fluid px-4">
            <h1 class="mt-4">编辑员工</h1>
            <ol class="breadcrumb mb-4">
              <li class="breadcrumb-item">
                <router-link to="/toAdminMain">主页</router-link>
              </li>
              <li class="breadcrumb-item">
                <router-link to="/employee/selEmployee">员工管理</router-link>
              </li>
              <li class="breadcrumb-item active">编辑员工</li>
            </ol>
            <div class="card mb-4">
              <form @submit.prevent="submitForm">
                <table class="table table-hover">
                  <tbody>
                  <tr>
                    <td text-align="center">工号：</td>
                    <td><input type="text" class="form-control" readonly v-model="employee.employeeAccount"></td>
                  </tr>
                  <tr>
                    <td text-align="center">姓名：</td>
                    <td><input type="text" class="form-control" v-model="employee.employeeName"></td>
                  </tr>
                  <tr>
                    <td text-align="center">性别：</td>
                    <td><input type="text" class="form-control" v-model="employee.employeeGender"></td>
                  </tr>
                  <tr>
                    <td text-align="center">年龄：</td>
                    <td><input type="text" class="form-control" v-model="employee.employeeAge"></td>
                  </tr>
                  <tr>
                    <td text-align="center">职务：</td>
                    <td><input type="text" class="form-control" v-model="employee.staff"></td>
                  </tr>
                  <tr>
                    <td text-align="center">备注信息：</td>
                    <td><input type="text" class="form-control" v-model="employee.employeeMessage"></td>
                  </tr>
                  </tbody>
                </table>
                <div style="text-align:center;margin-bottom:15px">
                  <input type="submit" class="btn btn-outline-success" value="确认修改">
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
import { useRoute, useRouter } from 'vue-router';
import NavBar from '@/components/NavBar.vue'; // 导入 Vue 组件
import SideNav from '@/components/SideNav.vue'; // 导入 Vue 组件
import FooterComponent from '@/components/FooterComponent.vue'; // 导入 Vue 组件
import CommonScripts from '@/components/CommonScripts.vue'; // 导入 Vue 组件
import axios from 'axios';

const employee = ref({});
const route = useRoute();
const router = useRouter();

onMounted(() => {
  fetchEmployee(route.params.employeeAccount);
});

function fetchEmployee(employeeAccount) {
  axios.get(`/api/employees/${employeeAccount}`)
      .then(response => {
        employee.value = response.data;
      })
      .catch(error => {
        console.error('Error fetching employee data:', error);
      });
}

function submitForm() {
  axios.put('/api/employees', employee.value)
      .then(() => {
        router.push('/employee/selEmployee'); // 跳转到员工列表页面
      })
      .catch(error => {
        console.error('Error updating employee:', error);
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
