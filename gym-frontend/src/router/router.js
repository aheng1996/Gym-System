import {createRouter, createWebHistory} from 'vue-router';
import UserLogin from '../views/userLogin.vue';
import UserMain from '../views/userMain.vue';
import UserInformation from '../views/userInformation.vue';
import UserClass from '../views/userClass.vue';
import UserApplyClass from '../views/userApplyClass.vue';
import AdminLogin from '../views/adminLogin.vue';
import AdminMain from '../views/adminMain.vue';
import AddMember from '../views/addMember.vue';
import AddEmployee from '../views/addEmployee.vue';
import AddEquipment from '../views/addEquipment.vue';
import UpdateMember from '../views/updateMember.vue';
import UpdateEmployee from '../views/updateEmployee.vue';
import UpdateEquipment from '../views/updateEquipment.vue';
import SelectMember from '../views/selectMember.vue';
import SelectClass from '../views/selectClass.vue';
import SelectEmployee from '../views/selectEmployee.vue';
import SelectEquipment from '../views/selectEquipment.vue';
import SelectByMemberAccount from '../views/selectByMemberAccount.vue';
import SelectClassOrder from '../views/selectClassOrder.vue';
import UserCommon from '../views/userCommon.vue';
import AdminCommon from '../views/adminCommon.vue';

const routes = [
    {path: '/', redirect: '/userLogin'},
    {path: '/user/login', name: 'UserLogin', component: UserLogin},
    {path: '/user/main', name: 'UserMain', component: UserMain},
    {path: '/user/toUserInfo', name: 'UserInformation', component: UserInformation},
    {path: '/user/toUpdateInfo', name: 'UpdateUserInformation', component: UpdateMember},  // 假设 UpdateMember 用于更新信息
    {path: '/user/toUserClass', name: 'UserClass', component: UserClass},
    {path: '/user/toApplyClass', name: 'UserApplyClass', component: UserApplyClass},
    {path: '/admin/login', name: 'AdminLogin', component: AdminLogin},
    {path: '/admin/main', name: 'AdminMain', component: AdminMain},
    {path: '/member/toSelByCard', name: 'SelectByMemberCard', component: SelectByMemberAccount},
    {path: '/member/selMember', name: 'SelectMember', component: SelectMember},
    {path: '/member/toUpdateMember/:memberAccount', name: 'UpdateMember', component: UpdateMember},
    {path: '/add-member', name: 'AddMember', component: AddMember},
    {path: '/add-employee', name: 'AddEmployee', component: AddEmployee},
    {path: '/add-equipment', name: 'AddEquipment', component: AddEquipment},
    {path: '/employee/selEmployee', name: 'SelectEmployee', component: SelectEmployee},
    {path: '/employee/toUpdateEmployee', name: 'UpdateEmployee', component: UpdateEmployee},
    {path: '/equipment/selEquipment', name: 'SelectEquipment', component: SelectEquipment},
    {path: '/equipment/toUpdateEquipment', name: 'UpdateEquipment', component: UpdateEquipment},
    {path: '/class/selClass', name: 'SelectClass', component: SelectClass},
    {path: '/class/selClassOrder', name: 'SelectClassOrder', component: SelectClassOrder},
    {path: '/user/common', name: 'UserCommon', component: UserCommon},
    {path: '/admin/common', name: 'AdminCommon', component: AdminCommon},
];

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes,
});

// 添加路由守卫
// router.beforeEach((to, from, next) => {
//     const isAuthenticated = /* 检查用户是否已登录的逻辑 */;
//     if (to.name !== 'UserLogin' && to.name !== 'AdminLogin' && !isAuthenticated) {
//         next({ name: 'UserLogin' }); // 或者根据需要重定向到 AdminLogin
//     } else {
//         next();
//     }
// });

export default router;
