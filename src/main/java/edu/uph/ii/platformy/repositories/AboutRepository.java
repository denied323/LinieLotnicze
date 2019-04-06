package edu.uph.ii.platformy.repositories;

import edu.uph.ii.platformy.models.About;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AboutRepository extends JpaRepository<About, Long> {



    Optional<About> findById(Long id);



}
