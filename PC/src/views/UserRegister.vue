<template>
    <el-container>
        <el-header height="80px">
            <el-page-header @back="goBack" content="User registration"></el-page-header>
        </el-header>
        <el-main>
            <el-form :model="userForm" status-icon :rules="rules" ref="userForm" label-width="100px" class="demo-ruleForm UserRegister_from">
                <el-form-item label="Username" prop="username">
                    <el-input type="text" v-model="userForm.username" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="Avatar" style="text-align: left">
                    <!-- <el-upload
                            ref="upload"
                            action="http://localhost:8084/upload"
                            name="picture"
                            list-type="picture-card"
                            :limit="1"
                            :file-list="fileList"
                            :on-exceed="onExceed"
                            :before-upload="beforeUpload"
                            :before-remove="beforeRemove"
                            :on-preview="handlePreview"
                            :on-success="handleSuccess"
                            :on-remove="handleRemove">
                        <i class="el-icon-plus"></i>
                    </el-upload>
                    <el-dialog :visible.sync="dialogVisible">
                        <img width="100%" :src="dialogImageUrl" alt="">
                    </el-dialog> -->
                    <el-avatar @click.native="Upstart()" shape="square" :size="150" :src="dialogImageUrl"></el-avatar>
                </el-form-item>
                <el-form-item label="Password" prop="password">
                    <el-input type="password" v-model="userForm.password" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="Confirm password" prop="checkPass">
                    <el-input type="password" v-model="userForm.checkPass" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="Name" prop="name">
                    <el-input type="text" v-model="userForm.name" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="Cell-phone number" prop="phone">
                    <el-input type="text" v-model="userForm.phone" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button @click="resetForm('userForm')">Reset</el-button>
                    <el-button type="primary" @click="submitForm(userForm)">Register</el-button>
                </el-form-item>
            </el-form>
        </el-main>
        <!--<el-footer></el-footer>-->
    </el-container>

</template>

