<template>
  <section class="container">
    <div>
      <logo />
      <h1 class="title">
        trophy-hunter
      </h1>
      <h2 class="subtitle">
        manage questions and achievements
      </h2>
      <div>who:
        {{ $store.state.colonists.active.colonist_name }}
      </div>
      <div>
        last question created:
        {{ $store.state.questions.lastQuestionCreated.created_timestamp
         }}
      </div>
      <Achievements />
      <Questions />
      <button style="padding: 10px; border-radius: 4px"
        v-on:click="postQuestion">send</button>
    </div>
  </section>
</template>

<script>
import Logo from '~/components/Logo.vue'
import Questions from '~/components/questions/Questions.vue'
import Achievements from '~/components/achievements/Achievements.vue'

export default {
  name: "TrophyCreator",
  data () {
    return {
        colonist: {}
    }
  },
  created () {
    //this.$store.dispatch({type: 'getColonist', name: 'Han', app: this})
  },
  methods: {
    postQuestion: function(){
      const question = {
        answer: "42",
        question: "What is the meaning of life?",
        colonist_id: 4
      }
      this.$store.dispatch({type: 'postQuestionAsync', question, app: this})
    }
  },
  components: {
    Logo,
    Questions,
    Achievements
  }
}
</script>

<style>

.container {
  min-height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
  text-align: center;
}

.title {
  font-family: 'Quicksand', 'Source Sans Pro', -apple-system, BlinkMacSystemFont,
    'Segoe UI', Roboto, 'Helvetica Neue', Arial, sans-serif;
  display: block;
  font-weight: 300;
  font-size: 100px;
  color: #35495e;
  letter-spacing: 1px;
}

.subtitle {
  font-weight: 300;
  font-size: 42px;
  color: #526488;
  word-spacing: 5px;
  padding-bottom: 15px;
}

.links {
  padding-top: 15px;
}

</style>
