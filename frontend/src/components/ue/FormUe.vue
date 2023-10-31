<script setup>
import { ref } from "vue";
import { reactive, onMounted } from "vue";
import ClassicEditor from '@ckeditor/ckeditor5-build-classic';
import { Form, Field } from 'vee-validate';
import * as Yup from 'yup';

// let prerequis;
// let intitule;
// let codeue = ref('')
// const creditects = ref()
// const numsemestre = ref()
// const annees = ref()

// const schema = Yup.object().shape({
//   intitule: Yup.string()
//         .required('intitule is required'),
//   codeue: Yup.string()
//         .required('codeue is required'),
//   creditects: Yup.string()
//         .required('creditects is required'),
//   prerequis: Yup.string()
//         .required('prerequis of Birth is required'),
//   numsemestre: Yup.string()
//         .required('numsemestre is required'),
//   annee: Yup.string()
//         .required('annee is required'),
// });

let data = reactive({
  s1: 1,
  s2: 2,
  matieres: [],
  annees: [],
})

// function ListMatieres() {
//   //appel des ues
//   fetch("/api/matieres")
//     .then((response) => {
//       if (!response.ok) { // status != 2XX
//         throw new Error(`code d'erreur = ${response.status}`);
//       }
//       return response.json();
//     })
//     .then((json) => {
//       json._embedded.matieres.forEach(e => {
//         data.matieres.push(e.intitule);
//     });
//      console.log(data.matieres);
//     })
//     .catch((error) => alert(error));
//     //appel des matiere de l'ue
// }


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
        data.annees.push(e.annee);
      });
      console.log("Annee : ", data.annees);
    })
    .catch((error) => alert(error));
  //appel des matiere de l'ue
}

const snackbar = ref(false);
const message = ref('');
const timeout = ref(3000);
const color = ref('success');

function ajouterUe(values) {
  // alert('SUCCESS!! :-)\n\n' + JSON.stringify(values, null, 4));
  // console.log("données à ajouter ! ",data.formulaire);
  // console.log("données à test ! ",JSON.stringify(values));
  // Ajouter un pays avec les données du formulaire
  const options = {
    method: "POST", // Verbe HTTP POST pour ajouter un enregistrement
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(values),
  };
  fetch("/rest/UeAddControler", options)
    .then((response) => {
      if (!response.ok) { // status != 2XX
        throw new Error(`code d'erreur = ${response.status}`);
      }
      return response.json();
    })
    .then((json) => {
      message.value = `L'UE ${json.id} a été ajouté.`;
      snackbar.value = true;
      // savingSuccessful = true;
      // data.formulaire = {...schema};
    })
    .catch(error => {
      message.value = 'Error fetching data';
      color.value = 'error';
      snackbar.value = true;
    });
}



// onMounted(ListMatieres);

const schema = Yup.object().shape({
  intitule: Yup.string()
    .required('intitule obligatoire ! '),
  codeue: Yup.string()
    .required('Code Ue Obligatoire ! '),
  creditects: Yup.number().required('Credit Ects Obligatoire ! ').min(0, 'Les credits ne peuvent pas être négatives '),
  prerequis: Yup.string()
    .required('Prerequis Obligatoire ! '),
  numsemestre: Yup.number()
    .required('Numéros du semestre Obligatoire !'),
  annee: Yup.string()
    .required('Annee Obligatoire'),
  formation: Yup.string()
    .required('Formation  Obligatoire'),
});

const formData = reactive({
  prerequis: '',
  anneeSelectionne: '',
});

function onSubmit(values) {
  // display form values on success
  alert('SUCCESS!! :-)\n\n' + JSON.stringify(values, null, 4));

}

onMounted(ListAnnee);
let savingSuccessful = false
</script>
<template>
  <div class="card">
    <div class="card-body">
      <v-icon icon="mdi-book-minus-outline" class="text center" />
      <v-snackbar v-model="snackbar" :timeout="timeout" :color="color">{{ message }}</v-snackbar>
      <h5 class="card-title">Unité d'enseignement</h5>

      <v-alert v-if="savingSuccessful" type="success" title="Ue ajouter avec succes"
        text="Bonne continuation !"></v-alert>

      <Form @submit="ajouterUe" :validation-schema="schema" v-slot="{ errors }" v-if="!savingSuccessful">
        <div class="form-row">
          <div class="form-group col-5">
            <label>Intitule</label>
            <Field name="intitule" type="text" class="form-control" :class="{ 'is-invalid': errors.intitule }" />
            <div class="invalid-feedback">{{ errors.intitule }}</div>
          </div>
          <br>
          <div class="form-group col-5">
            <label>code de l'UE</label>
            <Field name="codeue" type="text" class="form-control" :class="{ 'is-invalid': errors.codeue }" />
            <div class="invalid-feedback">{{ errors.codeue }}</div>
          </div>
        </div>
        <br>
        <div class="form-row">
          <div class="form-group col">
            <label>Credit ECTS</label>
            <Field name="creditects" type="number" class="form-control" :class="{ 'is-invalid': errors.creditects }" />
            <div class="invalid-feedback">{{ errors.creditects }}</div>
          </div>
          <br>
          <div class="form-group col">
            <label>Prerequis de l'UE</label>
            <Field name="prerequis" type="text" class="form-control" :class="{ 'is-invalid': errors.prerequis }" />
            <!-- <ckeditor  :editor="ClassicEditor"  ></ckeditor> -->
            <div class="invalid-feedback">{{ errors.prerequis }}</div>
          </div>
        </div>
        <br>
        <div class="form-group col">
          <label>Numéro du semestre</label>
          <Field name="numsemestre" as="select" class="form-control" :class="{ 'is-invalid': errors.numsemestre }">
            <option disabled value="">Choisir un semestre</option>
            <option value="1">1</option>
            <option value="2">2</option>
          </Field>
          <div class="invalid-feedback">{{ errors.numsemestre }}</div>
        </div>
        <br>
        <div class="form-group col">
          <label>Année de l'UE</label>
          <Field name="annee" as="select" class="form-control" :class="{ 'is-invalid': errors.annee }">
            <option disabled value="">Choisir une année</option>
            <option v-for=" anne in data.annees" :key="anne.id" :value="anne">{{ anne }}</option>
          </Field>
          <div class="invalid-feedback">{{ errors.annee }}</div>
        </div>
        <br>
        <div class="form-group col">
          <label>Fomation</label>
          <Field name="formation" as="select" class="form-control" :class="{ 'is-invalid': errors.formation }">
            <option disabled value="">Choisir une formation</option>
            <option value="FIA-3">FIA-3</option>
            <option value="FIA-4">FIA-4</option>
            <option value="FIA-5">FIA-5</option>
            <option disabled value="FIA-5">-------------------</option>
            <option value="FIE-1">FIE-1</option>
            <option value="FIE-2">FIE-2</option>
            <option value="FIE-3">FIE-3</option>
            <option value="FIE-4">FIE-4</option>
            <option value="FIE-5">FIE-5</option>
          </Field>
          <div class="invalid-feedback">{{ errors.formation }}</div>
        </div>
        <br>
        <div class="form-group">
          <button type="submit" class="btn btn-primary mr-1">Ajouter</button>
          <button type="reset" class="btn btn-secondary">Supprimer</button>
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