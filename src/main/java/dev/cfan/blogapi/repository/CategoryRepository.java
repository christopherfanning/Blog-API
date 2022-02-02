package dev.cfan.blogapi.repository;

import dev.cfan.blogapi.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findByIdAndUserId(Long categoryId, Long id);

}
