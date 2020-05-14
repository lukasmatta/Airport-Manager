package cz.muni.fi.pa165.airportmanager.rest.controllers;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import cz.muni.fi.pa165.airportmanager.dto.UserAuthenticateDTO;
import cz.muni.fi.pa165.airportmanager.dto.UserDTO;
import cz.muni.fi.pa165.airportmanager.facade.UserFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

@Controller
@RequestMapping("/auth")
public class LoginController {

    private final static Logger log = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UserFacade userFacade;


    @RequestMapping(value = "", method = RequestMethod.GET)
    public String loginUser(Model model, HttpServletRequest request) {

        if(request.getSession().getAttribute("user") != null){
            return "home";
        }

        log.debug("[AUTH] Login");

        model.addAttribute("userLogin", new UserAuthenticateDTO());
        return "/auth/login";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutUser(Model model, HttpServletRequest request) {
        log.debug("[AUTH] Logout");
        request.getSession().removeAttribute("user");
        return "/";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@Valid @ModelAttribute("userLogin") UserAuthenticateDTO formBean, BindingResult bindingResult,
                        Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder, HttpServletRequest request) {
        log.debug("login(userLogin={})", formBean);

        if (bindingResult.hasErrors()) {
            for (ObjectError ge : bindingResult.getGlobalErrors()) {
                log.debug("ObjectError: {}", ge);
            }
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
                log.debug("FieldError: {}", fe);
            }
            model.addAttribute("userLogin", new UserAuthenticateDTO());
            return "/auth/login";
        }

        UserDTO matchingUser = userFacade.findByName(formBean.getName());
        if(matchingUser==null) {
            log.warn("no user with nick {}", formBean.getName());
            redirectAttributes.addFlashAttribute("alert_warning", "No user with nick: " + formBean.getName());
            return "redirect:" + uriBuilder.path("/auth").build().toUriString();
        }

        if (!userFacade.authenticate(formBean)) {
            log.warn("wrong credentials: user={} password={}", formBean.getName(), formBean.getPassword());
            redirectAttributes.addFlashAttribute("alert_warning", "Login " + formBean.getName() + " failed ");
            return "redirect:" + uriBuilder.path("/auth").build().toUriString();
        }
        request.getSession().setAttribute("user", matchingUser);
        redirectAttributes.addFlashAttribute("alert_success", "Login " + formBean.getName() + " succeeded ");
        return "redirect:" + uriBuilder.path("/").build().toUriString();
    }
}