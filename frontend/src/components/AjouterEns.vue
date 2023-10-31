<script setup>
import { ref } from "vue";
import { reactive, onMounted } from "vue";
import ClassicEditor from '@ckeditor/ckeditor5-build-classic';

const utilisateur = {
     id: "",
     nom: "",
     prenom:"",
     username:"",
     role:"",
     annee:2023,
     matiere:[]
}

let data = reactive({
        matieres:[],
        annees: [],
        formulaire:{...utilisateur},
        filieres: []
      })

function ListMatieres() {
  //appel des ues
  fetch("/api/matieres")
    .then((response) => {
      if (!response.ok) { // status != 2XX
        throw new Error(`code d'erreur = ${response.status}`);
      }
      return response.json();
    })
    .then((json) => {
      json._embedded.matieres.forEach((m) => {
        data.matieres.push(m.inititule) ;

      });
      console.log("matieres : ",data.matieres);
    })
    .catch((error) => alert(error));
    //appel des matiere de l'ue
}


function ListAnnee() {
  //appel des ues
  fetch("/api/annees")
    .then((response) => {
      if (!response.ok) { // status != 2XX
        throw new Error(`code d'erreur = ${response.status}`);
      }
      return response.json();
    })
    .then((json) => {
      json._embedded.annees.forEach(e => {
        data.annees.push(e.annee) ;
      });
      console.log("Annee : ",data.annees);
    })
    .catch((error) => alert(error));
    //appel des matiere de l'ue
}




onMounted(ListMatieres);
onMounted(ListAnnee);

</script>
<template>
    <div class="card">
            <div class="card-body">
              <v-icon icon="mdi-book-minus-outline" 
              class="text center"
              /> 
              <h5 class="card-title">Ajout d'enseignant</h5>
                  <VForm @submit.prevent="ajouterEns"  
                    class="row g-3"
                    >
                    <VRow>
                      <VCol cols="12">
                        <VTextField
                          v-model="data.formulaire.id"
                          label="numéro identifiant"
                        />
                      </VCol>

                      <VCol cols="12">
                        <VTextField
                          v-model="data.formulaire.nom"
                          label="Nom"
                          placeholder="Nom"
                        />
                      </VCol>

                      <VCol cols="12">
                        <VTextField
                          v-model="data.formulaire.prenom"
                          label="Prénom"
                          type="number"
                          placeholder="Prénom"
                        />
                      </VCol>

                      <VCol cols="12"
                      class="d-flex mb-2  ">
                        <v-radio-group inline
                        v-model="data.formulaire.role"
                        >
                          <v-radio
                          label="Semestre 1" 
                          value="1" 
                          ></v-radio>
                          <v-radio 
                          label="Semestre 2" 
                          value="2"
                          ></v-radio>
                        </v-radio-group>
                        <v-select
                        v-model="data.formulaire.annee"
                        class="w-12 mr-15"
                        label="Annee"
                        :items="data.annees"
                        item-value="id"
                        ></v-select>
                        <v-select
                        v-model="data.formulaire.filiere"
                        class="w-12 mr-15"
                        label="Filière"
                        :items="data.filieres"
                        item-value="text"
                        ></v-select>
                      </VCol>
                      <VCol cols="12" 
                       class="m-1"
                       >
                        <h3 class="text-h5 font-weight-semibold mb-2">
                          Enseignement
                        </h3>
                      <VCol cols="12" 
                       class="m-1">
                        <v-select
                        v-model="data.formulaire.matiere"
                        :items="data.matieres"
                        item-text="intitule"
                        chips
                        label="Matières"
                        multiple
                      ></v-select>
                      </VCol>

                        <VBtn type="submit">
                          Enregister
                        </VBtn>

                        <VBtn
                          type="reset"
                          color="secondary"
                          variant="tonal"
                        >
                          Effacer
                        </VBtn>
                      </VCol>

                    </VRow>
                  </VForm>
            </div>
          </div>

</template>

<style scoped>
    
</style>