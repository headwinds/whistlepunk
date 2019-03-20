<template>
  <section>
    <div>
      <Trophy />
    </div>
  </section>
</template>

<script>

import Trophy from './trophy.vue'

export default {
  name: 'Achievement',
  data () {
    return {
      greeting: "Hello World",

    }
  },
  components: {
      Trophy
  },
  mounted (){

  },
  created (){
    console.log("achievement created")

  },
  methods: {
  }
}
</script>

<style>
</style>
