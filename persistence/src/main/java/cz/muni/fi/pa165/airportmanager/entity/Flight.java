package cz.muni.fi.pa165.airportmanager.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.NotNull;
import cz.muni.fi.pa165.airportmanager.exceptions.OverlappingTimeException;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Petr Kantek
 */
@Entity
public class Flight {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Airport origin;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Airport destination;

    @NotNull
    private ZonedDateTime departure;

    @NotNull
    private ZonedDateTime arrival;

    @ManyToOne(fetch = FetchType.LAZY)
    private Airplane plane;

    @JsonManagedReference
    @ManyToMany
    @JoinTable(
            name = "flights_stewards",
            joinColumns = @JoinColumn(name = "flight_id"),
            inverseJoinColumns = @JoinColumn(name = "steward_id"))
    private Set<Steward> stewards = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Airport getOrigin() {
        return origin;
    }

    public void setOrigin(Airport origin) {
        this.origin = origin;
    }

    public Airport getDestination() {
        return destination;
    }

    public void setDestination(Airport destination) {
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

    public Airplane getPlane() {
        return plane;
    }

    public void setPlane(Airplane plane) throws OverlappingTimeException {
        isResourceAvailable(plane.getFlights());
        this.plane = plane;
    }

    public Set<Steward> getStewards() {
        return stewards;
    }

    public void setStewards(Set<Steward> stewards) {
        this.stewards = stewards;
    }

    public void addSteward(Steward steward) throws OverlappingTimeException {
        if (stewards.contains(steward)) {
            return;
        }

        isResourceAvailable(steward.getFlights());
        stewards.add(steward);

        steward.addFlight(this);
    }

    public boolean removeSteward(Steward steward) {
        boolean contains = stewards.remove(steward);
        if (contains) {
            steward.removeFlight(this);
        }
        return contains;
    }

    private void isResourceAvailable(Collection<Flight> flights) throws OverlappingTimeException {
        if (flights == null) {
            return;
        }
        for (Flight flight : flights) {
            if (isOverlapping(flight.departure, flight.arrival)) {
                throw new OverlappingTimeException("is not available for this flight.");
            }
        }
    }

    private boolean isOverlapping(ZonedDateTime departure, ZonedDateTime arrival) {
        return  (this.departure.isBefore(departure) && this.arrival.isAfter(arrival)) ||
                (this.departure.isBefore(departure) && this.arrival.isAfter(departure) && this.arrival.isBefore(arrival)) ||
                (this.departure.isAfter(departure) && this.arrival.isBefore(arrival)) ||
                (this.departure.isAfter(departure) && this.departure.isBefore(arrival) && this.arrival.isAfter(departure));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Flight)) return false;

        Flight flight = (Flight) o;

        if (!getPlane().equals(flight.getPlane())) return false;
        if (!getOrigin().equals(flight.getOrigin())) return false;
        if (!getDestination().equals(flight.getDestination())) return false;
        if (!getDeparture().equals(flight.getDeparture())) return false;
        return getArrival().equals(flight.getArrival());
    }

    @Override
    public int hashCode() {
        int result = getPlane().hashCode();
        result = 31 * result + getOrigin().hashCode();
        result = 31 * result + getDestination().hashCode();
        result = 31 * result + getDeparture().hashCode();
        result = 31 * result + getArrival().hashCode();
        return result;
    }
}
