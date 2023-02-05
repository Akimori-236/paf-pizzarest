package nus.iss.tfip.pafpizzarest.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private Integer order_id;
    private Integer pizza_id;
    private Integer size_id;
    private Integer quantity;
    private Integer customer_id;
    private Boolean rush;
    private String comments;
}
