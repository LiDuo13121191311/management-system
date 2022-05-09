<template>
    <!--步骤一-->
    <el-main>
        <el-form :model="userForm" status-icon :rules="rules" ref="userForm" label-width="100px">
            <el-form-item label="用户名" prop="username">
                <el-input type="text" v-model="userForm.username" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="密码" prop="password">
                <el-input type="password" v-model="userForm.password" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="确认密码" prop="checkPass">
                <el-input type="password" v-model="userForm.checkPass" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="姓名" prop="name">
                <el-input type="text" v-model="userForm.name" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="身份证号" prop="identityNumber">
                <el-input type="text" v-model.number="userForm.identityNumber"></el-input>
            </el-form-item>
            <el-form-item label="手机号" prop="phone">
                <el-input type="text" v-model="userForm.phone" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item>
                <el-button @click="resetForm('userForm')">重置</el-button>
                <el-button type="primary" @click="submitForm(userForm)">下一步</el-button>
            </el-form-item>
        </el-form>
    </el-main>

</template>

<script>
    import Toast from "vant/lib/toast";

    export default {
        name: "SMRegister",
        data(){
            const validateUsername = (rule, value, callback) => {
                if (value === ''||value === ' ') {
                    callback(new Error('请输入用户名'));
                } else {
                    this.axios.get("http://localhost:8084/findShopManagerByUsername",{
                        params:{
                            username:value
                        }
                    }).then(resp=>{
                        if(resp.data==='none'){
                            callback();
                        }else {
                            callback(new Error('该用户名已被注册'));
                        }
                    })
                }
            };


            const validatePass = (rule, value, callback) => {
                if (value === ''||value === ' ') {
                    callback(new Error('请输入密码'));
                } else {
                    if (this.userForm.checkPass !== '') {
                        this.$refs.userForm.validateField('checkPass');
                    }
                    callback();
                }
            };

            const validatePass2 = (rule, value, callback) => {
                if (value === '') {
                    callback(new Error('请再次输入密码'));
                } else if (value !== this.userForm.password) {
                    callback(new Error('两次输入密码不一致!'));
                } else {
                    callback();
                }
            };

            const validateName = (rule, value, callback) => {
                if (value === '') {
                    callback(new Error('请输入姓名'));
                } else {
                    callback();
                }
            };

            const validateIdentityNumber = (rule, value, callback) => {
                if (value === '') {
                    callback(new Error('请输入身份证号'));
                } else {
                    this.axios.get("http://localhost:8084/findShopManagerByIdentityNumber",{
                        params:{
                            identityNumber:value
                        }
                    }).then(resp=>{
                        if(resp.data==='none'){
                            callback();
                        }else {
                            callback(new Error('该用户已注册'));
                        }
                    })
                }
            };

            const validatePhone = (rule, value, callback) => {
                if (value === ''||value === ' ') {
                    callback(new Error('请输入手机号'));
                } else {
                    this.axios.get("http://localhost:8084/findShopManagerByPhone",{
                        params:{
                            phone:value
                        }
                    }).then(resp=>{
                        if(resp.data==='none'){
                            callback();
                        }else {
                            callback(new Error('该手机号已注册'));
                        }
                    })

                }
            };
            return{
                userForm: {
                    username:'',
                    password: '',
                    checkPass: '',
                    name:'',
                    identityNumber:'',
                    phone:'',
                },
                userInfo:{
                    username:'',
                    password: '',
                    name:'',
                    identityNumber:'',
                    phone:'',
                },
                rules: {
                    username: [
                        { validator:validateUsername,trigger: 'blur'}
                    ],
                    password: [
                        { validator: validatePass, trigger: 'blur' }
                    ],
                    checkPass: [
                        { validator: validatePass2, trigger: 'blur' }
                    ],
                    name: [
                        { validator:validateName,trigger: 'blur'}
                    ],
                    identityNumber: [
                        { validator:validateIdentityNumber,trigger: 'blur'}
                    ],
                    phone: [
                        { validator:validatePhone,trigger: 'blur'}
                    ]
                },
            }
        },
        methods:{
            submitForm(form) {
                const that = this;
                this.userInfo.username = this.userForm.username;
                this.userInfo.password = this.userForm.password;
                this.userInfo.name = this.userForm.name;
                this.userInfo.phone = this.userForm.phone;
                this.userInfo.identityNumber = this.userForm.identityNumber;
                    this.$refs['userForm'].validate((valid) => {
                        if (valid) {
                            this.$emit("func",1);
                            this.$router.push({
                                path:"/shopRegister",
                                query:{
                                    userInfo:this.userInfo
                                }
                            })
                        } else {
                            Toast("请输入个人信息");
                            return false;
                        }
                    });
            },
            resetForm(formName) {
                this.$refs[formName].resetFields();
            },

        }
    }
</script>

<style scoped>

</style>
