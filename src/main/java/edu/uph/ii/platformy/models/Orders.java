package edu.uph.ii.platformy.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.Valid;
import java.util.Date;

@Entity
@Table(name="orders")
@Data
@NoArgsConstructor
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String surname;

    //private Long idLotu;
    private Long idUsera;
    private Long idLotu;

    int ilosc;

    private Float price;

    @Valid
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_lota", nullable = false)
    private Vehicle vehicle;

    @Valid
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userowe_id", nullable = false)
    private User user;



    private int status;


    public Orders(Long id, String name, String surname, Long idUsera, Long idLotu, int ilosc, float price, Vehicle vehicle, User user, int status){
        this(name, surname, idUsera, idLotu, ilosc, price, vehicle, user, status);
        this.id=id;
    }



    public Orders(String name, String surname, Long idUsera, Long idLotu, int ilosc, float price, Vehicle vehicle, User user, int status){
        this.name=name;
        this.surname=surname;
        this.idLotu=idLotu;
        this.idUsera=idUsera;
        this.ilosc=ilosc;
        this.price=price;
        this.vehicle=vehicle;
        this.user=user;
        this.status=status;
    }



}