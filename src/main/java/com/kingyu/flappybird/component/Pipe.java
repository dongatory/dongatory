package com.kingyu.flappybird.component;

import java.awt.*;
import java.awt.image.BufferedImage;

import com.kingyu.flappybird.util.Constant;
import com.kingyu.flappybird.util.GameUtil;

/**
 * Ong nuocCap nhat，Cap nhatOng nuocCap nhatVeCap nhat
 *
 * @author Kingyu
 */
public class Pipe {
    static BufferedImage[] imgs; // Ong nuocCap nhatHinh anh，staticCap nhatHinh anhCap nhat

    static {// Cap nhat，Cap nhat，Khoi taoHinh anh
        final int PIPE_IMAGE_COUNT = 3;
        imgs = new BufferedImage[PIPE_IMAGE_COUNT];
        for (int i = 0; i < PIPE_IMAGE_COUNT; i++) {
            imgs[i] = GameUtil.loadBufferedImage(Constant.PIPE_IMG_PATH[i]);
        }
    }

    // Cap nhatOng nuocCap nhat
    public static final int PIPE_WIDTH = imgs[0].getWidth();
    public static final int PIPE_HEIGHT = imgs[0].getHeight();
    public static final int PIPE_HEAD_WIDTH = imgs[1].getWidth();
    public static final int PIPE_HEAD_HEIGHT = imgs[1].getHeight();

    int x, y; // Ong nuocCap nhat，Cap nhat
    int width, height; // Ong nuocCap nhat，Cap nhat

    boolean visible; // Ong nuocCap nhat，trueCap nhat，falseCap nhatNhom doi tuong
    // Ong nuocCap nhat
    int type;
    public static final int TYPE_TOP_NORMAL = 0;
    public static final int TYPE_TOP_HARD = 1;
    public static final int TYPE_BOTTOM_NORMAL = 2;
    public static final int TYPE_BOTTOM_HARD = 3;
    public static final int TYPE_HOVER_NORMAL = 4;
    public static final int TYPE_HOVER_HARD = 5;

    // Ong nuocCap nhatToc do
    int speed;

    Rectangle pipeRect; // Ong nuocCap nhat

    // Cap nhat
    public Pipe() {
        this.speed = Constant.GAME_SPEED;
        this.width = PIPE_WIDTH;

        pipeRect = new Rectangle();
        pipeRect.width = PIPE_WIDTH;
    }

    /**
     * DatOng nuocCap nhat
     *
     * @param x:xCap nhat
     * @param y：yCap nhat
     * @param height：Ong nuocCap nhat
     * @param type：Ong nuocCap nhat
     * @param visible：Ong nuocCap nhat
     */
    public void setAttribute(int x, int y, int height, int type, boolean visible) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.type = type;
        this.visible = visible;
        setRectangle(this.x, this.y, this.height);
    }

    /**
     * DatCap nhat
     */
    public void setRectangle(int x, int y, int height) {
        pipeRect.x = x;
        pipeRect.y = y;
        pipeRect.height = height;
    }

    // Kiem traOng nuocCap nhat
    public boolean isVisible() {
        return visible;
    }

    // VeCap nhat
    public void draw(Graphics g, Bird bird) {
        switch (type) {
            case TYPE_TOP_NORMAL:
                drawTopNormal(g);
                break;
            case TYPE_BOTTOM_NORMAL:
                drawBottomNormal(g);
                break;
            case TYPE_HOVER_NORMAL:
                drawHoverNormal(g);
                break;
        }
//      //VeCap nhat
//      g.setColor(Color.black);
//      g.drawRect((int) pipeRect.getX(), (int) pipeRect.getY(), (int) pipeRect.getWidth(), (int) pipeRect.getHeight());

        //Cap nhatOng nuocCap nhatDi chuyen
        if (bird.isDead()) {
            return;
        }
        movement();
    }

    // VeCap nhatOng nuoc
    private void drawTopNormal(Graphics g) {
        // Cap nhat
        int count = (height - PIPE_HEAD_HEIGHT) / PIPE_HEIGHT + 1; // Cap nhat+1
        // VeOng nuocCap nhat
        for (int i = 0; i < count; i++) {
            g.drawImage(imgs[0], x, y + i * PIPE_HEIGHT, null);
        }
        // VeOng nuocCap nhat
        g.drawImage(imgs[1], x - ((PIPE_HEAD_WIDTH - width) >> 1),
                height - Constant.TOP_PIPE_LENGTHENING - PIPE_HEAD_HEIGHT, null); // Ong nuocCap nhatOng nuocCap nhat，xCap nhatXu ly
    }

    // VeCap nhatOng nuoc
    private void drawBottomNormal(Graphics g) {
        // Cap nhat
        int count = (height - PIPE_HEAD_HEIGHT - Constant.GROUND_HEIGHT) / PIPE_HEIGHT + 1;
        // VeOng nuocCap nhat
        for (int i = 0; i < count; i++) {
            g.drawImage(imgs[0], x, Constant.FRAME_HEIGHT - PIPE_HEIGHT - Constant.GROUND_HEIGHT - i * PIPE_HEIGHT,
                    null);
        }
        // VeOng nuocCap nhat
        g.drawImage(imgs[2], x - ((PIPE_HEAD_WIDTH - width) >> 1), Constant.FRAME_HEIGHT - height, null);
    }

    // VeCap nhatOng nuoc
    private void drawHoverNormal(Graphics g) {
        // Cap nhat
        int count = (height - 2 * PIPE_HEAD_HEIGHT) / PIPE_HEIGHT + 1;
        // VeOng nuocCap nhat
        g.drawImage(imgs[2], x - ((PIPE_HEAD_WIDTH - width) >> 1), y, null);
        // VeOng nuocCap nhat
        for (int i = 0; i < count; i++) {
            g.drawImage(imgs[0], x, y + i * PIPE_HEIGHT + PIPE_HEAD_HEIGHT, null);
        }
        // VeOng nuocCap nhat
        int y = this.y + height - PIPE_HEAD_HEIGHT;
        g.drawImage(imgs[1], x - ((PIPE_HEAD_WIDTH - width) >> 1), y, null);
    }

    /**
     * Cap nhatOng nuocCap nhat
     */
    private void movement() {
        x -= speed;
        pipeRect.x -= speed;
        if (x < -1 * PIPE_HEAD_WIDTH) {// Ong nuocCap nhat
            visible = false;
        }
    }

    /**
     * Kiem traCap nhatOng nuocCap nhat
     *
     * @return Cap nhattrue，Cap nhatfalse
     */
    public boolean isInFrame() {
        return x + width < Constant.FRAME_WIDTH;
    }

    // LayOng nuocCap nhatxCap nhat
    public int getX() {
        return x;
    }

    // LayOng nuocCap nhat
    public Rectangle getPipeRect() {
        return pipeRect;
    }

}
