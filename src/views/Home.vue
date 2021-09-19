<template>
  <el-container>
    <el-col :span="4">
      <el-aside>
        <el-col :span="18">
          <el-card class="box-card">
            <div slot="header" class="clearfix">
              <el-avatar size="large" src=""></el-avatar>
              <span v-if="this.$store.state.userName.length == 0" class="bottom-item"><el-link type="primary" href="/login">请登录 </el-link></span>
              <span v-else class="bottom-item">{{ this.$store.state.userName }}</span>
            </div>
            <div>用户信息</div>
            <el-button type="danger" @click="create">创作</el-button>
          </el-card>
        </el-col>
      </el-aside>
    </el-col>
    <el-container>
      <el-header id="header" height="200px">
        <el-col :span="18">
          <el-input placeholder="搜索" v-model="key">
            <el-button
              slot="append"
              icon="el-icon-search"
              @click="search"
            ></el-button>
          </el-input>
        </el-col>
      </el-header>
      <el-main>
        <list :data="data"></list>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
import list from "../components/list";
export default {
  components: { list },
  data() {
    return {
      key: "",
      data: [],
    };
  },
  methods: {
    search() {
      this.$axios
        .get("http://47.108.236.102:8080/Blog/search", {
          params: { key: this.key, page: "1", size: "10" },
        })
        .then((res) => {
          this.data = res.data.data;
        });
    },
    create(){
      this.$router.replace("/blogEdit");
    }
  },
  mounted() {
    this.$axios
      .get("http://47.108.236.102:8080/Blog/search", {
        params: { key: "%", page: "1", size: "10" },
      })
      .then((res) => {
        this.data = res.data.data;
      });
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