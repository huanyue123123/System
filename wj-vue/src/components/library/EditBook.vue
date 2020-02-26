<template>
  <div>
    <i class="el-icon-circle-plus-outline"  @click="addBook"></i>
    <el-dialog top = "5vh" title="添加/修改图书" :visible.sync="dialogFormVisible" @close="clear">
      <el-form :model="form" >
        <el-form-item label="书名" :label-width="formLabelWidth" prop="title">
          <el-input v-model="form.title" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="作者" :label-width="formLabelWidth" prop="author">
          <el-input v-model="form.author" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="出版日期" :label-width="formLabelWidth" prop="date">
          <div class="block ">
            <el-date-picker class="timePick"
              v-model="form.date" format = "yyyy-MM" value-format = "yyyy-MM"
              type="month"
              placeholder="选择日期"
              >
            </el-date-picker>
          </div>
        </el-form-item>
        <el-form-item label="出版社" :label-width="formLabelWidth" prop="press">
          <el-input v-model="form.press" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="封面" :label-width="formLabelWidth" prop="cover">
          <el-upload
            class="avatar-uploader"
            action="#"
            :http-request="httpRequest"
            :show-file-list="false"
            :on-success="handleAvatarSuccess"
            :before-upload="beforeAvatarUpload">
            <img v-if="imageUrl" :src="imageUrl" class="avatar">
            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
          </el-upload>
          <el-input type="hidden" v-model="form.cover" autocomplete="off" placeholder="图片 URL"></el-input>
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
  </div>
</template>
<script>
  export default {
    name: 'EditBook',
    data(){
      return {
        imageUrl: '',
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
        cates:[],
        formLabelWidth: '120px'
      };
    },
    mounted(){
      this.initCates();
    },
    methods:{
      addBook(){
        this.dialogFormVisible = true;
      },
      handleAvatarSuccess(res, file) {
        this.imageUrl = URL.createObjectURL(file.raw);
      },
      beforeAvatarUpload(file) {
        const isJPG = file.type === 'image/jpeg'||'image/png';
        const isLt2M = file.size / 1024 / 1024 < 5;

        if (!isJPG) {
          this.$message.error('上传头像图片只能是 JPG 或者png格式!');
        }
        if (!isLt2M) {
          this.$message.error('上传头像图片大小不能超过 5MB!');
        }
        return isJPG && isLt2M;
      },
      httpRequest(data){
        let _this = this
        let rd = new FileReader() // 创建文件读取对象
        let file = data.file
        rd.readAsDataURL(file) // 文件读取装换为base64类型
        rd.onloadend = function (e) {
          _this.imageUrl = this.result // this指向当前方法onloadend的作用域
          _this.form.cover = this.result;
        }
      },
      initCates(){
        this.$axios.get('/categories/cateList',{

        }).then(res => {
          if(res && res.data.code === 200){
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
        this.imageUrl = '';
      },
      onSubmit(){
        console.log(this.form.date);
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
  .timePick{
    width:100%;
  }
.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}
.avatar-uploader .el-upload:hover {
  border-color: #409EFF;
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  line-height: 178px;
  text-align: center;
}
.avatar {
  width: 178px;
  height: 178px;
  display: block;
}
.el-icon-circle-plus-outline {
  margin: 50px 0 0 20px;
  font-size: 100px;
  float: left;
  cursor: pointer;
}
</style>
