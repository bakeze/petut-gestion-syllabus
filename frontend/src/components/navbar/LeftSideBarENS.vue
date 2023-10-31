<script setup>
import {ref} from "vue"
import NavbarHeader from './NavbarHeader.vue'
const drawer = ref("false");
const emit = defineEmits(['drawl'])
console.log(drawer)
</script>


<template>
<!-- ======= Sidebar ======= -->
<NavbarHeader @draw="drawer = !drawer,  emit('drawl',drawer)"></NavbarHeader>
<aside id="sidebar" class="sidebar" v-if="drawer" >
<ul class="sidebar-nav" id="sidebar-nav" >
  <li class="nav-item">
    <a class="nav-link ">
      <i class="bi bi-grid"></i>
      <RouterLink to="/dashENS">
        <span>Dashboard</span>
      </RouterLink>
    </a>
  </li><!-- End Dashboard Nav -->

  <li class="nav-item">
    <a class="nav-link collapsed" data-bs-target="#components-nav" data-bs-toggle="collapse" href="#">
      <i class="bi bi-menu-button-wide"></i><span>Gestion des matières</span><i class="bi bi-chevron-down ms-auto"></i>
    </a>
    <ul id="components-nav" class="nav-content collapse " data-bs-parent="#sidebar-nav">
      <li>
        <RouterLink to="/dashens/listmatiereens">
          <i class="bi bi-circle"></i><span> Liste matières</span>
        </RouterLink>
      </li>
    </ul>
  </li><!-- End Components Nav -->

  <li class="nav-item">
    
    <a class="nav-link collapsed">
      <i class="bi bi-box-arrow-in-left" ></i>
      <span><RouterLink to="/login">
        Deconnexion
      </RouterLink></span>
    </a>
  </li><!-- End Login Page Nav -->
</ul>

</aside><!-- End Sidebar-->

</template>

<style scoped>
    
</style>