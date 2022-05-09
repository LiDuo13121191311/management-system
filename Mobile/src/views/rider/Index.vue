    <template>
        <div id="app" >

            <router-view></router-view>
            <div v-show="showTabbar" style="bottom: 0">
                <van-tabbar v-model="active" >
                    <van-tabbar-item name="home" icon="home-o" v-on:click="toOrderHome">订单广场</van-tabbar-item>
                    <van-tabbar-item name="order" icon="shopping-cart-o" v-on:click="toMyOrder">我的订单</van-tabbar-item>
                    <van-tabbar-item v-on:click="toMy">我的
                        <template #icon="props">
                            <img :src="props.active ? icon.active : icon.inactive" />
                        </template>
                    </van-tabbar-item>
                </van-tabbar>
            </div>
        </div>
    </template>
    <script>
        export default {
            created(){
                this.$router.push('/toOrderHome')
            },
            data() {
                return {
                    active:'',
                    showTabbar: true,
                    icon: {
                        active: 'https://img01.yzcdn.cn/vant/user-active.png',
                        inactive: 'https://img01.yzcdn.cn/vant/user-inactive.png',
                    },
                };
            },
            methods:{
                toOrderHome(){
                    this.$router.push('/toOrderHome')
                },
                toMy(){
                    this.$router.push('/my')
                },
                toMyOrder(){
                    this.$router.push('/myOrder')
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
