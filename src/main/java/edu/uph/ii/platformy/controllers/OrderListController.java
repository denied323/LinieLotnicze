package edu.uph.ii.platformy.controllers;

import edu.uph.ii.platformy.models.Orders;
import edu.uph.ii.platformy.models.User;
import edu.uph.ii.platformy.models.Vehicle;
import edu.uph.ii.platformy.repositories.OrdersRepository;
import edu.uph.ii.platformy.repositories.UserRepository;
import edu.uph.ii.platformy.repositories.VehicleRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class OrderListController {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrdersRepository orderRepository;

    @Autowired
    private VehicleRepository vehicleRepository;



//    @RequestMapping(value="/orderList.html", method = {RequestMethod.GET, RequestMethod.POST})
//    public String showOrderList(Model model){
//
//
//        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
//        String currentPrincipalName = authentication.getName();
//        User zalogowany = userRepository.findByUsername(currentPrincipalName);
//        model.addAttribute("zalogowany", zalogowany);
//        Long id = zalogowany.getId();
//
//
//
//        Order o = orderRepository.findByIdUsera(id);
//        Long id1 = o.getIdLotu();
//
//        if(o==null) {
//
//            return "redirect:pusto.html";
//
//        }
//        //Vehicle veh = vehicleRepository.findById(id1).get();
//        //model.addAttribute("veh", veh);
//        model.addAttribute("orderListPage", o);
//
//
//        return "orderList";
//
//
//    }





    @RequestMapping(value="/orderList.html", method = {RequestMethod.GET, RequestMethod.POST})
    public String showOrderList(Model model){

        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        User zalogowany = userRepository.findByUsername(currentPrincipalName);
        model.addAttribute("zalogowany", zalogowany);

        Long idu = zalogowany.getId();

        //Orders order = (Orders) orderRepository.findAll();
        //Orders ordera = orderRepository.findById(idu);

        //Vehicle veh = vehicleRepository.findById(id1).get();
        //model.addAttribute("veh", veh);
        model.addAttribute("orderListPage", orderRepository.findAll());
//
//
        return "orderList";
//
    }











    @GetMapping(path="/odrzucOrder.html", params={"id"})
    public String odrzucStypendia(Model model , @RequestParam("id") Long id){

        Orders order = orderRepository.findById(id).get();

        int ilosc2=order.getIlosc();

        Long ids = order.getIdLotu();

        orderRepository.delete(order);


        Vehicle vehicle = vehicleRepository.findById(ids).get();

        int ilosc1 = vehicle.getPlaces();

        vehicle.setPlaces(ilosc2+ilosc1);

        vehicleRepository.saveAndFlush(vehicle);

        return "redirect:orderList.html";
    }




    @GetMapping(path="/odrzucOrderBez.html", params={"id"})
    public String odrzucOrderBez(Model model , @RequestParam("id") Long id){

        Orders order = orderRepository.findById(id).get();

        orderRepository.delete(order);


        return "redirect:orderList.html";
    }




    @GetMapping(path="/akceptujOrder.html", params={"id"})
    public String akceptOrder(@RequestParam("id") Long id){


        Orders order = orderRepository.findById(id).get();

        order.setStatus(2);

        orderRepository.saveAndFlush(order);


        return "redirect:orderList.html";
    }


    @GetMapping(path="/akceptujOrderAdmin.html", params={"id"})
    public String akceptOrderAdmin(@RequestParam("id") Long id){


        Orders order = orderRepository.findById(id).get();

        order.setStatus(3);

        orderRepository.saveAndFlush(order);


        return "redirect:orderList.html";
    }


    @GetMapping(path="/odrzucOrderAdmin.html", params={"id"})
    public String odrzucOrderAdmin(Model model , @RequestParam("id") Long id){

        Orders order = orderRepository.findById(id).get();
        orderRepository.delete(order);

        return "orderList";
    }




}