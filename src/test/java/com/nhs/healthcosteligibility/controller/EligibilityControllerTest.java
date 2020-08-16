package com.nhs.healthcosteligibility.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(SpringRunner.class)
@WebMvcTest(EligibilityController.class)
public class EligibilityControllerTest {
    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders
                .standaloneSetup(new EligibilityController()).build();
    }

    @Test
    public void given_get_checkEligibility_then_view_eligibility()
            throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                .get("/checkEligibility"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.view().name("eligibility"));
    }


    @Test
    public void given_post_checkEligibility_when_valid_frequency_amount_then_success()
            throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                .post("/checkEligibility")
                .accept(MediaType.TEXT_HTML).param("frequency", "TWO_WEEK")
                .param("amount", "22.00"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.view().name("success"));
    }

    @Test
    public void given_post_checkEligibility_when_valid_frequency_and_null_amount_then_failure()
            throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                .post("/checkEligibility")
                .accept(MediaType.TEXT_HTML)
                .param("frequency", "TWO_WEEK"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.view().name("failure"));
    }

    @Test
    public void given_post_checkEligibility_when_Null_frequency_and_valid_amount_then_failure()
            throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                .post("/checkEligibility")
                .accept(MediaType.TEXT_HTML)
                .param("amount", "22"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.view().name("failure"));
    }

    @Test
    public void given_post_checkEligibility_when_invalid_frequency_amount_then_show_error()
            throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                .post("/checkEligibility")
                .accept(MediaType.TEXT_HTML).param("frequency", "TWO_WEEK")
                .param("amount", "22.77"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.view().name("redirect:" + EligibilityController.PATH));
    }
}