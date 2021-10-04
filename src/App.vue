<template>
  <div id="app">
    <router-view/>
  </div>
</template>

<script>
export default {
  name: "app",
  created(){
    //在页面加载时读取sessionStorage里的状态信息
    if (sessionStorage.getItem("store") ) {
        this.$store.replaceState(Object.assign({}, this.$store.state,JSON.parse(sessionStorage.getItem("store"))))
    } 

    //在页面刷新时将vuex里的信息保存到sessionStorage里
    window.addEventListener("beforeunload",()=>{
        sessionStorage.setItem("store",JSON.stringify(this.$store.state))
    })
  }
}
</script>

<style>
body {
  margin: 0;
  padding: 0;
  background-color: rgb(45,58,75);
}
.container {
  display: flex;
  flex-direction: column;
  justify-content: space-around;
  align-items: center;
}
.title{
  margin: 20px;
}
.link{
  margin: 5px;
}
</style>