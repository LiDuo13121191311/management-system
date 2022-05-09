import Vue from 'vue';
import App from './App.vue';
import router from './router';
import store from './store';
import {Lazyload, Locale} from 'vant';
import '@/styles/index.scss'
import { Toast } from 'vant';
Vue.use(Toast);
import locale from 'element-ui/lib/locale/lang/en' // lang i18n
import enUS from 'vant/es/locale/lang/en-US';
/*引入axios*/
import axios from "axios";
import VueAxios from "vue-axios";
Vue.use(VueAxios,axios);
//Vue.prototype.$axios = axios;
Locale.use('en-US', enUS);
Vue.use(Lazyload);

/*引入Vant*/
import Vant from 'vant';
import 'vant/lib/index.css';
Vue.use(Vant);

/*引入element-ui*/
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
Vue.use(ElementUI, { locale });

Vue.config.productionTip = false;

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app');
