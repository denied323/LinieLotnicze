package edu.uph.ii.platformy.controllers;

import edu.uph.ii.platformy.models.Accessory;
import edu.uph.ii.platformy.models.Vehicle;
import edu.uph.ii.platformy.models.VehicleClass;
import edu.uph.ii.platformy.models.VehicleName;
import edu.uph.ii.platformy.services.AccessoryService;
import edu.uph.ii.platformy.services.VehicleNameService;
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
@SessionAttributes(names={"vehicleName"})
@Log4j2
public class VehicleNameFormController {

    private VehicleNameService vehicleNameService;

    //@Autowired
    public VehicleNameFormController(VehicleNameService vehicleNameService)
    {
        this.vehicleNameService = vehicleNameService;
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value="/vehicleNameForm.html", method= RequestMethod.GET)
    public String showForm(Model model, Optional<Long> id){

        model.addAttribute("vehicleName",
                id.isPresent()?
                        vehicleNameService.getVehicleName(id.get()):
                        new VehicleName());

        return "vehicleNameForm";
    }



    @Secured("ROLE_ADMIN")
    @RequestMapping(value="/vehicleNameForm.html", method= RequestMethod.POST)
    //@ResponseStatus(HttpStatus.CREATED)
    public String processForm(@Valid @ModelAttribute("vehicleName") VehicleName a, BindingResult errors){

        if(errors.hasErrors()){
            return "vehicleName";
        }

        //log.info("Data utworzenia komponentu "+v.getCreatedDate());
        //log.info("Data edycji komponentu "+new Date());

        vehicleNameService.saveVehicleName(a);

        return "redirect:vehicleNameList.html";//po udanym dodaniu/edycji przekierowujemy na listę
    }

/*
    @InitBinder
    public void initBinder(WebDataBinder binder) {//Rejestrujemy edytory właściwości

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy-hh-mm");
		dateFormat.setLenient(false);
		CustomDateEditor dateEditor = new CustomDateEditor(dateFormat, false);
		binder.registerCustomEditor(Date.class, "dateStart", dateEditor);
        binder.registerCustomEditor(Date.class, "dateEnd", dateEditor);

        DecimalFormat numberFormat = new DecimalFormat("#0.00");
        numberFormat.setMaximumFractionDigits(2);
        numberFormat.setMinimumFractionDigits(2);
        numberFormat.setGroupingUsed(false);
        binder.registerCustomEditor(Float.class, "price", new CustomNumberEditor(Float.class, numberFormat, false));

    }
*/
}








