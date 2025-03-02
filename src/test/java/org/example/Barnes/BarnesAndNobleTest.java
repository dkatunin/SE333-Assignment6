package org.example.Barnes;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BarnesAndNobleTest {

    @Test
    @DisplayName("Specification-Based")
    void test1() {
        BookDatabase mockBookDatabase = mock(BookDatabase.class);
        BuyBookProcess mockBuyBookProcess = mock(BuyBookProcess.class);
        BarnesAndNoble barnesAndNoble = new BarnesAndNoble(mockBookDatabase, mockBuyBookProcess);

        Book book = new Book("12345", 10, 5);
        when(mockBookDatabase.findByISBN("12345")).thenReturn(book);

        Map<String, Integer> order = new HashMap<>();
        order.put("12345", 2);
        PurchaseSummary purchaseSummary = barnesAndNoble.getPriceForCart(order);

        assertNotNull(purchaseSummary);
        assertEquals(20.0, purchaseSummary.getTotalPrice());
    }

    @Test
    @DisplayName("Specification-Based")
    void test2() {
        BookDatabase mockBookDatabase = mock(BookDatabase.class);
        BuyBookProcess mockBuyBookProcess = mock(BuyBookProcess.class);
        BarnesAndNoble barnesAndNoble = new BarnesAndNoble(mockBookDatabase, mockBuyBookProcess);

        Book book = new Book("12345", 20, 1);
        when(mockBookDatabase.findByISBN("12345")).thenReturn(book);
        Map<String, Integer> order = new HashMap<>();
        order.put("12345", 3);
        PurchaseSummary purchaseSummary = barnesAndNoble.getPriceForCart(order);

        assertEquals(20.0, purchaseSummary.getTotalPrice());
    }

    @Test
    @DisplayName("Specification-Based")
    void test3() {
        BookDatabase mockBookDatabase = mock(BookDatabase.class);
        BuyBookProcess mockBuyBookProcess = mock(BuyBookProcess.class);
        BarnesAndNoble barnesAndNoble = new BarnesAndNoble(mockBookDatabase, mockBuyBookProcess);

        Map<String, Integer> order = Collections.emptyMap();
        PurchaseSummary purchaseSummary = barnesAndNoble.getPriceForCart(order);

        assertEquals(0.0, purchaseSummary.getTotalPrice());
    }

    @Test
    @DisplayName("Specification-Based")
    void test4() {
        BookDatabase mockBookDatabase = mock(BookDatabase.class);
        BuyBookProcess mockBuyBookProcess = mock(BuyBookProcess.class);
        BarnesAndNoble barnesAndNoble = new BarnesAndNoble(mockBookDatabase, mockBuyBookProcess);
        PurchaseSummary purchaseSummary = barnesAndNoble.getPriceForCart(null);

        assertNull(purchaseSummary);
    }

    @Test
    @DisplayName("Structural-Based")
    void test5() {
        BookDatabase mockBookDatabase = mock(BookDatabase.class);
        BuyBookProcess mockBuyBookProcess = mock(BuyBookProcess.class);
        BarnesAndNoble barnesAndNoble = new BarnesAndNoble(mockBookDatabase, mockBuyBookProcess);
        when(mockBookDatabase.findByISBN("99999")).thenReturn(null);

        Map<String, Integer> order = new HashMap<>();
        order.put("99999", 1);

        Exception exception = assertThrows(NullPointerException.class, () -> barnesAndNoble.getPriceForCart(order));
        assertNotNull(exception);
    }

    @Test
    @DisplayName("Structural-Based")
    void test6() {
        BookDatabase mockBookDatabase = mock(BookDatabase.class);
        BuyBookProcess mockBuyBookProcess = mock(BuyBookProcess.class);
        BarnesAndNoble barnesAndNoble = new BarnesAndNoble(mockBookDatabase, mockBuyBookProcess);

        Book book = new Book("12345", 50, 0);
        when(mockBookDatabase.findByISBN("12345")).thenReturn(book);
        Map<String, Integer> order = new HashMap<>();
        order.put("12345", 2);
        PurchaseSummary purchaseSummary = barnesAndNoble.getPriceForCart(order);

        assertEquals(0.0, purchaseSummary.getTotalPrice());
    }

    @Test
    @DisplayName("Structural-Based")
    void test7() {
        Book book1 = new Book("12345", 40, 5);
        Book book2 = new Book("12345", 30, 2);
        Book book3 = new Book("67890", 40, 5);

        assertEquals(book1, book2);
        assertNotEquals(book1, book3);
    }
}
