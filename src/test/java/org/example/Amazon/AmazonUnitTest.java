package org.example.Amazon;

import org.example.Amazon.Cost.ItemType;
import org.example.Amazon.Cost.PriceRule;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AmazonUnitTest {

    @Test
    @DisplayName("unit-testing")
    void test1() {
        ShoppingCart mockCart = mock(ShoppingCart.class);
        Amazon mockAmazon = new Amazon(mockCart, new ArrayList<>());
        Item mockItem = new Item(ItemType.ELECTRONIC, "Item1", 1, 10);

        mockAmazon.addToCart(mockItem);
        verify(mockCart, times(1)).add(mockItem);
    }

    @Test
    @DisplayName("unit-testing")
    void test2() {
        ShoppingCart mockCart = mock(ShoppingCart.class);
        List<Item> mockItems = List.of(new Item(ItemType.ELECTRONIC, "Item1", 2, 10));
        when(mockCart.getItems()).thenReturn(mockItems);

        PriceRule mockRule = items -> items.stream().mapToDouble(item -> item.getQuantity() * item.getPricePerUnit()).sum();
        List<PriceRule> mockRules = List.of(mockRule);

        Amazon amazon = new Amazon(mockCart, mockRules);
        assertEquals(20, amazon.calculate());
    }
}
