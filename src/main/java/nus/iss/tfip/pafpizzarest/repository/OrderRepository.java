package nus.iss.tfip.pafpizzarest.repository;

import java.sql.PreparedStatement;
import java.sql.Statement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import nus.iss.tfip.pafpizzarest.model.Order;

@Repository
public class OrderRepository {

    @Autowired
    private JdbcTemplate template;

    public void testConnection() {
        System.out.println(template.queryForList("SELECT * FROM `pizza`"));
    }

    public Integer insertOrder(Order order, Integer customerId) {
        KeyHolder holder = new GeneratedKeyHolder();

        template.update((PreparedStatementCreator) con -> {
            PreparedStatement ps = con.prepareStatement(Queries.SQLinsertOrder, Statement.RETURN_GENERATED_KEYS);
            // (`pizza_id`,`size_id`,`quantity`,`customer_id`,`isRush`,`comments`)
            ps.setString(1, order.getPizza().getPizza());
            ps.setString(2, order.getPizza().getSize());
            ps.setInt(3, order.getPizza().getQuantity());
            ps.setInt(4, customerId);
            ps.setBoolean(5, order.isRush());
            ps.setString(6, order.getComments());
            return ps;
        }, holder);

        // comes back as BigInteger, change to int
        return holder.getKey().intValue();
    }
}
