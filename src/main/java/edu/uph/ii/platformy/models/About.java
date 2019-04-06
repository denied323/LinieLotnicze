package edu.uph.ii.platformy.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class About{

    @Min(0)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String opis;

    private String dlaczego;

    public About(String name, String opis, String dlaczego){
        this.name = name;
        this.opis=opis;
        this.dlaczego=dlaczego;
    }

    public About(Long id, String name,String opis, String dlaczego){
        this.id = id;
        this.name = name;
        this.opis=opis;
        this.dlaczego=dlaczego;
    }

}