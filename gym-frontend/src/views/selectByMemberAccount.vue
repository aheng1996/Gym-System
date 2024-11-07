<template>
  <div class="sb-nav-fixed">
    <nav-bar></nav-bar> <!-- 导航栏组件 -->
    <div id="layoutSidenav">
      <side-nav role="admin"></side-nav> <!-- 侧边栏组件 -->
      <div id="layoutSidenav_content">
        <main>
          <div class="container-fluid px-4">
            <h1 class="mt-4">会员卡查询</h1>
            <ol class="breadcrumb mb-4">
              <li class="breadcrumb-item"><router-link to="/toAdminMain">主页</router-link></li>
              <li class="breadcrumb-item active">会员卡查询</li>
            </ol>
            <div class="card mb-4">
              <div class="card-header">
                <i class="fas fa-table me-1"></i>
                会员卡查询
              </div>
              <div class="card-body">
                <div>
                  <form @submit.prevent="submitForm">
                    <div style="width: 310px; margin-bottom: 15px;">
                      <div align="left" style="float: left;">
                        <input
                            type="text"
                            class="form-control"
                            v-model="memberAccount"
                            @keypress="validateNumber"
                            placeholder="请输入会员账号/卡号"
                        />
                      </div>
                      <div align="right">
                        <input type="submit" class="btn btn-outline-success" value="查询" />
                      </div>
                    </div>
                  </form>
                </div>
                <table class="table table-hover">
                  <thead class="bg-light">
                  <tr>
                    <th scope="col" class="border-0">会员账号/卡号</th>
                    <th scope="col" class="border-0">姓名</th>
                    <th scope="col" class="border-0">办卡时间</th>
                    <th scope="col" class="border-0">性别</th>
                    <th scope="col" class="border-0">年龄</th>
                    <th scope="col" class="border-0">联系方式</th>
                    <th scope="col" class="border-0">身高</th>
                    <th scope="col" class="border-0">体重</th>
                    <th scope="col" class="border-0">购买课时</th>
                    <th scope="col" class="border-0">剩余课时</th>
                    <th scope="col" class="border-0">操作</th>
                  </tr>
                  </thead>
                  <tbody>
                  <tr v-for="mem in memberList" :key="mem.memberAccount">
                    <td>{{ mem.memberAccount }}</td>
                    <td>{{ mem.memberName }}</td>
                    <td>{{ mem.cardTime }}</td>
                    <td>{{ mem.memberGender }}</td>
                    <td>{{ mem.memberAge }}</td>
                    <td>{{ mem.memberPhone }}</td>
                    <td>{{ mem.memberHeight }}</td>
                    <td>{{ mem.memberWeight }}</td>
                    <td>{{ mem.cardClass }}</td>
                    <td>{{ mem.cardNextClass }}</td>
                    <td>
                      <router-link :to="`/member/toUpdateMember/${mem.memberAccount}`">
                        <input type="button" class="btn btn-sm btn-outline-primary" value="编辑" />
                      </router-link>
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
import {ref, onMounted} from 'vue';
import axios from 'axios';
import NavBar from '@/components/NavBar.vue'; // 导入 Vue 组件
import SideNav from '@/components/SideNav.vue'; // 导入 Vue 组件
import FooterComponent from '@/components/FooterComponent.vue'; // 导入 Vue 组件
import CommonScripts from '@/components/CommonScripts.vue'; // 导入 Vue 组件

const memberAccount = ref('');
const memberList = ref([]); // 假设你有一个成员列表的 API 结果

onMounted(() => {
  fetchMemberList(); // 初始化时可以获取所有会员，或可根据需求修改为不调用
});

// 根据会员账号查询会员信息
async function fetchMemberList() {
  const response = await axios.get('/members'); // 假设返回的数据是成员列表
  memberList.value = response.data; // 仅在初始化时获取
}

function validateNumber(event) {
  const keyCode = event.keyCode ? event.keyCode : event.which;
  if (keyCode < 48 || keyCode > 57) {
    event.preventDefault();
  }
}

async function submitForm() {
  if (!memberAccount.value) {
    alert("请输入会员账号/卡号"); // 提示用户输入
    return;
  }

  console.log('提交表单', {memberAccount: memberAccount.value});
  const response = await axios.get(`/members?account=${memberAccount.value}`); // 假设 API 支持根据账号过滤
  memberList.value = response.data; // 更新会员列表
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
