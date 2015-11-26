/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data_rw;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Nick
 */
public class QueryRunner {
    
    private Connection conn;
    private Statement statement;
    private ResultSet resultSet;
    //private String[] caseInfo = new String[25];
    
    public QueryRunner(Connection c){
        conn = c;
    }
    
    public String[] getCases(){
        String[] caseInfo = new String[25];
        try {
            int i = 0;
            statement = conn.createStatement();
            resultSet = statement
                    .executeQuery("select * from CASE_TABLE limit 25");
            
            while(resultSet.next()){
                caseInfo[i] = resultSet.getNString(1) + " " + resultSet.getNString(2);
                i++;
            }
            
        } catch (SQLException ex) {
            System.out.println("Can't run query");
        }
        return caseInfo;
    }
    
    public void getMobos(){
        
    }
    
    public void getCpus(){
        
    }
    
    public void getHeatSinks(){
        
    }
    
    public void getGpus(){
        
    }
    
    public void getRam(){
        
    }
    
    public void getPsus(){
        
    }
    
    public void getHardDrives(){
        
    }
    
}
