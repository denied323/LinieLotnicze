package edu.uph.ii.platformy.services;

import edu.uph.ii.platformy.controllers.commands.VehicleFilter;
import edu.uph.ii.platformy.exceptions.VehicleNotFoundException;
import edu.uph.ii.platformy.models.Accessory;
import edu.uph.ii.platformy.models.Vehicle;
import edu.uph.ii.platformy.models.VehicleClass;
import edu.uph.ii.platformy.models.VehicleName;
import edu.uph.ii.platformy.repositories.AccessoryRepository;
import edu.uph.ii.platformy.repositories.VehicleNameRepository;
import edu.uph.ii.platformy.repositories.VehicleRepository;
import edu.uph.ii.platformy.repositories.VehicleClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    private AccessoryRepository accessoryRepository;

    @Autowired
    private VehicleClassRepository vehicleClassRepository;

    @Autowired
    private VehicleNameRepository vehicleNameRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Override
    public List<Accessory> getAllAccessories() {
        return accessoryRepository.findAll();
    }

    @Override
    public List<VehicleName> getAllNames() {
        return vehicleNameRepository.findAll();
    }

    @Override
    public List<VehicleClass> getAllClasses() {
        return vehicleClassRepository.findAll();
    }


    @Override
    public Page<Vehicle> getAllVehicles(VehicleFilter search, Pageable pageable) {
        Page page;
        if(search.isEmpty()){
            page = vehicleRepository.findAll(pageable);
        }else{
            //page = vehicleRepository.findAll(pageable);
            page = vehicleRepository.findAllVehiclesUsingFilter(search.getPhrase5LIKE(), search.getPhrase6LIKE(), search.getMinPrice(), search.getMaxPrice(), pageable);
        }

        return page;

    }

    @Transactional
    @Override
    public Vehicle getVehicle(Long did) {
        Optional<Vehicle> optionalVehicle = vehicleRepository.findById(did);
        Vehicle vehicle = optionalVehicle.orElseThrow(()->new VehicleNotFoundException(did));
        vehicle.getAccessories().size();//dociągnięcie leniwych akcesoriów. Wymagana adnotacja @Transaction nad metodą lub klasą, aby findById nie zamknęło transakcji
        return vehicle;
    }

    @Override
    public void deleteVehicle(Long id) {
    // w przypadku usuwania obsługa wyjątku VehicleNotFoundException nie jest niezbędna dla bezpieczeństwa systemu
        if(vehicleRepository.existsById(id) == true){
            vehicleRepository.deleteById(id);
        }else{
            throw new VehicleNotFoundException(id);
        }
    }

    @Override
    public void saveVehicle(Vehicle vehicle) {
        vehicleRepository.save(vehicle);
    }
}
