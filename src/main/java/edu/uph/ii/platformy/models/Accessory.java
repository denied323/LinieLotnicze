package edu.uph.ii.platformy.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Positive;

@Entity
@Table(name="accessories")
@Data
@NoArgsConstructor
public class Accessory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;
    private String name;

    @Positive
    @Max(1000000)
    private Float price;

    public Accessory(String name, Float price){
        this.name = name;
        this.price = price;
    }

}
