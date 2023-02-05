package nus.iss.tfip.pafpizzarest.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    private Integer customer_id;
    private String name;
    private String address;
    private String phone;
}
