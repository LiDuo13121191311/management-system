<template>
    <div>
    <div v-if="showShopInfo">
        <!--返回按钮和搜索按钮、店铺背景-->
        <span style="margin-top: 10px;font-size: 25px; position: absolute;z-index: 10;color: #fff;">
                    <van-icon name="arrow-left" @click="back"/>
        </span>
        <!-- <span style="margin-top: 10px;right: 10px; font-size: 25px; position: absolute;z-index: 10;">
            <van-icon name="search" @click="searchGoods"/>
        </span> -->
        <!-- <van-image width="100%" height="150" :src="shopMessage.logoSrc" style="position: relative"/> -->
        <img class="User_content_swipe" style="width: 100%;height: 150px;" src="../../assets/back.jpg" />

        <!--店面logo和相关信息-->
        <div style="top:80px;left: 10%;width: 80%;height: 100px;box-shadow:rgb(0 0 0 / 50%) 1px 1px 11px 2px;border-radius:10px;background-color: white;position: absolute;z-index: 10">
            <!-- <div style="display: inline-block;float: left">
                <van-image width="130" height="100" :src="shopMessage.logoSrc" round/>
            </div> -->
            <div style="display: inline-block;height: 100px;line-height: 1.2;padding: 13px 0;width: 100%;">
                <h3 style="margin: 0">{{shopMessage.shopName}}</h3>
                <h6 style="margin: 10px 0;">Monthly sale{{shopMessage.totalSales}}</h6>
                <!-- <h6 style="margin: 0">配送时间约{{shopMessage.deliveryTime}}分钟</h6> -->
                <span style="margin: 0;font-size: 5px">Notice:{{shopMessage.desc}}</span>
            </div>
            <!--<div style="width:100px">
                <h5>销量：{{shopMessage.totalSales}}</h5>
            </div>-->
        </div>
        <div style="margin-top: 60px;">
            <!--商品和评价标签页-->
            <van-tabs v-model="activeName" @click="hide(activeName)">
                <van-tab title="order" name="good" style="color: #409EFF;margin-left: 0;">
                    <el-tabs tab-position="left" style="width:100%;margin-top: 0; margin-bottom:0;height: 100%;">
                        <template v-for="(item,index1) in goodList" >
                            <el-tab-pane :label="item.categoryName" type="border-card">
                                <ul>
                                    <li v-for="(good,index2) in item.goods" :key="index2">
                                        <div style="border-radius: 10px;background-color: white;display: flex;">
                                            <div style="display:inline-block;">
                                                <img :src="Phototimg(good.goodPic)" width="100" height="100" alt="">
                                            </div>
                                            <div style="width: 100%;">
                                                <div style="font-size: 18px;font-weight: bold;text-align: center;">{{good.goodName}}</div>
                                                <div style="font-size: 14px;margin: 5px 0;">{{good.desc}}</div>
                                                <div style="font-size: 14px;margin: 5px 0;">Monthly sale: {{good.sales}}</div>
                                                <div style="font-size: 14px;display: flex;justify-content: space-between;padding: 0px 30px;">
                                                    <span style="float: left;color: crimson;display: inline-block">${{good.price}}</span>
                                                    <!--<van-stepper style="margin:10px;display:inline-block;" :default-value="0" theme="round" button-size="15" disable-input @plus="add" @minus="reduce"/>-->
                                                    <template>
                                                        <button style="border-radius: 50%;border: solid 1px" @click="reduce(index1,index2)" v-show="good.count>0">-</button>
                                                        <input type="text" v-model="good.count"  style="border: none;width: 20px;text-align: center" disabled="true" v-show="good.count>0"/>
                                                        <button style="border-radius: 50%;border: solid 1px; left: 0" @click="add(index1,index2)" >+</button>
                                                    </template>
                                                </div>
                                                <!-- <van-divider /> -->
                                            </div>
                                        </div>

                                    </li>
                                </ul>
                            </el-tab-pane>

                        </template>
                    </el-tabs>
                   <!-- <template v-for="item in goodList.category" >
                        <el-tab-pane :label="item.name">
                            <ul v-for="good in item.goods">
                                <li>{{good.name}}</li>
                            </ul>
                        </el-tab-pane>
                    </template>-->
                    <!--<el-tab-pane label="用户管理" lazy>用户管理</el-tab-pane>
                    <el-tab-pane label="配置管理">配置管理</el-tab-pane>
                    <el-tab-pane label="角色管理">角色管理</el-tab-pane>
                    <el-tab-pane label="定时任务补偿">定时任务补偿</el-tab-pane>-->
                </van-tab>

                <!-- <van-tab title="评价" name="comment">


                </van-tab>

                <van-tab title="商家" name="sell">


                </van-tab> -->

            </van-tabs>
            <!--填充-->
            <div style="height:50px;display:block;"></div>
        </div>

        <!--购物车-->
        <div class="shoppingCartN" v-if="shoppingCart">
           <div class="circleN" style="">
               <!--购物车为空时-->
               <div class="carN" style="" v-if="totalCount===null" @click="showSelectedGoods">
                   <van-icon name="shopping-cart" size="30" color="#80858a" :badge="totalCount"/>
               </div>
               <!--购物车不为空时-->
               <div class="carN" style="background-color: #00a0dc" v-if="totalCount > 0" @click="showSelectedGoods">
                   <van-icon name="shopping-cart" size="30" color="white" :badge="totalCount"/>
               </div>
           </div>
            <div style="border-right: 1px solid #51575f;left:55px;width: 80px;height: 30px;display: inline-block;position: absolute;margin: 10px 5px;color: #70757b" v-if="totalPrice===0">
                ${{goodTotalPrice}}
            </div>
            <div style="border-right: 1px solid #51575f;left:55px;width: 80px;height: 30px;display: inline-block;position: absolute;margin: 10px 5px;color: #fdfdfd" v-if="totalPrice>0">
                ${{goodTotalPrice}}
            </div>
            <!-- <div style="left:150px;color: #70757b;font-size: 12px;display:inline-block;position: absolute;margin:12px 0 0 5px;height: 44px">
                另需配送费${{deliveryPrice}}
            </div> -->
            <div style="float: right;width: 80px;">
                <van-button round size="large" style="background-color: #70757b;border-color: #70757b;font-size: 12px;margin-top: 5px;height: 40px" v-if="this.goodTotalPrice===0">Please place an order</van-button>
                <!-- <van-button round size="large" style="color: white;background-color: #70757b;border-color: #70757b;font-size: 12px;margin-top: 5px;height: 40px" v-if="this.goodTotalPrice < this.minPrice">请下单</van-button> -->
                <van-button round type="info" size="large" style="font-size: 12px;margin-top: 5px;height: 40px" v-if="this.goodTotalPrice > 0" @click="toPay">Go to settlement</van-button>
            </div>
        </div>

        <!--购物车详情-->
        <el-drawer class="User_drawer"
                :visible.sync="isShowSelectedGoods"
                direction="btt"
                :show-close="false"
                :with-header="false"
                :size="400">
                <div class="User_drawer_list">
                    <div style="text-align: left">
                        <van-tag color="#ffe1e1" text-color="#ad0000" ><span style="margin:10px;"> <van-icon name="cart" />Selected items</span></van-tag>
                    </div>
                    <div>
                <template>
                        <ul >
                            <li v-for="(good,index) in selectedGoods" :key="index">
                                <div style="border-radius: 10px;background-color: white">
                                    <div style="display:inline-block;float: left">
                                        <img :src="Phototimg(good.goodPic)" width="60" height="60" alt="">
                                    </div>
                                    <div>
                                        <h4 style="width: 160px;margin-bottom: 2px;margin-top: 10px">{{good.goodName}}</h4>
                                        <div style="width: 100%;font-size: 15px;margin-top: 20px;height: 20px">
                                            <span style="float: left;color: crimson;display: inline-block;margin-left: 20px">${{good.price*good.count}}</span>
                                            <div style="float: right;margin-right:10px">
                                                <button style="border-radius: 50%;border: solid 1px" @click="reduceCar(index)" v-show="good.count>0">-</button>
                                                <input type="text" v-model="good.count"  style="border: none;width: 20px;text-align: center" disabled="true" v-show="good.count>0"/>
                                                <button style="border-radius: 50%;border: solid 1px; left: 0" @click="addCar(index)" >+</button>
                                            </div>
                                        </div>
                                        <van-divider />
                                    </div>
                                </div>

                            </li>
                        </ul>

                </template>
            <!--填充-->
            <div style="height:50px;display:block;"></div>
        </div>
                </div>
            
        
        <div>

        </div>
        </el-drawer>


        <!--遮挡板-->
        <div style="width:100%; height: 50px;bottom: 0; position: fixed;background-color: #ffffff; z-index: 100" v-if="hider">

        </div>

    </div>

        <!--确认订单页面-->
        <div style="background-color:white;z-index: 99999;float:left;width: 100%;" v-if="showPay">
            <van-nav-bar
                    title="Confirm order"
                    left-text="Back"
                    left-arrow
                    @click-left="isShowPay"
            />
            <van-notice-bar
                    left-icon="volume-o"
                    text="Save food, start from me, start from now on. Please order in moderation to avoid waste"
            />
            <!-- <div style="width: 300px;;margin: 10px;border-radius: 10px;border: solid 1px">
                <div style="width: 300px;margin-bottom: 5px;">
                    <span v-if="addressMessage===null"><h3 style="display:inline-block;margin-bottom:0;margin-top: 5px;margin-left: 5px;margin-right: 10px">请选择收货地址</h3></span>
                    <span v-if="addressMessage!==null">
                        <div style="font-size: 13px;width: 150px;display: inline-block;">
                            <div>
                                {{addressMessage.address}}
                            </div>
                            <div>
                                <span>
                                    {{addressMessage.name}}
                                </span>
                                    {{addressMessage.tel}}
                                <span>
                            </span>
                            </div>
                        </div>

                    </span>
                    <span style="margin-left: 120px;"><van-icon name="arrow" @click="chooseAddress"/></span>
                </div>
                <div style="width: 300px;height: 40px;margin: 0;">
                    <span><h4 style="display:inline-block;margin-bottom:0;margin-top: 5px;margin-left: 5px;margin-right: 50px">预计送达时间</h4></span>
                    <span style="margin-left: 50px;"><van-icon name="clock-o" />12:50 <van-icon name="arrow" @click="chooseTime"/></span>

                </div>
                <div style="width: 300px;height: 40px;margin: 0;">
                    <span><h4 style="display:inline-block;margin-bottom:0;margin-top: 5px;margin-left: 5px;margin-right: 50px">支付方式</h4></span>
                    <span style="margin-left: 100px;">微信<van-icon name="arrow" @click="choosePayType"/></span>

                </div>

            </div> -->

            <div style="margin: 10px;border-radius: 10px;padding: 5px;">
                <template>
                    <ul>
                        <li v-for="(good,index) in selectedGoods" :key="index">
                            <div style="border-radius: 10px;background-color: white">
                                <div style="display:inline-block;float: left">
                                    <img :src="Phototimg(good.goodPic)" width="60" height="60" alt="">
                                </div>
                                <div>
                                    <h4 style="width: 160px;margin-bottom: 2px;margin-top: 10px">{{good.goodName}}</h4>
                                    <div style="width: 100%;font-size: 15px;margin-top: 20px;height: 20px">
                                        <span style="float: left;color: crimson;display: inline-block;margin-left: 20px">×{{good.count}}</span>
                                        <div style="float: right;margin-right:10px">
                                            <span style="float: left;color: crimson;display: inline-block;margin-left: 20px">${{good.price*good.count}}</span>
                                        </div>
                                    </div>
                                    <van-divider />
                                </div>
                            </div>
                        </li>
                    </ul>
                    <!-- <div>
                        <h4 style="width: 50px;margin-left: 10px;display: inline-block;margin-right: 180px;margin-top: 5px;margin-bottom: 5px">配送费</h4>
                        <span>${{deliveryPrice}}</span>
                    </div> -->
                    <div style="text-align: start;">
                        <div style="font-size: 18px;font-weight: bold;margin: 20px 0;">Select payment method</div>
                        <el-radio-group v-model="radio">
                            <div class="User_group_style">
                                <el-radio :label="3">
                                    <img style="width: 150px;height: 50px;" src="../../assets/bank1.png" />
                                </el-radio>
                            </div>
                            <div class="User_group_style">
                                <el-radio :label="6">
                                    <img style="width: 150px;height: 50px;" src="../../assets/bank2.png" />
                                </el-radio>
                            </div>
                            <div class="User_group_style">
                                <el-radio :label="9">
                                    <img style="width: 150px;height: 50px;" src="../../assets/bank3.png" />
                                </el-radio>
                            </div>
                        </el-radio-group>
                    </div>
                </template>
            </div>
            <!--填充-->
            <div style="height:50px;display:block;"></div>

            <!--总价格+结算按钮-->
            <div style="height: 50px;bottom: 0; position: fixed;background-color: #409EFF; z-index: 100">
                <van-submit-bar :price="(goodTotalPrice*100)" button-text="Immediate payment" @submit="submit" button-type="info"/>
            </div>
        </div>

        <!--选择收货地址页面-->
        <div v-if="showAddress">
            <div>
                <slot><van-nav-bar
                        title="地址列表"
                        left-text="返回"
                        left-arrow
                        @click-left="backPay"
                /></slot>
                <van-address-list
                        v-model="chosenAddressId"
                        :list="addressList"
                        @add="onAdd"
                        @select="onSelect"
                />
            </div>
        </div>

        <!--添加收货地址页面-->
        <div v-if="showAddAddress">
            <slot><van-nav-bar
                    title="新的地址"
                    left-text="返回"
                    left-arrow
                    @click-left="backChooseAddress"
            /></slot>
            <van-address-edit
                    :area-list="areaList"
                    show-set-default
                    :area-columns-placeholder="['请选择', '请选择', '请选择']"
                    @save="onSave"
            />
            <!--遮挡板-->
            <div style="width:100%; height: 50px;bottom: 0; position: fixed;background-color: #ffffff; z-index: 100" v-if="showAddAddress">

            </div>
            <!--:search-result="searchResult"
            :search-result="searchResult"-->
        </div>


        <!--付款页面-->
       <!-- <div v-if="showPayMoney">
            <slot><van-nav-bar
                    title="付款页面"
                    left-text="返回"
                    left-arrow
                    @click-left="backPay"
            /></slot>
            &lt;!&ndash;遮挡板&ndash;&gt;
            <div style="width:100%; height: 50px;bottom: 0; position: fixed;background-color: #ffffff; z-index: 100" v-if="showPayMoney">

            </div>
        </div>-->
    </div>

