package employees.employeemanager.utility;

import org.mindrot.jbcrypt.BCrypt;

public class hashHelper {
    public String hashPassword(String password){
        final int DEFAULT_SALT_ROUNDS = 13;
        return BCrypt.hashpw(password, BCrypt.gensalt(DEFAULT_SALT_ROUNDS));
    }
}
