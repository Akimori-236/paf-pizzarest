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

        String SQLgetJSON = """
                        SELECT o.order_id AS orderId, c.name, c.address, c.phone, (o.rush = 1) AS rush, o.comments, p.type AS pizza, s.size, o.quantity,
                        IF(o.rush=1, (round((p.price * s.multiplier * o.quantity), 2) + 2), round((p.price * s.multiplier * o.quantity), 2)) AS total
                        FROM `order` o
                        INNER JOIN `pizza` p
                        ON o.pizza_id=p.pizza_id
                        INNER JOIN `size` s
                        ON o.size_id=s.size_id
                        INNER JOIN `customer` c
                        ON o.customer_id=c.customer_id
                        WHERE o.order_id=?;
                        """;

        String SQLgetConfirmation = """
                        SELECT o.order_id AS orderId, c.address, o.rush,
                        p.price * s.multiplier * o.quantity as cost,
                        IF(o.rush=1, (round((p.price * s.multiplier * o.quantity), 2) + 2), round((p.price * s.multiplier * o.quantity), 2)) AS total
                        FROM `order` o
                        INNER JOIN `pizza` p
                        ON o.pizza_id=p.pizza_id
                        INNER JOIN `size` s
                        ON o.size_id=s.size_id
                        INNER JOIN `customer` c
                        ON o.customer_id=c.customer_id
                        WHERE o.order_id=?;
                        """;
}
