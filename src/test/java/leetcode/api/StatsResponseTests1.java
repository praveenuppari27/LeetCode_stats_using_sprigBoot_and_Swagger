package leetcode.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import leetcode.api.model.StatsResponse;
import leetcode.api.model.StatsResponse1;

public class StatsResponseTests1 {

    StatsResponse1 s = new StatsResponse1("success", "retrieved", 1, 2, 3, (float) 99.99, 10);
    @Test
    void statusCorrect() {
        assertEquals("success", s.getStatus());
    }

    @Test
    void messageCorrect() {
        assertEquals("retrieved", s.getMessage());
    }
    
    @Test
    void totalgetAttendedContestsCount() {
        assertEquals(1, s.getAttendedContestsCount());
    }
    
    @Test
    void totalrating() {
        assertEquals(2, s.getRating());
    }

    @Test
    void totalgetGlobalRanking() {
        assertEquals(3,s.getGlobalRanking());
    }

    @Test
    void totalgetTopPercentage() {
        assertEquals((float) 99.99, s.getTopPercentage());
    }

    @Test
    void getbadgeCountCorrect() {
        assertEquals(10, s.getbadgeCount());
    }
    

    @Test
    void sameRefEqualCorrect() {
        assertEquals(s, s);
    }


    @Test
    void sameValEqualCorrect() {
        StatsResponse1 copy = new StatsResponse1("success", "retrieved", 1, 2, 3, 4, 5);
        assertTrue(s.equals(copy));
    }
}
