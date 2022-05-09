<template>
    <div class="User_goods goods">
        <van-nav-bar
                title="Order details"
                left-text="Back"
                left-arrow
                @click-left="onClickLeft"
        />
        <!-- <van-cell-group class="goods-cell-group">

            <van-cell>
                <van-col span="16"><van-icon name="location-o" style="margin-right: 30px;" />收货人：{{orderInfo.addressMessage.name}}</van-col>
                <van-col>{{orderInfo.addressMessage.tel}}</van-col>
                <van-col span="21" style="padding-left: 43px;font-size: 13px;width:280px">收货地址：{{orderInfo.addressMessage.address}}</van-col>
            </van-cell>

        </van-cell-group> -->

        <van-cell-group class="goods-cell-group">
            <van-cell class="goods-express" style="font-weight: bold">
                <van-col span="20">{{orderInfo.shopName}}</van-col>
            </van-cell>
        </van-cell-group>

        <van-card
                v-for="(good,index) in orderInfo.goods"
                :key="index"
                :num="good.count"
                :price="good.price"
                :desc="good.desc"
                :title="good.goodName"
                :thumb="Phototimg(good.goodPic)"
        />

        <!--<van-cell-group v-if="deliveryMan!=null">
            <van-cell>
                <van-col span="21">配送骑士</van-col>
                <van-col>{{deliveryMan}}</van-col>
            </van-cell>
        </van-cell-group>-->
        <van-cell-group class="goods-cell-group">
            <van-cell class="goods-express" style="font-weight: bold">
                <van-col span="20">Commodity amount</van-col>
                <van-col style="color: red">${{orderInfo.goodTotalPrice}}</van-col>
            </van-cell>
        </van-cell-group>

        <!-- <van-cell-group>
            <van-cell class="goods-cell-group" style="font-weight: bold">
                <van-col span="20">配送费</van-col>
                <van-col style="color: red">${{orderInfo.deliveryPrice}}</van-col>
            </van-cell>
        </van-cell-group> -->


        <van-cell-group>
            <van-cell class="goods-cell-group" style="font-weight: bold">
                <van-col span="20">total</van-col>
                <van-col style="color: red">${{orderInfo.totalPrice}}</van-col>
            </van-cell>
        </van-cell-group>

        <van-cell-group>
            <van-cell class="goods-express" style="font-weight: bold">
                <van-col span="20" style="width:80px">Order time</van-col>
                <van-col style="color: red;margin-left: 70px;">{{orderInfo.createTime}}</van-col>
            </van-cell>
            <van-cell class="goods-express" style="font-weight: bold" v-if="orderDetail.orderTime!==null">
                <van-col span="20" style="width:80px">Order receiving time</van-col>
                <van-col style="color: red;margin-left: 70px;">{{orderDetail.orderTime}}</van-col>
            </van-cell>
            <!-- <van-cell class="goods-express" style="font-weight: bold" v-if="orderDetail.pickTime!==null">
                <van-col span="20" style="width:90px">骑手接单时间</van-col>
                <van-col style="color: red;width: 140px;margin-left: 60px;">{{orderDetail.pickTime}}</van-col>
            </van-cell>
            <van-cell class="goods-express" style="font-weight: bold" v-if="orderDetail.arriveTime!==null">
                <van-col span="20" style="width:90px">送达时间</van-col>
                <van-col style="color: red;width: 140px;margin-left: 60px;">{{orderDetail.arriveTime}}</van-col>
            </van-cell>
            <van-cell class="goods-express" style="font-weight: bold" v-if="deliveryMan!==null">
                <van-col span="20" style="width:90px">骑手联系方式</van-col>
                <van-col style="color: red;width: 140px;margin-left: 60px;">{{deliveryMan.phone}}</van-col>
            </van-cell> -->

            <van-cell class="goods-express" style="font-weight: bold">
                <van-col span="20" style="width:100px;margin-bottom: 10px;">Order status</van-col>
                <van-col style="color: red;margin-left: 0;">{{payStatChange(orderInfo.orderStat)}}</van-col>
            </van-cell>
        </van-cell-group>
        <!--填充-->
        <div style="height:50px;display:block;"></div>
    </div>
</template>

<script>
    export default {
        name: "OrderInfo",
        created() {
            this.orderInfo = this.$route.params.orderInfo;
            //根据oid获取taker
            this.axios.get("http://localhost:8084/findTakerByOid",{
                params:{
                    oid:this.orderInfo.oid
                }
            }).then(resp=>{
                console.log(resp.data);
                this.deliveryMan = resp.data;
            });

            this.axios.get("http://localhost:8084/getOrderByOid",{
                params:{
                    oid:this.orderInfo.oid
                }
            }).then(resp=>{
                console.log(resp.data);
                this.orderDetail = resp.data;
            })

            /*this.goods = this.$route.params.goods;
            this.totalPrice = this.$route.params.totalPrice;
            this.orderNumber = this.$route.params.orderNumber;
            this.addressMessage = this.$route.params.addressMessage;
            this.orderStat = this.$route.params.orderStat;
            this.deliveryPrice = this.$route.params.deliveryPrice;*/
        },
        data(){
            return{
                orderDetail:"",
                goods: null,
                totalPrice:null,
                orderNumber:null,
                addressMessage:null,
                orderStat:null,
                deliveryPrice:null,
                orderInfo:null,
                deliveryMan:{
                    phone:''
                },
            }
        },
        methods:{
            // 图片转换
            Phototimg(src) {
            const a = 'http://localhost:8084/' + src
            console.log(a)
            return a
            },
            payStatChange(stat){
                if(stat === 0){
                    return 'Unpaid';
                }else if(stat === 1){
                    return 'Paid, waiting for the merchant to receive the order';
                }else if(stat === 2){
                    return 'Completed';
                }
                // else if(stat === 2){
                //     return '商家已接单,等待骑士接单';
                // }
                // else if(stat === 3){
                //     /*this.axios.get("http://localhost:8084/findTakerById",this.orderInfo.dMId).then(resp=>{
                //         this.deliveryMan = resp.data;
                //     });*/
                //     return '骑士已接单,等待送达';
                // }else if(stat === 4){
                //     return '已送达';
                // }
            },
            onClickLeft(){
                this.$router.push("/order");
            }
        }
    }
</script>

<style lang="scss" scoped>
.User_goods {
    width: 100%;
    margin-left: 0;
}
</style>
