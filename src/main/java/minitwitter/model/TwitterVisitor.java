package minitwitter.model;

/**
 * Interface for visitors of MiniTwitter objects.
 * Implements the Visitor pattern.
 * @author Anh Hoang
 */
public interface TwitterVisitor {
    void visit(User user);
    void visit(Group group);
    void visit(Tweet tweet);
}
