
package student.attendance.system;

import config.HashingPassword;
import config.LogsHistory;
import config.myConnection;
import java.awt.Color;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;


public class SignupPage extends javax.swing.JFrame {

    
    public SignupPage() {
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
    
    //function if ID already exist on database
    public boolean checkID(int id) {
        myConnection connector = new myConnection();
        try {
            String query = "SELECT * FROM `teacher` WHERE `teacher_id` = '" + id + "'";
            ResultSet resultset = connector.getData(query);
            return resultset.next();
        } catch (SQLException ex) {
            System.out.println("Database Error in checkID " + ex.getMessage()); 
            return false;
        }
    }
   
    //ID validation
    private boolean validateID(String idnumbertext) {
        try {
            Integer.parseInt(idnumbertext);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    //Signup button
    private void signupButton(){
        HashingPassword hashpass = new HashingPassword();
        String idnumbertext = jIDNumber.getText().trim();
        String name = jTeacherName.getText().trim();
        String contactnumber = jContactNumber.getText().trim();
        String password = String.valueOf(jPassword.getPassword());
        String rePassword = String.valueOf(jConfirmPassword.getPassword());
        String hashPassword = hashpass.passwordHash(password); //hashpassword
        String birthdate = null;
        String gender = (String)jGender.getSelectedItem();
        String address = jAddress.getText().trim();

        // Check if any of the required fields are empty
        if(idnumbertext.isEmpty() || name.isEmpty() || contactnumber.isEmpty() || password.isEmpty() || rePassword.isEmpty() || address.isEmpty() || jBirthdate.getDate() == null){
            //if id is empty
            if(idnumbertext.isEmpty()) {
                jIDError.setText("Required");
            }else{
                if(!validateID(idnumbertext)) {  //check if ID is integer
                    jIDError.setText("Invalid ID");
                    return;
                }else if(checkID(Integer.parseInt(idnumbertext))) {  //check if ID already exist
                    jIDError.setText("ID already exist");
                }else{
                    jIDError.setText("");
                }     
            }
            //if name is empty
            if(name.isEmpty()){
                jNameError.setText("Required");
            }else{
                jNameError.setText("");
            }
            //if contact number is empty
            if(contactnumber.isEmpty()){
                jNumberError.setText("Required");
                jNumberError1.setText("");
            }else{
                //check if contact number contain number and have 11 digits number
                if(!contactnumber.matches("\\d+")){
                    jNumberError1.setText("Invalid contact number");
                }else if(contactnumber.length() != 11){
                    jNumberError1.setText("Contact number must be 11 digits");
                }else{
                    jNumberError1.setText("");
                }
                jNumberError.setText("");
            }
            //if password or confirm password is empty
            if(password.isEmpty() || rePassword.isEmpty()){
                if(password.isEmpty()){
                    jPasswordError.setText("Required");
                }else{
                    jPasswordError.setText("");
                }
                if(jConfirmPassword.getText().isEmpty()){
                    jRePasswordError.setText("Required");
                }else{
                    jRePasswordError.setText("");
                }
            }else{
                if(!rePassword.isEmpty()){
                    jRePasswordError.setText("");
                }
                // Check if password or re-entered password length is less than 8 characters
                if((password.length() < 8) || (rePassword.length() < 8)) {
                    jPassMatchError.setText("Password must at least 8 characters");
                }else{
                    jPassMatchError.setText("");
                }
                // Check if password and re-entered password match
                if(!password.equals(rePassword)) {
                    jPasswordError.setText("Password does not match");
                }else{
                    jPasswordError.setText("");
                }
            }
            //if address is empty
            if(address.isEmpty()){
                jAddressError.setText("Required");
            }else{
                jAddressError.setText("");
            }
            
            //if birthdate is invalid/null
            if(jBirthdate.getDate() == null){
                jBirthdateError.setText("Invalid Date");
            }else{
                jBirthdateError.setText("");
            }
            return;
        }else if(!validateID(idnumbertext) || !contactnumber.matches("\\d+") || contactnumber.length() != 11 || (password.length() < 8) || (rePassword.length() < 8) || !password.equals(rePassword) || checkID(Integer.parseInt(idnumbertext))){            
            //check if contact number contain number and have 11 digits number
            if(!contactnumber.matches("\\d+") || contactnumber.length() != 11){
                if(!contactnumber.matches("\\d+")){
                    jNumberError1.setText("Invalid contact number");
                    jNumberError.setText("");
                }else{
                    jNumberError1.setText("Contact number must be 11 digits");
                    jNumberError.setText("");
                }   
            }
            // Check if password or re-entered password length is less than 8 characters
            if((password.length() < 8) || (rePassword.length() < 8)) {
                jPassMatchError.setText("Password must at least 8 characters");
            }else{
                jPassMatchError.setText("");
            }
            // Check if password and re-entered password match
            if(!password.equals(rePassword)) {
                jPasswordError.setText("Password does not match");
            }else{
                jPasswordError.setText("");
            }
            
            if(!validateID(idnumbertext)) {  //check if ID is integer
                jIDError.setText("Invalid ID");
                return;
            }else if(checkID(Integer.parseInt(idnumbertext))) {  //check if ID already exist
                jIDError.setText("ID already exist");
            }else{
                jIDError.setText("");
            }           
            return;
        }else{
            SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
            birthdate = dateformat.format(jBirthdate.getDate());
            //insert data to database
            myConnection connection = new myConnection();
            String query = "INSERT INTO `teacher`(`teacher_id`, `teacher_name`, `teacher_contact_number`, `teacher_password`, `teacher_birthdate`, `teacher_gender`, `teacher_address`, `teacher_status`, teacher_archive) VALUES ("
                    + "'" + Integer.parseInt(idnumbertext) + "',"
                    + "'"+ name +"',"
                    + "'"+ contactnumber +"',"
                    + "'"+ hashPassword +"',"
                    + "'"+ birthdate +"',"
                    + "'"+ gender +"',"
                    + "'"+ address +"',"
                    + "'PENDING'"
                    + "'NO')";
            if(connection.insertData(query)) {
                showSuccessDialog("Register Successfully");
                //history logs
                LogsHistory logHistory = new LogsHistory();
                logHistory.insertTeacherLog(Integer.parseInt(idnumbertext), "New Teacher signed up");
                
                LoginPage loginFrame = new LoginPage();
                loginFrame.setVisible(true);
                loginFrame.pack();
                loginFrame.setLocationRelativeTo(null);
                this.dispose();
            }
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jIDNumber = new javax.swing.JTextField();
        jAddress = new javax.swing.JTextField();
        jContactNumber = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jBirthdate = new com.toedter.calendar.JDateChooser();
        jGender = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jTeacherName = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jConfirmPassword = new javax.swing.JPasswordField();
        jPassword = new javax.swing.JPasswordField();
        jLabel8 = new javax.swing.JLabel();
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
        jPassMatchError = new javax.swing.JLabel();
        jNumberError1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(540, 650));
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(25, 118, 211));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(25, 118, 211), 2));
        jPanel1.setMinimumSize(new java.awt.Dimension(540, 650));
        jPanel1.setPreferredSize(new java.awt.Dimension(540, 650));
        jPanel1.setLayout(null);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Gender");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(280, 350, 80, 30);

        jIDNumber.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jIDNumber.setBorder(null);
        jIDNumber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jIDNumberActionPerformed(evt);
            }
        });
        jPanel1.add(jIDNumber);
        jIDNumber.setBounds(280, 200, 240, 38);

        jAddress.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jAddress.setBorder(null);
        jPanel1.add(jAddress);
        jAddress.setBounds(20, 470, 500, 38);

        jContactNumber.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jContactNumber.setBorder(null);
        jPanel1.add(jContactNumber);
        jContactNumber.setBounds(20, 200, 240, 38);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Birthdate");
        jPanel1.add(jLabel5);
        jLabel5.setBounds(20, 350, 70, 30);

        jBirthdate.setDateFormatString("MMMM-dd-yyyy");
        jBirthdate.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jPanel1.add(jBirthdate);
        jBirthdate.setBounds(20, 380, 240, 38);

        jGender.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jGender.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Male", "Female" }));
        jGender.setBorder(null);
        jGender.setFocusable(false);
        jPanel1.add(jGender);
        jGender.setBounds(280, 380, 240, 38);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Address");
        jPanel1.add(jLabel6);
        jLabel6.setBounds(20, 441, 60, 30);

        jTeacherName.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jTeacherName.setBorder(null);
        jPanel1.add(jTeacherName);
        jTeacherName.setBounds(20, 110, 500, 38);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Teacher name");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(20, 81, 120, 30);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Confirm Password");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(280, 261, 130, 30);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("ID Number");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(280, 171, 80, 30);

        jConfirmPassword.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jConfirmPassword.setBorder(null);
        jPanel1.add(jConfirmPassword);
        jConfirmPassword.setBounds(280, 290, 240, 38);

        jPassword.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jPassword.setBorder(null);
        jPanel1.add(jPassword);
        jPassword.setBounds(20, 290, 240, 38);

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Contact Number");
        jPanel1.add(jLabel8);
        jLabel8.setBounds(20, 171, 120, 30);

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Password");
        jPanel1.add(jLabel9);
        jLabel9.setBounds(20, 261, 80, 30);

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
        jGoToLogin.setBounds(280, 560, 240, 40);

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
        jSignup.setBounds(20, 560, 240, 40);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(25, 118, 211), 2));
        jPanel2.setLayout(null);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(25, 118, 211));
        jLabel7.setText("USER REGISTRATION");
        jPanel2.add(jLabel7);
        jLabel7.setBounds(210, 0, 150, 35);

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

        jAddressError.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jAddressError.setForeground(new java.awt.Color(255, 204, 204));
        jPanel1.add(jAddressError);
        jAddressError.setBounds(90, 440, 190, 30);

        jNameError.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jNameError.setForeground(new java.awt.Color(255, 204, 204));
        jPanel1.add(jNameError);
        jNameError.setBounds(130, 80, 180, 30);

        jNumberError.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jNumberError.setForeground(new java.awt.Color(255, 204, 204));
        jPanel1.add(jNumberError);
        jNumberError.setBounds(150, 170, 110, 30);

        jIDError.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jIDError.setForeground(new java.awt.Color(255, 204, 204));
        jPanel1.add(jIDError);
        jIDError.setBounds(370, 170, 150, 30);

        jPasswordError.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jPasswordError.setForeground(new java.awt.Color(255, 204, 204));
        jPanel1.add(jPasswordError);
        jPasswordError.setBounds(100, 260, 160, 30);

        jRePasswordError.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jRePasswordError.setForeground(new java.awt.Color(255, 204, 204));
        jPanel1.add(jRePasswordError);
        jRePasswordError.setBounds(420, 260, 100, 30);

        jBirthdateError.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jBirthdateError.setForeground(new java.awt.Color(255, 204, 204));
        jPanel1.add(jBirthdateError);
        jBirthdateError.setBounds(100, 350, 160, 30);

        jPassMatchError.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jPassMatchError.setForeground(new java.awt.Color(255, 204, 204));
        jPanel1.add(jPassMatchError);
        jPassMatchError.setBounds(20, 240, 510, 30);

        jNumberError1.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jNumberError1.setForeground(new java.awt.Color(255, 204, 204));
        jPanel1.add(jNumberError1);
        jNumberError1.setBounds(20, 158, 240, 20);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jIDNumberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jIDNumberActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jIDNumberActionPerformed

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
            java.util.logging.Logger.getLogger(SignupPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SignupPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SignupPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SignupPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SignupPage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel close;
    private javax.swing.JTextField jAddress;
    private javax.swing.JLabel jAddressError;
    private com.toedter.calendar.JDateChooser jBirthdate;
    private javax.swing.JLabel jBirthdateError;
    private javax.swing.JPasswordField jConfirmPassword;
    private javax.swing.JTextField jContactNumber;
    private javax.swing.JComboBox<String> jGender;
    private javax.swing.JButton jGoToLogin;
    private javax.swing.JLabel jIDError;
    private javax.swing.JTextField jIDNumber;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jNameError;
    private javax.swing.JLabel jNumberError;
    private javax.swing.JLabel jNumberError1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel jPassMatchError;
    private javax.swing.JPasswordField jPassword;
    private javax.swing.JLabel jPasswordError;
    private javax.swing.JLabel jRePasswordError;
    private javax.swing.JButton jSignup;
    private javax.swing.JTextField jTeacherName;
    // End of variables declaration//GEN-END:variables
}
