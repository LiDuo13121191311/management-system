<template>
    <!--步骤二-->
    <el-main>
        <el-form :model="shopForm" status-icon :rules="shopRules" ref="shopForm" label-width="100px">
            <el-form-item label="店铺名:" prop="shopName">
                <el-input type="text" v-model="shopForm.shopName"></el-input>
            </el-form-item>
            <el-form-item label="店铺Logo" style="text-align: left">
                <el-upload
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
                </el-dialog>
            </el-form-item>
            <el-form-item label="营业许可证:" prop="license">
                <el-input type="text" v-model="shopForm.license" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="配送费$:" prop="deliveryCost">
                <el-input type="text" v-model="shopForm.deliveryCost" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="起送费$:" prop="minCost">
                <el-input type="text" v-model.number="shopForm.minCost"></el-input>
            </el-form-item>
            <el-form-item label="联系电话:" prop="phone">
                <el-input type="text" v-model="shopForm.phone" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="店铺详细地址:" prop="addressDetail">
                <el-input type="text" v-model="shopForm.addressDetail" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item>
                <el-button @click="resetForm('shopForm')">重置</el-button>
                <el-button type="primary" @click="submitForm(shopForm)">下一步</el-button>
            </el-form-item>
        </el-form>
    </el-main>
</template>

<script>
    import Toast from "vant/lib/toast";

    export default {
        name: "SRegister",
        created(){
            this.registerInfo.userInfo = this.$route.query.userInfo;
        },
        data(){
            /*店铺信息非空判断和唯一验证*/
            const validateShopName = (rule, value, callback) => {
                if (value === '') {
                    callback(new Error('请输入店铺名'));
                } else {
                    callback();
                }
            };


            const validateLicense = (rule, value, callback) => {
                if (value === '') {
                    callback(new Error('请输入营业许可证'));
                } else {
                    this.axios.get("http://localhost:8084/findShopByLicense",{
                        params:{
                            license:value
                        }
                    }).then(resp=>{
                        if(resp.data==='none'){
                            callback();
                        }else {
                            callback(new Error('该营业许可证已被使用'));
                        }
                    })
                }
            };

            const validateDeliveryCost = (rule, value, callback) => {
                if (value === '') {
                    callback(new Error('请输入配送费'));
                } else {
                    callback();
                }
            };

            const validateMinCost = (rule, value, callback) => {
                if (value === '') {
                    callback(new Error('请输入起送价格'));
                } else {
                    callback();
                }
            };

            const validateShopPhone = (rule, value, callback) => {
                if (value === '') {
                    callback(new Error('请输入店铺联系电话'));
                } else {
                    callback();
                }
            };

            const validateAddressDetail = (rule, value, callback) => {
                if (value === '') {
                    callback(new Error('请输入店铺详细地址'));
                } else {
                    callback();
                }
            };



            return{
                //文件上传的参数
                dialogImageUrl: '',
                dialogVisible: false,
                //图片列表（用于在上传组件中回显图片）
                /*fileList: [{name: '', url: ''}],*/
                fileList:[],
                picture:'',
                fileLimit:1,

                registerInfo:{
                    userInfo:null,
                    shopInfo:null
                },
                shopForm: {
                    shopName:'',
                    logoSrc: '',
                    license: '',
                    deliveryCost:'',
                    minCost:'',
                    phone:'',
                    addressDetail:''
                },

                shopRules:{
                    shopName:[
                        { validator:validateShopName,trigger: 'blur'}
                    ],
                    license: [
                        { validator:validateLicense,trigger: 'blur'}
                    ],
                    deliveryCost:[
                        { validator:validateDeliveryCost,trigger: 'blur'}
                    ],
                    minCost:[
                        { validator:validateMinCost,trigger: 'blur'}
                    ],
                    phone:[
                        { validator:validateShopPhone,trigger: 'blur'}
                    ],
                    addressDetail:[
                        { validator:validateAddressDetail,trigger: 'blur'}
                    ]
                }
            }
        },
        methods:{
            //图片预览
            handlePreview(file) {
                this.dialogImageUrl = file.url;
                this.dialogVisible = true;
            },

            //文件上传成功的钩子函数
            handleSuccess(res, file) {
                this.$message({
                    type: 'success',
                    message: '图片上传成功',
                    duration: 6000
                });
                console.log(file);
                if (file.response.code===200) {
                    this.shopForm.logoSrc = file.response.message; //将返回的文件储存路径赋值picture字段
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

            submitForm(form) {
                const that = this;
                    this.$refs['shopForm'].validate((valid) => {
                        if (valid) {
                            this.registerInfo.shopInfo = form;
                            this.$emit("registerInfo",this.registerInfo);
                            this.$emit("func",2);
                        } else {
                            Toast("请输入店铺信息");
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
