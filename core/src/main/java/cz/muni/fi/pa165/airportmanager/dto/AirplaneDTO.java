package cz.muni.fi.pa165.airportmanager.dto;

import cz.muni.fi.pa165.airportmanager.enums.AirplaneType;

import java.util.HashSet;
import java.util.Set;

/**
 * DTO for Airplane
 *
 * @author Lukáš Matta
 */

public class AirplaneDTO {
    private Long id;

    private Set<FlightDTO> flight = new HashSet<>();

    private String name;

    private AirplaneType type;

    private int capacity;

    public Long getId() {
        return id;
    }

    public Set<FlightDTO> getFlights() {
        return flight;
    }

    public void setFlights(Set<FlightDTO> flight) {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AirplaneDTO)) return false;

        AirplaneDTO airplane = (AirplaneDTO) o;

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
