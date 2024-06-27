package minitwitter.model;

/**
 * Visitor that collects analytics of MiniTwitter groups
 * @author Anh Hoang
 */
public class GroupVisitor implements TwitterVisitor {
    private int groupCount; // Number of groups
    
    /** Default constructor */
    public GroupVisitor() { groupCount = 0; }
    
    /** 
     * Get the number of total groups counted.
     * @return The number of groups counted by the visitor
     */
    public int getGroupCount() {
        return groupCount;
    }

    /** Visit a group and its subgroups and collect analytics. */
    @Override
    public void visit(Group group) {
        groupCount++; // Increment group count
        // Visit all subgroups recursively
        for (Group subgroup : group.getSubgroups()) {
            visit(subgroup);
        }
    }

    @Override
    public void visit(Tweet tweet) { } // Do nothing

    @Override
    public void visit(User user) {  // Do nothing
    
    }
    
}
