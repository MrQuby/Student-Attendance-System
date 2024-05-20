
package TEACHER;




import config.LogsHistory;
import config.Session;
import config.myConnection;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import javax.swing.JPanel;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import net.proteanit.sql.DbUtils;
import student.attendance.system.LoginPage;



public class TeacherAttendance extends javax.swing.JFrame {

    final String placeholderText = "filter by date";
    final String searchID = "search student ID";

    public TeacherAttendance() {
        initComponents();
        
        JFormattedTextField dateField = (JFormattedTextField) jDateSearch.getDateEditor().getUiComponent();
        dateField.setText(placeholderText);
        jSearchID.setText(searchID);
        ourDateTime();
        ImageIcon  image2 = new ImageIcon(new ImageIcon("src/icon/IT_logo.png").getImage().getScaledInstance(ITLogo.getWidth(), ITLogo.getHeight(), Image.SCALE_SMOOTH));
        ITLogo.setIcon(image2);
        ImageIcon  image3 = new ImageIcon(new ImageIcon("src/icon/logo.png").getImage().getScaledInstance(SCCLogo.getWidth(), SCCLogo.getHeight(), Image.SCALE_SMOOTH));
        SCCLogo.setIcon(image3);
        displayDataTable();
        
        // Set up a property change listener for the date property of the JDateChooser
        jDateSearch.addPropertyChangeListener("date", new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {               
                searchData();
            }
        }); 
    }

    
    //export table to excel file
    public void exportTableDataToCSV() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save Table Data as CSV");
        int userSelection = fileChooser.showSaveDialog(this);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            String filePath = fileChooser.getSelectedFile().getAbsolutePath();
            if (!filePath.toLowerCase().endsWith(".csv")) {
                filePath += ".csv";
            }
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
                TableModel model = jstudent_attendance.getModel();
                for (int col = 0; col < model.getColumnCount(); col++) {
                    writer.write(model.getColumnName(col));
                    if (col < model.getColumnCount() - 1) {
                        writer.write(",");
                    }
                }
                writer.newLine();

                for (int row = 0; row < model.getRowCount(); row++) {
                    for (int col = 0; col < model.getColumnCount(); col++) {
                        Object cellData = model.getValueAt(row, col);
                        if (cellData != null) {
                            writer.write(cellData.toString());
                        }
                        if (col < model.getColumnCount() - 1) {
                            writer.write(",");
                        }
                    }
                    writer.newLine();
                }
                JOptionPane.showMessageDialog(this, "Table data exported successfully to " + filePath, "Export Success", JOptionPane.INFORMATION_MESSAGE);
                Session session = Session.getInstance();
                int id = session.getId();
                LogsHistory logHistory = new LogsHistory();
                logHistory.insertTeacherLog(id, "Teacher export-CSV attendance table data");
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error exporting data: " + e.getMessage(), "Export Error", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        }
    }
    
    //display time
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
    
    //jTable settings
    public void tableSettings(){
        // Disable cell editing by default
        jstudent_attendance.setDefaultEditor(Object.class, null);

        //color of the header
        jstudent_attendance.getTableHeader().setBackground(Color.decode("#3399ff"));

        //change the column header name
        TableColumnModel columnModel = jstudent_attendance.getColumnModel();
        columnModel.getColumn(0).setHeaderValue("Student ID");
        columnModel.getColumn(1).setHeaderValue("Full Name");
        columnModel.getColumn(2).setHeaderValue("Time In");
        columnModel.getColumn(3).setHeaderValue("Time Out");
        columnModel.getColumn(4).setHeaderValue("Date");

        //size of the row height
        jstudent_attendance.setRowHeight(20);
        
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
        Font headerFont = new Font("Segoe UI", Font.PLAIN, 14);
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
    }
    
    //show data in the table
    public void displayDataTable(){
        try{
            myConnection dbc = new myConnection();
            ResultSet rs = dbc.getData("SELECT attendance_table.student_id, student.student_fullname, attendance_table.time_in, attendance_table.time_out, attendance_table.date "
                    + "FROM student "
                    + "INNER JOIN attendance_table ON student.student_id = attendance_table.student_id");
            jstudent_attendance.setModel(DbUtils.resultSetToTableModel(rs));

            jstudent_attendance.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {
                    if (!e.getValueIsAdjusting()) {
                        int selectedRow = jstudent_attendance.getSelectedRow();
                        if (selectedRow != -1) {
                            JFormattedTextField dateField = (JFormattedTextField) jDateSearch.getDateEditor().getUiComponent();
                            dateField.setText(placeholderText);
                            jSearchID.setText(searchID);
                        }
                    }
                }
            });
            
            tableSettings(); //table settings
            
            rs.close();
        }catch(SQLException ex){
            System.out.println("Errors: "+ex.getMessage());
        }
    }

    //filter data by date
    public void searchData() {
        Date selectedDate = jDateSearch.getDate();
        String studentId = jSearchID.getText().trim();
        boolean isNotInteger = false;
        int studentID = 0;

        try{
            studentID = Integer.parseInt(studentId);
        } catch (NumberFormatException ex) {
            isNotInteger = true;
        }
        if (selectedDate != null || !studentId.isEmpty()) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String formattedDate = (selectedDate != null) ? dateFormat.format(selectedDate) : null;
            try {
                myConnection dbc = new myConnection();
                
                String query = "SELECT attendance_table.student_id, student.student_fullname, attendance_table.time_in, attendance_table.time_out, attendance_table.date "
                    + "FROM student "
                    + "INNER JOIN attendance_table ON student.student_id = attendance_table.student_id "
                    + "WHERE 1=1";
                
                if (!isNotInteger) {
                    query += " AND student.student_id = '" + studentID + "'";
                }
                if (formattedDate != null) {
                    query += " AND attendance_table.date = '" + formattedDate + "'";
                }
                
                ResultSet rs = dbc.getData(query);
                jstudent_attendance.setModel(DbUtils.resultSetToTableModel(rs));

                tableSettings();

                rs.close();
            } catch (SQLException ex) {
                System.out.println("Errors: " + ex.getMessage());
            }
        } else {
            // Display all data when both date and student ID are empty
            if (selectedDate == null && studentId.isEmpty()) {
                try {
                    myConnection dbc = new myConnection();
                    String query = "SELECT attendance.student_id, student.student_fullname, attendance_table.time_in, attendance_table.time_out, attendance_table.date "
                        + "FROM student "
                        + "INNER JOIN attendance_table ON student.student_id = attendance_table.student_id "
                        + "WHERE";

                    ResultSet rs = dbc.getData(query);
                    jstudent_attendance.setModel(DbUtils.resultSetToTableModel(rs));

                    tableSettings();

                    rs.close();
                } catch (SQLException ex) {
                    System.out.println("Errors: " + ex.getMessage());
                }
            }  
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
        jLogoutButton = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jArchiveStudents = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jAdminName = new javax.swing.JLabel();
        datetime = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jstudent_attendance = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        jDateSearch = new com.toedter.calendar.JDateChooser();
        jButton1 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        SCCLogo = new javax.swing.JLabel();
        ITLogo = new javax.swing.JLabel();
        jSearchID = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("TEACHER ATTENDANCE");
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
        });
        jLogoutButton.setLayout(null);

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/logout.png"))); // NOI18N
        jLabel12.setText("  Logout");
        jLogoutButton.add(jLabel12);
        jLabel12.setBounds(30, 0, 130, 50);

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

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/ARCHIEVE.png"))); // NOI18N
        jLabel11.setText("  Archive Student");
        jArchiveStudents.add(jLabel11);
        jLabel11.setBounds(30, 0, 170, 50);

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

        jstudent_attendance.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jstudent_attendance.setForeground(new java.awt.Color(51, 51, 51));
        jScrollPane1.setViewportView(jstudent_attendance);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(230, 290, 860, 380);

        jLabel8.setBackground(new java.awt.Color(25, 118, 211));
        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(25, 118, 211));
        jLabel8.setText("Student Attendance");
        jPanel1.add(jLabel8);
        jLabel8.setBounds(230, 200, 190, 25);

        jDateSearch.setToolTipText("");
        jDateSearch.setDateFormatString("yyyy-MM-dd");
        jDateSearch.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jPanel1.add(jDateSearch);
        jDateSearch.setBounds(960, 250, 130, 24);

        jButton1.setBackground(new java.awt.Color(51, 153, 255));
        jButton1.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("EXPORT CSV");
        jButton1.setContentAreaFilled(false);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.setFocusPainted(false);
        jButton1.setOpaque(true);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1);
        jButton1.setBounds(230, 250, 100, 24);

        jLabel7.setBackground(new java.awt.Color(0, 153, 255));
        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 40)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(40, 110, 210));
        jLabel7.setText("St. Cecilia's College - Cebu, Inc.");
        jPanel1.add(jLabel7);
        jLabel7.setBounds(360, 10, 600, 60);
        jPanel1.add(SCCLogo);
        SCCLogo.setBounds(250, 10, 90, 70);
        jPanel1.add(ITLogo);
        ITLogo.setBounds(1000, 10, 70, 70);

        jSearchID.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jSearchID.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jSearchIDMouseClicked(evt);
            }
        });
        jSearchID.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jSearchIDKeyReleased(evt);
            }
        });
        jPanel1.add(jSearchID);
        jSearchID.setBounds(810, 250, 130, 24);

        jButton2.setBackground(new java.awt.Color(51, 153, 255));
        jButton2.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("REFRESH");
        jButton2.setContentAreaFilled(false);
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.setFocusPainted(false);
        jButton2.setOpaque(true);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2);
        jButton2.setBounds(350, 250, 100, 25);

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
        displayNameAndRole();
    }//GEN-LAST:event_formWindowActivated

    private void jProfileSettingsAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jProfileSettingsAncestorAdded
        
    }//GEN-LAST:event_jProfileSettingsAncestorAdded

    //logout button
    private void jLogoutButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLogoutButtonMouseClicked
        Session session = Session.getInstance();
        int id = session.getId();
        LogsHistory logHistory = new LogsHistory();
        logHistory.insertTeacherLog(id, "Teacher Logout");LoginPage loginFrame = new LoginPage();
        loginFrame.setVisible(true);
        loginFrame.pack();
        loginFrame.setLocationRelativeTo(null);
        this.dispose();
    }//GEN-LAST:event_jLogoutButtonMouseClicked

    private void jLogoutButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLogoutButtonMouseEntered
        setColor(jLogoutButton);
    }//GEN-LAST:event_jLogoutButtonMouseEntered

    private void jLogoutButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLogoutButtonMouseExited
        resetColor(jLogoutButton);
    }//GEN-LAST:event_jLogoutButtonMouseExited

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        exportTableDataToCSV();
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jSearchIDKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jSearchIDKeyReleased
        searchData();
    }//GEN-LAST:event_jSearchIDKeyReleased

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        displayDataTable();        
        jSearchID.setText(searchID);
        JFormattedTextField dateField = (JFormattedTextField) jDateSearch.getDateEditor().getUiComponent();
        dateField.setText(placeholderText);
    }//GEN-LAST:event_jButton2ActionPerformed

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

    private void jSearchIDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jSearchIDMouseClicked
        jSearchID.setText("");
    }//GEN-LAST:event_jSearchIDMouseClicked

    
    
    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TeacherAttendance().setVisible(true);
            }
        });
        
        
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ITLogo;
    private javax.swing.JLabel SCCLogo;
    private javax.swing.JLabel datetime;
    public javax.swing.JLabel jAdminName;
    private javax.swing.JPanel jArchiveStudents;
    private javax.swing.JPanel jAttendance;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JPanel jDashboard;
    private com.toedter.calendar.JDateChooser jDateSearch;
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
    private javax.swing.JPanel jLogoutButton;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jProfileSettings;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jSearchID;
    private javax.swing.JPanel jStudent;
    private javax.swing.JTable jstudent_attendance;
    // End of variables declaration//GEN-END:variables
}