</template>
<style lang="scss" scoped>
.User_group_style {
    .el-radio  {
        display: flex;
        align-items: center;
    }
}
.User_drawer {
      width: 100%;
      margin-left: 0;
      z-index: 10;
      .el-drawer__body {
          padding: 10px !important;
      }
      }
.User_drawer_list {
    padding: 10px;
}
    /*购物车为空时*/
    .shoppingCartN{
        width:100%;
        height: 50px;
        bottom: 0;
        position: fixed;
        background-color:#141d27;
        z-index: 9999;
    }
    .circleN{
        margin-left:10px; width: 60px;height: 60px; background-color: #141d27;text-align: center;border-radius: 50%;position: absolute;bottom: 0
    }
    .carN{
        margin-top: 10px;background-color: #2b343c;border-radius: 50%;width: 40px;height: 40px;margin-left: 10px
    }


    .minPriceDesc {
        font-size: 12px;
        font-weight: 700;
        line-height: 48px;
    }
    .not-enough {
        background: #2b333b;
    }
    .enough{
        background:#00b43c;
        color:#fff
    }

</style>
<script>

    import Toast from "vant/lib/toast";
    import { Dialog } from 'vant';
    import { areaList } from '@vant/area-data';

    export default {
        name: "Shop",

        created(){
          console.log("进入店铺"+this.$route.params.shop.sid);
          this.axios.get("http://localhost:8084/findAllGoodsBySid",{
              params:{
                  sid: this.$route.params.shop.sid
              }
          }).then(resp=>{
              //console.log(resp.data)
              this.goodList = resp.data;
          });
          this.axios.get("http://localhost:8084/findShopBySid",{
              params:{
                  sid: this.$route.params.shop.sid
              }
          }).then(resp=>{
              this.shopMessage = resp.data;
              this.minPrice = resp.data.minCost;
              this.deliveryPrice = resp.data.deliveryCost;
              console.log("店铺信息："+JSON.stringify(resp.data))
          })
        },
        computed:{
            onChange() {},
            /*计算商品总价格*/
            goodTotalPrice(){
                let goodTotalPrice = 0;
                this.goodList.forEach(function (item) {
                    item.goods.forEach(function (good) {
                        goodTotalPrice+= good.count*good.price;
                    })
                });
                return goodTotalPrice;
            },

            /*存放在购物车里的商品*/
            selectedGoods() {
                //存放good
                let goods = [];
                this.goodList.forEach(function (item) {
                    item.goods.forEach(function (good) {
                        if(good.count>0){
                            goods.push(good);
                        }
                    })
                });
                return goods;
            },


        },
        data(){
            return{
                /*存储订单信息*/
                /*uid:this.uid,
                    sid:this.shopId,
                    totalPrice:this.totalPrice,
                    goods:this.selectedGoods,
                    addressMessage:this.addressMessage,
                    deliveryPrice:this.deliveryPrice,*/
                orderInfo:{
                    uid:'',
                    sid:'',
                    goodTotalPrice:'',
                    deliveryPrice: '',
                    totalPrice:'',
                    goods:'',
                    addressMessage:'',
                    shopName:''
                },
                radio: 3,
                /*显示店铺主页面*/
                showShopInfo:true,
                showPayMoney:false,

                /*所选择的收货地址*/
                addressMessage: {
                    address: '1230',
                    name: '11111',
                    tel: '15212141214'
                },

                /*默认显示请选择收货地址*/
                showChooseAddress:true,
                areaList,
                showAddAddress:false,
                showAddress:false,
                showPay:false,

                /*用来保存新增的address信息*/
                addressInfo:{
                    /*(aId=0, areaCode=null, name=null, tel=null, addressDetail=null, province=null, city=null, county=null, uid=1)*/
                    uid: this.$store.getters.getUser.id,
                    areaCode:"",
                    name:"",
                    tel:"",
                    addressDetail:"",
                    province:"",
                    city:"",
                    county:""
                },

                /*总商品个数*/
                totalCount:null,

                /*多少元起送*/
                minPrice:0,

                /*当前总价：商品总价+配送费*/
                totalPrice:0,

                /*配送费*/
                deliveryPrice:0,

                shoppingCart:true,
                count:0,
                hider:false,
                activeKey:"a",
                activeName:'good',
                shopId:this.$route.params.shop.sid,
                //当前登录用户id
                uid:this.$store.getters.getUser.id,
                isShowSelectedGoods:false,
                chosenAddressId: 0,
                createTime:'',
                /*店铺信息*/
                shopMessage:'',

                addressList: [
                    {
                        id: '1',
                        name: '张三',
                        tel: '13000000000',
                        areaCode:'130105',
                        address: '浙江省杭州市西湖区文三路 138 号东方通信大厦 7 楼 501 室',
                        isDefault: true,
                    },
                    {
                        id: '2',
                        name: '李四',
                        tel: '1310000000',
                        address: '浙江省杭州市拱墅区莫干山路 50 号',
                    },
                ],

                /*商品列表*/
                goodList: [
                        {
                            categoryName:"优惠套餐",
                            goods:[
                                {
                                    gid:'',
                                    goodName:"法式汉堡",
                                    desc:'法国现做汉堡',
                                    goodPic:'pic/dinner.png',
                                    price:'1',
                                    sales:'100',
                                    cid:'',
                                    sid:'',
                                    count:0
                                }
                            ]
                        }/*,
                        {
                            categoryName:"中式套餐",
                            goods:[
                                {
                                    name:"全家桶1",
                                    desc:'法国现做汉堡',
                                    pic:'pic/dinner.png',
                                    price:'5',
                                    salesMonth:'100',
                                    count: 0
                                }
                            ]
                        },
                        {
                            categoryName:"风味小吃",
                            goods:[
                                {
                                    name:"肉串1",
                                    desc:'法国现做汉堡',
                                    pic:'pic/dinner.png',
                                    price:'7',
                                    salesMonth:'100',
                                    count: 0
                                }
                            ]
                        },
                        {
                            categoryName:"烧烤",
                            goods:[
                                {
                                    name:"骨肉相连1",
                                    desc:'法国现做汉堡',
                                    pic:'pic/dinner.png',
                                    price:'5',
                                    salesMonth:'100',
                                    count: 0
                                }
                            ]
                        }*/
                    ]
                }
        },

        methods:{
            // 图片转换
            Phototimg(src) {
            const a = 'http://localhost:8084/' + src
            console.log(a)
            return a
            },
            /*提交订单,显示支付中加载框*/
            submit(){
                // if(this.addressMessage === null){
                //     Toast("请选择收货地址");
                //     return;
                // }
                const that = this;
                const orderInfo = this.orderInfo;
                this.orderInfo.uid = that.uid;
                this.orderInfo.sid = that.shopId;
                this.orderInfo.goodTotalPrice = that.goodTotalPrice;
                this.orderInfo.deliveryPrice = that.deliveryPrice;
                this.orderInfo.totalPrice = (that.deliveryPrice*1.0+that.goodTotalPrice*1.0);
                this.orderInfo.addressMessage = that.addressMessage;
                this.orderInfo.shopName = that.shopMessage.shopName;
                this.orderInfo.goods = that.selectedGoods;
                console.log(this.orderInfo);

                //确认提示
                Dialog.confirm({
                    title: 'Confirm to use this payment method',
                    message: 'Confirm payment?',
                }).then(() => {
                        // on confirm
                        //Toast("提交订单");
                        const toast = Toast.loading({
                            duration: 2000,
                            forbidClick: true,
                            message: "Payment in progress..."
                        });
                        setTimeout(()=>{
                            /*将订单信息发给后端*/
                            this.axios.post("http://localhost:8084/createOrder",this.orderInfo)
                                .then(resp=>{
                                    if(resp.data !== null){
                                        console.log(resp.data.orderNumber);
                                        this.$router.push({
                                                name: "paySuccess",
                                                params:{
                                                    oid:resp.data.oid,
                                                    orderNumber:resp.data.orderNumber,
                                                    goodTotalPrice:resp.data.goodTotalPrice,
                                                    deliveryPrice:resp.data.deliveryPrice,
                                                    totalPrice:resp.data.totalPrice,
                                                    goods:this.selectedGoods,
                                                    addressMessage:this.addressMessage,
                                                    shopName:this.shopMessage.shopName,
                                                    orderStat:resp.data.orderStat,
                                                    createTime:resp.data.createTime
                                                }
                                            });
                                    }else {
                                        Toast("Failed to create order due to network or other reasons")
                                    }
                        })
                        },2000);
                });

            },

            /*选择收货地址*/
            onSelect(item){
                console.log(item);
                this.addressMessage = item;
                console.log(this.addressMessage);
                this.showAddress = false;
                this.showPay = true;
            },

            /*新增收货地址*/
            onSave(item){
                /*let addressInfo = JSON.stringify(item);
                console.log(addressInfo);
                Toast(addressInfo);*/
                this.addressInfo.areaCode = item.areaCode;
                this.addressInfo.name = item.name;
                if(this.addressInfo.province===this.addressInfo.city){
                    this.addressInfo.addressDetail = item.province+""+item.county+""+item.addressDetail;
                }else {
                    this.addressInfo.addressDetail = item.province+""+item.city+""+item.county+""+item.addressDetail;
                }
                this.addressInfo.tel = item.tel;
                this.addressInfo.province = item.province;
                this.addressInfo.city = item.city;
                this.addressInfo.county = item.county;
                console.log(this.addressInfo.addressDetail);
                //console.log(this.addressInfo);
                this.axios.post("http://localhost:8084/addAddress",
                    this.addressInfo
                ).then(resp=>{
                    console.log("sucess");
                    Toast("Added successfully!!!");
                    setTimeout(()=>{
                        this.showAddAddress = false;
                        this.chooseAddress();
                    },2000);

                })
            },

            /*添加收货地址页面*/
            onAdd(){
                this.showAddAddress = true;
                this.showAddress = false;
            },

            /*返回选择收货地址*/
            backChooseAddress(){
              this.showAddress = true;
              this.showAddAddress = false;
            },

            /*返回订单界面*/
            backPay(){
                this.showPayMoney = false;
                this.showAddress = false;
                this.showPay = true;
            },

            /*点击选择收货地址，获取所有收货地址*/
            chooseAddress(){
              //Toast("选择收货地址");
              const that = this;
              this.axios.get("http://localhost:8084/listAddress",{
                  params:{
                      uid:this.uid
                  }
              }).then(resp=>{
                      console.log(resp.data);
                      this.addressList = resp.data;
                      this.showPay = false;
                      this.showAddress = true;
              });

            },

            /*选择送达时间*/
            chooseTime(){
                Toast("选择送达时间")
            },

            /*选择支付方式*/
            choosePayType(){
                Toast("选择支付方式")
            },

            /*返回店铺主页*/
            isShowPay(){
              this.showPay=false;
              this.showShopInfo = true;
            },

            /*显示购物车详情*/
            showSelectedGoods(){
                this.isShowSelectedGoods = true;
            },

            /*在购物车详情页面进行商品的修改*/
            addCar(index){
                this.selectedGoods[index].count++;
                this.totalCount++;
            },


            reduceCar(index){
                this.selectedGoods[index].count--;
                this.totalCount--;
            },


            /*Go to settlement界面*/
            toPay(){
                console.log("进入结算界面");
                this.showPay=true;
                this.showShopInfo = false;

                /*this.goodList.category.forEach(function (item) {
                    item.goods.forEach(function (good) {
                        if(good.count>0){
                            console.log(good)
                        }
                    })
                });*/
                /*console.log(this.selectedGoods);*/
                //this.$router.push({path:'/pay'});
            },


            /*遮盖*/
            hide(index){
              if(index==='good'){
                  this.shoppingCart = true;
                  this.hider = false;
                  console.log("进入点餐界面")
              }else if(index==='comment'){
                  this.shoppingCart = false;
                  this.hider = true;
                  console.log("进入评论界面")
              }else if(index==='sell'){
                  this.shoppingCart = false;
                  this.hider = true;
                  console.log("进入商家界面")
              }
            },


            /*点击添加按钮*/
            add(index1,index2){
                let totalCount = null;
                const good = this.goodList[index1].goods[index2];
                good.count++;
                this.totalCount ++;
                //Toast(this.goodList.category[index1].goods[index2].count);
            },

            /*点击减少按钮*/
            reduce(index1,index2){
                const good = this.goodList[index1].goods[index2];
                good.count--;
                this.totalCount--;
                if(this.totalCount===0){
                    this.totalCount = null;
                }
                //Toast(this.goodList.category[index1].goods[index2].count);

            },

            /*返回上一页*/
            back(){
                //this.$router.push('../views/user/Search.vue')
                //this.$router.go(-1);
                //this.$router.replace({name:'search'})
                history.go(-1)
            },

            showItem(){
              console.log(this.goodList[0])
            },

            searchGoods(){
              console.log("搜索")
            },
            /*购物车结算状态*/
            payDesc() {
                // if (this.totalPrice === 0) {
                //     return `$${this.minPrice}元起送`;
                // } else if (this.totalPrice < this.minPrice) {
                //     let differPrice = this.minPrice - this.totalPrice;
                //     return `还差${differPrice}元起送`;
                // } else if (this.totalPrice >= this.minPrice) {
                //     return 'Go to settlement';
                // }
                if (this.totalPrice === 0) {
                    return `Please order`;
                } else if (this.totalPrice >= this.minPrice) {
                    return 'Go to settlement';
                }
            },

        }
    }
</script>


