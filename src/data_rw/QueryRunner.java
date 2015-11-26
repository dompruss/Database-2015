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

/**
 *
 * @author Nick
 */
public class QueryRunner {

    private Connection conn;
    private Statement statement;
    private ResultSet caseResultSet;
    private ResultSet moboCompResultSet;

    //private String[] caseInfo = new String[25];
    public QueryRunner(Connection c) {
        conn = c;
    }

    public String[] getCases() {
        String[] caseInfo = new String[25];
        try {
            int i = 0;
            statement = conn.createStatement();
            caseResultSet = statement
                    .executeQuery("select Manufacturer, Model from CASE_TABLE limit 25");

            while (caseResultSet.next()) {
                caseInfo[i] = caseResultSet.getNString(1) + " " + caseResultSet.getNString(2);
                i++;
            }

        } catch (SQLException ex) {
            System.out.println("Can't run query");
        }
        return caseInfo;
    }

    public void getMobos(String caseManu, String caseMod) {
        ArrayList<String> moboCompForms = new ArrayList<String>();

        String query = "SELECT AT, ATX, EATX, EEATX, Flex_ATX, HPTX, Micro_ATX, Mini_ITX, SSI_CEB, SSI_EEB, Thin_Mini_ITX, XL_ATX"
                + " FROM CASE_MOBO_COMP WHERE CASE_MOBO_COMP.CASEManufacturer = '"
                + caseManu + "' AND " + "CASE_MOBO_COMP.CASEModel = '" + caseMod + "'";

        try {
            statement = conn.createStatement();
            moboCompResultSet = statement
                    .executeQuery(query);
            ResultSetMetaData rsmd = moboCompResultSet.getMetaData();

            moboCompResultSet.next();
            System.out.println(rsmd.getColumnCount());

            for (int i = 0; i < rsmd.getColumnCount(); i++) {
                if (moboCompResultSet.getString(i + 1).equals("1")) {
                    moboCompForms.add(rsmd.getColumnName(i + 1));
                }
            }
            
            String orString = "";
            int size = moboCompForms.size();
            if (size > 1) {
                for (int i = 0; i < size; i++) {
                    String form = moboCompForms.get(i);
                        if(form.contains("_")){
                            form = form.replaceAll("_", " ");
                        }
                    if (i != size - 1) {
                        orString += "MOBO_TABLE.FormFactor = '" + form + "'" + " or ";
                    } else {
                        orString += "MOBO_TABLE.FormFactor = '" + form + "'";
                    }
                }
            }
            else{
                String form = moboCompForms.get(1);
                        if(form.contains("_")){
                            form = form.replaceAll("_", " ");
                        }
                orString = "MOBO_TABLE.FormFactor = '" + form + "'";
            }
            
            String moboQuery = "select Manufacturer, PartNum from MOBO_TABLE where " + orString;
            
            System.out.println(moboQuery);

        } catch (SQLException ex) {
            System.out.println("Can't run query");
        }
    }

    public void printArray(ArrayList a) {
        for (Object a1 : a) {

            System.out.print(a1 + ", ");
        }
    }

    public void getCpus() {

    }

    public void getHeatSinks() {

    }

    public void getGpus() {

    }

    public void getRam() {

    }

    public void getPsus() {

    }

    public void getHardDrives() {

    }

}
