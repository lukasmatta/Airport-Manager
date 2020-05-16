package cz.muni.fi.pa165.airportmanager.dto;

import java.time.ZonedDateTime;

/**
 * Class
 *
 * @author Almas Shakirtkhanov
 */
public class FlightCreateDTO {

    private long originID;

    private long destinationID;

    private ZonedDateTime departure;

    private ZonedDateTime arrival;

    private long planeID;

    public ZonedDateTime getDeparture() {
        return departure;
    }

    public ZonedDateTime getArrival() {
        return arrival;
    }

    public void setDeparture(ZonedDateTime departure) {
        this.departure = departure;
    }

    public void setArrival(ZonedDateTime arrival) {
        this.arrival = arrival;
    }

    public long getDestinationID() {
        return destinationID;
    }

    public long getOriginID() {
        return originID;
    }

    public long getPlaneID() {
        return planeID;
    }

    public void setDestinationID(long destinationID) {
        this.destinationID = destinationID;
    }

    public void setOriginID(long originID) {
        this.originID = originID;
    }

    public void setPlaneID(long planeID) {
        this.planeID = planeID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FlightCreateDTO)) return false;

        FlightCreateDTO flightCreateDTO = (FlightCreateDTO) o;

        if (getDeparture() != null ? !getDeparture().equals(flightCreateDTO.getDeparture()) : flightCreateDTO.getDeparture() != null)
            return false;
        if (getArrival() != null ? !getArrival().equals(flightCreateDTO.getArrival()) : flightCreateDTO.getArrival() != null)
            return false;
        if(getOriginID() != flightCreateDTO.getOriginID()){
            return false;
        }
        if(getDestinationID() != flightCreateDTO.getDestinationID()){
            return false;
        }
        return getPlaneID() != flightCreateDTO.getPlaneID() ;
    }

    @Override
    public int hashCode() {
        long result = getOriginID();
        result = 31 * result + getDestinationID();
        result = 31 * result + (getDeparture() != null ? getDeparture().hashCode() : 0);
        result = 31 * result + (getArrival() != null ? getArrival().hashCode() : 0);
        result = 31 * result + getPlaneID();
        return (int) result;
    }

    @Override
    public String toString() {
        return "FlightCreateDTO{" +
                ", origin=" + originID +
                ", destination=" + destinationID +
                ", departure=" + departure +
                ", arrival=" + arrival +
                ", plane=" + planeID +
                '}';
    }
}