<template>
  <div>
    <el-container>
      <el-header></el-header>
      <el-main>
        <el-col :span="8" offset="8">
          <login
            submitURL="http://47.108.236.102:8080/Account/login"
            verifyCodeURL="http://47.108.236.102:8080/Account/verificationCode"
            loginSuccessURL="/home"
            forgetPasswordURL="/forgetPassword"
            registerURL="/register"
          ></login>
        </el-col>
      </el-main>
      <el-footer></el-footer>
    </el-container>
  </div>
</template>

<script>
import login from "../components/login";
export default {
  components: { login },
};
</script>

<style>
</style>