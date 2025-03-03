package org.example.Amazon;

import org.example.Amazon.Cost.ItemType;
import org.example.Amazon.Cost.PriceRule;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class AmazonTests {
    @Test
    @DisplayName("specification-based")
    void test1() {
        Database database = new Database();
        database.resetDatabase();
        ShoppingCart cart = new ShoppingCartAdaptor(database);

        List<PriceRule> rules = new ArrayList<>();
        rules.add(items -> items.stream().mapToDouble(item -> item.getQuantity() * item.getPricePerUnit()).sum());

        Amazon amazon = new Amazon(cart, rules);
        cart.add(new Item(ItemType.ELECTRONIC, "Item1", 1, 10));
        cart.add(new Item(ItemType.OTHER, "Item2", 2, 20));

        double expectedPrice = 50;
        assertEquals(expectedPrice, amazon.calculate());
    }

    @Test
    @DisplayName("specification-based")
    void test2() {
        Database database = new Database();
        database.resetDatabase();
        ShoppingCart cart = new ShoppingCartAdaptor(database);
        List<PriceRule> rules = new ArrayList<>();
        Amazon amazon = new Amazon(cart, rules);

        Item item = new Item(ItemType.OTHER, "Item1", 1, 10);
        amazon.addToCart(item);

        assertEquals(1, cart.getItems().size());
    }

    @Test
    @DisplayName("structural-based")
    void test3() {
        Database database = new Database();
        database.resetDatabase();
        ShoppingCart cart = new ShoppingCartAdaptor(database);
        List<PriceRule> rules = new ArrayList<>();
        rules.add(items -> items.stream().mapToDouble(item -> item.getQuantity() * item.getPricePerUnit()).sum());
        rules.add(items -> items.size());

        Amazon amazon = new Amazon(cart, rules);
        cart.add(new Item(ItemType.ELECTRONIC, "Item1", 1, 10));
        cart.add(new Item(ItemType.ELECTRONIC, "Item2", 2, 20));

        double expectedPrice = 52;
        assertEquals(expectedPrice, amazon.calculate());
    }

    @Test
    @DisplayName("structural-based")
    void test4() {
        Database database = new Database();
        database.resetDatabase();
        ShoppingCart cart = new ShoppingCartAdaptor(database);
        List<PriceRule> rules = new ArrayList<>();

        Amazon amazon = new Amazon(cart, rules);
        assertEquals(0, amazon.calculate());
    }
}
