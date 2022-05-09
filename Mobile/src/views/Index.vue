<template>
    <div class="User_Main_On">
        <el-header class="User_Main" height="80px">
            <span></span>
            <el-button type="primary" icon="el-icon-user"  style="float: right;margin: 20px auto" @click="toLogin">
                <span>Go to login</span>
            </el-button>

        </el-header>
        <!-- <el-carousel class="Main_container">
            <el-carousel-item v-for="item in images" :key="item">
                <el-image :src="item" style="width: 100%;height: 400px;"></el-image>
            </el-carousel-item>
        </el-carousel> -->
        <!-- <img style="width: 100%;height: calc(100vh - 5px);margin-top: -80px;z-index: 1;transform: rotate(90deg);" src="../assets/back.png" /> -->
    </div>
</template>

<script>
    export default {
        name: "Index",
        data(){
            return{
                images:[
                    '/upload/p1.png',
                    '/upload/p2.png',
                    '/upload/p3.png',


                ],
                url:'../../public/upload/p1.png'
            }
        },
        methods:{
            toLogin(){
                this.$router.push("/toUserLogin")
            }
        }
    }
</script>

<style scoped>
.bck{
    background:url(../../public/upload/p1.png) no-repeat center;
    width:100%;
    height:800px;
}

.User_Main_On {
    background:url('../assets/back.png') no-repeat center;
    background-size: cover;
    height: 100vh;
}

.el-header {
    background-color: #0e95e5;
    color: #333;
    text-align: center;
    line-height: 60px;
}
.User_Main {
    height: 80px;
    background: none;
    z-index: 99;
    position: relative;
}
</style>
