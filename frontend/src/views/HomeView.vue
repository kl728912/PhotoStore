<template>
  <div class="home">
    <!-- Hero 輪播圖 -->
    <div class="hero-carousel">
      <div class="carousel-container" :style="{ transform: `translateX(-${currentIndex * 100}%)` }">
        <div v-for="(slide, index) in slides" :key="index" class="carousel-slide">
          <img :src="slide.image" :alt="slide.title">
          <div class="carousel-content">
            <h1>{{ slide.title }}</h1>
            <p>{{ slide.description }}</p>
          </div>
        </div>
      </div>
      <!-- 輪播指示器 -->
      <div class="carousel-indicators">
        <button v-for="(_, index) in slides" 
                :key="index" 
                :class="{ active: currentIndex === index }"
                @click="setSlide(index)">
        </button>
      </div>
    </div>

    <!-- 熱門作品區 -->
    <section class="featured-works">
      <h2>熱門作品</h2>
      <div class="photo-grid">
        <div v-for="photo in featuredPhotos" :key="photo.id" class="photo-card">
          <img :src="photo.imageUrl" :alt="photo.title">
          <div class="photo-info">
            <h3>{{ photo.title }}</h3>
            <p>{{ photo.price }} NT$</p>
          </div>
        </div>
      </div>
    </section>
  </div>
</template>

<script>
export default {
  name: 'HomeView',
  data() {
    return {
      currentIndex: 0,
      slides: [
        {
          image: '/images/hero1.jpg',
          title: '探索攝影藝術',
          description: '發現獨特的視角與故事'
        },
        {
          image: '/images/hero2.jpg',
          title: '精選作品',
          description: '來自專業攝影師的傑作'
        },
        {
          image: '/images/hero3.jpg',
          title: '限時特惠',
          description: '精選作品限時優惠中'
        }
      ],
      featuredPhotos: []
    }
  },
  methods: {
    setSlide(index) {
      this.currentIndex = index
    },
    startSlideShow() {
      setInterval(() => {
        this.currentIndex = (this.currentIndex + 1) % this.slides.length
      }, 5000)
    },
    async fetchFeaturedPhotos() {
      try {
        const response = await fetch('http://localhost:8081/api/photos')
        this.featuredPhotos = await response.json()
      } catch (error) {
        console.error('Error fetching photos:', error)
      }
    }
  },
  mounted() {
    this.startSlideShow()
    this.fetchFeaturedPhotos()
  }
}
</script>

<style scoped>
.home {
  width: 100%;
}

.hero-carousel {
  position: relative;
  width: 100%;
  height: 80vh;
  overflow: hidden;
}

.carousel-container {
  display: flex;
  transition: transform 0.5s ease-in-out;
  height: 100%;
}

.carousel-slide {
  min-width: 100%;
  position: relative;
}

.carousel-slide img {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.carousel-content {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  text-align: center;
  color: white;
  text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.5);
}

.carousel-content h1 {
  font-size: 3rem;
  margin-bottom: 1rem;
}

.carousel-content p {
  font-size: 1.5rem;
}

.carousel-indicators {
  position: absolute;
  bottom: 20px;
  left: 50%;
  transform: translateX(-50%);
  display: flex;
  gap: 10px;
}

.carousel-indicators button {
  width: 12px;
  height: 12px;
  border-radius: 50%;
  border: none;
  background: rgba(255, 255, 255, 0.5);
  cursor: pointer;
}

.carousel-indicators button.active {
  background: white;
}

.featured-works {
  padding: 4rem 2rem;
}

.featured-works h2 {
  text-align: center;
  margin-bottom: 2rem;
}

.photo-grid {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
  gap: 2rem;
  padding: 0 2rem;
}

.photo-card {
  border-radius: 8px;
  overflow: hidden;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
  transition: transform 0.3s ease;
}

.photo-card:hover {
  transform: translateY(-5px);
}

.photo-card img {
  width: 100%;
  height: 200px;
  object-fit: cover;
}

.photo-info {
  padding: 1rem;
}

.photo-info h3 {
  margin: 0;
  font-size: 1.2rem;
}

.photo-info p {
  margin: 0.5rem 0 0;
  color: #666;
}
</style>
