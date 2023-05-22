package employees.employeemanager.dto;

import employees.employeemanager.model.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO {
    private String streetName;
    private String houseNumber;
    private String zipCode;
    private String city;
    private String country;

    public AddressDTO(Address address) {
        this.streetName = address.getStreetName();
        this.houseNumber = address.getHouseNumber();
        this.zipCode = address.getZipCode();
        this.city = address.getCity();
        this.country = address.getCountry();
    }
}
