package employees.employeemanager.controller;

import employees.employeemanager.dto.EmployeeDTO;
import employees.employeemanager.model.Employee;
import employees.employeemanager.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public ResponseEntity<EmployeeDTO> createEmployee(@RequestBody Employee employee) {
        Employee createdEmployee = employeeService.createEmployee(employee);
        EmployeeDTO createdEmployeeDTO = new EmployeeDTO(createdEmployee);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdEmployeeDTO);
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

