<template>
  <div class="sb-nav-fixed">
    <nav-bar></nav-bar> <!-- 导航栏组件 -->
    <div id="layoutSidenav">
      <side-nav></side-nav> <!-- 侧边栏组件 -->
      <div id="layoutSidenav_content">
        <main>
          <div class="container-fluid px-4">
            <h1 class="mt-4">报名信息</h1>
            <ol class="breadcrumb mb-4">
              <li class="breadcrumb-item"><router-link to="/toAdminMain">主页</router-link></li>
              <li class="breadcrumb-item"><router-link to="/class/selClass">课程管理</router-link></li>
              <li class="breadcrumb-item active">报名信息</li>
            </ol>
            <div class="card mb-4">
              <!--当classOrderList为空（该课程无人报名）时显示-->
              <div v-if="isEmpty(classOrderList)">
                <table class="table table-hover">
                  <tbody>
                  <tr>
                    <td align="center">暂无报名信息！</td>
                  </tr>
                  </tbody>
                </table>
              </div>
              <!--当classOrderList不为空（该课程已有人报名）时显示-->
              <div v-else>
                <table class="table table-hover">
                  <tbody>
                  <tr>
                    <td text-align="center">课程编号：</td>
                    <td>{{ classOrderList[0].classId }}</td>
                  </tr>
                  <tr>
                    <td text-align="center">课程名称：</td>
                    <td>{{ classOrderList[0].className }}</td>
                  </tr>
                  <tr>
                    <td text-align="center">开课时间：</td>
                    <td>{{ classOrderList[0].classBegin }}</td>
                  </tr>
                  <tr>
                    <td colspan="2"></td>
                  </tr>
                  <tr>
                    <td colspan="2"><strong>报名会员：</strong></td>
                  </tr>
                  <tr v-for="order in classOrderList" :key="order.memberAccount">
                    <td>{{ order.memberAccount }}</td>
                    <td>{{ order.memberName }}</td>
                  </tr>
                  </tbody>
                </table>
              </div>
              <router-link to="/class/selClass" style="text-align:center;margin-top:10px;margin-bottom:20px">
                <input type="submit" class="btn btn-outline-secondary" value="返回">
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
import NavBar from '@/components/NavBar.vue'; // 导入 Vue 组件
import SideNav from '@/components/SideNav.vue'; // 导入 Vue 组件
import FooterComponent from '@/components/FooterComponent.vue'; // 导入 Vue 组件
import CommonScripts from '@/components/CommonScripts.vue'; // 导入 Vue 组件

const classOrderList = ref([]); // 模拟数据

function isEmpty(list) {
  return list.length === 0;
}

// 模拟加载数据
classOrderList.value = [
  // 示例数据
  { classId: '001', className: '瑜伽', classBegin: '2023-01-01 10:00', memberAccount: '001', memberName: '张三' },
  // 其他报名数据...
];
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
