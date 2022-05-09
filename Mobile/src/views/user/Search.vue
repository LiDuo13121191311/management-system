<template>
    <div>
        <van-nav-bar
                title="搜索页面"
                left-text="返回"
                left-arrow
                @click-left="onClickLeft"
        />
        <van-search v-model="value" placeholder="请输入搜索关键词" show-action>
            <template #action>
                <div @click="search">搜索</div>
            </template>
        </van-search>
        <el-card class="box-card" v-show="cardShow" style="background-color: #f7f8fa">
            <div slot="header" class="clearfix">
                <span>{{title}}</span>
            </div>
            <!--<van-card  v-for="(good,index) in goods" :key="index"
                       :num="good.num"
                       :price="good.price"
                       :desc="good.desc"
                       :title="good.title"
                       :thumb="good.thumb"
            >
            </van-card>-->
            <ul>
                <li v-for="(shop,index) in shops" :key="index">
                    <div style="border-radius: 10px;background-color: white"  @click="toShop(shop)">
                        <div style="display:inline-block;float: left">
                            <img :src="shop.logoSrc" width="80" height="80" alt="">
                        </div>
                        <div style="">
                            <h3 style="width: 195px;margin-bottom: 5px;padding-left: 80px;text-align: left">{{shop.shopName}}</h3>
                            <div style="width: 195px;padding-left: 80px;text-align: left">
                                <span style="font-size: 15px;">月售{{shop.totalSales}}</span><br>
                            </div>
                            <!-- <div style="width: 195px;font-size: 15px;padding-left: 80px;text-align: left">
                                <span style="">起送${{shop.deliveryCost}}</span>
                                <span style="margin: 5px">配送${{shop.minCost}}</span>
                                <span style="margin: 5px" v-if="shop.deliveryTime===null">40分钟</span>
                                <span style="margin: 5px" v-if="shop.deliveryTime!==null">{{shop.deliveryTime}}分钟</span>
                                <br>
                            </div> -->
                            <p style="width: 70px;margin: 10px">店铺主打</p>
                            <p style="width: 250px;height: 50px;margin: 10px" v-if="shop.desc!==null">{{shop.desc}}</p>
                            <p style="width: 250px;height: 50px;margin: 10px" v-if="shop.desc===null">暂无描述</p>
                        </div>
                    </div>

                </li>
            </ul>
        </el-card>
        <!--填充-->
        <div style="height:60px;display:block;background-color: white"></div>
    </div>
</template>

<script>
    import axios from "axios";

    export default {
        name: "Search",
        data(){
            return{
                totalPrice:null,
                show:true,
                value:'',
                cardShow:true,
                title:'专属好店',
                shops:[
                    {
                        shopName:"蜜雪冰城",
                        desc:"冰淇淋与茶",
                        totalSales: 2000,
                        minCost:"15",
                        deliveryCost:"3",
                        deliveryTime:"40",
                        logoSrc:"upload/蜜雪冰城logo_20210905175221.png"
                    },

                ],
            }
        },
        methods: {
            onClickLeft(){
                this.$router.push({
                    path:'/home_user',
                });

            },

            search(){
                console.log(this.value);
                this.axios.get("http://localhost:8084/findGoodsAndShopsByValue",{
                    params:{
                        value:this.value
                    }
                }).then(resp=>{
                    console.log(resp.data);
                    this.shops = resp.data;
                })
            },

            /*进入店铺界面*/
            toShop(shop){
                console.log("进入店铺界面"+shop.sid);
                this.$router.push({
                    name:'shop',
                    params:{
                        shop:shop
                    }
                })
            },


        }
    }
</script>

<style scoped>

</style>
