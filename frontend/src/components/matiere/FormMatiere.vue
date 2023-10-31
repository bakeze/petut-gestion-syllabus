<script setup>
import { ref } from "vue";
import { reactive, onMounted } from "vue";
import ClassicEditor from '@ckeditor/ckeditor5-build-classic';
import { Form, Field } from 'vee-validate';
import * as Yup from 'yup';

let data = reactive({
  ue: [],
  utilisateurs: [],
})

function ListUe() {
  //appel des ues
  fetch("/api/ues")
    .then((response) => {
      if (!response.ok) { // status != 2XX
        throw new Error(`code d'erreur = ${response.status}`);
      }
      return response.json();
    })
    .then((json) => {
      json._embedded.ues.forEach((u) => {
        data.ue.push(u);
      });
      console.log("ue : ", data.ues);
    })
    .catch((error) => alert(error));
  //appel des matiere de l'ue
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
        data.utilisateurs.push(e);
      });
      console.log("Annee : ", data.utilisateurs);
    })
    .catch((error) => alert(error));
  //appel des matiere de l'ue
}



const snackbar = ref(false);
const message = ref('');
const timeout = ref(3000);
const color = ref('success');

function ajouterMatiere(values) {
  alert('SUCCESS!! :-)\n\n' + JSON.stringify(values, null, 4));
  console.log("données à ajouter ! ", data.formulaire);
  console.log("données à test ! ", JSON.stringify(values));
  // Ajouter un pays avec les données du formulaire
  const options = {
    method: "POST", // Verbe HTTP POST pour ajouter un enregistrement
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(values),
  };
  fetch("/rest/MatiereAddControler", options)
    .then((response) => {
      if (!response.ok) { // status != 2XX
        throw new Error(`code d'erreur = ${response.status}`);
      }
      return response.json();
    })
    .then((json) => {
      message.value = `L'Matiere ${json.id} a été ajouté.`;
      snackbar.value = true;
      // savingSuccessful = true;
      // data.formulaire = {...schema};
    })
    .catch(error => {
      message.value = error;
      color.value = 'error';
      snackbar.value = true;
    });

}

const schema = Yup.object().shape({
  intitule: Yup.string()
    .required('intitule obligatoire ! '),
  codematiere: Yup.string()
    .required('Code Matière Obligatoire ! '),
  heurecm: Yup.number().required('heuresCM Obligatoire ! ').min(0, 'heuresCM ne peuvent pas être ingérierue à 0'),
  heuretd: Yup.number().required('heuresTD Obligatoire ! ').min(0, 'heuresTD ne peuvent pas être ingérierue à 0'),
  foad: Yup.number().required('foad Obligatoire ! ').min(0, 'foad ne peut pas être ingérierue à 0'),
  descripenseignement: Yup.string()
    .required('description enseignement obligatoire ! '),
  objetcifpedagogique: Yup.string()
    .required('Objectif pedagogique Obligatoire ! '),
  ue: Yup.string()
    .required('unite enseignement Obligatoire ! '),
  utilisateur: Yup.string()
    .required('le professeurs est Obligatoire ! '),
});


function onSubmit(values) {
  // display form values on success
  alert('SUCCESS!! :-)\n\n' + JSON.stringify(values, null, 4));

}

onMounted(ListUe);
onMounted(ListUsers);

