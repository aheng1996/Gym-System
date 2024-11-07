<template>
  <div id="layoutAuthentication">
    <div id="layoutAuthentication_content">
      <main>
        <div class="container">
          <div class="row justify-content-center">
            <div class="col-lg-5">
              <div class="card shadow-lg border-0 rounded-lg mt-5">
                <div class="card-header">
                  <h3 class="text-center font-weight-light my-4">管理员登录</h3>
                </div>
                <div class="card-body">
                  <form @submit.prevent="submitForm">
                    <div class="form-floating mb-3">
                      <input
                          class="form-control"
                          id="inputAdminAccount"
                          v-model="adminAccount"
                          type="text"
                          placeholder="Account"
                          @keypress="validateInput"
                      />
                      <label for="inputAdminAccount">账号</label>
                    </div>
                    <div class="form-floating mb-3">
                      <input
                          class="form-control"
                          id="inputAdminPassword"
                          v-model="adminPassword"
                          type="password"
                          placeholder="Password"
                      />
                      <label for="inputAdminPassword">密码</label>
                    </div>
                    <div class="form-check mb-3">
                      <input
                          class="form-check-input"
                          id="inputRememberPassword"
                          v-model="rememberPassword"
                          type="checkbox"
                          value=""
                      />
                      <label class="form-check-label" for="inputRememberPassword">记住密码</label>
                    </div>
                    <div class="d-flex align-items-center justify-content-between mt-4 mb-0">
                      <button type="submit" class="btn btn-success">登录</button>
                      <button type="button" class="btn btn-primary" @click="goToUserLogin">转到用户登录</button>
                    </div>
                  </form>
                  <div v-if="msg" style="margin-top: 20px; text-align: center; color: red">{{ msg }}</div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </main>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import { getCurrentInstance } from 'vue';
import { useRouter } from 'vue-router';

const adminAccount = ref('');
const adminPassword = ref('');
const rememberPassword = ref(false);
const msg = ref('');

const { proxy } = getCurrentInstance();
const axios = proxy.$axios;
const router = useRouter();

function validateInput(event) {
  if (event.keyCode < 48 || event.keyCode > 57) {
    event.preventDefault();
  }
}

function submitForm() {
  if (adminAccount.value === '') {
    alert("请输入账号");
    document.getElementById('inputAdminAccount').focus();
    return;
  }
  if (adminPassword.value === '') {
    alert("请输入密码");
    document.getElementById('inputAdminPassword').focus();
    return;
  }

  axios.post('/adminLogin', {
    adminAccount: adminAccount.value,
    adminPassword: adminPassword.value,
    rememberPassword: rememberPassword.value
  })
      .then(response => {
        console.log(response.data);
        msg.value = '登录成功';
        adminAccount.value = '';
        adminPassword.value = '';
        rememberPassword.value = false;
        router.push('/admin/main');
      })
      .catch(error => {
        console.error(error);
        msg.value = '登录失败';
      });
}

// 跳转到用户登录页面
function goToUserLogin() {
  router.push('/user/login'); // 跳转到用户登录页面
}
</script>

<style scoped>
/* 你可以在这里添加组件的样式 */
.bg-primary {
  background-image: url('@/assets/img/background.jpeg');
  background-size: 100% 100%;
}
</style>
