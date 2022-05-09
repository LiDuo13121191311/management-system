<template>
    <div class="">
        <el-header class="User_Main" height="80px">
            <span></span>
            <el-button type="primary" icon="el-icon-user"  style="float: right;margin: 20px auto" @click="toLogin">
                <span>Go to login</span>
            </el-button>

        </el-header>
        <!-- <el-carousel class="Main_container">
            <el-carousel-item v-for="item in images" :key="item">
                <el-image :src="item" style="width: 100%;height: 600px;"></el-image>
            </el-carousel-item>
            
        </el-carousel> -->
        <img class="img_qingxi" style="width: 100%;height: calc(100vh - 5px);margin-top: -80px;z-index: 1;" src="../assets/back111.jpg" />
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
.img_qingxi {
    image-rendering: -moz-crisp-edges;
    image-rendering: -o-crisp-edges;
    image-rendering: -webkit-optimize-contrast;
    image-rendering: crisp-edges;
    -ms-interpolation-mode: nearest-neighbor;
}
</style>
