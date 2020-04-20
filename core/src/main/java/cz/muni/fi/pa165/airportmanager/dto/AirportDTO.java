package cz.muni.fi.pa165.airportmanager.dto;

import java.util.Objects;

/**
 * DTO for Airport
 *
 * @author Tomáš Janíček
 */

public class AirportDTO {
    private Long id;
    private String city;
    private String country;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AirportDTO)) return false;
        AirportDTO that = (AirportDTO) o;
        return id.equals(that.id) &&
                city.equals(that.city) &&
                country.equals(that.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, city, country);
    }
}
