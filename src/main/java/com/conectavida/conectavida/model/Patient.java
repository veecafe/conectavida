package com.conectavida.conectavida.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "patients")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String cpf;
    private LocalDate birthDate;
    private String phone;
    private String cep;
    private String address;

    @ManyToMany
    @JoinTable(
            name = "patient_diseases",
            joinColumns = @JoinColumn(name = "patient_id"),
            inverseJoinColumns = @JoinColumn(name = "disease_id")
    )
    private Set<Disease> diseases = new HashSet<>();

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }
    public LocalDate getBirthDate() { return birthDate; }
    public void setBirthDate(LocalDate birthDate) { this.birthDate = birthDate; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getCep() { return cep; }
    public void setCep(String cep) { this.cep = cep; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public Set<Disease> getDiseases() { return diseases; }
    public void setDiseases(Set<Disease> diseases) { this.diseases = diseases; }
}