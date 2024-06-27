package minitwitter.view;
import minitwitter.controller.AdminController;
import javax.swing.tree.*;
import javax.swing.*;

/**
 * JFrame view to represent the admin control panel of
 * the MiniTwitter application.
 * @author Anh Hoang
 */
public class AdminView extends javax.swing.JFrame {
    AdminController admin = AdminController.getInstance();
    
    /**
     * Creates new form AdminView
     */
    public AdminView() {
        ImageIcon img = new ImageIcon(getClass().getClassLoader().getResource("birdFavicon.png"));
        this.setIconImage(img.getImage());
        AdminController.getInstance();
        initComponents();
        userTreePanel.setCellRenderer(new UserTreeCellRenderer());
        // initialize tree
        updateTree();
        newUserGroupMembership.addItem("Root");
        newGroupParentGroup.addItem("Root");
    }
    
    public void updateUserFields(String userID) {
        userViewSelect.addItem(userID);
        updateTree();
    }
    
    public void updateGroupFields(String groupID) {
        newUserGroupMembership.addItem(groupID);
        newGroupParentGroup.addItem(groupID);
        updateTree();
    }
    
    private void updateTree() {
        DefaultMutableTreeNode rootNode = admin.getRoot().createTreeNode();
        userTreePanel.setModel(new DefaultTreeModel(rootNode));
        // Expand tree by default
        for (int i = 0; i < userTreePanel.getRowCount(); i++) {
            userTreePanel.expandRow(i);
        }
    }

    private void openStats(String stat) {
        SwingUtilities.invokeLater(() -> {
            StatsView view = admin.getStatsViewFromType(stat);

            if (admin.getStatsViewFromType(stat) == null) {
                view = new StatsView(stat);
                admin.addStatsView(stat, view);
            }
            
            view.setVisible(true);
        });
    }
    
    private void openUserView(String userID) {
        if (userViewSelect.getSelectedItem() != null) {
            SwingUtilities.invokeLater(() -> {
                UserView view = admin.getUserViewFromID(userID);
                if (view == null) {
                    view = new UserView(userID);
                    admin.addUserView(userID, view);
                }
                view.setVisible(true);
            });
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

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        userTreePanel = new javax.swing.JTree();
        checkIDButton = new javax.swing.JButton();
        lastUpdatedButton = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        createUser = new javax.swing.JButton();
        newUserNameLabel = new javax.swing.JLabel();
        newUsernameField = new javax.swing.JTextField();
        newUserGroupMembershipLabel = new javax.swing.JLabel();
        newUserGroupMembership = new javax.swing.JComboBox<>();
        jPanel4 = new javax.swing.JPanel();
        createGroup = new javax.swing.JButton();
        newGroupNameLabel = new javax.swing.JLabel();
        groupNameField = new javax.swing.JTextField();
        newGroupParentLabel = new javax.swing.JLabel();
        newGroupParentGroup = new javax.swing.JComboBox<>();
        analyticsPanel = new javax.swing.JPanel();
        positivityStatButton = new javax.swing.JButton();
        tweetStatButton = new javax.swing.JButton();
        groupStatButton = new javax.swing.JButton();
        userStatButton = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        userViewSelect = new javax.swing.JComboBox<>();
        openUserView = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("MiniTwitter Admin View");
        setResizable(false);

        jPanel1.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 12)); // NOI18N

