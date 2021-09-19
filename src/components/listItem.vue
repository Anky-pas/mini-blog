<template>
  <div id="container">
    <div id="item">
      <div id="title">
        <a href="javascript:void(0);" @click="go">
          <h3>{{ blog.title }}</h3>
        </a>
      </div>
      <div id="main" class="markdown-body" v-html="blog.text"></div>
      <div id="bottom">
        <span class="bottom-item">{{ blog.date }}</span>
        <span class="bottom-item">博客</span>
        <span class="bottom-item">{{ blog.userName }}</span>
        <span class="bottom-item"
          ><i class="el-icon-star-off">{{ blog.star }}</i></span
        >
        <span class="bottom-item"
          ><i class="el-icon-folder-checked">{{ blog.collect }}</i></span
        >
        <span class="bottom-item"
          ><i class="el-icon-chat-dot-square">{{ blog.comment }}</i></span
        >
        <span class="bottom-item"
          ><i class="el-icon-view">{{ blog.browse }}</i></span
        >
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "listItem",
  props: ["blog"],
  methods: {
    go() {
      this.$router.push({
        path: "/blogDetail",
        query: { blogId: this.blog.blogId },
      });
    },
  },
  created() {
    var MarkdownIt = require("markdown-it");
    var md = new MarkdownIt();
    var result = md.render(this.blog.text);
    this.blog.text = result;
  },
};
</script>

<style scoped>
.bottom-item {
  margin: 10px;
}
a {
  text-decoration: none;
}
h3 {
  font-weight: 400;
}
#container {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}
#item {
  padding: 10px 0;
  border-bottom: 1px solid #f0f0f5;
  margin: 0;
  box-sizing: border-box;
}
#title {
  margin-bottom: 8px;
  margin: 0;
  padding: 0;
  box-sizing: border-box;
  color: #2440b3;
}
#main {
  display: flex;
  min-width: 0;
  max-width: 842px;
  margin: 0;
  padding: 0;
  box-sizing: border-box;
  max-height: 44px;
  color: #555666;
  line-height: 22px;
  margin-bottom: 8px;
  word-break: break-all;
  text-overflow: -o-ellipsis-lastline;
  overflow: hidden;
  text-overflow: ellipsis;
  display: -webkit-box;
  -webkit-line-clamp: 2;
  line-clamp: 2;
  -webkit-box-orient: vertical;
}
</style>