package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GUI implements ActionListener {
    private JLabel label;
    private JFrame frame;
    private JPanel encryptionPanel;
    private JPanel decriptionPanel;
    private JTextField textField;

    public GUI() {
        frame = new JFrame();



        JButton button = new JButton("Step 1");
        button.addActionListener(this);
        button.setBounds(400, 300, 30, 10);

        label = new JLabel();

        //Textinput
        textField = new JTextField();


        encryptionPanel = new JPanel();
        encryptionPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        encryptionPanel.setLayout(new GridLayout(8,8));
        encryptionPanel.setBackground(Color.gray);

        encryptionPanel.add(textField);
        encryptionPanel.add(button);
        encryptionPanel.add(label);

        decriptionPanel = new JPanel();
        decriptionPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        decriptionPanel.setLayout(new GridLayout(8,8));
        decriptionPanel.add(new JButton("hoi"));
        decriptionPanel.setBackground(Color.darkGray);


        frame.add(encryptionPanel, BorderLayout.WEST);
        frame.add(decriptionPanel, BorderLayout.EAST);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("pa3");
        frame.setSize(800,600);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int n = Integer.parseInt(textField.getText());
        Rsa result = new Rsa(n);
        result.calculatePandQ();
        label.setText(result.toString());
    }
}

