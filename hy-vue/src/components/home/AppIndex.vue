<template>
  <div>
  </div>
</template>

<script>
  export default {
    name: 'AppIndex',
    data(){

      return {}
    },
    methods:{
      logout(){
        this.$store.commit("login",{"username":null});
        this.$router.replace({path: '/login'})
      }
    }
  }
</script>

<style scoped>

</style>
