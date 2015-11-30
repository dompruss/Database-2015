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
    String[] positions = new String[35];     
    String[] clubs = new String[35];      
    String[] players = new String[35];    
    String[] seasons = new String[35];    
    String[] weeks = new String[35];       
    String[] games = new String[35];
    
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
        positionCombo = new JComboBox(positions);
        positionCombo.addActionListener(this);
        clubCombo = new JComboBox(clubs);
        clubCombo.addActionListener(this);
        playerCombo = new JComboBox(players);
        playerCombo.addActionListener(this);
        seasonCombo = new JComboBox(seasons);
        seasonCombo.addActionListener(this);
        weekCombo = new JComboBox(weeks);
        weekCombo.addActionListener(this);
        gameCombo = new JComboBox(games);
        gameCombo.addActionListener(this);
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

    public void updatePositions(String[] newPositions) {
        positions = newPositions;
        }

}
