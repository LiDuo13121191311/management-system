<template>
    <div>
        <el-page-header @back="goBack" content="登录"></el-page-header>
        <div class="login-form">
            <el-tabs type="border-card" class="many_login_tabs">
                <el-tab-pane label="店铺人员登陆">
                    <div class="form">
                        <el-form :model="shopManagerForm" status-icon :rules="rules" ref="shopManagerForm" label-width="100px" class="demo-ruleForm">
                            <el-form-item label="账号" prop="s_username">
                                <el-input  :placeholder="holder1" type="text" v-model="shopManagerForm.s_username" autocomplete="off"></el-input>
                            </el-form-item>
                            <el-form-item label="密码" prop="s_password">
                                <el-input type="password" :placeholder="holder2" v-model="shopManagerForm.s_password" autocomplete="off"></el-input>
                            </el-form-item>
                            <el-form-item>
                                <el-button type="primary" @click="submitShopManagerForm('shopManagerForm')">登录</el-button>
                                <el-button style="display: none;" @click="register()">注册</el-button>
                            </el-form-item>
                        </el-form>
                    </div>
                </el-tab-pane>
                <el-tab-pane label="管理员登陆">
                    <div class="form">
                        <el-form :model="managerForm" status-icon :rules="rules" ref="managerForm" label-width="100px" class="demo-ruleForm">
                            <el-form-item label="账号" prop="m_username">
                                <el-input :placeholder="holder1" type="text" v-model="managerForm.m_username" autocomplete="off"></el-input>
                            </el-form-item>
                            <el-form-item label="密码" prop="m_password">
                                <el-input type="password" :placeholder="holder2" v-model="managerForm.m_password" autocomplete="off"></el-input>
                            </el-form-item>
                            <el-form-item>
                                <el-button type="primary" @click="submitManageForm('managerForm')">登录</el-button>
                                <el-button style="display: none;" @click="register()">注册</el-button>
                            </el-form-item>
                        </el-form>
                    </div>
                </el-tab-pane>
            </el-tabs>
        </div>
    </div>
</template>

<script>
    import Toast from "vant/lib/toast";

    export default {
        name: "ManagerLogin",
        data(){
            const validateUsername = (rule, value, callback) => {
                if (value === '') {
                    callback(new Error('请输入用户名'));
                } else {
                    callback();
                }
            };

            const validatePassword = (rule, value, callback) => {
                if (value === '') {
                    callback(new Error('请输入密码'));
                } else {
                    callback();
                }
            };

            return {
                holder1:'请输入账号',
                holder2:'请输入密码',
                shopManagerForm: {
                    s_username:'lky',
                    s_password: '123'
                },
                managerForm:{
                    m_username:'admin',
                    m_password:'123'
                },
                rules: {
                    s_username:[
                        { validator: validateUsername, trigger: 'blur' }
                    ],
                    s_password: [
                        { validator: validatePassword, trigger: 'blur' }
                    ],
                    m_username: [
                        { validator: validateUsername, trigger: 'blur' }
                    ],
                    m_password: [
                        { validator: validatePassword, trigger: 'blur' }
                    ]
                }
            };
        },

    methods: {


        /*店家登录*/
        submitShopManagerForm: function (formName) {
            const that = this;
            this.$refs[formName].validate((valid) => {
                if (valid) {
                    this.axios.get("http://localhost:8084/shopManagerLogin",{
                        params:{
                            username: this.shopManagerForm.s_username,
                            password: this.shopManagerForm.s_password
                        }
                    }).then(function (resp) {
                        console.log(resp.data);
                        if(resp.data.code === 200){
                            Toast("登陆成功");
                            that.$store.commit("SET_SHOPMANAGERINFO",resp.data.data.user);
                            that.$router.push({
                                path:'/manager/index',
                                query:{
                                    userInfo:resp.data.data.user
                                }
                            })
                        }else {
                            Toast(resp.data.message)
                        }
                    });

                } else {
                    console.log('error submit!!');
                    return false;
                }
            });
        },


        /*管理员登录*/
        submitManageForm: function (formName) {
            const that = this;
            this.$refs[formName].validate((valid) => {
                if (valid) {
                    this.axios.get("http://localhost:8084/managerLogin",{
                        params:{
                            username: this.managerForm.m_username,
                            password: this.managerForm.m_password
                        }
                    }).then(function (resp) {
                        console.log(resp.data);
                        if(resp.data.code === 200){
                            Toast("登陆成功");
                            that.$store.commit("SET_MANAGERINFO",resp.data.data.user);
                            that.$router.push({
                                path:'/manager/index',
                                query:{
                                    userInfo:resp.data.data.user
                                }
                            })

                        }else {
                            Toast("账号或密码错误！！！")
                        }
                    });

                } else {
                    console.log('error submit!!');
                    return false;
                }
            });
        },



        register(){
            this.$router.replace("/registerType")
        },
        goBack(){
            this.$router.go('-1')
        }
    }

    }
</script>

<style scoped>
    /* .login-form{
        width: 600px;
        height: 300px;
        margin: 250px auto;
    }

    .el-tabs{
        width: 500px;
        height: 300px;
        margin: 250px auto;
    } */


    .form{
        margin: 50px auto;
    }
</style>
