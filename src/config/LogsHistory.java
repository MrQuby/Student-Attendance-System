
package config;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Time;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class LogsHistory {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    
    public void insertTeacherLog(int ID, String action) {
        LocalDateTime now = LocalDateTime.now();
        String currentDate = now.format(DATE_FORMAT);
        myConnection connector = new myConnection();
        Connection connect = connector.connect;;

        try {
            // Insert query
            String query = "INSERT INTO history_logs (teacher_id, log_action, log_date, log_time) VALUES (?, ?, ?, ?)";
            PreparedStatement insertAttendanceStmt = connect.prepareStatement(query);
            insertAttendanceStmt.setInt(1, ID);
            insertAttendanceStmt.setString(2, action);
            insertAttendanceStmt.setString(3, currentDate);
            insertAttendanceStmt.setTime(4, Time.valueOf(now.toLocalTime()));

            // Execute the query
            insertAttendanceStmt.executeUpdate();

            // Close the statement
            insertAttendanceStmt.close();
        } catch (Exception ex) {
            System.out.println("Error inserting teacher login log: " + ex.getMessage());
        }
    }
    
    public void insertAdminLog(int ID, String action) {
        LocalDateTime now = LocalDateTime.now();
        String currentDate = now.format(DATE_FORMAT);
        myConnection connector = new myConnection();
        Connection connect = connector.connect;;

        try {
            // Insert query
            String query = "INSERT INTO history_logs (admin_id, log_action, log_date, log_time) VALUES (?, ?, ?, ?)";
            PreparedStatement insertAttendanceStmt = connect.prepareStatement(query);
            insertAttendanceStmt.setInt(1, ID);
            insertAttendanceStmt.setString(2, action);
            insertAttendanceStmt.setString(3, currentDate);
            insertAttendanceStmt.setTime(4, Time.valueOf(now.toLocalTime()));

            // Execute the query
            insertAttendanceStmt.executeUpdate();

            // Close the statement
            insertAttendanceStmt.close();
        } catch (Exception ex) {
            System.out.println("Error inserting admin login log: " + ex.getMessage());
        }
    }
}
