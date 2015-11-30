/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data_rw;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nick
 */
public class QueryRunner {

    private Connection conn;
    private Statement statement;
    private ResultSet seasonResultSet;
    private ResultSet moboCompResultSet;
    private ResultSet clubResultSet;
    
    protected String[] clubs = new String[20];
   
    public QueryRunner(Connection c) {
        conn = c;
    }

    public String[] getClubs(){
        String query = "SELECT NAME FROM `Club` ORDER BY `NAME` ASC";
        ArrayList<String> clubNames = new ArrayList<>();
        int i=0;
        try{
            statement = conn.createStatement();
            clubResultSet = statement.executeQuery(query);
            
            while (clubResultSet.next()){
                clubs[i] = clubResultSet.getString("Name");
                i++;
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(QueryRunner.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return clubs;
    }
    
    public String[] getPlayers(){
        
        
        return clubs;
    }
}


