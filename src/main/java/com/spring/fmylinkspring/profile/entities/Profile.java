package com.spring.fmylinkspring.profile.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

/**
 * @author anthonylee
 */
@Table(name = "profiles")
@Entity
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    public Integer id;

    @Column()
    public String avatarUrl;

    @Column(nullable = false)
    public String username;

    @Column(name = "user_id", nullable = false)
    public Integer userId;

    @CreationTimestamp
    @Column(updatable = false, name = "created_at")
    public Date createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    public Date updatedAt;

}
