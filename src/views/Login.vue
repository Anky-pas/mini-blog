<template>
  <div class="html body" id="cont">
    <font color="#409eff" size="7">Mini-Blog</font>
    <login
      submitURL="http://47.108.236.102:8080/Account/login"
      verifyCodeURL="http://47.108.236.102:8080/Account/verificationCode"
      loginSuccessURL="/home"
      forgetPasswordURL="/forgetPassword"
      registerURL="/register"
    ></login>
  </div>
</template>

<script>
import login from "../components/login";
export default {
  components: { login },
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