package minitwitter.view;
import java.awt.Component;
import javax.swing.*;
import javax.swing.tree.*;

/**
 * Renderer for the JTree of groups and users.
 * @author Anh Hoang
 */
public class UserTreeCellRenderer extends DefaultTreeCellRenderer {
    
    /**
     * Creates a cell for the tree node based on the type of object
     * passed in. A User node will have a human icon, while Groups 
     * will have a folder icon (regardless of emptiness).
     * @return Tree cell based on passed-in User/Group object.
     */
    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value,
                                                  boolean sel,
                                                  boolean expanded,
                                                  boolean leaf, int row,
                                                  boolean hasFocus) {
        
        super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
        if (((DefaultMutableTreeNode) value).getAllowsChildren()) {
            setIcon(getDefaultOpenIcon()); // Default folder icon
        } else {
            // Load custom User icon from resource folder
            java.net.URL imgPath = getClass().getClassLoader().getResource
                                              ("userLeafIcon.png");
            setIcon(new ImageIcon(imgPath, "User"));
        }
        return this;
    }
}
