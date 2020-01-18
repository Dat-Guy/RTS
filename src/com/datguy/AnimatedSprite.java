package com.datguy;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class AnimatedSprite extends Image {

    private final BufferedImage image;
    private final ArrayList<BufferedImage> frames = new ArrayList<>();
    private final int width;
    private final int height;
    private final int fps;
    private final long creationTime;

    public AnimatedSprite(String path, int frameWidth, int frameHeight, int frameCount, int fps) throws IOException {
        creationTime = System.currentTimeMillis();
        this.fps = fps;
        width = frameWidth;
        height = frameHeight;
        image = ImageIO.read(new File(path));
        for (int i = 0; i < frameCount; i++) {
            frames.add(image.getSubimage(0,frameHeight * i, frameWidth, frameHeight));
        }
    }

    @Override
    public int getWidth(ImageObserver observer) {
        return width;
    }

    @Override
    public int getHeight(ImageObserver observer) {
        return height;
    }

    @Override
    public ImageProducer getSource() {
        return getFrame().getSource();
    }

    @Override
    public Graphics getGraphics() {
        return getFrame().getGraphics();
    }

    @Override
    public Object getProperty(String name, ImageObserver observer) {
        return image.getProperty(name, observer);
    }

    Image getFrame(){
        double frameTime = 1000.0 / fps;
        double relativeTime = (System.currentTimeMillis() - creationTime) % (frameTime * frames.size());
        return frames.get((int) Math.floor(relativeTime / frameTime));
    }
}
