package cz.muni.fi.pa165.airportmanager.entity;

import com.sun.istack.NotNull;
import cz.muni.fi.pa165.airportmanager.exceptions.OverlappingTimeException;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
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
    @ManyToOne
    private Airport origin;

    @NotNull
    @ManyToOne
    private Airport destination;

    @NotNull
    private LocalDateTime departure;

    @NotNull
    private LocalDateTime arrival;

    @NotNull
    @ManyToOne
    private Airplane plane;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "Flight_Stewards",
            joinColumns = {@JoinColumn(name = "flight_id")},
            inverseJoinColumns = {@JoinColumn(name = "steward_id")}
    )
    private Set<Steward> stewards;

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

    public LocalDateTime getDeparture() {
        return departure;
    }

    public void setDeparture(LocalDateTime departure) {
        this.departure = departure;
    }

    public LocalDateTime getArrival() {
        return arrival;
    }

    public void setArrival(LocalDateTime arrival) {
        this.arrival = arrival;
    }

    public Airplane getPlane() {
        return plane;
    }

    public void setPlane(Airplane plane) {
        try {
            isResourceAvailable(plane.getFlights());
            this.plane = plane;
        } catch (OverlappingTimeException e) {
            System.out.println("Plane with ID: " + plane.getId() + " is not available for given flight");
        }
    }

    public Set<Steward> getStewards() {
        return Collections.unmodifiableSet(stewards);
    }

    public void setStewards(Set<Steward> stewards) {
        this.stewards = stewards;
    }

    public void addSteward(Steward steward) {
        try {
            isResourceAvailable(steward.getFlights());
            stewards.add(steward);
        } catch (OverlappingTimeException e) {
            System.out.println("Steward " + steward.getName() + " is not available for given flight");
        }
    }

    public boolean removeSteward(Steward steward) {
        boolean contains = stewards.remove(steward);
        if (contains) {
            steward.removeFlight(this);
        }
        return contains;
    }

    private void isResourceAvailable(Collection<Flight> flights) throws OverlappingTimeException {
        for (Flight flight : flights) {
            if (isOverlapping(flight.departure, flight.arrival)) {
                throw new OverlappingTimeException();
            }
        }
    }

    private boolean isOverlapping(LocalDateTime departure, LocalDateTime arrival) {
        return this.departure.isBefore(arrival) && this.arrival.isBefore(departure);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Flight)) return false;

        Flight flight = (Flight) o;

        if (!getId().equals(flight.getId())) return false;
        if (!getPlane().equals(flight.getPlane())) return false;
        if (!getOrigin().equals(flight.getOrigin())) return false;
        if (!getDestination().equals(flight.getDestination())) return false;
        if (!getDeparture().equals(flight.getDeparture())) return false;
        if (!getArrival().equals(flight.getArrival())) return false;
        return getStewards().equals(flight.getStewards());
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getPlane().hashCode();
        result = 31 * result + getOrigin().hashCode();
        result = 31 * result + getDestination().hashCode();
        result = 31 * result + getDeparture().hashCode();
        result = 31 * result + getArrival().hashCode();
        result = 31 * result + getStewards().hashCode();
        return result;
    }
}
