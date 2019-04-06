package edu.uph.ii.platformy.repositories;

import edu.uph.ii.platformy.models.Accessory;
import edu.uph.ii.platformy.models.Vehicle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AccessoryRepository extends JpaRepository<Accessory, Integer> {



    @Query("SELECT v FROM Accessory v WHERE " +
            "(" +
            ":phrase5 is null OR :phrase5 = '' OR "+
            "upper(v.name) LIKE upper(:phrase5)" +
            ") " +
            "AND (:max is null OR v.price <= :max)")


    Page<Accessory> findAllAccessoriesUsingFilter(@Param("phrase5") String p, @Param("max") Float priceMax, Pageable pageable);


}
