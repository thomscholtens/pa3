package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;

public class GUI implements ActionListener {
    private JLabel label;
    private JLabel labelStep2;
    private JLabel labelStep3;
    private JLabel decLabel1;
    private JLabel decLabel2;
    private JLabel lEnc;
    private JLabel lDec;
    private Rsa result;
    private JFrame frame;
    private JPanel encryptionPanel;
    private JPanel decriptionPanel;
    private JButton button;
    private JButton btnStep2;
    private JButton btnStep3;
    private JButton decBtnStep1;
    private JButton decBtnStep2;
    private JTextField textField;
    private JTextField tfMessage;
    private JTextField decTF2;

    public GUI() {
        frame = new JFrame();


        button = new JButton("Step 1");
        button.addActionListener(this);
        button.setBounds(400, 300, 30, 10);

        btnStep2 = new JButton("Step 2");
        btnStep2.addActionListener(this);
        btnStep2.setBounds(400, 300, 30, 10);

        btnStep3 = new JButton("Step 3");
        btnStep3.addActionListener(this);
        btnStep3.setBounds(400, 300, 30, 10);

        decBtnStep1 = new JButton("Step 1");
        decBtnStep1.addActionListener(this);
        decBtnStep2 = new JButton("Step 2");
        decBtnStep2.addActionListener(this);

        lEnc = new JLabel();
        lEnc.setText("encryption");
        lDec = new JLabel();
        lDec.setText("decryption");

        label = new JLabel();
        labelStep2 = new JLabel();
        labelStep3 = new JLabel();

        decLabel1 = new JLabel();
        decLabel2 = new JLabel();

        //Textinput
        decTF2 = new JTextField(3);
        decTF2.setBounds(400, 300, 30, 10);
        textField = new JTextField(3);
        textField.setBounds(400, 300, 30, 10);
        tfMessage = new JTextField(5);
        tfMessage.setBounds(400, 300, 30, 10);
        encryptionPanel = new JPanel();
        encryptionPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        encryptionPanel.setLayout(new BoxLayout(encryptionPanel, BoxLayout.PAGE_AXIS));
        encryptionPanel.setBackground(Color.gray);
        encryptionPanel.setSize(400,800);


        encryptionPanel.add(lEnc);
        encryptionPanel.add(textField);
        encryptionPanel.add(button);
        encryptionPanel.add(label);
        encryptionPanel.add(btnStep2);
        encryptionPanel.add(labelStep2);
        encryptionPanel.add(tfMessage);
        encryptionPanel.add(btnStep3);
        encryptionPanel.add(labelStep3);


        decriptionPanel = new JPanel();
        decriptionPanel.setSize(400,800);
        decriptionPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        decriptionPanel.setBackground(Color.LIGHT_GRAY);

        decriptionPanel.setLayout(new BoxLayout(decriptionPanel, BoxLayout.PAGE_AXIS));

        decriptionPanel.add(lDec);
        decriptionPanel.add(decBtnStep1);
        decriptionPanel.add(decLabel1);
        decriptionPanel.add(decTF2);
        decriptionPanel.add(decBtnStep2);
        decriptionPanel.add(decLabel2);

        frame.setLayout(new GridLayout(0,2));
        frame.add(encryptionPanel);
        frame.add(decriptionPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("pa3");
        frame.setSize(800, 600);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button) {
            int n = Integer.parseInt(textField.getText());
            this.result = new Rsa(n);
            this.result.calculatePandQ();
            this.result.calculatePhi();
            String error;
            System.out.println(result.phi);
            if (result.phi == 0) {
                btnStep2.setEnabled(false);
                error = " <br>this is not a valid N <br>";
            } else {
                error = "";
                btnStep2.setEnabled(true);
            }
            label.setText("<html><body>p is " + this.result.p +
                    "<br>q is " + this.result.q +
                    "<br>Amount of time busy finding p and q: " + this.result.time + "ms" +
                    error +
                    "</body></html>");
        }
        if (e.getSource() == btnStep2) {
            this.result.generateE();
            labelStep2.setText("<html><body>e is " + this.result.e +

                    "</body></html>");
        }
        if (e.getSource() == btnStep3) {
            String message = tfMessage.getText();
            result.encryptMessage(message);
            labelStep3.setText("<html><body>Message after encryption is:" + this.result.encryptMessage +
                    "</body></html>");
            decTF2.setText(this.result.encryptMessage.toString());
        }
        if (e.getSource() == decBtnStep1) {
            this.result.calculateD();
            decLabel1.setText("<html><body>D is:" + this.result.d +
                    "</body></html>");

        }
        if (e.getSource() == decBtnStep2) {


            String s = decTF2.getText();

            s = s.replace("[", "");
            s = s.replace("]", "");
            s = s.replace(" ", "");

            ArrayList<String> message1 = new ArrayList<String>(Arrays.asList(s.split(",")));
            ArrayList<Integer> message2 = new ArrayList<Integer>();
            for (int i = 0; i < message1.size(); i++) {
                message2.add(Integer.parseInt(message1.get(i)));
            }

            result.decryptMessage(message2);
            decLabel2.setText("<html><body>Message after encryption is:" + this.result.decryptMessage +
                    "</body></html>");

        }
    }
}

