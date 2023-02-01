package pl.edu.ug.lab.wpluzek.projekt.Repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.edu.ug.lab.wpluzek.projekt.Domain.Furniture;
import pl.edu.ug.lab.wpluzek.projekt.Domain.Shop;

import java.util.Date;
import java.util.List;

@Repository
public interface ShopRepository extends CrudRepository<Shop, Long> {
    Shop findById(long id);
    List<Shop> findByName(Date date);

    @Query(value = "SELECT f from Furniture f WHERE f NOT IN (SELECT sf from Shop s JOIN s.availableFurniture sf WHERE s.id = :shop_id)")
    List<Furniture> findNewFurniture(@Param("shop_id") long shop_id);


}
