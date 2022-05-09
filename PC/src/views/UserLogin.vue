<template>
  <div>
    <el-page-header @back="goBack" content="Login"></el-page-header>
<div class="login-form">
  <el-tabs type="border-card" class="many_login_tabs">
    <el-tab-pane label="Account login">
      <div class="form">
        <el-form :model="usernameForm" status-icon :rules="rules" ref="usernameForm" label-width="100px" class="demo-ruleForm">
          <el-form-item label="Account" prop="username">
            <el-input  :placeholder="holder1" type="text" v-model="usernameForm.username" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="Password" prop="pass">
            <el-input type="password" :placeholder="holder2" v-model="usernameForm.pass" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="submitForm('usernameForm')">Login</el-button>
            <el-button @click="register()">Register</el-button>
          </el-form-item>
        </el-form>
      </div>
    </el-tab-pane>
    <el-tab-pane label="Store personnel login">
        <div class="form">
            <el-form :model="shopManagerForm" status-icon :rules="Mrules" ref="shopManagerForm" label-width="100px" class="demo-ruleForm">
                <el-form-item label="Account" prop="s_username">
                    <el-input  :placeholder="holder1" type="text" v-model="shopManagerForm.s_username" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="Password" prop="s_password">
                    <el-input type="password" :placeholder="holder2" v-model="shopManagerForm.s_password" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="submitShopManagerForm('shopManagerForm')">Login</el-button>
                    <!-- <el-button style="display: none;" @click="register()">注册</el-button> -->
                </el-form-item>
            </el-form>
        </div>
    </el-tab-pane>
    <!-- <el-tab-pane label="手机号登陆">
      <div class="form">
        <el-form :model="phoneForm" status-icon :rules="rules" ref="phoneForm" label-width="100px" class="demo-ruleForm">
          <el-form-item label="手机号" prop="phone">
            <el-input :placeholder="holder3" type="text" v-model="phoneForm.phone" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="验证码" style="width: 350px;display: inline-block" prop="code">
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
    </el-tab-pane> -->
  </el-tabs>
</div>
  </div>



</template>

<style>


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
<script>
/*// @ is an alias to /src
import HelloWorld from '@/components/HelloWorld.vue'*/


import Toast from "vant/lib/toast";

export default {
  name: 'Home',
    created(){
      console.log(this.$store.getters);
    },
    data() {
      const validateUsername = (rule, value, callback) => {
        if (value === ''||value.trim().length===0) {
          callback(new Error('enter one user name'));
        } else {
          callback();
        }
      };

      const validatePass = (rule, value, callback) => {
        if (value === ''||value.trim().length===0) {
          callback(new Error('Please input a password'));
        } else {
          callback();
        }
      };

      const validatePhone = (rule, value, callback) => {
        if (value === ''||value.trim().length===0) {
          callback(new Error('Please enter your mobile number'));
        } else {
          this.axios.get("http://localhost:8084/findUserByPhone", {
            params:{
              phone:value
            }
          }).then(resp => {
            if(resp.data === "exist"){
              this.phoneIsExist = true;
              callback();
            }else {
              this.phoneIsExist = false;
              callback(new Error('The phone number is not registered'));
            }
          });
        }
      };

      const validateCode = (rule, value, callback) => {
        if (value === ''||value.trim().length===0) {
          callback(new Error('Please enter the verification code'));
        } else {
          callback();
        }
      };
      const validatePassword = (rule, value, callback) => {
        if (value === '') {
            callback(new Error('Please input a password'));
        } else {
            callback();
        }
      };

      return {
        showCode:true,
        holder1:'Please enter the account number',
        holder2:'Please input a password',
        holder3:'Please enter your mobile number',
        holder4:'Please enter the verification code',
        usernameForm: {
          username:'lky',
          pass: '123'
        },
        shopManagerForm: {
            s_username:'lky1',
            s_password: '123'
        },
        phoneForm:{
          phone:'',
          code:''
        },
        rules: {
          username:[
            { validator: validateUsername, trigger: 'blur' }
          ],
          pass: [
            { validator: validatePass, trigger: 'blur' }
          ],
          phone: [
            { validator: validatePhone, trigger: 'blur' }
          ],
          code: [
            { validator: validateCode, trigger: 'blur' }
          ]
        },
        Mrules: {
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
                            Toast("Login successful");
                            that.$store.commit("SET_SHOPMANAGERINFO",resp.data.data.user);
                            localStorage.setItem('Admindata', JSON.stringify(resp.data.data.user))
                            that.$router.push({
                                path:'/manager/index',
                                // query:{
                                //     userInfo:resp.data.data.user
                                // }
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
        if(phone===null||phone===''||phone.trim().length===0){
          Toast("Please enter your mobile number!!!")
        }else {
          if(this.phoneIsExist){
            this.changeCodeStat();
            this.axios.get("http://localhost:8084/sendCodeUser",{
              params:{
                phone: phone
              }
            }).then(resp=>{
              if(resp.data==="success"){
                Toast("Sent successfully")
              }else {
                Toast("Sent fail")
              }
            })
          }else {
            Toast("Mobile phone number is not registered!!!")
          }
        }

      },

      submitForm: function (formName) {
        const that = this;
        if(formName==='usernameForm'){
          this.$refs[formName].validate((valid) => {
            if (valid) {
              this.axios.get("http://localhost:8084/login",{
                params:{
                  username: this.usernameForm.username,
                  password: this.usernameForm.pass
                }
              }).then(function (resp) {
                console.log(resp.data);
                if(resp.data.code === 200){
                  Toast("Login successful");
                  that.$store.commit("SET_USERINFO",resp.data.data.user);
                  that.$router.push('/user/index')
                }else {
                  Toast("Wrong account or password!!!")
                }
              });
              // that.$router.push('/user/index')
            } else {
              console.log('error submit!!');
              return false;
            }
          });
        }else if(formName==='phoneForm'){
          this.$refs[formName].validate((valid) => {
            if (valid) {
              this.axios.get("http://localhost:8084/loginByPhone",{
                params:{
                  phone: this.phoneForm.phone,
                  code: this.phoneForm.code
                }
              }).then(function (resp) {
                console.log(resp.data);
                if(resp.data.code === 200){
                  Toast("Login successful");
                  that.$store.commit("SET_USERINFO",resp.data.data.user);
                  that.$router.push('/user/index')
                }else {
                  Toast("Mobile phone number and verification code do not match!!!")
                }
              });

            } else {
              console.log('error submit!!');
              return false;
            }
          });
        }


      },
      register(){
          this.$router.push("/toUserRegister")
      },
      goBack(){
        this.$router.replace('/')
      }
    }
}
</script>
