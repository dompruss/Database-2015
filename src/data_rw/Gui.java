package data_rw;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.GridLayout;
import java.awt.Color;
import javax.swing.border.LineBorder;

public class Gui implements ActionListener {

    JFrame guiFrame = new JFrame(); // Frame for holding our JPanes
    GridLayout layout = new GridLayout(1, 2);
    

    final JPanel gamePanel = new JPanel();   // Panel for holding our Combo Boxes 
    final JPanel playerPanel = new JPanel();
    
ArrayList<String> moboCompForms = new ArrayList<>();
    String[] cases = new String[35];      // current compatible Cases
    String[] mobos = new String[35];      // current compatible Mother Boards
    String[] cpus = new String[35];       // current compatible Central Processing Units
    String[] gpus = new String[35];       // current compatible Graphics Processing Units
    String[] psus = new String[35];       // current compatible Power Supplies
    String[] hardDrives = new String[35]; // current compatible Hard Drives
    String[] heatSinks = new String[35];  // current compatible Heat Sinks
    String[] rams = new String[35];       // current compatible Random Access Memory

    JLabel positionLabel = new JLabel("Position:");
    JLabel clubLabel = new JLabel("Club:");
    JLabel playerLabel = new JLabel("Player:");
    JLabel seasonLabel = new JLabel("Season:");
    JLabel weekLabel = new JLabel("Week:");
    JLabel gameLabel = new JLabel("Game:");
    
    JTextArea playerInfo;
    JTextArea gameInfo;

    JComboBox positionCombo;
    JComboBox clubCombo;
    JComboBox playerCombo;
    JComboBox seasonCombo;
    JComboBox weekCombo;
    JComboBox gameCombo;

    
    QueryRunner qr;

    public Gui(QueryRunner inQr) {
        qr = inQr;
        guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        guiFrame.setTitle("Fantastic Fantasy Premier League App");
        guiFrame.setSize(500, 250);
        guiFrame.setLocationRelativeTo(null);   // centers gui
        gamePanel.setLayout(new BoxLayout(gamePanel, BoxLayout.PAGE_AXIS));
        playerPanel.setLayout(new BoxLayout(playerPanel, BoxLayout.PAGE_AXIS));
        initializeComboBoxes();
        initializeJPane();
    }

    private void initializeComboBoxes() {
        positionCombo = new JComboBox(cases);
        positionCombo.addActionListener(this);
        clubCombo = new JComboBox(mobos);
        clubCombo.addActionListener(this);
        playerCombo = new JComboBox(cpus);
        playerCombo.addActionListener(this);
        seasonCombo = new JComboBox(gpus);
        seasonCombo = new JComboBox(psus);
        weekCombo = new JComboBox(hardDrives);
        weekCombo = new JComboBox(heatSinks);
        gameCombo = new JComboBox(rams);
    }

    private void initializeJPane() {
        playerPanel.add(positionLabel);
        playerPanel.add(positionCombo);

        playerPanel.add(clubLabel);
        playerPanel.add(clubCombo);

        playerPanel.add(playerLabel);
        playerPanel.add(playerCombo);
        
        playerInfo = new JTextArea(5,15);
        playerPanel.add(playerInfo);

        gamePanel.add(seasonLabel);
        gamePanel.add(seasonCombo);

        gamePanel.add(weekLabel);
        gamePanel.add(weekCombo);
        
        gamePanel.add(gameLabel);
        gamePanel.add(gameCombo);

        gameInfo = new JTextArea(5,15);
        gamePanel.add(gameInfo);
        
        gamePanel.setVisible(true);
        playerPanel.setVisible(true);
        guiFrame.setLayout(layout);
        guiFrame.add(gamePanel);
        guiFrame.add(playerPanel);
        guiFrame.setVisible(true);
        gamePanel.setBackground(Color.decode("#007fff"));   
        playerPanel.setBackground(Color.decode("#007fff"));
        playerPanel.setBorder(new LineBorder(Color.black, 5));
        gamePanel.setBorder(new LineBorder(Color.black, 5));
        
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        JComboBox cb = (JComboBox) e.getSource();
        if (cb == positionCombo) {
            int selCase =  cb.getSelectedIndex();
           
        } 
        
        if (cb == clubCombo) {
            } 
        
        if (cb == playerCombo) {
            int selCpu =  cb.getSelectedIndex();
            } 
    }

    public void updateCases(String[] newCases) {
        cases = newCases;
        }

    public void updateMobos(String[] newMobos) {
        mobos = newMobos;
    }

    public void updateCPUS(String[] newCPUS) {
        cpus = newCPUS;
    }

    public void updateGPUS(String[] newGPUS) {
        gpus = newGPUS;
    }

    public void updatePSUS(String[] newPSUS) {
        psus = newPSUS;
    }

    public void updateHardDrive(String[] newHardDrives) {
        hardDrives = newHardDrives;
    }

    public void updateHeatSink(String[] newHeatSinks) {
        heatSinks = newHeatSinks;
    }

    public void updateRAM(String[] newRAMS) {
        rams = newRAMS;
    }
}
