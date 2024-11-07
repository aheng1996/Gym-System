<template>
  <div id="layoutAuthentication">
    <div id="layoutAuthentication_content">
      <main>
        <div class="container">
          <div class="row justify-content-center">
            <div class="col-lg-5">
              <div class="card shadow-lg border-0 rounded-lg mt-5">
                <div class="card-header">
                  <h3 class="text-center font-weight-light my-4">会员登录</h3>
                </div>
                <div class="card-body">
                  <form @submit.prevent="submitForm">
                    <div class="form-floating mb-3">
                      <input
                          class="form-control"
                          id="inputAccount"
                          v-model="memberAccount"
                          type="text"
                          placeholder="Account"
                          @keypress="validateInput"
                      />
                      <label for="inputAccount">账号</label>
                    </div>
                    <div class="form-floating mb-3">
                      <input
                          class="form-control"
                          id="inputPassword"
                          v-model="memberPassword"
                          type="password"
                          placeholder="Password"
                      />
                      <label for="inputPassword">密码</label>
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
                      <button type="submit" class="btn btn-success" :disabled="!validateForm()">登录</button>
                      <button type="button" class="btn btn-primary" @click="goToAdminLogin">转到管理员登录</button>
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

const memberAccount = ref(localStorage.getItem('memberAccount') || '');
const memberPassword = ref('');
const rememberPassword = ref(false);
const msg = ref('');

const { proxy } = getCurrentInstance();
const axios = proxy.$axios;
const router = useRouter();

function validateInput(event) {
  // 仅允许输入数字（如果需要）
  if (event.keyCode < 48 || event.keyCode > 57) {
    event.preventDefault();
  }
}

function validateForm() {
  return memberAccount.value !== '' && memberPassword.value !== '';
}

function submitForm() {
  if (!validateForm()) {
    alert("请输入账号和密码");
    return;
  }

  axios.post('/userLogin', {
    memberAccount: memberAccount.value,
    memberPassword: memberPassword.value,
    rememberPassword: rememberPassword.value
  })
      .then(response => {
        console.log(response.data);
        msg.value = '登录成功';
        if (rememberPassword.value) {
          localStorage.setItem('memberAccount', memberAccount.value);
        } else {
          localStorage.removeItem('memberAccount');
        }
        memberAccount.value = '';
        memberPassword.value = '';
        router.push('/user/main'); // 跳转到用户主界面
      })
      .catch(error => {
        console.error(error);
        msg.value = error.response?.data.message || '登录失败';
      });
}

// 跳转到管理员登录页面
function goToAdminLogin() {
  router.push('/admin/login');
}
</script>

<style scoped>
/* 你可以在这里添加组件的样式 */
.bg-primary {
  background-image: url('@/assets/img/background.jpeg');
  background-size: 100% 100%;
}
</style>
