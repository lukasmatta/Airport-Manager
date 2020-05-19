package cz.muni.fi.pa165.airportmanager.dto;


import java.util.HashSet;
import java.util.List;
import java.util.LinkedList;
import java.util.Set;
import java.util.HashSet;
import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * DTO for Steward
 *
 * @author Almas Shakirtkhanov
 */
public class StewardDTO extends BaseDTO {

    private String firstName;

    private String lastName;

    @JsonBackReference
    private Set<FlightDTO> flights = new HashSet<>();

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
        return getLastName().equals(steward.getLastName());
    }

    @Override
    public int hashCode() {
        if (getFirstName() != null && getLastName() != null) {
            int result = getFirstName().hashCode();
            result = 31 * result + getLastName().hashCode();
            return result;
        }
        return 1;
    }
}