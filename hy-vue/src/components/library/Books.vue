<template>
  <div>
    <el-row style="height: 840px;">
      <search-bar @onSearch="searchResult" ref="searchBar"></search-bar>
      <el-tooltip effect="dark" placement="right"
                  v-for="item in books"
                  :key="item.id">
        <p slot="content" style="font-size: 14px;margin-bottom: 6px;">{{item.title}}</p>
        <p slot="content" style="font-size: 13px;margin-bottom: 6px">
          <span>{{item.author}}</span> /
          <span>{{item.date}}</span> /
          <span>{{item.press}}</span>
        </p>
        <p slot="content" style="width: 300px" class="abstract">{{item.abs}}</p>
        <el-card style="width: 135px;margin-bottom: 20px;height: 233px;float: left;margin-right: 15px" class="book"
                 bodyStyle="padding:10px" shadow="hover">
          <div class="cover" @click="editBook(item)">
            <img :src="item.cover" alt="封面">
          </div>
          <div class="info">
            <div class="title">
              <a href="">{{item.title}}</a>
              <i class="el-icon-delete" @click="deleteBook(item.id)"></i>
            </div>
          </div>
          <div class="author">{{item.author}}</div>
        </el-card>
      </el-tooltip>
      <edit-book @onSubmit="loadBooks()" ref="edit"></edit-book>
    </el-row>
    <el-row>
      <el-pagination @current-change="handleCurrentChange"
        :current-page="pageNo"
        :page-size="pageSize"
        :total="books.length">
      </el-pagination>
    </el-row>
  </div>
</template>

<script>

  import EditBook from './EditBook'
  import SearchBar from './SearchBar'

  export default {
    name: 'Books',
    components: {EditBook,SearchBar},
    data () {
      return {
        books: [],
        pageSize:10,
        pageNo:1
      }
    },
    mounted(){
      this.loadBooks();
    },
    methods:{
      loadBooks(){
        this.$axios.post('/books',{pageNo:this.pageNo,pageSize: this.pageSize}).then(result => {
          if(result.data.code === 200){
            this.books = result.data.data;
          }
        })
      },
      editBook(item){

        this.$refs.edit.dialogFormVisible = true;
        this.$axios.post(item.id + '/detail',{}).then(result => {
            if(result.data.code === 200){
              this.$refs.edit.form =result.data.data;
            }
        })

      },
      handleCurrentChange: function (pageNo) {
        this.pageNo = pageNo
      },
      searchResult () {
        var _this = this
        this.$axios
          .post(this.$refs.searchBar.keywords + '/search', {

          }).then(resp => {
          if (resp && resp.data.code === 200) {
            //_this.books = resp.data.data
          }
        })
      },
      deleteBook (id) {
        this.$confirm('此操作将永久删除该书籍, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
            var ids = [];
            ids.push(id);
            this.$axios
              .post('/deleteByIds/'+ids).then(resp => {
              if (resp && resp.status === 200) {
                this.$message({
                  type: 'info',
                  message: '已删除'
                })
                this.loadBooks()
              }
            })
          }
        ).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          })
        })
        // alert(id)
      },

    }
  }
</script>

<style scoped>
  .cover {
    width: 115px;
    height: 172px;
    margin-bottom: 7px;
    overflow: hidden;
    cursor: pointer;
  }

  img {
    width: 115px;
    height: 172px;
    /*margin: 0 auto;*/
  }

  .title {
    font-size: 14px;
    text-align: left;
  }

  .author {
    color: #333;
    width: 102px;
    font-size: 13px;
    margin-bottom: 6px;
    text-align: left;
  }

  .abstract {
    display: block;
    line-height: 17px;
  }

  a {
    text-decoration: none;
  }

  a:link, a:visited, a:focus {
    color: #3377aa;
  }
  .el-icon-delete {
    cursor: pointer;
    float: right;
  }
</style>
