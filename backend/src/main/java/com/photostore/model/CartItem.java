package com.photostore.model;

import lombok.Data;
import javax.persistence.*;
import java.time.LocalDateTime;
@Data
@Entity
@Table(name = "cart_items")
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    
    @ManyToOne
    @JoinColumn(name = "photo_id", nullable = false)
    private Photo photo;
    
    private LocalDateTime addedDate;
}