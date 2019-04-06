package edu.uph.ii.platformy.controllers;

import edu.uph.ii.platformy.controllers.commands.VehicleFilter;
import edu.uph.ii.platformy.models.About;
import edu.uph.ii.platformy.models.Accessory;
import edu.uph.ii.platformy.models.Orders;
import edu.uph.ii.platformy.models.User;
import edu.uph.ii.platformy.repositories.AboutRepository;
import edu.uph.ii.platformy.repositories.UserRepository;
import edu.uph.ii.platformy.services.EmailSender;
import edu.uph.ii.platformy.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.validation.Valid;
import java.util.Optional;

/**
 * Created by grzesiek on 23.08.2017.
 */
@Controller
public class UserRegistrationFormController {


    private final EmailSender emailSender;
    private final TemplateEngine templateEngine;
    @Autowired
    public UserRegistrationFormController(EmailSender emailSender,
                                          TemplateEngine templateEngine){
        this.emailSender = emailSender;
        this.templateEngine = templateEngine;
    }


    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AboutRepository aboutRepository;

    @GetMapping("/registrationForm.html")
    public String registration(Model model) {
        model.addAttribute("userCommand", new User());
        return "registrationForm";
    }


    /*
    @Secured("ROLE_ADMIN")
    @RequestMapping(value="/userForm.html", method= RequestMethod.GET)
    public String showForm(Model model, Optional<Long> id){

        model.addAttribute("user",
                id.isPresent()?
                        userService.getUser(id.get()):
                        new Accessory());

        return "userForm";
    }

    @RequestMapping(value="/userList.html", method = {RequestMethod.GET, RequestMethod.POST})
    public String showVehicleList(Model model, @Valid @ModelAttribute("searchCommand") , BindingResult result){
        model.addAttribute("userListPage", userService.getAllUser());
        return "userList";
    }
*/




    @PostMapping("/registrationForm.html")
    public String registration(@Valid @ModelAttribute("userCommand") User userForm, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "registrationForm";
        }
        userService.save(userForm);

        Long id = userRepository.findTopByOrderByIdDesc().getId();

        String email = userRepository.findTopByOrderByIdDesc().getEmail();
        String name = userRepository.findTopByOrderByIdDesc().getName() + " " + userRepository.findTopByOrderByIdDesc().getSurname();
        String link = "http://localhost:8080/akceptUser.html?id=" + id;
        About about = aboutRepository.findById(Long.valueOf(1)).get();

        String nazwa= about.getName();


        Context context = new Context();
        context.setVariable("header", "Dziękujemy za rejestrację, " + name);
        context.setVariable("title", "Witam. Zarejestrowałeś się na naszej witrynie linii lotniczych.");
        context.setVariable("description", "Kliknij na link, żeby uaktywnić konto:   " +link );
        String body = templateEngine.process("template", context);
        emailSender.sendEmail(email, nazwa + " - Rejestracja", body);



        return "registrationSuccess";
    }

    @GetMapping(path="/akceptUser.html", params={"id"})
    public String akceptUser(@RequestParam("id") Long id){

System.out.println("1 ");
        User user = userRepository.findById(id).get();
        System.out.println("2 ");
        user.setEnabled(true);
        System.out.println("3 ");
        userRepository.saveAndFlush(user);
        System.out.println("4 ");

        return "registrationSuccess2";
    }


    @InitBinder
    public void initBinder(WebDataBinder binder) {
        //aby użytkownik nie mógł sobie wstrzyknąć aktywacji konta oraz ról (np., ADMIN)
        //roles są na wszelki wypadek, bo warstwa serwisów i tak ustawia ROLE_USER dla nowego usera
        binder.setDisallowedFields("enabled", "roles");
    }

}