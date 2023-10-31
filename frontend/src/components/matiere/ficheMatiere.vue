<script setup>
import { reactive, onMounted } from "vue";
let data = reactive({
    matiere: [],
});

const headers = [
    'Intitule',
    'Prérequis de la matière',
    'Les objectifs pédagogiques de la matière',
    'la description de la matière',


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
            console.log("matieres ==: ", data.matiere);
        })
        .catch((error) => alert(error));
    //appel des matiere
}

onMounted(refresh);

</script>

<template>
    <table>
        <tr>
            <td>INTITULE DE L’ENSEIGNEMENT : </td>
            <td> </td>
        </tr>
        <tr>
            <td>Code et Nom de l’UE</td>
            <td></td>
            <td> Année d’études</td>
            <td>Semestre</td>
        </tr>
        <tr>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
        </tr>
        <tr>
            <td>         Nom de l’intervenant </td>
            <td>               Coordonnées intervenant </td>
        </tr>
        <tr>
            <td>Nb heures</td>
        </tr>
        <tr>
            <td>CM</td>
            <td>Nb heures</td>
        </tr>
        <tr>
            <td>TD</td>
            <td>Nb heures TP</td>
            <td>Total encadré</td>
            <td>Travail perso</td>
            <td></td>
        </tr>
        <tr>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
        </tr>
        <tr>
            <td>Prérequis pour suivre l’enseignement :</td>
        </tr>
        <tr>
            <td>

            </td>
        </tr>
        <tr>
            <td></td>
        </tr>
        <tr>
            <td>Description de l’enseignement et plan du cours</td>
        </tr>
        <tr>
            <td></td>
        </tr>
        <tr>
            <td>Modalités d’évaluation</td>
        </tr>
    </table>
</template>

<style scoped>
table {
    border-collapse: collapse;
    width: 100%;
    background-color: white;
  }
  p{
    color: black;
  }
  tr, td {
    text-align: center;
    color: black;
    font-family: Arial, Helvetica, sans-serif;
    padding: 8px;
    border: 2px solid black;
    font-weight: bold;
  }
  
  tr {
    background-color: white;

  }
</style>