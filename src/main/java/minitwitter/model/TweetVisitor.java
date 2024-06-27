package minitwitter.model;
import java.util.*;

/**
 * Visitor that collects analytics of MiniTwitter tweets
 * @author Anh Hoang
 */
public class TweetVisitor implements TwitterVisitor {
    private int tweetCount;                 // Number of tweets
    private int positiveTweetCount;         // Number of tweets with positive keywords
    private Set<String> positiveKeywords;   // List of positive keywords

    /** Default constructor */
    public TweetVisitor() {
        tweetCount = 0;     // Number of tweets 
        positiveTweetCount = 0; // Number of tweets judged as positive
        // List of keywords to identify positive tweets
        positiveKeywords = new HashSet<>(Arrays.asList("good",
                            "great", "perfect", "awesome", "happy",
                            "amazing", "excellent", "wonderful",
                            "nice", "love", "i like"));
    }

    /**
     * Get for tweet count.
     * @return  Number of tweets counted by the visitor
     */
    public int getTweetCount() { return tweetCount; }

    /**
     * Get positive tweet count.
     * @return  Number of tweets with positive keywords counted by the visitor
     */
    public int getPositiveTweetCount() { return positiveTweetCount; }
    
    /**
     * Get positive tweet percent.
     * @return  Formatted percentage of positive tweets
     */
    public String getPositivePercent() {
        double positivePercent =  ((double)positiveTweetCount / tweetCount * 100.0);
        if (tweetCount == 0 || positivePercent == 0) {
            return "0%";
        }
        return String.format("%.2f", positivePercent) +  "%";
    }

    /**
     * Visit a tweet and collect analytics.
     */
    @Override
    public void visit(Tweet tweet) {
        tweetCount++; // Increment tweet count
        if (checkPositivity(tweet)) { // Check for presence of positive keywords
            positiveTweetCount++; // Increment positivity count 
        }
    }

    /** 
     * Check whether a tweet contains positive keywords.
     * @return  True if tweet contains keywords, false if not
     */
    public boolean checkPositivity(Tweet tweet) {
        for (String word : positiveKeywords) { // Iterate through list of keywwords
            if (tweet.getMessage().toLowerCase().contains(word)) {
                return true; // Return true if tweet contains any of the keywords
            }
        }
        return false; // Return false if the tweet contains no positive keywords
    }

    /**
     * Visit the tweets posted by a given user
     */
    @Override
    public void visit(User user) { 
        for (Tweet tweet : user.getNewsfeed()) { // For all posts in the user's feed
            if (tweet.getTweeterID().equals(user.getID())) { // If the user is the original poster
                visit(tweet);   // Visit the tweet
            }
        }
    }

    /**
     * Visit the users in a given group
     */
    @Override
    public void visit(Group group) { 
        for (User user : group.getUsers()) {
            visit(user);    // Visit users in the current group
        }
        for (Group subgroup : group.getSubgroups()) {
            visit(subgroup); // Recursively visit the group's subgroups
        }
    }
}
