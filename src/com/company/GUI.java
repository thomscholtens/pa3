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
    private JLabel labelE;
    private JLabel labelDecrypt;
    private JLabel decLabel1;
    private JLabel decLabel2;
    private JLabel labelStep1;
    private JLabel labelStep2;
    private JLabel labelStep3;
    private JLabel lDec;
    private Rsa result;
    private JFrame frame;
    private JPanel encryptionPanel;
    private JPanel step1Panel;
    private JPanel step2Panel;
    private JPanel step3Panel;
    private JPanel step1DecPanel;
    private JPanel step2DecPanel;
    private JPanel decriptionPanel;
    private JButton btnStep1;
    private JButton btnStep2;
    private JButton btnStep3;
    private JButton decBtnStep1;
    private JButton decBtnStep2;
    private JTextField textField;
    private JTextField tfMessage;
    private JTextField decTF2;
    private JTextField decTF1n;
    private JTextField decTF1e;

    public GUI() {
        frame = new JFrame();

        //Encryption panel
        encryptionPanel = new JPanel();
        encryptionPanel.setLayout(new GridLayout(3, 1));
        encryptionPanel.setBackground(Color.gray);
        encryptionPanel.setSize(400, 800);

        //Panel Step 1 Encryption
        step1Panel = new JPanel();
        step1Panel.setLayout(null);
        step1Panel.setBackground(Color.gray);

        JLabel labelEnc = new JLabel();
        labelEnc.setBounds(135, 0, 130, 40);
        labelEnc.setText("<html><body><h1>Encryption</h1></body></html>");

        labelStep1 = new JLabel();
        labelStep1.setBounds(10, 10, 100, 30);
        labelStep1.setText("<html><body><h2 style='text-decoration: underline;'>Step 1</h2></body></html>");

        JLabel textFieldLabel = new JLabel();
        textFieldLabel.setBounds(10, 50, 80, 30);
        textFieldLabel.setText("n=");

        textField = new JTextField(3);
        textField.setBounds(30, 55, 80, 20);

        btnStep1 = new JButton("Calc p and q");
        btnStep1.setBounds(120, 50, 120, 30);
        btnStep1.addActionListener(this);

        label = new JLabel();
        label.setText("<html><body>p is  <br>q is <br>Amount of time busy finding p and q:</body></html>");
        label.setBounds(10, 70, 400, 110);

        step1Panel.add(labelEnc);
        step1Panel.add(labelStep1);
        step1Panel.add(textFieldLabel);
        step1Panel.add(textField);
        step1Panel.add(btnStep1);
        step1Panel.add(label);
        //End panel step 1

        //Panel step 2 Encryption
        step2Panel = new JPanel();
        step2Panel.setLayout(null);
        step2Panel.setBackground(Color.lightGray);

        labelStep2 = new JLabel();
        labelStep2.setBounds(10, 10, 100, 30);
        labelStep2.setText("<html><body><h2 style='text-decoration: underline;'>Step 2</h2></body></html>");

        btnStep2 = new JButton("Generate new e");
        btnStep2.setBounds(160, 80, 160, 30);
        btnStep2.addActionListener(this);

        labelE = new JLabel();
        labelE.setBounds(100, 80, 100, 30);
        labelE.setText("<html><body>e is </body></html>");

        step2Panel.add(labelStep2);
        step2Panel.add(btnStep2);
        step2Panel.add(labelE);
        //End panel step 2

        //Panel step 3 Encryption
        step3Panel = new JPanel();
        step3Panel.setLayout(null);
        step3Panel.setBackground(Color.gray);

        labelStep3 = new JLabel();
        labelStep3.setBounds(10, 10, 100, 30);
        labelStep3.setText("<html><body><h2 style='text-decoration: underline;'>Step 3</h2></body></html>");

        JLabel messageLabel = new JLabel();
        messageLabel.setBounds(10, 65, 60, 30);
        messageLabel.setText("Message ");

        tfMessage = new JTextField();
        tfMessage.setBounds(70, 70, 220, 20);

        btnStep3 = new JButton("Encrypt");
        btnStep3.addActionListener(this);
        btnStep3.setBounds(300, 65, 80, 30);

        labelDecrypt = new JLabel();
        labelDecrypt.setBounds(10, 100, 300, 60);
        labelDecrypt.setText("<html><body>Message after encryption is: </body></html>");

        step3Panel.add(labelStep3);
        step3Panel.add(messageLabel);
        step3Panel.add(tfMessage);
        step3Panel.add(btnStep3);
        step3Panel.add(labelDecrypt);
        //End panel 3

        encryptionPanel.add(step1Panel);
        encryptionPanel.add(step2Panel);
        encryptionPanel.add(step3Panel);
        //End encryption panel

        //Decryption panel
        decriptionPanel = new JPanel();
        decriptionPanel.setLayout(new GridLayout(2, 1));
        decriptionPanel.setSize(400, 800);
        decriptionPanel.setBackground(Color.LIGHT_GRAY);

        //Panel step 1 Decription
        step1DecPanel = new JPanel();
        step1DecPanel.setLayout(null);
        step1DecPanel.setBackground(Color.gray);

        lDec = new JLabel();
        lDec.setText("<html><body><h1>Decryption</h1></body></html>");
        lDec.setBounds(135, 0, 130, 40);

        JLabel labelStep1Dec = new JLabel();
        labelStep1Dec.setBounds(10, 10, 100, 30);
        labelStep1Dec.setText("<html><body><h2 style='text-decoration: underline;'>Step 1</h2></body></html>");

        decBtnStep1 = new JButton("Calc d");
        decBtnStep1.addActionListener(this);
        decBtnStep1.setBounds(50, 110, 80, 30);

        decLabel1 = new JLabel();
        decLabel1.setBounds(50, 130, 50, 50);
        decLabel1.setText("<html><body>d is </body></html>");

        JLabel TFLabelN = new JLabel();
        TFLabelN.setBounds(50, 45, 80, 30);
        TFLabelN.setText("n=");

        decTF1n = new JTextField();
        decTF1n.setBounds(70, 50, 50, 20);

        JLabel TFLabelE = new JLabel();
        TFLabelE.setBounds(50, 75, 80, 30);
        TFLabelE.setText("e=");

        decTF1e = new JTextField();
        decTF1e.setBounds(70, 80, 50, 20);

        step1DecPanel.add(labelStep1Dec);
        step1DecPanel.add(lDec);
        step1DecPanel.add(decBtnStep1);
        step1DecPanel.add(decLabel1);
        step1DecPanel.add(decTF1n);
        step1DecPanel.add(TFLabelN);
        step1DecPanel.add(decTF1e);
        step1DecPanel.add(TFLabelE);
        //End step 1 dec

        //Panel Step 2 dec
        step2DecPanel = new JPanel();
        step2DecPanel.setLayout(null);
        step2DecPanel.setBackground(Color.lightGray);

        JLabel labelStep2Dec = new JLabel();
        labelStep2Dec.setBounds(10, 10, 100, 30);
        labelStep2Dec.setText("<html><body><h2 style='text-decoration: underline;'>Step 2</h2></body></html>");

        decBtnStep2 = new JButton("Decrypt");
        decBtnStep2.setBounds(50, 85, 80, 30);
        decBtnStep2.addActionListener(this);
        decBtnStep2.setEnabled(false);
        decLabel2 = new JLabel();
        decLabel2.setBounds(50, 100, 280, 60);
        decLabel2.setText("<html><body>Message after encryption is: </body></html>");

        decTF2 = new JTextField(3);
        decTF2.setBounds(50, 50, 300, 20);
//        decTF2.setBounds(70, 70, 220, 20);

        step2DecPanel.add(labelStep2Dec);
        step2DecPanel.add(decTF2);
        step2DecPanel.add(decBtnStep2);
        step2DecPanel.add(decLabel2);
        //End step 2 dec

        decriptionPanel.add(step1DecPanel);
        decriptionPanel.add(step2DecPanel);
        //End decyption panel

        frame.setLayout(new GridLayout(0, 2));
        frame.add(encryptionPanel);
        frame.add(decriptionPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("pa3");
        frame.setSize(800, 600);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnStep1) {
            int n = Integer.parseInt(textField.getText());
            decTF1n.setText(Integer.toString(n));
            this.result = new Rsa(n);
            this.result.calculatePandQ();
            this.result.calculatePhi();
            String error;
            System.out.println(result.phi);
            if (result.phi == 0 || result.p == 0 || result.q == 0) {
                btnStep2.setEnabled(false);
                error = " <br><p style='color: red'>This is not a valid n</p> <br>";
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
            decTF1e.setText(Integer.toString(this.result.e));
            labelE.setText("<html><body>e is " + this.result.e +

                    "</body></html>");
        }
        if (e.getSource() == btnStep3) {
            String message = tfMessage.getText();
            result.encryptMessage(message);
            labelDecrypt.setText("<html><body>Message after encryption is: " + this.result.encryptMessage +
                    "</body></html>");
            decTF2.setText(this.result.encryptMessage.toString());
        }
        if (e.getSource() == decBtnStep1) {
            if(this.result ==null){
                this.result = new Rsa(Integer.parseInt(decTF1n.getText()));
            }

            this.result.n =Integer.parseInt(decTF1n.getText());
            this.result.e =Integer.parseInt(decTF1e.getText());
            this.result.calculatePandQ();
            this.result.calculatePhi();
            this.result.calculateD();
            decBtnStep2.setEnabled(true);

            decLabel1.setText("<html><body>d is " + this.result.d +
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
            decLabel2.setText("<html><body>Message after encryption is: " + this.result.decryptMessage +
                    "</body></html>");

        }
    }
}

