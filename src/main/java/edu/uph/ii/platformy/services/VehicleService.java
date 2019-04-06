package edu.uph.ii.platformy.services;

import edu.uph.ii.platformy.controllers.commands.VehicleFilter;
import edu.uph.ii.platformy.models.Accessory;
import edu.uph.ii.platformy.models.Vehicle;
import edu.uph.ii.platformy.models.VehicleClass;
import edu.uph.ii.platformy.models.VehicleName;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface VehicleService {

    List<Accessory> getAllAccessories();

    List<VehicleName> getAllNames();

    List<VehicleClass> getAllClasses();

    Page<Vehicle> getAllVehicles(VehicleFilter filter, Pageable pageable);

    Vehicle getVehicle(Long id);

    void deleteVehicle(Long id);

    void saveVehicle(Vehicle vehicle);
}
