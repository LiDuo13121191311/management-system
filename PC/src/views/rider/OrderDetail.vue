<template>
    <div>
        <van-nav-bar
                title="订单详情"
                left-text="返回"
                left-arrow
                @click-left="onClickLeft"
        />
        <van-card>
            <template #title>
                <div style="width: 200px">
                    <h3 class="line-limit-length" style="margin: 5px;width: 120px;display: inline-block;text-align: left;">{{orderDetail.shop.shopName}}</h3>
                    <!--<span style="float: left;width: 250px" v-if="orderDetail.order.orderStat===3">
                            等待骑手送达
                    </span>
                    <span style="margin: 5px;float: right" v-if="orderDetail.order.orderStat===4">
                            已送达
                    </span>-->
                </div>
            </template>
            <br>
            <template #num>
                <span style="font-size: 15px;color: crimson;">配送费${{orderDetail.order.deliveryPrice}}</span>
            </template>
            <template #thumb>
                <img :src="orderDetail.shop.logoSrc" style="width: 90px" alt=""/>
            </template>
            <template #desc>
                <div style="text-align: left;font-size: 12px;margin-left: 10px;margin-bottom: 10px">
                    <span>取货地址：{{orderDetail.shop.addressDetail}}</span>
                </div>
                <div style="text-align: left;font-size: 12px;margin-left: 10px;margin-bottom: 10px">
                    <span>店铺电话：{{orderDetail.shop.phone}}</span>
                </div>
                <div style="text-align: left;font-size: 12px;margin-left: 10px;margin-bottom: 10px">
                    <span>收货地址：{{orderDetail.address.address}}</span>
                </div>
                <div style="text-align: left;font-size: 12px;margin-left: 10px;margin-bottom: 10px">
                    <span>用户名：{{orderDetail.userName}}</span>
                </div>
                <div style="text-align: left;font-size: 12px;margin-left: 10px;margin-bottom: 10px">
                    <span>用户电话：{{orderDetail.userPhone}}</span>
                </div>
                <div style="text-align: left;font-size: 13px;margin-left: 10px">
                    <span>下单时间：{{orderDetail.order.createTime}}</span>
                </div>
                <br>
                <div style="text-align: left;font-size: 13px;margin-left: 10px" v-if="orderDetail.order.orderTime!==null">
                    <span>接单时间：{{orderDetail.order.orderTime}}</span>
                </div>
                <br>
                <div style="text-align: left;font-size: 13px;margin-left: 10px" v-if="orderDetail.order.pickTime!==null">
                    <span>骑手接单时间：{{orderDetail.order.pickTime}}</span>
                </div>

                <div style="text-align: left;font-size: 13px;margin-left: 10px" v-if="orderDetail.order.arriveTime!==null">
                    <span>送达时间：{{orderDetail.order.arriveTime}}</span>
                </div>
                <br>
                <div style="text-align: left;font-size: 15px;margin-left: 10px" v-if="orderDetail.order.orderStat === 3">
                    <span>订单状态：待送达</span>
                </div>
                <div style="text-align: left;font-size: 15px;margin-left: 10px" v-if="orderDetail.order.orderStat === 4">
                    <span>订单状态：已送达</span>
                </div>

                    <br>

            </template>
            <!--<template #footer>
                <van-button round hairline style="width: 80px" type="small" @click="viewOrderInfo(order)">查看详情</van-button>
            </template>-->
        </van-card>
        <!--填充-->
        <div style="height:50px;display:block;"></div>
    </div>
</template>

<script>
    export default {
        name: "OrderDetail",
        created() {
            console.log("订单详情");
            console.log(this.$route.params.orderInfo);
            this.orderDetail = this.$route.params.orderInfo;
        },
        data(){
            return{
                orderDetail:{
                    userName:'lky',
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
                    }
                },
            }
        },
        methods:{
            onClickLeft(){
                this.$router.push("/myOrder");
            }
        }
    }
</script>

<style scoped>

</style>
