package com.vpjardim.contactsfix;

import org.imgscalr.Scalr;
import org.imgscalr.Scalr.Method;
import org.imgscalr.Scalr.Mode;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

/**
 * @author Vinícius Jardim
 * 13/06/2016
 */
public class PackIcons {

    static Out[] outs = new Out[] {
            new Out(48,  "app\\src\\main\\res\\mipmap-mdpi\\ic_launcher.png"),
            new Out(72,  "app\\src\\main\\res\\mipmap-hdpi\\ic_launcher.png"),
            new Out(96,  "app\\src\\main\\res\\mipmap-xhdpi\\ic_launcher.png"),
            new Out(144, "app\\src\\main\\res\\mipmap-xxhdpi\\ic_launcher.png"),
            new Out(192, "app\\src\\main\\res\\mipmap-xxxhdpi\\ic_launcher.png"),
            new Out(512, "app\\src\\main\\ic_launcher-web.png"),
    };

    public static class Out {

        public Out(int size, String path) {
            this.size = size;
            this.path = path;
        }

        public int size;
        public String path;
    }

    public static void main(String[] args) throws Exception {

        String iconPath = "D:\\Dropbox\\Projetos\\Outros\\ContactsFix_files\\ps\\app_icon.png";
        String projectPath = "D:\\Dropbox\\Projetos\\Outros\\ContactsFix\\";

        File iconFile = new File(iconPath);
        BufferedImage icon = ImageIO.read(iconFile);

        System.out.println("Packing icons...");
        System.out.println("Opening base icon at: " + iconPath);
        System.out.println("Project root at: " + projectPath);

        for(Out o : outs) {

            // Icon resized
            BufferedImage iconR = Scalr.resize(
                    icon,
                    Method.ULTRA_QUALITY,
                    Mode.FIT_TO_WIDTH,
                    o.size,
                    o.size,
                    Scalr.OP_ANTIALIAS);

            ImageIO.write(iconR, "PNG", new File(projectPath + o.path));

            System.out.println("\tSize: " + o.size + "; Path: " + o.path);
        }

        System.out.println("End packing");
    }
}