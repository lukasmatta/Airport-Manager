package cz.muni.fi.pa165.airportmanager.entity;

import cz.muni.fi.pa165.airportmanager.enums.AirplaneType;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Class representation of Airplane
 *
 * @author Tomáš Janíček
 */
@Entity(name = "Airplane")
public class Airplane implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(fetch = FetchType.LAZY)
    private Set<Flight> flight = new HashSet<>();

    private String name;

    @Enumerated
    private AirplaneType type;

    private int capacity;

    public Airplane() {
    }

    public Airplane(Long id) {
        this.id = id;
    }

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

        if (getCapacity() != airplane.getCapacity()) return false;
        if (!getName().equals(airplane.getName())) return false;
        return getType() == airplane.getType();
    }

    @Override
    public int hashCode() {
        int result = getName().hashCode();
        result = 31 * result + getType().hashCode();
        result = 31 * result + getCapacity();
        return result;
    }
}
