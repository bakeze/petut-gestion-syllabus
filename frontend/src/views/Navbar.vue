<script setup>
import { ref } from 'vue';
const showMenu = ref(false);
const toggleMenu = () => {
  showMenu.value = !showMenu.value;
};

</script>
<template>
  <link
    href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Jost:300,300i,400,400i,500,500i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i"
    rel="stylesheet">
  <link rel="stylesheet" href="">

  <body>
    <!-- ======= Header ======= -->
    <header id="header" class="fixed-top"   >
      <!-- .navbar -->
      <div class="container d-flex align-items-center">
        <a href="/" class="logo me-auto"><img src="../assets/assets/img/logo-isis-blan.svg" alt="" class="img-fluid"></a>

        <nav id="navbar" class="navbar"  >
          <ul >
            <li><a class="nav-link scrollto active">
                <RouterLink to="/">Acceuil</RouterLink>
              </a></li>
            <li><a class="nav-link scrollto">
                <RouterLink to="/cyclepreparatoire">Cycle préparatoire </RouterLink>
              </a></li>
            <li><a class="nav-link   scrollto">
                <RouterLink to="/CycleIngenieur">Cycle ingénieur </RouterLink>
              </a></li>
            <li><a class="nav-link scrollto">
                <RouterLink to="/CycleApprentissage">ISIS par L'apprentissage </RouterLink>
              </a></li>
            <li><a class="nav-link scrollto">Contact</a></li>
            <li>
              <RouterLink class="getstarted scrollto" to="/login">Connexion</RouterLink>
            </li>
          </ul>
          <i class="bi bi-list mobile-nav-toggle"  @click="toggleMenu" :class="{ 'navbar-mobile': showMenu, 'bi-list': showMenu, 'bi-x': showMenu }"></i>
        </nav>

      </div>
    </header>
  </body>
</template>



<style  scoped>
header {
  background-color: #37517e;
}

ul {
  position: relative;
}

li {
  position: relative;
  bottom: -20px;
  opacity: 0;
  animation: slide-up 0.5s ease forwards;
}

li:nth-child(1) {
  animation-delay: 0.1s;
}

li:nth-child(2) {
  animation-delay: 0.2s;
}

li:nth-child(3) {
  animation-delay: 0.3s;
}

li:nth-child(4) {
  animation-delay: 0.4s;
}

li:nth-child(5) {
  animation-delay: 0.5s;
}

li:nth-child(6) {
  animation-delay: 0.6s;
}

@keyframes slide-up {
  from {
    bottom: -20px;
    opacity: 0;
  }

  to {
    bottom: 0;
    opacity: 1;
  }
}
</style>