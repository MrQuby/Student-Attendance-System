
package TEACHER;

import config.HashingPassword;
import config.LogsHistory;
import config.Session;
import config.myConnection;
import java.awt.Color;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;


public class ChangePassword extends javax.swing.JFrame {

   
    public ChangePassword() {
        initComponents();
    }

    
    //function on success message dialog
    private void showSuccessDialog(String message) {
        UIManager.put("Button.focus", new ColorUIResource(new Color(0,0,0,0)));
        JOptionPane.showMessageDialog(null, message, "", JOptionPane.INFORMATION_MESSAGE);
    }
    
    //display ID to textfield
    public void displayID(){
        Session session = Session.getInstance();
        jIDNumber.setText("" + session.getId());
        jIDNumber.setEditable(false);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jIDNumber = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jConfirmNewPassword = new javax.swing.JPasswordField();
        jOldPassword = new javax.swing.JPasswordField();
        jNewPassword = new javax.swing.JPasswordField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jSave = new javax.swing.JButton();
        jCancel = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jConfirmNewPassError = new javax.swing.JLabel();
        jOldPassError = new javax.swing.JLabel();
        jNewPassError = new javax.swing.JLabel();
        jPassMatchError = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("CHANGE PASSWORD");
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(25, 118, 211));
        jPanel1.setLayout(null);

        jIDNumber.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jIDNumber.setBorder(null);
        jPanel1.add(jIDNumber);
        jIDNumber.setBounds(20, 120, 240, 40);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Confirm New Password");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(280, 190, 160, 30);

        jConfirmNewPassword.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jConfirmNewPassword.setBorder(null);
        jPanel1.add(jConfirmNewPassword);
        jConfirmNewPassword.setBounds(280, 220, 240, 40);

        jOldPassword.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jOldPassword.setBorder(null);
        jPanel1.add(jOldPassword);
        jOldPassword.setBounds(280, 120, 240, 40);