let savingSuccessful = false
</script>
<template>
  <div class="card">
    <div class="card-body">
      <v-icon icon="mdi-book-minus-outline" class="text center" />
      <v-snackbar v-model="snackbar" :timeout="timeout" :color="color">{{ message }}</v-snackbar>
      <h5 class="card-title">Enseignement</h5>
      <!--<Form v-slot="{ errors }">
                                          <v-container>
                                            <v-text-field v-model.trim="name" label="Nom" required></v-text-field>
                                            <v-text-field v-model.trim="email" label="E-mail" required></v-text-field>
                                            <v-textarea v-model.trim="message" label="Message" required></v-textarea>

                                            <div class="error" v-if="errors.any()">
                                              <p class="error-message" v-for="(error, key) in errors.all()" :key="key">{{ error }}</p>
                                            </div>

                                            <v-btn type="submit" :disabled="!$refs.form.valid">Envoyer</v-btn>
                                          </v-container>
                                        </Form>



                                                <VForm @submit="ajouterUe"
                                                :validation-schema="schema"
                                                v-slot="{ errors }"
                                                >
                                                <VRow>
                                                  <VCol cols="12">
                                                    <Field name="intitule" type="text" class="form-control" :class="{ 'is-invalid': errors.intitule }" v-slot="{ field }">
                                                    <VTextField
                                                    v-bind="field"
                                                    label="intitule" />
                                                    </Field>
                                                    <div name="field" class="invalid-feedback">{{errors.intitule}}</div>

                                                  </VCol> 
                                                   <VCol cols="12">
                                                    <VTextField
                                                      v-model="data.formulaire.codeue"
                                                      label="Code Ue"
                                                      placeholder="Code Ue"
                                                    />
                                                  </VCol>

                                                  <VCol cols="12">
                                                    <VTextField
                                                      v-model.number ="data.formulaire.creditects"
                                                      label="Crédit ECTS"
                                                      type="number"
                                                      placeholder="Crédit ECTS"
                                                    />
                                                  </VCol>

                                                  <VCol cols="12"
                                                  class="d-flex mb-2  ">
                                                    <v-radio-group inline
                                                    v-model="data.formulaire.numsemestre"
                                                    >
                                                      <v-radio
                                                      label="Semestre 1" 
                                                      :value="1"
                                                      ></v-radio>
                                                      <v-radio 
                                                      label="Semestre 2" 
                                                      :value="2"
                                                      ></v-radio>
                                                    </v-radio-group>
                                                    <v-select
                                                    v-model="data.formulaire.annee"
                                                    class="w-12 mr-15"
                                                    label="Annee"
                                                    :items="data.annees"></v-select>
                                                  </VCol>
                                                  <VCol cols="12" 
                                                   class="m-1"
                                                   >
                                                    <h3 class="text-h5 font-weight-semibold mb-2">
                                                      Prérequis de l'unité d'enseignement
                                                    </h3>
                                                   <ckeditor :editor="ClassicEditor" v-model="data.formulaire.prerequis"></ckeditor>
                                                  </VCol> 
                                                   <VCol
                                                    cols="12"
                                                    class="d-flex gap-4"
                                                  >
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
                                              </VForm>   -->
      <v-alert v-if="savingSuccessful" type="success" title="Ue ajouter avec succes"
        text="Bonne continuation !"></v-alert>
      <!-- <v-alert
                                                v-else
                                                type="error"
                                                title="l'ue n'a pas ete ajouter"
                                                text="Bonne continuation !"
                                              ></v-alert>                   -->
      <Form @submit="ajouterMatiere" :validation-schema="schema" v-slot="{ errors }" v-if="!savingSuccessful">
        <div class="form-row">
          <div class="form-group col-5">
            <label>Intitule</label>
            <Field name="intitule" type="text" class="form-control" :class="{ 'is-invalid': errors.intitule }" />
            <div class="invalid-feedback">{{ errors.intitule }}</div>
          </div>
          <br>
          <div class="form-group col-5">
            <label>Code de la matière</label>
            <Field name="codematiere" type="text" class="form-control" :class="{ 'is-invalid': errors.codematiere }" />
            <div class="invalid-feedback">{{ errors.codematiere }}</div>
          </div>
        </div>
        <br>
        <div class="form-row">
          <div class="form-group col">
            <label>heures cours majistrale(CM)</label>
            <Field name="heurecm" type="number" class="form-control" :class="{ 'is-invalid': errors.heurecm }" />
            <div class="invalid-feedback">{{ errors.heurecm }}</div>
          </div>
          <br>
          <div class="form-group col">
            <label>heures travaux diriger(TD)</label>
            <Field name="heuretd" type="number" class="form-control" :class="{ 'is-invalid': errors.heuretd }" />
            <!-- <ckeditor  :editor="ClassicEditor"  ></ckeditor> -->
            <div class="invalid-feedback">{{ errors.heuretd }}</div>
          </div>
          <br>
          <div class="form-group col">
            <label>Formation à distance (Foad)</label>
            <Field name="foad" type="number" class="form-control" :class="{ 'is-invalid': errors.foad }" />
            <!-- <ckeditor  :editor="ClassicEditor"  ></ckeditor> -->
            <div class="invalid-feedback">{{ errors.foad }}</div>
          </div>
          <br>
          <div class="form-group col">
            <label>Descriptif de l'enseignement</label>
            <Field name="descripenseignement" type="text" class="form-control"
              :class="{ 'is-invalid': errors.descripenseignement }" />
            <!-- <ckeditor  :editor="ClassicEditor"  ></ckeditor> -->
            <div class="invalid-feedback">{{ errors.descripenseignement }}</div>
          </div>
          <br>
          <div class="form-group col">
            <label>Objectif pédagogiques</label>
            <Field name="objetcifpedagogique" type="text" class="form-control"
              :class="{ 'is-invalid': errors.objetcifpedagogique }" />
            <!-- <ckeditor  :editor="ClassicEditor"  ></ckeditor> -->
            <div class="invalid-feedback">{{ errors.objetcifpedagogique }}</div>
          </div>

        </div>
        <br>
        <div class="form-group col">
          <label>Unité d'enseignement de la matiere </label>
          <Field name="ue" as="select" class="form-control" :class="{ 'is-invalid': errors.ue }">
            <option value="">Choisir une unité d'enseignement pour cette matière</option>
            <option v-for=" ue in data.ue" :key="ue.id" :value="ue.intitule">{{ ue.intitule }}</option>
          </Field>
          <div class="invalid-feedback">{{ errors.ue }}</div>
        </div>
        <br>
        <div class="form-group col">
          <label>Professeurs en charge de la matière</label>
          <Field name="utilisateur" as="select" class="form-control" :class="{ 'is-invalid': errors.utilisateur }">
            <option value="">Choisir un professeur</option>
            <option v-for=" user in data.utilisateurs" :key="user.id" :value="user.nom">{{ user.nom }}</option>
          </Field>
          <div class="invalid-feedback">{{ errors.utilisateur }}</div>
        </div>
        <br>
        <div class="form-group">
          <button type="submit" class="btn btn-primary mr-1">Ajouter</button>
          <button type="reset" class="btn btn-secondary">Effacer</button>
        </div>
      </Form>
    </div>

  </div>
</template>

<style scoped>
.card-form {
  padding: 2rem 1rem 0;
}

.input {
  display: flex;
  flex-direction: column-reverse;
  position: relative;
  padding-top: 1.5rem;
}

.input-label {
  color: #8597a3;
  position: absolute;
  top: 1.5rem;
  transition: .25s ease;
}

.input-field {
  border: 0;
  z-index: 1;
  background-color: transparent;
  border-bottom: 2px solid #eee;
  font: inherit;
  font-size: 1.125rem;
  padding: .25rem 0;
}

.input-field :focus,
.input-field :valid {
  outline: 0;
  border-bottom-color: #6658d3;
}

.input-field .input-label {
  color: #6658d3;
  transform: translateY(-1.5rem);
}

.action {
  margin-top: 2rem;
}

.action-button {
  font: inherit;
  font-size: 1.25rem;
  padding: 1em;
  width: 100%;
  font-weight: 500;
  background-color: #6658d3;
  border-radius: 6px;
  color: #FFF;
  border: 0;

}

.card-info {
  padding: 1rem 1rem;
  text-align: center;
  font-size: .875rem;
  color: #8597a3;
}

.card-info a {
  display: block;
  color: #6658d3;
  text-decoration: none;
}
</style>