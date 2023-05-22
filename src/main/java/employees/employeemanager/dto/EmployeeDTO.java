package employees.employeemanager.dto;

import employees.employeemanager.model.Address;
import employees.employeemanager.model.Department;
import employees.employeemanager.model.Employee;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class EmployeeDTO {
    private Long employeeId;
    private String firstName;
    private String lastName;
    private Date birthday;
    private int age;
    private String phoneNumber;
    private String department;
    private String username;
    private String password;
    private List<AddressDTO> addresses;

    public EmployeeDTO(Employee employee) {
        this.birthday = employee.getBirthday();
        this.age = employee.getAge();
        this.phoneNumber = employee.getPhoneNumber();
        this.firstName = employee.getName().getFirstName();
        this.lastName = employee.getName().getLastName();
        this.department = employee.getDepartment().name();
        this.employeeId = employee.getPersonId();
        this.username = employee.getLoginDetails().getUsername();
        this.addresses = employee.getAddresses().stream()
                .map(AddressDTO::new)
                .toList();
    }

    public Employee mapToEmployee() {
        Employee employee = new Employee();
        employee.getName().setFirstName(this.firstName);
        employee.getName().setLastName(this.lastName);
        employee.setBirthday(this.birthday);
        employee.setAge(this.age);
        employee.setPhoneNumber(this.phoneNumber);
        employee.setDepartment(Department.valueOf(this.department));
        employee.getLoginDetails().setUsername(this.username);
        employee.getLoginDetails().setPassword(this.password);

        List<Address> addresses = new ArrayList<>();
        for (AddressDTO addressDTO : this.addresses) {
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
}
