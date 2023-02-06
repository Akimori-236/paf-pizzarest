package nus.iss.tfip.pafpizzarest.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class Pizza {
    @NotNull(message = "Please select pizza type")
    @Pattern(regexp = "^(bella|margherita|marinara|spianatacalabrese|trioformaggio)$", message = "Pizza selection not available")
    private String pizza;

    @NotNull(message = "Please select pizza size")
    @Pattern(regexp = "^(sm|md|lg)$", message = "Pizza size not available")
    private String size;

    @NotNull(message = "Please enter number of pizzas")
    @Min(value = 1, message = "Minimum order is 1")
    @Max(value = 10, message = "Maximum order is 10")
    private Integer quantity;

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getPizza() {
        return pizza;
    }

    public void setPizza(String pizza) {
        this.pizza = pizza;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double cost() {
        Double cost = 0d;

        // type of pizza
        switch (this.getPizza()) {
            case "bella":
            case "marinara":
            case "spianatacalabrese":
                cost = 30d;
                break;
            case "margherita":
                cost = 22d;
                break;
            case "trioformaggio":
                cost = 25d;
                break;
            default:
                System.err.println("ERROR>>> Pizza type not valid");
        }

        switch (this.getSize()) {
            case "sm":
                break;
            case "md":
                cost *= 1.2;
                break;
            case "lg":
                cost *= 1.5;
                break;
            default:
                System.err.println("ERROR>>> Pizza size not valid");
        }
        return cost;
    }
}