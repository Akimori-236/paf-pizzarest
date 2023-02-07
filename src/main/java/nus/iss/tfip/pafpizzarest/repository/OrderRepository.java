package nus.iss.tfip.pafpizzarest.repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import nus.iss.tfip.pafpizzarest.exception.PizzaException;
import nus.iss.tfip.pafpizzarest.model.Confirmation;
import nus.iss.tfip.pafpizzarest.model.Order;

@Repository
public class OrderRepository {

    @Autowired
    private JdbcTemplate template;

    public void testConnection() {
        String response = template.queryForList("SELECT * FROM `pizza`").toString();
        System.out.println("TEST CONNECTION > " + response);
    }

    public Integer insertOrder(Order order, Integer customerId) throws PizzaException {
        KeyHolder holder = new GeneratedKeyHolder();

        template.update((PreparedStatementCreator) con -> {
            PreparedStatement ps = con.prepareStatement(Queries.SQLinsertOrder, Statement.RETURN_GENERATED_KEYS);
            // (`pizza_id`,`size_id`,`quantity`,`customer_id`,`rush`,`comments`)
            ps.setString(1, order.getPizza().getPizza());
            ps.setString(2, order.getPizza().getSize());
            ps.setInt(3, order.getPizza().getQuantity());
            ps.setInt(4, customerId);
            ps.setBoolean(5, order.isRush());
            ps.setString(6, order.getComments());
            return ps;
        }, holder);

        // comes back as BigInteger, change to int
        Number orderID = holder.getKey();
        if (orderID == null) {
            throw new PizzaException("ERROR >>> orderID coming back as null");
        }
        return orderID.intValue();
    }

    public Confirmation getConfirmation(Integer orderId) {
        Confirmation conf = template.queryForObject(Queries.SQLgetConfirmation, BeanPropertyRowMapper.newInstance(Confirmation.class), orderId);
        return conf;
    }


    public Map<String, Object> getJSON(Integer orderId) {
        Map<String, Object> response = template.queryForMap(Queries.SQLgetJSON, new Object[] { orderId });
        return response;
    }

}
