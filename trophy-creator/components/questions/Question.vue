<template>
  <section>
    <div style="display: flex; flex-direction: row; margin: 10px">
      <span style="margin-right: 10px">Question</span>
      <p style="white-space: pre-line;">{{ question }}</p>
      <br>
      <textarea style="width: 400px" v-model="question" placeholder="type your question"></textarea>
      <button v-on:click="handleSubmitQuestion">
        Submit
      </button>
    </div>
  </section>
</template>

<script>

export default {
  name: 'Question',
  data () {
    return {
      question: "",

    }
  },
  components: {

  },
  mounted (){

  },
  created (){
    console.log("question created")

  },
  methods: {
    handleSubmitQuestion: function(){
      console.log("handleSubmitQuestion")
    }
  }
}
</script>

<style>
</style>
