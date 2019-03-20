<template>
  <section>
    <div>
      Trophy
      <button style="padding: 10px; border-radius: 4px"
      @click="$store.commit({type: 'selectTrophy', trophy:
      {name: 'big game hunter'}})">
        {{ $store.state.selectedTrophy.name }}</button>
    </div>
  </section>
</template>

<script>

export default {
  name: 'Trophy',
  data () {
    return {
      greeting: "Hello World",

    }
  },
  components: {

  },
  mounted (){

  },
  created (){
    console.log("trophy created")

  },
  methods: {
  }
}
</script>

<style>
</style>
