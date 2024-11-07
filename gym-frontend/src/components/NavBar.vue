<template>
  <nav class="navbar">
    <div class="navbar-left">
      <h1>健身房管理系统</h1>
    </div>
    <!-- 鼠标悬停在用户信息上时显示下拉菜单 -->
    <div class="navbar-right" @mouseenter="showDropdown" @mouseleave="hideDropdown">
      <div class="user-info">
        <span>欢迎, {{ userNickname }}</span>
      </div>
      <!-- 使用 transition 显示和隐藏下拉菜单 -->
      <transition name="dropdown">
        <ul v-if="isDropdownOpen" class="dropdown-menu">
          <li><a @click.stop="logout">退出登录</a></li>
        </ul>
      </transition>
    </div>
  </nav>
</template>

<script>
export default {
  name: 'NavBar',
  props: {
    userNickname: {
      type: String,
      required: true
    }
  },
  data() {
    return {
      isDropdownOpen: false
    };
  },
  methods: {
    showDropdown() {
      this.isDropdownOpen = true;
    },
    hideDropdown() {
      this.isDropdownOpen = false;
    },
    logout() {
      // 登出逻辑：清除登录状态并重定向到登录页
      this.$router.push('/login');
      localStorage.removeItem('token');
      this.isDropdownOpen = false;
    }
  }
};
</script>

<style scoped>
.navbar {
  display: flex;
  justify-content: space-between;
  align-items: center;
  background-color: #333;
  color: white;
  padding: 10px 20px;
}

.navbar-left h1 {
  margin: 0;
  font-size: 24px;
}

.navbar-right {
  display: flex;
  align-items: center;
  position: relative;
}

.user-info {
  cursor: pointer;
  font-weight: bold;
}

.dropdown-menu {
  list-style-type: none;
  margin: 0;
  padding: 0;
  position: absolute;
  right: 0;
  top: 100%;
  background-color: white;
  border: 1px solid #ccc;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  width: 150px;
  border-radius: 4px;
  overflow: hidden;
  z-index: 1;
}

/* 添加下拉菜单项的样式 */
.dropdown-menu li {
  padding: 10px;
}

.dropdown-menu li a {
  color: #333;
  text-decoration: none;
  display: block;
}

.dropdown-menu li a:hover {
  background-color: #f0f0f0;
}

/* CSS 过渡效果 */
.dropdown-enter-active, .dropdown-leave-active {
  transition: opacity 0.3s ease, transform 0.3s ease;
}

.dropdown-enter, .dropdown-leave-to {
  opacity: 0;
  transform: translateY(-10px);
}
</style>
