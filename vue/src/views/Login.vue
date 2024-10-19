<template>
  <div class="container">
    <div style="width: 400px; padding: 30px; background-color: white; border-radius: 20px;">
      <div style="text-align: center; font-size: 20px; margin-bottom: 20px; color: #333">欢迎使用悠换二手货物交易平台
      </div>
      <el-form :model="form" :rules="rules" ref="formRef">
        <el-form-item prop="username">
          <el-input prefix-icon="el-icon-user" placeholder="请输入账号" v-model="form.username"></el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input prefix-icon="el-icon-lock" placeholder="请输入密码" show-password
                    v-model="form.password"></el-input>
        </el-form-item>
        <el-form-item>
          <el-select v-model="form.role" placeholder="请选择角色" style="width: 100%">
            <el-option label="管理员" value="ADMIN"></el-option>
            <el-option label="商家" value="BUSINESS"></el-option>
            <el-option label="用户" value="USER"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button style="width: 100%; background-color: #333; border-color: #333; color: white" @click="login">登 录
            / 注 册
          </el-button>
        </el-form-item>
        <!--        <div style="display: flex; align-items: center">-->
        <!--          <div style="flex: 1"></div>-->
        <!--          <div style="flex: 1; text-align: right">-->
        <!--            还没有账号？请 <a href="/register">注册</a>-->
        <!--          </div>-->
        <!--        </div>-->
      </el-form>
    </div>
  </div>
</template>

<script>
export default {
  name: "Login",
  data() {
    return {
      form: {role: 'ADMIN'},
      rules: {
        username: [
          {required: true, message: '请输入账号', trigger: 'blur'},
        ],
        password: [
          {required: true, message: '请输入密码', trigger: 'blur'},
        ]
      }
    }
  },
  created() {

  },
  methods: {
    login() {
      this.$refs['formRef'].validate((valid) => {
        if (valid) {
          // 验证通过
          this.$request.post('/login', this.form).then(res => {
            if (res.code === '200') {
              localStorage.setItem("xm-user", JSON.stringify(res.data))  // 存储用户数据
              this.$router.push('/')  // 跳转主页
              this.$message.success('登录成功')
            } else if (res.code === '404') {
              // 用户不存在或其他错误导致的登录失败，尝试注册
              this.registerUser();
            } else {
              this.$message.error(res.msg)
            }
          }).catch(() => {
            this.$message.error('登录请求失败')
          })
        }
      })
    },
    registerUser() {
      // 进行注册操作
      this.$request.post('/register', this.form).then(res => {
        if (res.code === '200') {
          this.$message.success('注册成功，正在登录...');
          // 注册成功后自动登录
          this.login();
        } else {
          this.$message.error(res.msg || '注册失败');
        }
      }).catch(() => {
        this.$message.error('注册请求失败');
      });
    }
  }
}
</script>

<style scoped>
.container {
  height: 100vh;
  overflow: hidden;
  background-image: url("@/assets/imgs/bg.jpg");
  background-size: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
  color: #666;
}

a {
  color: #2a60c9;
}
</style>