package minitwitter.model;
import java.util.*;
import minitwitter.controller.AdminController;
import javax.swing.tree.*;
import java.sql.Timestamp;

/**
 * Class representing a Group in the MiniTwitter application.
 * @author Anh Hoang
 */
public class Group implements TwitterComponent {
    String id;                // Group ID
    List<String> memberIDs;   // List of member IDs
    List<String> subgroupIDs; // List of subgroup IDs
    String parentID;          // Immediate parent group.
    private long creationTime;
    
    /**
     * Constructor for a group.
     * @param id        ID of new group
     * @param parentID  ID of the new group's parent
     */
    public Group(String id, String parentID) {
        this.id = id;
        this.parentID = parentID;
        this.memberIDs = new ArrayList<>();
        this.subgroupIDs = new ArrayList<>();
        this.creationTime = System.currentTimeMillis();
    }
    
    /**
     * Add a child to the group. Implements Composite pattern.
     * @param newChild  The child to be added. Should be either
     *                  a group or a user.
     */
    @Override
    public void add(TwitterComponent newChild) {
        if (newChild != null) { 
            if (newChild instanceof User) {
                memberIDs.add(newChild.getID());
            } else if (newChild instanceof Group) {
                subgroupIDs.add(newChild.getID());
            }
        }
    }
    
    /**
    * Accepts a new visitor
    * @param visitor  Visitor to be accepted
    */
    public void accept(TwitterVisitor visitor) {
        visitor.visit(this);
    }
    
    /**
     * Create a tree with the Group object as the root
     */
    @Override
    public DefaultMutableTreeNode createTreeNode() {
        DefaultMutableTreeNode groupNode = new DefaultMutableTreeNode(this.id, true);
        for (String childID : getChildren()) {
            TwitterComponent child = AdminController.getInstance().getUserFromID(childID);
            if (child == null) {
                child = AdminController.getInstance().getGroupFromID(childID);
                child.createTreeNode();
            }
            if (child != null) {
                groupNode.add(child.createTreeNode());
            }
        }
        return groupNode;
    }

    
    @Override
    public String getID() { return id; }
    public String getParentID() { return parentID; }
    public List<Group> getSubgroups() {
        List<Group> subgroupList = new ArrayList<>();
            for (String subgroupID : subgroupIDs) {
                Group subgroup = AdminController.getInstance().getGroupFromID(subgroupID);
                if (subgroup != null) {
                    subgroupList.add(subgroup);
                }
            }
            return subgroupList;
    }
    
    /**
     * Retrieves all users who are immediate members of the group.
     * @return
     */
    public List<User> getUsers() {
        List<User> userList = new ArrayList<>();
        for (String userID : memberIDs) {
            userList.add(AdminController.getInstance().getUserFromID(userID));
        }
        return userList;
        
    }

    /** 
     * Retrieve the list of IDs of the group's immediate members.
     * @return  List of strings denoting group member IDs
     */
    public List<String> getMemberIDs() {
        return this.memberIDs;
    }
    
    /** 
     * Retrieve the list of IDs of the group's immediate subgroups.
     * @return  List of strings denoting subgroups IDs
     */
    public List<String> getSubgroupIDs() {
        List<String> subgroups = this.subgroupIDs;
        return subgroups;
    }

    /**
     * Retrieve a list of the IDs of the group's immediate
     * children, including both members and subgroups.
     * @return  List of strings denoting all child IDs
     */
    @Override
    public List<String> getChildren(){
        List<String> children = new ArrayList<>();
        children.addAll(memberIDs);
        children.addAll(subgroupIDs);
        return children;
    }
    
    /**
     * Retrieve group's creation date.
     * @return Group's creation date in Epoch milliseconds
     */
    public long getCreationTime() { return this.creationTime; }

    /**
     * Retrieve group's creation date as a human-readable timestamp.
     * @return Group's creation date as a timestamp
     */
    public String getCreationTimeStamp() { return new Timestamp(creationTime).toString(); }
}
