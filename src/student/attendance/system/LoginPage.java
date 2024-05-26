
package student.attendance.system;



import ADMIN.AdminDashBoard;
import STUDENTS.StudentAttendance;
import TEACHER.TeacherDashBoard;
import config.HashingPassword;
import config.LogsHistory;
import config.Session;
import config.myConnection;
import java.awt.Color;
import java.awt.Image;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;


public class LoginPage extends javax.swing.JFrame {

    
    public LoginPage() {
        initComponents();
        ImageIcon  image2 = new ImageIcon(new ImageIcon("src/icon/IT_logo.png").getImage().getScaledInstance(logo.getWidth(), logo.getHeight(), Image.SCALE_SMOOTH));
        logo.setIcon(image2);
    }
    
    private void showSuccessDialog(String message) {
        UIManager.put("Button.focus", new ColorUIResource(new Color(0,0,0,0)));
        JOptionPane.showMessageDialog(null, message, "", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static String loginAccountStatus(int id, String password){
        myConnection connector = new myConnection();
        try{
            String query = "SELECT * FROM teacher  WHERE teacher_id = '" + id + "' AND teacher_password = '" + password + "'";
            ResultSet resultSet = connector.getData(query);
            if(resultSet.next()) {
                Session session = Session.getInstance();
                session.getData(id);
                return resultSet.getString("teacher_status");
            }else{
                return null;
            }
        }catch (SQLException ex) {
            System.out.println("Invalid Connection" + ex.getMessage());
            return null;
        }
    }
    
    public static String loginAccountArchive(int id, String password){
        myConnection connector = new myConnection();
        try{
            String query = "SELECT * FROM teacher  WHERE teacher_id = '" + id + "' AND teacher_password = '" + password + "'";
            ResultSet resultSet = connector.getData(query);
            if(resultSet.next()) {
                Session session = Session.getInstance();
                session.getData(id);
                return resultSet.getString("teacher_archive");
            }else{
                return null;
            }
        }catch (SQLException ex) {
            System.out.println("Invalid Connection" + ex.getMessage());
            return null;
        }
    }
    
    //check if admin id and pass is correct
    public static boolean loginAccountAdmin(int id, String password){
        myConnection connector = new myConnection();
        try{
            String query = "SELECT * FROM admin  WHERE admin_id = '" + id + "' AND admin_password = '" + password + "'";
            ResultSet resultSet = connector.getData(query);
            if(resultSet.next()) {
                return true;
            }else{
                return false;
            }
        }catch (SQLException ex) {
            System.out.println("Invalid Connection" + ex.getMessage());
            return false;
        }
    }
    
    public void setColor(JPanel panel) {
        panel.setBackground(new Color(80,140,210));
    }
    
    public void resetColor(JPanel panel) {
        panel.setBackground(new Color(255,255,255));
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jID = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jPassword = new javax.swing.JPasswordField();
        jButtonSignup = new javax.swing.JButton();
        jButtonLogin = new javax.swing.JButton();
        logo = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        disable = new javax.swing.JLabel();
        show = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        close = new javax.swing.JPanel();
        jClose = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jError = new javax.swing.JLabel();
        attendanceBackground = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("LOGIN PAGE");
        setUndecorated(true);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(25, 118, 211));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(25, 118, 211), 2));
        jPanel1.setForeground(new java.awt.Color(25, 118, 211));
        jPanel1.setPreferredSize(new java.awt.Dimension(360, 500));
        jPanel1.setLayout(null);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Password");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(30, 270, 80, 20);

