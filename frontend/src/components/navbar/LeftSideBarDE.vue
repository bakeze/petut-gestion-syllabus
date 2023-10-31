<script setup>
import { ref } from "vue"
import NavbarHeader from './NavbarHeader.vue'
const drawer = ref("false");
const emit = defineEmits(['drawl'])
console.log(drawer)
</script>


<template>
  <!-- ======= Sidebar ======= -->
  <NavbarHeader @draw="drawer = !drawer, emit('drawl', drawer)"></NavbarHeader>
  <aside id="sidebar" class="sidebar" v-if="drawer">
    <ul class="sidebar-nav" id="sidebar-nav">
      <li class="nav-item">
        <a class="nav-link ">
          <i class="bi bi-grid"></i>
          <RouterLink to="/dashde/dashresumede">
            <span>Dashboard</span>
          </RouterLink>
        </a>
      </li><!-- End Dashboard Nav -->

      <li class="nav-item">
        <a class="nav-link collapsed" data-bs-target="#components-nav" data-bs-toggle="collapse" href="#">
          <i class="bi bi-menu-button-wide"></i><span>UE</span><i class="bi bi-chevron-down ms-auto"></i>
        </a>
        <ul id="components-nav" class="nav-content collapse " data-bs-parent="#sidebar-nav">
          <li>
            <RouterLink to="/dashde/listue">
              <i class="bi bi-circle"></i><span> Lister UE</span>
            </RouterLink>
          </li>
          <li>
            <RouterLink to="/dashde/formue">
              <i class="bi bi-circle"></i><span>Ajouter UE</span>
            </RouterLink>
          </li>
          <li>
            <RouterLink to="/dashde/formue">
              <i class="bi bi-circle"></i><span>Fiche UE</span>
            </RouterLink>
          </li>
        </ul>
      </li><!-- End Components Nav -->

      <li class="nav-item">
        <a class="nav-link collapsed" data-bs-target="#forms-nav" data-bs-toggle="collapse" href="#">
          <i class="bi bi-journal-text"></i><span>Matières</span><i class="bi bi-chevron-down ms-auto"></i>
        </a>
        <ul id="forms-nav" class="nav-content collapse " data-bs-parent="#sidebar-nav">
          <li>
            <RouterLink to="/dashde/formue">
              <i class="bi bi-circle"></i><span>Lister Matière</span>
            </RouterLink>
          </li>
          <li>
            <RouterLink to="/dashde/formue">
              <i class="bi bi-circle"></i><span>Ajouter Matière</span>
            </RouterLink>
          </li>
          <li>
            <RouterLink to="/dashde/formue">
              <i class="bi bi-circle"></i><span>Fiche Enseignement</span>
            </RouterLink>
          </li>
        </ul>
      </li><!-- End Forms Nav -->

      <li class="nav-item">
        <a class="nav-link collapsed" data-bs-target="#tables-nav" data-bs-toggle="collapse" href="#">
          <i class="bi bi-layout-text-window-reverse"></i><span>Syllabus</span><i class="bi bi-chevron-down ms-auto"></i>
        </a>
        <ul id="tables-nav" class="nav-content collapse " data-bs-parent="#sidebar-nav">
          <li>
            <RouterLink to="/dashde/formue">
              <i class="bi bi-circle"></i><span>Cycle Etudiant</span>
            </RouterLink>
          </li>
          <li>
            <RouterLink to="/dashde/formue">
              <i class="bi bi-circle"></i><span>Cycle Apprentissage</span>
            </RouterLink>
          </li>
          <li>
            <RouterLink to="/dashde/formue">
              <i class="bi bi-circle"></i><span>Cycle Préparatoire</span>
            </RouterLink>
          </li>
        </ul>
      </li><!-- End Tables Nav -->

      <li class="nav-item">
        <a class="nav-link collapsed" data-bs-target="#charts-nav" data-bs-toggle="collapse" href="#">
          <i class="bi bi-bar-chart"></i><span>Historique des Syllabus</span><i class="bi bi-chevron-down ms-auto"></i>
        </a>
        <ul id="charts-nav" class="nav-content collapse " data-bs-parent="#sidebar-nav">
          <li>
            <RouterLink to="/dashde/formue">
              <i class="bi bi-circle"></i><span>Cycle Etudiant</span>
            </RouterLink>
          </li>
          <li>
            <RouterLink to="/dashde/formue">
              <i class="bi bi-circle"></i><span>Cycle Apprentissage</span>
            </RouterLink>
          </li>
          <li>
            <RouterLink to="/dashde/formue">
              <i class="bi bi-circle"></i><span>Cycle Préparatoire</span>
            </RouterLink>
          </li>
        </ul>
      </li><!-- End Charts Nav -->

      <li class="nav-item">
        <a class="nav-link collapsed" data-bs-target="#icons-nav" data-bs-toggle="collapse" href="#">
          <i class="bi bi-person"></i><span>Responsables d'années</span><i class="bi bi-chevron-down ms-auto"></i>
        </a>
        <ul id="icons-nav" class="nav-content collapse " data-bs-parent="#sidebar-nav">
          <li>
            <RouterLink to="/dashde/formue">
              <i class="bi bi-circle"></i><span>Cycle Etudiant</span>
            </RouterLink>
          </li>
          <li>
            <RouterLink to="/dashde/formue">
              <i class="bi bi-circle"></i><span>Cycle Apprentissage</span>
            </RouterLink>
          </li>
          <li>
            <RouterLink to="/dashde/formue">
              <i class="bi bi-circle"></i><span>Cycle Préparatoire</span>
            </RouterLink>
          </li>
        </ul>
      </li><!-- End Icons Nav -->

      <div class="text-center">
        <v-menu transition="scale-transition" origin="center center">
          <template v-slot:activator="{ props }">
            <v-btn dark color="primary" v-bind="props">
              Scale Transition
            </v-btn>
          </template>
          <v-list>
            <v-list-item v-for="n in 5" :key="n" @click="() => { }">
              <v-list-item-title v-text="'Item ' + n"></v-list-item-title>
            </v-list-item>
          </v-list>
        </v-menu>
      </div>


      <li class="nav-item">
        <a class="nav-link collapsed" href="pages-faq.html">
          <i class="bi bi-question-circle"></i>
          <span>F.A.Q</span>
        </a>
      </li><!-- End F.A.Q Page Nav -->

      <li class="nav-item">
        <a class="nav-link collapsed" href="pages-contact.html">
          <i class="bi bi-envelope"></i>
          <span>Contact</span>
        </a>
      </li><!-- End Contact Page Nav -->

      <li class="nav-item">
        <a class="nav-link collapsed" href="pages-register.html">
          <i class="bi bi-card-list"></i>
          <span>Register</span>
        </a>
      </li><!-- End Register Page Nav -->

      <li class="nav-item">
        <a class="nav-link collapsed" href="pages-login.html">
          <i class="bi bi-box-arrow-in-right"></i>
          <span>Login</span>
        </a>
      </li><!-- End Login Page Nav -->

      <li class="nav-item">

        <a class="nav-link collapsed">
          <i class="bi bi-box-arrow-in-left"></i>
          <span>
            <RouterLink to="/login">
              Deconnexion
            </RouterLink>
          </span>
        </a>
      </li><!-- End Login Page Nav -->


      <li class="nav-item">
        <a class="nav-link collapsed" href="pages-error-404.html">
          <i class="bi bi-dash-circle"></i>
          <span>Error 404</span>
        </a>
      </li><!-- End Error 404 Page Nav -->

      <li class="nav-item">
        <a class="nav-link collapsed" href="pages-blank.html">
          <i class="bi bi-file-earmark"></i>
          <span>Blank</span>
        </a>
      </li><!-- End Blank Page Nav -->

    </ul>

  </aside><!-- End Sidebar-->
</template>

<style scoped></style>