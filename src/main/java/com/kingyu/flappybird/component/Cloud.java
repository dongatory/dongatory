package com.kingyu.flappybird.component;

import com.kingyu.flappybird.util.Constant;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 * Cap nhat，Cap nhatVeCap nhat
 *
 * @author Kingyu
 */
public class Cloud {

    private final int speed; // Toc do
    private int x; // Cap nhat
    private final int y;

    private final BufferedImage img;

    private final int scaleImageWidth;
    private final int scaleImageHeight;

    // Cap nhat
    public Cloud(BufferedImage img, int x, int y) {
        super();
        this.img = img;
        this.x = x;
        this.y = y;
        this.speed = Constant.GAME_SPEED * 2; //Cap nhatToc do
        // Cap nhatHinh anhCap nhat 1.0~2.0
        double scale = 1 + Math.random(); // Math.random()Cap nhat0.0~1.0Cap nhatNgau nhienCap nhat
        // Cap nhatHinh anh
        scaleImageWidth = (int) (scale * img.getWidth());
        scaleImageHeight = (int) (scale * img.getWidth());
    }

    // VeCap nhat
    public void draw(Graphics g, Bird bird) {
        int speed = this.speed;
        if (bird.isDead())
            speed = 1;
        x -= speed;
        g.drawImage(img, x, y, scaleImageWidth, scaleImageHeight, null);
    }

    /**
     * Kiem traCap nhat
     *
     * @return Cap nhattrue，Cap nhatfalse
     */
    public boolean isOutFrame() {
        return x < -1 * scaleImageWidth;
    }

}
