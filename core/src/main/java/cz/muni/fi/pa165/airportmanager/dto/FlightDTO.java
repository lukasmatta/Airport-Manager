package cz.muni.fi.pa165.airportmanager.dto;

import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Flight DTO class
 *
 * @author Petr Kantek
 */
public class FlightDTO extends BaseDTO {

    private AirportDTO origin;

    private AirportDTO destination;

    private ZonedDateTime departure;

    private ZonedDateTime arrival;

    private AirplaneDTO plane;

    private Set<StewardDTO> stewards = new HashSet<>();

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

        if (getOrigin() != null ? !getOrigin().equals(flightDTO.getOrigin()) : flightDTO.getOrigin() != null)
            return false;
        if (getDestination() != null ? !getDestination().equals(flightDTO.getDestination()) : flightDTO.getDestination() != null)
            return false;
        if (getDeparture() != null ? !getDeparture().equals(flightDTO.getDeparture()) : flightDTO.getDeparture() != null)
            return false;
        if (getArrival() != null ? !getArrival().equals(flightDTO.getArrival()) : flightDTO.getArrival() != null)
            return false;
        return getPlane() != null ? getPlane().equals(flightDTO.getPlane()) : flightDTO.getPlane() == null;
    }

    @Override
    public int hashCode() {
        int result = getOrigin() != null ? getOrigin().hashCode() : 0;
        result = 31 * result + (getDestination() != null ? getDestination().hashCode() : 0);
        result = 31 * result + (getDeparture() != null ? getDeparture().hashCode() : 0);
        result = 31 * result + (getArrival() != null ? getArrival().hashCode() : 0);
        result = 31 * result + (getPlane() != null ? getPlane().hashCode() : 0);
        return result;
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
