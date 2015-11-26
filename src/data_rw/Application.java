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
        String caseFile = "CASE_Data.txt"; //done
        String moboFile = "MOBO_Data.txt"; //done
        String cpuFile = "CPU_Data.txt"; //done
        String hardDriveFile = "HARDDRIVE_Data.txt"; //done
        String gpuFile = "GPU_Data.txt"; //done
        String heatSinkFile = "HEATSINK_Data.txt"; //done
        String ramFile = "RAM_Data.txt"; //remake data file
        String powerSupplyFile = "PSU_Data.txt"; 
        
        String[] cases = new String[]{"Sample Case1", "Sample Case2","3","4","5","6","7","8","9","10"};
        String[] mobos = new String[]{"Sample Mobo1", "Sample Mobo2"};
        String[] cpus = new String[]{"Sample CPU1", "Sample CPU2"};
        String[] gpus = new String[]{"Sample GPU1", "Sample GPU2"};
        String[] powers = new String[]{"Sample PowerSource1", "Sample PowerSource2"};
        String[] hardDrives = new String[]{"Sample HardDrive1", "Sample HardDrive2"};
        String[] heatSinks = new String[]{"Sample HeatSink1", "Sample HeatSink2"};
        String[] rams = new String[]{"Sample Ram1", "Sample Ram2"};
        
        Connect con = new Connect();
        QueryRunner qr = new QueryRunner(con.getConn());
        Gui gui = new Gui(qr.getCases(),mobos,cpus,gpus,powers,hardDrives,heatSinks,rams);
        con.closeConn();
        
        //DataReader drCASE = new DataReader(caseFile, "case");
        //DataReader drMOBO = new DataReader(moboFile, "mobo");
        //DataReader drCPU = new DataReader(cpuFile, "cpu");
        //DataReader drRAM = new DataReader(ramFile, "ram");
        //DataReader drHD = new DataReader(hardDriveFile, "harddrive");
        //DataReader drGPU = new DataReader(gpuFile, "gpu");
        //DataReader drHS = new DataReader(heatSinkFile, "heatsink");
        //DataReader drPSU = new DataReader(powerSupplyFile, "psu");
    }
}
