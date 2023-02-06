package nus.iss.tfip.pafpizzarest.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class OrderRepository {

    @Autowired
    private JdbcTemplate template;


}
