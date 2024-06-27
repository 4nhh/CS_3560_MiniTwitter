package minitwitter.model;
import java.util.*;
import javax.swing.tree.*;
/**
 * Interface for MiniTwitter components.
 * Implements the Composite pattern.
 * @author Anh Hoang
 */
public interface TwitterComponent {
    public void add(TwitterComponent newChild);
    public List<String> getChildren();
    public String getID();
    DefaultMutableTreeNode createTreeNode();   
}
