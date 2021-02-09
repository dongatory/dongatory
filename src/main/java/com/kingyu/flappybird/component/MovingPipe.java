package com.kingyu.flappybird.component;

import java.awt.Graphics;

import com.kingyu.flappybird.util.Constant;

/**
 * Di chuyenOng nuocCap nhat，Cap nhatPipeCap nhat
 *
 * @author Kingyu
 */

public class MovingPipe extends Pipe {

    private int dealtY; // Di chuyenOng nuocCap nhat
    public static final int MAX_DELTA = 50; // Cap nhatDi chuyenCap nhat
    private int direction;
    public static final int DIR_UP = 0;
    public static final int DIR_DOWN = 1;

    // Cap nhat
    public MovingPipe() {
        super();
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
        super.setAttribute(x, y, height, type, visible);
        dealtY = 0;
        direction = DIR_DOWN;
        if (type == TYPE_TOP_HARD) {
            direction = DIR_UP;
        }
    }

    // VeCap nhat
    public void draw(Graphics g, Bird bird) {
        switch (type) {
            case TYPE_HOVER_HARD:
                drawHoverHard(g);
                break;
            case TYPE_TOP_HARD:
                drawTopHard(g);
                break;
            case TYPE_BOTTOM_HARD:
                drawBottomHard(g);
                break;

        }
        // Cap nhatOng nuocCap nhatDi chuyen
        if (bird.isDead()) {
            return;
        }
        movement();

        // VeCap nhat
//		g.setColor(Color.black);
//		g.drawRect((int) pipeRect.getX(), (int) pipeRect.getY(), (int) pipeRect.getWidth(), (int) pipeRect.getHeight());
    }

    // VeDi chuyenCap nhatOng nuoc
    private void drawHoverHard(Graphics g) {
        // Cap nhat
        int count = (height - 2 * PIPE_HEAD_HEIGHT) / PIPE_HEIGHT + 1;
        // VeOng nuocCap nhat
        g.drawImage(imgs[2], x - ((PIPE_HEAD_WIDTH - width) >> 1), y + dealtY, null);
        // VeOng nuocCap nhat
        for (int i = 0; i < count; i++) {
            g.drawImage(imgs[0], x, y + dealtY + i * PIPE_HEIGHT + PIPE_HEAD_HEIGHT, null);
        }
        // VeOng nuocCap nhat
        int y = this.y + height - PIPE_HEAD_HEIGHT;
        g.drawImage(imgs[1], x - ((PIPE_HEAD_WIDTH - width) >> 1), y + dealtY, null);
    }

    // VeCap nhatDi chuyenOng nuoc
    private void drawTopHard(Graphics g) {
        // Cap nhat
        int count = (height - PIPE_HEAD_HEIGHT) / PIPE_HEIGHT + 1; // Cap nhat+1
        // VeOng nuocCap nhat
        for (int i = 0; i < count; i++) {
            g.drawImage(imgs[0], x, y + dealtY + i * PIPE_HEIGHT, null);
        }
        // VeOng nuocCap nhat
        g.drawImage(imgs[1], x - ((PIPE_HEAD_WIDTH - width) >> 1),
                height - Constant.TOP_PIPE_LENGTHENING - PIPE_HEAD_HEIGHT + dealtY, null);
    }

    // VeCap nhatDi chuyenOng nuoc
    private void drawBottomHard(Graphics g) {
        // Cap nhat
        int count = (height - PIPE_HEAD_HEIGHT) / PIPE_HEIGHT + 1;
        // VeOng nuocCap nhat
        for (int i = 0; i < count; i++) {
            g.drawImage(imgs[0], x, Constant.FRAME_HEIGHT - PIPE_HEIGHT - i * PIPE_HEIGHT + dealtY, null);
        }
        // VeOng nuocCap nhat
        g.drawImage(imgs[2], x - ((PIPE_HEAD_WIDTH - width) >> 1), Constant.FRAME_HEIGHT - height + dealtY, null);
    }

    /**
     * Cap nhatOng nuocCap nhat
     */
    private void movement() {
        //xCap nhatOng nuocCap nhat
        x -= speed;
        pipeRect.x -= speed;
        if (x < -1 * PIPE_HEAD_WIDTH) {// Ong nuocCap nhat
            visible = false;
        }

        //Ong nuocCap nhatDi chuyenCap nhat
        if (direction == DIR_DOWN) {
            dealtY++;
            if (dealtY > MAX_DELTA) {
                direction = DIR_UP;
            }
        } else {
            dealtY--;
            if (dealtY <= 0) {
                direction = DIR_DOWN;
            }
        }
        pipeRect.y = this.y + dealtY;
    }

}
