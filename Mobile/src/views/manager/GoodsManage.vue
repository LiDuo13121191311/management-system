<template>
    <div style="text-align: center;margin: auto">
        <div style="height: 50px;">
            <el-button type="primary"
                       @click="add" style="float: left">Add</el-button>
        </div>
        <el-table size="mini"
                :data="goods"
        style="width:100%;border-radius: 4px">
            <el-table-column
                    label="Trade name"
                    prop="goodName" >
            </el-table-column>
            <el-table-column
                    label="Picture"
                    prop="goodPic" >
                <template slot-scope="scope">
                    <el-image :src="Phototimg(scope.row.goodPic)" style="width: 100px"/>
                </template>
            </el-table-column>
            <el-table-column
                    label="Price"
                    prop="price" >
            </el-table-column>
            <el-table-column
                    label="Category"
                    prop="category" >
            </el-table-column>
            <el-table-column
                    label="Sales"
                    prop="sales" >
            </el-table-column>
            <el-table-column
                    label="Desc"
                    prop="desc" >
            </el-table-column>
            <el-table-column label="Operation" width="160" >
                <template slot-scope="scope">
                    <el-button size="mini" @click="edit(scope.row)">Edit</el-button>
                    <el-button size="mini" type="danger" @click="del(scope.row)">Delete</el-button>
                </template>
            </el-table-column>
        </el-table>

        <!--点击新增后的弹窗-->
        <el-dialog class="Mobile_dialog" title="Commodity information" :visible.sync="addDialogVisible" width="90%">
            <el-form class="mobile_form" :model="newGoodInfo" status-icon :rules="newGoodRules" ref="newGoodInfo" style="text-align: left">
                <el-form-item label="Trade name" :label-width="formLabelWidth" prop="goodName">
                    <el-input v-model="newGoodInfo.goodName" autocomplete="off"  placeholder="Please enter the product name"></el-input>
                </el-form-item>
                <el-form-item label="Picture" :label-width="formLabelWidth">
                    <el-avatar @click.native="Upstart()" shape="square" :size="150" :src="dialogImageUrl"></el-avatar>
                </el-form-item>
                <el-form-item label="Price" :label-width="formLabelWidth" prop="price">
                    <el-input v-model="newGoodInfo.price" autocomplete="off" placeholder="Please enter the commodity price"></el-input>
                </el-form-item>
                <el-form-item label="Category" :label-width="formLabelWidth" prop="category">
                    <el-select v-model="newGoodInfo.cid" placeholder="Please select product category">
                            <el-option
                                v-for="item in categories"
                                :key="item.cid"
                                :label="item.categoryName"
                                :value="item.cid">
                            </el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="Desc" :label-width="formLabelWidth" prop="desc">
                    <el-input v-model="newGoodInfo.desc" autocomplete="off" placeholder="Please enter product description"  clearable maxlength="10"
                              show-word-limit></el-input>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="flush">Cancel</el-button>
                <el-button type="primary" @click="addSave">Save</el-button>
            </span>
        </el-dialog>

        <!--点击修改后的弹窗-->
        <el-dialog class="Mobile_dialog" title="Commodity information" :visible.sync="editDialogVisible" width="90%">
            <el-form class="mobile_form" :model="goodInfo" status-icon :rules="goodRules" ref="goodInfo" style="text-align: left">
                <el-form-item label="Trade name" :label-width="formLabelWidth" prop="goodName">
                    <el-input v-model="goodInfo.goodName" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="Picture" :label-width="formLabelWidth">
                    <el-avatar @click.native="Upstart()" shape="square" :size="150" :src="dialogImageUrl !=='' ? dialogImageUrl : Phototimg(goodInfo.goodPic)"></el-avatar>
                </el-form-item>
                <el-form-item label="Price" :label-width="formLabelWidth" prop="price">
                    <el-input v-model="goodInfo.price" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="Category" :label-width="formLabelWidth" prop="category">
                    <el-select v-model="goodInfo.cid" placeholder="Please select product category">
                            <el-option
                                v-for="item in categories"
                                :key="item.cid"
                                :label="item.categoryName"
                                :value="item.cid">
                            </el-option>
                    </el-select>
                </el-form-item>
                <!--<el-form-item label="销量" :label-width="formLabelWidth" prop="deliveryCost">
                    <el-input v-model="sales" autocomplete="off"></el-input>
                </el-form-item>-->
                <el-form-item label="Desc" :label-width="formLabelWidth" prop="desc">
                    <el-input v-model="goodInfo.desc" autocomplete="off"  clearable></el-input>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="flush">Cancel</el-button>
                <el-button type="primary" @click="save">Save</el-button>
            </span>
        </el-dialog>





        <!--分页-->
        <el-pagination
                @size-change="handleSizeChange"
                @current-change="handleCurrentChange"
                :current-page="currentPage"
                :page-sizes="pageSizes"
                :page-size="pageSize"
                layout=" sizes, prev, pager, next, jumper"
                :total="total">
        </el-pagination>
    </div>

