package com.photostore.config;

import com.photostore.model.Photo;
import com.photostore.model.User;
import com.photostore.repository.PhotoRepository;
import com.photostore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PhotoRepository photoRepository;

    @Override
    public void run(String... args) {        
        // 創建攝影師用戶
    User photographer = new User();
    photographer.setUsername("photographer1");
    photographer.setPassword("123456");
    photographer.setEmail("photographer1@test.com");  // 修改 email
    photographer.setRole("PHOTOGRAPHER");
    userRepository.save(photographer);

        // 創建一些照片
        createPhoto("日落風景", "美麗的海邊日落", "sunset.jpg", new BigDecimal("299"), photographer);
        createPhoto("城市夜景", "繁華都市夜景", "city.jpg", new BigDecimal("399"), photographer);
        createPhoto("山水風光", "青山綠水", "mountain.jpg", new BigDecimal("199"), photographer);
        createPhoto("花卉特寫", "春天的櫻花", "flower.jpg", new BigDecimal("159"), photographer);
    }

    private void createPhoto(String title, String description, String imageUrl, BigDecimal price, User photographer) {
        Photo photo = new Photo();
        photo.setTitle(title);
        photo.setDescription(description);
        photo.setImageUrl(imageUrl);
        photo.setPrice(price);
        photo.setPhotographer(photographer);
        photoRepository.save(photo);
    }
}