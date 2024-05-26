
package ADMIN;



import config.LogsHistory;
import config.Session;
import config.myConnection;
import java.awt.Color;
import java.awt.Image;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import javax.swing.JPanel;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import student.attendance.system.LoginPage;



public class AdminDashBoard extends javax.swing.JFrame {

    Session session = Session.getInstance();
    int adminID = session.getId();
    
    public AdminDashBoard() {
        initComponents();
        ourDateTime(); //show date and time
        //default image logo
        ImageIcon  image2 = new ImageIcon(new ImageIcon("src/icon/IT_logo.png").getImage().getScaledInstance(ITLogo.getWidth(), ITLogo.getHeight(), Image.SCALE_SMOOTH));
        ITLogo.setIcon(image2);
        ImageIcon  image3 = new ImageIcon(new ImageIcon("src/icon/scc logo.png").getImage().getScaledInstance(SCCLogo.getWidth(), SCCLogo.getHeight(), Image.SCALE_SMOOTH));
        SCCLogo.setIcon(image3);
    }

    //display date and time
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
    
    //display name and role
    public void displayNameAndRole(){
        jAdminName.setText("ADMIN");
    }
    
    //panel set color
    public void setColor(JPanel panel) {
        panel.setBackground(new Color(80,140,210));
    }
    
    //panel reset color
    public void resetColor(JPanel panel) {
        panel.setBackground(new Color(40,110,210));
    }
    
    //get number of students
    public int getNumberOfStudents() {
        int studentCount = 0;
        myConnection connector = new myConnection();
        try {
            String query = "SELECT COUNT(*) AS student_count FROM student WHERE student_archive = 'NO'";
            ResultSet resultset = connector.getData(query);
            if (resultset.next()) {
                studentCount = resultset.getInt("student_count");
                connector.closeConnection();
            }
        } catch (SQLException ex) {
            System.err.println("Error fetching student count: " + ex.getMessage());
        }
        return studentCount;
    }
    
    //get number of teacher
    public int getNumberOfTeachers() {
        int studentCount = 0;
        myConnection connector = new myConnection();
        try {
            String query = "SELECT COUNT(*) AS teacher_count FROM teacher WHERE teacher_status = 'ACTIVE'";
            ResultSet resultset = connector.getData(query);
            if (resultset.next()) {
                studentCount = resultset.getInt("teacher_count");
                connector.closeConnection();
            }
        } catch (SQLException ex) {
            System.err.println("Error fetching teacher count: " + ex.getMessage());
        }
        return studentCount;
    }
    
    //get number of today attendance
    public int getNumberOfTodayAttendance() {
        LocalDate today = LocalDate.now();
        String currentDate = today.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        int studentCount = 0;
        myConnection connector = new myConnection();
        try {
            String query = "SELECT COUNT(*) AS attendance_count FROM attendance_table WHERE date = '" + currentDate + "'";
            ResultSet resultset = connector.getData(query);
            if (resultset.next()) {
                studentCount = resultset.getInt("attendance_count");
                connector.closeConnection();
            }
        } catch (SQLException ex) {
            System.err.println("Error fetching attendance count: " + ex.getMessage());
        }
        return studentCount;
    }
    

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jDashboard = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jManageTeacher = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLogoutButton = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jHistoryLogs = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jArchiveTeacher = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jAdminName = new javax.swing.JLabel();
        datetime = new javax.swing.JLabel();
        jClasses = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jTeacherNumber = new javax.swing.JLabel();
        jStudents = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jStudentNumber = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jTodayAttendance = new javax.swing.JLabel();
        ITLogo = new javax.swing.JLabel();
        SCCLogo = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("TEACHER DASHBOARD");
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

        jPanel2.setBackground(new java.awt.Color(25, 118, 211));
        jPanel2.setLayout(null);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-admin-80 (2).png"))); // NOI18N
        jPanel2.add(jLabel1);
        jLabel1.setBounds(60, -10, 90, 111);

        jDashboard.setBackground(new java.awt.Color(40, 110, 210));
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

