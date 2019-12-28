package com.brandonhessler.Attenuator;

import de.javasoft.plaf.synthetica.SyntheticaBlackEyeLookAndFeel;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel(new SyntheticaBlackEyeLookAndFeel());
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Hello Professor!");
        AttenuatorControl attenuatorControl = new AttenuatorControl();
    }
}
