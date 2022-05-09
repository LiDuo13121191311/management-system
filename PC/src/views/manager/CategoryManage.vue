<template>
    <div style="text-align: center;margin: auto">
        <div style="height: 50px;">
            <el-button type="primary"
                       @click="add" style="float: left">Add</el-button>
        </div>
        <el-table
                :data="categories"
        style="width: 100%;border-radius: 4px;">
            <el-table-column
                    label="Class alias"
                    prop="categoryName">
            </el-table-column>
            <el-table-column label="Operation" width="160" >
                <template slot-scope="scope">
                    <el-button
                            size="mini"
                            @click="edit(scope.row)">Edit</el-button>
                    <el-button
                            size="mini"
                            type="danger"
                            @click="del(scope.row)">Delete</el-button>
                </template>
            </el-table-column>
        </el-table>

        <!--点击新增后的弹窗-->
        <el-dialog title="Category information" :visible.sync="addDialogVisible" style="line-height: 15px">
            <el-form :model="newCategoryInfo" status-icon :rules="newCategoryRules" ref="newCategoryInfo" style="text-align: left">
                <el-form-item label="Class alias" :label-width="formLabelWidth" prop="categoryName">
                    <el-input v-model="newCategoryInfo.categoryName" autocomplete="off" placeholder="Please enter a class alias"></el-input>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="flush">Cancel</el-button>
                <el-button type="primary" @click="addSave">Add</el-button>
            </span>
        </el-dialog>

        <!--点击修改后的弹窗-->
        <el-dialog title="Category information" :visible.sync="editDialogVisible" style="line-height: 15px">
            <el-form :model="categoryInfo" status-icon :rules="categoryRules" ref="categoryInfo" style="text-align: left">
                <el-form-item label="Class alias" :label-width="formLabelWidth" prop="categoryName">
                    <el-input v-model="categoryInfo.categoryName" autocomplete="off"></el-input>
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
                layout="total, sizes, prev, pager, next, jumper"
                :total="total">
        </el-pagination>
    </div>
</template>

<script>
    export default {
        name: "CategoryManage",
        created(){
            // console.log(this.$store.getters.getSid);
            // this.sid = this.$store.getters.getSid;
            this.sid = JSON.parse(localStorage.getItem("sid"))
            const data = JSON.parse(localStorage.getItem("shopManagerInfo"))
            this.axios.get("http://localhost:8084/findCategoriesByPage", {
                params: {
                    uid: data.smid,
                    pageSize: this.pageSize,
                    currentPage: this.currentPage
                }
            }).then(resp => {
                this.categories = resp.data.categories;
                this.total = resp.data.count;
            });
        },

        data() {
            /*类别信息非空判断和唯一验证*/
            const validateCategoryName = (rule, value, callback) => {
                if (value === ''||value==null) {
                        callback(new Error("Please enter a class alias"))
                } else {
                    if (value !== this.categoryInfo.categoryName) {
                        this.axios.get("http://localhost:8084/findCategoryNameBySid", {
                            params: {
                                categoryName: value,
                                sid: this.categoryInfo.sid
                            }
                        }).then(resp => {
                            if (resp.data === 'none') {
                                callback();
                            } else {
                                callback(new Error('The category already exists'));
                            }
                        })
                    }else {
                        callback();
                    }
                }
            };
            const validateNewCategoryName = (rule, value, callback) => {
                console.log(value)
                if (value === ''||value==null) {
                    callback(new Error("Please enter a class alias"))
                } else {
                    if (value !== this.newCategoryInfo.categoryName) {
                        this.axios.get("http://localhost:8084/findCategoryNameBySid", {
                            params: {
                                categoryName: value,
                                sid: this.sid
                            }
                        }).then(resp => {
                            if (resp.data === 'none') {
                                callback();
                            } else {
                                callback(new Error('The category already exists'));
                            }
                        })
                    }else {
                        callback();
                    }
                }
            };

                    return {
                        sid:'',
                        formLabelWidth: '120px',

                        categoryInfo: {
                            categoryName: '',
                            sid:'',
                            cid:''
                        },
                        newCategoryInfo: {
                            categoryName: '',
                            sid:'',
                            cid:''
                        },
                        categoryRules: {
                            categoryName: [
                                {validator: validateCategoryName, trigger: 'blur'}
                            ],

                        },
                        newCategoryRules:{
                            categoryName: [
                                {validator: validateNewCategoryName, trigger: 'blur'}
                            ],
                        },

                        editDisabled: true,
                        editDialogVisible: false,
                        addDialogVisible: false,

                        /*分页*/
                        pageSize: 5,
                        total: 0,
                        currentPage: 1,
                        pageSizes: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10],

                        categories: []
                    }
                },
                methods: {
                    /*保存新增的类别信息*/
                    addSave(){
                        this.newCategoryInfo.sid = this.sid;
                        console.log(this.newCategoryInfo);
                        this.$refs['newCategoryInfo'].validate((valid) => {
                            if (valid) {
                                this.axios.post("http://localhost:8084/addCategoryMessage", this.newCategoryInfo).then(resp => {
                                    if (resp.data === "success") {
                                        this.$message({
                                            message: "Successfully added！！！",
                                            type: 'success'
                                        });
                                        this.flush();
                                    } else {
                                        this.$message({
                                            message: "Failed to add！！！",
                                            type: 'warning'
                                        });
                                        this.flush();
                                    }
                                })
                            }
                        })
                    },
                    add(){
                      this.addDialogVisible = true;
                    },
                    edit(item){
                        console.log(item);
                        this.editDialogVisible = true;
                        this.categoryInfo.categoryName = item.categoryName;
                        this.categoryInfo.sid = item.sid;
                        this.categoryInfo.cid = item.cid;
                    },
                    del(row){
                        this.$confirm('Delete this category, continue?', 'Tips', {
                            confirmButtonText: 'Determine',
                            cancelButtonText: 'Cancel',
                            type: 'warning'
                        }).then(() => {
                            this.axios.get("http://localhost:8084/deleteCategory", { 
                                params: {  
                                    cid: row.cid
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

                /*取消保存*/
                flush() {
                    this.editDialogVisible = false;
                    this.$router.push({
                        name: "blank",
                        params: {
                            url: "categoryManage"
                        }
                    })
                },

            /*保存修改*/
            save() {
                console.log(this.categoryInfo);
                this.$refs['categoryInfo'].validate((valid) => {
                    if (valid) {
                        this.axios.post("http://localhost:8084/updateCategoryMessage", this.categoryInfo).then(resp => {
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
            }
        ,


            /*分页相关*/
            handleSizeChange(val)
            {
                this.pageSize = val;
                const that = this;
                this.axios.get("http://localhost:8084/findCategoriesByPage", {
                    params: {
                        uid: this.$store.getters.getShopManager.smid,
                        pageSize: this.pageSize,
                        currentPage: this.currentPage
                    }
                }).then(resp => {
                    this.categories = resp.data.categories;
                    this.total = resp.data.count;
                });
            }
        ,
            handleCurrentChange(val)
            {
                this.currentPage = val;
                const that = this;
                this.axios.get("http://localhost:8084/findCategoriesByPage", {
                    params: {
                        uid: this.$store.getters.getShopManager.smid,
                        pageSize: this.pageSize,
                        currentPage: this.currentPage
                    }
                }).then(resp => {
                    this.categories = resp.data.categories;
                    this.total = resp.data.count;
                });
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
