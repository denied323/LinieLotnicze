package edu.uph.ii.platformy.services;

import edu.uph.ii.platformy.controllers.commands.VehicleFilter;
import edu.uph.ii.platformy.models.Accessory;
import edu.uph.ii.platformy.models.User;
import edu.uph.ii.platformy.models.VehicleName;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
// Własne metody
    void save(User user);

    //Page<User> getAllUser();

    User getUser(Long id);

    boolean isUniqueLogin(String login);
}
