package com.kingyu.flappybird.component;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.kingyu.flappybird.app.Game;
import com.kingyu.flappybird.util.Constant;
import com.kingyu.flappybird.util.GameUtil;
import com.kingyu.flappybird.util.MusicUtil;

/**
 * ChimCap nhat，Cap nhatChimCap nhatVeCap nhat
 *
 * @author Kingyu
 */
public class Bird {
    public static final int IMG_COUNT = 8; // Hinh anhCap nhat
    public static final int STATE_COUNT = 4; // Cap nhat
    private final BufferedImage[][] birdImages; // ChimCap nhatHinh anhCap nhat
    private final int x;
    private int y; // ChimCap nhat
    private int wingState; // Cap nhat

    // Hinh anhCap nhat
    private BufferedImage image; // Cap nhatChimHinh anh

    // ChimCap nhat
    private int state;
    public static final int BIRD_NORMAL = 0;
    public static final int BIRD_UP = 1;
    public static final int BIRD_FALL = 2;
    public static final int BIRD_DEAD_FALL = 3;
    public static final int BIRD_DEAD = 4;

    private final Rectangle birdCollisionRect; // Cap nhat
    public static final int RECT_DESCALE = 2; // Cap nhat

    private final ScoreCounter counter; // Cap nhat
    private final GameOverAnimation gameOverAnimation;

    public static int BIRD_WIDTH;
    public static int BIRD_HEIGHT;

    // Cap nhatKhoi tao
    public Bird() {
        counter = ScoreCounter.getInstance(); // Cap nhat
        gameOverAnimation = new GameOverAnimation();

        // Cap nhatChimHinh anhCap nhat
        birdImages = new BufferedImage[STATE_COUNT][IMG_COUNT];
        for (int j = 0; j < STATE_COUNT; j++) {
            for (int i = 0; i < IMG_COUNT; i++) {
                birdImages[j][i] = GameUtil.loadBufferedImage(Constant.BIRDS_IMG_PATH[j][i]);
            }
        }

        assert birdImages[0][0] != null;
        BIRD_WIDTH = birdImages[0][0].getWidth();
        BIRD_HEIGHT = birdImages[0][0].getHeight();

        // Khoi taoChimCap nhat
        x = Constant.FRAME_WIDTH >> 2;
        y = Constant.FRAME_HEIGHT >> 1;

        // Khoi taoCap nhat
        int rectX = x - BIRD_WIDTH / 2;
        int rectY = y - BIRD_HEIGHT / 2;
        birdCollisionRect = new Rectangle(rectX + RECT_DESCALE, rectY + RECT_DESCALE * 2, BIRD_WIDTH - RECT_DESCALE * 3,
                BIRD_WIDTH - RECT_DESCALE * 4); // Cap nhatChimCap nhat
    }

    // VeCap nhat
    public void draw(Graphics g) {
        movement();
        int state_index = Math.min(state, BIRD_DEAD_FALL); // Hinh anhCap nhat
        // ChimCap nhatTinh toan
        int halfImgWidth = birdImages[state_index][0].getWidth() >> 1;
        int halfImgHeight = birdImages[state_index][0].getHeight() >> 1;
        if (velocity > 0)
            image = birdImages[BIRD_UP][0];
        g.drawImage(image, x - halfImgWidth, y - halfImgHeight, null); // xCap nhat1/4Cap nhat，yCap nhat

        if (state == BIRD_DEAD)
            gameOverAnimation.draw(g, this);
        else if (state != BIRD_DEAD_FALL)
            drawScore(g);
        // VeCap nhat
//      g.setColor(Color.black);
//      g.drawRect((int) birdRect.getX(), (int) birdRect.getY(), (int) birdRect.getWidth(), (int) birdRect.getHeight());
    }

