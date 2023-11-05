package tn.esprit.spring;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tn.esprit.spring.entities.Subscription;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

 class SubscriptionTest {

    private Subscription subscription;

    @BeforeEach
    public void setUp() {
        subscription = new Subscription();
    }

    @Test
     void testSetAndGetPrice() {
        Float price = 99.99f;
        subscription.setPrice(price);
        assertEquals(price, subscription.getPrice());
    }

    @Test
     void testSetAndGetStartDate() {
        LocalDate startDate = LocalDate.now();
        subscription.setStartDate(startDate);
        assertEquals(startDate, subscription.getStartDate());
    }
}