</template>

<script>
    export default {
        name: "GoodsManage",
        created(){
            const data = JSON.parse(localStorage.getItem("shopManagerInfo"))
            this.sid = JSON.parse(localStorage.getItem("sid"))
            const pageSize = this.pageSize;
            const currentPage = this.currentPage;
            this.axios.get("http://localhost:8084/findGoodsAndCategoryByPage",{
                params:{
                    // sid: this.sid,
                    uid: data.smid,
                    pageSize: pageSize,
                    currentPage: currentPage
                }
            }).then(resp=>{
                this.total = resp.data.totalCount;
                console.log(resp.data);
                this.goods = resp.data.goods;
            })
        },
        methods: {
            // 图片转换
            Phototimg(src) {
            const a = 'http://localhost:8084/' + src
            console.log(a)
            return a
            },
            /*保存添加的数据*/
            addSave(){
                this.newGoodInfo.sid = this.sid;
                this.newGoodInfo.goodPic = this.fileimgname
                this.$refs['newGoodInfo'].validate((valid) => {
                    if (valid) {
                        this.axios.post("http://localhost:8084/addGoodMessage", this.newGoodInfo).then(resp => {
                            if (resp.data === "success") {
                                this.$message({
                                    message: "Added successfully！！！",
                                    type: 'success'
                                });
                                this.flush();
                            } else {
                                this.$message({
                                    message: "Add failed！！！",
                                    type: 'warning'
                                });
                                this.flush();
                            }
                        })
                    }
                })
            },

            /*点击新增按钮*/
            add(){
                this.addDialogVisible = true;
                this.getAllCategories(null);
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
            edit(row) {
                this.dialogImageUrl = ''
                this.editDialogVisible = true;
                console.log(row);
                this.getAllCategories(row);
            },

            del(row){
                this.$confirm('Delete this item, continue?', 'Tips', {
                    confirmButtonText: 'Determine',
                    cancelButtonText: 'Cancel',
                    type: 'warning'
                }).then(() => {
                    this.axios.get("http://localhost:8084/deleteGood", { 
                        params: {  
                            gid: row.gid
                        }
                    }).then(resp => {
                        if (resp.data === "success") {
                            this.$message({
                                message: "Deleted successfully！！！",
                                type: 'success'
                            });
                            this.flush();
                        } else {
                            this.$message({
                                message: "Delete failed！！！",
                                type: 'warning'
                            });
                            this.flush();
                        }
                    })
                })
            },

            //获取所有类别
            getAllCategories(row){
                if(row!==null) {
                    this.goodInfo = row;
                }else {
                    this.newGoodInfo = {};
                }
                this.axios.get("http://localhost:8084/getAllCategoryBySid",{
                    params:{
                        sid:this.sid
                    }
                }).then(resp=>{
                    console.log(resp.data);
                    this.categories = resp.data;
                })
            },

            /*取消保存*/
            flush(){
                this.dialogVisible = false;
                this.$router.push({
                    name:"blank",
                    params:{
                        url:"goodsManage"
                    }
                })
            },

            /*保存修改*/
            save() {
                this.goodInfo.goodPic = this.fileimgname
                this.$refs['goodInfo'].validate((valid) => {
                    if (valid) {
                        this.axios.post("http://localhost:8084/updateGoodMessage", this.goodInfo).then(resp => {
                            if (resp.data === "success") {
                                this.$message({
                                    message: "Saved successfully！！！",
                                    type: 'success'
                                });
                                this.flush();
                            } else {
                                this.$message({
                                    message: "Save failed！！！",
                                    type: 'warning'
                                });
                                this.flush();
                            }
                        })
                    }
                })
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
                    this.goodInfo.goodPic = file.response.message; //将返回的文件储存路径赋值picture字段
                }
            },

            handleSuccessAdd(res, file) {
                this.$message({
                    type: 'info',
                    message: '图片上传成功',
                    duration: 6000
                });
                console.log(file);
                if (file.response.code===200) {
                    this.newGoodInfo.goodPic = file.response.message; //将返回的文件储存路径赋值picture字段
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

            /*分页相关*/
            handleSizeChange(val) {
                this.pageSize = val;
                const data = JSON.parse(localStorage.getItem("shopManagerInfo"))
                this.axios.get("http://localhost:8084/findGoodsAndCategoryByPage",{
                    params:{
                        uid:data.smid,
                        pageSize: this.pageSize,
                        currentPage: this.currentPage
                    }
                }).then(resp=>{
                    this.total = resp.data.totalCount;
                    console.log(resp.data);
                    this.goods = resp.data.goods;
                });
            },
            handleCurrentChange(val) {
                this.currentPage = val;
                const data = JSON.parse(localStorage.getItem("shopManagerInfo"))
                this.axios.get("http://localhost:8084/findGoodsAndCategoryByPage",{
                    params:{
                        uid:data.smid,
                        pageSize: this.pageSize,
                        currentPage: this.currentPage
                    }
                }).then(resp=>{
                    this.total = resp.data.totalCount;
                    console.log(resp.data);
                    this.goods = resp.data.goods;
                });
            }
        },

        data() {

            /*商品信息非空判断和唯一验证*/
            const validateGoodName = (rule, value, callback) => {
                if (value === ''||value==null) {
                    callback(new Error('Please enter the trade name'));
                } else {
                    if(value!==this.goodInfo.goodName){
                        this.axios.get("http://localhost:8084/checkGoodName",{
                            params:{
                                goodName:value
                            }
                        }).then(resp=>{
                            if(resp.data==='none'){
                                callback();
                            }else {
                                callback(new Error('The product name already exists!!'));
                            }
                        })
                    }else {
                        callback();
                    }
                }
            };

            const validateNewGoodName = (rule, value, callback) => {
                if (value === ''||value==null) {
                    callback(new Error('Please enter the trade name'));
                } else {
                    if(value!==this.newGoodInfo.goodName){
                        this.axios.get("http://localhost:8084/checkGoodName",{
                            params:{
                                goodName:value
                            }
                        }).then(resp=>{
                            if(resp.data==='none'){
                                callback();
                            }else {
                                callback(new Error('The product name already exists!!'));
                            }
                        })
                    }else {
                        callback();
                    }
                }
            };


            /*const validateCategoryName = (rule, value, callback) => {
                if (value === ''||value==null) {
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
            };*/

            const validatePrice = (rule, value, callback) => {
                if (value === ''||value==null) {
                    callback(new Error('Please enter price'));
                } else {
                    callback();
                }
            };

            /*const validateSales = (rule, value, callback) => {
                if (value === ''||value==null) {
                    callback(new Error('请输入起送价格'));
                } else {
                    callback();
                }
            };*/

            const validateDesc = (rule, value, callback) => {
                if (value === ''||value === null) {
                    callback(new Error('Please enter product description'));
                } else {
                    callback();
                }
            };




            return {
                fileimgname: '',
                goodInfo:{
                    cid:'',
                    category:'',
                    goodName: '',
                    goodPic: '',
                    price: '',
                    sales: '',
                    desc: ''
                },
                newGoodInfo:{
                    cid:'',
                    sid:'',
                    category:'',
                    goodName: '',
                    goodPic: '',
                    price: '',
                    sales: '',
                    desc: ''
                },
                categories:[],
                //控制弹窗是否显示
                editDialogVisible: false,
                addDialogVisible: false,

                formLabelWidth: '80px',

                //文件上传的参数
                dialogImageUrl: '',
                dialogPicVisible: false,
                fileList:[],
                picture:'',
                fileLimit:1,

                /*分页*/
                pageSize: 5,
                total: 0,
                currentPage: 1,
                pageSizes: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10],

                search:'',

                goodRules:{
                    goodName:[
                        { validator:validateGoodName,trigger: 'blur'}
                    ],
                    /*categoryName:[
                        { validator:validateCategoryName,trigger: 'blur'}
                    ],*/
                    price:[
                        { validator:validatePrice,trigger: 'blur'}
                    ],
                    /*sales:[
                        { validator:validateSales,trigger: 'blur'}
                    ],*/
                    desc:[
                        { validator:validateDesc,trigger: 'blur'}
                    ],

                },
                newGoodRules:{
                    goodName:[
                        { validator:validateNewGoodName,trigger: 'blur'}
                    ],
                    /*categoryName:[
                        { validator:validateCategoryName,trigger: 'blur'}
                    ],*/
                    price:[
                        { validator:validatePrice,trigger: 'blur'}
                    ],
                    /*sales:[
                        { validator:validateSales,trigger: 'blur'}
                    ],*/
                    desc:[
                        { validator:validateDesc,trigger: 'blur'}
                    ],

                },

                goods: []
            }
        }
    }
</script>

<style scoped>
.el-table{
    line-height: 50px;
    margin: auto;
}

    .cell{
        width: 50px;
    }
</style>
