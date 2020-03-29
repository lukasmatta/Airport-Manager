package cz.muni.fi.pa165.airportmanager.entity;

import com.sun.istack.NotNull;

import javax.persistence.*;

/**
 * Author: Lukas Matta
 */
@Entity
public class Airport {
    public Long getId() {
        return id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String city;

    @NotNull
    private String country;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
