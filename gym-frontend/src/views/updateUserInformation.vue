<template>
  <div id="layoutSidenav">
    <nav-bar></nav-bar>
    <side-nav></side-nav>
    <div id="layoutSidenav_content">
      <main>
        <div class="container-fluid px-4">
          <h1 class="mt-4">编辑个人信息</h1>
          <ol class="breadcrumb mb-4">
            <li class="breadcrumb-item"><router-link to="/user/main">主页</router-link></li>
            <li class="breadcrumb-item"><router-link to="/user/info">个人信息</router-link></li>
            <li class="breadcrumb-item active">编辑个人信息</li>
          </ol>
          <div class="card mb-4">
            <form @submit.prevent="submitForm">
              <table class="table table-hover">
                <tr>
                  <td>会员账号/卡号：</td>
                  <td><input type="text" class="form-control" v-model="member.memberAccount" readonly /></td>
                </tr>
                <tr>
                  <td>姓名：</td>
                  <td><input type="text" class="form-control" v-model="member.memberName" /></td>
                </tr>
                <tr>
                  <td>密码：</td>
                  <td><input type="password" class="form-control" v-model="member.memberPassword" /></td>
                </tr>
                <tr>
                  <td>性别：</td>
                  <td><input type="text" class="form-control" v-model="member.memberGender" /></td>
                </tr>
                <tr>
                  <td>年龄：</td>
                  <td><input type="text" class="form-control" v-model="member.memberAge" /></td>
                </tr>
                <tr>
                  <td>联系方式：</td>
                  <td><input type="text" class="form-control" v-model="member.memberPhone" /></td>
                </tr>
                <tr>
                  <td>身高：</td>
                  <td><input type="text" class="form-control" v-model="member.memberHeight" /></td>
                </tr>
                <tr>
                  <td>体重：</td>
                  <td><input type="text" class="form-control" v-model="member.memberWeight" /></td>
                </tr>
              </table>
              <div style="text-align:center;margin-bottom:15px">
                <button type="submit" class="btn btn-outline-success">确认修改</button>
              </div>
            </form>
          </div>
        </div>
      </main>
      <!-- Remove footer-bar if not used -->
      <!--<footer-bar></footer-bar>-->
    </div>
  </div>
</template>

<script>
import axios from "axios";
import NavBar from "@/components/NavBar.vue";
import SideNav from "@/components/SideNav.vue";
//import FooterComponent from "@/components/FooterComponent.vue";

export default {
  components: {
    NavBar,
    SideNav,
    //FooterComponent, // Remove this if not used
  },
  data() {
    return {
      member: {
        memberAccount: "",
        memberName: "",
        memberPassword: "",
        memberGender: "",
        memberAge: "",
        memberPhone: "",
        memberHeight: "",
        memberWeight: "",
      },
    };
  },
  methods: {
    async fetchMemberData() {
      try {
        const response = await axios.get("/api/user/details");
        this.member = response.data;
      } catch (error) {
        console.error("获取个人信息失败:", error);
      }
    },
    validatePhone() {
      const phoneRegex = /^[1][3-9][0-9]{9}$/;
      return phoneRegex.test(this.member.memberPhone);
    },
    async submitForm() {
      if (!this.validatePhone()) {
        alert("手机号码错误！");
        return;
      }
      try {
        await axios.post("/api/user/update", this.member);
        alert("个人信息更新成功！");
        this.$router.push("/user/info"); // 更新成功后重定向到个人信息页面
      } catch (error) {
        console.error("提交个人信息失败:", error);
        alert("提交失败，请稍后重试！");
      }
    },
  },
  created() {
    this.fetchMemberData();
  },
};
</script>

<style scoped>
/* 可添加局部样式 */
</style>
