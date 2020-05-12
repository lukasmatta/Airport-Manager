package cz.muni.fi.pa165.airportmanager.dto;

/**
 * DTO for User role
 *
 * @author Tomáš Janíček
 */
public class UserDTO extends BaseDTO {

    private String name;

    private String passwordHash;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserDTO)) return false;

        UserDTO userDTO = (UserDTO) o;

        if (getName() != null ? !getName().equals(userDTO.getName()) : userDTO.getName() != null) return false;
        return getPasswordHash() != null ? getPasswordHash().equals(userDTO.getPasswordHash()) : userDTO.getPasswordHash() == null;
    }

    @Override
    public int hashCode() {
        int result = getName() != null ? getName().hashCode() : 0;
        result = 31 * result + (getPasswordHash() != null ? getPasswordHash().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "name='" + name + '\'' +
                ", passwordHash='" + passwordHash + '\'' +
                ", id=" + id +
                '}';
    }
}
