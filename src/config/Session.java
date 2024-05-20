
package config;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;


public class Session {
    private static Session instance;
    private int id;
    private String fullname;
    private String contactNumber;
    private String password;
    private Date birthdate;
    private String gender;
    private String address;
    private String status;
    
    private Session(){
        //private contructors to prevent instance
    }

    public static synchronized Session getInstance() {
        if(instance == null){
            instance = new Session();
        }
        return instance;
    }

    public static boolean isIntanceEmpty() {
        return instance == null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    //get data from database and to update session data
    public void getData(int id){
        myConnection connector = new myConnection();
        try{
            String query = "SELECT * FROM teacher  WHERE teacher_id = '" + id + "'";
            ResultSet resultSet = connector.getData(query);
            if(resultSet.next()) {
                Session session = Session.getInstance();
                session.setId(resultSet.getInt("teacher_id"));
                session.setFullname(resultSet.getString("teacher_name"));
                session.setContactNumber(resultSet.getString("teacher_contact_number"));
                session.setPassword(resultSet.getString("teacher_password"));
                session.setBirthdate(resultSet.getDate("teacher_birthdate"));
                session.setGender(resultSet.getString("teacher_gender"));
                session.setAddress(resultSet.getString("teacher_address"));
                session.setStatus(resultSet.getString("teacher_status"));
            }
        }catch (SQLException ex) {
            System.out.println("Invalid Connection" + ex.getMessage());            
        }
    }
    
    public void getAdminData(int id){
        myConnection connector = new myConnection();
        try{
            String query = "SELECT * FROM admin  WHERE admin_id = '" + id + "'";
            ResultSet resultSet = connector.getData(query);
            if(resultSet.next()) {
                Session session = Session.getInstance();
                session.setId(resultSet.getInt("admin_id"));
                session.setPassword(resultSet.getString("admin_password"));
            }
        }catch (SQLException ex) {
            System.out.println("Invalid Connection" + ex.getMessage());            
        }
    }
}
