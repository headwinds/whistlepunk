import Vuex from 'vuex'

const createStore = () => {
  return new Vuex.Store({
    state: () => ({
      achievements: [],
      selectedTrophy: {name: ''},
      colonists: {
        page: 1,
        records: [],
        active: {name: "unknown"},
      },
      questions: {
        page: 1,
        records: [],
        lastQuestionCreated: {created_timestamp: "unknown"},
      },
      answers: {
        page: 1,
        records: []
      },
      achievements: {
        page: 1,
        records: []
      },
    }),
    mutations: {
      selectTrophy (state, payload) {
        state.selectedTrophy = payload.trophy;
      },
      addAchievement (state) {
        state.achievements.push(state)
      },
      updateAchievement (state) {
        //state.achievements.push(state)
      },
      deleteAchievement (state) {
        const achievements = state.achievements;
        const newAchievements = achievements.filter(achievement => state.id !== achievement.id)

        state.achievements = newAchievements;
      },
      addColonists (state, payload) {
        console.log("colonists ", payload)
        state.colonists.records = payload
      },
      setColonist(state, payload) {
        state.colonists.active = payload
      },
      setLastQuestionCreated(state,payload) {
        state.questions.lastQuestionCreated = payload
      }
    },
    actions: {
      async nuxtServerInit ({ commit }, { app })  {
        //const colonists = await app.$axios.$get('http://localhost:5000/get-colonists?page=' + 1)
        //commit('addColonists', colonists)
        const colonists = await app.$axios.$get('http://localhost:5000/get-colonist?name=Han')

        commit('setColonist', colonists[0])
      },
      async getColonist ({ commit }, { app, name }) {
        const colonists = await app.$axios.$get('http://localhost:5000/get-colonist?name=' + name)
        commit('setColonist', colonists[0])
      },
      async postQuestionAsync ({ commit }, { app, question }) {
        console.log('postQuestionAsync ', question)
        const response = await app.$axios.$post('http://localhost:5000/post-question', question)

        console.log('postQuestionAsync ', response)
        commit('setLastQuestionCreated', response)
      }
    },
  })
}

export default createStore
