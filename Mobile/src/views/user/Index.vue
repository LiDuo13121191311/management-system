<template>
  <div id="app" >
    <keep-alive>
      <router-view v-if="this.$route.meta.keepAlive"></router-view>
    </keep-alive>
    <router-view v-if="!this.$route.meta.keepAlive"></router-view>

    <div v-show="showTabbar" style="bottom: 0">
      <van-tabbar v-model="active">
        <van-tabbar-item name="home" icon="home-o" v-on:click="toHome">Homepage</van-tabbar-item>
        <van-tabbar-item name="order" icon="shopping-cart-o" v-on:click="toOrderList">Order</van-tabbar-item>
        <van-tabbar-item v-on:click="toMy">My
          <template #icon="props">
          <img :src="props.active ? icon.active : icon.inactive" />
        </template>
        </van-tabbar-item>
        <!--<van-tabbar-item name="my" icon="friends-o" v-on:click="toMy">我的</van-tabbar-item>-->
      </van-tabbar>
    </div>
  </div>
</template>
<script>
  export default {
    created(){
      this.$router.push('/home_user')
    },
    data() {
      return {
        active:'',
        showTabbar: true,
        showSearch: true,
        icon: {
          active: 'https://img01.yzcdn.cn/vant/user-active.png',
          inactive: 'https://img01.yzcdn.cn/vant/user-inactive.png',
        },
      };
    },
    methods:{
      toOrderList(){
        this.$router.push('/order')
      },
      toHome(){
        this.$router.push('/home_user')
      },
      toMy(){
        this.$router.push('/my_user')
      }
    }
  }

</script>
<style>
  #app {
    font-family: Avenir, Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
    color: #2c3e50;
  }

  #nav {
    padding: 30px;
  }

  #nav a {
    font-weight: bold;
    color: #2c3e50;
  }

  #nav a.router-link-exact-active {
    color: #42b983;
  }
</style>
