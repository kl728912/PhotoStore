package com.photostore.repository;

import com.photostore.model.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PhotoRepository extends JpaRepository<Photo, Long> {
    List<Photo> findByPhotographer_Id(Long photographerId);
    List<Photo> findByTitleContaining(String keyword);
}