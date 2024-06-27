package minitwitter.model;

/**
 * Visitor that collects analytics of MiniTwitter users
 * @author Anh Hoang
 */
public class UserVisitor implements TwitterVisitor {
    private int userCount = 0; // Number of users
    
    /** Default constructor */
    public UserVisitor() { }
    
    /**
     * Retrieve number of users counted by the visitor.
     * @return  Number of users counted by the visitor
     */
    public int getUserCount() { return userCount; }


    /** Visit a given user and collect analytics */
    @Override
    public void visit(User user) {
        userCount++;
        for (Tweet tweet : user.getNewsfeed()) {
            if (tweet.getTweeterID().equals(user.getID())) {
                visit(tweet);
            }
        }
    }

    /** Visit the users in a given group and its subgroups */
    @Override
    public void visit(Group group) {
        for (User user : group.getUsers()) {
            visit(user);
        }
        for (Group subgroup : group.getSubgroups()) {
            visit(subgroup);
        }
    }

    @Override
    public void visit(Tweet tweet) { } // Do nothing
    
}
