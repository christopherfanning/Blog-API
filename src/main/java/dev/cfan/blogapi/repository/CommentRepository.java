package dev.cfan.blogapi.repository;

import dev.cfan.blogapi.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
