package employees.employeemanager.service;

@FunctionalInterface
public interface LoginService{
    boolean login(String username, String password);
}
