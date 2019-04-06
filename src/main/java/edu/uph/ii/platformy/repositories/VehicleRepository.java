package edu.uph.ii.platformy.repositories;

import edu.uph.ii.platformy.models.Vehicle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface VehicleRepository extends JpaRepository<Vehicle, Long>, JpaSpecificationExecutor<Vehicle> {


    Vehicle findById(Vehicle id);

    @Query("SELECT v FROM Vehicle v WHERE " +
            "(" +
            ":phrase5 is null OR :phrase5 = '' OR "+
            "upper(v.start) LIKE upper(:phrase5)" +
            ")" + " AND " +
            "(" +
            ":phrase6 is null OR :phrase6 = '' OR "+
            "upper(v.destination) LIKE upper(:phrase6)" +
            ") " +
            "AND " +
            "(:min is null OR :min <= v.price) " +
            "AND (:max is null OR v.price <= :max)")
    Page<Vehicle> findAllVehiclesUsingFilter(@Param("phrase5") String phrase5, @Param("phrase6") String phrase6, @Param("min") Float priceMin, @Param("max") Float priceMax, Pageable pageable);

//    @Query("SELECT v FROM Vehicle v WHERE " +
//            "((" +
//            ":phrase5 is null OR :phrase5 = ''  "+ " OR " +
//            "upper(v.start) LIKE upper(:phrase5) " + " AND " +
//            "upper(v.destination) LIKE upper(:phrase6)" +
//            ") " +
//            "AND " +
//            "(:min is null OR :min <= v.price) " +
//            "AND (:max is null OR v.price <= :max))" + " OR " +
//
//            "( :phrase5 is null OR :phrase5 = ''  "+ " OR " +
//            "upper(v.start) LIKE upper(:phrase5) )" + " OR " +
//            "upper(v.destination) LIKE upper(:phrase6)" +
//
//
//    )

}