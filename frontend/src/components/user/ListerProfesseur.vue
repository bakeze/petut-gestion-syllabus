<script setup>
import { reactive, onMounted } from "vue";
let data = reactive({
  utilisateur: [],
});

const headers = [
  'Nom',
  'Prenom',
  'Rôle',
]

function refresh() {
  //appel des ues
  fetch("/api/utilisateurs")
    .then((response) => {
      if (!response.ok) { // status != 2XX
        throw new Error(`code d'erreur = ${response.status}`);
      }
      return response.json();
    })
    .then((json) => {
      data.utilisateur = json._embedded.utilisateurs;
      console.log("Utilisateur : ", data.utilisateur);
    })
    .catch((error) => alert(error));
  //appel des utilisateur
}

onMounted(refresh);
let enseignant = "ENSEIGNANT"
</script>

<template>
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
        <tr v-for="utilisateur in data.utilisateur" :key="utilisateur.id">
          <!-- Inv-if="utilisateur.role == enseignant" frormations des professeurs -->


          <td class="text-center" v-if="utilisateur.role == enseignant" v-text="utilisateur.nom" />
          <td class="text-center" v-if="utilisateur.role == enseignant" v-text="utilisateur.prenom" />
          <td class="text-center" v-if="utilisateur.role == enseignant" v-text="utilisateur.role" />

        </tr>
      </tbody>
    </VTable>
  </VCard>
</template>