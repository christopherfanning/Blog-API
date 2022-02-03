package dev.cfan.blogapi.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
@Getter @Setter
@NoArgsConstructor
//@AllArgsConstructor
//@Data
//@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    // toLowerCase setter
    public void setName(String name) {
        name = name.toLowerCase();
        this.name = name;
    }

    @Column
    private String email;

    @Column
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;


    // Might need this for Integration tests.
    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    @OneToMany(mappedBy = "user")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Post> post;


    @OneToOne(mappedBy = "user", orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    private Role userRole;

}
