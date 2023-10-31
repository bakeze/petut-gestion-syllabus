<script setup>
import { RouterLink, RouterView } from 'vue-router'
</script>
<template>
<div>
  <!-- les "composant view" s'afficheront ici -->
  <transition   name="fade" mode="out-in">
    <RouterView /> 
  </transition>
</div>            
</template>

<style scoped>
</style>