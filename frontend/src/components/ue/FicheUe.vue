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
            console.log("matiere ==: ", data.matiere);
        })
        .catch((error) => alert(error));
    //appel des matiere
}

onMounted(refresh);

</script>

<template>
    <div>
        <table>
            <tr>
                <td>Code UE</td>
                <td>Intitulé UE</td>
                <td>Crédits ECTS</td>
            </tr>
            <tr>
                <td>E1-1-SFPH</td>
                <td>Sciences fondamentales et physiques</td>
                <td>10</td>
            </tr>
        </table>
        <br>
        <table>
            <tr>
                <td>Responsable pédagogique de l’UE</td>
                <td>Principaux intervenants</td>
            </tr>
        </table>
        <br>
        <p><strong>Diplôme et Parcours-type</strong></p>
        <table>
            <tr>
                <td>Nature</td>
                <td>Domaine</td>
                <td>Diplôme</td>
                <td>Parcours-type / orientation</td>
                <td>Niveau</td>
                <td>Semestre</td>
                <td>Ordre UE
        dans la fiche programme du parcours-type concerné
                </td>
            </tr>
            <tr>
                <td>Princ.</td>
                <td>ISIS</td>
                <td>Spécialité « informatique pour la santé »</td>
                <td>Premier cycle</td>
                <td>FIE1</td>
                <td>1</td>
                <td>1</td>
            </tr>
        </table>
        <br>
        <p><strong>Mots-clés (RNCP)</strong></p>
        <table>
            <tr>
                <td></td>
            </tr>
        </table>
        <br>
        <p><strong>Compétences (RNCP)</strong></p>
        <table>
            <tr>
                <td>Capacité à mobiliser les ressources d’un large champ de sciences fondamentales</td>
            </tr>
        </table>
        <br>
        <table>
            <tr>
                <td>Mathématiques Analyse 1</td>
                <td>Cet enseignement aborde l&#39;étude des fonctions, la trigonométrie, les suites réelles et complexes ainsi que les équations différentielles du premier et second ordre à coefficients complexes.</td>
            </tr>
            <tr>
                <td>Electricité 1</td>
                <td>Différentes caractéristiques de signaux Loi d&#39;ohm en régime stationnaire puis en régime sinusoïdal. Lois de Kirchhoff, théorèmes de superposition, de Thévenin, de Norton et de Millman. Oscilloscope, multimètre. Représentation complexe des signaux sinusoïdaux.</td>
            </tr>
            <tr>
                <td>Mécanique physique</td>
                <td>Mécanique du point : vecteurs (OM) ⃗, vitesse, accélération. La loi fondamentale de la dynamique. énergie cinétique et énergie mécanique. relation force - énergie potentielle</td>
            </tr>
            <tr>
                <td>Optique</td>
                <td>Savoir déterminer le trajet suivi par la lumière (modèle du rayon lumineux) lorsqu’elle est réfractée ou réfléchie (lame de verre et dioptres) Dans l’approximation de Gauss, savoir déterminer, à partir d&#39;objets réels et virtuels, la position et la taille des images correspondantes théoriquement et graphiquement. Contenu : - Fondements de l’optique géométrique : principe de Fermat, lois de Descartes Formation des images - Dioptres et miroirs sphériques</td>
            </tr>
    </table>
    <br>
    <p><strong>Format des volumes horaires</strong></p>
    <table>
            <tr>
                <td>Volume horaire présentiel enseignant/étudiant</td>
                <td>Volume horaire &quot;étudiant &quot;</td>
            </tr>
            <tr>
                <td>(travail personnel, projet, stage)</td>
            </tr>
            <tr>
                <td>CM</td>
                <td>TD</td>
                <td>TP</td>
                <td>Travail personnel</td>
                <td>Projet (en heures)</td>
                <td>Stage (en heures)</td>
            </tr>
            <tr>
                <td>54</td>
                <td>74</td>
                <td>24</td>
                <td>90</td>
                <td>0</td>
                <td>0</td>
            </tr>
    </table>
    <br>
        <p><strong>Prérequis pour suivre l’UE</strong></p>
        <table>
            <tr>
                <td>Enseignement de spécialité de mathématiques terminale de lycée général
Enseignement de spécialité de physiques terminale de lycée général
</td>
            </tr>
        </table>
        <br>
        <p><strong>Modalités de contrôle des connaissances (Conditions de validation / Principes généraux)</strong></p>
        <table>
            <tr>
                <td>Voir les Modalités de Contrôle des Connaissances.</td>
            </tr>
        </table>
        <br>
        <p><strong>Bibliographie de base</strong></p>
        <table>
            <tr>
                <td> </td>
            </tr>
        </table>
    </div>

</template>

<style scoped>
table {
    border-collapse: collapse;
    width: 100%;
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
  
  }
</style>