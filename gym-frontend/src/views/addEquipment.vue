<template>
  <div class="sb-nav-fixed">
    <nav-bar></nav-bar> <!-- 替换为 Vue 组件 -->
    <div id="layoutSidenav">
      <side-nav></side-nav> <!-- 替换为 Vue 组件 -->
      <div id="layoutSidenav_content">
        <main>
          <div class="container-fluid px-4">
            <h1 class="mt-4">添加器材</h1>
            <ol class="breadcrumb mb-4">
              <li class="breadcrumb-item"><router-link to="/toAdminMain">主页</router-link></li>
              <li class="breadcrumb-item"><router-link to="/equipment/selEquipment">器材管理</router-link></li>
              <li class="breadcrumb-item active">添加器材</li>
            </ol>
            <div class="card mb-4">
              <form @submit.prevent="beforeSubmit">
                <table class="table table-hover">
                  <tbody>
                  <tr>
                    <td text-align="center">器材名称：</td>
                    <td><input type="text" class="form-control" placeholder="名称" v-model="equipmentName"></td>
                  </tr>
                  <tr>
                    <td text-align="center">器材位置：</td>
                    <td><input type="text" class="form-control" placeholder="x号房间" v-model="equipmentLocation"></td>
                  </tr>
                  <tr>
                    <td text-align="center">器材状态：</td>
                    <td><input type="text" class="form-control" placeholder="状态" v-model="equipmentStatus"></td>
                  </tr>
                  <tr>
                    <td text-align="center">备注信息：</td>
                    <td><input type="text" class="form-control" placeholder="备注" v-model="equipmentMessage"></td>
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
        <footer-component></footer-component> <!-- 替换为 Vue 组件 -->
      </div>
    </div>
    <common-scripts></common-scripts> <!-- 替换为 Vue 组件 -->
  </div>
</template>

<script setup>
import { ref } from 'vue';
import NavBar from '@/components/NavBar.vue'; // 导入 Vue 组件
import SideNav from '@/components/SideNav.vue'; // 导入 Vue 组件
import FooterComponent from '@/components/FooterComponent.vue'; // 导入 Vue 组件
import CommonScripts from '@/components/CommonScripts.vue'; // 导入 Vue 组件

// 定义表单数据
const equipmentName = ref('');
const equipmentLocation = ref('');
const equipmentStatus = ref('');
const equipmentMessage = ref('');

function beforeSubmit() {
  if (equipmentName.value === '') {
    alert("请输入器材名称！");
    return false;
  }
  if (equipmentLocation.value === '') {
    alert("请输入器材位置！");
    return false;
  }
  if (equipmentStatus.value === '') {
    alert("请输入器材状态！");
    return false;
  }
  if (equipmentMessage.value === '') {
    alert("请输入备注信息！");
    return false;
  }

  // 模拟提交表单逻辑
  console.log('提交表单', { equipmentName: equipmentName.value, equipmentLocation: equipmentLocation.value, equipmentStatus: equipmentStatus.value, equipmentMessage: equipmentMessage.value });

  // 提交成功后，清空表单
  equipmentName.value = '';
  equipmentLocation.value = '';
  equipmentStatus.value = '';
  equipmentMessage.value = '';
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
