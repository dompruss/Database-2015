/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data_rw;

/**
 *
 * @author Nick
 */
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.*;

public class Connect {
    
    private Connection conn;
    //private Statement statement;
    //private ResultSet resultSet;
    //private String[] caseInfo = new String[25];

    public Connect() {
        

        try {

            String url = "jdbc:mysql://csci440.cs.montana.edu:3306/mooren";
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, "mooren", "t3gjws7quz");
            System.out.println("Database connection established");
        } catch (Exception e) {
            e.printStackTrace();

//        } finally {
//            if (conn != null) {
//                try {
//                    conn.close();
//                    System.out.println("Database connection terminated");
//                } catch (Exception e) { /* ignore close errors */ }
//            }
        }
    }
    
//    public void getCases(){
//        try {
//            int i = 0;
//            statement = conn.createStatement();
//            resultSet = statement
//                    .executeQuery("select * from CASE_TABLE limit 25");
//            
//            while(resultSet.next()){
//                caseInfo[i] = resultSet.getNString(1) + " " + resultSet.getNString(2);
//                i++;
//            }
//            
//        } catch (SQLException ex) {
//            System.out.println("Can't run query");
//        }
//    }
    
    public void closeConn(){
        try {
            conn.close();
            System.out.println("Database connection terminated");
        } catch (SQLException ex) {
            System.out.println("No connection to close");
        }
    }
    
    public Connection getConn(){
        return conn;
    }
    
//    public String[] getCaseInfo(){
//        return caseInfo;
//    }
    
    public void printArray(String[] array){
        for(int i = 0; i < array.length; i++){
            System.out.println(array[i]);
        }
    }
}
