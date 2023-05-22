package employees.employeemanager.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee extends Person {

    @Enumerated(EnumType.STRING)
    private Department department;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "login_id")
    private LoginDetails loginDetails;
    @Builder(builderMethodName = "employeeBuilder")
    public Employee(Long personId, Date birthday, int age, String phoneNumber, List<Address> addresses, Name name, Department department, LoginDetails loginDetails) {
        super(personId, birthday, age, phoneNumber, addresses, name);
        this.department = department;
        this.loginDetails = loginDetails;
    }
}


