<template>
    <!-- <van-swipe :autoplay="3000">
        <van-swipe-item v-for="(image, index) in images" :key="index">
            <img v-lazy="image" class="User_content_swipe" />
        </van-swipe-item>
    </van-swipe> -->
    <img class="User_content_swipe" style="width: 108%;height: 200px;" src="../../assets/back.jpg" />
</template>

<script>
    export default {
        name: "Swipe",
        data() {
            return {
                images: [
                    /*'/upload/p1.png',
                    '/upload/p2.png',
                    '/upload/p3.png',
                    '/upload/p4.png',*/
                    '/upload/p5.png',
                    '/upload/p6.png',
                    '/upload/p7.png',

                ],
            };
        }
    }
</script>

<style lang="scss" scoped>
.User_content_swipe {
  max-width: 100%;
  width: 78%;
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
