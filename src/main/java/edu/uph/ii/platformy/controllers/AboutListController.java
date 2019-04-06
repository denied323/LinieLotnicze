package edu.uph.ii.platformy.controllers;

import edu.uph.ii.platformy.controllers.commands.AccessoryFilter;
import edu.uph.ii.platformy.controllers.commands.VehicleFilter;
import edu.uph.ii.platformy.repositories.AboutRepository;
import edu.uph.ii.platformy.services.AboutService;
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
@Log4j2
public class AboutListController {

    @Autowired
    private AboutService aboutService;

    @Autowired
    private AboutRepository aboutRepository;

    @RequestMapping(value="/about.html")
    public String showAbout(Model model){
        Integer y = 1;
        long x = y.longValue();
        model.addAttribute("about", aboutRepository.findById(Long.valueOf(1)).get());
        return "about";
    }


    private String prepareQueryString(String queryString) {//np., did=20&page=2&size=20
        if (queryString.contains("&")) {
            return "?"+queryString.substring(queryString.indexOf("&") + 1);//obcinamy parametr did, bo inaczej po przekierowaniu znowu będzie wywołana metoda deleteVihicle
        }else{
            return "";
        }
    }


}