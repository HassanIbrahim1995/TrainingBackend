package employees.employeemanager.dto;

import employees.employeemanager.model.Department;
import employees.employeemanager.model.Employee;
import employees.employeemanager.model.Name;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployeeDTO {
    private Long employeeId;
    private String firstName;
    private String lastName;
    private Date birthday;
    private int age;
    private String phoneNumber;
    private String department;
    private List<AddressDTO> addresses;

    public EmployeeDTO(Employee employee) {
        this.birthday = employee.getBirthday();
        this.age = employee.getAge();
        this.phoneNumber = employee.getPhoneNumber();
        this.firstName = employee.getName().getFirstName();
        this.lastName = employee.getName().getLastName();
        this.department = employee.getDepartment().name();
        this.employeeId = employee.getPersonId();
        this.addresses = employee.getAddresses().stream()
                .map(AddressDTO::new)
                .toList();
    }
}


