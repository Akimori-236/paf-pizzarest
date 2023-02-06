package nus.iss.tfip.pafpizzarest.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Size {
    private Integer size_id;

    @NotNull(message = "Please select pizza size")
    @Pattern(regexp = "^(sm|md|lg)$", message = "Pizza size not available")
    private String size;
    
    private Float multiplier;
}
