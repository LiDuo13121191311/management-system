<template>
<div>
    <el-main style="padding: 0;">
        <el-form :model="shopForm" status-icon :rules="shopRules" ref="shopForm" label-width="120px" class="mobile_form" style="margin: auto;padding: 5px;">
            <el-form-item label="Shop name:" prop="shopName">
                <el-input type="text" :disabled="editDisabled" v-model="shopForm.shopName"></el-input>
            </el-form-item>
            <!-- <el-form-item label="Store logo" style="text-align: left">
                <img width="200px;" height="200px" :src="shopForm.logoSrc" alt="" v-if="editDisabled">
                <template v-if="!editDisabled">
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
                </template>
            </el-form-item> -->
            <!-- <el-form-item label="Store logo:">
                <img width="200px;" height="200px" :src="shopForm.logoSrc" alt="" v-if="editDisabled">
                <el-avatar class="Logo_logo" v-else @click.native="Upstart()" shape="square" :src="dialogImageUrl !=='' ? '' : shopForm.logoSrc"></el-avatar>
            </el-form-item> -->
            <el-form-item label="Business license:" prop="license">
                <el-input type="text" :disabled="editDisabled" v-model="shopForm.license" autocomplete="off"></el-input>
            </el-form-item>
            <!-- <el-form-item label="配送费$:" prop="deliveryCost">
                <el-input type="text" :disabled="editDisabled" v-model="shopForm.deliveryCost" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="预估配送时间(分钟):" prop="deliveryTime">
                <el-input type="text" :disabled="editDisabled" v-model="shopForm.deliveryTime" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="起送费$:" prop="minCost">
                <el-input type="text" :disabled="editDisabled" v-model.number="shopForm.minCost"></el-input>
            </el-form-item> -->
            <el-form-item label="Contact number:" prop="phone">
                <el-input type="text" :disabled="editDisabled" v-model="shopForm.phone" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="Shop detailed address:" prop="addressDetail">
                <el-input type="text" :disabled="editDisabled" v-model="shopForm.addressDetail" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="Shop description:" prop="desc">
                <el-input type="text" autosize :disabled="editDisabled" v-model="shopForm.desc" autocomplete="off" maxlength="12"
                          show-word-limit></el-input>
            </el-form-item>
            <el-form-item v-if="editDisabled">
                <el-button type="primary" @click="toEditShopForm">Modify store information</el-button>
            </el-form-item>
            <template v-if="!editDisabled">
                <el-form-item>
                    <el-button type="primary" @click="save">Save</el-button>
                    <el-button type="primary" @click="flush">Cancel</el-button>
                </el-form-item>
            </template>

        </el-form>
    </el-main>
    <!--<el-form :label-position="top" label-width="80px" :model="shopMessage">
        <el-form-item label="店铺名称">
            <el-input v-model="shopMessage.shopName"></el-input>
        </el-form-item>
        <el-form-item label="图片">
            <el-input v-model="shopMessage.logoSrc"></el-input>
        </el-form-item>

        <el-form-item label="证件">
            <el-input v-model="shopMessage.license"></el-input>
        </el-form-item>
    </el-form>-->
</div>
</template>

