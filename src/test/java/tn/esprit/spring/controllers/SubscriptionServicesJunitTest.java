package tn.esprit.spring.controllers;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.spring.entities.Subscription;
import tn.esprit.spring.entities.TypeSubscription;
import tn.esprit.spring.repositories.ISkierRepository;
import tn.esprit.spring.repositories.ISubscriptionRepository;
import tn.esprit.spring.services.SubscriptionServicesImpl;

import java.time.LocalDate;

@ExtendWith(MockitoExtension.class)
 class SubscriptionServicesJunitTest {

    @Mock
    private ISubscriptionRepository subscriptionRepository;

    @Mock
    private ISkierRepository skierRepository;

    @InjectMocks
    private SubscriptionServicesImpl subscriptionServices;

    // Sample subscription for use in tests
    private Subscription sampleSubscription;

    @BeforeEach
    void setUp() {
        sampleSubscription = new Subscription();
        sampleSubscription.setNumSub(1L);
        sampleSubscription.setStartDate(LocalDate.now());
        sampleSubscription.setEndDate(LocalDate.now().plusMonths(1));
        sampleSubscription.setPrice(100.0f);
        // Assuming TypeSubscription is an enum with a value MONTHLY
        sampleSubscription.setTypeSub(TypeSubscription.MONTHLY);
    }

    @Test
     void whenAddSubscription_thenSaveSubscription() {
        // Arrange
        when(subscriptionRepository.save(any(Subscription.class))).thenReturn(sampleSubscription);

        // Act
        Subscription savedSubscription = subscriptionServices.addSubscription(sampleSubscription);

        // Assert
        assertNotNull(savedSubscription);
        assertEquals(sampleSubscription.getNumSub(), savedSubscription.getNumSub());
    }

    // Test without mocks (integration test - not executable in this environment)
    @Test
     void whenRetrieveSubscriptionById_thenSubscriptionReturned() {
        // This is just a skeleton for an integration test, which we cannot run here.
        // It assumes that you have an actual database and have inserted a subscription with ID 1.
        Long subscriptionId = 1L;
        Subscription retrievedSubscription = subscriptionServices.retrieveSubscriptionById(subscriptionId);

        assertNotNull(retrievedSubscription);
        assertEquals(subscriptionId, retrievedSubscription.getNumSub());
    }
}
