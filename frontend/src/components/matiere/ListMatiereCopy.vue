<script setup>
import { ref, reactive, onMounted, computed } from "vue";
import { Form, Field } from 'vee-validate';
import * as Yup from 'yup';

let data = reactive({
  matiere: [],
  ue: [],
  intituleErreurs: [],
  utilisateur: [],
});

const totalEncadre = computed(() => data.matiere.heurecm + data.matiere.heuretd)
const dialog = ref(false)
let indexmatiere = ref(null)
const matiereSelectionne = ref(null)

const snackbar = ref(false);
const message = ref('');
const timeout = ref(3000);
const color = ref('success');


function modifyMatiere(index) {
  console.log(index)
  indexmatiere = index;
  if (data.matiere.length >= index) {
    matiereSelectionne.value = data.matiere[index - 1];
    dialog.value = true;
  }
}

function enregistrer() {
  data.matiere[matiereSelectionne.value] = { ...matiere.value };
  console.log("Valeur a modifer : ", matiere.ue[matiereSelectionne.value]);
  dialog.value = false;
}

const schema = Yup.object().shape({
  id: Yup.number()
    .required('id obligatoire ! '),
  intitule: Yup.string()
    .required('intitule obligatoire ! '),
  codematiere: Yup.string()
    .required('Code Matière Obligatoire ! '),
  heurecm: Yup.number().required('heuresCM Obligatoire ! ').min(0, 'heuresCM ne peut pas être ingérieurs à 0'),
  heuretd: Yup.number().required('heuresTD Obligatoire ! ').min(0, 'heuresTD ne peuvent pas être ingérierue à 0'),
  foad: Yup.number().required('Formation à distance Obligatoire').min(0, 'foad ne peut pas être ingérierue à 0'),
  descripenseignement: Yup.string()
    .required('description enseignement obligatoire ! '),
  objectifpedagogique: Yup.string()
    .required('Objectif pedagogique Obligatoire ! '),
  ue: Yup.string()
    .required('unite enseignement Obligatoire ! '),
  utilisateur: Yup.string()
    .required('utilisateur Obligatoire ! '),
});


// Fonction pour modifier la matière
function modifierMatiere(values) {
  alert('SUCCESS!! :-)\n\n' + JSON.stringify(values, null, 4));
  console.log("données à ajouter ! ", data.formulaire);
  console.log("données à test ! ", JSON.stringify(values));

  const options = {
    method: "PUT", // Verbe HTTP POST pour ajouter un enregistrement
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(values),
  };
  fetch("/rest/ModifierMatiere", options)
    .then((response) => {
      if (!response.ok) { // status != 2XX
        throw new Error(`code d'erreur = ${response.status}`);
      }
      return response.json();
    })
    .then((json) => {
      message.value = `La matière ${json.id} a été modifiée.`;
      snackbar.value = true;
      refresh();
      // savingSuccessful = true;
      // data.formulaire = {...schema};
    })
    .catch(error => {
      message.value = 'Error fetching data';
      color.value = 'error';
      snackbar.value = true;
    });
}



const selected = ref('A')
const headers = [
  'Intitule',
  'Code Matière',
  'Heures CM',
  'Heures TD',
  'foad',
  'Total ecadré',
]

function refresh() {
  //appel des matières
  fetch("/api/matieres")
    .then((response) => {
      if (!response.ok) { // status != 2XX
        throw new Error(`code d'erreur = ${response.status}`);
      }
      return response.json();
    })
    .then((json) => {
      data.matiere = json._embedded.matieres;
      console.log("matiere ==: ", data.matiere);
    })
    .catch((error) => alert(error));
  //appel des matiere
}

function ListUsers() {
  //appel des ues
  fetch("/api/utilisateurs")
    .then((response) => {
      if (!response.ok) { // status != 2XX
        throw new Error(`code d'erreur = ${response.status}`);
      }
      return response.json();
    })
    .then((json) => {
      json._embedded.utilisateurs.forEach(e => {
        data.utilisateur.push(e);
      });
      console.log("Annee : ", data.utilisateur);
    })
    .catch((error) => alert(error));
  //appel des matiere de l'ue
}

function refreshue() {
  //appel des ues et matieres
  Promise.all([
    fetch("/api/ues"),
    fetch("/api/matieres")
  ])
    .then(responses => Promise.all(responses.map(response => response.json())))
    .then(jsons => {
      const ues = jsons[0]._embedded.ues;
      const matieres = jsons[1]._embedded.matieres;
      const ueMatieres = {};

      matieres.forEach(matiere => {
        if (matiere.ue_id) {
          ueMatieres[matiere.ue_id] = matiere;
        }
      });

      ues.forEach(ue => {
        const matiere = ueMatieres[ue.id];
        if (matiere) {
          ue.matiere = matiere;
        }
      });

      data.ue = ues;
      console.log("ues ==: ", data.ue);
    })
    .catch((error) => alert(error));
  //appel des ues et matieres
}

