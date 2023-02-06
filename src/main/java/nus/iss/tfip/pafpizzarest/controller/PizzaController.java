package nus.iss.tfip.pafpizzarest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import nus.iss.tfip.pafpizzarest.model.Order;
import nus.iss.tfip.pafpizzarest.model.Pizza;
import nus.iss.tfip.pafpizzarest.service.PizzaService;

@Controller
@RequestMapping(path = "/")
public class PizzaController {

    @Autowired
    private PizzaService pizzaSvc;

    @GetMapping
    public String showForm(Model model)  {
        model.addAttribute("order", new Order());
        return "index";
    }

    @PostMapping(path = "/pizza", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String pizzaForm(@Valid Pizza pizza, BindingResult binding, Model model, HttpSession session) {
        if (binding.hasErrors()) {
            System.err.println(binding.getAllErrors().get(0).getDefaultMessage().toString());
            model.addAttribute("pizza", pizza);
            return "index";
        }
        // store pizza
        session.setAttribute("pizza", pizza);
        // System.out.println("NEW ORDER: %s x %s (%s)".formatted(pizza.getQuantity(), pizza.getPizza(), pizza.getSize()));
        model.addAttribute("order", new Order());
        return "deliverydetails";
    }

    @PostMapping(path = "/pizza/order", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String detailsForm(@Valid Order order, BindingResult binding, Model model, HttpSession session) {
        if (binding.hasErrors()) {
            System.err.println(binding.getAllErrors().get(0).getDefaultMessage().toString());
            model.addAttribute("order", order);
            return "deliverydetails";
        }
        // pizzaSvc.saveOrder(order, session);

        model.addAttribute("order", order);
        return "confirmation";
    }
}
