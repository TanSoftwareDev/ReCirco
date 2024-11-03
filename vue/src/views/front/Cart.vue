<template>
  <div class="main-content">
    <div style="width: 70%; background-color: white; margin:30px auto; border-radius: 20px">
      <div style="padding: 5px 20px">
        <div style="font-size: 18px; color: #000000FF; line-height: 80px; border-bottom: #cccccc 1px solid">我的购物车（{{goodsData.length}}件）</div>
        <div style="margin: 20px 0; padding: 0 50px">
          <div class="table">
            <el-table :data="goodsData" strip>
              <el-table-column label="商品图片" width="120px">
                <template v-slot="scope">
                  <div style="display: flex; align-items: center">
                    <el-image style="width: 80px; height: 60px; border-radius: 5px" v-if="scope.row.goodsImg"
                              :src="scope.row.goodsImg" :preview-src-list="[scope.row.goodsImg]"></el-image>
                  </div>
                </template>
              </el-table-column>
              <el-table-column prop="goodsName" label="商品名称" width="200px">
                <template v-slot="scope">
                  <a :href="'/front/detail?id='+scope.row.goodsId">{{scope.row.goodsName}}</a>
                </template>
              </el-table-column>
              <el-table-column prop="businessName" label="店铺名称">
                <template v-slot="scope">
                  <a :href="'/front/business?id='+scope.row.businessId">{{scope.row.businessName}}</a>
                </template>
              </el-table-column>
              <el-table-column prop="goodsPrice" label="商品价格"></el-table-column>
              <el-table-column prop="num" label="选择数量">
                <template v-slot="scope">
                  <el-input-number v-model="scope.row.num" style="width: 100px" @change="handleChange" :min="1"></el-input-number>
                </template>
              </el-table-column>
              <el-table-column label="操作" align="center" width="180">
                <template v-slot="scope">
                  <el-button size="mini" type="danger" plain @click="del(scope.row.id)">移除购物车</el-button>
                </template>
              </el-table-column>

            </el-table>

            <div class="pagination" style="margin-top: 20px">
              <el-pagination
                  background
                  @current-change="handleCurrentChange"
                  :current-page="pageNum"
                  :page-sizes="[5, 10, 20]"
                  :page-size="pageSize"
                  layout="total, prev, pager, next"
                  :total="total">
              </el-pagination>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>

export default {

  data() {

    return {
      user: JSON.parse(localStorage.getItem('xm-user') || '{}'),
      goodsData:[],
      pageNum: 1,   // 当前的页码
      pageSize: 10,  // 每页显示的个数
      total: 0,
    }
  },
  // DOM元素渲染之后
  mounted() {
    this.loadGoods(1)
  },
  // methods：本页面所有的点击事件或者其他函数定义区
  methods: {
    loadGoods(pageNum){
      if (pageNum) this.pageNum = pageNum
      this.$request.get('/cart/selectPage', {
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
        }
      }).then(res => {
        if(res.code === '200'){
          this.goodsData = res.data?.list
          this.total = res.data?.total
        }else{
          this.$message.error(res.msg);
        }
      })
    },
    navTo(url) {
      location.href = url
    },
    del(id){
      this.$request.delete('/cart/delete/' + id).then(res=>{
        if(res.code==='200'){
          this.$message.success('移除成功')
          this.loadGoods(1)
        }else{
          this.$message.error(res.msg)
        }

      })
    },
    handleCurrentChange(pageNum){
      this.loadGoods(pageNum)
    }
  }
}
</script>


