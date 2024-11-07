<template>
  <div class="sb-nav-fixed">
    <nav-bar></nav-bar>
    <div id="layoutSidenav">
      <side-nav></side-nav>
      <div id="layoutSidenav_content">
        <main>
          <div class="container-fluid px-4">
            <h1 class="mt-4">添加课程</h1>
            <div class="card mb-4">
              <form @submit.prevent="beforeSubmit">
                <table class="table table-hover">
                  <tbody>
                  <tr>
                    <td text-align="center">编号：</td>
                    <td><input type="text" class="form-control" placeholder="4位编号" v-model="classId"></td>
                  </tr>
                  <tr>
                    <td text-align="center">名称：</td>
                    <td><input type="text" class="form-control" placeholder="课程名称" v-model="className"></td>
                  </tr>
                  <tr>
                    <td text-align="center">时间：</td>
                    <td><input type="text" class="form-control" placeholder="xxxx年xx月xx日 00:00" v-model="classBegin"></td>
                  </tr>
                  <tr>
                    <td text-align="center">时长：</td>
                    <td><input type="text" class="form-control" placeholder="xx分钟" v-model="classTime"></td>
                  </tr>
                  <tr>
                    <td text-align="center">教练：</td>
                    <td><input type="text" class="form-control" placeholder="教练" v-model="coach"></td>
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

const classId = ref('');
const className = ref('');
const classBegin = ref('');
const classTime = ref('');
const coach = ref('');

function beforeSubmit() {
  if (classId.value === '') {
    alert("请输入课程编号！");
    return false;
  }
  if (className.value === '') {
    alert("请输入课程名称！");
    return false;
  }
  if (classBegin.value === '') {
    alert("请输入课程时间！");
    return false;
  }
  if (classTime.value === '') {
    alert("请输入课程时长！");
    return false;
  }
  if (coach.value === '') {
    alert("请输入教练！");
    return false;
  }

  console.log('提交表单', { classId: classId.value, className: className.value, classBegin: classBegin.value, classTime: classTime.value, coach: coach.value });

  classId.value = '';
  className.value = '';
  classBegin.value = '';
  classTime.value = '';
  coach.value = '';
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
