<template>
  <el-container>
    <el-col :span="4">
      <el-aside>
        <el-col :span="18">
          <el-card class="box-card">
            <div slot="header" class="clearfix">
              <el-avatar size="large" src=""></el-avatar>
              <span
                v-if="this.$store.state.userName.length == 0"
                class="bottom-item"
              >
                <a href="/login">请登录</a>
              </span>
              <span v-else class="bottom-item">{{
                this.$store.state.userName
              }}</span>
            </div>
            <div>用户信息</div>
          </el-card>
        </el-col>
      </el-aside>
    </el-col>
    <el-container>
      <el-header></el-header>
      <el-main>
        <el-col :span="20">
          <el-form ref="editForm" status-icon :model="blog" label-width="80px">
            <el-form-item label="标题">
              <el-input v-model="blog.title"></el-input>
            </el-form-item>
            <el-form-item label="正文">
              <mavon-editor v-model="blog.text" />
            </el-form-item>
            <el-form-item>
              <div id="button">
                <el-button type="primary" @click="submit">立即发表</el-button>
                <el-button type="info">存草稿</el-button>
              </div>
            </el-form-item>
          </el-form>
        </el-col>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
export default {
  data() {
    return {
      blog: {
        title: "",
        text: "",
      },
    };
  },
  methods: {
    submit() {
      this.$axios
        .post("http://47.108.236.102:8080/Blog/add", this.blog, {
          headers: { Authorization: this.$store.state.token },
        })
        .then((res) => {
          if (res.data.code == 200) {
            alert("发表成功！");
          } else if (res.data.code == 400) {
            alert("账号信息过期，请重新登录！");
            this.$store.commit("updateToken","");
            this.$store.commit("updateUserName","");
            this.$store.commit("updateHeadImage","");
          }
        });
    },
  },
};
</script>

<style scoped>
#button {
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
}
</style>