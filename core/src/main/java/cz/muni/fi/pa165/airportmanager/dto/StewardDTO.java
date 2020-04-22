package cz.muni.fi.pa165.airportmanager.dto;


import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * DTO for Steward
 *
 * @author Almas Shakirtkhanov
 */
public class StewardDTO {

    private Long id;
    private String firstName;

    private String lastName;

    private Set<FlightDTO> flights = new HashSet<>();

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

    public Set<FlightDTO> getFlights() {
        return flights;
    }

    public void setFlights(Set<FlightDTO> flights) {
        this.flights = flights;
    }

    public void removeFlight(FlightDTO flight) {
        flights.remove(flight);
    }

    @Override
    public String toString() {
        return "StewardDTO{" +
                "id=" + id +
                ", First Name:=" + firstName +
                ", Last Name:='" + lastName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StewardDTO)) return false;

        StewardDTO steward = (StewardDTO) o;

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