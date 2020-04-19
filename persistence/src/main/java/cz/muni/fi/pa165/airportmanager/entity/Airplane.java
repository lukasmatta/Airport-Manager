package cz.muni.fi.pa165.airportmanager.entity;

import com.sun.istack.NotNull;
import cz.muni.fi.pa165.airportmanager.enums.AirplaneType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Class representation of Airplane
 *
 * @author Tomáš Janíček
 */
@Entity(name = "Airplane")
@Table(name = "airplane")
public class Airplane implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @OneToMany
    @JoinColumn(name = "flight_id")
    private Set<Flight> flight = new HashSet<>();

    @Column(name = "name")
    private String name;

    @Enumerated
    @Column(name = "type")
    private AirplaneType type;

    @Column(name = "capacity")
    private int capacity;

    public Long getId() {
        return id;
    }

    public Set<Flight> getFlights() {
        return flight;
    }

    public void setFlights(Set<Flight> flight) {
        this.flight = flight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AirplaneType getType() {
        return type;
    }

    public void setType(AirplaneType type) {
        this.type = type;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return "Airplane{" +
                "id=" + id +
                ", flight=" + flight +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", capacity=" + capacity +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Airplane)) return false;
        Airplane airplane = (Airplane) o;
        return capacity == airplane.capacity &&
                id.equals(airplane.id) &&
                flight.equals(airplane.flight) &&
                name.equals(airplane.name) &&
                type == airplane.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, flight, name, type, capacity);
    }
}
