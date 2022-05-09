<template>
    <div style="text-align: center;margin: auto;">
        <el-table
                :data="shopInfo"
                style="width:1450px;border-radius: 15px">
            <el-table-column
                    label="店铺名"
                    prop="shopInfo.shopName" >
            </el-table-column>
            <el-table-column
                    label="店铺Logo"
                    prop="shopInfo.logoSrc" >
                <template slot-scope="scope">
                    <el-image :src="scope.row.shopInfo.logoSrc" style="width: 50px;height: 50px"/>
                </template>
            </el-table-column>
            <el-table-column
                    label="营业许可证"
                    prop="shopInfo.license" >
            </el-table-column>
            <el-table-column
                    label="起送费"
                    prop="shopInfo.minCost" >
            </el-table-column>
            <el-table-column
                    label="配送费"
                    prop="shopInfo.deliveryCost" >
            </el-table-column>
            <el-table-column
                    label="配送时间"
                    prop="shopInfo.deliveryTime" >
                <template slot-scope="scope">
                    <span v-if="scope.row.shopInfo.deliveryTime!==null">{{scope.row.shopInfo.deliveryTime}}</span>
                    <span v-if="scope.row.shopInfo.deliveryTime===null">暂无</span>
                </template>
            </el-table-column>
            <el-table-column
                    label="总销量"
                    prop="shopInfo.totalSales" >
            </el-table-column>
            <el-table-column
                    label="店长"
                    prop="userInfo.username" >
            </el-table-column>
            <el-table-column
                    label="联系方式"
                    prop="userInfo.phone" >
            </el-table-column>
            <el-table-column
                    label="店铺地址"
                    prop="shopInfo.addressDetail" >
            </el-table-column>
            <el-table-column
                    label="店铺描述"
                    prop="shopInfo.desc">
                <template slot-scope="scope">
                    <span v-if="scope.row.shopInfo.desc!==null">{{scope.row.shopInfo.desc}}</span>
                    <span v-if="scope.row.shopInfo.desc===null">暂无</span>
                </template>
            </el-table-column>
            <el-table-column
                    label="店铺状态"
                    prop="shopInfo.stat" >
                <template slot-scope="scope">
                    <span v-if="scope.row.shopInfo.stat===1" style="color: green">营业中</span>
                    <span v-if="scope.row.shopInfo.stat===2" style="color: red">冻结中</span>
                </template>
            </el-table-column>
            <el-table-column
                    align="right" >
                <!--<template slot="header" slot-scope="scope">
                    <el-input
                            v-model="search"
                            size="mini"
                            placeholder="输入关键字搜索"/>
                </template>-->
                <template slot-scope="scope" style="width: 120px">
                    <el-button
                            size="mini"
                            @click="edit(scope.row)" style="width: 33px;height:50px;display: inline-block;text-align: center;padding: 2px">修改</el-button>
                    <el-button
                            size="mini"
                            type="danger"
                            v-if="scope.row.shopInfo.stat===1"
                            @click="changeStat(scope.row.shopInfo.sid,scope.row.shopInfo.stat)" style="margin-left: 2px;;width: 33px;height:50px;display: inline-block;padding: 2px">
                        冻结
                    </el-button>
                    <el-button
                            size="mini"
                            type="success"
                            @click="changeStat(scope.row.shopInfo.sid,scope.row.shopInfo.stat)" style="width: 50px;display: inline-block" v-if="scope.row.shopInfo.stat===2">开启</el-button>
                </template>
            </el-table-column>
        </el-table>

        <!--点击修改后的弹窗-->
        <el-dialog title="店铺信息" :visible.sync="dialogVisible" style="line-height: 15px">
            <el-form :model="editShopInfo" status-icon :rules="shopRules" ref="editShopInfo" style="text-align: left">
                <el-form-item label="店铺名" :label-width="formLabelWidth" prop="shopName">
                    <el-input v-model="editShopInfo.shopName" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="店铺Logo" :label-width="formLabelWidth">
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
                        <el-dialog :visible.sync="dialogPicVisible">
                            <img width="100%" :src="dialogImageUrl" alt="">
                        </el-dialog>
                </el-form-item>
                <el-form-item label="营业许可证" :label-width="formLabelWidth" prop="license">
                    <el-input v-model="editShopInfo.license" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="起送费" :label-width="formLabelWidth" prop="minCost">
                    <el-input v-model="editShopInfo.minCost" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="配送费" :label-width="formLabelWidth" prop="deliveryCost">
                    <el-input v-model="editShopInfo.deliveryCost" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="配送时间(分钟)" :label-width="formLabelWidth" prop="deliveryTime">
                    <el-input v-model="editShopInfo.deliveryTime" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="总销量" :label-width="formLabelWidth" prop="totalSales">
                    <el-input v-model="editShopInfo.totalSales" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="联系方式" :label-width="formLabelWidth" prop="phone">
                    <el-input v-model="editShopInfo.phone" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="店铺地址" :label-width="formLabelWidth" prop="addressDetail">
                    <el-input v-model="editShopInfo.addressDetail" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="店铺描述" :label-width="formLabelWidth" prop="desc">
                    <el-input v-model="editShopInfo.desc" autocomplete="off"></el-input>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="flush">取 消</el-button>
                <el-button type="primary" @click="save">保 存</el-button>
            </span>
        </el-dialog>


        <!--分页-->
        <el-pagination
                @size-change="handleSizeChange"
                @current-change="handleCurrentChange"
                :current-page="currentPage"
                :page-sizes="pageSizes"
                :page-size="pageSize"
                layout="total, sizes, prev, pager, next, jumper"
                :total="total">
        </el-pagination>
    </div>
