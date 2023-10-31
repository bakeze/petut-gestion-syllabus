import { createApp } from 'vue'

import App from './App.vue'
import router from "./router";
import CKEditor from '@ckeditor/ckeditor5-vue';

// Vuetify
import "vuetify/styles";
import { createVuetify } from "vuetify";
import * as components from "vuetify/components";
import * as directives from "vuetify/directives";


import { fa } from "vuetify/iconsets/fa";
import { aliases, mdi } from "vuetify/lib/iconsets/mdi";

import "@mdi/font/css/materialdesignicons.css";
import "@fortawesome/fontawesome-free/css/all.css";

const vuetify = createVuetify({
  icons: {
    defaultSet: "mdi",
    aliases,
    sets: {
      mdi,
      fa,
    },
  },
  components,
  directives,

})

// Import Bootstrap and BootstrapVue CSS files (order is important)
import 'bootstrap/dist/css/bootstrap.css'

//Vendor CSS Files 
import './assets/assets/vendor/aos/aos.css'
import './assets/assets/vendor/bootstrap/css/bootstrap.min.css'
import './assets/assets/vendor/bootstrap-icons/bootstrap-icons.css'
import './assets/assets/vendor/boxicons/css/boxicons.min.css'
import './assets/assets/vendor/glightbox/css/glightbox.min.css'
import './assets/assets/vendor/remixicon/remixicon.css'
import './assets/assets/vendor/swiper/swiper-bundle.min.css'
import './assets/assets/css/style.css'

//asser Table
import './assets/assets/assetstable/vendor/bootstrap/css/bootstrap.min.css'
import './assets/assets/assetstable/vendor/boxicons/css/boxicons.min.css'
import './assets/assets/assetstable/vendor/quill/quill.snow.css'
import './assets/assets/assetstable/vendor/quill/quill.bubble.css'
import './assets/assets/assetstable/vendor/remixicon/remixicon.css'
import './assets/assets/assetstable/vendor/simple-datatables/style.css'
import './assets/assets/assetstable/css/style.css'

import './assets/assets/assetstable/vendor/apexcharts/apexcharts.min.js'
import './assets/assets/assetstable/vendor/bootstrap/js/bootstrap.bundle.min.js'
import './assets/assets/assetstable/vendor/chart.js/chart.umd.js'
import './assets/assets/assetstable/vendor/echarts/echarts.min.js'
import './assets/assets/assetstable/vendor/quill/quill.min.js'
import './assets/assets/assetstable/vendor/simple-datatables/simple-datatables.js'
import './assets/assets/assetstable/vendor/tinymce/tinymce.min.js'
//rich editor
import AOS from 'aos'
import 'aos/dist/aos.css'
import responsive from 'vue-responsive'


createApp(App)
  .use(router)
  .use(vuetify)
  .use(CKEditor)
  .use(AOS.init())
  .use(responsive)
  .mount('#app')
