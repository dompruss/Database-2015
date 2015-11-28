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

    protected String[] caseManu = new String[35];
    protected String[] caseMod = new String[35];
    protected Double[] caseVidLen = new Double[35];
    
    public QueryRunner(Connection c) {
        conn = c;
    }

    public String[] getCases() {
        String[] cases = new String[35];
        try {
            int i = 0;
            statement = conn.createStatement();
            caseResultSet = statement
                    .executeQuery("select * from CASE_TABLE limit 35");
            
            while (caseResultSet.next()) {
                caseManu[i] = caseResultSet.getString("Manufacturer");
                caseMod[i] = caseResultSet.getString("Model");
                caseVidLen[i] = caseResultSet.getDouble("MaximumVideoCardLength");
                cases[i] = caseResultSet.getNString(1) + " " + caseResultSet.getNString(2);
                i++;
            }

        } catch (SQLException ex) {
            System.out.println("Can't run query");
        }
        return cases;
    }

    public String[] getMobos(String caseManu, String caseMod) {
        ArrayList<String> moboCompForms = new ArrayList<>();
        String[] compMobos = new String[35];
        int i = 0;

        String query = "SELECT AT, ATX, EATX, EEATX, Flex_ATX, HPTX, Micro_ATX, Mini_ITX, SSI_CEB, SSI_EEB, Thin_Mini_ITX, XL_ATX"
                + " FROM CASE_MOBO_COMP WHERE CASE_MOBO_COMP.CASEManufacturer = '"
                + caseManu + "' AND " + "CASE_MOBO_COMP.CASEModel = '" + caseMod + "'";

        try {
            statement = conn.createStatement();
            moboCompResultSet = statement.executeQuery(query);
            ResultSetMetaData rsmd = moboCompResultSet.getMetaData();

            moboCompResultSet.next();

            for (int j = 0; j < rsmd.getColumnCount(); j++) {
                if (moboCompResultSet.getString(j + 1).equals("1")) {
                    moboCompForms.add(rsmd.getColumnName(j + 1));
                }
            }
            
            String orString = "";
            int size = moboCompForms.size();
            if (size > 1) {
                for (int k = 0; k < size; k++) {
                    String form = moboCompForms.get(k);
                        if(form.contains("_")){
                            form = form.replaceAll("_", " ");
                        }
                    if (k != size - 1) {
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
            
            String moboQuery = "select Manufacturer, PartNum from MOBO_TABLE where " + orString + " limit 35";
            moboCompResultSet = statement.executeQuery(moboQuery);
            
            while (moboCompResultSet.next()) {
                compMobos[i] = moboCompResultSet.getString("Manufacturer") + " " + moboCompResultSet.getString("PartNum");
                i++;
            }
            
        } catch (SQLException ex) {
            System.out.println("Can't run query");
        }
        return compMobos;
    }

    public void printArray(String[] a) {
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
