<template>
  <div class="sb-nav-fixed">
    <nav-bar></nav-bar> <!-- 导航栏组件 -->
    <div id="layoutSidenav">
      <side-nav role="admin"></side-nav> <!-- 侧边栏组件 -->
      <div id="layoutSidenav_content">
        <main>
          <div class="container-fluid px-4">
            <h1 class="mt-4">编辑会员</h1>
            <ol class="breadcrumb mb-4">
              <li class="breadcrumb-item">
                <router-link to="/admin">主页</router-link>
              </li>
              <li class="breadcrumb-item">
                <router-link to="/member/manage">会员管理</router-link>
              </li>
              <li class="breadcrumb-item active">编辑会员</li>
            </ol>
            <div class="card mb-4 mx-auto" style="max-width: 1200px;">
              <form @submit.prevent="submitForm">
                <table class="table table-hover">
                  <tbody>
                  <tr v-for="(value, key) in member" :key="key">
                    <td>{{ displayLabel(key) }}：</td>
                    <td>
                      <input
                          type="text"
                          class="form-control"
                          v-model="member[key]"
                          :readonly="key === 'memberAccount' || key === 'memberPassword'"
                      />
                    </td>
                  </tr>
                  </tbody>
                </table>
                <div style="text-align:center;margin-bottom:15px">
                  <button type="submit" class="btn btn-outline-success">确认修改</button>
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

<script>
import axios from 'axios';
import NavBar from '@/components/NavBar.vue';
import SideNav from '@/components/SideNav.vue';
import FooterComponent from '@/components/FooterComponent.vue';

export default {
  components: {
    NavBar,
    SideNav,
    FooterComponent,
  },
  data() {
    return {
      member: {
        memberAccount: '',
        memberName: '',
        memberPassword: '',
        memberGender: '',
        memberAge: '',
        memberPhone: '',
        memberHeight: '',
        memberWeight: '',
        cardClass: '',
        cardNextClass: '',
      },
    };
  },
  methods: {
    displayLabel(key) {
      const labels = {
        memberAccount: '会员账号/卡号',
        memberName: '姓名',
        memberPassword: '密码',
        memberGender: '性别',
        memberAge: '年龄',
        memberPhone: '联系方式',
        memberHeight: '身高',
        memberWeight: '体重',
        cardClass: '购买课时',
        cardNextClass: '剩余课时',
      };
      return labels[key] || key;
    },
    async fetchMemberData() {
      const account = this.$route.params.memberAccount; // 获取会员账号
      console.log('Fetching data for account:', account); // 输出参数值
      const url = `/api/members/${account}`;
      console.log('请求 URL:', url); // 输出请求 URL
      try {
        const response = await axios.get(url);
        this.member = response.data; // 直接赋值给 member
      } catch (error) {
        console.error('获取会员数据失败:', error);
      }
    },
    validatePhone() {
      const phoneRegex = /^[1][3-9][0-9]{9}$/;
      return phoneRegex.test(this.member.memberPhone);
    },
    async submitForm() {
      if (!this.validatePhone()) {
        alert('手机号码错误！');
        return;
      }
      try {
        await axios.put(`/api/members/${this.member.memberAccount}`, this.member); // 使用 PUT 请求更新会员信息
        alert('会员信息更新成功！');
        this.$router.push('/member/manage');
      } catch (error) {
        console.error('提交会员数据失败:', error);
        alert('提交失败，请稍后重试！');
      }
    },
  },
  created() {
    this.fetchMemberData();
  },
};
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
  padding-left: 220px; /* 调整侧边栏的宽度 */
  padding-top: 20px;
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

#side-nav {
  width: 220px; /* 设置侧边栏的宽度 */
  flex-shrink: 0;
}
</style>
