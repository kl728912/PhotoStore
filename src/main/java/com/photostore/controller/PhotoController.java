package com.photostore.controller;

import com.photostore.model.Photo;
import com.photostore.repository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 照片資源的 RESTful API 控制器
 * 提供照片的 CRUD（創建、讀取、更新、刪除）操作
 */
@RestController
@RequestMapping("/api/photos")
public class PhotoController {
    
    /**
     * 照片資料訪問層
     */
    @Autowired
    private PhotoRepository photoRepository;

    /**
     * 獲取所有照片
     * @return 照片列表
     */
    @GetMapping
    public List<Photo> getAllPhotos() {
        return photoRepository.findAll();
    }

    /**
     * 根據ID獲取特定照片
     * @param id 照片ID
     * @return 如果找到照片則返回200(OK)，否則返回404(Not Found)
     */
    @GetMapping("/{id}")
    public ResponseEntity<Photo> getPhotoById(@PathVariable Long id) {
        return photoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * 創建新照片
     * @param photo 要創建的照片資訊
     * @return 創建成功的照片資訊
     */
    @PostMapping
    public Photo createPhoto(@RequestBody Photo photo) {
        return photoRepository.save(photo);
    }

    /**
     * 更新指定ID的照片
     * @param id 要更新的照片ID
     * @param photo 更新的照片資訊
     * @return 如果更新成功則返回200(OK)，否則返回404(Not Found)
     */
    @PutMapping("/{id}")
    public ResponseEntity<Photo> updatePhoto(@PathVariable Long id, @RequestBody Photo photo) {
        return photoRepository.findById(id)
                .map(existingPhoto -> {
                    photo.setId(id);
                    return ResponseEntity.ok(photoRepository.save(photo));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * 刪除指定ID的照片
     * @param id 要刪除的照片ID
     * @return 如果刪除成功則返回200(OK)，否則返回404(Not Found)
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePhoto(@PathVariable Long id) {
        return photoRepository.findById(id)
                .map(photo -> {
                    photoRepository.delete(photo);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}