package nus.iss.tfip.pafpizzarest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nus.iss.tfip.pafpizzarest.service.PizzaService;
import nus.iss.tfip.pafpizzarest.util.PizzaUtil;

@RestController
@RequestMapping(path = "/api/order")
public class PizzaRestController {

    @Autowired
    private PizzaService pizzaSvc;

    @GetMapping(path = "{orderId}")
    public ResponseEntity<String> getOrder(@PathVariable Integer orderId) {
        String jsonStr = pizzaSvc.getJsonById(orderId);
        if (jsonStr == "" || jsonStr == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(PizzaUtil.notFoundJson(orderId));
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(jsonStr);
    }
}