</template>

<script>
    import Toast from "vant/lib/toast";

    export default {
        name: "AllShops",
        created() {
            //this.userInfo = this.$route.params.userInfo;
            this.axios.get("http://localhost:8084/findAllShopInfo",{
                params:{
                    currentPage:this.currentPage,
                    pageSize:this.pageSize
                }
            }).then(resp=>{
                console.log(resp.data);
                this.shopInfo = resp.data.shopManagerRegisterVOS;
                this.total = resp.data.count;
            })
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
                    if(value!==this.licenseCount){
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

            const validateDesc = (rule, value, callback) => {
                if (value === ''||value === null) {
                    callback(new Error('请输入店铺描述'));
                } else {
                    callback();
                }
            };

            return{
                dialogPicVisible:false,

                //文件上传的参数
                dialogImageUrl: '',
                dialogVisible: false,
                fileList:[],
                picture:'',
                fileLimit:1,

                formLabelWidth: '120px',
                form:'',
                userInfo:'',
                search:'',

                licenseCount:'',
                shopInfo:[
                    {
                        shopInfo:{
                            shopName:'123'
                        },
                        shopManagerInfo:''
                    }
                ],
                editShopInfo:{
                    shopName:'',
                    license:'',
                    deliveryCost:'',
                    deliveryTime:'',
                    minCost:'',
                    totalSales:'',
                    phone:'',
                    addressDetail:'',
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
                },

                pageSize:5,
                total: 0,
                currentPage: 1,
                pageSizes:[1,2,3,4,5,6,7,8,9,10],
            }
        },
        methods:{
            /*改变店铺状态*/
            changeStat(sid,stat){
                console.log("sid:"+sid+",stat:"+stat);
                console.log(stat===1);
                if(stat === 1){
                    this.$confirm("确定要冻结这个店铺吗？",'提示',{
                        confirmButtonText: '确定',
                        cancelButtonText: '取消',
                        type:'info'
                    }).then(()=>{
                            this.axios.get("http://localhost:8084/changeShopStat",{
                                params:{
                                    sid:sid,
                                    stat:'2'
                                }
                            }).then(resp=>{
                                if(resp.data === "success"){
                                    this.$message({
                                        message:"冻结成功！！！",
                                        type:'success'
                                    });
                                    this.flush();
                                }
                            })
                    })
                }else if(stat === 2){
                        this.$confirm("确定要开启这个店铺吗？",'提示',{
                            confirmButtonText: '确定',
                            cancelButtonText: '取消',
                            type:'info'
                        }).then(()=>{
                                this.axios.get("http://localhost:8084/changeShopStat",{
                                    params:{
                                        sid:sid,
                                        stat:'1'
                                    }
                                }).then(resp=>{
                                    if(resp.data === "success"){
                                        this.$message({
                                            message:"开启成功！！！",
                                            type:'success'
                                        });
                                        this.flush();
                                    }
                                })
                        })
                    }
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


            /*取消保存*/
            flush(){
              this.dialogVisible = false;
              this.$router.push({
                  name:"blank",
                  params:{
                      url:"allShops"
                  }
              })
            },

         /*保存修改*/
            save() {
                console.log(this.editShopInfo);
                this.$refs['editShopInfo'].validate((valid) => {
                    if (valid) {
                        this.$confirm("确定要保存修改吗？", '提示', {
                            confirmButtonText: '确定',
                            cancelButtonText: '取消',
                            type: 'info'
                        }).then(() => {
                            this.axios.post("http://localhost:8084/updateShopMessage", this.editShopInfo).then(resp => {
                                if (resp.data === "success") {
                                    this.$message({
                                        message: "保存成功！！！",
                                        type: 'success'
                                    });
                                    this.flush();
                                } else {
                                    this.$message({
                                        message: "保存失败！！！",
                                        type: 'warning'
                                    });
                                    this.flush();
                                }
                            })
                        }).catch(() => {
                            this.$message({
                                type: 'info',
                                message: '已取消'
                            });
                        })
                    } else {
                        return false;
                    }
                })
            },

           /*修改店铺数据*/
            edit(item){
                console.log(item);
                this.licenseCount = item.shopInfo.license;
                this.dialogVisible = true;
                this.editShopInfo = item.shopInfo;
            },

            /*改变页数*/
            handleSizeChange(val) {
                this.pageSize = val;
                const that = this;
                this.axios.get("http://localhost:8084/findAllShopInfo",{
                    params:{
                        currentPage:this.currentPage,
                        pageSize:this.pageSize
                    }
                }).then(resp=>{
                    console.log(resp.data);
                    this.shopInfo = resp.data.shopManagerRegisterVOS;
                    this.total = resp.data.count;
                })
            },

            /*改变当前页*/
            handleCurrentChange(val) {
                this.currentPage = val;
                const that = this;
                this.axios.get("http://localhost:8084/findAllShopInfo",{
                    params:{
                        currentPage:this.currentPage,
                        pageSize:this.pageSize
                    }
                }).then(resp=>{
                    console.log(resp.data);
                    this.shopInfo = resp.data.shopManagerRegisterVOS;
                    this.total = resp.data.count;
                })
            }
        }
    }
</script>

<style scoped>
    .el-table{
        line-height: 50px;
        margin: auto;
    }
    .el-table-column{
        width: 50px;
    }
    .cell{
        width: 50px;
    }
</style>
