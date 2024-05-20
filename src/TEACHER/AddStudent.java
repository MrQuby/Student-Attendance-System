
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




public class AddStudent extends javax.swing.JFrame {

    File file = null;
    String imagePath = "src/icon/boyImage.png";
    
    public AddStudent() {
        initComponents();
        //default image
        ImageIcon  image1 = new ImageIcon(new ImageIcon("src/icon/boyImage.png").getImage().getScaledInstance(jImage.getWidth(), jImage.getHeight(), Image.SCALE_SMOOTH));
        jImage.setIcon(image1);
    }
 
    //method in selecting image
    public void insertImage() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        FileNameExtensionFilter filter = new FileNameExtensionFilter(".IMAGE","jpg","png","gif");
        fileChooser.addChoosableFileFilter(filter);
        int result = fileChooser.showSaveDialog(null);
        if(result == JFileChooser.APPROVE_OPTION){
            file = fileChooser.getSelectedFile();
            imagePath = file.getAbsolutePath();
            ImageIcon ii = new ImageIcon(imagePath);
            Image img = ii.getImage().getScaledInstance(jImage.getWidth(), jImage.getHeight(), Image.SCALE_SMOOTH);
            jImage.setIcon(new ImageIcon(img));
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
    
    //adding new student and save to database
    public void addStudent(){
        if(jStudentID.getText().isEmpty() || jFullName.getText().isEmpty() || jAddress.getText().isEmpty() || jBirthdate.getDate() == null){
            showErrorDialog("Please fill in all fields");
            return;
        }
        if(!validateID(jStudentID.getText())){
            showErrorDialog("Invalid ID");
            return;
        }
        File file = new File(imagePath);
        
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
        String birthdate = dateformat.format(jBirthdate.getDate());
        myConnection connector = new myConnection();
        try {
            InputStream inputImage = new FileInputStream(new File(imagePath));
            String query = "INSERT INTO student (student_id, student_fullname, student_birthdate, student_department, student_address, student_image, student_status, student_archive) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            
            Connection connection = connector.connect;
            PreparedStatement pst = connection.prepareStatement(query);

            pst.setInt(1, Integer.valueOf(jStudentID.getText()));
            pst.setString(2, jFullName.getText());
            pst.setString(3, birthdate);
            pst.setString(4, (String)jDepartment.getSelectedItem());
            pst.setString(5, jAddress.getText());
            pst.setBinaryStream(6, inputImage);
            pst.setString(7, (String)jStatus.getSelectedItem());
            pst.setString(8, "YES");
            if(connector.insertDatas(pst)){
                showSuccessDialog("Register successfully");
                //history logs
                Session session = Session.getInstance();
                int teacherID = session.getId();
                LogsHistory logHistory = new LogsHistory();
                logHistory.insertTeacherLog(teacherID, "Teacher added new student with ID number " + Integer.valueOf(jStudentID.getText()));
                
                TeacherManageStudent manageStudent = new TeacherManageStudent();
                manageStudent.setVisible(true);
                manageStudent.pack();
                manageStudent.setLocationRelativeTo(null);
                this.dispose();
            }
        } catch (FileNotFoundException | SQLException ex) {
            Logger.getLogger(AddStudent.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connector.closeConnection();
            } catch (SQLException ex) {
                Logger.getLogger(AddStudent.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    //function to clear all textfields
    private void clearFields() {
        jStudentID.setText("");
        jFullName.setText("");
        jAddress.setText("");
        jBirthdate.setDate(null);
        jImage.setIcon(null);
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
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jInsertImage = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jImage = new javax.swing.JLabel();
        jButtonCancel = new javax.swing.JButton();
        jButtonSave = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jAddress = new javax.swing.JTextArea();
        jBirthdate = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        jStatus = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jStudentID = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jFullName = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jDepartment = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("ADD STUDENT");
        setUndecorated(true);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 255)));
        jPanel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel1.setLayout(null);

        jInsertImage.setBackground(new java.awt.Color(255, 255, 255));
        jInsertImage.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 204, 255)));
        jInsertImage.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jInsertImage.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jInsertImageMouseClicked(evt);
            }
        });
        jInsertImage.setLayout(null);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel7.setText("Insert Image");
        jInsertImage.add(jLabel7);
        jLabel7.setBounds(80, 6, 70, 16);

        jPanel1.add(jInsertImage);
        jInsertImage.setBounds(440, 260, 230, 30);

        jImage.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 204, 255)));
        jPanel1.add(jImage);
        jImage.setBounds(440, 60, 230, 200);

        jButtonCancel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButtonCancel.setForeground(new java.awt.Color(255, 51, 51));
        jButtonCancel.setText("CANCEL");
        jButtonCancel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonCancel);
        jButtonCancel.setBounds(290, 360, 120, 25);

        jButtonSave.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButtonSave.setForeground(new java.awt.Color(0, 102, 255));
        jButtonSave.setText("SAVE");
        jButtonSave.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonSave);
        jButtonSave.setBounds(140, 360, 120, 25);

        jPanel3.setBackground(new java.awt.Color(25, 118, 211));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 255)));
        jPanel3.setLayout(null);

        jAddress.setColumns(20);
        jAddress.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jAddress.setLineWrap(true);
        jAddress.setRows(3);
        jAddress.setWrapStyleWord(true);
        jAddress.setBorder(null);
        jScrollPane2.setViewportView(jAddress);

        jPanel3.add(jScrollPane2);
        jScrollPane2.setBounds(140, 260, 270, 70);

        jBirthdate.setDateFormatString("MMMM dd, yyyy");
        jBirthdate.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jPanel3.add(jBirthdate);
        jBirthdate.setBounds(140, 180, 270, 26);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("ADDRESS");
        jPanel3.add(jLabel1);
        jLabel1.setBounds(20, 260, 100, 20);

        jStatus.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ACTIVE", "INACTIVE" }));
        jPanel3.add(jStatus);
        jStatus.setBounds(140, 220, 270, 26);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("STUDENT ID");
        jPanel3.add(jLabel4);
        jLabel4.setBounds(20, 60, 100, 30);

        jStudentID.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jPanel3.add(jStudentID);
        jStudentID.setBounds(140, 60, 270, 26);

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("FULL NAME");
        jPanel3.add(jLabel5);
        jLabel5.setBounds(20, 100, 100, 20);

        jFullName.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jPanel3.add(jFullName);
        jFullName.setBounds(140, 100, 270, 26);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("DEPARTMENT");
        jPanel3.add(jLabel3);
        jLabel3.setBounds(20, 140, 110, 30);

        jDepartment.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jDepartment.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "BSIT", "BSBA", "BSCRIM", "BEED", "BSHM" }));
        jPanel3.add(jDepartment);
        jDepartment.setBounds(140, 140, 270, 26);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("STATUS");
        jPanel3.add(jLabel6);
        jLabel6.setBounds(20, 220, 100, 20);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("STUDENT DETAILS");
        jPanel3.add(jLabel2);
        jLabel2.setBounds(20, 10, 170, 30);

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("BIRTHDATE");
        jPanel3.add(jLabel8);
        jLabel8.setBounds(20, 180, 100, 20);

        jPanel1.add(jPanel3);
        jPanel3.setBounds(0, 0, 700, 400);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jInsertImageMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jInsertImageMouseClicked
        insertImage();
    }//GEN-LAST:event_jInsertImageMouseClicked

    private void jButtonSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveActionPerformed
        addStudent();
        
    }//GEN-LAST:event_jButtonSaveActionPerformed

    private void jButtonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelActionPerformed
        TeacherManageStudent manageStudent = new TeacherManageStudent();
        manageStudent.setVisible(true);
        manageStudent.pack();
        manageStudent.setLocationRelativeTo(null);
        this.dispose();
    }//GEN-LAST:event_jButtonCancelActionPerformed

    
    
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
            java.util.logging.Logger.getLogger(AddStudent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddStudent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddStudent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddStudent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddStudent().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea jAddress;
    private com.toedter.calendar.JDateChooser jBirthdate;
    private javax.swing.JButton jButtonCancel;
    private javax.swing.JButton jButtonSave;
    public javax.swing.JComboBox<String> jDepartment;
    private javax.swing.JTextField jFullName;
    private javax.swing.JLabel jImage;
    private javax.swing.JPanel jInsertImage;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JComboBox<String> jStatus;
    private javax.swing.JTextField jStudentID;
    // End of variables declaration//GEN-END:variables
}
