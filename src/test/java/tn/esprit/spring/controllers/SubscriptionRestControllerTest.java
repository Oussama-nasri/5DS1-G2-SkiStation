package tn.esprit.spring.controllers;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import tn.esprit.spring.entities.Subscription;
import tn.esprit.spring.entities.SubscriptionDTO;
import tn.esprit.spring.services.ISubscriptionServices;

import java.time.LocalDate;

@ExtendWith(MockitoExtension.class)
class SubscriptionRestControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ISubscriptionServices subscriptionServices;

    @InjectMocks
    private SubscriptionRestController subscriptionRestController;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(subscriptionRestController).build();
    }

    @Test
    void whenAddSubscription_thenReturnSubscription() throws Exception {
        SubscriptionDTO subscriptionDTO = new SubscriptionDTO();
        subscriptionDTO.setStartDate(LocalDate.now());
        subscriptionDTO.setEndDate(LocalDate.now().plusMonths(1));
        subscriptionDTO.setPrice(100f);

        Subscription subscription = new Subscription();
        subscription.setNumSub(1L);
        subscription.setStartDate(subscriptionDTO.getStartDate());
        subscription.setEndDate(subscriptionDTO.getEndDate());
        subscription.setPrice(subscriptionDTO.getPrice());

        when(subscriptionServices.addSubscription(any(Subscription.class))).thenReturn(subscription);

        mockMvc.perform(post("/subscription/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(String.format("{ \"startDate\": \"%s\", \"endDate\": \"%s\", \"price\": %s }", subscriptionDTO.getStartDate(), subscriptionDTO.getEndDate(), subscriptionDTO.getPrice())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.numSub").value(subscription.getNumSub()))
                .andExpect(jsonPath("$.price").value(subscription.getPrice()));
    }

    @Test
    void whenGetById_thenReturnSubscription() throws Exception {
        Long numSubscription = 1L;
        Subscription subscription = new Subscription();
        subscription.setNumSub(numSubscription);
        subscription.setStartDate(LocalDate.now());
        subscription.setEndDate(LocalDate.now().plusMonths(1));
        subscription.setPrice(100f);

        when(subscriptionServices.retrieveSubscriptionById(numSubscription)).thenReturn(subscription);

        mockMvc.perform(get("/subscription/get/{id-subscription}", numSubscription))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.numSub").value(subscription.getNumSub()))
                .andExpect(jsonPath("$.price").value(subscription.getPrice()));
    }

}