package pl.edu.ug.lab.wpluzek.lab10.Service;

import org.springframework.stereotype.Service;
import pl.edu.ug.lab.wpluzek.lab10.Model.Customer;
import pl.edu.ug.lab.wpluzek.lab10.Repositories.CustomerRepository;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class CustomerService {
    final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void setCustomers(){

        Customer customer1 = new Customer("Adam", 2000.40, new Date(2002, Calendar.NOVEMBER,20));
        Customer customer2 = new Customer("Jan", 200.00, new Date());

        customerRepository.save(customer1);
        customerRepository.save(customer2);

        List<Customer> found = customerRepository.findByMoney(200);
        List<Customer> foundDate = customerRepository.findByDate(new Date(2002, Calendar.NOVEMBER,20));

        List<Customer> allCustomers = (List<Customer>) customerRepository.findAll();

        allCustomers
                .stream().map(Customer::toString)
                .forEach(System.out::println);

        System.out.println(found.get(0).getName());
        System.out.println(foundDate.get(0).getName());
    }
}
