package nus.iss.tfip.pafpizzarest.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private Integer order_id;
    private Pizza pizza;
    private Size size;

    @NotNull(message = "Please enter number of pizzas")
    @Min(value = 1, message = "Minimum order is 1")
    @Max(value = 10, message = "Maximum order is 10")
    private Integer quantity;

    private Customer customer;
    private Boolean rush;
    private String comments;
    private Float totalCost = null;

    private Float getTotal() {
        if (this.totalCost != null ){
            return this.totalCost;
        } else {
            this.totalCost = pizza.getPrice() * size.getMultiplier();
            if (this.rush) {
                this.totalCost += 2;
            }
            return this.totalCost;
        }
    }
}
