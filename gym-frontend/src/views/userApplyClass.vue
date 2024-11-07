<template>
  <div id="layoutSidenav">
    <nav-bar></nav-bar>
    <side-nav></side-nav>

    <div id="layoutSidenav_content">
      <main>
        <div class="container-fluid px-4">
          <h1 class="mt-4">报名选课</h1>

          <ol class="breadcrumb mb-4">
            <li class="breadcrumb-item"><router-link to="/user/main">主页</router-link></li>
            <li class="breadcrumb-item active">报名选课</li>
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
                <tr v-for="course in courseList" :key="course.classId">
                  <td>{{ course.classId }}</td>
                  <td>{{ course.className }}</td>
                  <td>{{ course.classBegin }}</td>
                  <td>{{ course.classTime }}</td>
                  <td>{{ course.coach }}</td>
                  <td>
                    <button @click="applyClass(course.classId)" class="btn btn-sm btn-outline-primary">
                      报名
                    </button>
                  </td>
                </tr>
                </tbody>
              </table>
            </div>
          </div>
        </div>
      </main>
      <footer-bar></footer-bar>
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
    //FooterComponent,
  },
  data() {
    return {
      courseList: [],
    };
  },
  methods: {
    async fetchCourses() {
      try {
        const response = await axios.get("/api/user/classes");
        this.courseList = response.data;
      } catch (error) {
        console.error("获取课程信息失败:", error);
      }
    },
    async applyClass(classId) {
      try {
        await axios.post(`/api/user/applyClass`, { classId });
        alert("报名成功！");
        this.fetchCourses(); // 刷新课程数据
      } catch (error) {
        console.error("报名失败:", error);
        alert("报名失败，请稍后再试！");
      }
    },
  },
  created() {
    this.fetchCourses();
  },
};
</script>

<style scoped>
/* 可添加样式 */
</style>
