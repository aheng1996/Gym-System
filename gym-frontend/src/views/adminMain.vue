<template>
  <div class="sb-nav-fixed">
    <nav-bar></nav-bar> <!-- 导航栏组件 -->
    <div id="layoutSidenav">
      <side-nav role="admin"></side-nav> <!-- 侧边栏组件 -->
      <div id="layoutSidenav_content">
        <main>
          <div class="container-fluid px-4">
            <h1 class="mt-4">管理员主页</h1>
            <ol class="breadcrumb mb-4">
              <li class="breadcrumb-item active">主页</li>
            </ol>
            <div class="row">
              <div class="col-xl-3 col-md-6">
                <div class="card bg-primary text-white mb-4">
                  <div class="card-body">健身房总人数</div>
                  <div class="card-footer d-flex align-items-center justify-content-between">
                    <span class="small text-white">{{ humanTotal }} 人</span>
                  </div>
                </div>
              </div>
              <div class="col-xl-3 col-md-6">
                <div class="card bg-warning text-white mb-4">
                  <div class="card-body">员工人数</div>
                  <div class="card-footer d-flex align-items-center justify-content-between">
                    <span class="small text-white">{{ employeeTotal }} 人</span>
                  </div>
                </div>
              </div>
              <div class="col-xl-3 col-md-6">
                <div class="card bg-success text-white mb-4">
                  <div class="card-body">会员人数</div>
                  <div class="card-footer d-flex align-items-center justify-content-between">
                    <span class="small text-white">{{ memberTotal }} 人</span>
                  </div>
                </div>
              </div>
              <div class="col-xl-3 col-md-6">
                <div class="card bg-danger text-white mb-4">
                  <div class="card-body">器材数</div>
                  <div class="card-footer d-flex align-items-center justify-content-between">
                    <span class="small text-white">{{ equipmentTotal }} 件</span>
                  </div>
                </div>
              </div>
            </div>
            <div class="row">
              <div class="col-xl-6">
                <div class="card mb-4">
                  <div class="card-header">
                    <i class="fas fa-chart-area me-1"></i>
                    区域图示例
                  </div>
                  <div class="card-body">
                    <canvas id="myAreaChart" width="100%" height="40"></canvas>
                  </div>
                </div>
              </div>
              <div class="col-xl-6">
                <div class="card mb-4">
                  <div class="card-header">
                    <i class="fas fa-chart-bar me-1"></i>
                    柱状图示例
                  </div>
                  <div class="card-body">
                    <canvas id="myBarChart" width="100%" height="40"></canvas>
                  </div>
                </div>
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
import { ref, onMounted } from 'vue';
import NavBar from '@/components/NavBar.vue';
import SideNav from '@/components/SideNav.vue';
import FooterComponent from '@/components/FooterComponent.vue';
import CommonScripts from '@/components/CommonScripts.vue';
import {
  Chart,
  LineController,
  LineElement,
  PointElement,
  LinearScale,
  Title,
  CategoryScale,
  BarController,
  BarElement
} from 'chart.js';

// 注册所有需要的控制器和比例尺
Chart.register(
    LineController,
    LineElement,
    PointElement,
    LinearScale,
    Title,
    CategoryScale,
    BarController,
    BarElement
);

const humanTotal = ref(0);
const employeeTotal = ref(0);
const memberTotal = ref(0);
const equipmentTotal = ref(0);

onMounted(() => {
  // 模拟数据加载
  humanTotal.value = 120; // 示例数据
  employeeTotal.value = 30;
  memberTotal.value = 90;
  equipmentTotal.value = 40;

  // 加载图表
  loadCharts();
});

function loadCharts() {
  // 获取图表上下文
  const ctxArea = document.getElementById('myAreaChart').getContext('2d');
  const ctxBar = document.getElementById('myBarChart').getContext('2d');

  // 示例图表数据
  new Chart(ctxArea, {
    type: 'line',
    data: {
      labels: ['January', 'February', 'March', 'April', 'May', 'June', 'July'],
      datasets: [{
        label: 'Dataset 1',
        data: [65, 59, 80, 81, 56, 55, 40],
        fill: false,
        borderColor: 'rgb(75, 192, 192)',
        tension: 0.1
      }]
    },
    options: {
      scales: {
        x: {
          type: 'category', // 确保使用正确的类型
        },
        y: {
          beginAtZero: true
        }
      },
      plugins: {
        title: {
          display: true,
          text: 'Line Chart Example'
        }
      }
    }
  });

  new Chart(ctxBar, {
    type: 'bar',
    data: {
      labels: ['January', 'February', 'March', 'April', 'May', 'June', 'July'],
      datasets: [{
        label: 'Dataset 2',
        data: [28, 48, 40, 19, 86, 27, 90],
        backgroundColor: 'rgba(255, 99, 132, 0.2)',
        borderColor: 'rgba(255, 99, 132, 1)',
        borderWidth: 1
      }]
    },
    options: {
      scales: {
        x: {
          type: 'category', // 确保使用正确的类型
        },
        y: {
          beginAtZero: true
        }
      },
      plugins: {
        title: {
          display: true,
          text: 'Bar Chart Example'
        }
      }
    }
  });
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
.card-header {
  font-weight: bold;
}
.card-footer {
  display: flex;
  justify-content: space-between;
  align-items: center;
}
</style>
