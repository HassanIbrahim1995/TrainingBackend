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

    @Builder
    public Employee(Long personId, Date birthday, int age, String phoneNumber, List<Address> addresses, Name name, Department department) {
        super(personId, birthday, age, phoneNumber, addresses, name);
        this.department = department;
    }


}


