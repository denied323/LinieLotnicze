package edu.uph.ii.platformy.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such vehicle")
public class AccessoryNotFoundException extends RuntimeException{

    public AccessoryNotFoundException(){
        super(String.format("Akcesorium nie istnieje"));
    }

    public AccessoryNotFoundException(Long id){
        super(String.format("Akcesorium o id %d nie istnieje", id));
    }
}
