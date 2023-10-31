<script setup>
import { ref } from "vue"
import NavbarHeader from '../../components/navbar/NavbarHeader.vue'
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
            <RouterLink to="/dashde/listerue1">
              <i class="bi bi-circle"></i><span> Lister UE</span>
            </RouterLink>
          </li>
          <li>
            <RouterLink to="/dashde/formue">
              <i class="bi bi-circle"></i><span>Ajouter UE</span>
            </RouterLink>
          </li>
          <li>
            <RouterLink to="/dashde/ficheue">
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
            <RouterLink to="/dashde/listmatiere">
              <i class="bi bi-circle"></i><span>Lister Matière</span>
            </RouterLink>
          </li>
          <li>
            <RouterLink to="/dashde/formmatiere">
              <i class="bi bi-circle"></i><span>Ajouter Matière</span>
            </RouterLink>
          </li>
          <li>
            <RouterLink to="/dashde/fichematiere">
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
        <a class="nav-link collapsed" data-bs-target="#user-nav" data-bs-toggle="collapse" href="#4">
          <i class="bi bi-layout-text-window-reverse"></i><span>Utilisateur</span><i
            class="bi bi-chevron-down ms-auto"></i>
        </a>
        <ul id="user-nav" class="nav-content collapse " data-bs-parent="#sidebar-nav">
          <li>
            <RouterLink to="/dashde/listerprofesseur">
              <i class="bi bi-circle"></i><span>Lister Professeurs</span>
            </RouterLink>
          </li>
          <li>
            <RouterLink to="/dashde/listerresponsablean">
              <i class="bi bi-circle"></i><span>Lister Responsables d'année</span>
            </RouterLink>
          </li>
          <li>
            <RouterLink to="/dashde/formuser1">
              <i class="bi bi-circle"></i><span>Ajouter Utilisateur</span>
            </RouterLink>
          </li>
        </ul>
      </li>
      <li class="nav-item">
        <a class="nav-link collapsed" data-bs-target="#charts-nav" data-bs-toggle="collapse" href="#">
          <i class="bi bi-bar-chart"></i><span>Historique des Syllabus</span><i class="bi bi-chevron-down ms-auto"></i>
        </a>
        <ul id="charts-nav" class="nav-content collapse " data-bs-parent="#sidebar-nav">
          <li>
            <RouterLink to="/dashde/formue">
              <i class="bi bi-circle"></i><span>2021</span>
            </RouterLink>
          </li>
          <li>
            <RouterLink to="/dashde/formue">
              <i class="bi bi-circle"></i><span>2022</span>
            </RouterLink>
          </li>
          <li>
            <RouterLink to="/dashde/formue">
              <i class="bi bi-circle"></i><span>2023</span>
            </RouterLink>
          </li>
        </ul>
      </li><!-- End Charts Nav -->




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






    </ul>

  </aside><!-- End Sidebar-->
</template>

<style scoped></style>