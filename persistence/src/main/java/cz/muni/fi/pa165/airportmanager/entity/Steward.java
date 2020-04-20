package cz.muni.fi.pa165.airportmanager.entity;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author Almas Shakirtkhanov
 */
@Entity(name = "Steward")
@Table(name = "steward")
public class Steward {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "firstName")
    private String firstName;

    @NotNull
    @Column(name = "secondName")
    private String lastName;

    @OneToMany
    @JoinColumn(name = "flight_id")
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

