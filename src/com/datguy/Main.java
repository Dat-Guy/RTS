package com.datguy;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;
import javax.swing.*;

public class Main {

    public static JFrame frame;
    public static RTSOutput canvas;

    public static void main(String[] args) {

	    canvas = new RTSOutput("RTS");
	    canvas.setBackground(Constants.Colors.BACKGROUND);
	    canvas.setState(Constants.GameStates.GAME_MENU);
        Clip clip = canvas.playSound("Assets/Sound/Music/Test_03.wav", true);

        frame = new JFrame();
	    frame.add(canvas);
	    frame.setName("RTS");
	    frame.setSize(500, 500);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        new Thread(() -> {
            while(!Thread.interrupted()) {
                canvas.repaint();
                try {
                    Thread.sleep(10);
                } catch (InterruptedException ignored) {
                    System.out.println("A thread was interrupted.");
                }
            }
        }).start();
    }
}
