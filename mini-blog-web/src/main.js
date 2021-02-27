import Vue from 'vue'
import store from './store'
import axios from 'axios'
import mavonEditor from 'mavon-editor'
import 'mavon-editor/dist/css/index.css'
import Element from 'element-ui'
import "element-ui/lib/theme-chalk/index.css"
import App from './App.vue'
import router from './router'

Vue.prototype.$axios = axios 

Vue.use(Element)
Vue.use(mavonEditor)

Vue.config.productionTip = false

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