    public static final int ACC_FLAP = 14; // players speed on flapping
    public static final double ACC_Y = 2; // players downward acceleration
    public static final int MAX_VEL_Y = 15; // max vel along Y, max descend speed
    private int velocity = 0; // bird's velocity along Y, default same as playerFlapped
    private final int BOTTOM_BOUNDARY = Constant.FRAME_HEIGHT - GameBackground.GROUND_HEIGHT - (BIRD_HEIGHT / 2);

    // ChimCap nhat
    private void movement() {
        // Cap nhat，Cap nhatChimCap nhat
        wingState++;
        image = birdImages[Math.min(state, BIRD_DEAD_FALL)][wingState / 10 % IMG_COUNT];
        if (state == BIRD_FALL || state == BIRD_DEAD_FALL) {
            freeFall();
            if (birdCollisionRect.y > BOTTOM_BOUNDARY) {
                if (state == BIRD_FALL) {
                    MusicUtil.playCrash();
                }
                die();
            }
        }
    }

    private void freeFall() {
        if (velocity < MAX_VEL_Y)
            velocity -= ACC_Y;
        y = Math.min((y - velocity), BOTTOM_BOUNDARY);
        birdCollisionRect.y = birdCollisionRect.y - velocity;
    }

    private void die() {
        counter.saveScore();
        state = BIRD_DEAD;
        Game.setGameState(Game.STATE_OVER);
    }

    // ChimCap nhat
    public void birdFlap() {
        if (keyIsReleased()) {
            if (isDead())
                return;
            MusicUtil.playFly(); // Cap nhat
            state = BIRD_UP;
            if (birdCollisionRect.y > Constant.TOP_BAR_HEIGHT) {
                velocity = ACC_FLAP; // Cap nhatToc doCap nhatToc do
                wingState = 0; // Dat laiCap nhat
            }
            keyPressed();
        }
    }

    // ChimCap nhat
    public void birdFall() {
        if (isDead())
            return;
        state = BIRD_FALL;
    }

    // ChimCap nhat（Cap nhat）
    public void deadBirdFall() {
        state = BIRD_DEAD_FALL;
        MusicUtil.playCrash(); // Cap nhat
        velocity = 0;  // Toc doCap nhat0，Cap nhatChimTiep tucCap nhatOng nuocCap nhat
    }

    // Kiem traChimCap nhat
    public boolean isDead() {
        return state == BIRD_DEAD_FALL || state == BIRD_DEAD;
    }

    // VeCap nhatDiem so
    private void drawScore(Graphics g) {
        g.setColor(Color.white);
        g.setFont(Constant.CURRENT_SCORE_FONT);
        String str = Long.toString(counter.getCurrentScore());
        int x = Constant.FRAME_WIDTH - GameUtil.getStringWidth(Constant.CURRENT_SCORE_FONT, str) >> 1;
        g.drawString(str, x, Constant.FRAME_HEIGHT / 10);
    }

    // Dat laiChim
    public void reset() {
        state = BIRD_NORMAL; // ChimCap nhat
        y = Constant.FRAME_HEIGHT >> 1; // ChimCap nhat
        velocity = 0; // ChimToc do

        int ImgHeight = birdImages[state][0].getHeight();
        birdCollisionRect.y = y - ImgHeight / 2 + RECT_DESCALE * 2; // ChimCap nhat

        counter.reset(); // Dat laiCap nhat
    }

    private boolean keyFlag = true; // Cap nhat，trueCap nhat，Cap nhat

    public void keyPressed() {
        keyFlag = false;
    }

    public void keyReleased() {
        keyFlag = true;
    }

    public boolean keyIsReleased() {
        return keyFlag;
    }

    public long getCurrentScore() {
        return counter.getCurrentScore();
    }

    public long getBestScore() {
        return counter.getBestScore();
    }

    public int getBirdX() {
        return x;
    }

    // LayChimCap nhat
    public Rectangle getBirdCollisionRect() {
        return birdCollisionRect;
    }
}
