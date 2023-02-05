package nus.iss.tfip.pafpizzarest.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Size {
    private Integer size_id;
    private String size;
    private Float multiplier;
}
