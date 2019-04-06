package edu.uph.ii.platformy.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such vehicle")
public class VehicleNotFoundException extends RuntimeException{

    public VehicleNotFoundException(){
        super(String.format("Oferta nie istnieje"));
    }

    public VehicleNotFoundException(Long id){
        super(String.format("Oferta o id %d nie istnieje", id));
    }
}
