package com.thedxs.socialvibes.repository;


import com.thedxs.socialvibes.model.Like;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like,Long> {
}