// function refreshue() {
//   //appel des ues
//   fetch("/api/ues")
//     .then((response) => {
//       if (!response.ok) { // status != 2XX
//         throw new Error(`code d'erreur = ${response.status}`);
//       }
//       return response.json();
//     })
//     .then((json) => {
//       data.ue = json._embedded.ues;
//       console.log("ues ==: ", data.ue);
//     })
//     .catch((error) => alert(error));
//   //appel des ues
// }

// function refreshue() {
//   //appel des ues
//   fetch("/api/ues")
//     .then((response) => {
//       if (!response.ok) { // status != 2XX
//         throw new Error(`code d'erreur = ${response.status}`);
//       }
//       return response.json();
//     })
//     .then((json) => {
//       data.ue = json._embedded.ues;
//       console.log("ues ==: ", data.ue);

//       // faire la jointure entre les matières et les UE
//       data.matiere.map(matiere => {
//         const ue = data.ue.find(ue => ue.id === matiere.ue.id);
//         matiere.formation = ue.formation;
//       });
//     })
//     .catch((error) => alert(error));
//   //appel des ues
// }

onMounted(refresh);
onMounted(refreshue);
onMounted(ListUsers);
</script>

<template>
  <div class="menu-container">

    <div class="menu">
      <li class="item" id="mn1" color="primary">
        <a class="btn" href="#mn1">Trier par FIA</a>
        <div class="submenu">
          <a href="#" @click.prevent="ToutAfficher()">Toutes
            les FIA</a>
        </div>
        <div class="submenu">
          <a href="#" @click.prevent="filterByFormation('FIA-3')">FIA-3</a>
          <a href="#" @click.prevent="filterByFormation('FIA-4')">FIA-4</a>
          <a href="#" @click.prevent="filterByFormation('FIA-5')">FIA-5</a>
        </div>
      </li>
    </div>
    <div class="menu3">
      <li class="item" id="mn2">
        <a class="btn" href="#mn2">Trier par FIE</a>
        <div class="submenu">
          <a href="#" @click.prevent="ToutAfficher()">Tou
            les FIE</a>
        </div>
        <div class="submenu">
          <a href="#" @click.prevent="filterByFormation('FIE-1')">FIE-1</a>
          <a href="#" @click.prevent="filterByFormation('FIE-2')">FIE-2</a>
          <a href="#" @click.prevent="filterByFormation('FIE-3')">FIE-3</a>
          <a href="#" @click.prevent="filterByFormation('FIE-4')">FIE-4</a>
          <a href="#" @click.prevent="filterByFormation('FIE-5')">FIE-5</a>
        </div>
      </li>
    </div>
  </div>
  <VCard>
    <VTable :headers="headers" :items="data" class="table-rounded" hide-default-footer disable-sort>
      <thead>
        <tr>
          <th v-for="header in headers" :key="header" class="text-center">
            {{ header }}
          </th>
        </tr>
      </thead>

      <tbody>
        <tr v-for="matiere in data.matiere" :key="matiere.id">
          <!-- name -->
          <td>
            <div class="d-flex">
              <div class="flex-grow-1">
                <span class="d-block font-weight-semibold text--primary text-truncate">{{ matiere.intitule }}</span>
              </div>
              <div>
                <v-icon class="fas fa-trash-alt" :class="{ 'mr-1': true }" @click="deleteUE(ue.id)" color="black" dark
                  size="20" block rounded elevation="x-4" align-end hover></v-icon>

                <v-icon class="fas fa-edit" :class="{ 'ml-1': true }" @click="modifyMatiere(matiere.id)" color="black"
                  dark size="20" wblock rounded elevation="x-4" align-end hover></v-icon>

                <v-dialog v-model="dialog" max-width="600px">
                  <v-card>
                    <v-card-title>
                      Modifier les informations de la matière
                    </v-card-title>
                    <v-snackbar v-model="snackbar" :timeout="timeout" :color="color">{{ message
                    }}</v-snackbar>
                    <v-card-text>
                      <Form @submit="modifierMatiere" :validation-schema="schema" v-slot="{ errors }">
                        <div class="form-row">
                          <div class="form-group col-5">
                            <!-- <label>Id</label> -->
                            <Field name="id" type="text" class="form-control" :value="indexmatiere"
                              :class="{ 'is-invalid': errors.id }" />
                            <div class="invalid-feedback">{{ errors.id }}</div>
                          </div>
                        </div>
                        <br>
                        <div class="form-row">
                          <div class="form-group col-5">
                            <label>Intitulé</label>
                            <Field name="intitule" class="form-control" :value="matiereSelectionne.intitule"
                              :class="{ 'is-invalid': errors.intitule }" readOnly />
                            <div class="invalid-feedback">{{ errors.intitule }}</div>
                          </div>
                          <br>
                          <div class="form-group col-5">
                            <label>Code matière</label>
                            <Field name="codematiere" type="text" :value="matiereSelectionne.codematiere"
                              class="form-control" :class="{ 'is-invalid': errors.codematiere }" readOnly />
                            <div class="invalid-feedback">{{ errors.codematiere }}</div>
                          </div>
                        </div>
                        <br>
                        <div class="form-row">
                          <div class="form-group col">
                            <label>Heures cours majistraux(CM)</label>
                            <Field name="heurecm" type="number" :value="matiereSelectionne.heurecm" class="form-control"
                              :class="{ 'is-invalid': errors.heurecm }" />
                            <div class="invalid-feedback">{{ errors.heurecm }}</div>
                          </div>
                          <br>
                          <div class="form-group col">
                            <label>Heures travaux dirigés(TD)</label>
                            <Field name="heuretd" type="number" class="form-control" :value="matiereSelectionne.heuretd"
                              :class="{ 'is-invalid': errors.heuretd }" />
                            <!-- <ckeditor  :editor="ClassicEditor"  ></ckeditor> -->
                            <div class="invalid-feedback">{{ errors.heuretd }}</div>
                          </div>
                          <br>
                          <div class="form-group col">
                            <label>Formation à distance </label>
                            <Field name="foad" type="number" class="form-control" :value="matiereSelectionne.foad"
                              :class="{ 'is-invalid': errors.foad }" />
                            <div class="invalid-feedback">{{ errors.foad }}</div>
                          </div>
                          <br>
                          <div class="form-group col">
                            <label>Descriptif de l'enseignement</label>
                            <Field name="descripenseignement" type="text" class="form-control"
                              :value="matiereSelectionne.descripenseignement"
                              :class="{ 'is-invalid': errors.descripenseignement }" />
                            <!-- <ckeditor  :editor="ClassicEditor"  ></ckeditor> -->
                            <div class="invalid-feedback">{{ errors.descripenseignement }}</div>
                          </div>
                          <br>
                          <div class="form-group col">
                            <label>Objectifs pédagogiques</label>
                            <Field name="objectifpedagogique" type="text" class="form-control"
                              :value="matiereSelectionne.objectifpedagogique"
                              :class="{ 'is-invalid': errors.objectifpedagogique }" />
                            <!-- <ckeditor  :editor="ClassicEditor"  ></ckeditor> -->
                            <div class="invalid-feedback">{{ errors.objectifpedagogique }}</div>
                          </div>
                          <br>
                          <div class="form-group col">
                            <label>Professeurs en charge de la matière</label>
                            <Field name="utilisateur" as="select" class="form-control"
                              :class="{ 'is-invalid': errors.utilisateur }">
                              <option value="">Choisir un professeur</option>
                              <option v-for=" user in data.utilisateur" :key="user.id" :value="user.nom">{{ user.nom }}
                              </option>
                            </Field>
                            <div class="invalid-feedback">{{ errors.utilisateur }}</div>
                          </div>
                        </div>
                        <br>
                        <div class="form-group col">
                          <label>Unité d'enseignement de la matiere </label>
                          <Field name="ue" as="select" class="form-control" :value="matiereSelectionne.ue"
                            :class="{ 'is-invalid': errors.ue }" readOnly>
                            <option value="">Choisir une unité d'enseignement pour cette matière</option>
                            <option v-for=" ue in data.ue" :key="ue.id" :value="ue.intitule">{{ ue.intitule }}</option>
                          </Field>
                          <div class="invalid-feedback">{{ errors.ue }}</div>
                        </div>
                        <br>
                        <br>
                        <div class="form-group">
                          <button type="submit" class="btn btn-primary mr-1">Modifier</button>
                          <button type="reset" class="btn btn-secondary">Annuler</button>
                        </div>
                      </Form>
                    </v-card-text>
                  </v-card>
                </v-dialog>
              </div>
            </div>
          </td>


          <td class="text-center" v-text="matiere.codematiere" />



          <td class="text-center" v-text="matiere.heurecm" />
          <td class="text-center" v-text="matiere.heuretd" />
          <td class="text-center" v-text="matiere.foad" />
          <td class="text-center" v-text="matiere.heurecm + matiere.heuretd" />

          <td class="text-center">
          </td>
        </tr>
      </tbody>
    </VTable>
  </VCard>
