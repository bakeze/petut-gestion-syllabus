<script setup>
import LeftSideBarENS from './navbar/LeftSideBarENS.vue'
import {ref} from "vue"
const drawerl = ref(false);
</script>

<template>
<LeftSideBarENS @drawl="drawerl = !drawerl"></LeftSideBarENS>
<body :class="{'toggle-sidebar' : drawerl }">
<main id="main" class="main">
<div class="pagetitle">
    <h1>Dashboard</h1>
    <nav>
      <ol class="breadcrumb">
        <li class="breadcrumb-item"><a href="index.html">Home</a></li>
        <li class="breadcrumb-item active"></li>
      </ol>
    </nav>
  </div><!-- End Page Title -->//
  <RouterView></RouterView>
</main><!-- End #main -->

<!-- ======= Footer ======= -->
</body>
</template>

<style scoped>
    
</style>