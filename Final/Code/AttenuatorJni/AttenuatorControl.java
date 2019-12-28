package com.brandonhessler.Attenuator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Cpl Hess on 4/15/2017.
 */
public class AttenuatorControl extends JFrame{
    private JButton connectToBoxesButton;
    private JButton startButton;
    private JButton stopButton;
    private JButton pauseButton;
    private JButton box1OnOffButton;
    private JButton box2OnOffButton;
    private JButton box1StartValButton;
    private JButton box2StartValButton;
    private JButton setRateButton;
    private JButton getValsButton;
    private JButton getStatusButton;
    private JButton getDataButton;
    private JTextField tfRunNumber;
    private JCheckBox booleanOnCheckBox1;
    private JCheckBox booleanOnCheckBox2;
    private JTextField tfBox1StartVal;
    private JTextField tfBox2StartVal;
    private JTextField tfBox1Port;
    private JTextField tfBox2Port;
    private JTextField tfRateIncrement;
    private JTextField tfRatePeriod;
    private JRadioButton box2RadioButton;
    private JRadioButton box1RadioButton;
    private JPanel panel1;
    private JButton btnHelp;
    private String helpString;

    public AttenuatorControl() {
        super("Attenuator Control");
        initComponents();
        initFieldFormatRestrictors();
        initHelpString();
        setContentPane(panel1);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void initComponents() {
        connectToBoxesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayMessage(new AttenuatorJni().
                        connect(Integer.parseInt(tfBox1Port.getText()),
                                Integer.parseInt(tfBox2Port.getText()))
                );
            }
        });

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayMessage(new AttenuatorJni().start(Integer
                        .parseInt(tfRunNumber.getText())));
            }
        });

        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayMessage(new AttenuatorJni().stop());
            }
        });

        pauseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayMessage(new AttenuatorJni().pause());
            }
        });

        box1OnOffButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayMessage(new AttenuatorJni()
                        .box1OnOff(booleanOnCheckBox1.isSelected()));
            }
        });

        box2OnOffButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayMessage(new AttenuatorJni()
                        .box2OnOff(booleanOnCheckBox2.isSelected()));
            }
        });

        box1StartValButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayMessage(new AttenuatorJni()
                        .box1StartVal(Integer
                                .parseInt(tfBox1StartVal.getText())));
            }
        });

        box2StartValButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayMessage(new AttenuatorJni()
                        .box2StartVal(Integer
                                .parseInt(tfBox2StartVal.getText())));
            }
        });

        setRateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int boxNum;
                if (box1RadioButton.isSelected())
                    boxNum = 1;
                else
                    boxNum = 2;

                displayMessage(new AttenuatorJni().setScanRate(
                        Integer.parseInt(tfRateIncrement.getText()),
                                Integer.parseInt(tfRatePeriod.getText()), boxNum)
                );
            }
        });

        getValsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,
                        new AttenuatorJni().getAttenuationValueMessage());
            }
        });

        getStatusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,
                        new AttenuatorJni().getStatusMessage());
            }
        });

        getDataButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,
                        new AttenuatorJni().getDataMessage());
            }
        });

        btnHelp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, helpString);
            }
        });
    }

    private void initFieldFormatRestrictors() {
        tfRunNumber.setDocument(new RegExFieldFormatRestrictor("^[0-9]*[1-9][0-9]*$", false));
        tfBox1StartVal.setDocument(new RegExFieldFormatRestrictor("^[0-9]*[1-9][0-9]*$", false));
        tfBox2StartVal.setDocument(new RegExFieldFormatRestrictor("^[0-9]*[1-9][0-9]*$", false));
        tfBox1Port.setDocument(new RegExFieldFormatRestrictor(
                "^([0-9]{1,4}|[1-5][0-9]{4}|6[0-4][0-9]{3}|" +
                        "65[0-4][0-9]{2}|655[0-2][0-9]|6553[0-5])$", false));
        tfBox2Port.setDocument(new RegExFieldFormatRestrictor(
                "^([0-9]{1,4}|[1-5][0-9]{4}|6[0-4][0-9]{3}|" +
                        "65[0-4][0-9]{2}|655[0-2][0-9]|6553[0-5])$", false));
        tfRateIncrement.setDocument(new RegExFieldFormatRestrictor("-?[0-9]{0,10}", false));
        tfRatePeriod.setDocument(new RegExFieldFormatRestrictor("^[0-9]*[1-9][0-9]*$", false));
    }

        private void displayMessage(boolean sucess) {
            String str;
            if (sucess)
                str = "Message successfully delivered to AP";
            else
                str = "There was an error in sending your message";

            JOptionPane.showMessageDialog(null, str);
        }

        private void initHelpString() {
            helpString = "In order to properly work these conditions " +
                    "must be met:\n" +
                    "Connect to Boxes: Box 1 Port = 5150\n" +
                    "Connect to Boxes: Box 2 Port = 5151\n" +
                    "Run number must be a positive int and " +
                    "system cannot be currently running\n" +
                    "System must be running in order to stop it\n" +
                    "System must be running in order to pause it\n" +
                    "Box 1 & 2 Start vals must be positive ints\n" +
                    "Scan Rate: increment must be an int, " +
                    "period must be a positive int, and a box must be selected";
        }

}
