package pl.edu.ug.lab.wpluzek.projekt.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.edu.ug.lab.wpluzek.projekt.Domain.Manufacturer;

import java.util.Date;
import java.util.List;

@Repository
public interface ManufacturerRepository extends CrudRepository<Manufacturer, Long> {
    List<Manufacturer> findById(long id);
    Manufacturer findByName(String name);

}
