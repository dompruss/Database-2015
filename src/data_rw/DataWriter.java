/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data_rw;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
//import java.util.logging.Level;
//import java.util.logging.Logger;

/**
 *
 * @author Nicolas
 */
public class DataWriter {
    
    protected String fileName;
    protected FileWriter fileWriter;
    protected BufferedWriter bufferedWriter;
    protected int numAttributes = 0;
    protected String[] moboForm = new String[]{"AT", "ATX", "EATX", "EEATX", "Flex ATX",
        "HPTX", "Micro ATX", "Mini ITX", "SSI CEB", "SSI EEB", "Thin Mini ITX", "XL ATX"};

    protected String[] moboSpeeds = new String[]{"1066", "1333", "1600", "1800", "1866", "2000", "2133", "2200", "2400", "2500",
        "2600", "2666", "2800", "2933", "3000", "3100", "3200", "3300"};

    public DataWriter(String writeFile, int inNumAttributes) {
        try {
            this.fileWriter = new FileWriter(writeFile);
            this.bufferedWriter = new BufferedWriter(fileWriter);
            numAttributes = inNumAttributes;
            fileName = writeFile;
        } catch (FileNotFoundException ex) {
            System.out.println(
                    "Unable to open file '"
                    + fileName + "'");
        } catch (IOException ex) {
            System.out.println(
                    "Error reading file '"
                    + fileName + "'");
        }

    }
    
    public void writeData(ArrayList a, ArrayList d, int n){
        
    }

    public String convertToString(String[] array) {
        String converted = "";
        for (int i = 0; i < array.length; i++) {
            if (i != array.length - 1) {
                converted += array[i] + ",";
            } else {
                converted += array[i];
            }
        }
        converted = converted.replaceAll("\\s", "_");
        return converted;
    }

    public boolean searchArray(String s, String[] forms) {
        boolean found = false;
        for (int i = 0; i < forms.length; i++) {
            if (forms[i].equals(s)) {
                found = true;
                return found;
            }
        }
        return found;
    }
    
    

    public void writeSecondaryTables(ArrayList a, ArrayList d, String[] list, String fileName, int startMod, int n, String regex, String split) {
        try {
            this.fileWriter = new FileWriter(fileName);
            this.bufferedWriter = new BufferedWriter(fileWriter);
            int backTrack = startMod;
            int modValue = startMod;
            String header = fileName.substring(0, fileName.indexOf("_"));
//            String noSpaces = (String) a.get(0);
//                noSpaces = noSpaces.replaceAll("\\s", "");
            bufferedWriter.write(header + (String) a.get(0) + "," + header + (String) a.get(1) + "," + convertToString(list));
            bufferedWriter.newLine();
            for (int j = 0; j < d.size(); j++) {
                if ((j + 1) % modValue == 0) {
                    modValue += n;
                    String data = (String) d.get(j);
                    if (!regex.equals("")) {
                        data = data.replaceAll(regex, "");
                    }
                    String[] matches = new String[list.length];
                    String[] compare = data.split(split);
                    for (int k = 0; k < list.length; k++) {
                        if (searchArray(list[k], compare)) {
                            matches[k] = "1";
                        } else {
                            matches[k] = "0";
                        }
                    }
                    bufferedWriter.write((String) d.get(j - (backTrack - 1)) + "," + (String) d.get(j - (backTrack - 2)) + "," + convertToString(matches));
                    bufferedWriter.newLine();
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
