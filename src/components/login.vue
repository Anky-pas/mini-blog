<template>
  <el-tabs v-model="login" type="border-card">
    <el-tab-pane label="用户名登录" name="name">
      <loginInfo
        v-if="login === 'name'"
        type="name"
        :submitURL="submitURL"
        :verifyCodeURL="verifyCodeURL"
        :loginSuccessURL="loginSuccessURL"
        :forgetPasswordURL="forgetPasswordURL"
        :registerURL="registerURL"
      ></loginInfo>
    </el-tab-pane>
    <el-tab-pane label="手机号登录" name="phone">
      <loginInfo
        v-if="login === 'phone'"
        type="phone"
        :submitURL="submitURL"
        :verifyCodeURL="verifyCodeURL"
        :loginSuccessURL="loginSuccessURL"
        :forgetPasswordURL="forgetPasswordURL"
        :registerURL="registerURL"
      ></loginInfo>
    </el-tab-pane>
  </el-tabs>
</template>

<script>
import loginInfo from "../components/loginInfo";
export default {
  components: { loginInfo },
  name: "login",
  props: [
    "submitURL",
    "verifyCodeURL",
    "loginSuccessURL",
    "forgetPasswordURL",
    "registerURL",
  ],
  data() {
    return {
      login: "name",
    };
  },
};
</script>

<style>
</style>