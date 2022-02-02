package dev.cfan.blogapi.repository;

import dev.cfan.blogapi.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Stolen from the https://git.generalassemb.ly/sureshmelvinsigera/Java-Spring/blob/master/README.md
    boolean existsByEmail(String emailAddress);

    User findByEmail(String email);

    User findByName(String name);

}
