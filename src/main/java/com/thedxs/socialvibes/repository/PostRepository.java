package com.thedxs.socialvibes.repository;

import com.thedxs.socialvibes.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Long> {
}
