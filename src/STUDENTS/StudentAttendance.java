
package STUDENTS;

import config.myConnection;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import javax.swing.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import net.proteanit.sql.DbUtils;


public class StudentAttendance extends javax.swing.JFrame {
  
    private StringBuilder tagIdBuilder = new StringBuilder();
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private HashMap<Integer, LocalDateTime> studentCheckInTimes = new HashMap<>();
    private final List<StudentData> students = new ArrayList<>(); //arraylist in storing student data
    
    public StudentAttendance() {
        initComponents();
        //action listener for RFID
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evt) {
                handleRFIDScan(evt);
            }
        });
        ourDateTime(); //show date and time
        setTextOfJLabel(); //text setting of JLabel
        displayDataTable(); //display data in the table
        setImage();
    }

    //set default image
    public void setImage(){
        ImageIcon  image1 = new ImageIcon(new ImageIcon("src/icon/boyImage.png").getImage().getScaledInstance(jImage1.getWidth(), jImage1.getHeight(), Image.SCALE_SMOOTH));
        jImage1.setIcon(image1);
        ImageIcon  image2 = new ImageIcon(new ImageIcon("src/icon/boyImage.png").getImage().getScaledInstance(jImage2.getWidth(), jImage2.getHeight(), Image.SCALE_SMOOTH));
        jImage2.setIcon(image2);
        jImage3.setIcon(image2);
        jImage4.setIcon(image2);
        ImageIcon  scclogo = new ImageIcon(new ImageIcon("src/icon/scc logo.png").getImage().getScaledInstance(jsccLogo.getWidth(), jsccLogo.getHeight(), Image.SCALE_SMOOTH));
        jsccLogo.setIcon(scclogo);
        ImageIcon  itlogo = new ImageIcon(new ImageIcon("src/icon/IT_logo.png").getImage().getScaledInstance(jITlogo.getWidth(), jITlogo.getHeight(), Image.SCALE_SMOOTH));
        jITlogo.setIcon(itlogo);
    }
    //text alignment
    public void setTextOfJLabel(){
        jStudentName1.setHorizontalAlignment(SwingConstants.CENTER);
        jStudentName1.setText("STUDENT NAME");
        jStatus1.setHorizontalAlignment(SwingConstants.CENTER);
        jStatus1.setText("STATUS");
        jDepartment1.setHorizontalAlignment(SwingConstants.CENTER);
        jDepartment1.setText("DEPARTMENT");
        
        jStudentName2.setHorizontalAlignment(SwingConstants.CENTER);
        jStudentName2.setText("STUDENT NAME");
        jStatus2.setHorizontalAlignment(SwingConstants.CENTER);
        jStatus2.setText("");
        jDepartment2.setHorizontalAlignment(SwingConstants.CENTER);
        jDepartment2.setText("DEPARTMENT");
        
        jStudentName3.setHorizontalAlignment(SwingConstants.CENTER);
        jStudentName3.setText("STUDENT NAME");
        jStatus3.setHorizontalAlignment(SwingConstants.CENTER);
        jStatus3.setText("");
        jDepartment3.setHorizontalAlignment(SwingConstants.CENTER);
        jDepartment3.setText("DEPARTMENT");
        
        jStudentName4.setHorizontalAlignment(SwingConstants.CENTER);
        jStudentName4.setText("STUDENT NAME");
        jStatus4.setHorizontalAlignment(SwingConstants.CENTER);
        jStatus4.setText("");
        jDepartment4.setHorizontalAlignment(SwingConstants.CENTER);
        jDepartment4.setText("DEPARTMENT");
    }
    
    // Inner class to store student data
    private static class StudentData {
        String name;
        String department;
        ImageIcon image;
        String status;

        public StudentData(String name, String department, ImageIcon image, String status) {
            this.name = name;
            this.department = department;
            this.image = image;
            this.status = status;
        }
    }
    
    // Helper method to update an individual panel with student data
    private void updatePanel(JLabel imageLabel, JLabel nameLabel, JLabel departmentLabel, StudentData data, JLabel statusLabel) {
        Image images = data.image.getImage();
        Image scaledImage = images.getScaledInstance(imageLabel.getWidth(), imageLabel.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledImageIcon = new ImageIcon(scaledImage);
        imageLabel.setIcon(scaledImageIcon);
        
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        nameLabel.setText(data.name);
        
        departmentLabel.setHorizontalAlignment(SwingConstants.CENTER);
        departmentLabel.setText(data.department);
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        System.out.println(data.status);
        if(data.status.equalsIgnoreCase("CHECKED IN")){
            statusLabel.setForeground(Color.BLUE);
            statusLabel.setText("IN");
        }else{
            statusLabel.setForeground(Color.RED);
            statusLabel.setText("OUT");
        }
        
        
    }
    
    // Method to update the GUI panels with the latest student data
    private void updatePanels() {
        // Update first student panel
        if (students.size() >= 2) {
            updatePanel(jImage2, jStudentName2, jDepartment2, students.get(1), jStatus2);
        }
        // Update second student panel
        if (students.size() >= 3) {
            updatePanel(jImage3, jStudentName3, jDepartment4, students.get(2), jStatus3);
        }
        // Update third student panel
        if (students.size() >= 4) {
            updatePanel(jImage4, jStudentName4, jDepartment3, students.get(3), jStatus4);
        }
    }
    
    // Method to rotate student data
    private void rotateStudentData(String name, String department, ImageIcon image, String status) {
        students.add(0, new StudentData(name, department, image, status));

        if (students.size() > 4) {
            students.remove(students.size() - 1);
        }
    }
    
    //show data in the table
    public void displayDataTable(){
        jstudent_attendance.setFocusable(false);
        LocalDate today = LocalDate.now();
        String currentDate = today.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        try{
            myConnection dbc = new myConnection();
            ResultSet rs = dbc.getData("SELECT attendance_table.student_id, student.student_fullname, attendance_table.time_in, attendance_table.time_out, attendance_table.date "
                    + "FROM student "
                    + "INNER JOIN attendance_table ON student.student_id = attendance_table.student_id "
                    + "WHERE attendance_table.date = '" + currentDate + "'");
            jstudent_attendance.setModel(DbUtils.resultSetToTableModel(rs));

            // Disable cell editing by default
            jstudent_attendance.setDefaultEditor(Object.class, null);
            
            //color of the header
            jstudent_attendance.getTableHeader().setBackground(Color.decode("#3366FF"));
            
            //change the column header name
            TableColumnModel columnModel = jstudent_attendance.getColumnModel();
            columnModel.getColumn(0).setHeaderValue("Student ID");
            columnModel.getColumn(1).setHeaderValue("Full Name");
            columnModel.getColumn(2).setHeaderValue("Time In");
            columnModel.getColumn(3).setHeaderValue("Time Out");
            columnModel.getColumn(4).setHeaderValue("Date");
            
            //size of the row height
            jstudent_attendance.setRowHeight(40);
            
            //size of column by percent
            int totalWidth = jstudent_attendance.getWidth();
            double[] columnWidthPercentages = {15, 40, 15, 15, 15};
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
            Font headerFont = new Font("Segoe UI", Font.PLAIN, 25);
            jstudent_attendance.getTableHeader().setFont(headerFont);
            jstudent_attendance.getTableHeader().setForeground(Color.WHITE);

            //Set alignment for header labels
            TableCellRenderer rendererFromHeader = jstudent_attendance.getTableHeader().getDefaultRenderer();
            JLabel headerLabel = (JLabel) rendererFromHeader;
            headerLabel.setHorizontalAlignment(JLabel.CENTER);
            
            //Set cell renderer to center-align data in all columns 
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(JLabel.CENTER);
            jstudent_attendance.setDefaultRenderer(Object.class, centerRenderer);
            rs.close();
        }catch(SQLException ex){
            System.out.println("Errors: "+ex.getMessage());
        }
    }
    
    //display date and time
    public void ourDateTime() {
        Thread thread = new Thread(() -> {
            while (true) {
                LocalDateTime now = LocalDateTime.now();
                String formattedDateTime = now.format(DateTimeFormatter.ofPattern("EEEE MMMM dd, yyyy , hh:mm:ss a"));
                String upperCaseDateTime = formattedDateTime.toUpperCase();
                jDateTime.setHorizontalAlignment(SwingConstants.CENTER);
                jDateTime.setText("" + upperCaseDateTime);
                try {
                    Thread.sleep(1000); // Update every second
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }
    
    //get fullname of student
    public String getName(int student_rfid){
        myConnection connector = new myConnection();
        try{
            String query = "SELECT student_fullname FROM student WHERE student_rfid = '" + student_rfid + "'";
            ResultSet resultset = connector.getData(query);
            if(resultset.next()){
                return resultset.getString("student_fullname");
            }else{
                return null;
            }
        }catch(SQLException ex){
            System.out.println("" + ex.getMessage());
            return null;
        }
    }
    
    //get department of student
    public String getDepartment(int student_rfid){
        myConnection connector = new myConnection();
        try{
            String query = "SELECT student_department FROM student WHERE student_rfid = '" + student_rfid + "'";
            ResultSet resultset = connector.getData(query);
            if(resultset.next()){
                return resultset.getString("student_department");
            }else{
                return null;
            }
        }catch(SQLException ex){
            System.out.println("" + ex.getMessage());
            return null;
        }
    }
    
    //check if rfid exist and ACTIVE
    private boolean isIDRegister(int student_rfid){
        myConnection connector = new myConnection();
        try{
            String query = "SELECT * FROM student WHERE student_rfid = '" + student_rfid + "' AND student_status = 'ACTIVE'";
            ResultSet resultset = connector.getData(query);
            if(resultset.next()){
                return true;
            }else{
                return false;
            }
        }catch(SQLException ex){
            System.out.println("" + ex.getMessage());
            return false;
        }
    }
    

    //get image of the student
    public ImageIcon getStudentImage(int student_rfid){
        myConnection connector = new myConnection();
        try {
            String query = "SELECT student_image FROM student WHERE student_rfid = '" + student_rfid + "'";
            ResultSet resultSet = connector.getData(query);
            if (resultSet.next()) {
                byte[] imageData = resultSet.getBytes("student_image");
                ImageIcon imageIcon = new ImageIcon(imageData);
                return imageIcon;
            } else {
                return null;
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
            return null;
        }
    }
    
    //display image from database
    public void showImage(int student_rfid) {
        myConnection connector = new myConnection();
        try {
            String query = "SELECT student_image FROM student WHERE student_rfid = '" + student_rfid + "'";
            ResultSet resultSet = connector.getData(query);
            if (resultSet.next()) {
                byte[] imageData = resultSet.getBytes("student_image");
                ImageIcon imageIcon = new ImageIcon(imageData);
                Image image = imageIcon.getImage();
                Image scaledImage = image.getScaledInstance(jImage1.getWidth(), jImage1.getHeight(), Image.SCALE_SMOOTH);
                ImageIcon scaledImageIcon = new ImageIcon(scaledImage);
                jImage1.setIcon(scaledImageIcon);
            } else {
                System.out.println("No image found for student ID: " + student_rfid);
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }
    
    //get student ID
    public int getStudentID(int student_rfid){
        myConnection connector = new myConnection();
        try{
            String query = "SELECT student_id FROM student WHERE student_rfid = '" + student_rfid + "'";
            ResultSet resultset = connector.getData(query);
            if(resultset.next()){
                return resultset.getInt("student_id");
            }else{
                return 0;
            }
        }catch(SQLException ex){
            System.out.println("" + ex.getMessage());
            return 0;
        }
    }
    
    // Method to handle attendance logging
    private void handleAttendance(int student_rfid) {
        int id = getStudentID(student_rfid);
        LocalDateTime now = LocalDateTime.now();
        myConnection connector = new myConnection();
        Connection connect = connector.connect;
        String currentDate = now.format(DATE_FORMAT);

        try {
            // Check if there's an existing attendance record for the student today
            String checkAttendanceQuery = "SELECT * FROM attendance_table WHERE student_id = '" + id + "' AND date = '" + currentDate + "'";
            ResultSet attendanceResultSet = connector.getData(checkAttendanceQuery);
            
            // Check if the student is already checked out today
            ResultSet attendanceRs = connector.getData("SELECT time_out FROM attendance_table WHERE student_id = " + id + " AND date = '" + currentDate + "'");
            if (attendanceRs.next() && attendanceRs.getTime("time_out") != null) {
                return;
            }
            
            if (attendanceResultSet.next()) {
                // Student has already checked in, so update the timeOut value
                String updateTimeOutQuery = "UPDATE attendance_table SET time_out = ? WHERE student_id = ? AND date = ?";
                PreparedStatement updateTimeOutStmt = connect.prepareStatement(updateTimeOutQuery);
                updateTimeOutStmt.setTime(1, Time.valueOf(now.toLocalTime()));
                updateTimeOutStmt.setInt(2, id);
                updateTimeOutStmt.setString(3, currentDate);
                updateTimeOutStmt.executeUpdate();
                
                // Update status label to indicate student checked out
                Font font = new Font("Segoe UI", Font.BOLD, 50);
                jStatus1.setFont(font);
                jStatus1.setText("CHECKED OUT");
                jStatus1.setForeground(Color.RED);
            } else {
                // No existing attendance record, so create a new record with timeIn value
                String insertAttendanceQuery = "INSERT INTO attendance_table (student_id, date, time_in) VALUES (?, ?, ?)";
                PreparedStatement insertAttendanceStmt = connect.prepareStatement(insertAttendanceQuery);
                insertAttendanceStmt.setInt(1, id);
                insertAttendanceStmt.setString(2, currentDate);
                insertAttendanceStmt.setTime(3, Time.valueOf(now.toLocalTime()));
                insertAttendanceStmt.executeUpdate();
                
                // Update status label to indicate student checked in
                Font font = new Font("Segoe UI", Font.BOLD, 50);
                jStatus1.setFont(font);
                jStatus1.setText("CHECKED IN");
                jStatus1.setForeground(Color.BLUE);
                studentCheckInTimes.put(id, now);
            }
            
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
            System.out.println("Database error occurred. Please try again.");
        }
    }
    
    //check if the student has already checked in within the last minute
    private boolean hasCheckedInWithinLastMinute(int student_rfid) {
        LocalDateTime lastCheckInTime = studentCheckInTimes.get(student_rfid);
        if (lastCheckInTime != null) {
            LocalDateTime now = LocalDateTime.now();
            long timeDifferenceInSeconds = Duration.between(lastCheckInTime, now).getSeconds();
            return timeDifferenceInSeconds < 60;
        }
        return false;
    }

    //check if the student has already checked out today
    private boolean hasCheckedOutToday(int student_rfid) {
        int id = getStudentID(student_rfid);
        myConnection connector = new myConnection();
        String currentDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        try {
            String query = "SELECT * FROM attendance_table WHERE student_id = '" + id + "' AND date = '" + currentDate + "' AND time_out IS NOT NULL";
            ResultSet resultSet = connector.getData(query);
            return resultSet.next();
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
            return false;
        }
    }
    
    //hadle rfid when swipe
    private void handleRFIDScan(KeyEvent evt) {
        tagIdBuilder.append(evt.getKeyChar());
        jStudentName1.setHorizontalAlignment(SwingConstants.CENTER);
        jDepartment1.setHorizontalAlignment(SwingConstants.CENTER);

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String tagId = tagIdBuilder.toString().trim();
            tagIdBuilder.setLength(0);

            int student_rfid = 0;
            try {
                student_rfid = Integer.parseInt(tagId);
            } catch (NumberFormatException e) {
                System.out.println("Invalid RFID tag ID format.");
                return;
            }
            // Get student details
            String name = getName(student_rfid);
            String department = getDepartment(student_rfid);
            ImageIcon image = getStudentImage(student_rfid);
            
            if (name != null) {
                name = name.toUpperCase();
            }
            
            // Check if the student has already checked in within the last minute
            if (hasCheckedInWithinLastMinute(student_rfid)) {
                showImage(student_rfid);
                Font font = new Font("Segoe UI", Font.BOLD, 39);
                jStatus1.setFont(font);
                jStatus1.setText("WAIT ONE MINUTE BEFORE CHECKING OUT");
                jStatus1.setForeground(Color.RED);
                jDepartment1.setText(department);
                jStudentName1.setText(name);
                return;
            }
        
            // Check if the student has already checked out today
            if (hasCheckedOutToday(student_rfid)) {
                showImage(student_rfid);
                jStatus1.setText("ALREADY CHECKED OUT");
                jStatus1.setForeground(Color.RED);
                jDepartment1.setText(department);
                jStudentName1.setText(name);
                return;
            }
            
            
            if(isIDRegister(Integer.valueOf(tagId))){
                handleAttendance(student_rfid);
                showImage(student_rfid);
                jDepartment1.setText(department);
                jStudentName1.setText(name);
                rotateStudentData(name, department, image, jStatus1.getText());  // Rotate student data               
                updatePanels(); // Update panels with latest student data
            }else{
                Font font = new Font("Segoe UI", Font.BOLD, 50);
                jStatus1.setFont(font);
                jStatus1.setText("UNKNOWN STUDENT");
                jStatus1.setForeground(Color.RED);
                jStudentName1.setText("");
                jDepartment1.setText("");
                ImageIcon  image1 = new ImageIcon(new ImageIcon("src/icon/unknown profile.png").getImage().getScaledInstance(jImage1.getWidth(), jImage1.getHeight(), Image.SCALE_SMOOTH));
                jImage1.setIcon(image1);
            }
            evt.consume();
        }
        displayDataTable();
    }
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jImage1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jStatus1 = new javax.swing.JLabel();
        jDepartment1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jsccLogo = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jITlogo = new javax.swing.JLabel();
        jDateTime = new javax.swing.JLabel();
        jStudentName1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jStudentName2 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jStatus2 = new javax.swing.JLabel();
        jDepartment2 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jStudentName3 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jStatus3 = new javax.swing.JLabel();
        jDepartment3 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jStudentName4 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jStatus4 = new javax.swing.JLabel();
        jDepartment4 = new javax.swing.JLabel();
        jImage2 = new javax.swing.JLabel();
        jImage3 = new javax.swing.JLabel();
        jImage4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jstudent_attendance = new javax.swing.JTable();

        jTextField1.setText("jTextField1");

        jLabel1.setText("jLabel1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("STUDENT ATTENDANCE");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(25, 118, 211));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(25, 118, 211), 2));
        jPanel1.setPreferredSize(new java.awt.Dimension(1920, 1080));
        jPanel1.setLayout(null);

        jImage1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jPanel1.add(jImage1);
        jImage1.setBounds(90, 210, 640, 590);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(25, 118, 211)));
        jPanel2.setLayout(null);

        jStatus1.setBackground(new java.awt.Color(25, 118, 211));
        jStatus1.setFont(new java.awt.Font("Segoe UI", 1, 50)); // NOI18N
        jStatus1.setText("time in");
        jPanel2.add(jStatus1);
        jStatus1.setBounds(0, 0, 820, 80);

        jPanel1.add(jPanel2);
        jPanel2.setBounds(0, 820, 820, 80);

        jDepartment1.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        jDepartment1.setForeground(new java.awt.Color(255, 255, 255));
        jDepartment1.setText("department");
        jPanel1.add(jDepartment1);
        jDepartment1.setBounds(0, 976, 820, 64);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(25, 118, 211)));
        jPanel3.setLayout(null);
        jPanel3.add(jsccLogo);
        jsccLogo.setBounds(10, 10, 180, 130);

        jLabel4.setBackground(new java.awt.Color(0, 153, 255));
        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 100)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(40, 110, 210));
        jLabel4.setText("St. Cecilia's College - Cebu, Inc.");
        jPanel3.add(jLabel4);
        jLabel4.setBounds(220, 0, 1520, 140);
        jPanel3.add(jITlogo);
        jITlogo.setBounds(1740, 10, 145, 135);

        jPanel1.add(jPanel3);
        jPanel3.setBounds(0, 0, 1920, 150);

        jDateTime.setBackground(new java.awt.Color(255, 255, 255));
        jDateTime.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        jDateTime.setForeground(new java.awt.Color(255, 255, 255));
        jDateTime.setText("                        TODAY IS DAY, MONTH DATE");
        jPanel1.add(jDateTime);
        jDateTime.setBounds(0, 160, 820, 30);

        jStudentName1.setFont(new java.awt.Font("Segoe UI", 1, 50)); // NOI18N
        jStudentName1.setForeground(new java.awt.Color(255, 255, 255));
        jStudentName1.setText("name");
        jPanel1.add(jStudentName1);
        jStudentName1.setBounds(0, 900, 820, 70);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(null);

        jStudentName2.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        jStudentName2.setText("Name");
        jPanel4.add(jStudentName2);
        jStudentName2.setBounds(0, 0, 280, 34);

        jPanel6.setLayout(null);

        jStatus2.setBackground(new java.awt.Color(204, 204, 204));
        jStatus2.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        jStatus2.setText("OUT");
        jPanel6.add(jStatus2);
        jStatus2.setBounds(0, 0, 70, 70);

        jPanel4.add(jPanel6);
        jPanel6.setBounds(280, 0, 70, 70);

        jDepartment2.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        jDepartment2.setText("Department");
        jPanel4.add(jDepartment2);
        jDepartment2.setBounds(0, 38, 280, 32);

        jPanel1.add(jPanel4);
        jPanel4.setBounds(820, 530, 350, 70);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setLayout(null);

        jStudentName3.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        jStudentName3.setText("Name");
        jPanel5.add(jStudentName3);
        jStudentName3.setBounds(0, 0, 280, 34);

        jPanel7.setLayout(null);

        jStatus3.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        jStatus3.setText("IN");
        jPanel7.add(jStatus3);
        jStatus3.setBounds(0, 0, 70, 70);

        jPanel5.add(jPanel7);
        jPanel7.setBounds(280, 0, 70, 70);

        jDepartment3.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        jDepartment3.setText("Department");
        jPanel5.add(jDepartment3);
        jDepartment3.setBounds(0, 38, 280, 32);

        jPanel1.add(jPanel5);
        jPanel5.setBounds(1190, 530, 350, 70);

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setLayout(null);

        jStudentName4.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        jStudentName4.setText("Name");
        jPanel10.add(jStudentName4);
        jStudentName4.setBounds(0, 0, 280, 34);

        jPanel8.setLayout(null);

        jStatus4.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        jStatus4.setText("IN");
        jPanel8.add(jStatus4);
        jStatus4.setBounds(0, 0, 70, 70);

        jPanel10.add(jPanel8);
        jPanel8.setBounds(280, 0, 70, 70);

        jDepartment4.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        jDepartment4.setText("Department");
        jPanel10.add(jDepartment4);
        jDepartment4.setBounds(0, 38, 280, 32);

        jPanel1.add(jPanel10);
        jPanel10.setBounds(1560, 530, 350, 70);

        jImage2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jPanel1.add(jImage2);
        jImage2.setBounds(820, 160, 350, 350);

        jImage3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jPanel1.add(jImage3);
        jImage3.setBounds(1190, 160, 350, 350);

        jImage4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jPanel1.add(jImage4);
        jImage4.setBounds(1560, 160, 350, 350);

        jstudent_attendance.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jstudent_attendance.setForeground(new java.awt.Color(51, 51, 51));
        jstudent_attendance.setAlignmentX(2.0F);
        jstudent_attendance.setAlignmentY(2.0F);
        jScrollPane1.setViewportView(jstudent_attendance);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(820, 620, 1090, 450);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        
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
            java.util.logging.Logger.getLogger(StudentAttendance.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StudentAttendance.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StudentAttendance.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StudentAttendance.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        
        
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new StudentAttendance().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jDateTime;
    private javax.swing.JLabel jDepartment1;
    private javax.swing.JLabel jDepartment2;
    private javax.swing.JLabel jDepartment3;
    private javax.swing.JLabel jDepartment4;
    private javax.swing.JLabel jITlogo;
    private javax.swing.JLabel jImage1;
    private javax.swing.JLabel jImage2;
    private javax.swing.JLabel jImage3;
    private javax.swing.JLabel jImage4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel jStatus1;
    private javax.swing.JLabel jStatus2;
    private javax.swing.JLabel jStatus3;
    private javax.swing.JLabel jStatus4;
    public javax.swing.JLabel jStudentName1;
    private javax.swing.JLabel jStudentName2;
    private javax.swing.JLabel jStudentName3;
    private javax.swing.JLabel jStudentName4;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel jsccLogo;
    private javax.swing.JTable jstudent_attendance;
    // End of variables declaration//GEN-END:variables
}
