/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data_rw;

/**
 *
 * @author Nicolas
 */
public class Application {
    public static void main(String[] args) {
        String caseFile = "CASE_Data.txt";
        String moboFile = "MOBO_Data.txt";
        String cpuFile = "CPU_Data.txt";
        String heatSinkFile = "HEATSINK_Data.txt"; //
        String gpuFile = "GPU_Data.txt";
        String ramFile = "RAM_Data.txt";
        String hardDriveFile = "HARDDRIVE_Data.txt";
        String powerSupplyFile = "POWERSUPPLY.txt";
        
       // DataWriter caseF = new CaseWriter();
        
        DataReader drCASE = new DataReader(caseFile, "case");
        DataReader drMOBO = new DataReader(moboFile, "mobo");
        DataReader drCPU = new DataReader(cpuFile, "cpu");
        //DataReader drRAM = new DataReader(ramFile);
    }
}
