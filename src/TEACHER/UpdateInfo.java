

package TEACHER;


import config.LogsHistory;
import config.Session;
import config.myConnection;
import java.awt.Color;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;


public class UpdateInfo extends javax.swing.JFrame {

    
    
    public UpdateInfo() {
        initComponents();      
    }
    

    //function on error meessage dialog
    private void showErrorDialog(String message) {
        UIManager.put("Button.Focus",new ColorUIResource(new Color(0,0,0,0)));
        JOptionPane.showMessageDialog(null, message, "", JOptionPane.ERROR_MESSAGE);
    }
    
    //function on success message dialog
    private void showSuccessDialog(String message) {
        UIManager.put("Button.focus", new ColorUIResource(new Color(0,0,0,0)));
        JOptionPane.showMessageDialog(null, message, "", JOptionPane.INFORMATION_MESSAGE);
    }
    
    //display data to textfield
    public void displayData(){
        Session session = Session.getInstance();
        jTeacherName.setText("" + session.getFullname());
        jIDNumber.setText("" + session.getId());
        jIDNumber.setEditable(false);
        jGender.setSelectedItem("" + session.getGender());
        jBirthdate.setDate(session.getBirthdate());
        jContactNumber.setText("" + session.getContactNumber());
        jAddress.setText("" + session.getAddress());
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
        jLabel4 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jCancel = new javax.swing.JButton();
        jSave = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jAddressError = new javax.swing.JLabel();
        jNameError = new javax.swing.JLabel();
        jNumberError = new javax.swing.JLabel();
        jIDError = new javax.swing.JLabel();
        jBirthdateError = new javax.swing.JLabel();
        jNumberError1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("UPDATE INFORMATION");
        setMinimumSize(new java.awt.Dimension(550, 600));
        setUndecorated(true);
        setSize(new java.awt.Dimension(0, 0));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(25, 118, 211));
        jPanel1.setMinimumSize(new java.awt.Dimension(540, 650));
        jPanel1.setPreferredSize(new java.awt.Dimension(540, 567));
        jPanel1.setLayout(null);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Gender");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(280, 270, 80, 30);

