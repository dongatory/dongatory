package com.kingyu.flappybird.app;

import com.kingyu.flappybird.component.GameElementLayer;
import com.kingyu.flappybird.component.Bird;
import com.kingyu.flappybird.component.GameBackground;
import com.kingyu.flappybird.component.GameForeground;
import com.kingyu.flappybird.component.WelcomeAnimation;

import static com.kingyu.flappybird.util.Constant.FRAME_HEIGHT;
import static com.kingyu.flappybird.util.Constant.FRAME_WIDTH;
import static com.kingyu.flappybird.util.Constant.FRAME_X;
import static com.kingyu.flappybird.util.Constant.FRAME_Y;
import static com.kingyu.flappybird.util.Constant.FPS;
import static com.kingyu.flappybird.util.Constant.GAME_TITLE;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.*;
import java.awt.image.BufferedImage;


/**
 * Cap nhat，Cap nhatVe
 *
 * @author Kingyu
 */

public class Game extends Frame {
    private static final long serialVersionUID = 1L; // Duy tri tinh tuong thich phien ban

    private static int gameState; // Trang thai tro choi
    public static final int GAME_READY = 0; // Tro choi chua bat dau
    public static final int GAME_START = 1; // Tro choi bat dau
    public static final int STATE_OVER = 2; // Tro choi ket thuc

    private GameBackground background; // Doi tuong nen tro choi
    private GameForeground foreground; // Doi tuong canh truoc tro choi
    private Bird bird; // Doi tuong chim
    private GameElementLayer gameElement; // Doi tuong thanh phan tro choi
    private WelcomeAnimation welcomeAnimation; // Doi tuong khi tro choi chua bat dau

    // Khoi tao trong constructor
    public Game() {
        initFrame(); // Khoi tao cua so tro choi
        setVisible(true); // Cua so mac dinh an, dat thanh hien thi
        initGame(); // Khoi tao doi tuong tro choi
    }

    // Khoi tao cua so tro choi
    private void initFrame() {
        setSize(FRAME_WIDTH, FRAME_HEIGHT); // Dat kich thuoc cua so
        setTitle(GAME_TITLE); // Dat tieu de cua so
        setLocation(FRAME_X, FRAME_Y); // Vi tri ban dau cua so
        setResizable(false); // Dat kich thuoc cua so khong thay doi
        // Them su kien dong cua so (lang nghe su kien cua so, gui den doi tuong tham so)
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0); // Ket thucCap nhat
            }
        });
        addKeyListener(new BirdKeyListener()); // ThemCap nhat
    }

    // Cap nhat
    class BirdKeyListener implements KeyListener {
        // Cap nhat，Cap nhat
        public void keyPressed(KeyEvent e) {
            int keycode = e.getKeyCode();
            switch (gameState) {
                case GAME_READY:
                    if (keycode == KeyEvent.VK_SPACE) {
                        // Cap nhat，ChimCap nhatBat dauCap nhatTrong lucCap nhat
                        bird.birdFlap();
                        bird.birdFall();
                        setGameState(GAME_START); // Trang thai tro choiCap nhat
                    }
                    break;
                case GAME_START:
                    if (keycode == KeyEvent.VK_SPACE) {
                        //Cap nhat，Cap nhatTrong lucCap nhat
                        bird.birdFlap();
                        bird.birdFall();
                    }
                    break;
                case STATE_OVER:
                    if (keycode == KeyEvent.VK_SPACE) {
                        //Tro choi ket thucCap nhat，Bat dau laiCap nhat
                        resetGame();
                    }
                    break;
            }
        }

        // Bat dau laiCap nhat
        private void resetGame() {
            setGameState(GAME_READY);
            gameElement.reset();
            bird.reset();
        }

        // Cap nhat，Cap nhat
        public void keyReleased(KeyEvent e) {
            int keycode = e.getKeyChar();
            if (keycode == KeyEvent.VK_SPACE) {
                bird.keyReleased();
            }
        }

        public void keyTyped(KeyEvent e) {
        }
    }

    // Khoi taoCap nhat
    private void initGame() {
        background = new GameBackground();
        gameElement = new GameElementLayer();
        foreground = new GameForeground();
        welcomeAnimation = new WelcomeAnimation();
        bird = new Bird();
        setGameState(GAME_READY);

        // Cap nhat
        new Thread(() ->{
            while (true) {
                repaint(); // Cap nhatrepaint(),Cap nhatJVMCap nhatupdate()
                try {
                    Thread.sleep(FPS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    // Cap nhat：Cap nhat，Cap nhat：Cap nhatrepaint()。
    // Cap nhat：Cap nhatVe，Cap nhatXu ly
    // Cap nhat，Cap nhatVeCap nhat，Cap nhat
    // （Cap nhat）Cap nhatHinh anh，Cap nhatVeCap nhatVeCap nhatHinh anh，Cap nhatHinh anhVeCap nhat
    private final BufferedImage bufImg = new BufferedImage(FRAME_WIDTH, FRAME_HEIGHT, BufferedImage.TYPE_4BYTE_ABGR);

    /**
     * VeCap nhat Cap nhatrepaint()Cap nhat，JVMCap nhatupdate()，Cap nhatgCap nhat，Cap nhat
     * Cap nhat，Cap nhatrepaint()，Cap nhat
     */
    public void update(Graphics g) {
        Graphics bufG = bufImg.getGraphics(); // Cap nhatHinh anhCap nhat
        // Cap nhatHinh anhCap nhatVeCap nhatVeCap nhatHinh anh
        background.draw(bufG, bird); // NenCap nhat
        foreground.draw(bufG, bird); // Canh truocCap nhat
        if (gameState == GAME_READY) { // Tro choi chua bat dau
            welcomeAnimation.draw(bufG);
        } else { // Tro choi ket thuc
            gameElement.draw(bufG, bird); // Cap nhat
        }
        bird.draw(bufG);
        g.drawImage(bufImg, 0, 0, null); // Cap nhatHinh anhVeCap nhat
    }

    public static void setGameState(int gameState) {
        Game.gameState = gameState;
    }

}
