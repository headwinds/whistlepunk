<template>
  <section>
    <div>
      <Question />
    </div>
  </section>
</template>

<script>

import Question from './Question.vue'

export default {
  name: 'Questions',
  data () {
    return {
      greeting: "Hello World",

    }
  },
  components: {
    Question
  },
  mounted (){

  },
  created (){
    console.log("questions created")

  },
  methods: {
  }
}
</script>

<style>
</style>
