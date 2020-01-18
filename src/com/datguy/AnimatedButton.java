package com.datguy;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

public class AnimatedButton extends AnimatedSprite {

    private Dimension displayDimensions;
    private Point displayPosition;

    public AnimatedButton(String path, int frameWidth, int frameHeight, int displayWidth, int displayHeight,
                          int displayX, int displayY, int frameCount, int fps) throws IOException {
        super(path, frameWidth, frameHeight, frameCount, fps);
        displayDimensions = new Dimension(displayWidth, displayHeight);
        displayPosition = new Point(displayX, displayY);
    }

    public void updateBounds(int displayWidth, int displayHeight, int displayX, int displayY) {
        displayDimensions.setSize(displayWidth, displayHeight);
        displayPosition.setLocation(displayX, displayY);
    }

    public boolean checkClick(Point mousePosition) {
        return mousePosition.x > displayPosition.x && mousePosition.x < displayPosition.x + displayDimensions.width
                && mousePosition.y > displayPosition.y && mousePosition.y < displayPosition.y + displayDimensions.height;
    }

    public List<Point> getBounds() {
        return Arrays.asList(new Point(displayPosition.x, displayPosition.y), new Point(displayPosition.x + displayDimensions.width, displayPosition.y), new Point(displayPosition.x, displayPosition.y + displayDimensions.height), new Point(displayPosition.x + displayDimensions.width, displayPosition.y + displayDimensions.height));
    }

    public Image getFrame() {
        Image unscaled = super.getFrame();
        return unscaled.getScaledInstance(displayDimensions.width, displayDimensions.height, Image.SCALE_DEFAULT);
    }
}
