package nl.inholland.guitarshopapi.repository;

import nl.inholland.guitarshopapi.model.Guitar;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuitarRepository extends CrudRepository<Guitar, Long> {
}
