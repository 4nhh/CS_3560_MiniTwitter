package minitwitter.controller;
import minitwitter.model.*;
import minitwitter.view.*;
import java.util.*;

/**
 * Controller for MiniTwitter operations.Facilitates communication between
 * model/data and GUI.
 * Implements the Singleton pattern.
 * @author Anh Hoang
 */
public class AdminController {
    private static AdminController instance;
    Map<String, UserView> userViews;
    Map<String, StatsView> statsViews;
    private Map<String, User> users;
    private Map<String, Group> groups;
    private Group root;
    
    /** Constructor for an AdminController object */
    private AdminController() {        
        users = new HashMap<>();
        groups = new HashMap<>();
        userViews = new HashMap<>();
        statsViews = new HashMap<>();
        root = new Group("Root", null);
        groups.put("Root", root);
    }
    
    /** 
     * Implements the Singleton pattern to retrieve the sole
     * AdminController instance
     */
    public static AdminController getInstance() {
        if (instance == null) { instance = new AdminController(); }
        return instance;
    }
    
    /** 
     * Retrieves the root group.
     * @return  Root Group of entire MiniTwitter system
     */
    public Group getRoot() { return root; }

    /**
     * Retrieves all users in the MiniTwitter system.
     * @return  List of users registered in MiniTwitter
     */
    public Collection<User> getAllUsers() { return users.values(); }

    /**
     * Retrieves all groups in the MiniTwitter system.
     * @return  List of groups registered in MiniTwitter
     */
    public Collection<Group> getAllGroups() { return groups.values(); }
    
    public User getUserFromID(String id) { return users.get(id); }
    public Group getGroupFromID(String id) { return groups.get(id); }
    public List<String> getFollowingIDs(String followerID) {  return getUserFromID(followerID).getFollowingIDs(); }
    public List<String> getFollowerIDs(String userID) { return getUserFromID(userID).getFollowerIDs(); }
    public String getUserCreationTimestamp(String userID) { return getUserFromID(userID).getCreationTimeStamp(); }
    public void addUserView(String userID, UserView view) { userViews.put(userID, view); }
    public UserView getUserViewFromID(String userID) { return userViews.get(userID); }
    public void addStatsView(String type, StatsView view) { statsViews.put(type, view); }
    public StatsView getStatsViewFromType(String type) { return statsViews.get(type); }
    public long getLastUpdateTime(String userID) { return getUserFromID(userID).getLastUpdateTime(); }

    /**
     * Create a new user.
     * @param id        ID of new user
     * @param groupID   ID of new user's parent group
     */
    public void newUser(String id, String groupID) {
        if (getUserFromID(id) == null) {
            User newUser = new User(id);
            users.put(id, newUser);
            Group parentGroup = getGroupFromID(groupID);
            if (parentGroup != null) {
                parentGroup.add(newUser);
            }
        }
    }
    
    /** 
     * Create a new group.
     * @param id        ID of new group
     * @param groupID   ID of new group's parent group
     */
    public void newGroup(String id, String parentID) {
        if (!groups.keySet().contains(id)) {
            Group newGroup = new Group(id, parentID);
            groups.put(id, newGroup);
            Group parentGroup = getGroupFromID(parentID);
            if (parentGroup != null) {
                parentGroup.add(newGroup);
            }
        }
    }
    
    /**
     * Create and post a new tweet.
     * @param tweeterID     ID of tweet poster
     * @param tweetMessage  Message content of tweet
     * @return              Formatted string of tweet
     */
    public String postTweet(String tweeterID, String tweetMessage) {
        User tweeter = getUserFromID(tweeterID);
        tweetMessage = tweetMessage.trim();
        if (tweetMessage.length() > 0) {
            tweeter.sendTweet(tweetMessage);
        }
        return tweeter.getNewsfeed().getLast().toString();
    }
    