</template>
<style scoped>
.menu-container .submenu {
  max-height: 0;
  overflow: hidden;
  transition: max-height 0.3s ease-out;
}

.menu-container .item:hover .submenu {
  max-height: 1000px;
  /* la hauteur maximale du sous-menu */
}

:root {
  font-family: Arial, Helvetica, sans-serif;
  font-size: 15px;
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

* {
  text-decoration: none;
  color: rgb(0, 0, 0);
  list-style: none;
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

body {
  display: flex;
  align-items: center;
  justify-content: center;
  min-height: 100vh;
  background: #eb8bf9;
  overflow: hidden;
}

.menu,
.menu2,
.menu3 {
  width: 200px;
  height: auto;
  background: #6200ee;
  border-radius: 8px;
  overflow: hidden;
  box-shadow: 0 0 10px rgba(113, 16, 92, 0.5);
  margin-left: 5%;
  margin-bottom: 10px;

}

.menu .btn {
  display: block;
  padding: 1rem;
  border-bottom: solid 1px #1b1b2f;
  border-top: solid 1px #1f4068;
  position: relative;
}

.menu .submenu {
  background: #ffffff;
  overflow: hidden;
  max-height: 0;
  transition: max-height .8s ease-out;
}

.menu .submenu a {
  display: block;
  padding: 1rem;
  position: relative;
}

.menu .submenu a::before {
  content: '';
  display: block;
  position: absolute;
  top: 0;
  left: 0;
  height: 100%;
  width: 5px;
  background: #e43f5a;
  opacity: 0;
  transition: all .5s;
}

.menu .submenu a:hover {
  padding-left: calc(1rem + 5px);
}

.menu .submenu a:hover::before {
  opacity: 1;
}

.item:target .submenu {
  max-height: 20rem;
}

.item>a {
  color: #ffffff;
}

.menu2 {
  width: 200px;
  height: auto;
  background: #6200ee;
  border-radius: 8px;
  overflow: hidden;
  box-shadow: 0 0 10px rgba(113, 16, 92, 0.5);
  margin-left: 60%;
  margin-bottom:
    -60px;

}

.menu2 .btn {
  display: block;
  padding: 1rem;
  border-bottom: solid 1px #1b1b2f;
  border-top: solid 1px #1f4068;
  position: relative;
}

.menu2 .submenu {
  background: #ffffff;
  overflow: hidden;
  max-height: 0;
  transition: max-height .8s ease-out;
}

.menu2 .submenu a {
  display: block;
  padding: 1rem;
  position: relative;
}

.menu2 .submenu a::before {
  content: '';
  display: block;
  position: absolute;
  top: 0;
  left: 0;
  height: 100%;
  width: 5px;
  background: #e43f5a;
  opacity: 0;
  transition: all .5s;
}

.menu2 .submenu a:hover {
  padding-left: calc(1rem + 5px);
}

.menu2 .submenu a:hover::before {
  opacity: 1;
}

/*Style pour menu3*/
.menu3 {
  width: 200px;
  height: auto;
  background: #6200ee;
  border-radius: 8px;
  overflow: hidden;
  box-shadow: 0 0 10px rgba(113, 16, 92, 0.5);
  margin-left: 35%;
  margin-bottom:
    10px;

}

.menu3 .btn {
  display: block;
  padding: 1rem;
  border-bottom: solid 1px #1b1b2f;
  border-top: solid 1px #1f4068;
  position: relative;
}

.menu3 .submenu {
  background: #ffffff;
  overflow: hidden;
  max-height: 0;
  transition: max-height .8s ease-out;
}

.menu3 .submenu a {
  display: block;
  padding: 1rem;
  position: relative;
}

.menu3 .submenu a::before {
  content: '';
  display: block;
  position: absolute;
  top: 0;
  left: 0;
  height: 100%;
  width: 5px;
  background: #e43f5a;
  opacity: 0;
  transition: all .5s;
}

.menu3 .submenu a:hover {
  padding-left: calc(1rem + 5px);
}

.menu3 .submenu a:hover::before {
  opacity: 1;
}

/**/
.menu-container {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.menu,
.menu2,
.menu3 {
  list-style: none;
  margin: 0;
  padding: 0;
  margin-bottom: 10px;
}
</style>