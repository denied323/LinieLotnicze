package edu.uph.ii.platformy.repositories;

import edu.uph.ii.platformy.models.Accessory;
import edu.uph.ii.platformy.models.Vehicle;
import edu.uph.ii.platformy.models.VehicleName;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface VehicleNameRepository extends JpaRepository<VehicleName, Long>, JpaSpecificationExecutor<VehicleName> {

    //nazwa metody jest jednocześnie zapytaniem
    //Page<Vehicle> findByVehicleNameContaining(String phrase, Pageable pageable);


    //nad klasą Vehicle znajduje się definicja zapytania (@NamedQuery) powiązana z tą metodą
    Page<VehicleName> findAllVehicleNamesUsingNamedQuery(String phrase5, Pageable pageable);


    @Query(
            "SELECT v FROM VehicleName v WHERE " +


                    ":phrase5 is null OR :phrase5 = '' OR "+
                    "upper(v.name) LIKE upper(:phrase5)" )
    Page<Vehicle> findAllVehicleNamesUsingFilter(@Param("phrase5") String phrase5, Pageable pageable);

}