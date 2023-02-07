package nus.iss.tfip.pafpizzarest.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.servlet.http.HttpSession;
import nus.iss.tfip.pafpizzarest.exception.PizzaException;
import nus.iss.tfip.pafpizzarest.model.Order;
import nus.iss.tfip.pafpizzarest.model.Pizza;
import nus.iss.tfip.pafpizzarest.repository.CustomerRepository;
import nus.iss.tfip.pafpizzarest.repository.OrderRepository;

import jakarta.json.Json;
import jakarta.json.JsonObjectBuilder;

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

    public void testConn() {
        orderRepo.testConnection();
    }

    @Transactional(rollbackFor = PizzaException.class)
    public String getJsonById(Integer orderId) {
        // get DB response
        Map<String, Object> response = orderRepo.getJSON(orderId);

        // build JSON String from DB response
        JsonObjectBuilder json = Json.createObjectBuilder()
                .add("orderId", response.get("orderId").toString())
                .add("name", response.get("name").toString())
                .add("address", response.get("address").toString())
                .add("phone", response.get("phone").toString())
                .add("rush", Boolean.parseBoolean(response.get("rush").toString()))
                .add("pizza", response.get("pizza").toString())
                .add("size", response.get("size").toString())
                .add("quantity", response.get("quantity").toString())
                .add("total", Float.parseFloat(response.get("total").toString()));

        // null checking for comments (its the only thing not NON NULL)
        if (null != response.get("comments")) {
            json.add("comments", response.get("comments").toString());
        } else {
            json.addNull("comments");
        }

        return json.build().toString();
    }

}
