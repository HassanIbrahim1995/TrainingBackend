package employees.employeemanager.service.passwordService;

import org.springframework.stereotype.Service;

@Service
public class pepperService {
    private final String PEPER;
    public pepperService() {
        this.PEPER = "$2a$10$EUENeKqb2i.1Ohb2hEKP6e";
    }
    public String getPaper(){
        return PEPER;
    }
}

