import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
        userInfo: JSON.parse(localStorage.getItem("userInfo")),
        riderInfo: localStorage.getItem("riderInfo"),
        shopManagerInfo: localStorage.getItem("shopManagerInfo"),
        managerInfo: localStorage.getItem("managerInfo"),
        sid: localStorage.getItem("sid")
  },
  mutations: {
      SET_USERINFO: (state, userInfo) => {
          state.userInfo = userInfo;
          localStorage.setItem("userInfo",JSON.stringify(userInfo));
      },
      SET_RIDERINFO: (state, riderInfo) => {
          state.riderInfo = riderInfo;
          localStorage.setItem("riderInfo",JSON.stringify(riderInfo));
      },
      SET_SHOPMANAGERINFO: (state, shopManagerInfo) => {
          state.shopManagerInfo = shopManagerInfo;
          localStorage.setItem("shopManagerInfo",JSON.stringify(shopManagerInfo));
      },
      SET_MANAGERINFO: (state, managerInfo) => {
          state.managerInfo = managerInfo;
          localStorage.setItem("managerInfo",JSON.stringify(managerInfo));
      },
      SET_SID: (state, sid)=>{
          state.sid = sid;
          localStorage.setItem("sid",JSON.stringify(sid));
      }
  },
  getters: {
    getUser: state => {
        return state.userInfo
    },

    getRider: state => {
        return state.riderInfo
    },

    getShopManager: state => {
          return state.shopManagerInfo
    },

    getManager: state => {
        return state.managerInfo
    },

      getSid: state => {
          return state.sid
      }
  },
  actions: {
  },
  modules: {
  }
})
