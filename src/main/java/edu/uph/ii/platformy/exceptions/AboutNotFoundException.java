package edu.uph.ii.platformy.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such vehicle")
public class AboutNotFoundException extends RuntimeException{

    public AboutNotFoundException(){
        super(String.format("about nie istnieje"));
    }

    public AboutNotFoundException(Long id){
        super(String.format("about o id %d nie istnieje", id));
    }
}
