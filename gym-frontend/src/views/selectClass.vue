<template>
  <div class="sb-nav-fixed">
    <nav-bar></nav-bar> <!-- 导航栏组件 -->
    <div id="layoutSidenav">
      <side-nav></side-nav> <!-- 侧边栏组件 -->
      <div id="layoutSidenav_content">
        <main>
          <div class="container-fluid px-4">
            <h1 class="mt-4">课程管理</h1>
            <ol class="breadcrumb mb-4">
              <li class="breadcrumb-item"><router-link to="/toAdminMain">主页</router-link></li>
              <li class="breadcrumb-item active">课程管理</li>
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
                    <th>时长</th>
                    <th>教练</th>
                    <th>操作</th>
                  </tr>
                  </thead>
                  <tbody>
                  <tr v-for="cls in classList" :key="cls.classId">
                    <td>{{ cls.classId }}</td>
                    <td>{{ cls.className }}</td>
                    <td>{{ cls.classBegin }}</td>
                    <td>{{ cls.classTime }}</td>
                    <td>{{ cls.coach }}</td>
                    <td>
                      <router-link :to="`/class/selClassOrder/${cls.classId}`" style="text-decoration: none;">
                        <input type="button" class="btn btn-sm btn-outline-secondary" value="报名信息" />
                      </router-link>
                      <button @click="deleteClass(cls.classId)" class="btn btn-sm btn-outline-danger">删除</button>
                    </td>
                  </tr>
                  </tbody>
                </table>
              </div>
            </div>
            <div style="text-align:center; margin-top:20px; margin-bottom:20px;">
              <router-link to="/class/toAddClass">
                <input type="button" class="btn btn-outline-success" value="添加课程信息" />
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
//import { useRouter } from 'vue-router';
import NavBar from '@/components/NavBar.vue'; // 导入 Vue 组件
import SideNav from '@/components/SideNav.vue'; // 导入 Vue 组件
import FooterComponent from '@/components/FooterComponent.vue'; // 导入 Vue 组件
import CommonScripts from '@/components/CommonScripts.vue'; // 导入 Vue 组件

const classList = ref([
  // 示例数据
  { classId: '001', className: '瑜伽', classBegin: '2023-01-01 10:00', classTime: '60分钟', coach: '张教练' },
  // 其他课程数据...
]);

//const router = useRouter();

function deleteClass(classId) {
  if (confirm("确定要删除吗？")) {
    console.log("删除课程", classId);
    // 执行删除逻辑
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

.card-footer {
  display: flex;
  justify-content: space-between;
  align-items: center;
}
</style>
