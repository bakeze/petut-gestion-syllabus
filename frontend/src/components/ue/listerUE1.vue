<script setup>
import { ref, reactive, onMounted } from "vue";
import { Form, Field } from 'vee-validate';
import * as Yup from 'yup';

let data = reactive({
    ue: [],
    filteredUE: [], // Ajout d'un tableau pour stocker les UEs filtrées
    annees: [],
});

const dialog = ref(false)

const ueSelectionne = ref(null)

let indexue = ref(null)

function modifyUE(index) {
    console.log(index)
    indexue = index;
    if (data.ue.length >= index) {
        ueSelectionne.value = data.ue[index - 1];
        dialog.value = true;
    }
}

function enregistrer() {
    data.ue[ueSelectionne.value] = { ...ue.value };
    console.log("Valeur a modifer : ", data.ue[ueSelectionne.value]);
    dialog.value = false;
}


const headers = [
    'Intitule',
    'Code Ue',
    'Formation',
    'Credit ECTS',
    'Semestre',
]
function filterByFormation(formation) {
    data.filteredUE = data.ue.filter(ue => ue.formation === formation);
}
function filterByanne(anne) {
    data.filteredUE = data.annees.filter(anne => annees.anne === anne);
}
function ToutAfficher() {
    data.filteredUE = data.ue;
}
function ToutAfficherFIA() {
    data.filteredUE = data.ue.filter(ue => {
        const formationsToDisplay = ["FIA-3", "FIA-4", "FIA-5"];
        return formationsToDisplay.includes(ue.formation);
    });
}
function ToutAfficherFIE() {
    data.filteredUE = data.ue.filter(ue => {
        const formationsToDisplay = ["FIE-1", "FIE-2", "FIE-3", "FIE-4", "FIE-5"];
        return formationsToDisplay.includes(ue.formation);
    });
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
            data.ue = json._embedded.ues;
            data.filteredUE = data.ue; // Initialise filteredUE avec toutes les UEs
            console.log("Ue : ", data.ue);
        })
        .catch((error) => alert(error));
    //appel des matiere de l'ue
}

