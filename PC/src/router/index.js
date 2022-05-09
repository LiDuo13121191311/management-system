import Vue from 'vue'
import VueRouter from 'vue-router'
import UserLogin from '../views/UserLogin.vue'

Vue.use(VueRouter);

/*//解决编程式路由往同一地址跳转时会报错的情况
const originalPush = VueRouter.prototype.push;
const originalReplace = VueRouter.prototype.replace;
//push
VueRouter.prototype.push = function push(location, onResolve, onReject) {
  if (onResolve || onReject)
    return originalPush.call(this, location, onResolve, onReject);
  return originalPush.call(this, location).catch(err => err);
};
//replace
VueRouter.prototype.replace = function push(location, onResolve, onReject) {
  if (onResolve || onReject)
    return originalReplace.call(this, location, onResolve, onReject);
  return originalReplace.call(this, location).catch(err => err);
};*/



const routes = [
  {
    path:'/',
    component:()=>import('../views/Index.vue')
  },


  {
    path:'/login',
    name:'login',
    component:()=>import('../views/Login.vue')
  },
  {
    path: '/toUserLogin',
    name: 'UserLogin',
    component: UserLogin
  },
  {
    path: '/toRiderLogin',
    name: 'riderLogin',
    component:()=>import('../views/RiderLogin.vue')
  },
  {
    path: '/about',
    name: 'About',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
  },
  {
    path:'/register',
    component:()=>import('../views/Register.vue')
  },
  {
    path:'/registerType',
    component:()=> import('../views/RegisterType.vue')
  },
  {
    path:'/toUserRegister',
    component:()=>import('../views/UserRegister.vue')
  },
  {
    path:'/toRiderRegister',
    component:()=>import('../views/RiderRegister.vue')
  },
  {
    path:'/toShopManagerRegister',
    component:()=>import('../views/ShopManagerRegister.vue'),
    children:[
      {
        path:'/shopManagerRegister',
        component:()=>import('../views/SMRegister.vue')
      },
      {
        path:'/shopRegister',
        component:()=>import('../views/SRegister.vue')
      }
    ]
  },
  {
    path:'/toManagerLogin',
    component:()=>import('../views/ManagerLogin.vue')
  },

  {
    path:'/rider/index',
    component:()=>import('../views/rider/Index.vue'),
    children:[ //二级路由
      {
        path:'/toOrderHome',
        name:'toOrderHome',
        component:()=>import('../views/rider/Home.vue')
      },
      {
        path:'/my',
        name:'my',
        component:()=>import('../views/rider/My.vue')
      },
      {
        path:'/myOrder',
        name:'myOrder',
        component:()=>import('../views/rider/MyOrder.vue')
      },
      {
        path:'/orderInfo',
        name:'orderInfo',
        component:()=>import('../views/rider/OrderInfo.vue')
      },
      {
        path:'/orderDetail',
        name:'orderDetail',
        component:()=>import('../views/rider/OrderDetail.vue')
      },
      {
        path:'/blank_rider',
        name:'blank_rider',
        component:()=>import("../views/rider/Blank.vue")
      }

    ]
  },

  {
    path:'/manager/index',
    component:()=>import('../views/manager/Index.vue'),
    children:[ //二级路由
      {
        path:'/home',
        component:()=>import('../views/manager/Home.vue')
      },
      {
        path:'/shopMessage',
        name:'shopMessage',
        component:()=>import('../views/manager/ShopMessage.vue')
      },
      {
        path:'/dataMessage',
        component:()=>import('../views/manager/DataMessage.vue')
      },
      {
        path:'/goodsManage',
        name:'goodsManage',
        component:()=>import('../views/manager/GoodsManage.vue')
      },
      {
        path:'/categoryManage',
        name:'categoryManage',
        component:()=>import('../views/manager/CategoryManage.vue')
      },
      {
        path:'/newOrder',
        name:'newOrder',
        component:()=>import('../views/manager/NewOrder.vue')
      },
      {
        path:'/oldOrder',
        name:'oldOrder',
        component:()=>import('../views/manager/OldOrder.vue')
      },

      {
        path:'/allShops',
        name:'allShops',
        component:()=>import('../views/manager/AllShops.vue')
      },
      {
        path:'/shopApplyFor',
        name:'shopApplyFor',
        component:()=>import('../views/manager/ShopApplyFor.vue')
      },
      {
        path:'/userManager',
        name:'userManager',
        component:()=>import('../views/manager/UserManager.vue')
      },
      {
        path:'/riderManager',
        name:'riderManager',
        component:()=>import('../views/manager/RiderManager.vue')
      },
      {
        path:'/shopManagerManager',
        name:'shopManagerManager',
        component:()=>import('../views/manager/ShopManagerManager.vue')
      },
      {
        path:'/blank',
        name:'blank',
        component:()=>import('../views/manager/Blank.vue')
      }

    ]
  },

  {
    path: '/user/index',
    component:() => import('../views/user/Index.vue'),
    children:[ //二级路由
      {
        path:'/home_user',
        component:()=>import('../views/user/Home.vue')
      },
      {
        path:'/my_user',
        component:()=>import('../views/user/My.vue')
      },
      {
        path:'/order',
        component:()=>import('../views/user/Order.vue')
      },
      {
        path:'/search',
        name:'search',
        component:()=>import('../views/user/Search.vue'),
        meta:{
          keepAlive:true
        }
      },
      {
        path:'/toMessage',
        name:'message',
        component:()=>import('../views/user/Message.vue')
      },
      {
        path:'/address',
        name:'address',
        component:()=>import('../views/user/Address.vue')
      },
      {
        path:'/addressEdit',
        name:'addressEdit',
        component:()=>import('../views/user/AddressEdit.vue')
      },
      {
        path:'/addressAdd',
        name:'addressAdd',
        component:()=>import('../views/user/AddressAdd.vue')
      },
      {
        path:'/shop',
        name:'shop',
        component:()=>import('../views/user/Shop.vue')
      },
      {
        path:'/pay',
        name:'pay',
        component:()=>import('../views/user/Pay.vue')
      },
      {
        path:'/paySuccess',
        name:'paySuccess',
        component:()=>import('../views/user/PaySuccess.vue')
      },
      {
        path:'/orderInfo_user',
        name:'orderInfo_user',
        component:()=>import('../views/user/OrderInfo.vue')
      },

    ]
  }
];

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
});

export default router
