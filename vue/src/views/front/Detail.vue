<template>
  <div class="main-content">
    <div style="width: 60%; background-color: white; min-height: 1000px; margin: 20px auto; border-radius: 20px">
      <div style="padding: 15px 20px">
        <el-row :gutter="20">
          <el-col :span="12">
            <img :src="goodsData.img" alt="" style="width: 100%; height: 400px; border-radius: 20px">
          </el-col>
          <el-col :span="12">
            <div style="font-size: 20px; font-weight: 900; overflow: hidden; text-overflow: ellipsis; display: -webkit-box; -webkit-line-clamp: 2; -webkit-box-orient: vertical;">{{ goodsData.name }}</div>
            <div style="color: #666666FF; font-size: 14px; margin-top: 5px">销量：{{ goodsData.count }}</div>
            <div style="color: red; margin-top: 15px; font-size: 14px">成交价：<span style="font-size: 20px;">{{ goodsData.price }} / {{ goodsData.unit }}</span></div>
            <div style="margin-top: 20px">
              <img src="@/assets/imgs/right.png" alt="" style="width: 70%; height: 130px; border-radius: 15px">
            </div>
            <div style="color: #666666FF; font-size: 14px; margin-top: 20px">商家：{{goodsData.businessId}}</div>
            <div style="color: #666666FF; font-size: 14px; margin-top: 20px">分类：{{goodsData.typeId}}</div>
            <div style="color: #666666FF; margin-top: 20px">
              <el-button type="warning">加入购物车</el-button>
              <el-button type="warning">收藏</el-button>
            </div>
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
    let goodsId = this.$route.query.id
    return {
      user: JSON.parse(localStorage.getItem('xm-user') || '{}'),
      goodsId: goodsId,
      goodsData: {}
    }
  },
  // DOM元素渲染之后
  mounted() {
    // console.log("adfasffffffffffffffffffffffffffffff");
    // console.log(this.$route.query);
    // console.log(this.$router.query.id);
    this.loadGoods()
  },
  // methods：本页面所有的点击事件或者其他函数定义区
  methods: {
    // 测试版
    // loadGoods() {
    //   this.$request.get('http://127.0.0.1:4523/m2/5319188-4989426-default/224812492').then(res => {
    //     if (res.code === '200') {
    //       this.goodsData = res.data
    //     } else {
    //       this.$message.error(res.msg)
    //     }
    //   })
      // 正式版
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


