/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data_rw;

import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Nicolas
 */
public class HardDriveWriter extends DataWriter {

    public HardDriveWriter(String writeFile, int numAttributes) {
        super(writeFile, numAttributes);
    }

    @Override
    public void writeData(ArrayList a, ArrayList d, int n) {
        try {
            for (int i = 0; i < a.size(); i++) {
                if (i != a.size() - 1) {
                    bufferedWriter.write((String) a.get(i) + ",");
                } else {
                    bufferedWriter.write((String) a.get(i));
                }
            }

            bufferedWriter.newLine();
            int partMod = 2;
            int capMod = 3;
            for (int j = 0; j < d.size(); j++) {
                if ((j + 1) % partMod == 0) {
                    partMod += n;
                    String info = (String) d.get(j);
                    if (info.contains("<")) {
                        info = info.substring(0, info.indexOf("<"));
                    }
                    bufferedWriter.write(info + ",");
                } else if ((j + 1) % capMod == 0) {
                    capMod += n;
                    String info = (String) d.get(j);
                    if (info.contains("G")) {
                        info = info.substring(0, info.indexOf("G"));
                    } else if (info.contains("TB")) {
                        info = info.replaceAll("TB", "000");
//                        int thou = Integer.parseInt(info);
//                        thou = thou * 1000;
//                        info = Integer.toString(thou);
                    }
                    bufferedWriter.write(info + ",");
                } else {
                    if ((j + 1) % n == 0) {
                        String info = (String) d.get(j);
                        info = info.replaceAll("&quot;", "");
                        bufferedWriter.write(info);
                        bufferedWriter.newLine();
                    } else {
                        bufferedWriter.write((String) d.get(j) + ",");
                    }
                }

            }

            bufferedWriter.close();
        } catch (IOException ex) {
            System.out.println(
                    "Error writing to file '"
                    + fileName + "'");
        }
    }
}
