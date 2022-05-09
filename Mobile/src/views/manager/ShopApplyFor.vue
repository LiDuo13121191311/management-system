<template>
    <div style="text-align: center;margin: auto">
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
                    <!--<el-image :src="require('../../../public'+scope.row.shopInfo.logoSrc)" style="width: 50px;height: 50px"/>-->
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
            <!--<el-table-column
                    label="总销量"
                    prop="shopInfo.totalSales" >
            </el-table-column>-->
            <el-table-column
                    label="店长"
                    prop="userInfo.username" >
            </el-table-column>
            <el-table-column
                    label="联系方式"
                    prop="shopInfo.phone" >
            </el-table-column>
            <el-table-column
                    label="店铺地址"
                    prop="shopInfo.addressDetail" >
            </el-table-column>
            <el-table-column
                    align="right">
                <!--<template slot="header" slot-scope="scope">
                    <el-input
                            v-model="search"
                            size="mini"
                            placeholder="输入关键字搜索"/>
                </template>-->
                <template slot-scope="scope" style="width: 110px">
                    <el-button
                            size="mini"
                            type="primary"
                            @click="agree(scope.row.shopInfo.sid)" style="width: 55px;display: inline-block;text-align: center">同意</el-button>
                    <!--<el-button
                            size="mini"
                            type="warning"
                            @click="edit(scope.$index, scope.row)" style="width: 55px;text-align: center">驳回</el-button>-->
                </template>
            </el-table-column>
        </el-table>
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
        name: "ShopApplyFor",
        created(){
          this.axios.get("http://localhost:8084/findAllShopApplyFor",{
              params:{
                  currentPage:this.currentPage,
                  pageSize:this.pageSize
              }
          }).then(resp=>{
              if(resp.data!=="none"){
                  console.log(resp.data);
                  this.shopInfo = resp.data;
                  this.total = resp.data.length;
              }
          })
        },
        data(){
            return{

                search:'',
                shopInfo:[],

                //分页
                pageSize:5,
                total: 0,
                currentPage: 1,
                pageSizes:[1,2,3,4,5,6,7,8,9,10],
            }
        },
        methods:{

            /*同意申请*/
            agree(sid){
                console.log("sid:"+sid);
                this.$confirm("确定要同意这个店铺申请吗？",'提示',{
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type:'info'
                }).then(()=>{
                    this.axios.get("http://localhost:8084/agreeShopApplyFor",{
                        params:{
                            sid:sid
                        }
                    }).then(resp=>{
                        if(resp.data==="success"){
                            this.$message({
                                message:"操作成功！！！",
                                type:'success'
                            });
                            this.flush();
                        }else {
                            this.$message({
                                message:"操作失败！！！",
                                type:'warning'
                            });
                            this.flush();
                        }
                    })
                })
            },

            /*刷新*/
            flush(){
                this.dialogVisible = false;
                this.$router.push({
                    name:"blank",
                    params:{
                        url:"allShops"
                    }
                })
            },

            /*分页*/
            handleSizeChange(val) {
                this.pageSize = val;
                const that = this;
                this.axios.get("http://localhost:8084/findAllShopApplyFor",{
                    params:{
                        currentPage:this.currentPage,
                        pageSize:this.pageSize
                    }
                }).then(resp=>{
                    console.log(resp.data);
                    this.shopInfo = resp.data;
                    this.total = resp.data.length;
                })
            },
            handleCurrentChange(val) {
                this.currentPage = val;
                const that = this;
                this.axios.get("http://localhost:8084/findAllShopApplyFor",{
                    params:{
                        currentPage:this.currentPage,
                        pageSize:this.pageSize
                    }
                }).then(resp=>{
                    console.log(resp.data);
                    this.shopInfo = resp.data;
                    this.total = resp.data.length;
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
</style>
