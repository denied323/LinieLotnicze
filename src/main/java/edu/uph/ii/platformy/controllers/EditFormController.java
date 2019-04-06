package edu.uph.ii.platformy.controllers;


import edu.uph.ii.platformy.models.*;
import edu.uph.ii.platformy.repositories.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.*;

@Controller
@SessionAttributes(names={"podanieUser"})
@Log4j2
public class EditFormController {



    @Autowired
    private UserRepository userRepository;


    @Autowired
    private PasswordEncoder passwordEncoder;







    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @GetMapping(path="/podanieUserForm.html")
    public String showPodanieUserForm(Model model){



        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        User zalogowany = userRepository.findByUsername(currentPrincipalName);

        Long id = zalogowany.getId();

        model.addAttribute("zalogowany", zalogowany);


        model.addAttribute("users", userRepository.findById(id).get());

        model.addAttribute("podanieUser", new User());


        return "podanieUserForm";
    }



    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @RequestMapping(value="/podanieUserForm.html", method= RequestMethod.POST)
    //@ResponseStatus(HttpStatus.CREATED)
    public String processPodanieUserForm(Model model, @Valid @ModelAttribute("podanieUser") User a, BindingResult errors){

        if(errors.hasErrors()){
            return "podanieUserForm";
        }



        String haslo = passwordEncoder.encode(a.getPassword());

        System.out.println(haslo +"\n" +a.getName() +"\n" +a.getSurname() +"\n" +a.getEmail());

        a.setName(a.getName());
        a.setSurname(a.getSurname());
        a.setEmail(a.getEmail());
        a.setPassword(haslo);



        //userRepository.saveAndFlush(a);


        return "redirect:";



    }











}