package employees.employeemanager.service.passwordService;

@FunctionalInterface
public interface HashPassword {
    String hashPassword(String password);
}

