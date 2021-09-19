<template>
  <el-form
    :model="ruleForm"
    :rules="rules"
    ref="ruleForm"
    label-width="80px"
    class="demo-ruleForm"
  >
    <el-form-item v-if="type === 'name'" label="用户名" prop="name">
      <el-input placeholder="请输入用户名" v-model="ruleForm.name"></el-input>
    </el-form-item>
    <el-form-item v-else label="手机号" prop="phone">
      <el-input placeholder="请输入手机号" v-model="ruleForm.phone"></el-input>
    </el-form-item>
    <el-form-item label="密码" prop="password">
      <el-input
        placeholder="请输入密码"
        type="password"
        v-model="ruleForm.password"
        autocomplete="off"
        show-password
      ></el-input>
    </el-form-item>
    <el-form-item label="验证码" prop="verifyCode">
      <el-col :span="12">
        <el-input
          placeholder="请输入验证码"
          v-model="ruleForm.verifyCode"
        ></el-input>
      </el-col>
      <el-col :span="10" offset="2">
        <img v-bind:src="src" title="看不清楚，换一张" @click="getVerifyCode" />
      </el-col>
    </el-form-item>
    <el-form-item>
      <el-col :span="6">
        <el-button type="primary" @click="login">登录</el-button>
      </el-col>
      <el-col :span="6">
        <el-button type="info" @click="register">注册</el-button>
      </el-col>
      <el-col :span="6">
        <el-link class="link" type="primary" :href="forgetPasswordURL">忘记密码</el-link>
      </el-col>
      <el-col :span="6">
        <el-link class="link" type="info" href="/Home">游客访问</el-link>
      </el-col>
    </el-form-item>
  </el-form>
</template>

<script>
export default {
  name: "loginInfo",
  props: [
    "type",
    "submitURL",
    "verifyCodeURL",
    "loginSuccessURL",
    "forgetPasswordURL",
    "registerURL",
  ],
  data() {
    return {
      ruleForm: {
        name: "",
        phone: "",
        password: "",
        verifyCode: "",
        login: this.type,
      },
      rules: {
        name: [
          { required: true, message: "请输入用户名", trigger: "blur" },
          { min: 1, max: 10, message: "长度在1到10个字符", trigger: "blur" },
        ],
        phone: [
          { required: true, message: "请输入手机号", trigger: "blur" },
          { min: 11, max: 11, message: "长度为11个字符", trigger: "blur" },
        ],
        password: [
          { required: true, message: "请输入密码", trigger: "blur" },
          { min: 6, max: 20, message: "长度在6到20个字符", trigger: "blur" },
        ],
        verifyCode: [
          { required: true, message: "请输入验证码", trigger: "blur" },
        ],
      },
      src: "",
    };
  },
  methods: {
    getVerifyCode() {
      this.$axios.get(this.verifyCodeURL).then((res) => {
        this.src = "data:image/png;base64," + res.data;
      });
    },
    login() {
      this.$axios.post(this.submitURL, this.ruleForm).then((res) => {
        if (res.data.code == 200) {
          this.$store.commit("updateToken", res.data.token);
          this.$store.commit("updateUserName", res.data.userName);
          this.$store.commit("updateHeadImage", res.data.headImage);
          this.$router.replace(this.loginSuccessURL);
        } else {
          alert(res.data.msg);
        }
      });
    },
    register() {
      this.$router.replace(this.registerURL);
    },
  },
  mounted() {
    this.$axios.get(this.verifyCodeURL).then((res) => {
      this.src = "data:image/png;base64," + res.data;
    });
  },
};
</script>

<style>
</style>