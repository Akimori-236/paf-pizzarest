package nus.iss.tfip.pafpizzarest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import nus.iss.tfip.pafpizzarest.exception.PizzaException;
import nus.iss.tfip.pafpizzarest.model.Order;
import nus.iss.tfip.pafpizzarest.model.Pizza;
import nus.iss.tfip.pafpizzarest.service.PizzaService;
import nus.iss.tfip.pafpizzarest.util.PizzaUtil;

@Controller
@RequestMapping(path = "/pizza")
public class PizzaController {

    @Autowired
    private PizzaService pizzaSvc;

    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String pizzaForm(@Valid Pizza pizza, BindingResult binding, Model model, HttpSession session) {
        pizzaSvc.testConn();
        // IF INPUT ERROR
        if (binding.hasErrors()) {
            System.err.println(PizzaUtil.getValidationError(binding));
            model.addAttribute("pizza", pizza);
            return "index";
        }
        // store pizza
        session.setAttribute("pizza", pizza);
        System.out.println("NEW ORDER: %s x %s (%s)".formatted(pizza.getQuantity(), pizza.getPizza(), pizza.getSize()));

        model.addAttribute("order", new Order());
        return "deliverydetails";
    }

    @PostMapping(path = "/order", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String detailsForm(@Valid Order order, BindingResult binding, Model model, HttpSession session) throws PizzaException {
        // IF INPUT ERROR
        if (binding.hasErrors()) {
            System.err.println(PizzaUtil.getValidationError(binding));
            model.addAttribute("order", order);
            return "deliverydetails";
        }
        // NO ERRORS
        Integer orderID = pizzaSvc.saveOrder(order, session);
        order.setId(orderID);
        model.addAttribute("order", order);
        return "confirmation";
    }
}
