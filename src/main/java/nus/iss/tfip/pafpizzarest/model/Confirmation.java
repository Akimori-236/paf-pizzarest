package nus.iss.tfip.pafpizzarest.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Confirmation {
    private Integer orderId;
    private String address;
    private Boolean rush;
    private Float cost;
    private Float total;
}
