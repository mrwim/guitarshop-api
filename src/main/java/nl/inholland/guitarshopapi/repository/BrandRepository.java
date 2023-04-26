package nl.inholland.guitarshopapi.repository;

import nl.inholland.guitarshopapi.model.Brand;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BrandRepository extends CrudRepository<Brand, Long> {
    Optional<Brand> getBrandByNameIgnoreCase(String name);
}
