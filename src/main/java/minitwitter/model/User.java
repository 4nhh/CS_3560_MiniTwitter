package minitwitter.model;
import minitwitter.controller.AdminController;
import java.util.*;
import javax.swing.tree.*;
import java.sql.Timestamp;

/**
 * A class representing users in the MiniTwitter application.
 * Implements the Observer pattern.
 * @author Anh Hoang
 */
public class User extends Observable implements Observer, TwitterComponent {
    private String id;                  // User's ID
    private List<String> followingIDs;  // List of IDs of users followed
    private List<String> followerIDs;   // List of IDs of users followingIDs
    private LinkedList<Tweet> newsfeed;       // List of tweets on user's feed
    private long creationTime;
    private long lastUpdateTime;
    
    /**
     * Constructor for a new user.
     * @param id User's ID. Human-readable, chosen name.
     */
    public User(String id) {
        this.id = id;
        this.followingIDs = new ArrayList<>();
        this.followerIDs = new ArrayList<>();
        this.newsfeed = new LinkedList<>();
        this.creationTime = System.currentTimeMillis();
        this.lastUpdateTime = creationTime; 
    }
    
    /**
     * Retrieve the user's ID.
     * @return  The user's ID
     */
    @Override
    public String getID() { return this.id; }

    /**
     * Retrieve the IDs of the users followed by this user.
     * @return  IDs of all users the user is following
     */
    public List<String> getFollowingIDs() { return this.followingIDs; }

    /**
     * Retrieve the IDs of users following this user.
     * @return  IDs of all users following this user
     */
    public List<String> getFollowerIDs() { return this.followerIDs; }
    
    /**
     * Retrieve all Tweets in the user's news feed.
     * @return  List of tweets in the user's news feed
     */
    public LinkedList<Tweet> getNewsfeed() { return this.newsfeed; }

    /**
     * Retrieve user's creation date.
     * @return User's creation date in Epoch milliseconds
     */
    public long getCreationTime() { return this.creationTime; }

    /**
     * Retrieve user's last update time.
     * @return User's late update time in Epoch milliseconds
     */
    public long getLastUpdateTime() { return this.lastUpdateTime; }

    /**
     * Retrieve user's creation date as a human-readable timestamp.
     * @return User's creation date as a timestamp
     */
    public String getCreationTimeStamp() { return new Timestamp(creationTime).toString(); }

    /**
     * Follow a user with their UID.
     * @param UID The ID of the user to be followed.
     */
    public void follow(String UID) {
        if (!followingIDs.contains(UID)) {
            followingIDs.add(UID);
        }
        AdminController.getInstance().notifyFollowed(UID, this.id);
    }
    
    /**
     * Add a follower's UID to the list of follower UIDs.
     * @param UID The ID of the new follower.
     */
    public void addFollower(String UID) {
        if (!followerIDs.contains(UID)) {
            followerIDs.add(UID);
        }
    }
    
    /**
     * Send a tweet and notify all followers.
     * @param message Tweet message contents.
     */
    public void sendTweet(String message) {
        Tweet newTweet = new Tweet(this.id, message);
        this.update(this, newTweet);
        AdminController.getInstance().notifyTweet(this.id, newTweet);
    }
    
    /** 
     * Update the user's feed with a new tweet
     * @param obj   Unused. Remnant from implemented observer class.
     * @param arg   Tweet object to be added to feed
     */
    @Override
    public void update(Observable obj, Object arg) {
        if (arg instanceof Tweet) {
            newsfeed.add((Tweet)arg);
            lastUpdateTime = ((Tweet)arg).getCreationTime();
        }
    }
    
    /**
    * Accepts a new visitor
    * @param visitor  Visitor to be accepted
    */
    public void accept(TwitterVisitor visitor) {
        visitor.visit(this);
    }
    
    /** Create a tree node for the user */
    @Override
    public DefaultMutableTreeNode createTreeNode() {
        return new javax.swing.tree.DefaultMutableTreeNode(this.id, false);
    }
    
    @Override
    public List<String> getChildren() { throw new UnsupportedOperationException(); } // Users can't have children
    @Override
    public void add(TwitterComponent newChild) { throw new UnsupportedOperationException(); } // Users can't add children

}
