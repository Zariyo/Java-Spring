package pl.edu.ug.lab.wpluzek.projekt.Repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.edu.ug.lab.wpluzek.projekt.Domain.Furniture;
import pl.edu.ug.lab.wpluzek.projekt.Domain.Manufacturer;

import java.util.Date;
import java.util.List;

@Repository
public interface FurnitureRepository extends CrudRepository<Furniture, Long> {
    List<Furniture> findById(long id);
    List<Furniture> findByName(String name);

    @Query("SELECT f FROM Furniture f JOIN f.manufacturer m WHERE m.name = :manufacturerName")
    List<Furniture> findByManufacturerName(@Param("manufacturerName") String manufacturerName);

}
