<template>
    <div >
        <div style="height: 150px;margin-top: 70px">
            <div style="">
                <van-image
                        round
                        width="150"
                        height="150"
                        src="https://img01.yzcdn.cn/vant/cat.jpeg"
                />
            </div>
            <div style="margin-top: 50px">
                <van-tag color="#ffe1e1" size="large" text-color="#ad0000">vvvip</van-tag>
                <span style="color: #409EFF;" v-model="deliveryMan.name">   {{deliveryMan.name}}</span>
                <h4 style="color: #409EFF;" v-model="deliveryMan.totalNum">总接单数： {{deliveryMan.totalNum==null?0:deliveryMan.totalNum}}  </h4>
            </div>
            <div style=" margin-top: 20px">
                <van-button round type="info" style="width: 200px" @click="updateMessage">修改个人信息</van-button>
                <br>
                <br>
                <van-button round type="info" style="width: 200px" @click="loginOut">退出登录</van-button>
            </div>

        </div>

    </div>


</template>

<script>

    import Toast from "vant/lib/toast";

    export default {
        name: "My",
        created(){
            this.rider = this.$store.getters.getRider;
            this.axios.get("http://localhost:8084/findDeliveryByDid",{
                params:{
                    did:this.rider.did
                }
            }).then(resp=>{
                console.log(resp.data);
                this.deliveryMan = resp.data;
            })
        },
        data(){
            return {
                deliveryMan:{
                    name:'',
                    totalNum:'',
                }

            }
        },
        methods:{
            updateMessage(){
                /*console.log("修改个人信息");
                this.$router.push({
                    name:'message',
                    //参数会显示在请求路径中，类似于get传参
                    /!* query:{
                         username:this.username,
                         password:this.password
                     }*!/

                    //参数不会显示在请求路径中，类似于post传参
                    params:{
                        username:this.username,
                        password:this.password
                    }
                });*/
            },

            //退出登录
            loginOut(){
                Toast("退出登录");
                this.$store.commit("SET_RIDERINFO",null);
                this.$router.push("/toRiderLogin");
            }
        }
    }
</script>

<style scoped>

</style>
