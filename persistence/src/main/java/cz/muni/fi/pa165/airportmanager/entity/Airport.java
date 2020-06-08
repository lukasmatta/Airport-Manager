package cz.muni.fi.pa165.airportmanager.entity;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * Author: Lukas Matta
 */
@Entity
@Table(name="airports")
public class Airport implements Serializable {
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

    public Airport() { }

    public Airport(long id) {
        this.id = id;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Airport)) return false;

        Airport airport = (Airport) o;

        if (!getCity().equals(airport.getCity())) return false;
        return getCountry().equals(airport.getCountry());
    }

    @Override
    public int hashCode() {
        int result = getCity().hashCode();
        result = 31 * result + getCountry().hashCode();
        return result;
    }
}
