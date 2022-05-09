<template>
    <div>
        <span @click="back">返回</span>
        <h1>确认订单</h1>
    </div>

</template>

<script>
    export default {
        name: "Pay",
        methods:{
            back(){
                history.go(-1)
            }
        }
    }
</script>

<style scoped>

</style>
