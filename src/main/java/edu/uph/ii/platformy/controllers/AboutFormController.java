package edu.uph.ii.platformy.controllers;

import edu.uph.ii.platformy.models.*;
import edu.uph.ii.platformy.services.AboutService;
import edu.uph.ii.platformy.services.AccessoryService;
import edu.uph.ii.platformy.services.VehicleService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@SessionAttributes(names={"about"})
@Log4j2
public class AboutFormController {

    private AboutService aboutService;

    //@Autowired
    public AboutFormController(AboutService aboutService)
    {
        this.aboutService = aboutService;
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value="/aboutForm.html", method= RequestMethod.GET)
    public String showForm(Model model, Optional<Long> id){

        model.addAttribute("about",
                id.isPresent()?
                        aboutService.getAbout(id.get()):
                        new About());

        return "aboutForm";
    }



    @Secured("ROLE_ADMIN")
    @RequestMapping(value="/aboutForm.html", method= RequestMethod.POST)
    public String processForm(@Valid @ModelAttribute("about") About a, BindingResult errors){

        if(errors.hasErrors()){
            return "aboutForm";
        }

        //log.info("Data utworzenia komponentu "+v.getCreatedDate());
        //log.info("Data edycji komponentu "+new Date());

        aboutService.saveAbout(a);

        return "redirect:about.html";//po udanym dodaniu/edycji przekierowujemy na listę
    }

}








