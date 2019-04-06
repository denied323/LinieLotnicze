package edu.uph.ii.platformy.services;

import edu.uph.ii.platformy.controllers.commands.VehicleFilter;
import edu.uph.ii.platformy.exceptions.VehicleNotFoundException;
import edu.uph.ii.platformy.models.Accessory;
import edu.uph.ii.platformy.repositories.AccessoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class AccessoryServiceImpl implements AccessoryService {

    @Autowired
    private AccessoryRepository accessoryRepository;


    @Override
    public Page<Accessory> getAllAccessories(VehicleFilter search, Pageable pageable) {
        Page page;
        if(search.isEmpty()){
            page = accessoryRepository.findAll(pageable);
        }else{
            //page = vehicleRepository.findAll(pageable);
            page = accessoryRepository.findAllAccessoriesUsingFilter(search.getPhrase5LIKE(), search.getMaxPrice(), pageable);
        }

        return page;

    }

    @Transactional
    @Override
    public Accessory getAccessory(Long id) {

        Optional<Accessory> optionalAccessory = accessoryRepository.findById(Math.toIntExact(id)); //long na int
        Accessory accessory = optionalAccessory.orElseThrow(()->new VehicleNotFoundException(id));
        //accessory.getName().size();//dociągnięcie leniwych akcesoriów. Wymagana adnotacja @Transaction nad metodą lub klasą, aby findById nie zamknęło transakcji
        return accessory;
    }

    @Override
    public void deleteAccessory(Long id) {
        // w przypadku usuwania obsługa wyjątku VehicleNotFoundException nie jest niezbędna dla bezpieczeństwa systemu
        if(accessoryRepository.existsById(Math.toIntExact(id)) == true){
            accessoryRepository.deleteById(Math.toIntExact(id));
        }else{
            throw new VehicleNotFoundException(id);
        }
    }

    @Override
    public void saveAccessory(Accessory accessory) {
        accessoryRepository.save(accessory);
    }
}
