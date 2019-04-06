package edu.uph.ii.platformy.controllers.commands;

import lombok.Getter;
import lombok.Setter;
import org.springframework.util.StringUtils;

import javax.validation.constraints.PositiveOrZero;
import java.util.Date;

@Getter @Setter
public class VehicleFilter {





    private String phrase5;//z

    private String phrase6;//do

    @PositiveOrZero
    private Float minPrice;

    @PositiveOrZero
    private Float maxPrice;


    public boolean isEmpty(){
        return StringUtils.isEmpty(phrase5) && StringUtils.isEmpty(phrase6) && minPrice == null && minPrice == null;
    }

    public void clear(){
        this.minPrice = null;
        this.maxPrice = null;
        this.phrase5 = "";
        this.phrase6 = "";
    }




    public String getPhrase5LIKE(){
        if(StringUtils.isEmpty(phrase5)) {
            return null;
        }else{
            return "%"+phrase5+"%";
        }
    }

    public String getPhrase6LIKE(){
        if(StringUtils.isEmpty(phrase6)) {
            return null;
        }else{
            return "%"+phrase6+"%";
        }
    }


}
