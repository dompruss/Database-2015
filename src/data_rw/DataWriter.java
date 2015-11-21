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

    private String[] moboSpeeds = new String[]{"1066", "1333", "1600", "1800", "1866", "2000", "2133", "2200", "2400", "2500",
        "2600", "2666", "2800", "2933", "3000", "3100", "3200", "3300"};

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
                writeSecondaryTables(a, d, moboForm, "CASE_MOBO_COMP.csv", 11, 13, "", ", ");
                break;

            case "MOBO.csv":
                writeMobo(a, d, numAttributes);
                writeSecondaryTables(a, d, moboSpeeds, "MOBO_SPEED.csv", 7, 16, "\\w*-", " / ");
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

    public void writeMobo(ArrayList a, ArrayList d, int n) {
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
            int memSlotMod = 6;
            int memTypeMod = 7;
            int maxMemMod = 8;

            for (int j = 0; j < d.size(); j++) {
                if ((j + 1) % partMod == 0) {
                    partMod += 16;
                    String info = (String) d.get(j);
                    info = info.substring(0, info.indexOf("<"));
                    bufferedWriter.write(info + ",");
                } else if ((j + 1) % memSlotMod == 0) {
                    memSlotMod += 16;
                    String info = (String) d.get(j);
                    info = info.replaceAll("\\d x ", "");
                    bufferedWriter.write(info + ",");
                } else if ((j + 1) % memTypeMod == 0) {
                    memTypeMod += 16;
                    String info = (String) d.get(j);
                    info = info.substring(0, info.indexOf("-"));
                    bufferedWriter.write(info + ",");
                } else if ((j + 1) % maxMemMod == 0) {
                    maxMemMod += 16;
                    String info = (String) d.get(j);
                    info = info.substring(0, info.indexOf("G"));
                    bufferedWriter.write(info + ",");
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

    public void writeSecondaryTables(ArrayList a, ArrayList d, String[] list, String fileName, int startMod, int n, String regex, String split) {
        FileWriter compWrite = null;
        try {
            int backTrack = startMod;
            int modValue = startMod;
            String header = fileName.substring(0, fileName.indexOf("_")) + " ";
            compWrite = new FileWriter(fileName);
            BufferedWriter compBufferedWriter = new BufferedWriter(compWrite);
            compBufferedWriter.write(header + (String) a.get(0) + "," + header + (String) a.get(1) + "," + convertToString(list));
            compBufferedWriter.newLine();
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
                    compBufferedWriter.write((String) d.get(j - (backTrack - 1)) + "," + (String) d.get(j - (backTrack - 2)) + "," + convertToString(matches));
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
