<template>
  <section>
    <div>
      <h2>Choose a Trophy</h2>
      <Achievement />
    </div>
  </section>
</template>

<script>

import Achievement from './achievement.vue'

export default {
  name: 'Achievements',
  data () {
    return {
      greeting: "Hello World",

    }
  },
  components: {
    Achievement
  },
  mounted (){

  },
  created (){
    console.log("achievements created")

  },
  methods: {
  }
}
</script>

<style>
</style>