        jNewPassword.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jNewPassword.setBorder(null);
        jPanel1.add(jNewPassword);
        jNewPassword.setBounds(20, 220, 240, 40);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 28)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("CHANGE PASSWORD");
        jPanel1.add(jLabel5);
        jLabel5.setBounds(130, 20, 300, 50);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Old Password");
        jPanel1.add(jLabel6);
        jLabel6.setBounds(280, 90, 110, 30);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("New Password");
        jPanel1.add(jLabel7);
        jLabel7.setBounds(20, 190, 110, 30);

        jSave.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jSave.setForeground(new java.awt.Color(25, 118, 211));
        jSave.setText("SAVE CHANGES");
        jSave.setFocusPainted(false);
        jSave.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jSaveMouseClicked(evt);
            }
        });
        jSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jSaveActionPerformed(evt);
            }
        });
        jPanel1.add(jSave);
        jSave.setBounds(20, 320, 240, 40);

        jCancel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jCancel.setForeground(new java.awt.Color(255, 0, 0));
        jCancel.setText("CANCEL");
        jCancel.setFocusPainted(false);
        jCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCancelActionPerformed(evt);
            }
        });
        jPanel1.add(jCancel);
        jCancel.setBounds(280, 320, 240, 40);

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("ID Number");
        jPanel1.add(jLabel8);
        jLabel8.setBounds(20, 90, 80, 30);

        jConfirmNewPassError.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jConfirmNewPassError.setForeground(new java.awt.Color(255, 204, 204));
        jPanel1.add(jConfirmNewPassError);
        jConfirmNewPassError.setBounds(450, 192, 70, 30);

        jOldPassError.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jOldPassError.setForeground(new java.awt.Color(255, 204, 204));
        jPanel1.add(jOldPassError);
        jOldPassError.setBounds(380, 92, 150, 30);

        jNewPassError.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jNewPassError.setForeground(new java.awt.Color(255, 204, 204));
        jPanel1.add(jNewPassError);
        jNewPassError.setBounds(126, 192, 150, 30);

        jPassMatchError.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jPassMatchError.setForeground(new java.awt.Color(255, 204, 204));
        jPanel1.add(jPassMatchError);
        jPassMatchError.setBounds(20, 174, 250, 20);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 540, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jSaveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jSaveMouseClicked

    }//GEN-LAST:event_jSaveMouseClicked

    private void jSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSaveActionPerformed
        Session session = Session.getInstance();
        HashingPassword hashpass = new HashingPassword();
        int id = Integer.parseInt(jIDNumber.getText());
        String currentPassword = session.getPassword();//current password
        String hashOldPassword = hashpass.passwordHash(jOldPassword.getText());
        String newPassword = jNewPassword.getText();
        String confirmNewPassword = jConfirmNewPassword.getText();
        String newPasswordHash = hashpass.passwordHash(newPassword);

        // Check if any of the required fields are empty
        if(jIDNumber.getText().isEmpty() || jOldPassword.getText().isEmpty() || jNewPassword.getText().isEmpty() || jConfirmNewPassword.getText().isEmpty()) {
            if(jNewPassword.getText().isEmpty()){
                jNewPassError.setText("Required");
            }else{
                jNewPassError.setText("");
            }
            if(jOldPassword.getText().isEmpty()){
                jOldPassError.setText("Required");
            }else{
                jOldPassError.setText("");
            }
            if(jConfirmNewPassword.getText().isEmpty()){
                jConfirmNewPassError.setText("Required");
            }else{
                jConfirmNewPassError.setText("");
            }
            return;
        }
        
        // Check if new password or re-entered password length is less than 8 characters
        if((newPassword.length() < 8) || (confirmNewPassword.length() < 8)){
            jPassMatchError.setText("Password must at least 8 characters");
            return;
        }else{
            jPassMatchError.setText("");
        }
        
        //check if oldPassword pass is correct or match to user current password
        if(!currentPassword.equals(hashOldPassword)){
            jOldPassError.setText("Incorrect Old Password");
            return;
        }else{
            jOldPassError.setText("");
        }
        
        //check if newPassword and ConfirmNewPassword does not match
        if(!newPassword.equals(confirmNewPassword)){
            jNewPassError.setText("Password does not match");
            return;
        }else{
            jNewPassError.setText("");
        }
        
        //update password data to database
        myConnection connection = new myConnection();
        String query = "UPDATE teacher SET "
                + "teacher_password = '"+ newPasswordHash +"' "
                + "WHERE teacher_id = '"+ id +"'";
        if(connection.insertData(query)){
            showSuccessDialog("Update Successfully");
            session.getData(id);
            //history logs
            String teacherGender;
            if(session.getGender().equals("Male")){
                teacherGender = "his";
            }else{
                teacherGender = "her";
            }
            int teacherID = session.getId();
            LogsHistory logHistory = new LogsHistory();
            logHistory.insertTeacherLog(teacherID, "Teacher updated " + teacherGender + " password");
            
            TeacherProfile profileFrame = new TeacherProfile();
            profileFrame.setVisible(true);
            profileFrame.pack();
            profileFrame.setLocationRelativeTo(null);
            this.dispose();
        }else{
            System.out.println("Connection Error");
        }
    }//GEN-LAST:event_jSaveActionPerformed

    private void jCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCancelActionPerformed
        TeacherProfile profileFrame = new TeacherProfile();
        profileFrame.setVisible(true);
        profileFrame.pack();
        profileFrame.setLocationRelativeTo(null);
        this.dispose();
    }//GEN-LAST:event_jCancelActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        displayID();
    }//GEN-LAST:event_formWindowActivated

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
            java.util.logging.Logger.getLogger(ChangePassword.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ChangePassword.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ChangePassword.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ChangePassword.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ChangePassword().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jCancel;
    private javax.swing.JLabel jConfirmNewPassError;
    private javax.swing.JPasswordField jConfirmNewPassword;
    private javax.swing.JTextField jIDNumber;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jNewPassError;
    private javax.swing.JPasswordField jNewPassword;
    private javax.swing.JLabel jOldPassError;
    private javax.swing.JPasswordField jOldPassword;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel jPassMatchError;
    private javax.swing.JButton jSave;
    // End of variables declaration//GEN-END:variables
}
