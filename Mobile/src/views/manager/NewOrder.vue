<template>
    <div style="text-align: center;margin: auto">
        <el-table size="mini"
                :data="orders"
                style="width:100%;border-radius: 4px;margin-top: 20px;">
            <el-table-column
                    label="order number"
                    prop="orderInfo.orderNumber" >
            </el-table-column>
            <el-table-column
                    label="Order user"
                    prop="userInfo.username" >
            </el-table-column>
            <el-table-column width="120"
                    label="Order details"
                    prop="orderInfo" >
                <template slot-scope="scope">
                    <el-popover
                            placement="right"
                            trigger="click">
                        <div>
                            <el-table :data="scope.row.goodInfo" size="mini">
                                <el-table-column width="200" property="goodName" label="Trade name"></el-table-column>
                                <el-table-column width="80" property="count" label="Quantity">
                                    <template slot-scope="scope">
                                        ×&nbsp;{{scope.row.count}}
                                    </template>
                                </el-table-column>
                            </el-table>
                            <div style="margin: 5px;color: #409EFF">Total price：${{scope.row.orderInfo.totalPrice}}</div>
                        </div>
                        <el-button size="mini" slot="reference">View details</el-button>
                    </el-popover>
                </template>
            </el-table-column>
            <!-- <el-table-column
                    label="送达地址"
                    prop="addressInfo.address" >
            </el-table-column> -->
            <el-table-column
                    label="Creation time"
                    prop="orderInfo.createTime" >
            </el-table-column>
            <el-table-column width="140" label="Operation" >
                <template slot-scope="scope">
                    <el-button
                            size="mini"
                            type="primary"
                            @click="pick(scope.row)">Order receiving</el-button>
                </template>
            </el-table-column>
        </el-table>
        <!--分页-->
        <el-pagination
                @size-change="handleSizeChange"
                @current-change="handleCurrentChange"
                :current-page="currentPage"
                :page-sizes="pageSizes"
                :page-size="pageSize"
                layout="sizes, prev, pager, next, jumper"
                :total="total">
        </el-pagination>
    </div>
</template>

<script>
    export default {
        name: "NewOrder",
        created(){
            // this.sid = this.$store.getters.getSid;
            // console.log(this.sid);
            const data = JSON.parse(localStorage.getItem("shopManagerInfo"))
            this.sid = JSON.parse(localStorage.getItem("sid"))
            this.axios.get("http://localhost:8084/getAllNewOrderBySid",{
                params:{
                    sid:this.sid,
                    pageSize: this.pageSize,
                    currentPage: this.currentPage
                }
            }).then(resp=>{
                console.log(resp.data);
                this.orders = resp.data.orders;
                this.total = resp.data.totalCount;
            })
        },
        data(){
            return{
                sid:'',
                orders:[
                    {
                        orderNumber:'',
                        userInfo:'',
                        orderInfo:'',
                        addressInfo:'',
                        createTime:''
                    }
                ],

                /*分页*/
                pageSize:5,
                total: 0,
                currentPage: 1,
                pageSizes:[1,2,3,4,5,6,7,8,9,10],

                search:'',
            }
        },
        methods:{
            /*接单*/
            pick(item){
                this.$confirm("Are you sure you want to take the order", 'Tips', {
                    confirmButtonText: 'Determine',
                    cancelButtonText: 'Cancel',
                    type: 'info'
                }).then(() => {
                    this.axios.get("http://localhost:8084/shopPickOrder",{
                        params:{
                            oid:item.orderInfo.oid
                        }
                    }).then(resp=>{
                        if (resp.data === "success") {
                            this.$message({
                                message: "Order received successfully！！！",
                                type: 'success'
                            });
                            this.flush();
                        } else {
                            this.$message({
                                message: "Order receiving failed！！！",
                                type: 'warning'
                            });
                            this.flush();
                        }
                    })
                })
            },

            /*刷新*/
            flush(){
                this.$router.push({
                    name:"blank",
                    params:{
                        url:"newOrder"
                    }
                })
            },

            /*分页相关*/
            handleSizeChange(val) {
                this.pageSize = val;
                const that = this;
                this.axios.get("http://localhost:8084/getAllNewOrderBySid",{
                    params:{
                        sid:this.sid,
                        pageSize: this.pageSize,
                        currentPage: this.currentPage
                    }
                }).then(resp=>{
                    this.orders = resp.data.orders;
                    this.total = resp.data.totalCount;
                });
            },
            handleCurrentChange(val) {
                this.currentPage = val;
                const that = this;
                this.axios.get("http://localhost:8084/getAllNewOrderBySid",{
                    params:{
                        sid:this.sid,
                        pageSize: this.pageSize,
                        currentPage: this.currentPage
                    }
                }).then(resp=>{
                    this.orders = resp.data.orders;
                    this.total = resp.data.totalCount;
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
