<template>
  <div class="html body" id="cont">
    <div class="title">
      <font color="#409eff" size="5">修改账户密码</font>
    </div>
    <el-card class="box-card">
      <el-form
        label-position="right"
        :model="user"
        status-icon
        :rules="rules"
        ref="user"
        label-width="100px"
        class="demo-ruleForm"
      >
        <el-form-item label="用户名" prop="name">
          <el-input v-model="user.name"></el-input>
        </el-form-item>
        <el-form-item label="邮箱验证码" prop="emailCode">
          <el-row :gutter="5">
            <el-col :span="15">
                <el-input v-model="user.emailCode" :span="8"></el-input>
            </el-col>
            <el-col :span="4">
                <el-button @click="getEmailCode(user.name)">获取验证码</el-button>
            </el-col>
          </el-row>
        </el-form-item>
        <el-form-item label="新密码" prop="password">
          <el-input
            type="password"
            v-model="user.password"
            autocomplete="off"
            show-password
          ></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="info" @click="submitForm('user')"
            >未开放</el-button
          >
          <el-button @click="resetForm('user')">重置</el-button>
          <el-link class="link" type="primary" href="/Home">返回首页</el-link>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script>
export default {
  data() {
    var checkEmailCode = (value, callback) => {
      if (!value) {
        return callback(new Error("邮箱验证码不能为空"));
      }
      setTimeout(() => {
        if (value != user.emailCode) {
          callback(new Error("验证码错误"));
        } else {
          callback();
        }
      }, 1000);
    };
    return {
      user: {
        name: "",
        emailCode: "",
        password: "",
      },
      rules: {
        name: [
          { required: true, message: "请输入用户名", trigger: "blur" },
          { min: 1, max: 10, message: "长度在1到10个字符", trigger: "blur" },
        ],
        emailCode: [{ required: true, validator: checkEmailCode, trigger: "blur" }],
        password: [
          { required: true, message: "请输入新密码", trigger: "blur" },
          { min: 6, max: 20, message: "长度在6到20个字符", trigger: "blur" },
        ],
      },
    };
  },
  methods: {
    submitForm(user) {

    },
    resetForm(user) {
      this.$refs[user].resetFields();
    },
    getEmailCode(name){
        
    },
  },
};
</script>

<style scoped>
.html {
  height: 100%;
  width: 100%;
  position: fixed;
}
#cont{
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
}
</style>