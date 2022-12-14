package pl.edu.ug.lab.wpluzek.lab10.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.edu.ug.lab.wpluzek.lab10.Model.Customer;

import java.util.Date;
import java.util.List;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {
    List<Customer> findByMoney(double money);
    List<Customer> findByDate(Date date);

}