        userTreePanel.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 12)); // NOI18N
        userTreePanel.setModel(userTreePanel.getModel());
        jScrollPane1.setViewportView(userTreePanel);

        checkIDButton.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 12)); // NOI18N
        checkIDButton.setForeground(new java.awt.Color(60, 60, 60));
        checkIDButton.setText("Check IDs");
        checkIDButton.setToolTipText("Check if IDs are duplicated or contain invalid characters");
        checkIDButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkIDButtonActionPerformed(evt);
            }
        });

        lastUpdatedButton.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 12)); // NOI18N
        lastUpdatedButton.setForeground(new java.awt.Color(60, 60, 60));
        lastUpdatedButton.setText("Last Updated");
        lastUpdatedButton.setToolTipText("Find name of last updated user");
        lastUpdatedButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lastUpdatedButtonActionPerformed(evt);
            }
        });

        jPanel2.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 12)); // NOI18N

        jTabbedPane1.setForeground(new java.awt.Color(90, 90, 90));
        jTabbedPane1.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 14)); // NOI18N

        createUser.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 12)); // NOI18N
        createUser.setForeground(new java.awt.Color(90, 90, 90));
        createUser.setText("Create");
        createUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createUserActionPerformed(evt);
            }
        });

        newUserNameLabel.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 14)); // NOI18N
        newUserNameLabel.setForeground(new java.awt.Color(90, 90, 90));
        newUserNameLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        newUserNameLabel.setText("Username");

        newUsernameField.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 12)); // NOI18N
        newUsernameField.setForeground(new java.awt.Color(40, 40, 40));

        newUserGroupMembershipLabel.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 14)); // NOI18N
        newUserGroupMembershipLabel.setForeground(new java.awt.Color(90, 90, 90));
        newUserGroupMembershipLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        newUserGroupMembershipLabel.setText("Group");

        newUserGroupMembership.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 12)); // NOI18N
        newUserGroupMembership.setForeground(new java.awt.Color(40, 40, 40));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(newUserNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(newUsernameField))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(createUser, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(newUserGroupMembershipLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(newUserGroupMembership, 0, 1, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(newUserNameLabel)
                    .addComponent(newUsernameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(newUserGroupMembershipLabel)
                    .addComponent(newUserGroupMembership, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(createUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(69, 69, 69))
        );

        jTabbedPane1.addTab("User", jPanel3);

        createGroup.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 12)); // NOI18N
        createGroup.setForeground(new java.awt.Color(90, 90, 90));
        createGroup.setText("Create");
        createGroup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createGroupActionPerformed(evt);
            }
        });

        newGroupNameLabel.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 14)); // NOI18N
        newGroupNameLabel.setForeground(new java.awt.Color(90, 90, 90));
        newGroupNameLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        newGroupNameLabel.setText("Group Name");

        groupNameField.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 12)); // NOI18N
        groupNameField.setForeground(new java.awt.Color(40, 40, 40));

        newGroupParentLabel.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 14)); // NOI18N
        newGroupParentLabel.setForeground(new java.awt.Color(90, 90, 90));
        newGroupParentLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        newGroupParentLabel.setText("Parent");

        newGroupParentGroup.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 12)); // NOI18N
        newGroupParentGroup.setForeground(new java.awt.Color(40, 40, 40));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(newGroupNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(groupNameField))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(createGroup, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(newGroupParentLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(newGroupParentGroup, 0, 1, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(newGroupNameLabel)
                    .addComponent(groupNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(newGroupParentLabel)
                    .addComponent(newGroupParentGroup, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(createGroup, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(69, 69, 69))
        );

        jTabbedPane1.addTab("Group", jPanel4);

        analyticsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Analytics", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Yu Gothic UI Semibold", 0, 14), new java.awt.Color(90, 90, 90))); // NOI18N
        analyticsPanel.setToolTipText("");
        analyticsPanel.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 12)); // NOI18N

        positivityStatButton.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 12)); // NOI18N
        positivityStatButton.setForeground(new java.awt.Color(90, 90, 90));
        positivityStatButton.setText("Positivity");
        positivityStatButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                positivityStatButtonActionPerformed(evt);
            }
        });

        tweetStatButton.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 12)); // NOI18N
        tweetStatButton.setForeground(new java.awt.Color(90, 90, 90));
        tweetStatButton.setText("Total Tweets");
        tweetStatButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tweetStatButtonActionPerformed(evt);
            }
        });

        groupStatButton.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 12)); // NOI18N
        groupStatButton.setForeground(new java.awt.Color(90, 90, 90));
        groupStatButton.setText("Total Groups");
        groupStatButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                groupStatButtonActionPerformed(evt);
            }
        });

        userStatButton.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 12)); // NOI18N
        userStatButton.setForeground(new java.awt.Color(90, 90, 90));
        userStatButton.setText("Total Users");
        userStatButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userStatButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout analyticsPanelLayout = new javax.swing.GroupLayout(analyticsPanel);
        analyticsPanel.setLayout(analyticsPanelLayout);
        analyticsPanelLayout.setHorizontalGroup(
            analyticsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(analyticsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(analyticsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(analyticsPanelLayout.createSequentialGroup()
                        .addComponent(tweetStatButton, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(positivityStatButton, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(analyticsPanelLayout.createSequentialGroup()
                        .addComponent(userStatButton, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(groupStatButton, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        analyticsPanelLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {positivityStatButton, tweetStatButton});

        analyticsPanelLayout.setVerticalGroup(
            analyticsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, analyticsPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(analyticsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(groupStatButton, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(userStatButton, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(analyticsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(positivityStatButton, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tweetStatButton, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        analyticsPanelLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {groupStatButton, positivityStatButton, userStatButton});

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "User View", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Yu Gothic UI Semibold", 0, 14))); // NOI18N
        jPanel6.setForeground(new java.awt.Color(90, 90, 90));
        jPanel6.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 12)); // NOI18N

        userViewSelect.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 12)); // NOI18N
        userViewSelect.setForeground(new java.awt.Color(40, 40, 40));

        openUserView.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 12)); // NOI18N
        openUserView.setForeground(new java.awt.Color(90, 90, 90));
        openUserView.setText("View");
        openUserView.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openUserViewActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(userViewSelect, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(openUserView, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(userViewSelect, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                    .addComponent(openUserView, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane1)
                    .addComponent(analyticsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(analyticsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(checkIDButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lastUpdatedButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(checkIDButton)
                    .addComponent(lastUpdatedButton))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void createUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createUserActionPerformed
        String username = newUsernameField.getText().replaceAll("[^a-zA-Z0-9]", "");
        String groupName = (String)newUserGroupMembership.getSelectedItem();
        if (!username.isEmpty() && admin.getUserFromID(username) == null) {
            admin.newUser(username, groupName);
            newUsernameField.setText("");
            updateTree();
            updateUserFields(username);
            admin.updateStatByType("Users");
        }
    }//GEN-LAST:event_createUserActionPerformed

    private void createGroupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createGroupActionPerformed
        String groupName = groupNameField.getText().replaceAll("[^a-zA-Z0-9]", "").trim();
        String parentID = (String)newGroupParentGroup.getSelectedItem();
        if (!groupName.equals("") && admin.getGroupFromID(groupName) == null) {
            admin.newGroup(groupName, parentID);
            groupNameField.setText("");
            updateGroupFields(groupName);
            admin.updateStatByType("Groups");
        }
    }//GEN-LAST:event_createGroupActionPerformed

    private void openUserViewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openUserViewActionPerformed
        String userID = (String)userViewSelect.getSelectedItem();
        openUserView(userID);
    }//GEN-LAST:event_openUserViewActionPerformed

    private void groupStatButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_groupStatButtonActionPerformed
        openStats("Groups");
    }//GEN-LAST:event_groupStatButtonActionPerformed

    private void userStatButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userStatButtonActionPerformed
        openStats("Users");
    }//GEN-LAST:event_userStatButtonActionPerformed

    private void tweetStatButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tweetStatButtonActionPerformed
        openStats("Tweets");
    }//GEN-LAST:event_tweetStatButtonActionPerformed

    private void positivityStatButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_positivityStatButtonActionPerformed
        openStats("Positivity");
    }//GEN-LAST:event_positivityStatButtonActionPerformed

    private void checkIDButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkIDButtonActionPerformed
        if (admin.checkIDValidity()) {
            JOptionPane.showMessageDialog(null, "SUCCESS: All user and group IDs are valid.", "Last Updated", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "ERROR: At least one user or group ID is invalid.", "Last Updated", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_checkIDButtonActionPerformed

    private void lastUpdatedButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lastUpdatedButtonActionPerformed
        String lastUpdatedUser = admin.getLastUpdatedUser();
        if (lastUpdatedUser != null) {
            JOptionPane.showMessageDialog(null, "The last updated user was " + lastUpdatedUser + ".", "Last Updated", JOptionPane.PLAIN_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "There have been no updates yet.", "Last Updated", JOptionPane.PLAIN_MESSAGE);
        }
    }//GEN-LAST:event_lastUpdatedButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AdminView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdminView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdminView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdminView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdminView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel analyticsPanel;
    private javax.swing.JButton checkIDButton;
    private javax.swing.JButton createGroup;
    private javax.swing.JButton createUser;
    private javax.swing.JTextField groupNameField;
    private javax.swing.JButton groupStatButton;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JButton lastUpdatedButton;
    private javax.swing.JLabel newGroupNameLabel;
    private javax.swing.JComboBox<String> newGroupParentGroup;
    private javax.swing.JLabel newGroupParentLabel;
    private javax.swing.JComboBox<String> newUserGroupMembership;
    private javax.swing.JLabel newUserGroupMembershipLabel;
    private javax.swing.JLabel newUserNameLabel;
    private javax.swing.JTextField newUsernameField;
    private javax.swing.JButton openUserView;
    private javax.swing.JButton positivityStatButton;
    private javax.swing.JButton tweetStatButton;
    private javax.swing.JButton userStatButton;
    private javax.swing.JTree userTreePanel;
    private javax.swing.JComboBox<String> userViewSelect;
    // End of variables declaration//GEN-END:variables
}
