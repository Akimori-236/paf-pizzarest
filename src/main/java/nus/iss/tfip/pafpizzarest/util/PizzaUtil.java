package nus.iss.tfip.pafpizzarest.util;

import java.util.List;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class PizzaUtil {
    public static String notFoundJson(Integer orderId) {
        JsonObject jObj = Json.createObjectBuilder()
                .add("message", "Order %s not found".formatted(orderId))
                .build();
        return jObj.toString();
    }

    public static String getValidationError(BindingResult binding) {
        List<ObjectError> errors = binding.getAllErrors();
        return errors.get(0).getDefaultMessage().toString();
    }
}
