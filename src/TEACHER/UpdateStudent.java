
package TEACHER;



import config.LogsHistory;
import config.Session;
import config.myConnection;
import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.ColorUIResource;




public class UpdateStudent extends javax.swing.JFrame {

    private ImageIcon format = null;
    String imagePath;
    
    public UpdateStudent() {
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
    //function in selecting image
    public void selectImage() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        FileNameExtensionFilter filter = new FileNameExtensionFilter(".IMAGE","jpg","png","gif");
        fileChooser.addChoosableFileFilter(filter);
        int result = fileChooser.showSaveDialog(null);
        if(result == JFileChooser.APPROVE_OPTION){
            File file = fileChooser.getSelectedFile();
            imagePath = file.getAbsolutePath();
            ImageIcon ii = new ImageIcon(imagePath);
            Image img = ii.getImage().getScaledInstance(jDisplayImages.getWidth(), jDisplayImages.getHeight(), Image.SCALE_SMOOTH);
            jDisplayImages.setIcon(new ImageIcon(img));
        }
    }
    //display image from database
    public void showImage(int id) {
        myConnection connector = new myConnection();
        try {
            String query = "SELECT student_image FROM student WHERE student_id = '" + id + "'";
            ResultSet resultSet = connector.getData(query);
            if (resultSet.next()) {
                byte[] imageData = resultSet.getBytes("student_image");
                ImageIcon imageIcon = new ImageIcon(imageData);
                Image image = imageIcon.getImage();
                Image scaledImage = image.getScaledInstance(230, 200, Image.SCALE_SMOOTH);
                ImageIcon scaledImageIcon = new ImageIcon(scaledImage);
                jDisplayImages.setIcon(scaledImageIcon);
            } else {
                showErrorDialog("No image found for student ID: " + id);
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }
    
    //RFID validation
    private boolean validateID(String idnumbertext) {
        try {
            Integer.parseInt(idnumbertext);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jInsertImage = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButtonSave = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jAddress = new javax.swing.JTextArea();
        jBirthdate = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jStatus = new javax.swing.JComboBox<>();
        jDisplayImages = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jFullName = new javax.swing.JTextField();
        jDepartment = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jStudentRFID = new javax.swing.JTextField();
        jStudentID = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("UPDATE STUDENT");
        setUndecorated(true);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(25, 118, 211));
        jPanel1.setLayout(null);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("UPDATE STUDENT DETAILS");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(20, 20, 260, 25);

        jInsertImage.setBackground(new java.awt.Color(255, 255, 255));
        jInsertImage.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 204, 255)));
        jInsertImage.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jInsertImage.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jInsertImage.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jInsertImageMouseClicked(evt);
            }
        });
        jInsertImage.setLayout(null);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel7.setText("Insert Image");
        jInsertImage.add(jLabel7);
        jLabel7.setBounds(80, 4, 90, 20);

        jPanel1.add(jInsertImage);
        jInsertImage.setBounds(440, 260, 230, 30);

        jButton2.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 51, 51));
        jButton2.setText("CANCEL");
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2);
        jButton2.setBounds(290, 400, 120, 25);

        jButtonSave.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jButtonSave.setText("SAVE");
        jButtonSave.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonSave);
        jButtonSave.setBounds(140, 400, 120, 25);

        jPanel3.setBackground(new java.awt.Color(25, 118, 211));
        jPanel3.setLayout(null);

        jAddress.setColumns(20);
        jAddress.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jAddress.setLineWrap(true);
        jAddress.setRows(3);
        jAddress.setWrapStyleWord(true);
        jAddress.setBorder(null);
        jScrollPane2.setViewportView(jAddress);

        jPanel3.add(jScrollPane2);
        jScrollPane2.setBounds(140, 250, 270, 70);

        jBirthdate.setDateFormatString("MM-dd-yy");
        jBirthdate.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jPanel3.add(jBirthdate);
        jBirthdate.setBounds(140, 170, 270, 26);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("ADDRESS");
        jPanel3.add(jLabel1);
        jLabel1.setBounds(20, 250, 100, 20);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("STATUS");
        jPanel3.add(jLabel6);
        jLabel6.setBounds(20, 210, 60, 30);

        jStatus.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ACTIVE", "INACTIVE" }));
        jPanel3.add(jStatus);
        jStatus.setBounds(140, 210, 270, 26);

        jDisplayImages.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 204, 255)));
        jPanel3.add(jDisplayImages);
        jDisplayImages.setBounds(440, 10, 230, 200);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("DEPARTMENT");
        jPanel3.add(jLabel3);
        jLabel3.setBounds(20, 130, 110, 20);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("STUDENT RFID");
        jPanel3.add(jLabel4);
        jLabel4.setBounds(20, 50, 100, 20);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("FULL NAME");
        jPanel3.add(jLabel5);
        jLabel5.setBounds(20, 90, 100, 20);

        jFullName.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jPanel3.add(jFullName);
        jFullName.setBounds(140, 90, 270, 26);

        jDepartment.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jDepartment.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "BSIT", "BSBA", "BSCRIM", "BEED", "BSHM" }));
        jPanel3.add(jDepartment);
        jDepartment.setBounds(140, 130, 270, 26);

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("BIRTHDATE");
        jPanel3.add(jLabel8);
        jLabel8.setBounds(20, 170, 100, 20);

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("STUDENT ID");
        jPanel3.add(jLabel9);
        jLabel9.setBounds(20, 14, 100, 20);

        jStudentRFID.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jPanel3.add(jStudentRFID);
        jStudentRFID.setBounds(140, 50, 270, 26);

        jStudentID.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jPanel3.add(jStudentID);
        jStudentID.setBounds(140, 10, 270, 26);

        jPanel1.add(jPanel3);
        jPanel3.setBounds(0, 50, 700, 330);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //insert image button
    private void jInsertImageMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jInsertImageMouseClicked
        selectImage();
    }//GEN-LAST:event_jInsertImageMouseClicked

    //save button
    private void jButtonSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveActionPerformed
        if(jStudentRFID.getText().isEmpty() || jFullName.getText().isEmpty() || jAddress.getText().isEmpty() || jBirthdate.getDate() == null){
            showErrorDialog("Please fill in all fields");
            return;
        }
        if(!validateID(jStudentRFID.getText())){
            showErrorDialog("Invalid RFID");
            return;
        }
        File file = null;
        if (imagePath != null && !imagePath.isEmpty()) {
            file = new File(imagePath);
        }
    
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String birthdate = dateFormat.format(jBirthdate.getDate());
        myConnection connector = new myConnection();
        
        try {
            InputStream inputImage = null;
            if (file != null) {
                inputImage = new FileInputStream(file);
            }

            String query;
            if (file != null) {
                query = "UPDATE student SET "
                    + "student_rfid=?, "
                    + "student_fullname=?, "
                    + "student_birthdate=?, "
                    + "student_address=?, "
                    + "student_department=?, "
                    + "student_image=?, "
                    + "student_status=? "
                    + "WHERE student_id=?";
            } else {
                query = "UPDATE student SET "
                    + "student_rfid=?, "
                    + "student_fullname=?, "
                    + "student_birthdate=?, "
                    + "student_address=?, "
                    + "student_department=?, "
                    + "student_status=? "
                    + "WHERE student_id=?";
            }

            Connection connection = connector.connect;
            PreparedStatement pst = connection.prepareStatement(query);

            pst.setString(1, jStudentRFID.getText());
            pst.setString(2, jFullName.getText());
            pst.setString(3, birthdate);
            pst.setString(4, jAddress.getText());
            pst.setString(5, (String)jDepartment.getSelectedItem());

            if (file != null) {
                pst.setBinaryStream(6, inputImage);
                pst.setString(7, (String)jStatus.getSelectedItem());
                pst.setInt(8, Integer.valueOf(jStudentID.getText()));
            } else {
                pst.setString(6, (String) jStatus.getSelectedItem());
                pst.setInt(7, Integer.valueOf(jStudentID.getText()));
            }

            if (connector.insertDatas(pst)) {
                showSuccessDialog("Data updated successfully");
                //history logs
                Session session = Session.getInstance();
                int teacherID = session.getId();
                LogsHistory logHistory = new LogsHistory();
                logHistory.insertTeacherLog(teacherID, "Teacher updated student details with ID number " + Integer.valueOf(jStudentID.getText()));
                
                TeacherManageStudent manageStudentFrame = new TeacherManageStudent();
                manageStudentFrame.setVisible(true);
                manageStudentFrame.pack();
                manageStudentFrame.setLocationRelativeTo(null);
                this.dispose();
            }
        } catch (FileNotFoundException | SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(AddStudent.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connector.closeConnection();
            } catch (SQLException ex) {
                System.out.println("" + ex.getMessage());
                Logger.getLogger(AddStudent.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }//GEN-LAST:event_jButtonSaveActionPerformed

    //cancel button
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        TeacherManageStudent manageStudentFrame = new TeacherManageStudent();
        manageStudentFrame.setVisible(true);
        manageStudentFrame.pack();
        manageStudentFrame.setLocationRelativeTo(null);
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    
    
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
            java.util.logging.Logger.getLogger(UpdateStudent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UpdateStudent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UpdateStudent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UpdateStudent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UpdateStudent().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JTextArea jAddress;
    public com.toedter.calendar.JDateChooser jBirthdate;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButtonSave;
    public javax.swing.JComboBox<String> jDepartment;
    public javax.swing.JLabel jDisplayImages;
    public javax.swing.JTextField jFullName;
    private javax.swing.JPanel jInsertImage;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    public javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    public javax.swing.JComboBox<String> jStatus;
    public javax.swing.JTextField jStudentID;
    public javax.swing.JTextField jStudentRFID;
    // End of variables declaration//GEN-END:variables
}
