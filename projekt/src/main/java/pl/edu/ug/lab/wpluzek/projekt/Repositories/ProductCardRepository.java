package pl.edu.ug.lab.wpluzek.projekt.Repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.edu.ug.lab.wpluzek.projekt.Domain.ProductCard;

import java.util.Date;
import java.util.List;

@Repository
public interface ProductCardRepository extends CrudRepository<ProductCard, Long> {
    ProductCard findById(long id);
    List<ProductCard> findByModelNumber(String modelNumber);


}
