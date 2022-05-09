<template>
    <div class="content">
        <el-page-header @back="goBack" content="Select registration type"></el-page-header>
        <div class="registerType">
            <el-button type="primary" icon="el-icon-edit" @click="toUser">我是顾客</el-button>
            <!-- <el-button type="primary" icon="el-icon-share" @click=toRider>我是骑手</el-button> -->
            <!-- <el-button type="primary" icon="el-icon-share" @click=toShopManager>我是店家</el-button> -->
        </div>
    </div>


</template>

<script>
    export default {
        name: "RegisterType",
        methods:{
            toUser(){
                this.$router.replace('/toUserRegister');
            },

            toRider(){
                this.$router.replace('/toRiderRegister');
            },

            toShopManager(){
                this.$router.replace('/toShopManagerRegister');
            },

            goBack(){
                this.$router.replace('/')
            }
        }
    }
</script>

<style scoped>
    .el-button{
        width: 200px;
        height: 80px;
    }

    /* .registerType{
        width: 700px;
        height: 100px;
        margin: 250px auto;
        padding:auto 60px;
    } */
</style>
