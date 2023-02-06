package nus.iss.tfip.pafpizzarest.repository;

public interface Queries {

    // i need IDno back from this to insert order
    String SQLinsertCustomer = """
            INSERT INTO `customer`
            (`name`,`address`,`phone`)
            VALUES(?,?,?);
                """;

    String SQLinsertOrder = """
            INSERT INTO `order`
            (`pizza_id`,`size_id`,`quantity`,`customer_id`,`rush`,`comments`)
            VALUES(
            (SELECT pizza_id FROM pizza WHERE type=?),
            (SELECT size_id FROM size WHERE size=?),
            ?,?,?,?);
                """;

    String SQLgetCost = """
            SELECT ROUND((s.multiplier * p.price * o.quantity), 2) AS price
            FROM `order` o
            INNER JOIN `pizza` p
            ON o.pizza_id = p.pizza_id
            INNER JOIN `size` s
            ON o.size_id = s.size_id
            WHERE o.order_id=?;
                """;
}
