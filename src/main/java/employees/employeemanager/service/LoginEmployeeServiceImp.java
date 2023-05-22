package employees.employeemanager.service;

import employees.employeemanager.service.passwordService.PasswordService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class LoginEmployeeServiceImp implements LoginService {

    private final EmployeeService employeeService;

    private final PasswordService passwordService;
    @Override
    public boolean login(String username, String password) {
        return employeeService.getEmployeeByUsername(username)
                .map(employee -> passwordService
                        .checkPassword(
                                password, employee.getLoginDetails().getPassword())
                )
                .orElse(false);
    }
}
