package cz.muni.fi.pa165.airportmanager.entity;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author Almas Shakirtkhanov
 */
@Entity
public class Steward implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @OneToMany
    private Set<Flight> flights = new HashSet<Flight>();

    public Steward(){}

    public Steward(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public Set<Flight> getFlights() {
        return flights;
    }

    public void setFlights(Set<Flight> flights) {
        this.flights = flights;
    }

    public void removeFlight(Flight flight) {
        flights.remove(flight);
    }

    @Override
    public String toString() {
        return "Steward{" +
                "id=" + id +
                ", First Name:=" + firstName +
                ", Last Name:='" + lastName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Steward)) return false;

        Steward steward = (Steward) o;

        if (!getFirstName().equals(steward.getFirstName())) return false;
        if (!getLastName().equals(steward.getLastName())) return false;
        return getFlights() != null ? getFlights().equals(steward.getFlights()) : steward.getFlights() == null;
    }

    @Override
    public int hashCode() {
        int result = getFirstName().hashCode();
        result = 31 * result + getLastName().hashCode();
        result = 31 * result + (getFlights() != null ? getFlights().hashCode() : 0);
        return result;
    }
}