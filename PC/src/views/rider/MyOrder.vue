<template>
    <div>
        <h4>我所接的订单</h4>
        <van-card v-for="(order,index) in myOrders" :key="index">
            <template #title>
                <div style="width: 200px">
                    <h3 class="line-limit-length" style="margin: 5px;width: 90px;display: inline-block;text-align: left;">{{order.shop.shopName}}</h3>
                    <span style="margin: 5px;float: right" v-if="order.order.orderStat===3">
                            等待送达
                    </span>
                    <span style="margin: 5px;float: right" v-if="order.order.orderStat===4">
                            已送达
                    </span>
                </div>
            </template>
            <template #num>
                <span style="font-size: 15px;color: crimson">配送费${{order.order.deliveryPrice}}</span>
            </template>
            <template #thumb>
                <img :src="order.shop.logoSrc" style="width: 90px" alt=""/>
            </template>
            <template #desc>
                <div style="text-align: left;font-size: 12px;margin-left: 10px;margin-bottom: 10px">
                    <span>取货地址：{{order.shop.addressDetail}}</span>
                </div>
                <div style="text-align: left;font-size: 12px;margin-left: 10px;margin-bottom: 10px">
                    <span>收货地址：{{order.address.address}}</span>
                </div>
                <div style="text-align: left;font-size: 13px;margin-left: 10px">
                    <span>下单时间：{{order.order.createTime}}</span>
                </div>
                <div style="text-align: left;font-size: 15px;margin-left: 10px" v-if="order.order.orderStat === 3">
                    <span>订单状态：待送达</span>
                </div>
                <div style="text-align: left;font-size: 15px;margin-left: 10px" v-if="order.order.orderStat === 4">
                    <span>订单状态：已送达</span>
                </div>
            </template>
            <template #footer>
                <van-button round hairline style="width: 80px" size="small" type="primary" @click="deliverySuccess(order)" v-if="order.order.orderStat === 3">已送达</van-button>
                <van-button round hairline style="width: 80px" size="small" type="info" @click="orderInfo(order)">查看详情</van-button>
            </template>

        </van-card>
        <div v-if="myOrders.length === 0">
            <van-empty description="您还没有接单噢~~去订单广场看看吧！！" />
        </div>
        <!--填充-->
        <div style="height:50px;display:block;"></div>

    </div>

</template>

<script>
    import { Dialog } from 'vant';
    import { Notify } from 'vant';
    export default {
        name: "MyOrder",
        created(){
          this.deliveryInfo = this.$store.getters.getRider;
          this.axios.get("http://localhost:8084/getAllOrderByDid",{
              params:{
                  did: this.deliveryInfo.did
              }
          }).then(resp=>{
              console.log(resp.data);
              this.myOrders = resp.data;
          })
        },
        data(){
            return{
                did:'',
                deliveryInfo:'',
                myOrders:[
                    {
                        /*userName:'lky',
                        userPhone:'12112112112',
                        shop:{
                            address:'浙江省杭州市西湖区KFC',
                            logoSrc:'/pic/kfcLogo.png',
                            shopName:'KFC',
                            phone:'12313212312'
                        },
                        address:{
                            address:'浙江省杭州市西湖区文三路 138 号东方通信大厦 7 楼 501 室'
                        },
                        order:{
                            orderStat:3,
                            deliveryPrice:'5',
                            createTime:'2021-8-10 19:34:10',
                            orderNumber:'DM202108101934100001',
                            totalPrice:'20'
                        }*/
                    }
                ]
            }
        },
        methods:{
            //已送达，改变订单状态
            deliverySuccess(order){
                console.log(order);
                Dialog.confirm({
                    title: '提示',
                    message: '确定已经送达吗？提前点击送达会受到相应的处罚！',
                }).then(() => {
                        console.log(order);
                        this.axios.get("http://localhost:8084/deliverySuccess",{
                            params:{
                                oid:order.order.oid
                            }
                        }).then(resp => {
                            if (resp.data === "success") {
                                Notify({ type: 'success', message: '送单成功' });
                                this.flush();
                            } else {
                                Notify({ type: 'danger', message: '送单失败' });
                                this.flush();
                            }
                        })
                    })
                    .catch(() => {
                        Notify({ type: 'warning', message: '取消' });
                    });
                /*this.$confirm("确定已经送达吗？提前点击送达会受到相应的处罚！", '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'info'
                }).then(() => {
                    console.log(order);
                    this.axios.get("http://localhost:8084/deliverySuccess",{
                        params:{
                            oid:order.oid
                        }
                    }).then(resp => {
                        if (resp.data === "success") {
                            this.$message({
                                message: "送单成功！！！",
                                type: 'success'
                            });
                            this.flush();
                        } else {
                            this.$message({
                                message: "送单失败！！！",
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
                })*/
            },

            orderInfo(order){
                console.log(order);
                this.$router.push({
                    name:"orderDetail",
                    params:{
                        orderInfo:order
                    }
                })
            },
            //刷新
            flush(){
                this.$router.push({
                    name:"blank_rider",
                    params:{
                        url:"myOrder"
                    }
                })
            }
        }
    }
</script>

<style scoped>
    .line-limit-length {

        overflow: hidden;

        text-overflow: ellipsis;

        white-space: nowrap;

    }
</style>
