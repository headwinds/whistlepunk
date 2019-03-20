<template>
  <section>
    <div>
      template
    </div>
  </section>
</template>

<script>
import Logo from '~/components/Logo.vue'

export default {
  name: 'Template',
  data () {
    return {
      greeting: "Hello World",

    }
  },
  components: {
    Logo
  },
  mounted (){

  },
  created (){
    console.log("created")

  },
  methods: {
  }
}
</script>

<style>
</style>
