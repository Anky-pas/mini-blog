import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import Login from '../views/Login.vue'
import Register from '../views/Register.vue'
import BlogDetail from '../views/BlogDetail.vue'
import BlogEdit from '../views/BlogEdit.vue'
import My from '../views/My.vue'
import ResultOverview from '../views/ResultOverview.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Index',
    redirect: { name: 'Home'}
  },
  {
    path: '/home',
    name: 'Home',
    component: Home
  },
  {
    path: '/login',
    name: 'Login',
    component: Login
  },
  {
    path: '/register',
    name: 'Register',
    component: Register
  },
  {
    path: '/blogEdit',
    name: 'BlogEdit',
    component: BlogEdit
  },
  {
    path: '/blogDetail',
    name: 'BlogDetail',
    component: BlogDetail
  },
  {
    path: '/my',
    name: 'My',
    component: My
  },
  {
    path: '/resultOverview',
    name: 'ResultOverview',
    component: ResultOverview
  }
  // {
  //   path: '/about',
  //   name: 'About',
  //   // route level code-splitting
  //   // this generates a separate chunk (about.[hash].js) for this route
  //   // which is lazy-loaded when the route is visited.
  //   component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
  // }
]

const router = new VueRouter({
  mode:'history',
  routes
})

export default router
