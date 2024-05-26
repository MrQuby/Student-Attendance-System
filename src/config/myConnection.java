
package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class myConnection {
    public Connection connect;
    public myConnection(){
        try{
            connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/school", "root", "");
        }catch(SQLException ex){
            System.out.println("Can't connect to database: "+ex.getMessage());
        }
    }
    //Function to retrieve data
    public ResultSet getData(String sql) throws SQLException{
        PreparedStatement stmt = connect.prepareStatement(sql);
        ResultSet rst = stmt.executeQuery();
        return rst;
    }
    
     //Function to save data
    public boolean insertData(String sql){
        try{
            PreparedStatement pst = connect.prepareStatement(sql);
            pst.executeUpdate();
            pst.close();
           return true;
        }catch(SQLException ex){
            System.out.println("Connection Error: "+ex);
           return false;
        }
    }
    
    //function to save data with image
    public boolean insertDatas(PreparedStatement pst) {
        try {
            pst.executeUpdate();
            pst.close();
            return true;
        } catch (SQLException ex) {
            System.out.println("Connection Error: " + ex);
            return false;
        }
    }
        
        
    // Function to close connection
    public void closeConnection() throws SQLException {
        if (connect != null) {
            connect.close();
        }
    }
}
