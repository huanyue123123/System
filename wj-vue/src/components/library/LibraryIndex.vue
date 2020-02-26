<template>
  <el-container>
    <el-aside style="width: 200px;margin-top: 20px">
      <switch></switch>
      <SideMenu @indexSelect="listByCategory" ref="sideMenu"></SideMenu>
    </el-aside>
    <el-main>
      <Books class = "books-area" ref="booksArea"></Books>
    </el-main>
  </el-container>
</template>

<script>

  import SideMenu from './SideMenu'
  import Books from './Books'

  export default {
    name: 'AppLibrary',
    components:{SideMenu,Books},
    methods: {
      listByCategory () {
        var _this = this
        var cid = this.$refs.sideMenu.cid
        var url = '/books'
        this.$axios.post(url,{"cid":cid}).then(resp => {
          if (resp && resp.data.code === 200) {
            console.log(resp.data.data);
            _this.$refs.booksArea.books = resp.data.data;
          }
        })
      }
    }
  }
</script>

<style scoped>
</style>
