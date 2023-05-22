package employees.employeemanager.insertRecords;

import employees.employeemanager.repository.AddressRepository;
import employees.employeemanager.repository.EmployeeRepository;
import employees.employeemanager.model.Address;
import employees.employeemanager.model.Department;
import employees.employeemanager.model.Employee;
import employees.employeemanager.model.Name;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

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

        for (int i = 0; i < 15; i++) {
            Employee employee = new Employee();

            Name name = new Name();
            name.setFirstName(faker.name().firstName());
            name.setLastName(faker.name().lastName());

            employee.setBirthday(faker.date().birthday(18, 65));
            employee.setAge(random.nextInt(60) + 18);
            employee.setPhoneNumber(faker.phoneNumber().cellPhone());
            employee.setDepartment(Department.values()[random.nextInt(Department.values().length)]);
            employee.setName(name);

            List<Address> addresses = new ArrayList<>();
            for (int j = 0; j < 2; j++) {
                Address address = new Address();
                address.setStreetName(faker.address().streetName());
                address.setHouseNumber(faker.address().buildingNumber());
                address.setZipCode(faker.address().zipCode());
                address.setCity(faker.address().city());
                address.setCountry("Netherlands");
                address.setEmployee(employee); // Set the person associated with the address

                addresses.add(address);
            }
            employee.setAddresses(addresses);

            employeeRepository.save(employee);
        }
    }



}

