package data_rw;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;

public class Gui implements ActionListener {

    final JPanel guiPanel = new JPanel();
    JFrame guiFrame = new JFrame();
    
    JLabel caseLabel = new JLabel("Cases:");
    JLabel moboLabel = new JLabel("Mother Boards:");
    JLabel cpuLabel = new JLabel("CPU:");
    JLabel gpuLabel = new JLabel("GPU:");
    JLabel powerLabel = new JLabel("Power Supply:");
    JLabel hardDriveLabel = new JLabel("Hard Drive:");
    JLabel heatSinkLabel = new JLabel("Heat Sink:");
    JLabel ramLabel = new JLabel("RAM:");
    
    JComboBox caseCombo;
        //caseCombo.addActionListener(this);
        JComboBox moboCombo;
        //moboCombo.addActionListener(this);
        JComboBox cpuCombo;
        JComboBox gpuCombo;
        JComboBox powerCombo;
        JComboBox hardCombo ;
        JComboBox heatCombo;
        JComboBox ramCombo;

    public Gui(String[] cases, String[] mobos, String[] cpus, String[] gpus, String[] powers, String[] hardDrives, String[] heatSinks, String[] rams) {
        //JFrame guiFrame = new JFrame();
        guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        guiFrame.setTitle("Computer Compatability Database");
        guiFrame.setSize(250, 500);
        guiFrame.setLocationRelativeTo(null);

        //final JPanel guiPanel = new JPanel();
        //guiPanel.addMouseListener(this);
        guiPanel.setLayout(new BoxLayout(guiPanel, BoxLayout.PAGE_AXIS));
//        JLabel caseLabel = new JLabel("Cases:");
//        JLabel moboLabel = new JLabel("Mother Boards:");
//        JLabel cpuLabel = new JLabel("CPU:");
//        JLabel gpuLabel = new JLabel("GPU:");
//        JLabel powerLabel = new JLabel("Power Supply:");
//        JLabel hardDriveLabel = new JLabel("Hard Drive:");
//        JLabel heatSinkLabel = new JLabel("Heat Sink:");
//        JLabel ramLabel = new JLabel("RAM:");
        caseCombo = new JComboBox(cases);
        caseCombo.addActionListener(this);
        moboCombo = new JComboBox(mobos);
        moboCombo.addActionListener(this);
        cpuCombo = new JComboBox(cpus);
        gpuCombo = new JComboBox(gpus);
        powerCombo = new JComboBox(powers);
        hardCombo = new JComboBox(hardDrives);
        heatCombo = new JComboBox(heatSinks);
        ramCombo = new JComboBox(rams);

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

    @Override
    public void actionPerformed(ActionEvent e) {
        JComboBox cb = (JComboBox) e.getSource();
        if (cb == caseCombo) {
            String caseName = (String) cb.getSelectedItem();
            System.out.println(caseName);
        }
    }

}
