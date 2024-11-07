import { createApp } from 'vue';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'element-plus/dist/index.css'; // 引入 Element Plus 样式
import ElementPlus from 'element-plus';
import * as ElementPlusIconsVue from '@element-plus/icons-vue';
import App from './App.vue';
import router from './router/router';
import './assets/css/style.css';
import NavBar from './components/NavBar.vue';
import SideNav from './components/SideNav.vue';
import FooterComponent from './components/FooterComponent.vue';
import CommonScripts from './components/CommonScripts.vue';
import axios from 'axios';

// 防抖函数
const debounce = (fn, delay) => {
    let timer;
    return (...args) => {
        if (timer) {
            clearTimeout(timer);
        }
        timer = setTimeout(() => {
            fn(...args);
        }, delay);
    };
};

window.ResizeObserver = class extends window.ResizeObserver {
    constructor(callback) {
        super(debounce(callback, 200)); // 添加防抖处理
    }
};

const app = createApp(App);

// 配置 axios 基础 URL
axios.defaults.baseURL = 'http://localhost:8080/api';
app.config.globalProperties.$axios = axios;

// 注册全局组件
app.component('nav-bar', NavBar);
app.component('side-nav', SideNav);
app.component('footer-component', FooterComponent);
app.component('common-scripts', CommonScripts);

// 使用 Element Plus 插件
app.use(ElementPlus);

// 注册 Element Plus 图标
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
    app.component(key, component);
}

// 挂载应用并使用路由
app.use(router).mount('#app');