function supprimerUe(id) {
    // alert('SUCCESS!! :-)\n\n' + JSON.stringify(values, null, 4));
    // console.log("données à ajouter ! ", data.formulaire);
    // console.log("données à test ! ", JSON.stringify(values));
    //   Ajouter un pays avec les données du formulaire
    const options = {
        method: "PUT", // Verbe HTTP POST pour ajouter un enregistrement
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(values),
    };
    fetch("/rest/ModiferUe", options)
        .then((response) => {
            if (!response.ok) { // status != 2XX
                throw new Error(`code d'erreur = ${response.status}`);
            }
            return response.json();
        })
        .then((json) => {
            message.value = `L'UE ${json.id} a été modifier.`;
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



function modiferUe(values) {
  alert('SUCCESS!! :-)\n\n' + JSON.stringify(values, null, 4));
  console.log("données à ajouter ! ",data.formulaire);
  console.log("données à test ! ",JSON.stringify(values));
  
  const options = {
    method: "PUT", // Verbe HTTP POST pour ajouter un enregistrement
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(values),
  };
  fetch("/rest/ModiferUe", options)
    .then((response) => {
      if (!response.ok) { // status != 2XX
        throw new Error(`code d'erreur = ${response.status}`);
      }
      return response.json();
    })
    .then((json) => {
      message.value = `L'UE ${json.id} a été modifiée.`;
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

const schema = Yup.object().shape({
    id: Yup.number()
        .required('id obligatoire ! '),
    intitule: Yup.string()
        .required('intitule obligatoire ! '),
    codeue: Yup.string()
        .required('Code Ue Obligatoire ! '),
    creditects: Yup.number().required('Credit Ects Obligatoire ! ').min(0, 'Les credits ne peuvent pas être négatives '),
    prerequis: Yup.string()
        .required('Prerequis Obligatoire ! '),
    numsemestre: Yup.number()
        .required('Numéros du semestre Obligatoire !'),
    formation: Yup.string()
        .required('Formation  Obligatoire'),
});


onMounted(refresh);
onMounted(ListAnnee);

let isSubMenuActive = false;

const toggleSubMenu = () => {
    isSubMenuActive = !isSubMenuActive;
}


const closeSubMenu = (event) => {
    if (!event.target.closest('.menu2')) {
        isSubMenuActive = false;
    }
}

// Ferme la liste déroulante lorsqu'on clique en dehors de la liste
document.addEventListener('click', closeSubMenu);

</script>

<template>
    <div class="menu-container">

        <div class="menu">
            <li class="item" id="mn1" color="primary">
                <a class="btn" href="#mn1" @click="toggleSubMenu()">Trier par FIA</a>
                <div class="submenu">
                    <a href="#" @click.prevent="ToutAfficher()">Toutes
                        les formations </a>
                </div>
                <div class="submenu">
                    <a href="#" @click="ToutAfficherFIA()">Toutes
                        les FIA</a>
                </div>
                <div class="submenu" :class="{ 'is-active': isSubMenuActive }">
                    <a href="#" @click.prevent="filterByFormation('FIA-3')">FIA-3</a>
                    <a href="#" @click.prevent="filterByFormation('FIA-4')">FIA-4</a>
                    <a href="#" @click.prevent="filterByFormation('FIA-5')">FIA-5</a>
                </div>
            </li>
        </div>
        <div class="menu3">
            <li class="item" id="mn2">
                <a class="btn" href="#mn2" @click="toggleSubMenu()">Trier par FIE</a>
                <div class="submenu">
                    <a href="#" @click.prevent="ToutAfficherFIE()">Toutes
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
                <tr v-for="(ue, index) in data.filteredUE" :key="ue.id">
                    <!-- name -->
                    <td>
                        <div class="d-flex">
                            <div class="flex-grow-1">
                                <span class="d-block font-weight-semibold text--primary text-truncate">
                                    {{ ue.intitule }}</span>
                            </div>
                            <div>
                                <v-icon class="fas fa-trash-alt" :class="{ 'mr-1': true }" @click="deleteUE(ue.id)"
                                    color="black" dark size="20" block rounded elevation="x-4" align-end hover></v-icon>

                                <v-icon class="fas fa-edit" :class="{ 'ml-1': true }" @click="modifyUE(ue.id)" color="black"
                                    dark size="20" wblock rounded elevation="x-4" align-end hover></v-icon>

                                <v-dialog v-model="dialog" max-width="600px">
                                    <v-card>
                                        <v-card-title>
                                            Modifier les informations de l'UE
                                        </v-card-title>
                                        <v-snackbar v-model="snackbar" :timeout="timeout" :color="color">{{ message
                                        }}</v-snackbar>
                                        <v-card-text>
                                                    <Form @submit="modiferUe" :validation-schema="schema" v-slot="{ errors }" >
                                                    <div class="form-row">
                                                        <div class="form-group col-5">
                                                        <!-- <label>Id</label> -->
                                                        <Field name="id" type="text" class="form-control"  :value="indexue" :class="{ 'is-invalid': errors.id }"  />
                                                        <div class="invalid-feedback">{{ errors.id }}</div>
                                                    </div>
                                                    <br>
                                                    <div class="form-group col-5">
                                                        <label>Intitule</label>
                                                        <Field name="intitule" type="text" class="form-control"  :value="ueSelectionne.intitule" :class="{ 'is-invalid': errors.intitule }"  />
                                                        <div class="invalid-feedback">{{ errors.intitule }}</div>
                                                    </div>
                                                    <br>
                                                    <div class="form-group col-5">
                                                        <label>code de l'UE</label>
                                                        <Field name="codeue" type="text" class="form-control" :value="ueSelectionne.codeue" :class="{ 'is-invalid': errors.codeue }" />
                                                        <div class="invalid-feedback">{{ errors.codeue }}</div>
                                                    </div>
                                                    </div>
                                                    <br>
                                                    <div class="form-row">
                                                    <div class="form-group col">
                                                        <label>Credit ECTS</label>
                                                        <Field name="creditects" type="number" class="form-control" :value="ueSelectionne.creditects" :class="{ 'is-invalid': errors.creditects }" />
                                                        <div class="invalid-feedback">{{ errors.creditects }}</div>
                                                    </div>
                                                    <br>
                                                    <div class="form-group col">
                                                        <label>Prerequis de l'UE</label>
                                                        <Field name="prerequis" type="text" class="form-control" :value="ueSelectionne.prerequis" :class="{ 'is-invalid': errors.prerequis }" />
                                                        <!-- <ckeditor  :editor="ClassicEditor"  ></ckeditor> -->
                                                        <div class="invalid-feedback">{{ errors.prerequis }}</div>
                                                    </div>
                                                    </div>
                                                    <br>
                                                    <div class="form-group col">
                                                    <label>Numéro du semestre</label>
                                                    <Field name="numsemestre" as="select" class="form-control" :value="ueSelectionne.numsemestre" :class="{ 'is-invalid': errors.numsemestre }">
                                                        <option disabled value="">Choisir un semestre</option>
                                                        <option value="1">1</option>
                                                        <option value="2">2</option>
                                                    </Field>
                                                    <div class="invalid-feedback">{{ errors.numsemestre }}</div>
                                                    </div>
                                                    <br>
                                                    <div class="form-group col">
                                                    <label>Fomation</label> 
                                                    <Field name="formation" as="select" class="form-control" :value="ueSelectionne.formation" :class="{ 'is-invalid': errors.formation }">
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
                                                    <button type="submit"  class="btn btn-primary mr-1">Modifier</button>
                                                    <button type="reset" class="btn btn-secondary">Effacer</button>
                                                    </div> 
                                                </Form>     
                                            <!-- </v-form> -->
                                        </v-card-text>
                                        <v-card-actions>
                                            <v-spacer></v-spacer>
                                        </v-card-actions>
                                    </v-card>

                                </v-dialog>

                    <td class="text-center">
                    </td>
                    </div>
                    </div>
                    </td>
                    <td class="text-center" v-text="ue.codeue" />
                    <td class="text-center" v-text="ue.formation" />
                    <td class="text-center" v-text="ue.creditects" />
                    <td class="text-center" v-text="ue.numsemestre" />
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