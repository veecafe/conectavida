package com.conectavida.conectavida.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "providers")
public class Provider {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String registrationNumber;
    private String profession;
    private String bio;
    private String phone;
    private String cep;
    private String address;

    @ManyToMany
    @JoinTable(
            name = "provider_specialties",
            joinColumns = @JoinColumn(name = "provider_id"),
            inverseJoinColumns = @JoinColumn(name = "specialty_id")
    )
    private Set<Specialty> specialties = new HashSet<>();

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    public String getRegistrationNumber() { return registrationNumber; }
    public void setRegistrationNumber(String registrationNumber) { this.registrationNumber = registrationNumber; }
    public String getProfession() { return profession; }
    public void setProfession(String profession) { this.profession = profession; }
    public String getBio() { return bio; }
    public void setBio(String bio) { this.bio = bio; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getCep() { return cep; }
    public void setCep(String cep) { this.cep = cep; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public Set<Specialty> getSpecialties() { return specialties; }
    public void setSpecialties(Set<Specialty> specialties) { this.specialties = specialties; }
}