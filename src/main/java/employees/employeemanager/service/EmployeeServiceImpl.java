package employees.employeemanager.service;

import employees.employeemanager.model.Employee;
import employees.employeemanager.repository.EmployeeRepository;
import employees.employeemanager.service.passwordService.PasswordService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final PasswordService passwordService;
    @Override
    public Employee getEmployeeById(Long employeeId) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(employeeId);
        return optionalEmployee.orElse(null);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee createEmployee(Employee employee) {
        String hashedPassword = passwordService.hashPassword(employee.getLoginDetails().getPassword());
        employee.getLoginDetails().setPassword(hashedPassword);
        return employeeRepository.save(employee);
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployee(Long employeeId) {
        employeeRepository.deleteById(employeeId);
    }

    @Override
    public List<Employee> getEmployeesByPage(int page, int perPage) {
        Pageable pageable = PageRequest.of(page - 1, perPage);
        Page<Employee> employeePage = employeeRepository.findAll(pageable);
        return employeePage.getContent();
    }

    @Override
    public Optional<Employee> getEmployeeByUsername(String username) {
        return employeeRepository.findByLoginDetails_Username(username);
    }

}

