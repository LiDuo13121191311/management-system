<template>
    <div>
        <h4>订单广场</h4>
        <div v-if="orderFree.length !== 0">
            <van-card v-for="(freeOrder,index) in orderFree" :key="index" v-if="orderFree.length!==0">
                <template #title>
                    <div style="width: 200px">
                        <h3 class="line-limit-length" style="margin: 5px;width: 90px;display: inline-block;text-align: left;">{{freeOrder.shop.shopName}}</h3>
                        <span style="margin: 5px;float: right" v-if="freeOrder.order.orderStat===2">
                            等待骑手接单
                        </span>
                    </div>
                </template>
                <template #num>
                    <span style="font-size: 15px;color: crimson">配送费${{freeOrder.order.deliveryPrice}}</span>
                </template>
                <template #thumb>
                    <img :src="freeOrder.shop.logoSrc" style="width: 90px" alt=""/>
                </template>
                <template #desc>
                    <div style="text-align: left;font-size: 12px;margin-left: 10px;margin-bottom: 10px">
                        <span>取货地址：{{freeOrder.shop.address}}</span>
                    </div>
                    <div style="text-align: left;font-size: 12px;margin-left: 10px;margin-bottom: 10px">
                        <span>收货地址：{{freeOrder.address.address}}</span>
                    </div>
                    <div style="text-align: left;margin-left: 10px">
                        <span>下单时间：{{freeOrder.order.createTime}}</span>
                    </div>
                    <div style="text-align: left;margin-left: 10px">
                        <span>接单时间：{{freeOrder.order.orderTime}}</span>
                    </div>
                </template>
                <template #footer>
                    <van-button round hairline style="width: 80px" type="small" @click="pickOrder(freeOrder)">接单</van-button>
                </template>
            </van-card>
        </div>

        <div v-if="orderFree.length === 0">
            <van-empty description="暂时没有订单噢~~" />
        </div>
        <!--填充-->
        <div style="height:50px;display:block;"></div>

    </div>

</template>

<script>
    import Toast from "vant/lib/toast";
    export default {
        name: "Home",
        created() {
            this.axios.get("http://localhost:8084/getFreeOrders").then(resp=>{
                console.log(resp.data);
                this.orderFree = resp.data;
            })
        },
        data(){
            return{
                orderFree:[
                    {
                        userName:'',
                        userPhone:'',
                        shop:{
                            address:'',
                            logoSrc:'',
                            shopName:'',
                            phone:''
                        },
                        address:{
                            address:''
                        },
                        order:{
                            oid:'',
                            deliveryPrice:'',
                            createTime:'',
                            orderNumber:'',
                            totalPrice:''
                        }
                    }
                ],
            }
        },
        methods:{
            pickOrder(order){
                const toast = Toast.loading({
                    duration: 2000,
                    forbidClick: true,
                    message: "抢单中..."
                });
                setTimeout(()=>{
                    this.axios.get("http://localhost:8084/pickOrderByDid",{
                        params:{
                            oid:order.order.oid,
                            did:this.$store.getters.getRider.did
                        }
                    }).then(resp=>{
                        if(resp.data==="success"){
                            Toast("接单成功！！！");
                            /*this.$router.push('/toOrderHome');*/
                            this.flush();
                        }else {
                            Toast("接单失败！！！");
                            this.$router.push('/toOrderHome');
                        }
                    })
                },2000)
            },

            flush(){
                this.$router.push({
                    name:"blank_rider",
                    params:{
                        url:"toOrderHome"
                    }
                })
            },

        }
    }
</script>

<style scoped>

</style>
