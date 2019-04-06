package edu.uph.ii.platformy.controllers;

import edu.uph.ii.platformy.controllers.commands.AccessoryFilter;
import edu.uph.ii.platformy.controllers.commands.VehicleFilter;
import edu.uph.ii.platformy.services.AccessoryService;
import edu.uph.ii.platformy.services.VehicleService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.text.DecimalFormat;

@Controller
@SessionAttributes("searchCommand")
@Log4j2
public class AccessoryListController {

    @Autowired
    private AccessoryService accessoryService;



    @GetMapping(value="/error1")
    public String resetaccessoryList(){
        return "redirect:accessoryList.html";
    }


    @ModelAttribute("searchCommand")
    public VehicleFilter getSimpleSearch(){
        return new VehicleFilter();
    }

    @GetMapping(value="/accessoryList.html", params = {"all"})
    public String resetaccessoryList(@ModelAttribute("searchCommand") VehicleFilter search){
        search.clear();
        return "redirect:accessoryList.html";
    }

    @RequestMapping(value="/accessoryList.html", method = {RequestMethod.GET, RequestMethod.POST})
    public String showAccessoryList(Model model, @Valid @ModelAttribute("searchCommand") VehicleFilter search, BindingResult result, Pageable pageable){
        model.addAttribute("accessoryListPage", accessoryService.getAllAccessories(search, pageable));
        return "accessoryList";
    }

    @Secured("ROLE_ADMIN")
    @GetMapping(path="/accessoryList.html", params={"did"})
    public String deleteAccessory(long did, HttpServletRequest request){
        accessoryService.deleteAccessory(did);
        String queryString = prepareQueryString(request.getQueryString());
        return String.format("redirect:accessoryList.html%s", queryString);//robimy przekierowanie, ale zachowując parametry pageingu
    }

    private String prepareQueryString(String queryString) {//np., did=20&page=2&size=20
        if (queryString.contains("&")) {
            return "?"+queryString.substring(queryString.indexOf("&") + 1);//obcinamy parametr did, bo inaczej po przekierowaniu znowu będzie wywołana metoda deleteVihicle
        }else{
            return "";
        }
    }


    @InitBinder
    public void initBinder(WebDataBinder binder) {//Rejestrujemy edytory właściwości
        DecimalFormat numberFormat = new DecimalFormat("#0.00");
        numberFormat.setMaximumFractionDigits(2);
        numberFormat.setMinimumFractionDigits(2);
        numberFormat.setGroupingUsed(false);
        CustomNumberEditor priceEditor = new CustomNumberEditor(Float.class, numberFormat, true);
        binder.registerCustomEditor(Float.class, "maxPrice", priceEditor);

    }



}