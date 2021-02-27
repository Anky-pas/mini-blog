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
          <el-card class="box-card">
            <div id="header" slot="header" class="clearfix">
              <h3>{{ blog.title }}</h3>
            </div>
            <div class="markdown-body" v-html="blog.text"></div>
          </el-card>
        </el-col>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
import "github-markdown-css/github-markdown.css";
export default {
  created() {
    this.$axios
      .get("http://47.108.236.102:8080/Blog/searchById", {
        params: { blogId: this.$route.query.blogId },
      })
      .then((res) => {
        this.blog = res.data.data;
        var MarkdownIt = require("markdown-it");
        var md = new MarkdownIt();
        var result = md.render(res.data.data.text);
        this.blog.text = result;
      });
  },
  data() {
    return {
      blogId: "",
      blog: {},
    };
  },
};
</script>

<style scoped>
#header {
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
}
</style>