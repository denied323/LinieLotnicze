package edu.uph.ii.platformy.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.sql.Time;
import java.util.*;


@Entity
@Table(name = "vehicles")
@Data
@AllArgsConstructor
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Valid
    @ManyToOne(fetch = FetchType.EAGER)//EAGER powoduje zaciągnięcie obiektu VehicleType wraz z obiektem Vehicle.
    @JoinColumn(name="name_id")
    private VehicleName vehicleName;

    @NotBlank
    @Size(min = 2, max = 50)
    public String start;

    @NotBlank
    @Size(min = 2, max = 50)
    public String destination;

    //@NotBlank
    @Positive
    @Max(1000000)
    public Float price;

    //@NotBlank
    @PositiveOrZero
    @Max(300)
    public int places;

    //@NotBlank
    //@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @Future //data pozniejsza
    //@Temporal(TemporalType.DATE)
    public Date dateStart;

    @NotBlank
    @Size(min = 2, max = 50)
    public String startTime;

    //@NotBlank
    //@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @Future //data pozniejsza
    //@Temporal(TemporalType.DATE)
    public Date dateEnd;

    @NotBlank
    @Size(min = 2, max = 50)
    public String endTime;


    @Valid
    @ManyToOne(fetch = FetchType.EAGER)//EAGER powoduje zaciągnięcie obiektu VehicleType wraz z obiektem Vehicle.
    @JoinColumn(name="class_id")
    private VehicleClass vehicleClass;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Accessory> accessories;



    public Vehicle() {
        this.vehicleName = new VehicleName();
        this.dateStart = new Date();
        this.dateEnd = new Date();
        this.vehicleClass = new VehicleClass();
        this.accessories = new HashSet<>();
    }

    public Vehicle(long id, VehicleName vehicleName, String start, String destination, Float price, Integer places, Date dateStart, String startTime, Date dateEnd, String endTime, VehicleClass vehicleClass) {
        this(vehicleName, start, destination, price, places, dateStart, startTime, dateEnd, endTime, vehicleClass);
        this.id = id;
    }

    public Vehicle(VehicleName vehicleName, String start, String destination, Float price, Integer places, Date dateStart, String startTime, Date dateEnd, String endTime, VehicleClass vehicleClass) {
        this.vehicleName = vehicleName;
        this.start = start;
        this.destination = destination;
        this.price = price;
        this.places = places;
        this.dateStart = dateStart;
        this.startTime=startTime;
        this.dateEnd = dateEnd;
        this.endTime=endTime;
        this.vehicleClass = vehicleClass;
        this.accessories = accessories;
    }
}
