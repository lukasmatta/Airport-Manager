package cz.muni.fi.pa165.airportmanager.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sun.istack.NotNull;
import cz.muni.fi.pa165.airportmanager.exceptions.OverlappingTimeException;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Almas Shakirtkhanov
 */
@Entity
@Table(name="stewards")
public class Steward implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @JsonBackReference
    @ManyToMany (mappedBy = "stewards", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Flight> flights = new HashSet<>();

    public Steward() {}

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

    public void addFlight(Flight flight) throws OverlappingTimeException {
        if (flights.contains(flight)) {
            return;
        }
        flights.add(flight);
        flight.addSteward(this);
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
        return getLastName().equals(steward.getLastName());
    }

    @Override
    public int hashCode() {
        int result = getFirstName().hashCode();
        result = 31 * result + getLastName().hashCode();
        return result;
    }
}