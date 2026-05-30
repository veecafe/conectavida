package com.conectavida.conectavida.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "institutions")
public class Institution {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String cnpj;
    private String description;
    private String cep;
    private String address;
    private String phone;
    private String website;

    @ManyToMany
    @JoinTable(
            name = "institution_providers",
            joinColumns = @JoinColumn(name = "institution_id"),
            inverseJoinColumns = @JoinColumn(name = "provider_id")
    )
    private Set<Provider> providers = new HashSet<>();

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    public String getCnpj() { return cnpj; }
    public void setCnpj(String cnpj) { this.cnpj = cnpj; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getCep() { return cep; }
    public void setCep(String cep) { this.cep = cep; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getWebsite() { return website; }
    public void setWebsite(String website) { this.website = website; }
    public Set<Provider> getProviders() { return providers; }
    public void setProviders(Set<Provider> providers) { this.providers = providers; }
}