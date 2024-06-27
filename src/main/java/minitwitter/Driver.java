package minitwitter;
import minitwitter.view.AdminView;
import javax.swing.SwingUtilities;

/**
 * Main driver class to trigger the Admin control panel.
 * @author Anh Hoang
 */
public class Driver {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AdminView view = new AdminView();
            view.setVisible(true);
        });
    }
}