package edu.uph.ii.platformy.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;

@Entity
@NamedQuery(name = "VehicleName.findAllVehicleNamesUsingNamedQuery",
        query =(
                "SELECT v FROM VehicleName v WHERE " +


                        ":phrase5 is null OR :phrase5 = '' OR "+
                        "upper(v.name) LIKE upper(:phrase5)" ))
@Getter
@Setter
@NoArgsConstructor
public class VehicleName{

    @Min(0)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public VehicleName( String name){
        this.name = name;
    }

    public VehicleName(Long id, String name){
        this.id = id;
        this.name = name;
    }

}
