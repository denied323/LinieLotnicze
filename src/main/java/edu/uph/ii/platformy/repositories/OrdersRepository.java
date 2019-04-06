package edu.uph.ii.platformy.repositories;

import edu.uph.ii.platformy.models.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepository extends JpaRepository<Orders, Long> {

      Orders findById(Orders id);


//    @Query("SELECT o FROM order o WHERE id LIKE SELECT MAX(id) FROM order")
      Orders findByIdUsera(Long id);



}
