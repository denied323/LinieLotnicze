package edu.uph.ii.platformy.services;

import edu.uph.ii.platformy.controllers.commands.VehicleFilter;
import edu.uph.ii.platformy.models.About;
import edu.uph.ii.platformy.models.Accessory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AboutService {



     About getAbout(Long id);

    void saveAbout(About about);
}