        jManageTeacher.setBackground(new java.awt.Color(40, 110, 210));
        jManageTeacher.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jManageTeacherMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jManageTeacherMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jManageTeacherMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jManageTeacherMousePressed(evt);
            }
        });
        jManageTeacher.setLayout(null);

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/student.png"))); // NOI18N
        jLabel9.setText("  Manage Teacher");
        jManageTeacher.add(jLabel9);
        jLabel9.setBounds(30, 0, 170, 50);

        jPanel2.add(jManageTeacher);
        jManageTeacher.setBounds(0, 240, 220, 50);

        jLogoutButton.setBackground(new java.awt.Color(40, 110, 210));
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

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/logout.png"))); // NOI18N
        jLabel8.setText("  Logout");
        jLogoutButton.add(jLabel8);
        jLabel8.setBounds(30, 0, 130, 50);

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

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/student.png"))); // NOI18N
        jLabel12.setText("  History Logs");
        jHistoryLogs.add(jLabel12);
        jLabel12.setBounds(30, 0, 170, 50);

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

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/ARCHIEVE.png"))); // NOI18N
        jLabel18.setText("  Archive Teacher");
        jArchiveTeacher.add(jLabel18);
        jLabel18.setBounds(30, 0, 170, 50);

        jPanel2.add(jArchiveTeacher);
        jArchiveTeacher.setBounds(0, 290, 220, 50);

        jPanel1.add(jPanel2);
        jPanel2.setBounds(0, 0, 220, 700);

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

        jClasses.setBackground(new java.awt.Color(25, 118, 211));
        jClasses.setLayout(null);

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Teachers");
        jClasses.add(jLabel13);
        jLabel13.setBounds(60, 60, 60, 20);

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/teacher.png"))); // NOI18N
        jClasses.add(jLabel14);
        jLabel14.setBounds(170, 20, 70, 80);

        jTeacherNumber.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jTeacherNumber.setForeground(new java.awt.Color(255, 255, 255));
        jClasses.add(jTeacherNumber);
        jTeacherNumber.setBounds(60, 20, 60, 30);

        jPanel1.add(jClasses);
        jClasses.setBounds(710, 290, 280, 110);

        jStudents.setBackground(new java.awt.Color(25, 118, 211));
        jStudents.setLayout(null);

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Students.png"))); // NOI18N
        jStudents.add(jLabel11);
        jLabel11.setBounds(160, 10, 70, 80);

        jStudentNumber.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jStudentNumber.setForeground(new java.awt.Color(255, 255, 255));
        jStudents.add(jStudentNumber);
        jStudentNumber.setBounds(50, 20, 60, 30);

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Students");
        jStudents.add(jLabel17);
        jLabel17.setBounds(50, 60, 60, 20);

        jPanel1.add(jStudents);
        jStudents.setBounds(320, 290, 280, 110);

        jPanel6.setBackground(new java.awt.Color(25, 118, 211));
        jPanel6.setLayout(null);

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/attendances.png"))); // NOI18N
        jPanel6.add(jLabel15);
        jLabel15.setBounds(190, 20, 70, 80);

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Student Attendance");
        jPanel6.add(jLabel16);
        jLabel16.setBounds(30, 60, 150, 20);

        jTodayAttendance.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jTodayAttendance.setForeground(new java.awt.Color(255, 255, 255));
        jPanel6.add(jTodayAttendance);
        jTodayAttendance.setBounds(60, 20, 70, 30);

        jPanel1.add(jPanel6);
        jPanel6.setBounds(520, 470, 280, 110);
        jPanel1.add(ITLogo);
        ITLogo.setBounds(990, 10, 70, 70);
        jPanel1.add(SCCLogo);
        SCCLogo.setBounds(250, 10, 90, 70);

        jLabel7.setBackground(new java.awt.Color(0, 153, 255));
        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 40)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(40, 110, 210));
        jLabel7.setText("St. Cecilia's College - Cebu, Inc.");
        jPanel1.add(jLabel7);
        jLabel7.setBounds(360, 10, 600, 60);

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
    private void jManageTeacherMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jManageTeacherMouseClicked
        AdminManageTeacher manageTeacherFrame = new AdminManageTeacher();
        manageTeacherFrame.setVisible(true);
        manageTeacherFrame.pack();
        manageTeacherFrame.setLocationRelativeTo(null);
        this.dispose();
    }//GEN-LAST:event_jManageTeacherMouseClicked

    private void jManageTeacherMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jManageTeacherMousePressed
        setColor(jManageTeacher);
    }//GEN-LAST:event_jManageTeacherMousePressed

    private void jDashboardMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jDashboardMouseEntered
        setColor(jDashboard);
    }//GEN-LAST:event_jDashboardMouseEntered

    private void jDashboardMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jDashboardMouseExited
        resetColor(jDashboard);
    }//GEN-LAST:event_jDashboardMouseExited

    private void jManageTeacherMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jManageTeacherMouseEntered
        setColor(jManageTeacher);
    }//GEN-LAST:event_jManageTeacherMouseEntered

    private void jManageTeacherMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jManageTeacherMouseExited
        resetColor(jManageTeacher);
    }//GEN-LAST:event_jManageTeacherMouseExited

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        displayNameAndRole();
        jStudentNumber.setHorizontalAlignment(SwingConstants.CENTER);
        jStudentNumber.setText("" + getNumberOfStudents());
        jTeacherNumber.setHorizontalAlignment(SwingConstants.CENTER);
        jTeacherNumber.setText("" + getNumberOfTeachers());
        jTodayAttendance.setHorizontalAlignment(SwingConstants.CENTER);
        jTodayAttendance.setText("" + getNumberOfTodayAttendance());
    }//GEN-LAST:event_formWindowActivated

    //logout button
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
    //history logs button
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

    
    
    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdminDashBoard().setVisible(true);
            }
        });
        
        
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ITLogo;
    private javax.swing.JLabel SCCLogo;
    private javax.swing.JLabel datetime;
    public javax.swing.JLabel jAdminName;
    private javax.swing.JPanel jArchiveTeacher;
    private javax.swing.JPanel jClasses;
    private javax.swing.JPanel jDashboard;
    private javax.swing.JPanel jHistoryLogs;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jLogoutButton;
    private javax.swing.JPanel jManageTeacher;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JLabel jStudentNumber;
    private javax.swing.JPanel jStudents;
    private javax.swing.JLabel jTeacherNumber;
    private javax.swing.JLabel jTodayAttendance;
    // End of variables declaration//GEN-END:variables
}
