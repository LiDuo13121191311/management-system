<template>
    <!-- <van-swipe :autoplay="3000">
        <van-swipe-item v-for="(item, index) in images" :key="index">
            <img :src="item.src" class="User_content_swipe" />
        </van-swipe-item>
    </van-swipe> -->
    <img class="User_content_swipe" style="width: 58%;height: 300px;" src="../../assets/back.jpg" />
    <!-- <el-carousel class="User_content_swipe" :interval="4000" type="card" height="200px">
        <el-carousel-item v-for="item in images" :key="item">
          <img :src="item.src" />
          <el-image :src="item.src"></el-image>
        </el-carousel-item>
    </el-carousel> -->
</template>

<script>
    export default {
        name: "Swipe",
        data() {
            return {
                images: [
                    { src: '../../assets/steak.png' },
                    { src: '../../assets/sale.webp' },
                    { src: '../../assets/Pizza.png' },
                    { src: '../../assets/pasta.png' },
                    { src: '../../assets/Cake.jpg' }
                ],
            };
        }
    }
</script>

<style lang="scss" scoped>
.User_content_swipe {
  max-width: 100%;
  width: 58%;
  height: 300px;
}

@media screen and (max-width: 450px) {
  // 这里写对应要修改的样式如：
  .User_content_swipe {
    max-width: 100%;
    width: 100%;
    height: 200px;
  }
}
</style>
