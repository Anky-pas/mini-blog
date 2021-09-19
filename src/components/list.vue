<template>
    <el-card class="box-card" shadow="never">
        <listItem v-for="blog in data" :blog="blog" :key="blog.blogId"></listItem>
        <div v-if="data.length == 0">未找到相应内容</div>
    </el-card>
</template>

<script>
import listItem from "../components/listItem"
export default {
    name:"list",
    components:{listItem},
    props:["data"]
}
</script>

<style>

</style>