package cz.muni.fi.pa165.airportmanager.dto;

import cz.muni.fi.pa165.airportmanager.enums.AirplaneType;

/**
 * Class
 *
 * @author Petr Kantek
 */
public class AirplaneCreateDTO {

    private String name;

    private AirplaneType type;

    private int capacity;

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
        if (!(o instanceof AirplaneCreateDTO)) return false;

        AirplaneCreateDTO that = (AirplaneCreateDTO) o;

        if (capacity != that.capacity) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return type == that.type;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + capacity;
        return result;
    }

    @Override
    public String toString() {
        return "AirplaneCreateDTO{" +
                "name='" + name + '\'' +
                ", type=" + type +
                ", capacity=" + capacity +
                '}';
    }
}
