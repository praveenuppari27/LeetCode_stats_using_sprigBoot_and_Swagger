package leetcode.api.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class StatsResponse {
    private final String status;
    private final String message;
    private final int totalSolved;
    private final int totalQuestions;
    private final int easySolved;
    private final int totalEasy;
    private final int mediumSolved;
    private final int totalMedium;
    private final int hardSolved;
    private final int totalHard;
    private final float acceptanceRate;
    private final int ranking;
    private final int contributionPoints;
    private final int reputation;
    private final int rating;
    private final int totalContests;
    private final int participatedContests;
    private final int highestContestRanking;
    private final int streak;
    private final int totalActiveDays;
//    private final Map<String, Float> languagesPercentage;
    private final Map<String, Float> languageProblemCount;

    public StatsResponse(String status, String message,
    		int totalSolved, int totalQuestions, int easySolved, 
    		int totalEasy, int mediumSolved, int totalMedium, int hardSolved,
    		int totalHard, float acceptanceRate, int ranking, int contributionPoints,
    		int reputation,int rating,int totalContests,int participatedContests,
    		int highestContestRanking,int streak,int totalActiveDays,
    		Map<String, Float> languageProblemCount) {
        this.status = status;
        this.message = message;
        this.totalSolved = totalSolved;
        this.totalQuestions = totalQuestions;
        this.easySolved = easySolved;
        this.totalEasy = totalEasy;
        this.mediumSolved = mediumSolved;
        this.totalMedium = totalMedium;
        this.hardSolved = hardSolved;
        this.totalHard = totalHard;
        this.acceptanceRate = acceptanceRate;
        this.ranking = ranking;
        this.contributionPoints = contributionPoints;
        this.reputation = reputation;
        this.rating=rating;
        this.totalContests=totalContests;
        this.participatedContests=participatedContests;
        this.highestContestRanking=highestContestRanking;
//        this.languagesPercentage=languagesPercentage;
        this.streak=streak;
        this.totalActiveDays=totalActiveDays;
        this.languageProblemCount = languageProblemCount;
    }

    public static StatsResponse error(String status, String message) {
        return new StatsResponse(status, message, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,Collections.emptyMap());
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public int getTotalSolved() {
        return totalSolved;
    }

    public int getTotalQuestions() { return totalQuestions; }

    public int getEasySolved() {
        return easySolved;
    }

    public int getTotalEasy() {
        return totalEasy;
    }

    public int getMediumSolved() {
        return mediumSolved;
    }

    public int getTotalMedium() {
        return totalMedium;
    }

    public int getHardSolved() {
        return hardSolved;
    }

    public int getTotalHard() {
        return totalHard;
    }

    public float getAcceptanceRate() {
        return acceptanceRate;
    }

    public int getRanking() {
        return ranking;
    }

    public int getContributionPoints() {
        return contributionPoints;
    }

    public int getReputation() {
        return reputation;
    }


    public int getRating() {
        return rating;
    }
    public int getStreak() {
        return streak;
    }
    
    public int getTotalContests() {
        return totalContests;
    }
    
    public int getParticipatedContests() {
        return participatedContests;
    }
    
    public int getHighestContestRanking() {
        return highestContestRanking;
    }
    
    public int getTotalActiveDays() {
        return totalActiveDays;
    }
    
//    public Map<String, Float> getlanguagesPercentage() {
//        return languagesPercentage;
//    }
//    
    public Map<String, Float> getlanguageProblemCount() {
        return languageProblemCount;
    }
    
    

    public boolean equals(StatsResponse s) {
        // Compared with itself
        if (s == this) {
            return true;
        }
        return status.equals(s.getStatus()) && message.equals(s.getMessage()) && totalSolved == s.getTotalSolved() && totalQuestions == s.getTotalQuestions() && easySolved == s.getEasySolved() && totalEasy == s.getTotalEasy() && mediumSolved == s.getMediumSolved() && totalMedium == s.getTotalMedium() && hardSolved == s.getHardSolved() && totalHard == s.getTotalHard() && acceptanceRate == s.getAcceptanceRate() && ranking == s.getRanking() && contributionPoints == s.getContributionPoints() && 
        		reputation == s.getReputation() && rating==s.getRating() &&
        		totalContests==s.getTotalContests() &&
        		participatedContests==s.getParticipatedContests() &&
        		streak==s.getStreak() &&
        		highestContestRanking==s.getHighestContestRanking() &&
        		totalActiveDays==s.getTotalActiveDays();
    }
}
