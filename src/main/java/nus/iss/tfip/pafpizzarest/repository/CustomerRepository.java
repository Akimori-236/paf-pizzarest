package nus.iss.tfip.pafpizzarest.repository;

import java.sql.PreparedStatement;
import java.sql.Statement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import nus.iss.tfip.pafpizzarest.model.Order;

@Repository
public class CustomerRepository {

    @Autowired
    private JdbcTemplate template;

    public Integer insertCustomer(Order order) {
        KeyHolder holder = new GeneratedKeyHolder();

        template.update((PreparedStatementCreator) con -> {
            PreparedStatement ps = con.prepareStatement(Queries.SQLinsertCustomer, Statement.RETURN_GENERATED_KEYS);
            // (`name`,`address`,`phone`)
            ps.setString(1, order.getName());
            ps.setString(2, order.getAddress());
            ps.setString(3, order.getPhone());
            return ps;
        }, holder);

        // comes back as BigInteger, change to int
        return holder.getKey().intValue();
    }
}
