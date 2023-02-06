package nus.iss.tfip.pafpizzarest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;
import nus.iss.tfip.pafpizzarest.model.Order;
import nus.iss.tfip.pafpizzarest.model.Pizza;
import nus.iss.tfip.pafpizzarest.repository.CustomerRepository;
import nus.iss.tfip.pafpizzarest.repository.OrderRepository;

@Service
public class PizzaService {

    @Autowired
    private OrderRepository orderRepo;
    @Autowired
    private CustomerRepository custRepo;

    public void saveOrder(Order order, HttpSession session) {
        Pizza pizza = (Pizza) session.getAttribute("pizza");
        order.setPizza(pizza);
        session.setAttribute("order", order);
        System.out.println("%s from %s ordered %s pizza".formatted(order.getName(), order.getAddress(),
                order.getPizza().getPizza()));
        // pizzaRepo.saveOrder(order);
    }

}
