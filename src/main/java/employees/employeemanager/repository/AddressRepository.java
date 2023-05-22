package employees.employeemanager.repository;

import employees.employeemanager.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
public interface AddressRepository extends JpaRepository<Address, Long> {

}