<script>
    import Toast from "vant/lib/toast";

    export default {
        name: "ShopMessage",
        created() {
            const userInfo = JSON.parse(localStorage.getItem('shopManagerInfo'))
            const uid = userInfo.smid;
            console.log(uid);
            this.axios.get("http://localhost:8084/findShopByUid",{
                params:{
                    uid:uid
                }
            }).then(resp=>{
                console.log(resp.data);
                this.shopForm = resp.data;
                this.licenseCount = resp.data.license;
            })


        },
        data(){

            /*店铺信息非空判断和唯一验证*/
            const validateShopName = (rule, value, callback) => {
                if (value === '') {
                    callback(new Error('Please enter the store name'));
                } else {
                    callback();
                }
            };


            const validateLicense = (rule, value, callback) => {
                if (value === '') {
                    callback(new Error('Please enter the business license'));
                } else {
                    if(value!==this.licenseCount){
                        this.axios.get("http://localhost:8084/findShopByLicense",{
                            params:{
                                license:value
                            }
                        }).then(resp=>{
                            if(resp.data==='none'){
                                callback();
                            }else {
                                callback(new Error('The business license has been used'));
                            }
                        })
                    }else {
                        callback();
                    }
                }
            };

            const validateDeliveryCost = (rule, value, callback) => {
                if (value === '') {
                    callback(new Error('请输入配送费'));
                } else {
                    callback();
                }
            };

            const validateDeliveryTime = (rule, value, callback) => {
                if (value === ''||value === null) {
                    callback(new Error('请输入预估配送时间'));
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
                    callback(new Error('Please enter the store contact number'));
                } else {
                    callback();
                }
            };

            const validateAddressDetail = (rule, value, callback) => {
                if (value === '') {
                    callback(new Error('Please enter the detailed address of the store'));
                } else {
                    callback();
                }
            };

            const validateDesc = (rule, value, callback) => {
                if (value === ''||value === null) {
                    callback(new Error('Please enter the store description'));
                } else {
                    callback();
                }
            };

            return{
                editDisabled:true,


                shopForm: {
                    shopName:'',
                    logoSrc: '',
                    license: '',
                    deliveryCost:'',
                    deliveryTime:null,
                    minCost:'',
                    phone:'',
                    addressDetail:'',
                    desc:null
                },


                //文件上传的参数
                dialogImageUrl: '',
                dialogVisible: false,
                fileList:[],
                picture:'',
                fileLimit:1,

                licenseCount:'',
                shopMessage:{
                    sid:'',
                    logoSrc:'',
                    shopName:'',
                    license:'',
                    stat:'',
                    deliveryCost:'',
                    deliveryTime:'',
                    minCost:'',
                    totalSales:'',
                    phone:'',
                    desc:''
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
                    deliveryTime:[
                        { validator:validateDeliveryTime,trigger: 'blur'}
                    ],
                    minCost:[
                        { validator:validateMinCost,trigger: 'blur'}
                    ],
                    phone:[
                        { validator:validateShopPhone,trigger: 'blur'}
                    ],
                    addressDetail:[
                        { validator:validateAddressDetail,trigger: 'blur'}
                    ],
                    desc:[
                        { validator:validateDesc,trigger: 'blur'}
                    ]
                }
            }
        },
        methods:{
            // 图片转换
            Phototimg(src) {
            const a = 'http://localhost:8084/' + src
            console.log(a)
            return a
            },
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
            /*点击修改按钮*/
            toEditShopForm(){
                this.editDisabled = false;
                console.log("修改")
            },

            //保存
            save(){
                this.$refs['shopForm'].validate((valid) => {
                    if (valid) {
                        this.axios.post("http://localhost:8084/updateShopMessage",this.shopForm)
                            .then(resp=>{
                                if(resp.data === "success"){
                                    console.log("Modified successfully");
                                    this.$message({
                                        type: 'success',
                                        message: 'Modified successfully',
                                        duration: 6000
                                    });
                                    //刷新
                                    this.flush();
                                }

                            })
                    } else {
                        Toast("Please enter store information");
                        return false;
                    }
                });
            },

            //文件上传成功的钩子函数
            handleSuccess(res, file) {
                this.$message({
                    type: 'info',
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
            //图片预览
            handlePreview(file) {
                this.dialogImageUrl = file.url;
                this.dialogVisible = true;
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

            //取消
            flush(){
                this.editDisabled = true;
                this.$router.push({
                    name:"blank",
                    params:{
                        url:"shopMessage"
                    }
                })
            }
        }
    }
</script>

<style lang="scss" scoped>

</style>
