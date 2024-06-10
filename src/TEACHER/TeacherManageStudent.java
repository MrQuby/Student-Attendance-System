
package TEACHER;



import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import config.LogsHistory;
import config.Session;
import config.myConnection;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JPanel;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.ColorUIResource;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import net.proteanit.sql.DbUtils;
import student.attendance.system.LoginPage;



public class TeacherManageStudent extends javax.swing.JFrame {

    
    
    public TeacherManageStudent() {
        initComponents();
        ourDateTime();
        ImageIcon  image2 = new ImageIcon(new ImageIcon("src/icon/IT_logo.png").getImage().getScaledInstance(ITLogo.getWidth(), ITLogo.getHeight(), Image.SCALE_SMOOTH));
        ITLogo.setIcon(image2);
        ImageIcon  image3 = new ImageIcon(new ImageIcon("src/icon/scc logo.png").getImage().getScaledInstance(SCCLogo.getWidth(), SCCLogo.getHeight(), Image.SCALE_SMOOTH));
        SCCLogo.setIcon(image3);
        displayDataTable();
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
    
    //display name and role
    public void displayNameAndRole(){
        Session session = Session.getInstance();
        String name = session.getFullname();
        jAdminName.setText(name);
    }
    
    //set color
    public void setColor(JPanel panel) {
        panel.setBackground(new Color(80,140,210));
    }
    
    //reset color
    public void resetColor(JPanel panel) {
        panel.setBackground(new Color(40,110,210));
    }
    //jtable settings
    public void tableSettings(){
        // Disable cell editing by default
        jstudent_Table.setDefaultEditor(Object.class, null);

        //color of the header
        jstudent_Table.getTableHeader().setBackground(Color.decode("#3399ff"));

        //change the column header name
        TableColumnModel columnModel = jstudent_Table.getColumnModel();
        columnModel.getColumn(0).setHeaderValue("Student ID");
        columnModel.getColumn(1).setHeaderValue("Student RFID");
        columnModel.getColumn(2).setHeaderValue("Full Name");
        columnModel.getColumn(3).setHeaderValue("Birthdate");
        columnModel.getColumn(4).setHeaderValue("Department");
        columnModel.getColumn(5).setHeaderValue("Address");
        columnModel.getColumn(6).setHeaderValue("Status");

        //size of the row height
        jstudent_Table.setRowHeight(20);
        
        //size of column by percent
        int totalWidth = jstudent_Table.getWidth();
        double[] columnWidthPercentages = {10, 10, 20, 10, 10, 24, 8};
        int[] columnWidths = new int[columnWidthPercentages.length];

        for (int i = 0; i < columnWidthPercentages.length; i++) {
            columnWidths[i] = (int) (totalWidth * (columnWidthPercentages[i] / 100.0));
        }

        columnModel.getColumn(0).setPreferredWidth(columnWidths[0]);
        columnModel.getColumn(1).setPreferredWidth(columnWidths[1]);
        columnModel.getColumn(2).setPreferredWidth(columnWidths[2]);
        columnModel.getColumn(3).setPreferredWidth(columnWidths[3]);
        columnModel.getColumn(4).setPreferredWidth(columnWidths[4]);
        columnModel.getColumn(5).setPreferredWidth(columnWidths[5]);
        columnModel.getColumn(6).setPreferredWidth(columnWidths[6]);
        
        // Set font properties for the header
        Font headerFont = new Font("Segoe UI", Font.PLAIN, 14);
        jstudent_Table.getTableHeader().setFont(headerFont);
        jstudent_Table.getTableHeader().setForeground(Color.WHITE);

        //Set alignment for header labels
        TableCellRenderer rendererFromHeader = jstudent_Table.getTableHeader().getDefaultRenderer();
        JLabel headerLabel = (JLabel) rendererFromHeader;
        headerLabel.setHorizontalAlignment(JLabel.CENTER);
        
        //Set cell renderer to center-align data in all columns 
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        jstudent_Table.setDefaultRenderer(Object.class, centerRenderer);
    }
    
    //show data in the table
    public void displayDataTable(){
        try{
            myConnection dbc = new myConnection();
            ResultSet rs = dbc.getData("SELECT student_id,student_rfid,student_fullname,student_birthdate,student_department,student_address,student_status FROM student WHERE student_archive = 'NO'");
            jstudent_Table.setModel(DbUtils.resultSetToTableModel(rs));
            
            //clear the searchfield if click to table
            jstudent_Table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                public void valueChanged(ListSelectionEvent e) {
                    if (!e.getValueIsAdjusting()) { // Ensure that the event is not fired multiple times
                        int selectedRow = jstudent_Table.getSelectedRow();
                        if (selectedRow != -1) { // If a row is selected
                            Object studentID = jstudent_Table.getValueAt(selectedRow, 0); // Assuming student ID is in the first column
                            jSearchField.setText("search student ID");
                            jError.setText("");
                        }
                    }
                }
            });
            
            tableSettings();
            rs.close();
        }catch(SQLException ex){
            System.out.println("Errors: "+ex.getMessage());
        }
    }
    
    
    
    //archieve the selected ID
    private void archiveSelectedRow() {
        int selectedRow = jstudent_Table.getSelectedRow();
        if (selectedRow >= 0) {
            int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to archive this record?", "", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                int studentID = (int)jstudent_Table.getValueAt(selectedRow, 0); // Assuming student ID is in the first column
                String query = "UPDATE student SET student_archive = 'YES',student_status = 'INACTIVE' WHERE student_id = '" + studentID + "'";
                myConnection dbc = new myConnection();
                if(dbc.insertData(query)){
                    showSuccessDialog("Student ID " + studentID + " Record archive successfully.");
                    //history logs
                    Session session = Session.getInstance();
                    int teacherID = session.getId();
                    LogsHistory logHistory = new LogsHistory();
                    logHistory.insertTeacherLog(teacherID, "Teacher archived student with ID number " + studentID);
                    
                    displayDataTable();
                }else{
                    JOptionPane.showMessageDialog(null, "Failed to archive record.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Please select a row to archive.", "No Row Selected", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    
    //show data to selected row
    public void updateData(){
        int rowindex = jstudent_Table.getSelectedRow();
        if(rowindex < 0){
            showErrorDialog("Please select a student");
            return;
        } else {
            TableModel model = jstudent_Table.getModel();
            UpdateStudent updateFrame = new UpdateStudent();
            int studentID = (int) model.getValueAt(rowindex, 0);
            updateFrame.jStudentID.setText("" + (int)model.getValueAt(rowindex, 0));
            updateFrame.jStudentID.setEditable(false);
            updateFrame.jStudentRFID.setText("" + (int)model.getValueAt(rowindex, 1));
            updateFrame.jFullName.setText("" + model.getValueAt(rowindex, 2));

            try {
                java.sql.Date sqlDate = (java.sql.Date) model.getValueAt(rowindex, 3);
                Date utilDate = new Date(sqlDate.getTime());
                updateFrame.jBirthdate.setDate(utilDate);
            } catch (IllegalArgumentException ex) {
                System.out.println(ex.getMessage());
            }
            
            updateFrame.jDepartment.setSelectedItem("" + model.getValueAt(rowindex, 4));
            updateFrame.jAddress.setText("" + model.getValueAt(rowindex, 5));
            updateFrame.jStatus.setSelectedItem("" + model.getValueAt(rowindex, 6));
            
            updateFrame.setVisible(true);
            updateFrame.pack();
            updateFrame.setLocationRelativeTo(null);
            updateFrame.showImage(studentID);
            this.dispose();
        }
    }
    
    //search data in table
    public void searchData(){
        String searchText = jSearchField.getText();
        if (!searchText.isEmpty()) {
            try {
                jError.setText("");
                myConnection dbc = new myConnection();
                int studentID = Integer.parseInt(searchText);
                String query = "SELECT student_id, student_rfid, student_fullname, student_birthdate, student_department, student_address, student_status FROM student WHERE student_archive = 'NO' AND student_id = '"+ studentID+"'";
                ResultSet rs = dbc.getData(query);
                jstudent_Table.setModel(DbUtils.resultSetToTableModel(rs));
                tableSettings();
                rs.close();
            } catch (NumberFormatException ex) {
                jError.setText("Invalid ID");
            } catch (SQLException ex) {
                System.out.println("Errors: " + ex.getMessage());
            }
        } else {
            // If search field is empty, display all data
            jError.setText("");
            displayDataTable();
        }
    }
    
    public void exportTableDataToExcel() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save Table Data as Excel");
        int userSelection = fileChooser.showSaveDialog(this);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            String filePath = fileChooser.getSelectedFile().getAbsolutePath();
            if (!filePath.toLowerCase().endsWith(".xlsx")) {
                filePath += ".xlsx";
            }
            try (Workbook workbook = new XSSFWorkbook()) {
                Sheet sheet = workbook.createSheet("Table Data");

                TableModel model = jstudent_Table.getModel();

                // Write column headers
                Row headerRow = sheet.createRow(0);
                for (int col = 0; col < model.getColumnCount(); col++) {
                    Cell cell = headerRow.createCell(col);
                    cell.setCellValue(model.getColumnName(col));
                }

                // Write row data
                for (int row = 0; row < model.getRowCount(); row++) {
                    Row dataRow = sheet.createRow(row + 1);
                    for (int col = 0; col < model.getColumnCount(); col++) {
                        Cell cell = dataRow.createCell(col);
                        Object cellData = model.getValueAt(row, col);
                        if (cellData != null) {
                            cell.setCellValue(cellData.toString());
                        }
                    }
                }

                try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
                    workbook.write(fileOut);
                }

                JOptionPane.showMessageDialog(this, "Table data exported successfully to " + filePath, "Export Success", JOptionPane.INFORMATION_MESSAGE);
                Session session = Session.getInstance();
                int id = session.getId();
                LogsHistory logHistory = new LogsHistory();
                logHistory.insertTeacherLog(id, "Teacher exported Excel attendance table data");
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error exporting data: " + e.getMessage(), "Export Error", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        }
    }
    
    public void importExcelData() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Open Excel File");
        // Add filter for Excel files
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Excel Files", "xlsx", "xls");
        fileChooser.setFileFilter(filter);
        int userSelection = fileChooser.showOpenDialog(this);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();

            try (FileInputStream fis = new FileInputStream(file);
                 Workbook workbook = new XSSFWorkbook(fis)) {

                Sheet sheet = workbook.getSheetAt(0);
                Iterator<Row> rowIterator = sheet.iterator();

                if (!rowIterator.hasNext()) {
                    throw new IOException("The Excel file is empty.");
                }

                // Read the headers
                Row headerRow = rowIterator.next();
                Iterator<Cell> headerCellIterator = headerRow.cellIterator();
                List<String> headers = new ArrayList<>();

                while (headerCellIterator.hasNext()) {
                    headers.add(headerCellIterator.next().getStringCellValue());
                }

                DefaultTableModel model = new DefaultTableModel(headers.toArray(), 0);
                List<String[]> data = new ArrayList<>();

                while (rowIterator.hasNext()) {
                    Row row = rowIterator.next();
                    String[] rowData = new String[headers.size()];
                    for (int col = 0; col < headers.size(); col++) {
                        Cell cell = row.getCell(col);
                        if (cell != null) {
                            rowData[col] = cell.toString();
                        } else {
                            rowData[col] = "";
                        }
                    }
                    data.add(rowData);
                    model.addRow(rowData);
                }

                jstudent_Table.setModel(model);
                tableSettings(); // Apply table settings

                // Insert or update database
                insertOrUpdateDatabase(data);

                JOptionPane.showMessageDialog(this, "Excel data imported successfully.", "Import Success", JOptionPane.INFORMATION_MESSAGE);
                Session session = Session.getInstance();
                int id = session.getId();
                LogsHistory logHistory = new LogsHistory();
                logHistory.insertTeacherLog(id, "Teacher imported Excel attendance table data");

            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error importing data: " + e.getMessage(), "Import Error", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        }
    }

    private void insertOrUpdateDatabase(List<String[]> data) {
        myConnection dbc = new myConnection();
        Connection connector = dbc.connect;

        try {
            for (String[] row : data) {
                if (row.length != 7) {
                    System.out.println("Skipping invalid row: " + String.join(", ", row));
                    continue;
                }

                String studentId = row[0].trim();
                String studentRfid = row[1].trim();
                String fullName = row[2].trim();
                String birthdate = row[3].trim();
                String department = row[4].trim();
                String address = row[5].trim();
                String status = row[6].trim();

                // Check if record exists
                String checkQuery = "SELECT * FROM student WHERE student_id = ?";
                try (PreparedStatement checkStmt = connector.prepareStatement(checkQuery)) {
                    checkStmt.setString(1, studentId);
                    ResultSet rs = checkStmt.executeQuery();

                    if (rs.next()) {
                        // Update existing record
                        String updateQuery = "UPDATE student SET student_rfid = ?, student_fullname = ?, student_birthdate = ?, student_department = ?, student_address = ?, student_status = ?, student_archive = 'NO' WHERE student_id = ?";
                        try (PreparedStatement pstmt = connector.prepareStatement(updateQuery)) {
                            pstmt.setString(1, studentRfid);
                            pstmt.setString(2, fullName);
                            pstmt.setString(3, birthdate);
                            pstmt.setString(4, department);
                            pstmt.setString(5, address);
                            pstmt.setString(6, status);
                            pstmt.setString(7, studentId);
                            pstmt.executeUpdate();
                        }
                    } else {
                        // Insert new record
                        String insertQuery = "INSERT INTO student (student_id, student_rfid, student_fullname, student_birthdate, student_department, student_address, student_status, student_archive) VALUES (?, ?, ?, ?, ?, ?, ?, 'NO')";
                        try (PreparedStatement pstmt = connector.prepareStatement(insertQuery)) {
                            pstmt.setString(1, studentId);
                            pstmt.setString(2, studentRfid);
                            pstmt.setString(3, fullName);
                            pstmt.setString(4, birthdate);
                            pstmt.setString(5, department);
                            pstmt.setString(6, address);
                            pstmt.setString(7, status);
                            pstmt.executeUpdate();
                        }
                    }
                    rs.close();
                }
            }
        } catch (SQLException e) {
            System.out.println("Error updating database: " + e.getMessage());
            e.printStackTrace();
        }
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
        jArchiveStudents = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLogoutButton1 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jAdminName = new javax.swing.JLabel();
        datetime = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jAddButton = new javax.swing.JButton();
        jUpdateButton = new javax.swing.JButton();
        jButtonRefreshData = new javax.swing.JButton();
        jSearchField = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jstudent_Table = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        SCCLogo = new javax.swing.JLabel();
        ITLogo = new javax.swing.JLabel();
        jError = new javax.swing.JLabel();
        jArchive = new javax.swing.JButton();
        jButtonRefreshData1 = new javax.swing.JButton();
        jButtonRefreshData2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("TEACHER MANAGE STUDENT");
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
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jArchiveStudentsMousePressed(evt);
            }
        });
        jArchiveStudents.setLayout(null);

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/ARCHIEVE.png"))); // NOI18N
        jLabel11.setText("  Archive Student");
        jArchiveStudents.add(jLabel11);
        jLabel11.setBounds(30, 0, 170, 50);

        jPanel2.add(jArchiveStudents);
        jArchiveStudents.setBounds(0, 340, 220, 50);

        jLogoutButton1.setBackground(new java.awt.Color(40, 110, 210));
        jLogoutButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLogoutButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLogoutButton1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLogoutButton1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLogoutButton1MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLogoutButton1MousePressed(evt);
            }
        });
        jLogoutButton1.setLayout(null);

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/logout.png"))); // NOI18N
        jLabel12.setText("  Logout");
        jLogoutButton1.add(jLabel12);
        jLabel12.setBounds(30, 0, 130, 50);

        jPanel2.add(jLogoutButton1);
        jLogoutButton1.setBounds(0, 440, 220, 50);

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

        jLabel8.setBackground(new java.awt.Color(25, 118, 211));
        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(25, 118, 211));
        jLabel8.setText("Student List");
        jPanel1.add(jLabel8);
        jLabel8.setBounds(230, 210, 120, 25);

        jAddButton.setBackground(new java.awt.Color(51, 153, 255));
        jAddButton.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jAddButton.setForeground(new java.awt.Color(255, 255, 255));
        jAddButton.setText("ADD NEW");
        jAddButton.setContentAreaFilled(false);
        jAddButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jAddButton.setFocusPainted(false);
        jAddButton.setOpaque(true);
        jAddButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAddButtonActionPerformed(evt);
            }
        });
        jPanel1.add(jAddButton);
        jAddButton.setBounds(230, 260, 87, 24);

        jUpdateButton.setBackground(new java.awt.Color(51, 153, 255));
        jUpdateButton.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jUpdateButton.setForeground(new java.awt.Color(255, 255, 255));
        jUpdateButton.setText("UPDATE");
        jUpdateButton.setContentAreaFilled(false);
        jUpdateButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jUpdateButton.setFocusPainted(false);
        jUpdateButton.setOpaque(true);
        jUpdateButton.setPreferredSize(new java.awt.Dimension(85, 24));
        jUpdateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jUpdateButtonActionPerformed(evt);
            }
        });
        jPanel1.add(jUpdateButton);
        jUpdateButton.setBounds(330, 260, 85, 24);

        jButtonRefreshData.setBackground(new java.awt.Color(51, 153, 255));
        jButtonRefreshData.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jButtonRefreshData.setForeground(new java.awt.Color(255, 255, 255));
        jButtonRefreshData.setText("REFRESH");
        jButtonRefreshData.setContentAreaFilled(false);
        jButtonRefreshData.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonRefreshData.setFocusPainted(false);
        jButtonRefreshData.setOpaque(true);
        jButtonRefreshData.setPreferredSize(new java.awt.Dimension(85, 24));
        jButtonRefreshData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRefreshDataActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonRefreshData);
        jButtonRefreshData.setBounds(530, 260, 85, 24);

        jSearchField.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jSearchField.setText("search student ID");
        jSearchField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jSearchFieldMouseClicked(evt);
            }
        });
        jSearchField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jSearchFieldKeyReleased(evt);
            }
        });
        jPanel1.add(jSearchField);
        jSearchField.setBounds(960, 260, 130, 24);

        jstudent_Table.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jstudent_Table.setForeground(new java.awt.Color(51, 51, 51));
        jstudent_Table.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jScrollPane1.setViewportView(jstudent_Table);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(230, 300, 860, 380);

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

        jError.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jError.setForeground(new java.awt.Color(255, 0, 0));
        jError.setText("   ");
        jPanel1.add(jError);
        jError.setBounds(960, 240, 130, 16);

        jArchive.setBackground(new java.awt.Color(51, 153, 255));
        jArchive.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jArchive.setForeground(new java.awt.Color(255, 255, 255));
        jArchive.setText("ARCHIVE");
        jArchive.setContentAreaFilled(false);
        jArchive.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jArchive.setFocusPainted(false);
        jArchive.setOpaque(true);
        jArchive.setPreferredSize(new java.awt.Dimension(85, 24));
        jArchive.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jArchiveActionPerformed(evt);
            }
        });
        jPanel1.add(jArchive);
        jArchive.setBounds(430, 260, 85, 24);

        jButtonRefreshData1.setBackground(new java.awt.Color(51, 153, 255));
        jButtonRefreshData1.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jButtonRefreshData1.setForeground(new java.awt.Color(255, 255, 255));
        jButtonRefreshData1.setText("IMPORT CSV");
        jButtonRefreshData1.setContentAreaFilled(false);
        jButtonRefreshData1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonRefreshData1.setFocusPainted(false);
        jButtonRefreshData1.setOpaque(true);
        jButtonRefreshData1.setPreferredSize(new java.awt.Dimension(85, 24));
        jButtonRefreshData1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRefreshData1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonRefreshData1);
        jButtonRefreshData1.setBounds(750, 260, 110, 24);

        jButtonRefreshData2.setBackground(new java.awt.Color(51, 153, 255));
        jButtonRefreshData2.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jButtonRefreshData2.setForeground(new java.awt.Color(255, 255, 255));
        jButtonRefreshData2.setText("EXPORT CSV");
        jButtonRefreshData2.setContentAreaFilled(false);
        jButtonRefreshData2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonRefreshData2.setFocusPainted(false);
        jButtonRefreshData2.setOpaque(true);
        jButtonRefreshData2.setPreferredSize(new java.awt.Dimension(85, 24));
        jButtonRefreshData2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRefreshData2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonRefreshData2);
        jButtonRefreshData2.setBounds(630, 260, 110, 24);

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

    //profile button
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
        TeacherManageStudent manageStudentFrame = new TeacherManageStudent();
        manageStudentFrame.setVisible(true);
        manageStudentFrame.pack();
        manageStudentFrame.setLocationRelativeTo(null);
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
        displayNameAndRole();
    }//GEN-LAST:event_formWindowActivated

    private void jProfileSettingsAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jProfileSettingsAncestorAdded
        
    }//GEN-LAST:event_jProfileSettingsAncestorAdded

    //add button
    private void jAddButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAddButtonActionPerformed
        AddStudent addStudentFrame = new AddStudent();
        addStudentFrame.setVisible(true);
        addStudentFrame.pack();
        addStudentFrame.setLocationRelativeTo(null);
        this.dispose();
    }//GEN-LAST:event_jAddButtonActionPerformed

    //update button
    private void jUpdateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jUpdateButtonActionPerformed
        updateData();
    }//GEN-LAST:event_jUpdateButtonActionPerformed

    //refresh button
    private void jButtonRefreshDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRefreshDataActionPerformed
        jError.setText("");
        jSearchField.setText("search student ID");
        displayDataTable();
    }//GEN-LAST:event_jButtonRefreshDataActionPerformed

    //when mouse click to search textfield
    private void jSearchFieldMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jSearchFieldMouseClicked
        jSearchField.setText("");
    }//GEN-LAST:event_jSearchFieldMouseClicked

    //search textfield
    private void jSearchFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jSearchFieldKeyReleased
        searchData();
    }//GEN-LAST:event_jSearchFieldKeyReleased

    //logout button
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

    private void jArchiveStudentsMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jArchiveStudentsMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jArchiveStudentsMousePressed

    private void jArchiveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jArchiveActionPerformed
        archiveSelectedRow();
    }//GEN-LAST:event_jArchiveActionPerformed

    private void jLogoutButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLogoutButton1MouseClicked
        int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to logout?", "", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION){
            //history logs
            Session session = Session.getInstance();
            int teacherID = session.getId();
            LogsHistory logHistory = new LogsHistory();
            logHistory.insertTeacherLog(teacherID, "Teacher Logout");

            LoginPage loginFrame = new LoginPage();
            loginFrame.setVisible(true);
            loginFrame.pack();
            loginFrame.setLocationRelativeTo(null);
            this.dispose();
        }
    }//GEN-LAST:event_jLogoutButton1MouseClicked

    private void jLogoutButton1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLogoutButton1MouseEntered
        setColor(jLogoutButton1);
    }//GEN-LAST:event_jLogoutButton1MouseEntered

    private void jLogoutButton1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLogoutButton1MouseExited
        resetColor(jLogoutButton1);
    }//GEN-LAST:event_jLogoutButton1MouseExited

    private void jLogoutButton1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLogoutButton1MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jLogoutButton1MousePressed

    private void jButtonRefreshData1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRefreshData1ActionPerformed
        importExcelData();
    }//GEN-LAST:event_jButtonRefreshData1ActionPerformed

    private void jButtonRefreshData2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRefreshData2ActionPerformed
        exportTableDataToExcel();
    }//GEN-LAST:event_jButtonRefreshData2ActionPerformed

    
    
    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TeacherManageStudent().setVisible(true);
            }
        });
        
        
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ITLogo;
    private javax.swing.JLabel SCCLogo;
    private javax.swing.JLabel datetime;
    private javax.swing.JButton jAddButton;
    public javax.swing.JLabel jAdminName;
    private javax.swing.JButton jArchive;
    private javax.swing.JPanel jArchiveStudents;
    private javax.swing.JPanel jAttendance;
    private javax.swing.JButton jButtonRefreshData;
    private javax.swing.JButton jButtonRefreshData1;
    private javax.swing.JButton jButtonRefreshData2;
    private javax.swing.JPanel jDashboard;
    private javax.swing.JLabel jError;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jLogoutButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jProfileSettings;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jSearchField;
    private javax.swing.JPanel jStudent;
    private javax.swing.JButton jUpdateButton;
    private javax.swing.JTable jstudent_Table;
    // End of variables declaration//GEN-END:variables
}
