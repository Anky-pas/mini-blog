<template>
  <div class="html body container">
    <div>
      <font color="#409eff" size="5">欢迎加入Mini-Blog</font>
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
        <el-form-item label="性别" prop="sex">
          <el-radio v-model="user.sex" label="1">男</el-radio>
          <el-radio v-model="user.sex" label="2">女</el-radio>
        </el-form-item>
        <el-form-item label="年龄" prop="age">
          <el-input v-model="user.age"></el-input>
        </el-form-item>
        <el-form-item label="专业" prop="profession">
          <el-input v-model="user.profession"></el-input>
        </el-form-item>
        <el-form-item label="个人简介" prop="personal_profile">
          <el-input v-model="user.personal_profile"></el-input>
        </el-form-item>
        <el-form-item label="手机号" prop="phone">
          <el-input v-model="user.phone"></el-input>
        </el-form-item>
        <el-form-item label="邮箱" prop="email">
          <el-input v-model="user.email"></el-input>
        </el-form-item>
        <el-form-item label="邮箱验证码" prop="emailCode">
          <el-row :gutter="5">
            <el-col :span="15">
                <el-input v-model="user.emailCode" :span="8"></el-input>
            </el-col>
            <el-col :span="4">
                <el-button @click="getEmailCode()">获取验证码</el-button>
            </el-col>
          </el-row>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input
            type="password"
            v-model="user.password"
            autocomplete="off"
          ></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="info" @click="submitForm('user')"
            >未开放注册</el-button
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
    var checkAge = (value, callback) => {
      if (!value) {
        return callback(new Error("年龄不能为空"));
      }
      setTimeout(() => {
        if (!Number.isInteger(value)) {
          callback(new Error("请输入数字值"));
        } else {
          callback();
        }
      }, 1000);
    };
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
        sex: 1,
        age: 18,
        profession: "",
        personal_profile: "",
        phone: "",
        email: "",
        emailCode: "",
        password: "",
      },
      rules: {
        name: [
          { required: true, message: "请输入用户名", trigger: "blur" },
          { min: 1, max: 10, message: "长度在1到10个字符", trigger: "blur" },
        ],
        sex: [{ required: true, trigger: "blur" }],
        age: [{ required: true, validator: checkAge, trigger: "blur" }],
        phone: [
          { required: true, message: "请输入手机号", trigger: "blur" },
          { min: 11, max: 11, message: "长度为11个字符", trigger: "blur" },
        ],
        email: [{ required: true, message: "请输入邮箱", trigger: "blur" }],
        emailCode: [{ required: true, validator: checkEmailCode, trigger: "blur" }],
        password: [
          { required: true, message: "请输入密码", trigger: "blur" },
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
    getEmailCode(){

    },
  },
};
</script>

<style>
.html {
  height: 100%;
  width: 100%;
  position: fixed;
}
</style>