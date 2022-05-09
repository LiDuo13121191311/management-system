<template>
    <div style="width: 100%;height: auto;line-height: 2;">
        <div style="line-height: 20px;">
            <h2 style="margin: 10px;">Store data statistics</h2>
        </div>
        <div style="margin:5px;display:inline-block">
            <h1 style="margin: 0;line-height: 20px;">Store order quantity</h1>
            <div id="chart_example1" style="height: 200px;">

            </div>
        </div>
        <div style="display:inline-block">
            <h1 style="margin: 0;line-height: 20px;">Commodity sales</h1>
            <div id="chart_example2" style="height: 200px;">

            </div>
        </div>

    </div>
</template>

<script>
    import * as echarts from 'echarts';
    export default {
        created(){
            this.sid = this.$store.getters.getSid;
            this.axios.get("http://localhost:8084/getGoodData",{
                params:{
                    sid:this.sid
                }
            }).then(resp=>{
                let goods = resp.data.goods;
                //存储数据
                let goodData = [];
                goods.forEach(function (item) {
                    let obj = {};
                    obj.name = item.name;
                    obj.value = item.value;
                    goodData.push(obj);
                });
                this.goodData = goodData;
                this.bingGraph();
            })
        },
        data() {
            return {
                sid:'',
                goodData:[],
                new_data1: ['January', 'February', 'March', 'April', 'May','June', 'July', 'August', 'September', 'October','November', 'December'],
                new_data2: [500, 666, 444, 858, 654,500, 666, 444, 858, 654,500, 666]
            }
        },
        methods: {
            /*构建饼状图*/
            bingGraph(){
                let goodData = this.goodData;
                let this_ = this;
                let myChart1 = echarts.init(document.getElementById('chart_example1'));
                let myChart2 = echarts.init(document.getElementById('chart_example2'));
                let option1 = {
                    color: ['#0b8ee5'],
                    tooltip: {
                        trigger: 'axis',
                        axisPointer: {
                            type: 'shadow'
                        }
                    },
                    xAxis: [
                        {
                            type: 'category',
                            data: this_.new_data1,
                            axisTick: {
                                alignWithLabel: true
                            }
                        }
                    ],
                    yAxis: [
                        {
                            type: 'value'
                        }
                    ],
                    series: [
                        {
                            name: '每月花费',
                            type: 'bar',
                            barWidth: '60%',
                            //data:[995,666,444,858,654,236,645,546,846,225,547,356]
                            data: this_.new_data2
                        }
                    ]
                };
                let option2 = {
                    tooltip: {
                        trigger: 'item'
                    },
                    legend: {
                        top: '1%',
                        left: 'center'
                    },
                    series: [
                        {
                            name: '销售量',
                            type: 'pie',
                            radius: ['40%', '70%'],
                            //roseType: 'rose',
                            avoidLabelOverlap: false,
                            itemStyle: {
                                borderRadius: 10,
                                borderColor: '#fff',
                                borderWidth: 2
                            },
                            label: {
                                show: false,
                                position: 'center'
                            },
                            emphasis: {
                                label: {
                                    show: true,
                                    fontSize: '20',
                                    fontWeight: 'bold'
                                }
                            },
                            labelLine: {
                                show: false
                            },
                            data: goodData
                        }
                    ]
                };
                myChart1.setOption(option1);
                myChart2.setOption(option2);

                //建议加上以下这一行代码，当浏览器窗口缩小的时候,红色边框和图表的相对位置不变
                window.addEventListener('resize', function () {
                    myChart1.resize()
                });
                window.addEventListener('resize', function () {
                    myChart2.resize()
                });
            }
        },
        watch: {},
    }
</script>

<style scoped>
    /*h2{
        text-align: center;
        padding: 30px;
        font-size: 18px;
    }*/
    #chart_example1{
        width: 300px;
        height: 300px;
        /*border: 1px solid #0b8ee5;
        border-radius: 15px;*/
        margin: 0 auto;
    }
    #chart_example2{
        width: 400px;
        height: 300px;
        /*border: 1px solid #0b8ee5;
        border-radius: 15px;*/
        margin: 0 auto;
    }
</style>
