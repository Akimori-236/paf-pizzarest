package nus.iss.tfip.pafpizzarest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.servlet.http.HttpSession;
import nus.iss.tfip.pafpizzarest.exception.PizzaException;
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

    @Transactional(rollbackFor = PizzaException.class)
    public Integer saveOrder(Order order, HttpSession session) throws PizzaException {
        System.out.println("*** TXN >>> START");
        // save pizza from session into order
        Pizza pizza = (Pizza) session.getAttribute("pizza");
        order.setPizza(pizza);

        session.setAttribute("order", order);
        System.out.println("%s from %s ordered %s pizza".formatted(order.getName(), order.getAddress(),
                order.getPizza().getPizza()));
        System.out.println("*** TXN >>> START Customer Insert");
        Integer custID = custRepo.insertCustomer(order);
        System.out.println("*** TXN >>> END Customer Insert");
        if (custID < 1) {
            throw new PizzaException("***MySQL > Error inserting customer");
        }
        System.out.println("*** TXN >>> START Order Insert");
        Integer orderID = orderRepo.insertOrder(order, custID);
        System.out.println("*** TXN >>> END Order Insert");
        return orderID;
    }

}
