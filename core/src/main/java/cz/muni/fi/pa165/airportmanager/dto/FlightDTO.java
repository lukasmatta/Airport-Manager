package cz.muni.fi.pa165.airportmanager.dto;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Flight DTO class
 *
 * @author Petr Kantek
 */
public class FlightDTO {

    private Long id;

    private AirportDTO origin;

    private AirportDTO destination;

    private ZonedDateTime departure;

    private ZonedDateTime arrival;

    private AirplaneDTO plane;

    private Set<StewardDTO> stewards = new HashSet<StewardDTO>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AirportDTO getOrigin() {
        return origin;
    }

    public void setOrigin(AirportDTO origin) {
        this.origin = origin;
    }

    public AirportDTO getDestination() {
        return destination;
    }

    public void setDestination(AirportDTO destination) {
        this.destination = destination;
    }

    public ZonedDateTime getDeparture() {
        return departure;
    }

    public void setDeparture(ZonedDateTime departure) {
        this.departure = departure;
    }

    public ZonedDateTime getArrival() {
        return arrival;
    }

    public void setArrival(ZonedDateTime arrival) {
        this.arrival = arrival;
    }

    public AirplaneDTO getPlane() {
        return plane;
    }

    public void setPlane(AirplaneDTO plane) {
        this.plane = plane;
    }

    public Set<StewardDTO> getStewards() {
        return stewards;
    }

    public void setStewards(Set<StewardDTO> stewards) {
        this.stewards = stewards;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FlightDTO)) return false;
        FlightDTO flightDTO = (FlightDTO) o;
        return getOrigin().equals(flightDTO.getOrigin()) &&
                getDestination().equals(flightDTO.getDestination()) &&
                getDeparture().equals(flightDTO.getDeparture()) &&
                getArrival().equals(flightDTO.getArrival()) &&
                getPlane().equals(flightDTO.getPlane()) &&
                Objects.equals(getStewards(), flightDTO.getStewards());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOrigin(), getDestination(), getDeparture(), getArrival(), getPlane(), getStewards());
    }

    @Override
    public String toString() {
        return "FlightDTO{" +
                "id=" + id +
                ", origin=" + origin +
                ", destination=" + destination +
                ", departure=" + departure +
                ", arrival=" + arrival +
                ", plane=" + plane +
                ", stewards=" + stewards +
                '}';
    }
}
