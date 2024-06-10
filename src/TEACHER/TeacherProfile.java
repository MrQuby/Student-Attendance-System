
package TEACHER;


import config.LogsHistory;
import config.Session;
import java.awt.Color;
import java.awt.Image;
import javax.swing.JPanel;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import student.attendance.system.LoginPage;



public class TeacherProfile extends javax.swing.JFrame {


    
    public TeacherProfile() {
        initComponents();
        ourDateTime();
        ImageIcon  image2 = new ImageIcon(new ImageIcon("src/icon/IT_logo.png").getImage().getScaledInstance(ITLogo.getWidth(), ITLogo.getHeight(), Image.SCALE_SMOOTH));
        ITLogo.setIcon(image2);
        ImageIcon  image3 = new ImageIcon(new ImageIcon("src/icon/scc logo.png").getImage().getScaledInstance(SCCLogo.getWidth(), SCCLogo.getHeight(), Image.SCALE_SMOOTH));
        SCCLogo.setIcon(image3);
    }

    //show date and time
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
        Session session = Session.getInstance();
        String name = session.getFullname();
        jAdminName.setText(name);
    }
    
    
    public void setColor(JPanel panel) {
        panel.setBackground(new Color(80,140,210));
    }
    
    public void resetColor(JPanel panel) {
        panel.setBackground(new Color(40,110,210));
    }
    //display profile info
    public void displayProfileData(){
        Session session = Session.getInstance();
        jFullName.setText("" + session.getFullname());
        jIDNumber.setText("" + session.getId());
        jGender.setText("" + session.getGender());
        jBirthDate.setText("" + session.getBirthdate());
        jContactNumber.setText("" + session.getContactNumber());
        jAddress.setText("" + session.getAddress());
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jProfileSettings = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jDashboard = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jAttendance = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jStudent = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLogoutButton = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jArchiveStudents = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jAdminName = new javax.swing.JLabel();
        datetime = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jBirthDate = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jFullName = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jContactNumber = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jButtonChangePassword = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jAddress = new javax.swing.JLabel();
        jGender = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jIDNumber = new javax.swing.JLabel();
        jButtonUpdateInfo = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        SCCLogo = new javax.swing.JLabel();
        ITLogo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("TEACHER PROFILE");
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

        jProfileSettings.setBackground(new java.awt.Color(40, 110, 210));
        jProfileSettings.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jProfileSettings.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                jProfileSettingsAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        jProfileSettings.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jProfileSettingsMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jProfileSettingsMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jProfileSettingsMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jProfileSettingsMousePressed(evt);
            }
        });
        jProfileSettings.setLayout(null);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/profile (2).png"))); // NOI18N
        jLabel4.setText("  Profile Settings");
        jProfileSettings.add(jLabel4);
        jLabel4.setBounds(30, 0, 170, 50);

        jPanel2.add(jProfileSettings);
        jProfileSettings.setBounds(0, 390, 220, 50);

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
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jDashboardMousePressed(evt);
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

        jAttendance.setBackground(new java.awt.Color(40, 110, 210));
        jAttendance.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jAttendance.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jAttendanceMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jAttendanceMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jAttendanceMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jAttendanceMousePressed(evt);
            }
        });
        jAttendance.setLayout(null);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/attendance.png"))); // NOI18N
        jLabel5.setText("  Attendance");
        jAttendance.add(jLabel5);
        jLabel5.setBounds(30, 0, 130, 50);

        jPanel2.add(jAttendance);
        jAttendance.setBounds(0, 240, 220, 50);

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

        jStudent.setBackground(new java.awt.Color(40, 110, 210));
        jStudent.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jStudent.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jStudentMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jStudentMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jStudentMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jStudentMousePressed(evt);
            }
        });
        jStudent.setLayout(null);

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/student.png"))); // NOI18N
        jLabel9.setText("  Manage Student");
        jStudent.add(jLabel9);
        jLabel9.setBounds(30, 0, 170, 50);

        jPanel2.add(jStudent);
        jStudent.setBounds(0, 290, 220, 50);

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
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLogoutButtonMousePressed(evt);
            }
        });
        jLogoutButton.setLayout(null);

        jLabel19.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/logout.png"))); // NOI18N
        jLabel19.setText("  Logout");
        jLogoutButton.add(jLabel19);
        jLabel19.setBounds(30, 0, 130, 50);

        jPanel2.add(jLogoutButton);
        jLogoutButton.setBounds(0, 440, 220, 50);

        jArchiveStudents.setBackground(new java.awt.Color(40, 110, 210));
        jArchiveStudents.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jArchiveStudents.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jArchiveStudentsMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jArchiveStudentsMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jArchiveStudentsMouseExited(evt);
            }
        });
        jArchiveStudents.setLayout(null);

        jLabel21.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/ARCHIEVE.png"))); // NOI18N
        jLabel21.setText("  Archive Student");
        jArchiveStudents.add(jLabel21);
        jLabel21.setBounds(30, 0, 170, 50);

        jPanel2.add(jArchiveStudents);
        jArchiveStudents.setBounds(0, 340, 220, 50);

        jPanel1.add(jPanel2);
        jPanel2.setBounds(0, 0, 220, 700);

        jPanel3.setBackground(new java.awt.Color(25, 118, 211));
        jPanel3.setLayout(null);

        jLabel10.setFont(new java.awt.Font("Segoe UI Semibold", 0, 20)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Teacher:");
        jPanel3.add(jLabel10);
        jLabel10.setBounds(20, 30, 80, 30);

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

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(25, 118, 211));
        jLabel8.setText("Name");
        jPanel1.add(jLabel8);
        jLabel8.setBounds(310, 320, 120, 30);

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(153, 204, 255));
        jLabel20.setText("__________________________________________________");
        jPanel1.add(jLabel20);
        jLabel20.setBounds(460, 410, 310, 20);

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(153, 204, 255));
        jLabel11.setText("__________________________________________________");
        jPanel1.add(jLabel11);
        jLabel11.setBounds(460, 330, 310, 20);

        jBirthDate.setFont(new java.awt.Font("Segoe UI Semilight", 1, 16)); // NOI18N
        jBirthDate.setForeground(new java.awt.Color(25, 118, 211));
        jBirthDate.setText("Date of Birth");
        jPanel1.add(jBirthDate);
        jBirthDate.setBounds(460, 430, 300, 50);

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(25, 118, 211));
        jLabel12.setText("Address");
        jPanel1.add(jLabel12);
        jLabel12.setBounds(310, 520, 120, 30);

        jFullName.setFont(new java.awt.Font("Segoe UI Semilight", 1, 16)); // NOI18N
        jFullName.setForeground(new java.awt.Color(25, 118, 211));
        jFullName.setText("Full Name");
        jPanel1.add(jFullName);
        jFullName.setBounds(460, 310, 300, 50);

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(25, 118, 211));
        jLabel13.setText("ID Number");
        jPanel1.add(jLabel13);
        jLabel13.setBounds(310, 360, 120, 30);

        jContactNumber.setFont(new java.awt.Font("Segoe UI Semilight", 1, 16)); // NOI18N
        jContactNumber.setForeground(new java.awt.Color(25, 118, 211));
        jContactNumber.setText("Contact Number");
        jPanel1.add(jContactNumber);
        jContactNumber.setBounds(460, 470, 300, 50);

        jLabel22.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(153, 204, 255));
        jLabel22.setText("__________________________________________________");
        jPanel1.add(jLabel22);
        jLabel22.setBounds(460, 450, 310, 20);

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(25, 118, 211));
        jLabel14.setText("Gender");
        jPanel1.add(jLabel14);
        jLabel14.setBounds(310, 400, 120, 30);

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(25, 118, 211));
        jLabel15.setText("Date of Birth");
        jPanel1.add(jLabel15);
        jLabel15.setBounds(310, 440, 120, 30);

        jButtonChangePassword.setBackground(new java.awt.Color(25, 118, 211));
        jButtonChangePassword.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButtonChangePassword.setForeground(new java.awt.Color(255, 255, 255));
        jButtonChangePassword.setText("Change Password");
        jButtonChangePassword.setContentAreaFilled(false);
        jButtonChangePassword.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonChangePassword.setOpaque(true);
        jButtonChangePassword.setRequestFocusEnabled(false);
        jButtonChangePassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonChangePasswordActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonChangePassword);
        jButtonChangePassword.setBounds(500, 620, 160, 30);

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(25, 118, 211));
        jLabel16.setText("PROFILE INFORMATION");
        jPanel1.add(jLabel16);
        jLabel16.setBounds(310, 240, 300, 30);

        jLabel26.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(153, 204, 255));
        jLabel26.setText("__________________________________________________");
        jPanel1.add(jLabel26);
        jLabel26.setBounds(460, 530, 310, 20);

        jAddress.setFont(new java.awt.Font("Segoe UI Semilight", 1, 16)); // NOI18N
        jAddress.setForeground(new java.awt.Color(25, 118, 211));
        jAddress.setText("Address");
        jPanel1.add(jAddress);
        jAddress.setBounds(460, 510, 300, 50);

        jGender.setFont(new java.awt.Font("Segoe UI Semilight", 1, 16)); // NOI18N
        jGender.setForeground(new java.awt.Color(25, 118, 211));
        jGender.setText("Gender");
        jPanel1.add(jGender);
        jGender.setBounds(460, 390, 300, 50);

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(25, 118, 211));
        jLabel17.setText("Contact Number");
        jPanel1.add(jLabel17);
        jLabel17.setBounds(310, 480, 130, 30);

        jLabel24.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(153, 204, 255));
        jLabel24.setText("__________________________________________________");
        jPanel1.add(jLabel24);
        jLabel24.setBounds(460, 490, 310, 20);

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(153, 204, 255));
        jLabel18.setText("__________________________________________________");
        jPanel1.add(jLabel18);
        jLabel18.setBounds(460, 370, 310, 20);

        jIDNumber.setFont(new java.awt.Font("Segoe UI Semilight", 1, 16)); // NOI18N
        jIDNumber.setForeground(new java.awt.Color(25, 118, 211));
        jIDNumber.setText("ID Number");
        jPanel1.add(jIDNumber);
        jIDNumber.setBounds(460, 350, 300, 50);

        jButtonUpdateInfo.setBackground(new java.awt.Color(25, 118, 211));
        jButtonUpdateInfo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButtonUpdateInfo.setForeground(new java.awt.Color(255, 255, 255));
        jButtonUpdateInfo.setText("Update Info");
        jButtonUpdateInfo.setContentAreaFilled(false);
        jButtonUpdateInfo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonUpdateInfo.setOpaque(true);
        jButtonUpdateInfo.setRequestFocusEnabled(false);
        jButtonUpdateInfo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUpdateInfoActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonUpdateInfo);
        jButtonUpdateInfo.setBounds(310, 620, 160, 30);

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

    //profile settings button
    private void jProfileSettingsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jProfileSettingsMouseClicked
        TeacherProfile profileFrame = new TeacherProfile();
        profileFrame.setVisible(true);
        profileFrame.pack();
        profileFrame.setLocationRelativeTo(null);
        this.dispose();
    }//GEN-LAST:event_jProfileSettingsMouseClicked

    //dashboard button
    private void jDashboardMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jDashboardMouseClicked
        TeacherDashBoard dashboardFrame = new TeacherDashBoard();
        dashboardFrame.setVisible(true);
        dashboardFrame.pack();
        dashboardFrame.setLocationRelativeTo(null);
        this.dispose();
    }//GEN-LAST:event_jDashboardMouseClicked

    //attendance button
    private void jAttendanceMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jAttendanceMouseClicked
        TeacherAttendance attendanceFrame = new TeacherAttendance();
        attendanceFrame.setVisible(true);
        attendanceFrame.pack();
        attendanceFrame.setLocationRelativeTo(null);
        this.dispose();
    }//GEN-LAST:event_jAttendanceMouseClicked

    private void jDashboardMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jDashboardMousePressed
        
    }//GEN-LAST:event_jDashboardMousePressed

    private void jAttendanceMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jAttendanceMousePressed
        
    }//GEN-LAST:event_jAttendanceMousePressed

    private void jProfileSettingsMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jProfileSettingsMousePressed
        
    }//GEN-LAST:event_jProfileSettingsMousePressed

    //manage student button
    private void jStudentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jStudentMouseClicked
        TeacherManageStudent attendanceFrame = new TeacherManageStudent();
        attendanceFrame.setVisible(true);
        attendanceFrame.pack();
        attendanceFrame.setLocationRelativeTo(null);
        this.dispose();
    }//GEN-LAST:event_jStudentMouseClicked

    private void jStudentMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jStudentMousePressed
        setColor(jStudent);
    }//GEN-LAST:event_jStudentMousePressed

    private void jDashboardMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jDashboardMouseEntered
        setColor(jDashboard);
    }//GEN-LAST:event_jDashboardMouseEntered

    private void jDashboardMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jDashboardMouseExited
        resetColor(jDashboard);
    }//GEN-LAST:event_jDashboardMouseExited

    private void jAttendanceMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jAttendanceMouseEntered
        setColor(jAttendance);
    }//GEN-LAST:event_jAttendanceMouseEntered

    private void jAttendanceMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jAttendanceMouseExited
        resetColor(jAttendance);
    }//GEN-LAST:event_jAttendanceMouseExited

    private void jStudentMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jStudentMouseEntered
        setColor(jStudent);
    }//GEN-LAST:event_jStudentMouseEntered

    private void jStudentMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jStudentMouseExited
        resetColor(jStudent);
    }//GEN-LAST:event_jStudentMouseExited

    private void jProfileSettingsMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jProfileSettingsMouseEntered
        setColor(jProfileSettings);
    }//GEN-LAST:event_jProfileSettingsMouseEntered

    private void jProfileSettingsMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jProfileSettingsMouseExited
        resetColor(jProfileSettings);
    }//GEN-LAST:event_jProfileSettingsMouseExited

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        displayProfileData();
        displayNameAndRole();
    }//GEN-LAST:event_formWindowActivated

    private void jProfileSettingsAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jProfileSettingsAncestorAdded
        
    }//GEN-LAST:event_jProfileSettingsAncestorAdded

    private void jButtonUpdateInfoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUpdateInfoActionPerformed
        UpdateInfo updateFrame = new UpdateInfo();
        updateFrame.setVisible(true);
        updateFrame.pack();
        updateFrame.setLocationRelativeTo(null);
        this.dispose();
    }//GEN-LAST:event_jButtonUpdateInfoActionPerformed

    //logout button
    private void jLogoutButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLogoutButtonMouseClicked
        int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to logout?", "", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION){
            //history logs
            Session session = Session.getInstance();
            int id = session.getId();
            LogsHistory logHistory = new LogsHistory();
            logHistory.insertTeacherLog(id, "Teacher Logout");
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

    private void jLogoutButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLogoutButtonMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jLogoutButtonMousePressed

    private void jButtonChangePasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonChangePasswordActionPerformed
        ChangePassword passwordFrame = new ChangePassword();
        passwordFrame.setVisible(true);
        passwordFrame.pack();
        passwordFrame.setLocationRelativeTo(null);
        this.dispose();
    }//GEN-LAST:event_jButtonChangePasswordActionPerformed

    private void jArchiveStudentsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jArchiveStudentsMouseClicked
        TeacherArchiveStudent archiveFrame = new TeacherArchiveStudent();
        archiveFrame.setVisible(true);
        archiveFrame.pack();
        archiveFrame.setLocationRelativeTo(null);
        this.dispose();
    }//GEN-LAST:event_jArchiveStudentsMouseClicked

    private void jArchiveStudentsMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jArchiveStudentsMouseEntered
        setColor(jArchiveStudents);
    }//GEN-LAST:event_jArchiveStudentsMouseEntered

    private void jArchiveStudentsMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jArchiveStudentsMouseExited
        resetColor(jArchiveStudents);
    }//GEN-LAST:event_jArchiveStudentsMouseExited

    
    
    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TeacherProfile().setVisible(true);
            }
        });
        
        
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ITLogo;
    private javax.swing.JLabel SCCLogo;
    private javax.swing.JLabel datetime;
    public javax.swing.JLabel jAddress;
    public javax.swing.JLabel jAdminName;
    private javax.swing.JPanel jArchiveStudents;
    private javax.swing.JPanel jAttendance;
    public javax.swing.JLabel jBirthDate;
    private javax.swing.JButton jButtonChangePassword;
    private javax.swing.JButton jButtonUpdateInfo;
    public javax.swing.JLabel jContactNumber;
    private javax.swing.JPanel jDashboard;
    public javax.swing.JLabel jFullName;
    public javax.swing.JLabel jGender;
    public javax.swing.JLabel jIDNumber;
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
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jLogoutButton;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jProfileSettings;
    private javax.swing.JPanel jStudent;
    // End of variables declaration//GEN-END:variables
}