        jID.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jID.setForeground(new java.awt.Color(255, 255, 255));
        jID.setBorder(null);
        jID.setCaretColor(new java.awt.Color(255, 255, 255));
        jID.setOpaque(false);
        jID.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jIDMouseClicked(evt);
            }
        });
        jID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jIDActionPerformed(evt);
            }
        });
        jPanel1.add(jID);
        jID.setBounds(60, 200, 270, 40);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("User ID");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(30, 180, 80, 20);

        jPassword.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jPassword.setForeground(new java.awt.Color(255, 255, 255));
        jPassword.setBorder(null);
        jPassword.setCaretColor(new java.awt.Color(255, 255, 255));
        jPassword.setOpaque(false);
        jPassword.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPasswordMouseClicked(evt);
            }
        });
        jPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPasswordActionPerformed(evt);
            }
        });
        jPanel1.add(jPassword);
        jPassword.setBounds(60, 290, 270, 40);

        jButtonSignup.setBackground(new java.awt.Color(255, 255, 255));
        jButtonSignup.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButtonSignup.setForeground(new java.awt.Color(255, 51, 51));
        jButtonSignup.setText("SIGNUP");
        jButtonSignup.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonSignup.setFocusPainted(false);
        jButtonSignup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSignupActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonSignup);
        jButtonSignup.setBounds(200, 360, 130, 40);

        jButtonLogin.setBackground(new java.awt.Color(255, 255, 255));
        jButtonLogin.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButtonLogin.setForeground(new java.awt.Color(0, 153, 255));
        jButtonLogin.setText("LOGIN");
        jButtonLogin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonLogin.setFocusPainted(false);
        jButtonLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLoginActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonLogin);
        jButtonLogin.setBounds(30, 360, 130, 40);
        jPanel1.add(logo);
        logo.setBounds(147, 50, 70, 70);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("__________________________________________________");
        jPanel1.add(jLabel5);
        jLabel5.setBounds(30, 210, 310, 40);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("__________________________________________________");
        jPanel1.add(jLabel6);
        jLabel6.setBounds(30, 300, 310, 40);

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_user_20px_1.png"))); // NOI18N
        jPanel1.add(jLabel7);
        jLabel7.setBounds(30, 210, 20, 20);

        disable.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_invisible_20px_1.png"))); // NOI18N
        disable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                disableMouseClicked(evt);
            }
        });
        jPanel1.add(disable);
        disable.setBounds(30, 300, 20, 20);

        show.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_eye_20px_1.png"))); // NOI18N
        show.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                showMouseClicked(evt);
            }
        });
        jPanel1.add(show);
        show.setBounds(30, 300, 20, 20);

        jLabel10.setBackground(new java.awt.Color(255, 255, 255));
        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("ST. CECILIAS COLLEGE");
        jPanel1.add(jLabel10);
        jLabel10.setBounds(110, 130, 160, 20);

        close.setBackground(new java.awt.Color(255, 255, 255));
        close.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                closeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                closeMouseExited(evt);
            }
        });
        close.setLayout(null);

        jClose.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jClose.setForeground(new java.awt.Color(255, 0, 51));
        jClose.setText("   X");
        jClose.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jClose.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jCloseMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jCloseMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jCloseMouseExited(evt);
            }
        });
        jClose.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jCloseKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jCloseKeyReleased(evt);
            }
        });
        close.add(jClose);
        jClose.setBounds(0, 0, 30, 30);

        jPanel1.add(close);
        close.setBounds(327, 2, 31, 31);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(25, 118, 211), 2));
        jPanel2.setLayout(null);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(25, 118, 211));
        jLabel4.setText("USER LOGIN");
        jPanel2.add(jLabel4);
        jLabel4.setBounds(140, 0, 90, 35);

        jPanel1.add(jPanel2);
        jPanel2.setBounds(0, 0, 360, 35);

        jError.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jError.setForeground(new java.awt.Color(255, 204, 204));
        jPanel1.add(jError);
        jError.setBounds(30, 243, 300, 28);

        attendanceBackground.setBackground(new java.awt.Color(25, 118, 211));
        attendanceBackground.setLayout(null);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("[ GO TO ATTENDANCE ]");
        jLabel3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel3MouseExited(evt);
            }
        });
        attendanceBackground.add(jLabel3);
        jLabel3.setBounds(0, 0, 160, 20);

        jPanel1.add(attendanceBackground);
        attendanceBackground.setBounds(102, 440, 158, 20);

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

    private void jIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jIDActionPerformed
        
    }//GEN-LAST:event_jIDActionPerformed

    private void jButtonLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLoginActionPerformed
        int id;
        try {
            id = Integer.parseInt(jID.getText());
        }catch(NumberFormatException ex){
            jError.setText("Invalid user ID");
            return;
        }
        
        HashingPassword hashpass = new HashingPassword();
        String password = jPassword.getText();
        String hashPassword = hashpass.passwordHash(password);
        String status = loginAccountStatus(id,hashPassword);
        String archive = loginAccountArchive(id,hashPassword);
        if(loginAccountAdmin(id, hashPassword)){
            showSuccessDialog("Login Successfully");
            Session session = Session.getInstance();
            session.getAdminData(id);
            //history logs
            LogsHistory logHistory = new LogsHistory();
            logHistory.insertAdminLog(id, "Admin Login");
            
            AdminDashBoard adminFrame = new AdminDashBoard();
            adminFrame.setVisible(true);
            adminFrame.pack();
            adminFrame.setLocationRelativeTo(null);
            this.dispose(); 
        }else{
            if(status != null){
                if(status.equals("ACTIVE") && archive.equals("NO")){
                    showSuccessDialog("Login Successfully");
                    //history logs
                    LogsHistory logHistory = new LogsHistory();
                    logHistory.insertTeacherLog(id, "Teacher Login");
                    
                    TeacherDashBoard teacherframe = new TeacherDashBoard();
                    teacherframe.setVisible(true);
                    teacherframe.pack();
                    teacherframe.setLocationRelativeTo(null);
                    this.dispose();
                }else if(status.equals("INACTIVE") && archive.equals("NO")){
                    jError.setText("User is InActive");
                    return;
                }else{
                    jError.setText("Invalid user ID or password");
                    return;
                }
            }else{
                jError.setText("Invalid user ID or password");
            }
        }
        
    }//GEN-LAST:event_jButtonLoginActionPerformed

    private void disableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_disableMouseClicked
        jPassword.setEchoChar((char)0);
        disable.setVisible(false);
        disable.setEnabled(false);
        show.setEnabled(true);
        show.setEnabled(true);
    }//GEN-LAST:event_disableMouseClicked

    private void showMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_showMouseClicked
        jPassword.setEchoChar((char)8226);
        disable.setVisible(true);
        disable.setEnabled(true);
        show.setEnabled(false);
        show.setEnabled(false);
    }//GEN-LAST:event_showMouseClicked

    private void jButtonSignupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSignupActionPerformed
        SignupPage SignFrame = new SignupPage();
        SignFrame.setVisible(true);
        SignFrame.pack();
        SignFrame.setLocationRelativeTo(null);
        this.dispose();
    }//GEN-LAST:event_jButtonSignupActionPerformed

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        StudentAttendance SA = new StudentAttendance();
        SA.setVisible(true);
        SA.pack();
        SA.setLocationRelativeTo(null);
        this.dispose();
    }//GEN-LAST:event_jLabel3MouseClicked

    private void jCloseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCloseMouseClicked
        System.exit(0);
    }//GEN-LAST:event_jCloseMouseClicked

    private void jCloseKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jCloseKeyPressed
        
    }//GEN-LAST:event_jCloseKeyPressed

    private void jCloseKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jCloseKeyReleased
        
    }//GEN-LAST:event_jCloseKeyReleased

    private void jCloseMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCloseMouseEntered
        setColor(close);
    }//GEN-LAST:event_jCloseMouseEntered

    private void jCloseMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCloseMouseExited
        resetColor(close);
    }//GEN-LAST:event_jCloseMouseExited

    private void closeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeMouseEntered
        setColor(close);
    }//GEN-LAST:event_closeMouseEntered

    private void closeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeMouseExited
        resetColor(close);
    }//GEN-LAST:event_closeMouseExited

    private void jPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPasswordActionPerformed
        
    }//GEN-LAST:event_jPasswordActionPerformed

    private void jPasswordMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPasswordMouseClicked
        jError.setText("");
    }//GEN-LAST:event_jPasswordMouseClicked

    private void jIDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jIDMouseClicked
        jError.setText("");
    }//GEN-LAST:event_jIDMouseClicked

    private void jLabel3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseEntered
        attendanceBackground.setBackground(new Color(80,140,210));
    }//GEN-LAST:event_jLabel3MouseEntered

    private void jLabel3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseExited
        attendanceBackground.setBackground(new Color(25,118,211));
    }//GEN-LAST:event_jLabel3MouseExited

    
    
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
            java.util.logging.Logger.getLogger(LoginPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginPage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel attendanceBackground;
    private javax.swing.JPanel close;
    private javax.swing.JLabel disable;
    private javax.swing.JButton jButtonLogin;
    private javax.swing.JButton jButtonSignup;
    private javax.swing.JLabel jClose;
    private javax.swing.JLabel jError;
    private javax.swing.JTextField jID;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPasswordField jPassword;
    private javax.swing.JLabel logo;
    private javax.swing.JLabel show;
    // End of variables declaration//GEN-END:variables
}