        jIDNumber.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jIDNumber.setBorder(null);
        jIDNumber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jIDNumberActionPerformed(evt);
            }
        });
        jPanel1.add(jIDNumber);
        jIDNumber.setBounds(280, 200, 240, 40);

        jAddress.setBorder(null);
        jPanel1.add(jAddress);
        jAddress.setBounds(20, 390, 500, 40);

        jContactNumber.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jContactNumber.setBorder(null);
        jPanel1.add(jContactNumber);
        jContactNumber.setBounds(20, 200, 240, 40);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Birthdate");
        jPanel1.add(jLabel5);
        jLabel5.setBounds(20, 270, 70, 30);

        jBirthdate.setDateFormatString("MMMM-dd-yyyy");
        jBirthdate.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jPanel1.add(jBirthdate);
        jBirthdate.setBounds(20, 300, 240, 40);

        jGender.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jGender.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Male", "Female" }));
        jGender.setBorder(null);
        jGender.setFocusable(false);
        jPanel1.add(jGender);
        jGender.setBounds(280, 300, 240, 40);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Address");
        jPanel1.add(jLabel6);
        jLabel6.setBounds(20, 360, 60, 30);

        jTeacherName.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTeacherName.setBorder(null);
        jPanel1.add(jTeacherName);
        jTeacherName.setBounds(20, 110, 500, 40);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Teacher name");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(20, 81, 120, 30);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("ID Number");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(280, 171, 80, 30);

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Contact Number");
        jPanel1.add(jLabel8);
        jLabel8.setBounds(20, 171, 120, 30);

        jCancel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jCancel.setForeground(new java.awt.Color(255, 0, 0));
        jCancel.setText("CANCEL");
        jCancel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jCancel.setFocusPainted(false);
        jCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCancelActionPerformed(evt);
            }
        });
        jPanel1.add(jCancel);
        jCancel.setBounds(280, 480, 240, 40);

        jSave.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jSave.setForeground(new java.awt.Color(25, 118, 211));
        jSave.setText("SAVE CHANGES");
        jSave.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
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
        jSave.setBounds(20, 480, 240, 40);

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Profile Information");
        jPanel1.add(jLabel10);
        jLabel10.setBounds(130, 10, 280, 40);

        jAddressError.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jAddressError.setForeground(new java.awt.Color(255, 204, 204));
        jPanel1.add(jAddressError);
        jAddressError.setBounds(90, 370, 140, 16);

        jNameError.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jNameError.setForeground(new java.awt.Color(255, 204, 204));
        jPanel1.add(jNameError);
        jNameError.setBounds(128, 90, 140, 16);

        jNumberError.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jNumberError.setForeground(new java.awt.Color(255, 204, 204));
        jPanel1.add(jNumberError);
        jNumberError.setBounds(140, 180, 120, 16);

        jIDError.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jIDError.setForeground(new java.awt.Color(255, 204, 204));
        jPanel1.add(jIDError);
        jIDError.setBounds(365, 180, 130, 16);

        jBirthdateError.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jBirthdateError.setForeground(new java.awt.Color(255, 204, 204));
        jPanel1.add(jBirthdateError);
        jBirthdateError.setBounds(90, 280, 130, 16);

        jNumberError1.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jNumberError1.setForeground(new java.awt.Color(255, 204, 204));
        jPanel1.add(jNumberError1);
        jNumberError1.setBounds(20, 160, 240, 16);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jIDNumberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jIDNumberActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jIDNumberActionPerformed

    //function button goto login
    private void jCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCancelActionPerformed
        TeacherProfile profileFrame = new TeacherProfile();
        profileFrame.setVisible(true);
        profileFrame.pack();
        profileFrame.setLocationRelativeTo(null);
        this.dispose();
    }//GEN-LAST:event_jCancelActionPerformed

    private void jSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSaveActionPerformed
        String idnumbertext = jIDNumber.getText().trim();
        String name = jTeacherName.getText().trim();
        String contactnumber = jContactNumber.getText().trim();
        String birthdate = null;
        String gender = (String)jGender.getSelectedItem();
        String address = jAddress.getText().trim();
        int id = Integer.parseInt(idnumbertext);
        
        // Check if any of the required fields are empty
        if (name.isEmpty() || contactnumber.isEmpty() || address.isEmpty() || jBirthdate.getDate() == null) {
            if(contactnumber.isEmpty()){
                jNumberError.setText("Required");
                jNumberError1.setText("");
            }else{
                //check if contact number contain number/length is not 11 digits
                if(!contactnumber.matches("\\d+")){
                    jNumberError1.setText("Invalid contact Number");
                }else if(contactnumber.length() != 11){
                    jNumberError1.setText("Contact number must be 11 digits");
                }else{
                    jNumberError1.setText("");
                    jNumberError.setText("");
                }
            }
            if(name.isEmpty()){
                jNameError.setText("Required");
            }else{
                jNameError.setText("");
            }
            if(address.isEmpty()){
                jAddressError.setText("Required");
            }else{
                jAddressError.setText("");
            }
            if(jBirthdate.getDate() == null){
                jBirthdateError.setText("Invalid Date");
            }else{
                jBirthdateError.setText("");
            }
            return;
        }else if(!contactnumber.matches("\\d+") || contactnumber.length() != 11){
            //check if contact number contain number/length is not 11 digits
            if(!contactnumber.matches("\\d+")){
                jNumberError1.setText("Invalid contact number");
                jNumberError.setText("");
            }else{
                jNumberError1.setText("Contact number must be 11 digits");
                jNumberError.setText("");
            }
            return;
        }else{
            SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
            birthdate = dateformat.format(jBirthdate.getDate());
            //insert data to database
            myConnection connection = new myConnection();
            String query = "UPDATE `teacher` SET "
                + "`teacher_name` = '" + name + "', "
                + "`teacher_contact_number` = '" + contactnumber + "', "
                + "`teacher_birthdate` = '" + birthdate + "', "
                + "`teacher_gender` = '" + gender + "', "
                + "`teacher_address` = '" + address + "' "
                + "WHERE teacher_id = '" + id + "'";      
            if(connection.insertData(query)) {
                showSuccessDialog("Update Successfully");
                Session session = Session.getInstance();
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
                logHistory.insertTeacherLog(teacherID, "Teacher updated " + teacherGender + " profile information");
                //goto teacher profile
                TeacherProfile profileFrame = new TeacherProfile();
                profileFrame.setVisible(true);
                profileFrame.pack();
                profileFrame.setLocationRelativeTo(null);
                this.dispose();
            }
        }
        
        
        
    }//GEN-LAST:event_jSaveActionPerformed
  
    private void jSaveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jSaveMouseClicked
        
    }//GEN-LAST:event_jSaveMouseClicked

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        displayData();
    }//GEN-LAST:event_formWindowActivated

    

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
            java.util.logging.Logger.getLogger(UpdateInfo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UpdateInfo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UpdateInfo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UpdateInfo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

       
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {                
              new UpdateInfo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField jAddress;
    private javax.swing.JLabel jAddressError;
    private com.toedter.calendar.JDateChooser jBirthdate;
    private javax.swing.JLabel jBirthdateError;
    private javax.swing.JButton jCancel;
    private javax.swing.JTextField jContactNumber;
    private javax.swing.JComboBox<String> jGender;
    private javax.swing.JLabel jIDError;
    private javax.swing.JTextField jIDNumber;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jNameError;
    private javax.swing.JLabel jNumberError;
    private javax.swing.JLabel jNumberError1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton jSave;
    private javax.swing.JTextField jTeacherName;
    // End of variables declaration//GEN-END:variables
}
