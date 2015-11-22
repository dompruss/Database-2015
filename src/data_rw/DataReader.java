/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data_rw;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Nicolas
 */
public class DataReader {

    private String fileName = "";
    private int numAttributes;
    private ArrayList<String> data = new ArrayList<String>();//write each piece of data here then have data writer divied up array
    private ArrayList<String> attributes = new ArrayList<String>();
    //private DataWriter dw;
    //private DataWriter secondaryWriter;

    public DataReader(String inFileName, String component) {
        fileName = inFileName;
        numAttributes = 0;
        getAttributesAndData();
        printAttributes(attributes);
        trimData(data);
        //printData(data);
        setDataWriter(component);
        //dw.writeData(attributes, data, numAttributes);
        System.out.println();
        System.out.println(data.size());
    }

    public void setDataWriter(String component) {
        switch (component) {
            case "case":
                DataWriter caseW = new CaseWriter(convertFileName(fileName), numAttributes);
                caseW.writeData(attributes, data, numAttributes);
                caseW.writeSecondaryTables(attributes, data, caseW.moboForm, "CASE_MOBO_COMP.csv", 11, numAttributes, "", ", ");
                break;

            case "mobo":
                DataWriter moboW = new MoboWriter(convertFileName(fileName), numAttributes);
                moboW.writeData(attributes, data, numAttributes);
                moboW.writeSecondaryTables(attributes, data, moboW.moboSpeeds, "MOBO_SPEED.csv", 7, numAttributes, "\\w*-", " / ");
                break;
                
            case "cpu":
                DataWriter cpuW = new CpuWriter(convertFileName(fileName), numAttributes);
                cpuW.writeData(attributes, data, numAttributes);
                break;
                
            case "ram":
                DataWriter ramW = new RamWriter(convertFileName(fileName), numAttributes);
                ramW.writeData(attributes, data, numAttributes);
                break;
                
            case "harddrive":
                DataWriter hdW = new HardDriveWriter(convertFileName(fileName), numAttributes);
                hdW.writeData(attributes, data, numAttributes);
                break;
                
            case "gpu":
                DataWriter gpuW = new GpuWriter(convertFileName(fileName), numAttributes);
                gpuW.writeData(attributes, data, numAttributes);
                break;

            default:
                System.out.println("Invalid file name");
                break;
        }
    }

    public void getAttributesAndData() {

        // This will reference one line at a time
        String line = null; // \s*<h4>\w*\W*\s*[#]*\w*</h4>
        String pattern = "\\s*<h4>\\w*\\W*\\w*\\s*[#]*\\d*\\s*\\w*\\s*[3.0]*\\s*\\W*\\w*[(s)]*</h4>";   //Matches RAM, MOBO, GPU
        String pattern2 = "\\s*<h4>\\w+\\s+\\d+\\W+\\d+[&quot;]+\\s+\\w*+</h4>";

        // Create a Pattern object
        Pattern regex = Pattern.compile(pattern);
        Pattern regex2 = Pattern.compile(pattern2);

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader
                    = new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader
                    = new BufferedReader(fileReader);

            while ((line = bufferedReader.readLine()) != null) {
                //System.out.println(line);
                // Now create matcher object.
                Matcher m = regex.matcher(line);
                Matcher m2 = regex2.matcher(line);

                if (m.matches() || m2.matches()) {
                    //System.out.println("match");
                    line = line.trim();
                    line = line.substring(4, line.length() - 5);
                    line = line.replaceAll("&quot;", "");
                    line = line.replaceAll("\\s", "");
                    if(line.contains("#")){
                        line = line.replaceAll("#", "Num");
                    }
                    if (!searchArray(line, attributes)) {
                        attributes.add(line);
                        numAttributes++;
                    }
                    //System.out.println(line);
                } else if (!line.equals("") && !line.equals("\\W")) {
                    line = line.trim();
                    //line = line.replaceAll("\\W", "");
                    data.add(line);
                }
            }
            System.out.println(numAttributes);

            // Always close files.
            bufferedReader.close();
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

    public void printAttributes(ArrayList a) {
        for (Object a1 : a) {
            System.out.println(a1);
        }
    }

    public void printData(ArrayList a) {
        for (Object a1 : a) {

            System.out.print(a1 + ", ");
        }
    }

    public boolean searchArray(String s, ArrayList a) {
        boolean found = false;
        for (int i = 0; i < a.size(); i++) {
            if (a.get(i).equals(s)) {
                found = true;
                return found;
            }
        }
        return found;
    }

    public String convertFileName(String file) {
        String writeFile = file;
        writeFile = writeFile.replaceAll("_Data", "");
        writeFile = writeFile.replaceAll("txt", "csv");
        System.out.print(writeFile);
        return writeFile;
    }

    public void trimData(ArrayList d) {
        for (int i = 0; i < d.size(); i++) {
            if (d.get(i).equals("")) {
                d.remove(i);
                i--;
            }
        }
    }

}
