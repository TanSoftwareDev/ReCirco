<template>
  <div class="main-content">
    <div style="height: 60px; background-color: #C566F6FF"></div>
    <div style="display: flex">
      <div class="left"></div>
      <div style="width: 66%; height: 1000px; background-color: white">
        <div style="color: #FB0137FF; margin: 15px 0 15px 18px; font-weight: bold; font-size: 16px">主题市场</div>
        <div style="display: flex; margin: 0 25px">
          <div style="flex: 2">
            <div style="display: flex; color: #666666FF; margin: 14px 0">
              <div>
                <img src="@/assets/imgs/bg.jpg" alt="" style="width: 20px; height: 20px">
              </div>
              <div style="margin-left: 10px; font-size: 14px">靓丽女装</div>
            </div>
          </div>
          <div style="flex: 5; height: 300px; background-color: green;"></div>
          <div style="flex: 3; height: 300px; background-color: #2a60c9"></div>
        </div>
      </div>
      <div class="right"></div>
    </div>
  </div>
</template>

<script>

export default {

  data() {
    return {
      typeData: []
    }
  },
  // DOM元素渲染之后
  mounted() {
    this.loadType()
  },
  // methods：本页面所有的点击事件或者其他函数定义区
  methods: {
    loadType() {
      this.$request.get('/type/selectAll').then(res => {
        if (res.code === '200') {
          this.typeData = res.data
        } else {
          this.$message.error(res.msg)
        }
      })
    }
  }
}
</script>

<style scoped>
.main-content {
  min-height: 100vh;
  background-size: 100%;
  background-image: url("@/assets/imgs/img.png");
}

.left {
  width: 17%;
  background-repeat: no-repeat;
  background-image: url("@/assets/imgs/left-img.png");
}

.right {
  width: 17%;
  background-repeat: no-repeat;
  background-image: url("@/assets/imgs/right-img.png");
}
</style>
