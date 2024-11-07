<template>
  <div class="sidebar-menu">
    <el-menu :default-active="activeMenu" class="el-menu-vertical-demo" router>
      <!-- 管理员专属菜单项 -->
      <template v-if="role === 'admin'">
        <el-menu-item index="/admin/main">
          <el-icon><House></House></el-icon> 主页
        </el-menu-item>
        <el-menu-item index="/member/toSelByCard">
          <el-icon><Search></Search></el-icon> 会员卡查询
        </el-menu-item>
        <el-menu-item index="/member/selMember">
          <el-icon><User></User></el-icon> 会员管理
        </el-menu-item>
        <el-menu-item index="/employee/selEmployee">
          <el-icon><UserFilled></UserFilled></el-icon> 员工管理
        </el-menu-item>
        <el-menu-item index="/equipment/selEquipment">
          <el-icon><Tools></Tools></el-icon> 器材管理
        </el-menu-item>
        <el-menu-item index="/class/selClass">
          <el-icon><Notebook></Notebook></el-icon> 课程管理
        </el-menu-item>
      </template>

      <!-- 用户专属菜单项 -->
      <template v-else>
        <el-menu-item index="/user/main">
          <el-icon><House></House></el-icon> 用户主页
        </el-menu-item>
        <el-menu-item index="/user/classes">
          <el-icon><Notebook></Notebook></el-icon> 我的课程
        </el-menu-item>
        <el-menu-item index="/user/info">
          <el-icon><User></User></el-icon> 个人信息
        </el-menu-item>
      </template>
    </el-menu>
  </div>
</template>

<script>
import { ref } from 'vue';
import { ElMenu, ElMenuItem, ElIcon } from 'element-plus';
import { Search, User, UserFilled, Tools, Notebook, House } from '@element-plus/icons-vue';

export default {
  props: {
    role: {
      type: String,
      required: true
    }
  },
  components: {
    ElMenu,
    ElMenuItem,
    ElIcon,
    Search,
    User,
    UserFilled,
    Tools,
    Notebook,
    House
  },
  setup() {
    const activeMenu = ref('/home');
    return { activeMenu };
  }
};
</script>
