package nus.iss.tfip.pafpizzarest.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pizza {
    private Integer pizza_id;
    private String type;
    private Integer price;
}