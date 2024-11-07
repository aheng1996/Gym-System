<template>
  <div class="sb-nav-fixed">
    <nav-bar></nav-bar>
    <div id="layoutSidenav">
      <side-nav></side-nav>
      <div id="layoutSidenav_content">
        <main>
          <div class="container-fluid px-4">
            <h1 class="mt-4">添加员工</h1>
            <div class="card mb-4">
              <form @submit.prevent="beforeSubmit">
                <table class="table table-hover">
                  <tbody> <!-- 添加 tbody -->
                  <tr>
                    <td text-align="center">工号：</td>
                    <td><input type="text" class="form-control" placeholder="工号由系统自动生成" v-model="employeeAccount" disabled></td>
                  </tr>
                  <tr>
                    <td text-align="center">姓名：</td>
                    <td><input type="text" class="form-control" placeholder="姓名" v-model="employeeName"></td>
                  </tr>
                  <tr>
                    <td text-align="center">性别：</td>
                    <td><input type="text" class="form-control" placeholder="性别" v-model="employeeGender"></td>
                  </tr>
                  <tr>
                    <td text-align="center">年龄：</td>
                    <td><input type="text" class="form-control" placeholder="年龄" v-model="employeeAge"></td>
                  </tr>
                  <tr>
                    <td text-align="center">职务：</td>
                    <td><input type="text" class="form-control" placeholder="职务" v-model="staff"></td>
                  </tr>
                  <tr>
                    <td text-align="center">备注信息：</td>
                    <td><input type="text" class="form-control" placeholder="备注信息" v-model="employeeMessage"></td>
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
        <footer-component></footer-component>
      </div>
    </div>
    <common-scripts></common-scripts>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import NavBar from '@/components/NavBar.vue';
import SideNav from '@/components/SideNav.vue';
import FooterComponent from '@/components/FooterComponent.vue';
import CommonScripts from '@/components/CommonScripts.vue';

const employeeAccount = ref('');
const employeeName = ref('');
const employeeGender = ref('');
const employeeAge = ref('');
const staff = ref('');
const employeeMessage = ref('');

function beforeSubmit() {
  if (employeeName.value === '') {
    alert("请输入姓名！");
    return false;
  }
  if (employeeGender.value === '') {
    alert("请输入性别！");
    return false;
  }
  if (employeeAge.value === '') {
    alert("请输入年龄！");
    return false;
  }
  if (staff.value === '') {
    alert("请输入职务！");
    return false;
  }

  console.log('提交表单', { employeeAccount: employeeAccount.value, employeeName: employeeName.value, employeeGender: employeeGender.value, employeeAge: employeeAge.value, staff: staff.value, employeeMessage: employeeMessage.value });

  employeeName.value = '';
  employeeGender.value = '';
  employeeAge.value = '';
  staff.value = '';
  employeeMessage.value = '';
}
</script>

<style scoped>
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
