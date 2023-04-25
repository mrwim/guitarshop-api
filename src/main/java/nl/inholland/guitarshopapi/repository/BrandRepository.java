package nl.inholland.guitarshopapi.repository;

import nl.inholland.guitarshopapi.model.Brand;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends CrudRepository<Brand, Long> {
    Brand findBrandByName(String name);
}
