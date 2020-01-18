package com.datguy;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;

public class RTSOutput extends Canvas implements MouseInputListener {

    private final int borderSize = 5;

    //private Image menuIcon;
    private AnimatedSprite menuIcon;
    private AnimatedButton startButton;
    private Constants.GameStates state;
    private ArrayList<Clip> activeSounds;

    public RTSOutput(String name) {

        super();

        activeSounds = new ArrayList<>();
        this.setName(name);

        try {
            //menuIcon = RTSLoader.LoadImage("Assets/Images/RTS_02.gif");
            //menuIcon = menuIcon.getScaledInstance(800/8, 550/8, Image.SCALE_DEFAULT);
            menuIcon = new AnimatedSprite("Assets/Images/RTS_02.png", 800, 550, 9, 12);
            startButton = new AnimatedButton("Assets/Images/*START*.png", 800, 250, 0, 0, 0, 0, 6, 9);
        } catch (IOException e) {
            menuIcon = null;
            System.out.println("There was a non-fatal loading error:");
            e.printStackTrace();
            System.out.println("End stack trace.");
        }

        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Dimension s = this.getSize();

        Dimension paneSize = new Dimension();
        paneSize.setSize(s.width - borderSize * 2, s.height - borderSize * 2);

        g.setColor(Color.black);
        g.fillRoundRect(borderSize, borderSize, paneSize.width, paneSize.height, borderSize * 2, borderSize * 2);
        g.setClip(borderSize, borderSize, paneSize.width, paneSize.height);

        if (state == Constants.GameStates.GAME_MENU) {
            paintMainMenu(g, paneSize);
        }
    }

    public void paintMainMenu(Graphics g, Dimension paneSize) {

        Dimension menuSize = new Dimension();
        menuSize.setSize(paneSize.width / 20.0 * 8, paneSize.height / 20.0 * 5.5);

        g.drawImage(menuIcon.getFrame().getScaledInstance(menuSize.width, menuSize.height, Image.SCALE_DEFAULT), borderSize * 2, borderSize * 2, null);

        startButton.updateBounds(paneSize.width / 35 * 16, paneSize.height / 35 * 5, 18, paneSize.height / 2);
        Dimension buttonSize = new Dimension();
        buttonSize.setSize(paneSize.width / 35.0 * 16, paneSize.height / 35.0 * 5);

        g.drawImage(startButton.getFrame(), borderSize * 2, paneSize.height / 2, null);
    }

    public void paintGame(Graphics g, Dimension paneSize) {

    }

    public Clip playSound(String path, boolean loop) {
        try {
            AudioInputStream sound = RTSLoader.LoadSound(path);
            Clip clip = AudioSystem.getClip();
            clip.open(sound);
            if (loop) {
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            }
            activeSounds.add(clip);
            return clip;
        } catch (Exception e){
            System.out.println("There was a non-fatal loading error:");
            e.printStackTrace();
            System.out.println("End stack trace.");
            return null;
        }
    }

    public void setState(Constants.GameStates state) {
        if (state != this.state) {
            activeSounds.forEach(DataLine::stop);
        }
        this.state = state;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("Mouse pressed at:");
        Dimension paneSize = new Dimension();
        paneSize.setSize(this.getSize().width - borderSize * 2, this.getSize().height - borderSize * 2);
        Point mousePos = e.getPoint();
        System.out.println(mousePos);
        if (state == Constants.GameStates.GAME_MENU) {
            if (startButton.checkClick(mousePos)) {
                setState(Constants.GameStates.MAIN_GAME);
                System.out.println("Entering game");
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
