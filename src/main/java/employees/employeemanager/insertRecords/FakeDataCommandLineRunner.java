package employees.employeemanager.insertRecords;

import com.github.javafaker.Faker;
import employees.employeemanager.model.*;
import employees.employeemanager.repository.EmployeeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class FakeDataCommandLineRunner implements CommandLineRunner {

    private final EmployeeRepository employeeRepository;

    public FakeDataCommandLineRunner(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void run(String... args) {
        Faker faker = new Faker(new Locale("nl"));
        Random random = new Random();

        List<Employee> employees = IntStream.range(0, 30)
                .mapToObj(i -> {
                    Employee employee = Employee.employeeBuilder().build();

                    String username = faker.name().username();
                    String password = faker.internet().password();

                    LoginDetails loginDetails = new LoginDetails();
                    loginDetails.setUsername(username);
                    loginDetails.setPassword(password);
                    loginDetails.setEmployee(employee);
                    employee.setLoginDetails(loginDetails);
                    Name name = new Name();
                    name.setFirstName(faker.name().firstName());
                    name.setLastName(faker.name().lastName());

                    employee.setBirthday(faker.date().birthday(18, 65));
                    employee.setAge(random.nextInt(60) + 18);
                    employee.setPhoneNumber(faker.phoneNumber().cellPhone());
                    employee.setDepartment(Department.values()[random.nextInt(Department.values().length)]);
                    employee.setName(name);

                    List<Address> addresses = IntStream.range(0, 2)
                            .mapToObj(j -> {
                                Address address = new Address();
                                address.setStreetName(faker.address().streetName());
                                address.setHouseNumber(faker.address().buildingNumber());
                                address.setZipCode(faker.address().zipCode());
                                address.setCity(faker.address().city());
                                address.setCountry("Netherlands");
                                address.setEmployee(employee);
                                return address;
                            })
                            .collect(Collectors.toList());

                    employee.setAddresses(addresses);
                    return employee;
                })
                .collect(Collectors.toList());

        employeeRepository.saveAll(employees);
    }
}

