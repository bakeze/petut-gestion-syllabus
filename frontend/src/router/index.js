import { createRouter, createWebHistory } from 'vue-router'
import Home from '../views/Home.vue'
import Countries from '../views/Countries.vue'
import About from '../views/About.vue'
import Ajax from '../views/Ajax.vue'
import Cyclepreparatoire from '../views/Cyclepreparatoire.vue'
import CycleApprentissage from '../views/CycleApprentissage.vue'
import CycleIngenieur from '../views/CycleIngenieur.vue'
import Login from '../views/Login.vue'
import Register from '../views/Register.vue'
import DashDe from '../views/DirecteurEtudes.vue'
import Index from '../components/Index.vue'
import FormUe from '../components/ue/FormUe.vue'
import DashResumeDE from '../components/dashboardde/DashboardResumeDE.vue'
import FormMatiere from '../components/matiere/FormMatiere.vue'
import ficheMatiere from '../components/matiere/ficheMatiere.vue'
import FicheUe from '../components/ue/FicheUe.vue'
import ListMatiere from '../components/matiere/ListMatiere.vue'
import ListerProfesseur from '../components/user/ListerProfesseur.vue'
import ListerResponsableAn from '../components/user/ListerResponsableAn.vue'
import Formuser1 from '../components/user/Formuser1.vue'
import semestrefia1 from '../components/semestres/FIA/Semestre1.vue'
import semestrefia2 from '../components/semestres/FIA/Semestre2.vue'
import semestrefia3 from '../components/semestres/FIA/Semestre3.vue'
import semestrefia4 from '../components/semestres/FIA/Semestre4.vue'
import semestrefia5 from '../components/semestres/FIA/Semestre5.vue'
import semestrefia6 from '../components/semestres/FIA/Semestre6.vue'
import Allsemestrefia from '../components/semestres/FIA/AllSemestres.vue'
import listerUE1 from '../components/ue/listerUE1.vue'
import dashRa from '../components/DashboardResponsableAnnee.vue'
import dashens from '../components/DashboardEnseignant.vue'
import Allsemestrefie from '../components/semestres/FIE/AllSemestres.vue'
import semestrefie1 from '../components/semestres/FIE/Semestre1.vue'
import semestrefie2 from '../components/semestres/FIE/Semestre2.vue'
import semestrefie3 from '../components/semestres/FIE/Semestre2.vue'
import semestrefie4 from '../components/semestres/FIE/Semestre2.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  // Associe des chemins d'accès au composant vue à afficher
  routes: [
    {
      path: '/CycleApprentissage',
      name: 'CycleApprentissage',
      component: CycleApprentissage,
      children: [
        {
          path: '',
          component: Allsemestrefia
        },
        {
          path: 'semestre1',
          component: semestrefia1
        },
        {
          path: 'semestre2',
          component: semestrefia2
        },
        {
          path: 'semestre3',
          component: semestrefia3
        },

        {
          path: 'semestre4',
          component: semestrefia4
        },
        {
          path: 'semestre5',
          component: semestrefia5
        },
        {
          path: 'semestre6',
          component: semestrefia6
        }
      ]
    },
    {
      path: '/CycleIngenieur',
      name: 'CycleIngenieur',
      component: CycleIngenieur
    },
    {
      path: '/cyclepreparatoire',
      name: 'cyclepreparatoire',
      component: Cyclepreparatoire,
      children: [
        {
          path: '',
          component: Allsemestrefie
        },
        {
          path: 'semestre1',
          component: semestrefie1
        },
        {
          path: 'semestre2',
          component: semestrefie2
        },
        {
          path: 'semestre3',
          component: semestrefie3
        },
        {
          path: 'semestre4',
          component: semestrefie4
        },
      ]
    },
    {
      path: '/login',
      name: 'login',
      component: Login
    },
    {
      path: '/register',
      name: 'register',
      component: Register
    },
    {
      path: '/dashRa',
      name: 'dashRa',
      component: dashRa,
      children: [
        {
          path: '',
          component: DashResumeDE
        },
        {
          path: 'dashresumera',
          component: DashResumeDE
        },
        {
          path: 'listerUE1',
          component: listerUE1
        },
        {
          path: 'formUe',
          component: FormUe
        },
        {
          path: 'formmatiere',
          component: FormMatiere
        },
        {
          path: 'listmatiere',
          component: ListMatiere
        },
        {
          path: 'listerprofesseur',
          component: ListerProfesseur
        },
        {
          path: 'listerresponsablean',
          component: ListerResponsableAn
        },
        {
          path: 'formuser1',
          component: Formuser1
        },
        {
          path: 'fichematiere',
          component: ficheMatiere
        },
        {
          path: 'ficheue',
          component: FicheUe
        },
      ]

    },
    {
      path: '/dashens',
      name: 'dashens',
      component: dashens,
      children: [
        {
          path: 'dashens',
          component: dashens
        },
        {
          path: 'formmatiere',
          component: FormMatiere
        },
        // {
        //   path: 'listmatiereens',
        //   component: ListMatiereEns
        // },
      ]
    },
    {
      path: '/dashde',
      name: 'dashde',
      component: DashDe,
      children: [
        {
          path: '',
          component: DashResumeDE
        },
        {
          path: 'dashresumede',
          component: DashResumeDE
        },
        {
          path: 'formmatiere',
          component: FormMatiere
        },
        {
          path: 'listmatiere',
          component: ListMatiere
        },

        {
          path: 'formue',
          component: FormUe
        },
        {
          path: 'listerUE1',
          component: listerUE1
        },
        {
          path: 'listerprofesseur',
          component: ListerProfesseur
        },
        {
          path: 'listerresponsablean',
          component: ListerResponsableAn
        },
        {
          path: 'formuser1',
          component: Formuser1
        },
        {
          path: 'fichematiere',
          component: ficheMatiere
        },
        {
          path: 'ficheue',
          component: FicheUe
        },
      ]
    },
    {
      path: '/',
      name: 'home',
      component: Home
    },
    {
      path: '/countries',
      name: 'countries',
      component: Countries
    },
    {
      path: '/ajax',
      name: 'ajax',
      component: Ajax
    },
    {
      path: '/index',
      name: 'index',
      component: Index
    },
    {
      path: '/about',
      name: 'about',
      component: About
    },

  ]
})

export default router
