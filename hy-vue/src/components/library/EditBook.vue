<template>
  <el-dialog width = "50%" title="添加/修改图书" :visible.sync="dialogFormVisible" @close="clear">
    <el-form :model="form" >
      <el-form-item label="书名" :label-width="formLabelWidth" prop="title">
        <el-input v-model="form.title" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="作者" :label-width="formLabelWidth" prop="author">
        <el-input v-model="form.author" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="出版日期" :label-width="formLabelWidth" prop="date">
        <el-input v-model="form.date" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="出版社" :label-width="formLabelWidth" prop="press">
        <el-input v-model="form.press" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="封面" :label-width="formLabelWidth" prop="cover">
        <el-input v-model="form.cover" autocomplete="off" placeholder="图片 URL"></el-input>
      </el-form-item>
      <el-form-item label="简介" :label-width="formLabelWidth" prop="abs">
        <el-input type="textarea" v-model="form.abs" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="分类" :label-width="formLabelWidth" prop="category.id">
        <el-select v-model="form.category.id" filterable placeholder="请选择分类"  class = "categorys">
          <el-option
            v-for="item in cates"
            :key="item.id"
            :label="item.name"
            :value="item.id">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item prop="id" style="height: 0" >
        <el-input type="hidden" v-model="form.id" autocomplete="off"></el-input>
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button @click="dialogFormVisible = false">取 消</el-button>
      <el-button type="primary" @click="onSubmit()">确 定</el-button>
    </div>
  </el-dialog>

</template>
<script>
  export default {
    name: 'EditBook',
    data(){
      return {
        dialogFormVisible: false,
        form: {
          id: '',
          title: '',
          author: '',
          date: '',
          press: '',
          cover: '',
          abs: '',
          category: {
            id: '',
            name: ''
          }
        },
        cates:[{
          id:1,
          name:'文学'
        },{
          id:2,
          name:'流行'
        },{
          id:3,
          name:'文化'
        },{
          id:4,
          name:'生活'
        },{
          id:5,
          name:'经管'
        },{
          id:6,
          name:'科技'
        }],
        formLabelWidth: '120px'
      };
    },
    mounted(){
      this.initCates();
    },
    methods:{
      initCates(){
        this.$axios.get('/categories/cateList',{

        }).then(res => {
          if(res && res.data.code === 200){
            console.log(res.data.data);
            this.cates = res.data.data;
          }
        })
      },
      clear () {
        this.form = {
          id: '',
          title: '',
          author: '',
          date: '',
          press: '',
          cover: '',
          abs: '',
          category: ''
        }
      },
      onSubmit(){
        this.$axios.post('/saveBooks',{
          id: this.form.id,
          cover: this.form.cover,
          title: this.form.title,
          author: this.form.author,
          date: this.form.date,
          press: this.form.press,
          abs: this.form.abs,
          cid: this.form.category.id
        }).then(res => {
          if(res && res.data.code === 200){
            this.dialogFormVisible = false;
            this.$emit('onSubmit')
            console.log(res.data.data);
          }
        })


      }
    }
  };
</script>
<style scoped>
.categorys{
  width:100%;
}
  .editContainer{
    width:50%;
  }
</style>
