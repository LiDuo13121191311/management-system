<template>

</template>

<script>
    export default {
        name: "Blank",
        created() {
            this.$router.push({name:''+this.$route.params.url})
        }
    }
</script>

<style scoped>

</style>
