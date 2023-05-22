package employees.employeemanager.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table
@ToString
public class Address {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "address_id")
    private Long addressId;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Employee employee;

    @Column
    private String streetName;
    @Column
    private String houseNumber;
    @Column
    private String zipCode;
    @Column
    private String city;
    @Column
    private String country;

    public Address(String streetName, String houseNumber, String zipCode, String city, String country) {
        this.streetName = streetName;
        this.houseNumber = houseNumber;
        this.zipCode = zipCode;
        this.city = city;
        this.country = country;
    }

}
