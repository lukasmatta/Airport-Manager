package cz.muni.fi.pa165.airportmanager.entity;

import com.sun.istack.NotNull;
import cz.muni.fi.pa165.airportmanager.enums.AirplaneType;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Objects;

/**
 * Class representation of Airplane
 *
 * @author Tomáš Janíček
 */
@Entity
public class Airplane {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

//    @OneToMany
//    @NotNull
//    private Set<Flight> flight;

    @NotNull
    private String name;

    @NotNull
    @Enumerated
    private AirplaneType type;

    @NotNull
    private int capacity;

    public Long getId() {
        return id;
    }

//    public Flight getFlight() {
//        return flight;
//    }
//
//    public void setFlight(Flight flight) {
//        this.flight = flight;
//    }

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Airplane airplane = (Airplane) o;
        return capacity == airplane.capacity &&
                id.equals(airplane.id) &&
                name.equals(airplane.name) &&
                type == airplane.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, type, capacity);
    }
}
