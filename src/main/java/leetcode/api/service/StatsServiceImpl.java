package leetcode.api.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import leetcode.api.model.StatsResponse;
import leetcode.api.model.StatsResponse1;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

@Service
public class StatsServiceImpl implements StatsService {
    @Override
    public StatsResponse getStats(String username) {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");
        
        String query = String.format("{\"query\":\"query getUserProfile($username: String!) {allQuestionsCount { difficulty count } matchedUser(username: $username) { languageProblemCount { languageName problemsSolved } contributions { points } profile { reputation ranking } userCalendar { streak totalActiveDays } submissionCalendar submitStats { acSubmissionNum { difficulty count submissions } totalSubmissionNum { difficulty count submissions } } } } \",\"variables\":{\"username\":\"%s\"}}", username); 


        RequestBody body = RequestBody.create(mediaType, query);
        Request request = new Request.Builder()
                .url("https://leetcode.com/graphql/")
                .method("POST", body)
                .addHeader("referer", String.format("https://leetcode.com/%s/", username))
                .addHeader("Content-Type", "application/json")
                .build();
        try {
            Response response = client.newCall(request).execute();
            System.out.println("test" + response);
            
            String responseString = response.body().string();
            JSONObject jsonObject = new JSONObject(responseString);
            
            if (response.isSuccessful()) {
                if (jsonObject.has("errors")) {
                    return StatsResponse.error("error", "user does not exist");
                } else {
                    return decodeGraphqlJson(jsonObject);
                }
            } else {
                return StatsResponse.error("error", jsonObject.getString("error"));
            }
        } catch (IOException | JSONException ex) {
            return StatsResponse.error("error", ex.getMessage());
        }

    }
    
    private StatsResponse decodeGraphqlJson(JSONObject json) {
        int totalSolved = 0;
        int totalQuestions = 0;
        int easySolved = 0;
        int totalEasy = 0;
        int mediumSolved = 0;
        int totalMedium = 0;
        int hardSolved = 0;
        int totalHard = 0;
        float acceptanceRate = 0;
        int ranking = 0;
        int contributionPoints = 0;
        int reputation = 0;
        int streak=0;
        int totalActiveDays=0;
        int rating=0;
        int totalContests=0;
        int participatedContests=0;
        int highestContestRanking=0;

//        final Map<String, Integer> submissionCalendar = new TreeMap<>();
        final Map<String, Float> languageProblemCount = new HashMap<>();

        try {
            JSONObject data = json.getJSONObject("data");
            JSONArray allQuestions = data.getJSONArray("allQuestionsCount");
            JSONObject matchedUser = data.getJSONObject("matchedUser");
            JSONObject submitStats = matchedUser.getJSONObject("submitStats");
            JSONArray actualSubmissions = submitStats.getJSONArray("acSubmissionNum");
            JSONArray totalSubmissions = submitStats.getJSONArray("totalSubmissionNum");
            JSONObject userCalendar = matchedUser.getJSONObject("userCalendar");
            streak = userCalendar.getInt("streak");
            totalActiveDays=userCalendar.getInt("totalActiveDays");
//            JSONObject contests = -1;
            

            totalQuestions = allQuestions.getJSONObject(0).getInt("count");
            totalEasy = allQuestions.getJSONObject(1).getInt("count");
            totalMedium = allQuestions.getJSONObject(2).getInt("count");
            totalHard = allQuestions.getJSONObject(3).getInt("count");

            totalSolved = actualSubmissions.getJSONObject(0).getInt("count");
            easySolved = actualSubmissions.getJSONObject(1).getInt("count");
            mediumSolved = actualSubmissions.getJSONObject(2).getInt("count");
            hardSolved = actualSubmissions.getJSONObject(3).getInt("count");

            float totalAcceptCount = actualSubmissions.getJSONObject(0).getInt("submissions");
            float totalSubCount = totalSubmissions.getJSONObject(0).getInt("submissions");
            if (totalSubCount != 0) {
                acceptanceRate = round((totalAcceptCount / totalSubCount) * 100, 2);
            }

            contributionPoints = matchedUser.getJSONObject("contributions").getInt("points");
            reputation = matchedUser.getJSONObject("profile").getInt("reputation");
            ranking = matchedUser.getJSONObject("profile").getInt("ranking");
            
            rating = -1;
            totalContests = -1;
            participatedContests = -1;
            highestContestRanking = -1;
//            JSONArray languagesStats = [];
//       
//             System.out.println("matchedUser: " + matchedUser);

            JSONArray languageProblemCounts = matchedUser.getJSONArray("languageProblemCount");
            for (int i = 0; i < languageProblemCounts.length(); i++) {
                JSONObject languageData = languageProblemCounts.getJSONObject(i);
                String languageName = languageData.getString("languageName");
                int problemsSolved = languageData.getInt("problemsSolved");
                double percent = (problemsSolved / (float) totalSolved) * 100;
                languageProblemCount.put(languageName, (float) percent);
            }

//            final JSONObject submissionCalendarJson = new JSONObject(matchedUser.getString("submissionCalendar"));
//
//            for (String timeKey: submissionCalendarJson.keySet()) {
//                submissionCalendar.put(timeKey, submissionCalendarJson.getInt(timeKey));
//            }

        } catch (JSONException ex) {
            return StatsResponse.error("error", ex.getMessage());
        }

        return new StatsResponse("success", "retrieved", totalSolved, totalQuestions, easySolved, totalEasy, mediumSolved, totalMedium, hardSolved, totalHard, acceptanceRate, ranking, contributionPoints, reputation, totalContests, participatedContests, highestContestRanking, rating,streak,totalActiveDays,languageProblemCount);
    }

