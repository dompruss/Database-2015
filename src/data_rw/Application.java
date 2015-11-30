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
        String[] positions = new String[6];
        positions[0] = "Goalkeeper";
        positions[1] = "Defender";
        positions[2] = "Midfielder";
        positions[3] = "Forward";
        positions[4] = "Owner";
        positions[5] = "Manager";
        System.out.println("Goat here");
        gui.updatePositions(positions);
        gui.updateClubs(qr.getClubs());
        }
}
