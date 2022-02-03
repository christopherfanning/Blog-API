package dev.cfan.blogapi.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;


@Entity
@Data
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String role;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "user_id")
    User user;
}