    private float round(float d, int decimalPlace) {
        BigDecimal bd = new BigDecimal(Float.toString(d));
        bd = bd.setScale(decimalPlace, RoundingMode.HALF_UP);
        return bd.floatValue();
    }
 
 
    @Override
    public StatsResponse1 getStatsothers(String username) {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        MediaType mediaType = MediaType.parse("application/json");

        // Define the GraphQL query to retrieve user contest ranking info
        String query = String.format(
            "{\"query\":\"query userContestRankingInfo($username: String!) { " +
            "userContestRanking(username: $username) { " +
            "attendedContestsCount " +
            "rating " +
            "globalRanking " +
            "totalParticipants " +
            "topPercentage " +
            "badge { " +
            "name " +
            "} " +
            "} " +
            "}\",\"variables\":{\"username\":\"%s\"}}",
            username);

        RequestBody body = RequestBody.create(mediaType, query);
        Request request = new Request.Builder()
                .url("https://leetcode.com/graphql/")
                .method("POST", body)
                .addHeader("referer", String.format("https://leetcode.com/%s/", username))
                .addHeader("Content-Type", "application/json")
                .build();

        try {
            Response response = client.newCall(request).execute();
            String responseString = response.body().string();
            JSONObject jsonObject = new JSONObject(responseString);

            if (response.isSuccessful()) {
                if (jsonObject.has("errors")) {
                    return StatsResponse1.error("error", "user does not exist");
                } else {
                    return decodeContestRankingJson(jsonObject);
                }
            } else {
                return StatsResponse1.error("error", jsonObject.getString("error"));
            }
        } catch (IOException | JSONException ex) {
            return StatsResponse1.error("error", ex.getMessage());
        }
    }
    private StatsResponse1 decodeContestRankingJson(JSONObject json) {
        try {
            JSONObject data = json.getJSONObject("data");
            JSONObject userContestRanking = data.getJSONObject("userContestRanking");

            int attendedContestsCount = userContestRanking.getInt("attendedContestsCount");
            int rating = userContestRanking.getInt("rating");
            int globalRanking = userContestRanking.getInt("globalRanking");
//            int totalParticipants = userContestRanking.getInt("totalParticipants");
            float topPercentage = (float) userContestRanking.getDouble("topPercentage");

            JSONObject badge = userContestRanking.optJSONObject("badge");
            Integer badgeCount = null;
            if (badge != null) {
                badgeCount = badge.optInt("count");
            }
            else badgeCount=0;

            return new StatsResponse1("success", "retrieved", attendedContestsCount, rating,
                                     globalRanking, topPercentage, badgeCount);
        } catch (JSONException ex) {
            return StatsResponse1.error("error", ex.getMessage());
        }
    }


}
