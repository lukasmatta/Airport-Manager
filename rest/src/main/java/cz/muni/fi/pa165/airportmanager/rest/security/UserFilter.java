package cz.muni.fi.pa165.airportmanager.rest.security;

import javax.servlet.annotation.WebFilter;

@WebFilter(urlPatterns =  {"/airports/*", "/airplanes/*", "/flights/*", "/stewards/*"})
public class UserFilter extends LoggedFilter { }