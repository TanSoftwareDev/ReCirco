<template>
  <div class="main-content">
    <div style="width: 60%; background-color: white; min-height: 1000px; margin: 20px auto; border-radius: 20px">
      <div style="padding: 15px 20px">
        <el-row :gutter="20">
          <el-col :span="12">
            <img :src="goodsData.img" alt="" style="width: 100%; height: 400px; border-radius: 20px">
          </el-col>
          <el-col :span="12">
            <div>{{ goodsData.name }}</div>
            <div>销量：{{ goodsData.count }}</div>
            <div>疯抢价：{{ goodsData.price }} / {{ goodsData.unit }}</div>
            <div>
              <img src="@/assets/imgs/right.png" alt="">
            </div>
            <div></div>
            <div></div>
            <div></div>
          </el-col>
        </el-row>
      </div>
      <div></div>
    </div>
  </div>
</template>

<script>

export default {

  data() {
    let goodsId = this.$router.query.id
    return {
      user: JSON.parse(localStorage.getItem('xm-user') || '{}'),
      goodsId: goodsId,
      goodsData: {}
    }
  },
  // DOM元素渲染之后
  mounted() {
    this.loadGoods()
  },
  // methods：本页面所有的点击事件或者其他函数定义区
  methods: {
    loadGoods() {
      this.$request.get('/goods/selectById?id=' + this.goodsId).then(res => {
        if (res.code === '200') {
          this.goodsData = res.data
        } else {
          this.$message.error(res.msg)
        }
      })
    }
  }
}
</script>


