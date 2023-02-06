package nus.iss.tfip.pafpizzarest.model;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import java.util.Random;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class Order {
    private Integer id;

    @Size(min = 3, message = "Name must be minimum 3 characters")
    private String name;

    @NotEmpty(message = "Address cannot be empty")
    private String address;

    @Size(min = 8, max = 8, message = "Phone number must be 8 digits")
    private String phone;

    private Boolean rush = false;

    private String comments;

    private Pizza pizza;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Boolean isRush() {
        return rush;
    }

    public void setRush(Boolean rush) {
        this.rush = rush;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Pizza getPizza() {
        return pizza;
    }

    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }

    public Double totalCost() {
        Double cost = this.getPizza().cost();
        if (this.isRush()) {
            return cost += 2;
        }
        return cost;
    }

    public JsonObject toJson() {
        return Json.createObjectBuilder()
                .add("orderId", this.getId())
                .add("name", this.getName())
                .add("address", this.getAddress())
                .add("phone", this.getPhone())
                .add("rush", this.isRush())
                .add("comments", this.getComments())
                .add("pizza", this.getPizza().getPizza())
                .add("size", this.getPizza().getSize())
                .add("quantity", this.getPizza().getQuantity())
                .add("total", this.totalCost())
                .build();
    }

    public String getJsonString() {
        JsonObject jObj = this.toJson();
        return jObj.toString();
    }

}
