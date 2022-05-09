<template>
    <div>
        <slot><van-nav-bar
                title="地址编辑"
                left-text="返回"
                left-arrow
                @click-left="onClickLeft"
        /></slot>
        <van-address-edit
                    :area-list="areaList"
                    :address-info="addressInfo"
                    show-delete
                    show-set-default

                    save-button-text="保存修改"
                    @save="onSave"
                    @delete="onDelete"
            />
        <!--遮挡板-->
        <div style="width:100%; height: 50px;bottom: 0; position: fixed;background-color: #ffffff; z-index: 100">

        </div>
        <!--显示搜索结果-->
        <!--:search-result="searchResult"-->
        <!--show-search-result-->
        <!--@change-detail="onChangeDetail"-->
    </div>

</template>

<script>
    /*引入Toast*/
    import Toast from 'vant/lib/toast';
    import { areaList } from '@vant/area-data';

    export default {
        name: "AddressEdit",
        created(){
            let data = JSON.parse(this.$route.params.addressMessage);
            //console.log(data);
            this.addressInfo = data;
            let index = data.address.indexOf("区");
            if(index<0) index = data.address.indexOf("县");
            this.addressInfo.addressDetail = data.address.substring(index+1);
        },
        data() {
            return {
                // detailAddress:this.$route.params.addressMessage.address.substring(this.addressM.indexOf("区")+1),
                // addressM:this.$route.params.addressMessage.address,
                addressInfo:null,
                /*area:[
                    this.addressM.substring(0,this.addressM.indexOf("省")+1),
                    this.addressM.substring(this.addressM.indexOf("省")+1,this.addressM.indexOf("市")+1),
                    this.addressM.substring(this.addressM.indexOf("市")+1,this.addressM.indexOf("区")+1),
                    ],*/
                areaList,
            };
        },
        methods: {
            onClickLeft(){
              this.$router.push('/address')
            },
            /*private Integer aId;
    private String areaCode;
    private String name;
    private String tel;
    private String address;
    private String province;
    private String city;
    private String county;*/
            onSave(item) {
                console.log(item);
                const address = item;
                console.log(address);
                this.axios.put("http://localhost:8084/updateAddressById",address).then(resp=>{
                        if(resp.data==="success"){
                            Toast("修改成功");
                        }else {
                            Toast("修改失败")
                        }
                    this.$router.push("/address")
                })
            },
            onDelete() {
                Toast('delete');
            },
            /*onChangeDetail(val) {
                if (val) {
                    this.searchResult = [
                        {
                            name: '黄龙万科中心',
                            address: '杭州市西湖区',
                        },
                    ];
                } else {
                    this.searchResult = [];
                }
            },*/
        },
    }
</script>

<style scoped>

</style>
