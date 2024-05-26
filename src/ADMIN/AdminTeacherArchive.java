
package ADMIN;


import config.LogsHistory;
import config.Session;
import config.myConnection;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JPanel;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import net.proteanit.sql.DbUtils;
import student.attendance.system.LoginPage;



public class AdminTeacherArchive extends javax.swing.JFrame {

    Session session = Session.getInstance();
    int adminID = session.getId();
    
    public AdminTeacherArchive() {
        initComponents();
        ourDateTime();//show date and time
        //deafault image logo
        ImageIcon  image2 = new ImageIcon(new ImageIcon("src/icon/IT_logo.png").getImage().getScaledInstance(ITLogo.getWidth(), ITLogo.getHeight(), Image.SCALE_SMOOTH));
        ITLogo.setIcon(image2);
        ImageIcon  image3 = new ImageIcon(new ImageIcon("src/icon/scc logo.png").getImage().getScaledInstance(SCCLogo.getWidth(), SCCLogo.getHeight(), Image.SCALE_SMOOTH));
        SCCLogo.setIcon(image3);
        
        displayDataTable();//display data in the table
    }
    

    //display time
    public void ourDateTime() {
        Thread thread = new Thread(() -> {
            while (true) {
                LocalDateTime now = LocalDateTime.now();
                String formattedDateTime = now.format(DateTimeFormatter.ofPattern("EEEE MMMM dd, yyyy  hh:mm:ss a"));
                datetime.setHorizontalAlignment(SwingConstants.RIGHT);
                datetime.setText(formattedDateTime);
                try {
                    Thread.sleep(1000); // Update every second
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }
    
    //function on success message dialog
    private void showSuccessDialog(String message) {
        UIManager.put("Button.focus", new ColorUIResource(new Color(0,0,0,0)));
        JOptionPane.showMessageDialog(null, message, "", JOptionPane.INFORMATION_MESSAGE);
    }
    
    //display name
    public void displayNameAndRole(){
        jAdminName.setText("ADMIN");
    }
    
    //set color
    public void setColor(JPanel panel) {
        panel.setBackground(new Color(80,140,210));
    }
    
    //reset color
    public void resetColor(JPanel panel) {
        panel.setBackground(new Color(40,110,210));
    }
    
    //jtable settings
    public void tableSettings(){
        // Disable cell editing by default
        jteacher_Table.setDefaultEditor(Object.class, null);

        //color of the header
        jteacher_Table.getTableHeader().setBackground(Color.decode("#3399ff"));

        //change the column header name
        TableColumnModel columnModel = jteacher_Table.getColumnModel();
        columnModel.getColumn(0).setHeaderValue("Teacher ID");
        columnModel.getColumn(1).setHeaderValue("Full Name");
        columnModel.getColumn(2).setHeaderValue("Birthdate");
        columnModel.getColumn(3).setHeaderValue("Address");
        columnModel.getColumn(4).setHeaderValue("Status");

        //size of the row height
        jteacher_Table.setRowHeight(20);
        
        //size of column by percent
        int totalWidth = jteacher_Table.getWidth();
        double[] columnWidthPercentages = {15, 25, 15, 30, 15};
        int[] columnWidths = new int[columnWidthPercentages.length];

        for (int i = 0; i < columnWidthPercentages.length; i++) {
            columnWidths[i] = (int) (totalWidth * (columnWidthPercentages[i] / 100.0));
        }

        columnModel.getColumn(0).setPreferredWidth(columnWidths[0]);
        columnModel.getColumn(1).setPreferredWidth(columnWidths[1]);
        columnModel.getColumn(2).setPreferredWidth(columnWidths[2]);
        columnModel.getColumn(3).setPreferredWidth(columnWidths[3]);
        columnModel.getColumn(4).setPreferredWidth(columnWidths[4]);
        
        // Set font properties for the header
        Font headerFont = new Font("Segoe UI", Font.PLAIN, 14);
        jteacher_Table.getTableHeader().setFont(headerFont);
        jteacher_Table.getTableHeader().setForeground(Color.WHITE);

        //Set alignment for header labels
        TableCellRenderer rendererFromHeader = jteacher_Table.getTableHeader().getDefaultRenderer();
        JLabel headerLabel = (JLabel) rendererFromHeader;
        headerLabel.setHorizontalAlignment(JLabel.CENTER);
        
        //Set cell renderer to center-align data in all columns 
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        jteacher_Table.setDefaultRenderer(Object.class, centerRenderer);
    }
    
    //show pending data in the table
    public void displayPendingDataTable(){
        try{
            myConnection dbc = new myConnection();
            ResultSet rs = dbc.getData("SELECT teacher_id,teacher_name,teacher_birthdate,teacher_address,teacher_status FROM teacher WHERE teacher_archive = 'YES' AND teacher_status = 'PENDING'");
            jteacher_Table.setModel(DbUtils.resultSetToTableModel(rs));
            tableSettings();
            rs.close();
        }catch(SQLException ex){
            System.out.println("Errors: "+ex.getMessage());
        }
    }
    
    //show data int the table
    public void displayDataTable(){
        try{
            myConnection dbc = new myConnection();
            ResultSet rs = dbc.getData("SELECT teacher_id,teacher_name,teacher_birthdate,teacher_address,teacher_status FROM teacher WHERE teacher_archive = 'YES'");
            jteacher_Table.setModel(DbUtils.resultSetToTableModel(rs));
            tableSettings();
            rs.close();
        }catch(SQLException ex){
            System.out.println("Errors: "+ex.getMessage());
        }
    }
    
    //show active data in the table
    public void displayActiveDataTable(){
        try{
            myConnection dbc = new myConnection();
            ResultSet rs = dbc.getData("SELECT teacher_id,teacher_name,teacher_birthdate,teacher_address,teacher_status FROM teacher WHERE teacher_archive = 'YES' AND teacher_status = 'ACTIVE'");
            jteacher_Table.setModel(DbUtils.resultSetToTableModel(rs));
            tableSettings();
            rs.close();
        }catch(SQLException ex){
            System.out.println("Errors: "+ex.getMessage());
        }
    }
    
    //restore the selected ID
    private void restoreSelectedRow() {
        int selectedRow = jteacher_Table.getSelectedRow();
        if (selectedRow >= 0) {
            int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to restore this record?", "", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                int teacherID = (int)jteacher_Table.getValueAt(selectedRow, 0); // Assuming teacher ID is in the first column
                String query = "UPDATE teacher SET teacher_archive = 'NO',teacher_status = 'INACTIVE' WHERE teacher_id = '" + teacherID + "'";
                myConnection dbc = new myConnection();
                if(dbc.insertData(query)){
                    showSuccessDialog("Teacher ID " + teacherID + " Record restore successfully.");
                    //history logs
                    LogsHistory logHistory = new LogsHistory();
                    logHistory.insertAdminLog(adminID, "Admin restored teacher with ID number " + teacherID);
                    
                    displayDataTable();
                }else{
                    JOptionPane.showMessageDialog(null, "Failed to restore record.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Please select a row to restore.", "No Row Selected", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jAdminName = new javax.swing.JLabel();
        datetime = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jDashboard = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jManageStudent = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLogoutButton = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jHistoryLogs = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jArchiveTeacher = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jteacher_Table = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        SCCLogo = new javax.swing.JLabel();
        ITLogo = new javax.swing.JLabel();
        jError = new javax.swing.JLabel();
        jApprove = new javax.swing.JButton();
        jRefresh = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("TEACHER MANAGE STUDENT");
        setMinimumSize(new java.awt.Dimension(1100, 700));
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(25, 118, 211), 2));
        jPanel1.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        jPanel1.setMinimumSize(new java.awt.Dimension(0, 0));
        jPanel1.setPreferredSize(new java.awt.Dimension(1100, 700));
        jPanel1.setLayout(null);

        jPanel3.setBackground(new java.awt.Color(25, 118, 211));
        jPanel3.setLayout(null);

        jLabel10.setFont(new java.awt.Font("Segoe UI Semibold", 0, 20)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Admin:");
        jPanel3.add(jLabel10);
        jLabel10.setBounds(20, 30, 70, 30);

        jAdminName.setFont(new java.awt.Font("Segoe UI Semibold", 0, 20)); // NOI18N
        jAdminName.setForeground(new java.awt.Color(255, 255, 255));
        jPanel3.add(jAdminName);
        jAdminName.setBounds(100, 30, 240, 30);

        datetime.setBackground(new java.awt.Color(255, 255, 255));
        datetime.setFont(new java.awt.Font("Segoe UI Semibold", 0, 20)); // NOI18N
        datetime.setForeground(new java.awt.Color(255, 255, 255));
        jPanel3.add(datetime);
        datetime.setBounds(410, 30, 440, 40);

        jPanel1.add(jPanel3);
        jPanel3.setBounds(220, 90, 880, 100);

        jPanel2.setBackground(new java.awt.Color(25, 118, 211));
        jPanel2.setLayout(null);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-admin-80 (2).png"))); // NOI18N
        jPanel2.add(jLabel1);
        jLabel1.setBounds(60, -10, 90, 111);

        jDashboard.setBackground(new java.awt.Color(40, 110, 210));
        jDashboard.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jDashboard.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jDashboardMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jDashboardMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jDashboardMouseExited(evt);
            }
        });
        jDashboard.setLayout(null);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/dashboard.png"))); // NOI18N
        jLabel3.setText("  Dashboard");
        jDashboard.add(jLabel3);
        jLabel3.setBounds(30, 0, 130, 50);

        jPanel2.add(jDashboard);
        jDashboard.setBounds(0, 190, 220, 50);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("System");
        jPanel2.add(jLabel2);
        jLabel2.setBounds(70, 117, 70, 25);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Student Attendance");
        jPanel2.add(jLabel6);
        jLabel6.setBounds(30, 80, 170, 40);

        jManageStudent.setBackground(new java.awt.Color(40, 110, 210));
        jManageStudent.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jManageStudent.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jManageStudentMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jManageStudentMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jManageStudentMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jManageStudentMousePressed(evt);
            }
        });
        jManageStudent.setLayout(null);

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/student.png"))); // NOI18N
        jLabel9.setText("  Manage Teacher");
        jManageStudent.add(jLabel9);
        jLabel9.setBounds(30, 0, 170, 50);

        jPanel2.add(jManageStudent);
        jManageStudent.setBounds(0, 240, 220, 50);

        jLogoutButton.setBackground(new java.awt.Color(40, 110, 210));
        jLogoutButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLogoutButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLogoutButtonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLogoutButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLogoutButtonMouseExited(evt);
            }
        });
        jLogoutButton.setLayout(null);

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/logout.png"))); // NOI18N
        jLabel12.setText("  Logout");
        jLogoutButton.add(jLabel12);
        jLabel12.setBounds(30, 0, 130, 50);

        jPanel2.add(jLogoutButton);
        jLogoutButton.setBounds(0, 390, 220, 50);

        jHistoryLogs.setBackground(new java.awt.Color(40, 110, 210));
        jHistoryLogs.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jHistoryLogs.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jHistoryLogsMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jHistoryLogsMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jHistoryLogsMouseExited(evt);
            }
        });
        jHistoryLogs.setLayout(null);

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/student.png"))); // NOI18N
        jLabel11.setText("  History Logs");
        jHistoryLogs.add(jLabel11);
        jLabel11.setBounds(30, 0, 170, 50);

        jPanel2.add(jHistoryLogs);
        jHistoryLogs.setBounds(0, 340, 220, 50);

        jArchiveTeacher.setBackground(new java.awt.Color(40, 110, 210));
        jArchiveTeacher.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jArchiveTeacher.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jArchiveTeacherMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jArchiveTeacherMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jArchiveTeacherMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jArchiveTeacherMousePressed(evt);
            }
        });
        jArchiveTeacher.setLayout(null);

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/ARCHIEVE.png"))); // NOI18N
        jLabel13.setText("  Archive Teacher");
        jArchiveTeacher.add(jLabel13);
        jLabel13.setBounds(30, 0, 170, 50);

        jPanel2.add(jArchiveTeacher);
        jArchiveTeacher.setBounds(0, 290, 220, 50);

        jPanel1.add(jPanel2);
        jPanel2.setBounds(0, 0, 220, 700);

        jLabel8.setBackground(new java.awt.Color(25, 118, 211));
        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(25, 118, 211));
        jLabel8.setText("Archive Teachers");
        jPanel1.add(jLabel8);
        jLabel8.setBounds(230, 210, 190, 25);

        jteacher_Table.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jteacher_Table.setForeground(new java.awt.Color(51, 51, 51));
        jteacher_Table.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jScrollPane1.setViewportView(jteacher_Table);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(230, 300, 860, 380);

        jLabel7.setBackground(new java.awt.Color(0, 153, 255));
        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 40)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(40, 110, 210));
        jLabel7.setText("St. Cecilia's College - Cebu, Inc.");
        jPanel1.add(jLabel7);
        jLabel7.setBounds(360, 10, 600, 60);
        jPanel1.add(SCCLogo);
        SCCLogo.setBounds(250, 10, 90, 70);
        jPanel1.add(ITLogo);
        ITLogo.setBounds(980, 10, 70, 70);

        jError.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jError.setForeground(new java.awt.Color(255, 0, 0));
        jError.setText("   ");
        jPanel1.add(jError);
        jError.setBounds(860, 240, 130, 16);

        jApprove.setBackground(new java.awt.Color(51, 153, 255));
        jApprove.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jApprove.setForeground(new java.awt.Color(255, 255, 255));
        jApprove.setText("RESTORE");
        jApprove.setContentAreaFilled(false);
        jApprove.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jApprove.setFocusPainted(false);
        jApprove.setOpaque(true);
        jApprove.setPreferredSize(new java.awt.Dimension(85, 24));
        jApprove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jApproveActionPerformed(evt);
            }
        });
        jPanel1.add(jApprove);
        jApprove.setBounds(230, 260, 85, 24);

        jRefresh.setBackground(new java.awt.Color(51, 153, 255));
        jRefresh.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jRefresh.setForeground(new java.awt.Color(255, 255, 255));
        jRefresh.setText("REFRESH");
        jRefresh.setContentAreaFilled(false);
        jRefresh.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jRefresh.setFocusPainted(false);
        jRefresh.setOpaque(true);
        jRefresh.setPreferredSize(new java.awt.Dimension(85, 24));
        jRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRefreshActionPerformed(evt);
            }
        });
        jPanel1.add(jRefresh);
        jRefresh.setBounds(330, 260, 85, 24);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //dashboard button
    private void jDashboardMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jDashboardMouseClicked
        AdminDashBoard dashboardFrame = new AdminDashBoard();
        dashboardFrame.setVisible(true);
        dashboardFrame.pack();
        dashboardFrame.setLocationRelativeTo(null);
        this.dispose();
    }//GEN-LAST:event_jDashboardMouseClicked

    //manage teacher button
    private void jManageStudentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jManageStudentMouseClicked
        AdminManageTeacher manageTeacherFrame = new AdminManageTeacher();
        manageTeacherFrame.setVisible(true);
        manageTeacherFrame.pack();
        manageTeacherFrame.setLocationRelativeTo(null);
        this.dispose();
    }//GEN-LAST:event_jManageStudentMouseClicked

    private void jManageStudentMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jManageStudentMousePressed
        setColor(jManageStudent);
    }//GEN-LAST:event_jManageStudentMousePressed

    private void jDashboardMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jDashboardMouseEntered
        setColor(jDashboard);
    }//GEN-LAST:event_jDashboardMouseEntered

    private void jDashboardMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jDashboardMouseExited
        resetColor(jDashboard);
    }//GEN-LAST:event_jDashboardMouseExited

    private void jManageStudentMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jManageStudentMouseEntered
        setColor(jManageStudent);
    }//GEN-LAST:event_jManageStudentMouseEntered

    private void jManageStudentMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jManageStudentMouseExited
        resetColor(jManageStudent);
    }//GEN-LAST:event_jManageStudentMouseExited

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        displayNameAndRole();
    }//GEN-LAST:event_formWindowActivated

    private void jLogoutButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLogoutButtonMouseClicked
        int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to logout?", "", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION){
            //history logs
            LogsHistory logHistory = new LogsHistory();
            logHistory.insertAdminLog(adminID, "Admin Logout");

            LoginPage loginFrame = new LoginPage();
            loginFrame.setVisible(true);
            loginFrame.pack();
            loginFrame.setLocationRelativeTo(null);
            this.dispose();
        }
    }//GEN-LAST:event_jLogoutButtonMouseClicked

    private void jLogoutButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLogoutButtonMouseEntered
        setColor(jLogoutButton);
    }//GEN-LAST:event_jLogoutButtonMouseEntered

    private void jLogoutButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLogoutButtonMouseExited
        resetColor(jLogoutButton);
    }//GEN-LAST:event_jLogoutButtonMouseExited
    //approve button
    private void jApproveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jApproveActionPerformed
        restoreSelectedRow();
    }//GEN-LAST:event_jApproveActionPerformed

    private void jHistoryLogsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jHistoryLogsMouseClicked
        AdminTeacherHistoryLogs logsFrame = new AdminTeacherHistoryLogs();
        logsFrame.setVisible(true);
        logsFrame.pack();
        logsFrame.setLocationRelativeTo(null);
        this.dispose();
    }//GEN-LAST:event_jHistoryLogsMouseClicked

    private void jHistoryLogsMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jHistoryLogsMouseEntered
        setColor(jHistoryLogs);
    }//GEN-LAST:event_jHistoryLogsMouseEntered

    private void jHistoryLogsMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jHistoryLogsMouseExited
        resetColor(jHistoryLogs);
    }//GEN-LAST:event_jHistoryLogsMouseExited

    private void jArchiveTeacherMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jArchiveTeacherMouseClicked
        AdminTeacherArchive archiveFrame = new AdminTeacherArchive();
        archiveFrame.setVisible(true);
        archiveFrame.pack();
        archiveFrame.setLocationRelativeTo(null);
        this.dispose();
    }//GEN-LAST:event_jArchiveTeacherMouseClicked

    private void jArchiveTeacherMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jArchiveTeacherMouseEntered
        setColor(jArchiveTeacher);
    }//GEN-LAST:event_jArchiveTeacherMouseEntered

    private void jArchiveTeacherMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jArchiveTeacherMouseExited
        resetColor(jArchiveTeacher);
    }//GEN-LAST:event_jArchiveTeacherMouseExited

    private void jArchiveTeacherMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jArchiveTeacherMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jArchiveTeacherMousePressed

    private void jRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRefreshActionPerformed
        displayDataTable();
    }//GEN-LAST:event_jRefreshActionPerformed

    
    
    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdminTeacherArchive().setVisible(true);
            }
        });
        
        
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ITLogo;
    private javax.swing.JLabel SCCLogo;
    private javax.swing.JLabel datetime;
    public javax.swing.JLabel jAdminName;
    private javax.swing.JButton jApprove;
    private javax.swing.JPanel jArchiveTeacher;
    private javax.swing.JPanel jDashboard;
    private javax.swing.JLabel jError;
    private javax.swing.JPanel jHistoryLogs;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jLogoutButton;
    private javax.swing.JPanel jManageStudent;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JButton jRefresh;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jteacher_Table;
    // End of variables declaration//GEN-END:variables
}
