package edu.uph.ii.platformy.services;

import edu.uph.ii.platformy.controllers.commands.VehicleFilter;
import edu.uph.ii.platformy.models.Accessory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AccessoryService {


    Page<Accessory> getAllAccessories(VehicleFilter filter, Pageable pageable);

    Accessory getAccessory(Long id);

    void deleteAccessory(Long id);

    void saveAccessory(Accessory accessory);
}
