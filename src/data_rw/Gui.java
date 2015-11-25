package data_rw;

import java.awt.BorderLayout;
import javax.swing.*;
public class Gui {
    
    String[] cases;
    String[] mobos;
    String[] gpus;
    String[] powers;
    String[] hardDrives;
    String[] heatSinks;
    String[] rams;
    
    public Gui(String[] cases, String[] mobos, String[] cpus, String[] gpus, String[] powers, String[] hardDrives, String[] heatSinks, String[] rams ) {
        JFrame guiFrame = new JFrame();
        guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        guiFrame.setTitle("Computer Compatability Database");
        guiFrame.setSize(250, 500);
        guiFrame.setLocationRelativeTo(null);
        
        final JPanel guiPanel = new JPanel();
        guiPanel.setLayout(new BoxLayout(guiPanel, BoxLayout.PAGE_AXIS));
        
//        this.cases = new String[]{"Sample Case1", "Sample Case2"};
//        this.mobos = new String[]{"Sample Mobo1", "Sample Mobo2"};
//        this.gpus = new String[]{"Sample GPU1", "Sample GPU2"};
//        this.powers = new String[]{"Sample PowerSource1", "Sample PowerSource2"};
//        this.hardDrives = new String[]{"Sample HardDrive1", "Sample HardDrive2"};
//        this.heatSinks = new String[]{"Sample HeatSink1", "Sample HeatSink2"};
//        this.rams = new String[]{"Sample Ram1", "Sample Ram2"};
        JLabel caseLabel = new JLabel("Cases:");
        JLabel moboLabel = new JLabel("Mother Boards:");
        JLabel cpuLabel = new JLabel("CPU:");
        JLabel gpuLabel = new JLabel("GPU:");
        JLabel powerLabel = new JLabel("Power Source:");
        JLabel hardDriveLabel = new JLabel("Hard Drive:");
        JLabel heatSinkLabel = new JLabel("Heat Sink:");
        JLabel ramLabel = new JLabel("RAM:");        
        JComboBox caseCombo = new JComboBox(cases);
        JComboBox moboCombo = new JComboBox(mobos);
        JComboBox cpuCombo = new JComboBox(cpus);
        JComboBox gpuCombo = new JComboBox(gpus);
        JComboBox powerCombo = new JComboBox(powers);
        JComboBox hardCombo = new JComboBox(hardDrives);
        JComboBox heatCombo = new JComboBox(heatSinks);
        JComboBox ramCombo = new JComboBox(rams);    
        
        guiPanel.add(caseLabel);
        guiPanel.add(caseCombo);
        
        guiPanel.add(moboLabel);
        guiPanel.add(moboCombo);
        
        guiPanel.add(cpuLabel);
        guiPanel.add(cpuCombo);
        
        guiPanel.add(gpuLabel);
        guiPanel.add(gpuCombo);
        
        guiPanel.add(powerLabel);
        guiPanel.add(powerCombo);
        
        guiPanel.add(hardDriveLabel);
        guiPanel.add(hardCombo);
        
        guiPanel.add(heatSinkLabel);
        guiPanel.add(heatCombo);
        
        guiPanel.add(ramLabel);
        guiPanel.add(ramCombo);
        
        guiPanel.setVisible(true);
        guiFrame.add(guiPanel);
        guiFrame.setVisible(true);
    }
    
}
