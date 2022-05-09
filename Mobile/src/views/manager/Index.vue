<template>
    <el-container>
        <el-header class="mainHeader">
            <div class="header_left">
                <span class="header_title" v-if="userInfo.rid===2">Personal Stores</span>
                <span class="header_title" v-if="userInfo.rid===3">欢迎管理员！！！</span>
                <i :class="FXvalue ? 'el-icon-s-unfold' : 'el-icon-s-fold'" style="font-size: 20px;" @click="ChangeWidth"></i>
            </div>
            <div class="header_right">
                <!-- <div class="search">
                    <i class="el-icon-search"></i>
                </div>
                <div class="notice">
                    <i class="el-icon-bell"></i>
                </div> -->
                <!-- <div class="avatar" style="padding-top: 15px">
                    <el-avatar src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png" class="el-avatar"></el-avatar>
                </div> -->
                <div class="user">
                    <el-dropdown trigger="click">
                        <span class="el-dropdown-link">
                            {{userInfo.username}}<i class="el-icon-arrow-down el-icon--right"></i>
                        </span>
                        <el-dropdown-menu slot="dropdown">
                            <el-dropdown-item ><span @click="loginOut">Log out</span></el-dropdown-item>
                        </el-dropdown-menu>
                    </el-dropdown>
                </div>
            </div>
        </el-header>
        <el-container>
            <!--店铺人员界面-->
            <el-aside :width="Widthvalue ? '25px' : '200px'" v-if="userInfo.rid===2">
                    <el-menu
                            :default-active="activeIndex1"
                            class="el-menu-vertical-demo"
                            @open="handleOpen"
                            @close="handleClose"
                            background-color="#545c64"
                            text-color="#fff"
                            router
                            active-text-color="#ffd04b">

                        <el-submenu index="1" v-if="shopInfo.stat===1">
                            <template slot="title">
                                <i class="el-icon-location"></i>
                                <span>Personal Stores</span>
                            </template>
                            <el-menu-item-group>
                                <template slot="title">Shop situation</template>
                                <el-menu-item index="/dataMessage">Data statistics</el-menu-item>
                                <el-menu-item index="/shopMessage">Store information</el-menu-item>
                            </el-menu-item-group>
                        </el-submenu>

                        <el-submenu index="2" v-if="shopInfo.stat===1">
                            <template slot="title">
                                <i class="el-icon-location"></i>
                                <span>Store management</span>
                            </template>
                            <el-menu-item-group>
                                <el-menu-item index="/goodsManage">
                                    <span slot="title">Dish management</span>
                                </el-menu-item>
                                <el-menu-item index="/categoryManage">
                                    <span slot="title">Category management</span>
                                </el-menu-item>
                            </el-menu-item-group>
                        </el-submenu>

                        <el-submenu index="3" v-if="shopInfo.stat===1">
                            <template slot="title">
                                <i class="el-icon-location"></i>
                                <span>Order management</span>
                            </template>
                            <el-menu-item-group>
                                <el-menu-item index="/newOrder">
                                    <span slot="title">New order</span>
                                </el-menu-item>
                                <el-menu-item index="/oldOrder">
                                    <span slot="title">Received orders</span>
                                </el-menu-item>
                            </el-menu-item-group>
                        </el-submenu>

                        <!--店铺未通过申请显示不可用-->
                        <el-submenu index="1" disabled v-if="shopInfo.stat===0">
                            <template slot="title">
                                <i class="el-icon-location"></i>
                                <span>Personal Stores</span>
                            </template>
                            <el-menu-item-group>
                                <template slot="title">Shop situation</template>
                                <el-menu-item index="/dataMessage">Data statistics</el-menu-item>
                                <el-menu-item index="/shopMessage">Store information</el-menu-item>
                            </el-menu-item-group>
                        </el-submenu>

                        <!--店铺未通过申请显示不可用-->
                        <el-submenu index="2" disabled v-if="shopInfo.stat===0">
                            <template slot="title">
                                <i class="el-icon-location"></i>
                                <span>Store management</span>
                            </template>
                            <el-menu-item-group>
                                <el-menu-item index="/goodsManage">
                                    <span slot="title">Dish management</span>
                                </el-menu-item>
                                <el-menu-item index="/categoryManage">
                                    <span slot="title">Category management</span>
                                </el-menu-item>
                            </el-menu-item-group>
                        </el-submenu>

                        <!--店铺未通过申请显示不可用-->
                        <el-submenu index="3" disabled v-if="shopInfo.stat===0">
                            <template slot="title">
                                <i class="el-icon-location"></i>
                                <span>Order management</span>
                            </template>
                            <el-menu-item-group>
                                <el-menu-item index="/newOrder">
                                    <span slot="title">New order</span>
                                </el-menu-item>
                                <el-menu-item index="/oldOrder">
                                    <span slot="title">Received orders</span>
                                </el-menu-item>
                            </el-menu-item-group>

                        </el-submenu>


                    </el-menu>
            </el-aside>

            <!--管理员界面-->
           <el-aside width="200px" v-if="userInfo.rid===3">
                <el-menu
                        :default-active="activeIndex"
                        class="el-menu-vertical-demo"
                        @open="handleOpen"
                        @close="handleClose"
                        @select="handleSelect"
                        background-color="#545c64"
                        text-color="#fff"
                        router
                        active-text-color="#ffd04b">
                    <el-submenu index="1">
                        <template slot="title">
                            <i class="el-icon-location"></i>
                            <span>店铺管理</span>
                        </template>
                        <el-menu-item-group>
                            <template slot="title">店铺管理</template>
                            <el-menu-item index="/allShops">所有店铺</el-menu-item>
                            <el-menu-item index="/shopApplyFor">店铺申请</el-menu-item>
                        </el-menu-item-group>
                    </el-submenu>
                    <el-submenu index="2">
                        <template slot="title">
                            <i class="el-icon-location"></i>
                            <span>用户管理</span>
                        </template>
                        <el-menu-item-group>
                            <el-menu-item index="/userManager">
                                <span slot="title">用户管理</span>
                            </el-menu-item>
                            <el-menu-item index="/riderManager">
                                <span slot="title">骑手管理</span>
                            </el-menu-item>
                            <el-menu-item index="/shopManagerManager">
                                <span slot="title">店铺管理人员管理</span>
                            </el-menu-item>
                        </el-menu-item-group>
                    </el-submenu>



                </el-menu>
            </el-aside>
            <!--店铺审核通过-->
            <el-main style="text-align: center;padding: 5px;" v-if="shopInfo!=null&&shopInfo.stat===1">
                <router-view/>
            </el-main>
            <!--店铺未审核-->
            <el-main style="text-align: center" v-if="shopInfo!=null&&shopInfo.stat===0">
                <el-empty description="店铺正在审核中，请耐心等待。。。。"></el-empty>
            </el-main>
            <!--管理员-->
            <el-main style="text-align: center;" v-if="shopInfo.stat===null">
                <router-view/>
            </el-main>
        </el-container>
        <el-footer>Footer</el-footer>
    </el-container>
