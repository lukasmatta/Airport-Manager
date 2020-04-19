package cz.muni.fi.pa165.airportmanager.entity;

import com.sun.istack.NotNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Steward {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @OneToMany
    private Set<Flight> flights = new HashSet<Flight>();

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Steward steward = (Steward) o;
        return Objects.equals(id, steward.id) &&
                Objects.equals(firstName, steward.firstName) &&
                Objects.equals(lastName, steward.lastName) &&
                Objects.equals(flights, steward.flights);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, flights);
    }
}

