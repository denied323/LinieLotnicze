package edu.uph.ii.platformy.models;

import edu.uph.ii.platformy.validators.annotations.UniqueUsername;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Set;

/**
 * Created by grzesiek on 23.08.2017.
 */
@Entity
@Table(name = "users")
@Getter @Setter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(min = 2, max = 36)
    @UniqueUsername
    private String username;
    private String password;
    @Transient//pole nie będzie odwzorowane w db
    private String passwordConfirm;
    private boolean enabled = false;

    @AssertTrue
    private boolean isPasswordsEquals(){
        return password == null || passwordConfirm == null || password.equals(passwordConfirm);
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;



    @NotBlank
    @Length(min = 2, max = 32)
    private String name;

    @NotBlank
    @Length(min = 2, max = 32)
    private String surname;


    @NotBlank
    @Length(min = 2, max = 32)
    private String email;


    //@Past //blad przy initalizerze :(
    @Column(name="birth_date", nullable = false)
    private Date birthDate;


    public User(String username, String name, String surname, String email, Date birthDate){
        this(username, name, surname, email, birthDate, false);
    }

    public User(String username, String name, String surname, String email, Date birthDate, boolean enabled){
        this.username = username;
        this.enabled = enabled;
        this.name=name;
        this.surname=surname;
        this.email=email;
        this.birthDate=birthDate;
    }

}