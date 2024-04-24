package leetcode.api.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class StatsResponse1 {
    private final String status;
    private final String message;
    private final int attendedContestsCount;
    private final int globalRanking;
    private final int rating;
    private final float topPercentage;
    private final int badgeCount;

    public StatsResponse1(String status, String message,int attendedContestsCount, int rating, int globalRanking,float topPercentage,int badgeCount) {
        this.status = status;
        this.message = message;
        this.attendedContestsCount =attendedContestsCount;
        this.rating = rating;
        this.globalRanking = globalRanking;
        this.topPercentage = topPercentage;
        this.badgeCount = badgeCount;
    }

    public static StatsResponse1 error(String status, String message) {
        return new StatsResponse1(status, message, 0, 0, 0, 0, 0);
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public int getAttendedContestsCount() {
        return attendedContestsCount;
    }

    public int getRating() {
        return rating;
    }
    public int getGlobalRanking() {
        return globalRanking;
    }
    
    public float getTopPercentage() {
        return topPercentage;
    }
    
    public int getbadgeCount() {
        return badgeCount;
    }
    
    

    public boolean equals(StatsResponse1 s) {
        // Compared with itself
        if (s == this) {
            return true;
        }
        return status.equals(s.getStatus()) && message.equals(s.getMessage()) && 
        		attendedContestsCount == s.getAttendedContestsCount() && 
        		rating==s.getRating() &&
        		globalRanking==s.getGlobalRanking() &&
        		topPercentage==s.getTopPercentage() &&
        		badgeCount==s.getbadgeCount();
    }
}
