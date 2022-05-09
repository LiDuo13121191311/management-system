<template>
    <div>
        <el-container>
            <el-header height="40px">
                <el-page-header @back="goBack" content="店家注册"></el-page-header>
            </el-header>
            <div style="width: 100%">
                <el-steps :active="active" finish-status="success" :space="500" align-center style="margin: auto">
                    <el-step title="店家注册"></el-step>
                    <el-step title="店铺申请"></el-step>
                    <el-step title="注册成功"></el-step>
                </el-steps>
            </div>
            <div v-if="active!==2">
                <router-view @func="getActive" @registerInfo="getRegisterInfo"/>
            </div>
            <div v-if="active===2">

            </div>

            <el-footer></el-footer>
        </el-container>
    </div>


</template>
<script>
    import Toast from "vant/lib/toast";

    export default {
        name: "ShopManager",
        created(){
          this.$router.push("/shopManagerRegister")
        },

        data() {
            return {
                active:0,
                registerInfo:{
                    userInfo:null,
                    shopInfo:null
                },
            };
        },
        methods: {
            getActive(data){
                if(data===1){
                    this.active = data;
                }else if(data===2){
                    this.active = data;
                }

            },
            goBack() {
                // this.$router.push("/registerType")
                this.$router.go('-1')
            },
            getRegisterInfo(data){
                this.registerInfo.userInfo = data.userInfo;
                this.registerInfo.shopInfo = data.shopInfo;
                console.log(this.registerInfo);
                this.axios.post("http://localhost:8084/shopManagerRegister",data)
                    .then(resp=>{
                    this.registerSuccess();
                });
            },

            registerSuccess() {
                this.$message({
                    message: '恭喜你，注册成功！！！',
                    type: 'success',
                    offset:300
                });
                setTimeout(()=>{
                    //注册成功，跳转到登陆界面
                    this.$router.push("/toManagerLogin");
                },3000);

            },
        }
    }
</script>
<style>
    .el-form{
        width: 500px;
        height: 200px;
        margin: 20px auto;
    }

    .el-container{
        height: 800px;
    }
    .el-main {
        background-color: #E9EEF3;
        color: #333;
        text-align: center;
        line-height: 160px;
        height: 600px;
    }

    .el-header {
        background-color: #409EFF;
        color: #333;
        text-align: center;
    }

    .el-footer{
        background-color: #B3C0D1;
        color: #333;
    }
</style>
<style scoped>

</style>
