package tn.esprit.spring;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.spring.entities.Subscription;
import tn.esprit.spring.entities.TypeSubscription;
import tn.esprit.spring.repositories.ISkierRepository;
import tn.esprit.spring.repositories.ISubscriptionRepository;
import tn.esprit.spring.services.SubscriptionServicesImpl;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import java.util.Optional;

 class SubscriptionServiceJunitTest {

    @Mock
    private ISubscriptionRepository subscriptionRepository;

    @Mock
    private ISkierRepository skierRepository;

    @InjectMocks
    private SubscriptionServicesImpl subscriptionService;

    @BeforeEach
    void init_mocks() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void whenAddAnnualSubscription_thenEndDateIsOneYearLater() {
        // Arrange
        LocalDate startDate = LocalDate.now();
        Subscription subscription = new Subscription();
        subscription.setStartDate(startDate);
        subscription.setTypeSub(TypeSubscription.ANNUAL);

        when(subscriptionRepository.save(any(Subscription.class))).thenAnswer(i -> i.getArguments()[0]);

        // Act
        Subscription savedSubscription = subscriptionService.addSubscription(subscription);

        // Assert
        assertNotNull(savedSubscription.getEndDate());
        assertEquals(startDate.plusYears(1), savedSubscription.getEndDate());
    }


    @Test
     void whenRetrieveSubscriptionById_thenCorrectSubscriptionIsReturned() {
        // Arrange
        Long subscriptionId = 1L;
        Subscription subscription = new Subscription();
        subscription.setNumSub(subscriptionId);
        when(subscriptionRepository.findById(subscriptionId)).thenReturn(Optional.of(subscription));

        // Act
        Subscription foundSubscription = subscriptionService.retrieveSubscriptionById(subscriptionId);

        // Assert
        assertNotNull(foundSubscription);
        assertEquals(subscriptionId, foundSubscription.getNumSub());
    }
}
