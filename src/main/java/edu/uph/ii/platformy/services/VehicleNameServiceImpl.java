package edu.uph.ii.platformy.services;

import edu.uph.ii.platformy.controllers.commands.VehicleFilter;
import edu.uph.ii.platformy.exceptions.VehicleNotFoundException;
import edu.uph.ii.platformy.models.VehicleName;
import edu.uph.ii.platformy.repositories.VehicleNameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class VehicleNameServiceImpl implements VehicleNameService {

    @Autowired
    private VehicleNameRepository vehicleNameRepository;


    @Override
    public Page<VehicleName> getAllVehicleName(VehicleFilter search, Pageable pageable) {
        Page page;
        if(search.isEmpty()){
            page = vehicleNameRepository.findAll(pageable);
        }else{
            //page = vehicleNameRepository.findAll(pageable);
            page = vehicleNameRepository.findAllVehicleNamesUsingFilter(search.getPhrase5LIKE(), pageable);
        }

        return page;

    }

    @Transactional
    @Override
    public VehicleName getVehicleName(Long id) {

        Optional<VehicleName> optionalAccessory = vehicleNameRepository.findById(id); //long na int
        VehicleName vehicleName = optionalAccessory.orElseThrow(()->new VehicleNotFoundException(id));
        //accessory.getName().size();//dociągnięcie leniwych akcesoriów. Wymagana adnotacja @Transaction nad metodą lub klasą, aby findById nie zamknęło transakcji
        return vehicleName;
    }

    @Override
    public void deleteVehicleName(Long id) {
        // w przypadku usuwania obsługa wyjątku VehicleNotFoundException nie jest niezbędna dla bezpieczeństwa systemu
        if(vehicleNameRepository.existsById(id) == true){
            vehicleNameRepository.deleteById(id);
        }else{
            throw new VehicleNotFoundException(id);
        }
    }

    @Override
    public void saveVehicleName(VehicleName vehicleName) {
        vehicleNameRepository.save(vehicleName);
    }
}
