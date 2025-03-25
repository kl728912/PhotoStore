package com.photostore.model;

import lombok.Data;
import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "photos")
public class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String title;
    
    private String description;
    
    @Column(nullable = false)
    private String imageUrl;
    
    @Column(nullable = false)
    private BigDecimal price;
    
    @ManyToOne
    @JoinColumn(name = "photographer_id", nullable = false)
    private User photographer;
}