package pl.edu.ug.lab.wpluzek.projekt.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.edu.ug.lab.wpluzek.projekt.Domain.Shop;

import java.util.Date;
import java.util.List;

@Repository
public interface ShopRepository extends CrudRepository<Shop, Long> {
    List<Shop> findById(long id);
    List<Shop> findByName(Date date);

}
