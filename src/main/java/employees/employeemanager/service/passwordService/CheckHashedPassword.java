package employees.employeemanager.service.passwordService;

@FunctionalInterface
public interface CheckHashedPassword {
    boolean checkPassword(String originalPassword,String hashedPassword);
}

