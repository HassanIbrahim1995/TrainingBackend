package employees.employeemanager.controller;

import employees.employeemanager.dto.AddressDTO;
import employees.employeemanager.dto.EmployeeDTO;
import employees.employeemanager.model.Address;
import employees.employeemanager.model.Department;
import employees.employeemanager.model.Employee;
import employees.employeemanager.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/employees")
@AllArgsConstructor
@CrossOrigin
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("/{employeeId}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Long employeeId) {
        Employee employee = employeeService.getEmployeeById(employeeId);
        if (employee != null) {
            EmployeeDTO employeeDTO = new EmployeeDTO(employee);
            return ResponseEntity.ok(employeeDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("all")
    public List<EmployeeDTO> getAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        return employees.stream()
                .map(EmployeeDTO::new)
                .collect(Collectors.toList());
    }

    @PostMapping("/createEmployee")
    public ResponseEntity<EmployeeDTO> createEmployee(@RequestBody EmployeeDTO employeeDTO) {
        Employee employee = mapToEmployee(employeeDTO);
        Employee createdEmployee = employeeService.createEmployee(employee);
        EmployeeDTO createdEmployeeDTO = new EmployeeDTO(createdEmployee);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdEmployeeDTO);
    }

    public Employee mapToEmployee(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        employee.setBirthday(employeeDTO.getBirthday());
        employee.setAge(employeeDTO.getAge());
        employee.setPhoneNumber(employeeDTO.getPhoneNumber());
        employee.getName().setFirstName(employeeDTO.getFirstName());
        employee.getName().setLastName(employeeDTO.getLastName());
        employee.setDepartment(Department.valueOf(employeeDTO.getDepartment()));
        employee.getLoginDetails().setUsername(employeeDTO.getUsername());
        employee.getLoginDetails().setPassword(employeeDTO.getPassword());
        List<Address> addresses = new ArrayList<>();
        for (AddressDTO addressDTO : employeeDTO.getAddresses()) {
            Address address = new Address();
            address.setStreetName(addressDTO.getStreetName());
            address.setCity(addressDTO.getCity());
            address.setZipCode(addressDTO.getZipCode());
            address.setEmployee(employee);
            addresses.add(address);
        }
        employee.setAddresses(addresses);
        return employee;
    }


    @PutMapping("/{employeeId}")
    public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable Long employeeId, @RequestBody Employee employee) {
        Employee existingEmployee = employeeService.getEmployeeById(employeeId);
        if (existingEmployee != null) {
            employee.setPersonId(employeeId);
            Employee updatedEmployee = employeeService.updateEmployee(employee);
            EmployeeDTO updatedEmployeeDTO = new EmployeeDTO(updatedEmployee);
            return ResponseEntity.ok(updatedEmployeeDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{employeeId}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long employeeId) {
        Employee existingEmployee = employeeService.getEmployeeById(employeeId);
        if (existingEmployee != null) {
            employeeService.deleteEmployee(employeeId);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getAllEmployeesWithPages(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int perPage) {
        List<Employee> employees = employeeService.getEmployeesByPage(page, perPage);
        List<EmployeeDTO> employeeDTOs = employees.stream()
                .map(EmployeeDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(employeeDTOs);
    }

}

