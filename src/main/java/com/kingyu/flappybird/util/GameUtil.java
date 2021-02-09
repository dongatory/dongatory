package com.kingyu.flappybird.util;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;

import javax.imageio.ImageIO;

/**
 * Lop tien ich，Cap nhat
 *
 * @author Kingyu
 */
public class GameUtil {

    private GameUtil() {
    } // Cap nhat，Cap nhat

    /**
     * Cap nhatHinh anhCap nhat
     *
     * @param imgPath Hinh anhCap nhat
     * @return Hinh anhCap nhat
     */
    public static BufferedImage loadBufferedImage(String imgPath) {
        try {
            return ImageIO.read(new FileInputStream(imgPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Kiem traCap nhat
     *
     * @param numerator   Cap nhat，Cap nhat0Cap nhat
     * @param denominator Cap nhat，Cap nhat0Cap nhat
     * @return Cap nhattrue，Cap nhatfalse
     */
    public static boolean isInProbability(int numerator, int denominator) throws Exception {
        // Cap nhat0
        if (numerator <= 0 || denominator <= 0) {
            throw new Exception("Cap nhat");
        }
        //Cap nhat，Cap nhat
        if (numerator >= denominator) {
            return true;
        }

        return getRandomNumber(1, denominator + 1) <= numerator;
    }

    /**
     * Cap nhatNgau nhienCap nhat
     *
     * @param min Cap nhat，Cap nhat
     * @param max Cap nhat，Cap nhat
     * @return Cap nhatNgau nhienCap nhat
     */
    public static int getRandomNumber(int min, int max) {
        return (int) (Math.random() * (max - min) + min);
    }

    /**
     * Cap nhat
     */
    public static int getStringWidth(Font font, String str) {
        AffineTransform affinetransform = new AffineTransform();
        FontRenderContext frc = new FontRenderContext(affinetransform, true, true);
        return (int) (font.getStringBounds(str, frc).getWidth());
    }

    public static int getStringHeight(Font font, String str) {
        AffineTransform affinetransform = new AffineTransform();
        FontRenderContext frc = new FontRenderContext(affinetransform, true, true);
        return (int) (font.getStringBounds(str, frc).getHeight());
    }


    /**
     *
     * @param image:Hinh anhCap nhat
     * @param x：xCap nhat
     * @param y：yCap nhat
     * @param g：Cap nhat
     */
    public static void drawImage(BufferedImage image, int x, int y, Graphics g) {
        g.drawImage(image, x, y, null);
    }

}
