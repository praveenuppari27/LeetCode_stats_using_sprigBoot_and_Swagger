package leetcode.api.service;

import leetcode.api.model.StatsResponse;
import leetcode.api.model.StatsResponse1;

public interface StatsService {
    StatsResponse getStats(String username);
	StatsResponse1 getStatsothers(String username);
}
