package cz.muni.fi.pa165.airportmanager.rest.security;


import cz.muni.fi.pa165.airportmanager.dto.UserDTO;
import javax.servlet.annotation.WebFilter;


@WebFilter(urlPatterns =  {"/stewards/auth/*", "/flights/auth/*", "/airports/auth/*", "/airplanes/auth/*"})
public class AdminFilter extends LoggedFilter {
    @Override
    public Boolean checkRequirements(UserDTO user) {
        return user.isAdmin();
    }
}
