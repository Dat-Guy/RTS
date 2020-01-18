package com.datguy;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class RTSLoader {
    public static AudioInputStream LoadSound(String path) throws IOException, UnsupportedAudioFileException {
        return AudioSystem.getAudioInputStream(new File(path).getAbsoluteFile());
    }

    public static Image LoadImage(String path) throws IOException {
        return ImageIO.read(new File(path));
    }
}
