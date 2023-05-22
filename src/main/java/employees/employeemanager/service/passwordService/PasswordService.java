package employees.employeemanager.service.passwordService;

import employees.employeemanager.utility.hashHelper;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PasswordService implements HashPassword, CheckHashedPassword {
    pepperService paperService;
    @Autowired
    public PasswordService(pepperService paperService) {
        this.paperService = paperService;
    }

    @Override
    public String hashPassword(String password){
        hashHelper hashHelper = new hashHelper();
        return hashHelper.hashPassword(paperService.getPaper() + password);
    }
    @Override
    public boolean checkPassword(String originalPassword,String hashedPassword){
        return BCrypt.checkpw((paperService.getPaper() + originalPassword),hashedPassword);
    }
}

