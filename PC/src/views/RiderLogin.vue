<template>
    <div>
        <el-page-header @back="goBack" content="骑手登录"></el-page-header>
        <div class="login-form">
            <el-tabs>
                <el-tab-pane label="手机号登陆">
                    <div class="form">
                        <el-form :model="phoneForm" status-icon :rules="rules" ref="phoneForm" label-width="100px" class="demo-ruleForm">
                            <el-form-item label="手机号"  prop="phone">
                                <el-input :placeholder="holder3" type="text" v-model="phoneForm.phone" autocomplete="off"></el-input>
                            </el-form-item>
                            <el-form-item label="验证码" style="width: 380px;display: inline-block" prop="code">
                                <el-input type="text"  :placeholder="holder4" v-model="phoneForm.code" autocomplete="off"></el-input>
                            </el-form-item>
                            <span v-if="showCode===true">
                                <el-button  type="button" style="width: 120px;" @click="sentCode">获取验证码</el-button>
                            </span>
                            <span v-if="showCode===false">
                                <el-button  type="button" style="width: 120px;">{{count}}</el-button>
                            </span>

                            <el-form-item>
                                <el-button type="primary" @click="submitForm('phoneForm')">登录</el-button>
                                <el-button @click="register()">注册</el-button>
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
        name: 'RiderLogin',
        created(){
            console.log(this.$store.getters);
        },
        data() {
            const validatePhone = (rule, value, callback) => {
                if (value === ''||value.trim().length===0) {
                    callback(new Error('请输入手机号'));
                }else {
                    this.axios.get("http://localhost:8084/checkPhone", {
                        params:{
                            phone:value
                        }
                    }).then(resp => {
                        if(resp.data === "exist"){
                            this.phoneIsExist = true;
                            callback();
                        }else {
                            this.phoneIsExist = false;
                            callback(new Error('该手机号未注册'));
                        }
                    });
                }
            };

            const validateCode = (rule, value, callback) => {
                if (value === ''||value.trim().length===0) {
                    callback(new Error('请输入验证码'));
                } else {
                    callback();
                }
            };

            return {
                count:'',
                timer: null,
                showCode:true,
                holder3:'请输入手机号',
                holder4:'请输入验证码',
                phoneForm:{
                    phone:'15179510163',
                    code:''
                },
                phoneIsExist: false,
                rules: {
                    phone: [
                        { validator: validatePhone, trigger: 'blur' }
                    ],
                    code: [
                        { validator: validateCode, trigger: 'blur' }
                    ]
                }
            };
        },

        methods: {
            changeCodeStat(){
                const TIME_COUNT = 60;
                if (!this.timer) {
                    this.count = TIME_COUNT;
                    this.showCode = false;
                    this.timer = setInterval(() => {
                        if (this.count > 0 && this.count <= TIME_COUNT) {
                            this.count--;
                        } else {
                            this.show = true;
                            clearInterval(this.timer);
                            this.timer = null;
                        }
                    }, 1000)
                }
            },

            /*发送code*/
            sentCode(){
                const phone = this.phoneForm.phone;
                if(phone===null||phone===''){
                    Toast("请输入手机号！！！")
                }else {
                    if(this.phoneIsExist){
                        this.changeCodeStat();
                        this.axios.get("http://localhost:8084/sendCode",{
                            params:{
                                phone: phone
                            }
                        }).then(resp=>{
                            if(resp.data==="success"){
                                Toast("发送成功")
                            }else {
                                Toast("fail")
                            }
                        })
                    }else {
                        Toast("手机号未注册！！！")
                    }
                }

            },

            /*登录*/
            submitForm: function (formName) {
                const that = this;
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        this.axios.get("http://localhost:8084/riderCodeLogin",{
                            params:{
                                phone: this.phoneForm.phone,
                                code: this.phoneForm.code
                            }
                        }).then(function (resp) {
                            console.log(resp.data);
                            if(resp.data === "fail"){
                                Toast("验证码输入错误！！！");
                            }else {
                                Toast("登陆成功");
                                that.$store.commit("SET_RIDERINFO",resp.data);
                                that.$router.push('/rider/index');
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
