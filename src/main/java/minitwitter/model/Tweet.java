package minitwitter.model;
import java.sql.Timestamp;

/** 
 * Class representing a Tweet object in the MiniTwitter
 * application.
 * @author Anh Hoang
 */
public class Tweet {
    private String tweeterID;  // ID of user who posted the tweet
    private String message;    // Tweet text content
    private long creationTime; // Time of group's creation
    
    /** 
     * Constructor for new tweet. 
     * @param tweeterID  ID of user tweeting
     * @param message    Text to be tweeted
     */
    public Tweet(String tweeterID, String message) {
        this.tweeterID = tweeterID;
        this.message = message;
        this.creationTime = System.currentTimeMillis();
    }

    /** 
     * Getter for tweeter ID.
     * @return ID of original poster
     */
    public String getTweeterID() { return tweeterID; }

    /**
     * Getter for tweet message.
     * @return Tweet message text
     */
    public String getMessage() { return message; }

    /**
    * Accepts a new visitor.
    * @param visitor  Visitor to be accepted
    */
    public void accept(TwitterVisitor visitor) {
        visitor.visit(this);
    }

    /**
     * Converts a Tweet object to a human readable string
     * that conveys its data.
     * @return Formatted tweet with clear author, time, and message
     */
    @Override
    public String toString() {
        String time = new Timestamp(creationTime).toString();
        String formattedTweet = getTweeterID() + " (" + time + ")" + ":  " + getMessage();
        return formattedTweet;
    }

    /**
     * Retrieve group's creation date.
     * @return Group's creation date in Epoch milliseconds
     */
    public long getCreationTime() {
        return creationTime;
    }

}
