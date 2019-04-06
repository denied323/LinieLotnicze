package edu.uph.ii.platformy.services;

import edu.uph.ii.platformy.controllers.commands.VehicleFilter;
import edu.uph.ii.platformy.exceptions.AboutNotFoundException;
import edu.uph.ii.platformy.exceptions.VehicleNotFoundException;
import edu.uph.ii.platformy.models.About;
import edu.uph.ii.platformy.models.Accessory;
import edu.uph.ii.platformy.repositories.AboutRepository;
import edu.uph.ii.platformy.repositories.AccessoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class AboutServiceImpl implements AboutService {


    @Autowired
    private AboutRepository aboutRepository;


//    @Transactional
//    @Override
//    public About getAbout(int id) {
//        Integer y = 1;
//        long x = y.longValue();
//        //Optional<About> optionalAccessory = aboutRepository.findAll(); //long na int
//        //About about = optionalAccessory.orElseThrow(()->new VehicleNotFoundException(x));
//        //return about;
//
//    }


    @Transactional
    @Override
    public About getAbout(Long id) {

        Optional<About> optionalAbout = aboutRepository.findById(id); //long na int
        About about = optionalAbout.orElseThrow(()->new AboutNotFoundException(id));
        //accessory.getName().size();//dociągnięcie leniwych akcesoriów. Wymagana adnotacja @Transaction nad metodą lub klasą, aby findById nie zamknęło transakcji
        return about;
    }



    @Override
    public void saveAbout(About about) {
        aboutRepository.save(about);
    }
}
