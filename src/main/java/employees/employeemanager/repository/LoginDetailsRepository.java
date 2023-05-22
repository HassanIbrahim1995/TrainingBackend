package employees.employeemanager.repository;

import employees.employeemanager.model.LoginDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginDetailsRepository extends JpaRepository<LoginDetails, Long> {
}
