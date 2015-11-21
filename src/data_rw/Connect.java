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
import javax.sql.*;

public class Connect
{
   public static void main (String[] args)
   {
       Connection conn = null;

       try
       {

           String url = "jdbc:mysql://153.90.2.81:3306/csci440.cs.montana.edu/phpMyAdmin/";
           Class.forName ("com.mysql.jdbc.Driver");
           conn = DriverManager.getConnection (url,"mooren","t3gjws7quz");
           System.out.println ("Database connection established");
       }
       catch (Exception e)
       {
           e.printStackTrace();

       }
       finally
       {
           if (conn != null)
           {
               try
               {
                   conn.close ();
                   System.out.println ("Database connection terminated");
               }
               catch (Exception e) { /* ignore close errors */ }
           }
       }
   }
}
