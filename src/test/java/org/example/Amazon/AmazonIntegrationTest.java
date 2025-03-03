package org.example.Amazon;

import org.example.Amazon.Cost.ItemType;
import org.example.Amazon.Cost.PriceRule;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class AmazonIntegrationTest {
    private static Database database;
    private static ShoppingCart cart;

    @BeforeAll
    static void setup() {
        database = new Database();
        cart = new ShoppingCartAdaptor(database);
    }

    @Test
    @DisplayName("integration-testing")
    void test1() {
        database.resetDatabase();
        List<PriceRule> rules = new ArrayList<>();
        rules.add(items -> items.stream().mapToDouble(item -> item.getQuantity() * item.getPricePerUnit()).sum());

        Amazon amazon = new Amazon(cart, rules);
        amazon.addToCart(new Item(ItemType.ELECTRONIC, "Item1", 1, 10));
        amazon.addToCart(new Item(ItemType.ELECTRONIC, "Item2", 2, 20));

        assertEquals(50, amazon.calculate());
    }

    @Test
    @DisplayName("integration-testing")
    void test2() {
        database.resetDatabase();
        Item item1 = new Item(ItemType.ELECTRONIC, "Item1", 1, 10);
        Item item2 = new Item(ItemType.OTHER, "Item2", 2, 20);

        cart.add(item1);
        cart.add(item2);

        assertEquals(2, cart.getItems().size());
    }
}
