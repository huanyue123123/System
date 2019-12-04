<template>
  <body id="poster">

    <el-form label-position="left" class = "login-container" hide-required-asterisk :model="loginForm" :rules="rules" ref = "loginForm"
             label-width="80px">
      <el-alert v-show="inputError"
                title="账号或密码错误"
                type="error"
                center
                show-icon>
      </el-alert>
      <el-form-item label="账号" prop="username">
        <el-input type = "text" v-model =  "loginForm.username" placeholder="请输入账号" auto-complete="off" minlength = "6"> </el-input>
      </el-form-item>

      <el-form-item label="密码" prop="password">
        <el-input type="password" v-model="loginForm.password" placeholder="请输入密码" auto-complete="off" show-password> </el-input>
      </el-form-item>
      <el-form-item label="验证码" prop="checkCode">
        <el-row  >
          <el-col :xs="12" :md="12" :sm="12" :lg="12">
            <el-input type="text"  v-model="loginForm.checkCode" placeholder="请输入验证码" auto-complete="off" > </el-input>
          </el-col>
          <el-col :xs="12" :md="12" :sm="12" :lg="12" >
            <div class="identifybox">
              <div @click="refreshCode">
                <el-image :src="checkCode" style="height:40px"  ></el-image>
              </div>
              <el-button @click="refreshCode" type='text' class="textbtn">看不清，换一张</el-button>
            </div>
          </el-col>

        </el-row>

      </el-form-item>
      <el-form-item>

      </el-form-item>
      <el-form-item style="width: 100%">
        <el-button type="primary" style="width: 100%;background: #505458;border: none" v-on:click="login('loginForm')">登录</el-button>
      </el-form-item>
    </el-form>
  </body>
</template>

<script>
export default {
  name: 'Login',
  data () {
    var checkUser = (rule,value,callback) => {
      if (!value) {
        return callback(new Error('账号不能为空'));
      }
      var regUser = /^[a-zA-Z][a-zA-Z0-9]{4,13}[a-zA-Z]$/;
      if(!regUser.test(value)){
        return callback(new Error('账号开头结尾必须为字母且长度6-15'));
      }
      callback();

    };
    var checkPwd = (rule,value,callback) => {
      if (!value) {
        return callback(new Error('密码不能为空'));
      }
      var regPwd = /^[a-zA-Z][a-zA-Z0-9]{5,14}$/;
      if(!regPwd.test(value)){
        return callback(new Error('密码以字母开头长度6-15，且不包含特殊符号'));
      }
      callback();
    };
    var checkVerify = (rule,value,callback) => {
      if (!value) {
        return callback(new Error('验证码'));
      }
      var regPwd = /^[a-zA-Z0-9]{4}$/;
      if(!regPwd.test(value)){
        return callback(new Error('验证码格式有误'));
      }
      let data = new FormData();
      data.append('verify',value);
      data.append('uuid',this.uuid);
      this.$axios
        .post('/checkVerify', data)
        .then(successResponse => {

          if(successResponse.data.code === 200){
            callback();
          }else if(successResponse.data.code === 400){
            return callback(new Error('验证码不正确'));
          }else{
            this.refreshCode();
            return callback(new Error('验证码过期，请重新输入'));
          }
        })
        .catch(failResponse => {
        })


    };

    return {
      loginForm: {
        username: '',
        password: '',
        checkCode:''
      },
      uuid:"",
      checkCode:"",
      rules: {
        username:[
          { validator: checkUser, trigger: 'blur' }
        ],
        password:[
          { validator: checkPwd, trigger: 'blur' }
        ],
        checkCode:[
          { validator: checkVerify, trigger: 'blur' }
        ]
      },
      responseResult: [],
      inputError:false
    }
  },
  methods: {
    login (formName) {
      this.$refs[formName].validate((valid) => {
        var map = {};
        map["name"] = "key";
        map["salt"] = "value";
        var list  = [];
        list.push(map);
        if (valid) {
          this.$axios
            .post('/login', {
              username: this.loginForm.username,
              password: this.loginForm.password,
              checkCode:this.loginForm.checkCode
            })
            .then(successResponse => {

              if (successResponse.data.code === 200) {
                this.$store.commit('login',successResponse.data.data)
                this.$router.replace({path: '/index'})
              }else if(successResponse.data.code === 400){
                this.inputError = true;
                this.refreshCode();
              }
            })
            .catch(failResponse => {
            })
        } else {

          console.log('error submit!!');
          return false;
        }
      });


    },
    refreshCode(){
      this.$axios
        .get('/createImg', {

        })
        .then(successResponse => {
          if(successResponse.data.code === 200){
            this.checkCode = successResponse.data.data.base64;
            this.uuid = successResponse.data.data.uuid;
          }
        })
        .catch(failResponse => {
        })
    }
  },
  mounted () {
    this.$axios
      .get('/createImg', {

      })
      .then(successResponse => {

        if(successResponse.data.code === 200){
          this.checkCode = successResponse.data.data.base64;
          this.uuid = successResponse.data.data.uuid;
        }
      })
      .catch(failResponse => {
      })
  }
}

</script>
<style>
  .login-container {
    border-radius: 15px;
    background-clip: padding-box;
    margin: 250px auto;
    width: 350px;
    padding: 15px 35px 15px 35px;
    background: #fff;
    border: 1px solid #eaeaea;
    box-shadow: 0 0 25px #cac6c6;
  }

  .login_title {
    margin: 0px auto 40px auto;
    text-align: center;
    color: #505458;
  }

  #poster {
   /* background:url("../assets/gelei/1.png") no-repeat;
    background-position: center;
    height: 100%;
    width: 100%;
    background-size: cover;
    position: fixed;*/
  }
  body{
    margin: 0px;
  }

</style>
