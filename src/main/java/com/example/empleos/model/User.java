package com.example.empleos.model;

import javax.persistence.*;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String username;
    private String name;
    private String email;
    private String password;
    private Integer status;
    private Date registrationDate;
    @ManyToMany(fetch = FetchType.EAGER)                            // Cuando se busque un usuario se buscan tambien todos los perfiles del mismo
    @JoinTable(                                                     // Crea una tabla intermendia con las key foraneas del usuario y de sus perfiles
            name = "UserProfile",
            joinColumns = @JoinColumn(name = "idUser"),             // joincolumn id user porque estamos en la clase usuario
            inverseJoinColumns = @JoinColumn(name = "idProfile")    // Otra tabla con la que se esta relacinando el usuario
    )
    private List<Profile> profiles;

    public void addProfile(Profile profile){
        if (profiles==null) {
            profiles = new LinkedList<>();
        }
        profiles.add(profile);
    }


    public List<Profile> getProfiles() {
        return profiles;
    }

    public void setProfiles(List<Profile> profiles) {
        this.profiles = profiles;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }
}
