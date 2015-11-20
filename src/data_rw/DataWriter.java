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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nicolas
 */
public class DataWriter {

    private FileWriter fileWriter;
    private BufferedWriter bufferedWriter;
    private String fileName;
    private int numAttributes = 0;
    private String[] moboForm = new String[]{"AT", "ATX", "EATX", "EEATX", "Flex ATX",
        "HPTX", "Micro ATX", "Mini ITX", "SSI CEB", "SSI EEB", "Thin Mini ITX", "XL ATX"};

    public DataWriter(ArrayList a, ArrayList d, String writeFile, int inNumAttributes) {

        numAttributes = inNumAttributes;

        try {
            // The name of the file to open.
            fileName = writeFile;

            // Assume default encoding.
            fileWriter = new FileWriter(fileName);

            // Always wrap FileWriter in BufferedWriter.
            bufferedWriter = new BufferedWriter(fileWriter);
        } catch (FileNotFoundException ex) {
            System.out.println(
                    "Unable to open file '"
                    + fileName + "'");
        } catch (IOException ex) {
            System.out.println(
                    "Error reading file '"
                    + fileName + "'");
        }
        
        switch (fileName) {
            case "CASE.csv":
                writeCase(a, d, numAttributes);
                writeCaseMoboComp(a, d);
                break;

            default:
                System.out.println("Invalid file name");
                break;
        }
//        writeCase(a, d, numAttributes);
//        writeCaseMoboComp(a, d);

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

    public void writeCase(ArrayList a, ArrayList d, int n) {
        try {
            for (int i = 0; i < a.size(); i++) {
                if (i == 10) {

                } else {
                    if (i != a.size() - 1) {
                        bufferedWriter.write((String) a.get(i) + ",");
                    } else {
                        bufferedWriter.write((String) a.get(i));
                    }
                }
            }

            bufferedWriter.newLine();
            int startMod = 11;
            for (int j = 0; j < d.size(); j++) {
                if ((j + 1) % startMod == 0) {
                    startMod += 13;

                } else {
                    if ((j + 1) % n == 0) {
                        bufferedWriter.write((String) d.get(j));
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

    public void writeCaseMoboComp(ArrayList a, ArrayList d) {
        FileWriter compWrite = null;
        try {
            compWrite = new FileWriter("CASE_MOBO_COMP.csv");
            BufferedWriter compBufferedWriter = new BufferedWriter(compWrite);
            compBufferedWriter.write("CASE " + (String) a.get(0) + "," + "CASE " + (String) a.get(1) + "," + convertToString(moboForm));
            compBufferedWriter.newLine();
            int startMod = 11;
            for (int j = 0; j < d.size(); j++) {
                if ((j + 1) % startMod == 0) {
                    startMod += 13;
                    String forms = (String) d.get(j);
                    String[] matches = new String[moboForm.length];
                    String[] splitForms = forms.split(", ");
                    for (int k = 0; k < moboForm.length; k++) {
                        if (searchArray(moboForm[k], splitForms)) {
                            matches[k] = "1";
                        } else {
                            matches[k] = "0";
                        }
                    }
                    compBufferedWriter.write((String) d.get(j - 10) + "," + (String) d.get(j - 9) + "," + convertToString(matches));
                    compBufferedWriter.newLine();
                }
            }
            compBufferedWriter.close();
        } catch (IOException ex) {
            System.out.println(
                    "Error writing to file '"
                    + fileName + "'");
        }
    }
}
