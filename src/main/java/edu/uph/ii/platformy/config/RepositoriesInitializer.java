package edu.uph.ii.platformy.config;

import edu.uph.ii.platformy.models.*;
import edu.uph.ii.platformy.repositories.*;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;

@Configuration
public class RepositoriesInitializer {

    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private VehicleNameRepository vehicleNameRepository;
    @Autowired
    private VehicleClassRepository vehicleClassRepository;
    @Autowired
    private AccessoryRepository accessoryRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AboutRepository aboutRepository;

    @Bean
    InitializingBean init() {

        return () -> {

            if(vehicleNameRepository.findAll().isEmpty()) {//przyjmijmy, że jeśli repozytorium typów jest puste, to trzeba zainicjalizować bazę danych

                VehicleName vn = new VehicleName("boening 307");
                vehicleNameRepository.save(vn);
                vehicleNameRepository.save(new VehicleName("airbus786"));

                VehicleClass vt = new VehicleClass("Klasa ekonomiczna");
                vehicleClassRepository.save(vt);
                vehicleClassRepository.save(new VehicleClass("Klasa biznes"));
                vehicleClassRepository.save(new VehicleClass("Pierwsza Klasa"));

                About ab = new About();
                ab.setName("Tomaszowe Linie Lotnicze");
                ab.setOpis("Bardzo fajne");
                ab.setDlaczego("Tanio u nas jest");
                aboutRepository.save(ab);



                Vehicle v1 = new Vehicle();
                v1.setVehicleName(vn);
                v1.setStart("Warszawa");
                v1.setDestination("Londyn");
                v1.setPrice(1500f);
                v1.setPlaces(100);
                v1.setDateStart(new Date(2019, 3, 21, 11,12,13));
                v1.setStartTime("12:00");
                v1.setDateEnd(new Date(2019, 3, 21,14,30,00));
                v1.setEndTime("14:00");
                v1.setVehicleClass(vt);
                vehicleRepository.save(v1);
/*
                Vehicle v2 = new Vehicle();
                v2.setVehicleName(vn);
                v2.setStart("Nowy Jork");
                v2.setDestination("Berlin");
                v2.setPrice(1800f);
                v2.setPlaces(200);
                v2.setDateStart(new Date(2019, 3, 21, 11,12,13));
                v2.setStartTime("12:00");
                v2.setDateEnd(new Date(2019, 3, 21,14,30,00));
                v2.setEndTime("14:00");
                v2.setVehicleClass(vt);
                vehicleRepository.save(v2);

                Vehicle v3 = new Vehicle();
                v3.setVehicleName(vn);
                v3.setStart("Moskwa");
                v3.setDestination("Szczecin");
                v3.setPrice(2200f);
                v3.setPlaces(250);
                v3.setDateStart(new Date(2019, 3, 22, 23,50,00));
                v3.setDateEnd(new Date(2019, 3, 23,02,15,00));
                v3.setVehicleClass(vt);
                vehicleRepository.save(v3);
*/


            }

            if(accessoryRepository.findAll().isEmpty() == true){


                Accessory a = new Accessory();
                a.setName("Toaleta");
                a.setPrice(1.15f);
                accessoryRepository.save(a);



            }

            if(roleRepository.findAll().isEmpty() == true){
                try {
                    Role roleUser = roleRepository.save(new Role(Role.Types.ROLE_USER));
                    Role roleAdmin = roleRepository.save(new Role(Role.Types.ROLE_ADMIN));

                    User user = new User("user", "Imie", "Nazwisko", "user@wp.pl", new Date(1995,12,25), true);
                    user.setRoles(new HashSet<>(Arrays.asList(roleUser)));
                    user.setPassword(passwordEncoder.encode("user"));

                    User admin = new User("admin", "ImieAdmin", "NazwiskoAdmin", "admin@wp.pl", new Date(1995,11,12), true);
                    admin.setRoles(new HashSet<>(Arrays.asList(roleAdmin)));
                    admin.setPassword(passwordEncoder.encode("admin"));


                    userRepository.save(user);
                    userRepository.save(admin);

                }catch(Exception e){
                    e.printStackTrace();
                }
            }

        };

    }

}
