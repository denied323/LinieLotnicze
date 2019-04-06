package edu.uph.ii.platformy.services;

import edu.uph.ii.platformy.controllers.commands.VehicleFilter;
import edu.uph.ii.platformy.models.VehicleName;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface VehicleNameService {


    Page<VehicleName> getAllVehicleName(VehicleFilter filter, Pageable pageable);

    VehicleName getVehicleName(Long id);

    void deleteVehicleName(Long id);

    void saveVehicleName(VehicleName name);
}
