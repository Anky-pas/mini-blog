import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    token:"",
    userName: "",
    headImage: ""
  },
  mutations: {
    updateToken(state,token){
      state.token = token;
    },
    updateUserName(state,userName){
      state.userName = userName;
    },
    updateHeadImage(state,headImage){
      state.headImage = headImage;
    }
  },
  actions: {
  },
  modules: {
  }
})
