package employees.employeemanager.service;

import employees.employeemanager.model.Employee;

import java.util.List;

public interface EmployeeService {

    Employee getEmployeeById(Long employeeId);

    List<Employee> getAllEmployees();

    Employee createEmployee(Employee employee);

    Employee updateEmployee(Employee employee);

    void deleteEmployee(Long employeeId);
    public List<Employee> getEmployeesByPage(int page, int perPage);

    public int getTotalEmployeeCount();
}