<script>
    import Toast from "vant/lib/toast";

    export default {
        name: "UserRegister",
        data() {
            const validateUsername = (rule, value, callback) => {
                if (value === '') {
                    callback(new Error('enter one user name'));
                } else {
                    this.axios.get("http://localhost:8084/findUserByUsername",{
                        params:{
                            username:value
                        }
                    }).then(resp=>{
                        if(resp.data==='none'){
                            callback();
                        }else {
                            callback(new Error('The user name is already registered'));
                        }
                    })
                }
            };


            const validatePass = (rule, value, callback) => {
                if (value === ''||value === ' ') {
                    callback(new Error('Please input a password'));
                } else {
                    if (this.userForm.checkPass !== '') {
                        this.$refs.userForm.validateField('checkPass');
                    }
                    callback();
                }
            };

            const validatePass2 = (rule, value, callback) => {
                if (value === '') {
                    callback(new Error('Please enter the password again'));
                } else if (value !== this.userForm.password) {
                    callback(new Error('The two passwords are inconsistent!'));
                } else {
                    callback();
                }
            };

            const validateName = (rule, value, callback) => {
                if (value === '') {
                    callback(new Error('Please enter your name'));
                } else {
                    callback();
                }
            };

            const validatePhone = (rule, value, callback) => {
                if (value === ''||value === ' ') {
                    callback(new Error('Please enter your mobile number'));
                } else {
                    this.axios.get("http://localhost:8084/findUserByPhone",{
                        params:{
                            phone:value
                        }
                    }).then(resp=>{
                        if(resp.data==='none'){
                            callback();
                        }else {
                            callback(new Error('This phone number is already registered'));
                        }
                    })

                }
            };



            return {
                //文件上传的参数
                dialogImageUrl: '',
                dialogVisible: false,
                fileList:[],
                picture:'',
                fileLimit:1,


                userForm: {
                    username:'',
                    picSrc:'',
                    password: '',
                    checkPass: '',
                    name:'',
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
                    phone: [
                        { validator:validatePhone,trigger: 'blur'}
                    ],

                }
            };
        },
        methods: {
            // 上传图片
            Upstart() {
            //创建input
            const upload = document.createElement("input");
            //设置type为file
            upload.type = "file";
            //类型
            upload.accept = "image/png, image/jpeg";
            //添加onchange事件
            upload.onchange = this.setFile;
            //模拟点击
            upload.click();
            },
            setFile(e) {
            //获取文件
            const file = e.path[0].files[0];
            //将其放入formdata中方便上传
            const formData = new FormData();
            formData.append("imgFile", file);
            const ImgUrl = window.URL.createObjectURL(file);
            //构建Image对象方便获取其宽高
            const img = new Image();
            img.src = ImgUrl;
            let data = new FormData()
            data.append('photo', file)
            this.axios.post("http://localhost:8084/upload_photo",data,{
                headers: { "Content-Type": "multipart/form-data" }
            }).then(resp=>{
                console.log("上传后");
                console.log(resp.data);
                this.fileimgname = resp.data.filename
                this.dialogImageUrl = ImgUrl;
                // this.user.picSrc = resp.data.message
            })
            },
            submitForm(form) {
                const that = this;
                this.$refs['userForm'].validate((valid) => {
                    if (valid) {
                        console.log(form);
                        this.$confirm("Are you sure the information is correct?",'Registration tips',{
                            confirmButtonText: 'Determine',
                            cancelButtonText: 'Cancel',
                            type:'info'
                        }).then(()=> {
                            this.axios.post("http://localhost:8084/userRegister", form)
                                .then(resp => {
                                    if (resp.data === "success") {
                                        this.$message({
                                            message: 'Registration succeeded!!!',
                                            type: 'success'
                                        });
                                        this.$router.push("/toUserLogin")
                                    } else {
                                        this.$message({
                                            message: 'Registration failed!!!',
                                            type: 'error'
                                        });
                                    }
                                })
                        })
                    }
                });
            },


            /*文件上传相关*/
            //文件上传成功的钩子函数
            handleSuccess(res, file) {
                this.$message({
                    type: 'info',
                    message: '图片上传成功',
                    duration: 6000
                });
                console.log(file);
                if (file.response.code===200) {
                    this.userForm.picSrc = file.response.message; //将返回的文件储存路径赋值picture字段
                }
            },

            //删除文件之前的钩子函数
            handleRemove(file, fileList) {
                this.$message({
                    type: 'info',
                    message: '已删除原有图片',
                    duration: 6000
                });
            },

            //点击列表中已上传的文件事的钩子函数
            handlePreview(file) {
            },

            //上传的文件个数超出设定时触发的函数
            onExceed(files, fileList) {
                this.$message({
                    type: 'info',
                    message: '最多只能上传一个图片',
                    duration: 6000
                });
            },

            //文件上传前的前的钩子函数
            //参数是上传的文件，若返回false，或返回Primary且被reject，则停止上传
            beforeUpload(file){
                const isJPG = file.type === 'image/jpeg';
                const isGIF = file.type === 'image/gif';
                const isPNG = file.type === 'image/png';
                const isBMP = file.type === 'image/bmp';
                const isLt2M = file.size / 1024 / 1024 < 2;

                if (!isJPG && !isGIF && !isPNG && !isBMP) {
                    this.$message.error('上传图片必须是JPG/GIF/PNG/BMP 格式!');
                }
                if (!isLt2M) {
                    this.$message.error('上传图片大小不能超过 2MB!');
                }
            },

            //删除前提示
            beforeRemove(file) {
                return this.$confirm(`确定移除 ${ file.name }？`);
            },

            resetForm(formName) {
                this.$refs[formName].resetFields();
            },
            goBack() {
                this.$router.go('-1')
            }
        }
    }
</script>

<style scoped>
    /* .el-form{
        width: 500px;
        height: 200px;
        margin: 20px auto;
    } */

    /* .el-container{
        height: 680px;
    } */
    .el-main {
        /* background-color: #E9EEF3; */
        color: #333;
        text-align: center;
        /* line-height: 160px; */

    }

    .el-header {
        background-color: #0e95e5;
        color: #333;
        text-align: center;
    }

    /* .el-footer{
        background-color: #B3C0D1;
        color: #333;
    } */

</style>
