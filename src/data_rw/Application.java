/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data_rw;

/**
 *
 * @author Nik
 */

public class Application {
    public static void main(String[] args) {

        
        Connect con = new Connect();
        QueryRunner qr = new QueryRunner(con.getConn());
        Gui gui = new Gui(qr);
        gui.updateCases(qr.getCases());
        
        }
}
