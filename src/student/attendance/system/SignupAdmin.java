
package student.attendance.system;

import config.HashingPassword;
import config.myConnection;
import java.awt.Color;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;


public class SignupAdmin extends javax.swing.JFrame {

    
    public SignupAdmin() {
        initComponents();
    }
    
    //function on success message dialog
    private void showSuccessDialog(String message) {
        UIManager.put("Button.focus", new ColorUIResource(new Color(0,0,0,0)));
        JOptionPane.showMessageDialog(null, message, "", JOptionPane.INFORMATION_MESSAGE);
    }
    public void setColor(JPanel panel) {
        panel.setBackground(new Color(80,140,210));
    }
    
    public void resetColor(JPanel panel) {
        panel.setBackground(new Color(255,255,255));
    }

    //ID validation
    private boolean validateID(String idnumbertext) {
        try {
            Integer.parseInt(idnumbertext);
            return true;
        } catch (NumberFormatException e) {
            jIDError.setText("Invalid ID");
            return false;
        }
    }
    
    //Signup button
    private void signupButton(){
        String idnumbertext = jIDNumber.getText().trim();
        HashingPassword hashpass = new HashingPassword();
        String password = String.valueOf(jPassword.getPassword());
        String rePassword = String.valueOf(jConfirmPassword.getPassword());
        String hashPassword = hashpass.passwordHash(password); //hashpassword;

        // Check if any of the required fields are empty
        if(idnumbertext.isEmpty() || password.isEmpty() || rePassword.isEmpty()){
            if(idnumbertext.isEmpty()) {
                jIDError.setText("Required");
            }
            if(password.isEmpty()){
                jPasswordError.setText("Required");
            }
            if(rePassword.isEmpty()){
                jRePasswordError.setText("Required");
            }
            return;
        }else if(!validateID(idnumbertext) || (password.length() < 8) || (rePassword.length() < 8) || !password.equals(rePassword)){
            // Check if password or re-entered password length is less than 8 characters
            if((password.length() < 8) || (rePassword.length() < 8)) {
                jPasswordError.setText("Password must at least 8 characters");
            }else{
                jPasswordError.setText("");
            }
            // Check if password and re-entered password match
            if(!password.equals(rePassword)) {
                jPasswordError.setText("Password does not match");
            }else{
                jPasswordError.setText("");
            }
            if(!validateID(idnumbertext)) {
                jIDError.setText("Invalid ID");
                return;
            }
            return;
        }else{
            //insert data to database
            myConnection connection = new myConnection();
            String query = "INSERT INTO `admin`(`admin_id`, `admin_name`, `admin_password`) VALUES ("
                    + "'" + Integer.parseInt(idnumbertext) + "',"
                    + "'ADMIN',"
                    + "'" + hashPassword + "')";
            if(connection.insertData(query)) {
                showSuccessDialog("Register Successfully");
                LoginPage LoginFrame = new LoginPage();
                LoginFrame.setVisible(true);
                LoginFrame.pack();
                LoginFrame.setLocationRelativeTo(null);
                this.dispose();
            }
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jIDNumber = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jConfirmPassword = new javax.swing.JPasswordField();
        jPassword = new javax.swing.JPasswordField();
        jLabel9 = new javax.swing.JLabel();
        jGoToLogin = new javax.swing.JButton();
        jSignup = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        close = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jAddressError = new javax.swing.JLabel();
        jNameError = new javax.swing.JLabel();
        jNumberError = new javax.swing.JLabel();
        jIDError = new javax.swing.JLabel();
        jPasswordError = new javax.swing.JLabel();
        jRePasswordError = new javax.swing.JLabel();
        jBirthdateError = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(540, 346));

        jPanel1.setBackground(new java.awt.Color(25, 118, 211));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(25, 118, 211), 2));
        jPanel1.setMinimumSize(new java.awt.Dimension(540, 650));
        jPanel1.setPreferredSize(new java.awt.Dimension(540, 650));
        jPanel1.setLayout(null);

        jIDNumber.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jIDNumber.setBorder(null);
        jPanel1.add(jIDNumber);
        jIDNumber.setBounds(20, 100, 500, 38);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Confirm Password");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(280, 150, 130, 30);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("ID Number");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(20, 70, 80, 30);

        jConfirmPassword.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jConfirmPassword.setBorder(null);
        jPanel1.add(jConfirmPassword);
        jConfirmPassword.setBounds(280, 180, 240, 38);

        jPassword.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jPassword.setBorder(null);
        jPanel1.add(jPassword);
        jPassword.setBounds(20, 180, 240, 38);

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Password");
        jPanel1.add(jLabel9);
        jLabel9.setBounds(20, 150, 80, 30);

        jGoToLogin.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jGoToLogin.setForeground(new java.awt.Color(255, 0, 0));
        jGoToLogin.setText("GO TO LOGIN");
        jGoToLogin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jGoToLogin.setFocusPainted(false);
        jGoToLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jGoToLoginActionPerformed(evt);
            }
        });
        jPanel1.add(jGoToLogin);
        jGoToLogin.setBounds(280, 260, 240, 40);

        jSignup.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jSignup.setForeground(new java.awt.Color(25, 118, 211));
        jSignup.setText("SIGNUP");
        jSignup.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jSignup.setFocusPainted(false);
        jSignup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jSignupActionPerformed(evt);
            }
        });
        jPanel1.add(jSignup);
        jSignup.setBounds(20, 260, 240, 40);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(25, 118, 211), 2));
        jPanel2.setLayout(null);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(25, 118, 211));
        jLabel7.setText("ADMIN REGISTRATION");
        jPanel2.add(jLabel7);
        jLabel7.setBounds(200, 0, 170, 35);

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

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 0, 51));
        jLabel11.setText("   X");
        jLabel11.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel11MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel11MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel11MouseExited(evt);
            }
        });
        close.add(jLabel11);
        jLabel11.setBounds(0, 0, 30, 30);

        jPanel2.add(close);
        close.setBounds(507, 2, 31, 31);

        jPanel1.add(jPanel2);
        jPanel2.setBounds(0, 0, 540, 35);

        jAddressError.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jAddressError.setForeground(new java.awt.Color(255, 204, 204));
        jPanel1.add(jAddressError);
        jAddressError.setBounds(90, 440, 110, 30);

        jNameError.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jNameError.setForeground(new java.awt.Color(255, 204, 204));
        jPanel1.add(jNameError);
        jNameError.setBounds(130, 80, 130, 30);

        jNumberError.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jNumberError.setForeground(new java.awt.Color(255, 204, 204));
        jPanel1.add(jNumberError);
        jNumberError.setBounds(150, 170, 110, 30);

        jIDError.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jIDError.setForeground(new java.awt.Color(255, 204, 204));
        jPanel1.add(jIDError);
        jIDError.setBounds(370, 170, 110, 30);

        jPasswordError.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jPasswordError.setForeground(new java.awt.Color(255, 204, 204));
        jPanel1.add(jPasswordError);
        jPasswordError.setBounds(100, 260, 110, 30);

        jRePasswordError.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jRePasswordError.setForeground(new java.awt.Color(255, 204, 204));
        jPanel1.add(jRePasswordError);
        jRePasswordError.setBounds(410, 260, 110, 30);

        jBirthdateError.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jBirthdateError.setForeground(new java.awt.Color(255, 204, 204));
        jPanel1.add(jBirthdateError);
        jBirthdateError.setBounds(100, 350, 110, 30);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //function button goto login
    private void jGoToLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jGoToLoginActionPerformed
        LoginPage LoginFrame = new LoginPage();
        LoginFrame.setVisible(true);
        LoginFrame.pack();
        LoginFrame.setLocationRelativeTo(null);
        this.dispose();
    }//GEN-LAST:event_jGoToLoginActionPerformed

    private void jSignupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSignupActionPerformed
        signupButton();
    }//GEN-LAST:event_jSignupActionPerformed

    private void jLabel11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseClicked
        System.exit(0);
    }//GEN-LAST:event_jLabel11MouseClicked

    private void jLabel11MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseEntered
        setColor(close);
    }//GEN-LAST:event_jLabel11MouseEntered

    private void jLabel11MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseExited
        resetColor(close);
    }//GEN-LAST:event_jLabel11MouseExited

    private void closeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeMouseEntered
        setColor(close);
    }//GEN-LAST:event_closeMouseEntered

    private void closeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeMouseExited
        resetColor(close);
    }//GEN-LAST:event_closeMouseExited

    

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
            java.util.logging.Logger.getLogger(SignupAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SignupAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SignupAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SignupAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SignupAdmin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel close;
    private javax.swing.JLabel jAddressError;
    private javax.swing.JLabel jBirthdateError;
    private javax.swing.JPasswordField jConfirmPassword;
    private javax.swing.JButton jGoToLogin;
    private javax.swing.JLabel jIDError;
    private javax.swing.JTextField jIDNumber;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jNameError;
    private javax.swing.JLabel jNumberError;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPasswordField jPassword;
    private javax.swing.JLabel jPasswordError;
    private javax.swing.JLabel jRePasswordError;
    private javax.swing.JButton jSignup;
    // End of variables declaration//GEN-END:variables
}
