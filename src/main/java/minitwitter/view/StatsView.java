package minitwitter.view;
import minitwitter.controller.AdminController;
import javax.swing.ImageIcon;

/**
 * JFrame view for MiniTwitter analytics.
 * @author Anh Hoang
 */
public class StatsView extends javax.swing.JFrame {
    String statType;
    StatsView userCountView, groupCountView, tweetCountView, positivityView;
    AdminController admin;

    /**
     * Creates new form StatsView
     */
    public StatsView(String stat) {
        ImageIcon img = new ImageIcon(getClass().getClassLoader().getResource("featherFavicon.png"));
        this.setIconImage(img.getImage());
        admin = AdminController.getInstance();
        initComponents();
        statType = stat;
        statTitle.setText(stat);
        updateStat();
    }

    /** Updates the value displayed by the Analytics window */
    public void updateStat() {
        switch (statType) {
            case "Users":
                statValue.setText(Integer.toString(admin.getUserStats()));
                break;
            case "Groups":
                statValue.setText(Integer.toString(admin.getGroupStats()));
                break;
            case "Tweets":
                statValue.setText(Integer.toString(admin.getTweetStats()));
                break;
            case "Positivity":
                statValue.setText(admin.getPositivity());
                break;
            default:
                break;
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        statValue = new javax.swing.JLabel();
        statTitle = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Analytics");
        setAlwaysOnTop(false);
        setBackground(new java.awt.Color(250, 250, 250));
        setResizable(false);

        statValue.setFont(new java.awt.Font("TT Hoves DemiBold", 0, 36)); // NOI18N
        statValue.setForeground(new java.awt.Color(90, 90, 90));
        statValue.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        statValue.setText("<Value>");

        statTitle.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 12)); // NOI18N
        statTitle.setForeground(new java.awt.Color(120, 120, 120));
        statTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        statTitle.setText("<title>");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(statValue, javax.swing.GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE)
                    .addComponent(statTitle, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(statValue, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(statTitle)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel statTitle;
    private javax.swing.JLabel statValue;
    // End of variables declaration//GEN-END:variables
}