    /**
     * Retrieve a user's news feed. 
     * @param id    ID of user whose feed is to be retrieved
     * @return      List of tweet Strings in the user's news feed
     */
    public List<String> getNewsFeedFromID(String id) {
        User user = getUserFromID(id);
        List<String> tweetContents = new ArrayList<>();
        for (Tweet tweet : user.getNewsfeed()) {
            tweetContents.add(tweet.toString());
        }
        return tweetContents;
    }
    
    /**
     * Retrieve application-wide user count data.
     * @return  The number of total users
     */
    public int getUserStats() {
        UserVisitor visitor = new UserVisitor();
        visitor.visit(root);
        return visitor.getUserCount();
    }

    /**
     * Retrieve application-wide group count data.
     * @return  The number of total groups
     */
    public int getGroupStats() {
        GroupVisitor visitor = new GroupVisitor();
        visitor.visit(root);
        return visitor.getGroupCount();
    }

    /**
     * Retrieve application-wide tweet count data.
     * @return  The number of total tweets
     */
    public int getTweetStats() {
        TweetVisitor visitor = new TweetVisitor();
        visitor.visit(root);
        return visitor.getTweetCount();
    }

    /**
     * Retrieve application-wide tweet positivity metrics.
     * @return  Percent of tweets with positive keywords
     */
    public String getPositivity() {
        TweetVisitor visitor = new TweetVisitor();
        visitor.visit(root);
        return visitor.getPositivePercent();
    }
    
    /**
     * Make one user follow another
     * @param followerID    ID of user following
     * @param followedID    ID of user to be followed
     */
    public void followUser(String followerID, String followedID) {
        User newFollower = getUserFromID(followerID);
        if (newFollower != null) {
            newFollower.follow(followedID);
        }
    }
    
    /** 
     * Update follower list of another user upon following
     * @param followedID    ID of user newly followed
     * @param followerID    ID of user following
     */
    public void notifyFollowed(String followedID, String followerID) {
        User followedUser = users.get(followedID);
        if (followedUser != null) {
            followedUser.addFollower(followerID);
        }
    }

    /**
     * Notify a tweeter's followers of a newly posted tweet..
     * @param tweeterID     ID of user followed
     * @param tweet         Newly posted tweet
     */
    public void notifyTweet(String tweeterID, Tweet tweet) {
        User tweeter = getUserFromID(tweeterID);
        if (tweeter != null) {
            for (String followerID : tweeter.getFollowerIDs()) {
                User follower = getUserFromID(followerID);
                if (follower != null) {
                    follower.update(tweeter, tweet);
                }
            }
        }
    }
    
    /**
     * Open a StatsView window to display MiniTwitter
     * analytics.
     * @param type  Type of statistic window opened:
     *              Users, Groups, Tweets, or Positivity
     */
    public void updateStatByType(String type) {
        StatsView statView = statsViews.get(type);
        if (statView != null) {
            statView.updateStat();
        }
    }

    /** 
     * Retrieve the ID of the user with the most recently updated news feed.
     * @return ID of most recently updated user
     */
    public String getLastUpdatedUser() {
        if (getTweetStats() == 0 && getUserStats() == 0) { return null; }
        Map<Long, String> userLastUpdates = new HashMap<>();
        for (String userID : users.keySet()) {
            userLastUpdates.put(getLastUpdateTime(userID), userID);
        }
        long globalLastUpdateTime = Collections.max(userLastUpdates.keySet());
        return userLastUpdates.get(globalLastUpdateTime);
    }

    /**
     * Check all stored User and Group IDs for uniqueness and spaces.
     * @return True if all IDs are valid, false if not
     */
    public boolean checkIDValidity() {
        List<String> allIDs = new ArrayList<>();
        Set<String> lowercaseIDs = new HashSet<>();
        allIDs.addAll(users.keySet());
        allIDs.addAll(groups.keySet());

        for (String id : allIDs) {
            if (id.contains(" ") || lowercaseIDs.add(id.toLowerCase()) == false) {
                return false;
            }
        }
        return true;
    }

}