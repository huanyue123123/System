<template>
  <el-menu router style="min-width: 1300px"
    :default-active="activerouter" class="el-menu-demo" mode="horizontal" @select="selectEvent">
    <el-menu-item v-for="(item,i) in navList" :key="i" :index="item.name" >
      {{item.navItem}}
    </el-menu-item>
  </el-menu>

</template>
<script>

  export default {
    name: 'NavMenu',
    data () {
      return {
        navList: [
          {name: '/index', navItem: '首页'},
          {name: '/', navItem: '笔记本'},
          {name: '/library', navItem: '图书馆'},
          {name: '/', navItem: '个人中心'}
        ],
        activerouter:'/index'
      }
    },
    mounted(){
      this.activerouter = window.location.pathname;
    },
    methods:{
      selectEvent(key,keyPath){
        console.log(key+"-"+keyPath)
      }
    }
  }
</script>
<style scoped>
  a{
    text-decoration: none;
  }

  span {
    pointer-events: none;
  }
</style>
