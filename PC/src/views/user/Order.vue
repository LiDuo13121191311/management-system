<template>
    <div style="width: 60%;margin-left: 20%;">
        <span><h4>My Order</h4></span>
        <!--<van-card  v-for="(good,index) in goods" :key="index"
                   :num="good.count"
                   :price="good.price"
                   :desc="good.desc"
                   :title="good.title"
                   :thumb="good.thumb"
        >
            <template #tags>
                <van-tag plain type="danger">标签</van-tag>
            </template>
            <template #tags>
                <van-tag plain type="danger">标签</van-tag>
            </template>
            <template #footer>
                <van-button round hairline style="width: 80px" type="small" @click="deleteOrder(good.num)">删除订单</van-button>
            </template>
        </van-card>-->
        <van-card  v-for="(order,index) in orders" :key="index" @click="toOrderInfo(order)">
            <template #title>
                <div style="display: flex;margin-bottom: 20px;">
                    <h3 class="line-limit-length" style="margin: 5px;width: 120px;display: inline-block;text-align: left;">{{order.shopName}}</h3>
                    <span style="margin: 5px;float: right;" v-if="order.orderStat===1">
                            Waiting for the merchant to receive the order
                    </span>
                    <!-- <span style="margin: 5px;float: right" v-if="order.orderStat===2">
                            等待骑手接单
                    </span>
                    <span style="margin: 5px;float: right" v-if="order.orderStat===3">
                            等待送达
                    </span>
                    <span style="margin: 5px;float: right" v-if="order.orderStat===4">
                            已送达
                    </span> -->
                    <span style="margin: 5px;float: right;width: 75px" v-if="order.orderStat===2">
                            Completed
                    </span>
                </div>
            </template>
            <template #num>
                <span style="font-size: 15px;color: crimson">${{order.totalPrice}}</span>
            </template>
            <!-- <template #thumb>
                <img :src="order.logoSrc" style="width: 90px;height: 80px" alt=""/>
            </template> -->
            <template #desc>
                <div style="text-align: left;font-size: 15px;margin-left: 10px">
                    <span>{{order.createTime}}</span>
                </div>

            </template>
            <!--<template #tags>
                <van-tag plain type="danger">标签</van-tag>
                <van-tag plain type="danger">标签</van-tag>
            </template>-->
            <!-- <template #footer>
                <van-button round hairline style="width: 80px" type="small">删除订单</van-button>
            </template> -->
        </van-card>
        <div v-if="orders.length === 0">
            <van-empty description="您还没有订单噢~~现在去下单吧！！" />
        </div>
        <!--填充-->
        <div style="height:50px;display:block;"></div>
    </div>
</template>

<script>
    import Toast from "vant/lib/toast";

    export default {
        name: "Order",
        created(){
            const uid = JSON.stringify(this.$store.getters.getUser.id);
            console.log(uid);
            this.axios.get("http://localhost:8084/findAllOrderByUid",{
                params:{
                    uid:uid
                }
            }).then(resp=>{
                console.log(resp.data);
                this.orders  = resp.data;
            })
        },
        data(){
            return{
                uid:'',
                orders:[
                    {
                        /*
                        goods: null,
                        totalPrice:null,
                        orderNumber:null,
                        addressMessage:null,
                        orderStat:null,
                        deliveryPrice:null,
                      */
                        /*oid:'',
                        sid:'',
                        logoSrc:'/pic/kfcLogo.png',
                        shopName:'华莱士·全鸡汉堡',
                        orderStat:1,
                        totalPrice:'50',
                        createTime:'2021.8.9',
                        goods:'',
                        orderNumber:'',
                        deliveryPrice:''*/
                    },
                    /*{
                        num: 2,
                        price:"2.00",
                        desc:"描述信息",
                        title:"商品标题",
                        thumb:"https://img01.yzcdn.cn/vant/ipad.jpeg"
                    },
                    {
                        num: 3,
                        price:"2.00",
                        desc:"描述信息",
                        title:"商品标题",
                        thumb:"https://img01.yzcdn.cn/vant/ipad.jpeg"
                    },
                    {
                        num: 4,
                        price:"2.00",
                        desc:"描述信息",
                        title:"商品标题",
                        thumb:"https://img01.yzcdn.cn/vant/ipad.jpeg"
                    },
                    {
                        num: 5,
                        price:"2.00",
                        desc:"描述信息",
                        title:"商品标题",
                        thumb:"https://img01.yzcdn.cn/vant/ipad.jpeg"
                    }*/
                ],
            }
        },
        methods:{
            deleteOrder(id){
                Toast("删除订单"+id);
                console.log(id)
            },

            toOrderInfo(order){
                console.log(order);
                this.$router.push({
                    name:"orderInfo_user",
                    params:{
                        orderInfo: order
                    }
                })

               /* orderInfo: {
                    goodTotalPrice:null,
                        deliveryPrice:null,
                        totalPrice:null,
                        orderNumber:null,
                        goods:null,
                        addressMessage: null,
                        orderStat:null,
                        createTime:null,
                        /!*deliveryManId*!/
                        dMId:null
                }*/
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
