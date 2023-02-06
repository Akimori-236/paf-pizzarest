package nus.iss.tfip.pafpizzarest.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pizza {
    private Integer pizza_id;

    @NotNull(message = "Please select pizza type")
    @Pattern(regexp = "^(bella|margherita|marinara|spianatacalabrese|trioformaggio)$", message = "Pizza selection not available")
    private String type;

    private Integer price;
}