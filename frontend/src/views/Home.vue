<script setup>
import CycleApprentissage from './CycleApprentissage.vue';
import CycleIngenieur from './CycleIngenieur.vue';
import CyclePreparatoire from './Cyclepreparatoire.vue';
import Navbar from './Navbar.vue';
</script>
<template>
<Navbar></Navbar>
<!-- ======= Hero Section ======= -->
<div class = "home">
    <section id="hero"   >
    <div class="container">
      <div class="row">
        <div class="col-lg-6 d-flex flex-column justify-content-center pt-4 pt-lg-0 order-2 order-lg-1" >
          <h1>Découvrez nos programmes !  </h1>
          <h2>école d'ingénieur en informatique et systeme d'information pour la santé !</h2>
          <div class="d-flex justify-content-center justify-content-lg-start">
            <a href="#about" class="btn-get-started scrollto">Voir plus</a>
            <a href="https://www.youtube.com/@ecoledingenieursisis5058/featured" class="glightbox btn-watch-video"><i class="bi bi-play-circle"></i><span>Voir la Video</span></a>
          </div>
        </div>
        <div class="col-lg-6 order-1 order-lg-2 hero-img" >
          <img src="../assets/assets/img/icon-heart.svg" class="img-fluid animated" alt="">
        </div>
      </div>
    </div>
  </section>
</div> 
 <!-- ======= About Us Section ======= -->
 <section id="about" class="about">
      <div class="container" data-aos="fade-up" >

        <div class="section-title">
          <h2>Nos programmes</h2>
        </div>

        <div class="row content">
          <div class="col-lg-6">
            <p>
              Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore
              magna aliqua.
            </p>
            <ul>
              <li><i class="ri-check-double-line"></i> Ullamco laboris nisi ut aliquip ex ea commodo consequat</li>
              <li><i class="ri-check-double-line"></i> Duis aute irure dolor in reprehenderit in voluptate velit</li>
              <li><i class="ri-check-double-line"></i> Ullamco laboris nisi ut aliquip ex ea commodo consequat</li>
            </ul>
          </div>
          <div class="col-lg-6 pt-4 pt-lg-0">
            <p>
              Ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate
              velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in
              culpa qui officia deserunt mollit anim id est laborum.
            </p>
            <a href="#" class="btn-learn-more">Learn More</a>
          </div>
        </div>

      </div>
    </section><!-- End About Us Section -->
    <section id="cta" class="cta">
      <div class="container" data-aos="zoom-in">
        <div class="row">
          <div class="col-lg-9 text-center text-lg-start">
            <h3>Call To Action</h3>
            <p> Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>
          </div>
          <div class="col-lg-3 cta-btn-container text-center">
            <a class="cta-btn align-middle" href="#">Call To Action</a>
          </div>
        </div>

      </div>
    </section><!-- End Cta Section -->
    
  <!-- <CycleApprentissage></CycleApprentissage>
  <CyclePreparatoire></CyclePreparatoire>
  <CycleIngenieur></CycleIngenieur> -->

</template>



<style  scoped>
.home{
    margin-left: -40px;
    margin-right: -40px;
}
</style>