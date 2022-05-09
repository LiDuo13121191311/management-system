<template>
    <div>
        <van-nav-bar
                title="订单详情"
                left-text="返回"
                left-arrow
                @click-left="onClickLeft"
        />
        <!--<van-card v-model="orderInfo" :key="index">
            <template #title>
                <div style="width: 200px">
                    <h3 class="line-limit-length" style="margin: 5px;width: 90px;display: inline-block;text-align: left;">{{shop.shopName}}</h3>
                    <span style="margin: 5px;float: right" v-if="order.orderStat===3">
                            等待骑手送达
                    </span>
                    <span style="margin: 5px;float: right" v-if="order.orderStat===4">
                            已送达
                    </span>
                </div>
            </template>
            <template #num>
                <span style="font-size: 15px;color: crimson;">配送费${{order.deliveryPrice}}</span>
            </template>
            <template #thumb>
                <img :src="shop.logoSrc" style="width: 90px" alt=""/>
            </template>
            <template #desc>
                <div style="text-align: left;font-size: 12px;margin-left: 10px;margin-bottom: 10px">
                    <span>取货地址：{{shop.address}}</span>
                </div>
                <div style="text-align: left;font-size: 12px;margin-left: 10px;margin-bottom: 10px">
                    <span>店铺电话：{{shop.phone}}</span>
                </div>
                <div style="text-align: left;font-size: 12px;margin-left: 10px;margin-bottom: 10px">
                    <span>收货地址：{{address.address}}</span>
                </div>
                <div style="text-align: left;font-size: 12px;margin-left: 10px;margin-bottom: 10px">
                    <span>用户名：{{userName}}</span>
                </div>
                <div style="text-align: left;font-size: 12px;margin-left: 10px;margin-bottom: 10px">
                    <span>用户电话：{{userPhone}}</span>
                </div>
                <div style="text-align: left;font-size: 13px;margin-left: 10px">
                    <span>下单时间：{{order.createTime}}</span>
                </div>
                <br>
                <div style="text-align: left;font-size: 13px;margin-left: 10px" v-if="order.orderTime!==null">
                    <span>接单时间：{{order.orderTime}}</span>
                </div>
                <div style="text-align: left;font-size: 13px;margin-left: 10px" v-if="order.pickTime!==null">
                    <span>骑手接单时间：{{order.pickTime}}</span>
                </div>
                <div style="text-align: left;font-size: 13px;margin-left: 10px" v-if="order.arriveTime!==null">
                    <span>送达时间：{{order.arriveTime}}</span>
                </div>
                <div style="text-align: left;font-size: 15px;margin-left: 10px" v-if="order.orderStat === 3">
                    <span>订单状态：待送达</span>
                </div>
                <div style="text-align: left;font-size: 15px;margin-left: 10px" v-if="order.orderStat === 4">
                    <span>订单状态：已送达</span>
                </div>

                    <br>

            </template>
            &lt;!&ndash;<template #footer>
                <van-button round hairline style="width: 80px" type="small" @click="viewOrderInfo(order)">查看详情</van-button>
            </template>&ndash;&gt;
        </van-card>-->
        <!--填充-->
        <div style="height:50px;display:block;"></div>

    </div>

</template>

<script>
    export default {
        create(){
            console.log("查看订单详情");
            //console.log(this.$route.params.orderInfo)
            /*this.axios.get("http://localhost:8084/getOrderByDid",{
                params:{
                    did: this.deliveryInfo.did
                }
            }).then(resp=>{
                console.log(resp.data);
                this.myOrders = resp.data;
            })*/
        },
        data(){
            return{
                /*orderInfo:[
                    {
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
                            orderTime:null,
                            pickTime:null,
                            arriveTime:null,
                            orderNumber:'DM202108101934100001',
                            totalPrice:'20'
                        }
                    }
                ]*/
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
