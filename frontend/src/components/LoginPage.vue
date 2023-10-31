<script setup>
import { useTheme } from 'vuetify'
import logo from '../assets/logoisis.png'
import AuthProvider from '../components/pages/authentication/AuthProvider.vue'
import authV1MaskDark from '../assets/images/pages/auth-v1-mask-dark.png'
import authV1MaskLight from '../assets/images/pages/auth-v1-mask-light.png'
import authV1Tree2 from '../assets/images/pages/auth-v1-tree-2.png'
import Heartimg from '../assets/assets/img/icon-heart.svg'

import { ref } from "vue";
const form = ref({
  email: '',
  password: '',
  remember: false,
})
const vuetifyTheme = useTheme()
// const authThemeMask = computed(() => {
//   return vuetifyTheme.global.name.value === 'light' ? authV1MaskLight : authV1MaskDark
// })
const isPasswordVisible = ref(false)

let emaildirecteuretudes = "directeuretudes@gmail.com"
let emailra = "responsableannee@gmail.com"
let emailens = "enseignant@gmail.com"

</script>

<template>
  <v-icon size="large" color="blue-darken-2" icon="mdi-message-text"></v-icon>
  <div class="auth-wrapper d-flex align-center justify-center pa-4">
    <VCard class="auth-card" max-width="448">
      <VCardItem class="justify-center">
        <VImg class="" :src="logo" :width="100" />
        TEST
      </VCardItem>

      <VCardText class="pt-2 ">
        <h5 class="text-h5 font-weight-semibold mb-1">
          Bienvenu à ISIS! 👋🏻
        </h5>
        <p class="mb-0">
          Connectez vous vous pour continuer l'aventure !

        </p>
      </VCardText>
      <VCardText>
        <VForm @submit.prevent="() => { }">
          <VRow>
            <!-- email -->
            <VCol cols="12">
              <VTextField v-model="form.email" label="Email" type="email" />
            </VCol>

            <!-- password -->
            <VCol cols="12">
              <VTextField v-model="form.password" label="Password" :type="isPasswordVisible ? 'text' : 'password'"
                :append-inner-icon="isPasswordVisible ? 'mdi-eye-off-outline' : 'mdi-eye-outline'"
                @click:append-inner="isPasswordVisible = !isPasswordVisible" />

              <!-- remember me checkbox -->
              <div class="d-flex align-center justify-space-between flex-wrap mt-1 mb-4">
                <VCheckbox v-model="form.remember" label="Remember me" />

                <a class="ms-2 mb-1" href="javascript:void(0)">
                  Mot de passe oublier
                </a>
              </div>

              <!-- login button -->
              <VBtn block type="submit" to="/dashde" v-if="form.email == emaildirecteuretudes">
                Directeur des Etudes
              </VBtn>
              <VBtn block type="submit" to="/dashra" v-if="form.email == emailra" >
                Responsable d'année
              </VBtn>
              <VBtn block type="submit" to="/dashens" v-if="form.email == emailens" >
                Enseignant
              </VBtn>
            </VCol>

            <!-- create account -->
            <VCol cols="12" class="text-center text-base">
            <span>Nouveau sur la plateforme ?</span>
            <RouterLink class="text-primary ms-2" :to="{ name: 'register' }">
              Create an account
            </RouterLink>
            </VCol>

            <VCol cols="12" class="d-flex align-center">
            <VDivider />
            <span class="mx-4">or</span>
            <VDivider />
            </VCol>

            <!-- auth providers -->
            <VCol cols="12" class="text-center">
              <AuthProvider />
            </VCol>
          </VRow>
        </VForm>
      </VCardText>
    </VCard>

    <!-- <VImg
        class="auth-footer-start-tree d-none d-md-block"
        :src="Heartimg"
        :width="10"
      /> -->

    <!-- bg img -->
    <!-- <VImg
        class="auth-footer-mask d-none d-md-block"
        :src="authThemeMask"
      /> -->
  </div>
</template>
<style lang="scss" scoped>
@use "../components/@core/scss/pages/page-auth.scss";
</style>
