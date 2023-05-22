package employees.employeemanager.service;

import employees.employeemanager.model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    Employee getEmployeeById(Long employeeId);

    List<Employee> getAllEmployees();

    public Employee createEmployee(Employee employee);

    Employee updateEmployee(Employee employee);

    void deleteEmployee(Long employeeId);
    public List<Employee> getEmployeesByPage(int page, int perPage);

    Optional<Employee> getEmployeeByUsername(String username);
}

