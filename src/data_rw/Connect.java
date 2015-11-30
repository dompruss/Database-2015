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

    public Connect() {
        

        try {
            String url = "jdbc:mysql://csci440.cs.montana.edu:3306/mooren";
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, "mooren", "t3gjws7quz");
            System.out.println("Database connection established");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
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
    
    public void printArray(String[] array){
        for(int i = 0; i < array.length; i++){
            System.out.println(array[i]);
        }
    }
}
