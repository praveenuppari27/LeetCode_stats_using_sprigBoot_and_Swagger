package leetcode.api;

import leetcode.api.controller.UserController;
import leetcode.api.model.StatsResponse1;
import leetcode.api.service.StatsService;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import org.springframework.test.web.servlet.MvcResult;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;

@WebMvcTest(UserController.class)
public class UserControllerTests1 {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StatsService service;


    @Test
    void validUsername() throws Exception {
        StatsResponse1 mockResponse = new StatsResponse1("success", "retrieved", 1, 2, 3, (float) 99.99, 10);
        when(service.getStatsothers("user_exists")).thenReturn(mockResponse);

        MvcResult result = mockMvc.perform(get("/user_exists")).andExpect(status().isOk()).andReturn();
        String resultStr = result.getResponse().getContentAsString();
        String expected = "{\"status\":\"success\",\"message\":\"retrieved\",\"attendedContestsCount\":1,\"rating\":2,\"globalRanking\":3,\"topPercentage\":99.99,\"badgeCount\":10}";
        assertEquals(resultStr, expected);
    }

    @Test
    void noUsername() throws Exception {
        MvcResult result = mockMvc.perform(get("/")).andExpect(status().isOk()).andReturn();
        String resultStr = result.getResponse().getContentAsString();
        String expected = "{\"status\":\"error\",\"message\":\"please enter your username (ex: leetcode-stats-api.herokuapp.com/LeetCodeUsername)\",\"attendedContestsCount\":0,\"rating\":0,\"globalRanking\":0,\"topPercentage\":0.0,\"badgeCount\":0}";
        assertEquals(resultStr, expected);
    }

    @Test
    void nonValidUsername() throws Exception {
        StatsResponse1 mockResponse = StatsResponse1.error("error", "user does not exist");
        when(service.getStatsothers("user_does_not_exist")).thenReturn(mockResponse);

        MvcResult result = mockMvc.perform(get("/user_does_not_exist")).andExpect(status().isOk()).andReturn();
        String resultStr = result.getResponse().getContentAsString();
        String expected = "{\"status\":\"error\",\"message\":\"please enter your username (ex: leetcode-stats-api.herokuapp.com/LeetCodeUsername)\",\"attendedContestsCount\":0,\"rating\":0,\"globalRanking\":0,\"topPercentage\":0.0,\"badgeCount\":0}";
        assertEquals(resultStr, expected);
    }
}
