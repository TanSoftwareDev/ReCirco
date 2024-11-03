<template>
  <div class="main-content">
    <div style="width: 80%; background-color: white; margin:30px auto; border-radius: 20px">
      <div style="padding: 5px 20px">
        <div style="font-size: 18px; color: #000000FF; line-height: 80px; border-bottom: #cccccc 1px solid">我的订单（{{ordersData.length}}个）</div>
        <div style="margin: 20px 0; padding: 0 50px">
          <div class="table">
            <el-table :data="ordersData" strip>
              <el-table-column label="商品图片" width="120px">
                <template v-slot="scope">
                  <div style="display: flex; align-items: center">
                    <el-image style="width: 80px; height: 60px; border-radius: 5px" v-if="scope.row.goodsImg"
                              :src="scope.row.goodsImg" :preview-src-list="[scope.row.goodsImg]"></el-image>
                  </div>
                </template>
              </el-table-column>
              <el-table-column prop="orderId" label="订单编号" width="200px">
                <template v-slot="scope">
                  <a :href="'/front/detail?id='+scope.row.goodsId">{{scope.row.orderId}}</a>
                </template>
              </el-table-column>
              <el-table-column prop="goodsName" label="商品名称":show-overflow-tooltip="true">
                <template v-slot="scope">
                  <a :href="'/front/detail?id='+scope.row.goodsId">{{scope.row.goodsName}}</a>
                </template>
              </el-table-column>
              <el-table-column prop="businessName" label="店铺名称">
                <template v-slot="scope">
                  <a :href="'/front/business?id='+scope.row.businessId">{{scope.row.businessName}}</a>
                </template>
              </el-table-column>
              <el-table-column prop="goodsPrice" label="商品单价">
                <template v-slot="scope">
                  {{scope.row.goodsprice}} / {{scope.row.goodsUnit}}
                </template>
              </el-table-column>
              <el-table-column prop="num" label="商品数量"></el-table-column>
              <el-table-column prop="price" label="订单总价"></el-table-column>
              <el-table-column prop="username" label="收货人"></el-table-column>
              <el-table-column prop="useraddress" label="收货地址"></el-table-column>
              <el-table-column prop="phone" label="联系电话"></el-table-column>
              <el-table-column prop="status" label="订单状态"></el-table-column>
              <el-table-column label="操作" align="center" width="180">
                <template v-slot="scope">
                  <el-button size="mini" type="primary" v-if="scope.row.status === '待收货'"plain @click="updateStatus(scope.row,'已完成')">确认收货</el-button>
                  <el-button size="mini" type="danger" plain @click="del(scope.row.id)">删除</el-button>
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
      ordersData:[],
      pageNum: 1,   // 当前的页码
      pageSize: 10,  // 每页显示的个数
      total: 0,
      form:{}
    }
  },
  // DOM元素渲染之后
  mounted() {
    this.loadOrders(1)
  },
  // methods：本页面所有的点击事件或者其他函数定义区
  methods: {
    loadOrders(pageNum){
      if (pageNum) this.pageNum = pageNum
      this.$request.get('/orders/selectPage', {
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
        }
      }).then(res => {
        if(res.code === '200'){
          this.ordersData = res.data?.list
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
      this.$request.delete('/orders/delete/' + id).then(res=>{
        if(res.code==='200'){
          this.$message.success('删除成功')
          this.loadOrders(1)
        }else{
          this.$message.error(res.msg)
        }

      })
    },
    handleCurrentChange(pageNum){
      this.loadOrders(pageNum)
    },
    updateStatus(row, status){
      this.form = row
      this.form.status =status
      this.$request.put('/orders/update', this.form).then(res =>{
        if(res.code === '200'){
          this.$message.success('操作成功')
        }else{
          this.$message.error(res.msg)
        }
      })

    }

  }
}
</script>