</template>

<script>
    import Toast from "vant/lib/toast";

    export default {
        name: "Index",
        created(){
            // this.userInfo = this.$route.query.userInfo;
            this.userInfo = JSON.parse(localStorage.getItem('Admindata'))
            const that = this;
            console.log(this.userInfo);
            if(this.userInfo.rid===2){
                this.axios.get("http://localhost:8084/findShopByUid",{
                    params:{
                        uid:this.userInfo.smid
                    }
                }).then((resp)=>{
                    that.shopInfo = resp.data;
                    that.$store.commit("SET_SID",resp.data.sid);
                    localStorage.setItem('shopdata', JSON.stringify(resp.data))
                    console.log(that.shopInfo);
                    this.$router.push("/shopMessage")
                })
            }else if(this.userInfo.rid===3){
                this.$router.push("/allShops")
            }


        },

        data(){
            return{
                activeIndex:"/allShops",
                activeIndex1:"/shopMessage",
                userInfo:"",
                shopInfo:{
                    stat:null
                },
                FXvalue: true,
                Widthvalue: true

            }
        },


        methods: {
            ChangeWidth() {
                this.FXvalue = !this.FXvalue
                this.Widthvalue = !this.Widthvalue
            },
            /*点击导航栏携带数据跳转*/
            /*handleSelect(index,indexPath){
                console.log(index);
                this.$router.push({
                    name:index,
                    params:{
                        userInfo:this.userInfo
                    }
                })
            },*/

            handleSelect(key, keyPath) {
                console.log(key, keyPath);
            },

            handleOpen(key, keyPath) {
                console.log(key, keyPath);
            },
            handleClose(key, keyPath) {
                //console.log(key, keyPath);
            },

            //Log out
            loginOut(){
                Toast("Log out");
                if(this.userInfo.rid===2){
                    this.$store.commit("SET_SHOPMANAGERINFO",null);
                    localStorage.removeItem("shopdata");
                    localStorage.removeItem("Admindata");
                    localStorage.removeItem("managerInfo");
                    localStorage.removeItem("shopManagerInfo");
                    localStorage.removeItem("sid");
                    localStorage.removeItem("userInfo");
                    this.$router.push("/toUserLogin")
                }else if(this.userInfo.rid===3){
                    this.$store.commit("SET_MANAGERINFO",null);
                    this.$router.push("/toUserLogin")
                }

            }
        }
    }
</script>

<style scoped>
    .el-header, .el-footer {
        background-color: #B3C0D1;
        color: #333;
        text-align: center;
        line-height: 60px;
    }

    .el-aside {
        background-color: #ffffff;
        color: #333;
        line-height: 300px;
    }

    .el-main {
        background-color: #E9EEF3;
        color: #333;
        text-align: center;
        line-height: 160px;
    }

    .mainHeader {
        display: flex;
        justify-content: space-between;
        align-items: center;
        padding: 0px 20px;
        height: 60px;
    }
    .header_title {
            color: #0b8ee5;
            font-size: 22px;
            font-weight: 600;
    }

        .header_right {
            display: flex;
            align-items: center;
        }
            .search {
                margin-right: 40px;
            }
            .el-icon-search {
                font-size: 18px;
                color: #1a1a1b;
            }

        .notice {
            margin-right: 60px;
        }
            .el-icon-bell {
                font-size: 18px;
                color: #1a1a1b;
            }

        .avatar {
            margin-right: 12px;
        }
            .el-avatar {
            height: 30px;
            width: 30px;
            }

        /*.user {
            cursor: pointer;
        }*/
            .el-dropdown-link {
                color: #1a1a1b;
                font-size: 16px;
                outline: none;
            }



</style>
