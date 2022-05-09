<template>
    <div>
        <slot><van-nav-bar
                title="地址列表"
                left-text="返回"
                left-arrow
                @click-left="onClickLeft"
        /></slot>
        <div v-if="list.length === 0">
            <van-empty description="您还没有添加地址噢，点击新增地址添加吧~~" />
        </div>
        <van-address-list
                v-model="chosenAddressId"
                :list="list"
                :disabled-list="disabledList"
                disabled-text="以下地址超出配送范围"
                default-tag-text="默认"
                @add="onAdd"
                @edit="onEdit"
        />
    </div>

</template>

<script>
    /*引入Toast*/
    import Toast from 'vant/lib/toast';

    export default {
        name: "Address",
        created(){
            console.log(this.$store.getters.getUser.id);
            this.axios.get("http://localhost:8084/listAddress",{
                params:{
                    uid: this.$store.getters.getUser.id
                }
            }).then(resp=>{
                console.log(resp.data);
                this.list = resp.data
            })
        },
        data() {
            return {
                chosenAddressId: 0,
                list: [
                    /*{
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
                    },*/
                ],
                disabledList: [
                    {
                        id: '3',
                        name: '王五',
                        tel: '1320000000',
                        address: '浙江省杭州市滨江区江南大道 15 号',
                    },
                ],
            };
        },
        methods: {
            onAdd() {
                //Toast('新增地址');
                this.$router.push('/addressAdd')
            },
            onEdit(item, index) {
                //Toast('编辑地址:' + index);
                //console.log(item)
                let data = JSON.stringify(item);
                this.$router.push({
                    name:'addressEdit',
                    params:{
                        addressMessage:data
                    }
                })
            },
            onClickLeft(){
                this.$router.push('/my_user');
            }
        },
    }
</script>

<style scoped>

</style>
