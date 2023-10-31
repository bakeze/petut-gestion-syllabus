<script setup>
import { reactive, onMounted } from "vue";


let data = reactive({
  ue: [],
  matieres: [],
  intervenants: []
});


function refresh() {
  //appel des ues
  fetch("/api/ues")
    .then((response) => {
      if (!response.ok) { // status != 2XX
        throw new Error(`code d'erreur = ${response.status}`);
      }
      return response.json();
    })
    .then((json) => {
      json._embedded.ues.forEach(ue => {
        if (ue.numsemestre == 1) {
            data.ue.push(ue);
        }
        console.log(data.ue);
    });
      
    })
    .catch((error) => alert(error));
    //appel des matiere de l'ue
}

function matiereue(id){
  console.log(id);
  fetch("/api/ues/"+id+"/matiere")
    .then((response) => {
      if (!response.ok) { // status != 2XX
        throw new Error(`code d'erreur = ${response.status}`);
      }
      return response.json();
    })
    .then((json) => {
      data.matieres = json._embedded.matieres;
      console.log("Ue : "+id,"==>", data.matieres);

    })
    .catch((error) => alert(error));

    //appel des matiere de l'ue
}

function intervenantmatiere(id){
  console.log(id);
  fetch("/api/matieres/"+id+"/utilisateur")
    .then((response) => {
      if (!response.ok) { // status != 2XX
        throw new Error(`code d'erreur = ${response.status}`);
      }
      return response.json();
    })
    .then((json) => {
      data.intervenants = json;
      console.log("id matiere : "+id,"==>", data.intervenants);
    })
    .catch((error) => alert(error));
}

onMounted(refresh);

</script>

<template>
     <!-- ======= Features Section ======= -->
     <section id="features" class="features">
      <div class="container" data-aos="fade-up">
       
        <ul class="nav nav-tabs row gy-4 d-flex">

          <li class="nav-item col-2 col-md-1 col-lg-3" v-for="ue in data.ue" :key="ue.id "   >
            <a id ="ue"  class="nav-link active show"
              @click="matiereue(ue.id)" data-bs-toggle="tab" 
              >
              <h4>{{ ue.codeue }} - {{ ue.intitule }}</h4> 
            </a>
          </li><!-- End Tab 1 Nav -->
        </ul>
        
        <div class="tab-content">

          <div class="tab-pane active show" id="tab-1">
            <div class="row gy-4">
              <div class="col-lg-8 order-2 order-lg-1" data-aos="fade-up" data-aos-delay="100" v-for="matiere in data.matieres" :key="matiere.id"    >
                <h3>{{matiere.intitule}}</h3>
                <h4> <strong>Code Matière :</strong> {{matiere.codematiere}}  <strong>Heure CM :</strong> {{matiere.heurecm}}   <strong>Heure TD :</strong> {{matiere.heuretd}}   </h4>
                <p class="fst-italic">
                  {{matiere.descripenseignement}}
                </p>
                <h2>Objectifs pédagogiques</h2>
                <ul>
                  <li><i class="bi bi-check-circle-fill"></i> {{matiere.objetcifpedagogique}}</li>
                </ul>

              </div>
              <!-- <div class="col-lg-4 order-1 order-lg-2 text-center" data-aos="fade-up" data-aos-delay="200">
                <img src="assets/img/features-1.svg" alt="" class="img-fluid">
              </div> -->
            </div>
          </div><!-- End Tab Content 1 -->

        </div>

      </div>
    </section><!-- End Features Section -->
</template>

<style scoped>
/*--------------------------------------------------------------
# Disable aos animation delay on mobile devices
--------------------------------------------------------------*/
@media screen and (max-width: 768px) {
  [data-aos-delay] {
    transition-delay: 0 !important;
  }
}


</style>