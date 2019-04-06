package edu.uph.ii.platformy.controllers;


import edu.uph.ii.platformy.models.Orders;
import edu.uph.ii.platformy.models.User;
import edu.uph.ii.platformy.models.Vehicle;
import edu.uph.ii.platformy.repositories.OrdersRepository;
import edu.uph.ii.platformy.repositories.UserRepository;
import edu.uph.ii.platformy.repositories.VehicleRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;


@Controller
@SessionAttributes(names={"podanieOrder"})
@Log4j2
public class PodanieOrderFormController {



    @Autowired
    private OrdersRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VehicleRepository vehicleRepository;





    @Secured("ROLE_USER")
    @GetMapping(path="/podanieOrderForm.html", params={"id"})
    public String showPodanieOrderForm(Model model, @RequestParam("id") Long id){



        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        User zalogowany = userRepository.findByUsername(currentPrincipalName);

        //Vehicle test = vehicleRepository.findById(id);

        Vehicle vehicle = vehicleRepository.findById(id).get();

        int dostepne = vehicle.getPlaces();
        float cena = vehicle.getPrice();




        model.addAttribute("cena",cena);

        model.addAttribute("zalogowany", zalogowany);

        model.addAttribute("zzz",id);

        model.addAttribute("dostepne", dostepne);

        model.addAttribute("vehicle", vehicleRepository.findById(id).get());



        model.addAttribute("podanieOrder", new Orders());





        return "podanieOrderForm";
    }



    @Secured({"ROLE_USER"})
    @RequestMapping(value="/podanieOrderForm.html", method= RequestMethod.POST)
    //@ResponseStatus(HttpStatus.CREATED)
    public String processPodanieOrderForm(Model model, @Valid @ModelAttribute("podanieOrder") Orders a, BindingResult errors){



//        for (int i=0;i<page.size();i++){
//                id++;
//            }

//
//
////        for()


        orderRepository.save(a);







        //Order order = orderRepository.maxId();

        Long id=a.getIdLotu();

        System.out.println(id);
        Vehicle vehicle = vehicleRepository.findById(id).get();

        int dostepne = vehicle.getPlaces();

        System.out.println("   " +dostepne);
        //Order ordero = orderRepository.findById(id).get();
        vehicle.setPlaces(dostepne-(a.getIlosc()));
        vehicleRepository.saveAndFlush(vehicle);







        return "redirect:";

    }






}
