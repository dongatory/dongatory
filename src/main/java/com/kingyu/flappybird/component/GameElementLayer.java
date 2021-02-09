package com.kingyu.flappybird.component;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import com.kingyu.flappybird.util.Constant;
import com.kingyu.flappybird.util.GameUtil;

/**
 * Cap nhat，Cap nhatOng nuocCap nhatTao raCap nhatVeCap nhatOng nuoc
 *
 * @author Kingyu
 */

public class GameElementLayer {
    private final List<Pipe> pipes; // Ong nuocCap nhat

    // Cap nhat
    public GameElementLayer() {
        pipes = new ArrayList<>();
    }

    // VeCap nhat
    public void draw(Graphics g, Bird bird) {
        // Cap nhatOng nuocCap nhat，Cap nhatVe，Cap nhat
        for (int i = 0; i < pipes.size(); i++) {
            Pipe pipe = pipes.get(i);
            if (pipe.isVisible()) {
                pipe.draw(g, bird);
            } else {
                Pipe remove = pipes.remove(i);
                PipePool.giveBack(remove);
                i--;
            }
        }
        // Phat hien va cham
        isCollideBird(bird);
        pipeBornLogic(bird);
    }

    /**
     * ThemOng nuocCap nhat： Cap nhatThemCap nhat，ThemCap nhat； Ong nuocCap nhat，Cap nhatChieu cao cua soCap nhat1/6；
     * Cap nhatOng nuocCap nhat1/4； Ong nuocCap nhat[1/8~5/8]
     */
    public static final int VERTICAL_INTERVAL = Constant.FRAME_HEIGHT / 5;
    public static final int HORIZONTAL_INTERVAL = Constant.FRAME_HEIGHT >> 2;
    public static final int MIN_HEIGHT = Constant.FRAME_HEIGHT >> 3;
    public static final int MAX_HEIGHT = ((Constant.FRAME_HEIGHT) >> 3) * 5;

    private void pipeBornLogic(Bird bird) {
        if (bird.isDead()) {
            // Cap nhatThemOng nuoc
            return;
        }
        if (pipes.size() == 0) {
            // Cap nhat，Cap nhatThemCap nhatOng nuoc
            int topHeight = GameUtil.getRandomNumber(MIN_HEIGHT, MAX_HEIGHT + 1); // Ngau nhienTao raOng nuocCap nhat

            Pipe top = PipePool.get("Pipe");
            top.setAttribute(Constant.FRAME_WIDTH, -Constant.TOP_PIPE_LENGTHENING,
                    topHeight + Constant.TOP_PIPE_LENGTHENING, Pipe.TYPE_TOP_NORMAL, true);

            Pipe bottom = PipePool.get("Pipe");
            bottom.setAttribute(Constant.FRAME_WIDTH, topHeight + VERTICAL_INTERVAL,
                    Constant.FRAME_HEIGHT - topHeight - VERTICAL_INTERVAL, Pipe.TYPE_BOTTOM_NORMAL, true);

            pipes.add(top);
            pipes.add(bottom);
        } else {
            // Kiem traCap nhatOng nuocCap nhat，Cap nhatThemOng nuoc
            Pipe lastPipe = pipes.get(pipes.size() - 1); // Cap nhatOng nuoc
            int currentDistance = lastPipe.getX() - bird.getBirdX() + Bird.BIRD_WIDTH / 2; // ChimCap nhatOng nuocCap nhat
            final int SCORE_DISTANCE = Pipe.PIPE_WIDTH * 2 + HORIZONTAL_INTERVAL; // Cap nhat
            if (lastPipe.isInFrame()) {
                if (pipes.size() >= PipePool.FULL_PIPE - 2
                        && currentDistance <= SCORE_DISTANCE + Pipe.PIPE_WIDTH * 3 / 2) {
                    ScoreCounter.getInstance().score(bird);
                }
                try {
                    int currentScore = (int) ScoreCounter.getInstance().getCurrentScore() + 1; // LayCap nhatDiem so
                    // Di chuyenOng nuocCap nhatDiem soCap nhat，Cap nhat19Cap nhatDi chuyenOng nuoc
                    if (GameUtil.isInProbability(currentScore, 20)) {
                        if (GameUtil.isInProbability(1, 4)) // Tao raDi chuyenOng nuocCap nhatDi chuyenCap nhatOng nuocCap nhat
                            addMovingHoverPipe(lastPipe);
                        else
                            addMovingNormalPipe(lastPipe);
                    } else {
                        if (GameUtil.isInProbability(1, 2)) // Tao raCap nhatOng nuocCap nhatOng nuocCap nhat
                            addNormalPipe(lastPipe);
                        else
                            addHoverPipe(lastPipe);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
    }

    /**
     * ThemCap nhatOng nuoc
     *
     * @param lastPipe Cap nhatOng nuocCap nhatLayxCap nhat
     */
    private void addNormalPipe(Pipe lastPipe) {
        int topHeight = GameUtil.getRandomNumber(MIN_HEIGHT, MAX_HEIGHT + 1); // Ngau nhienTao raOng nuocCap nhat
        int x = lastPipe.getX() + HORIZONTAL_INTERVAL; // Cap nhatOng nuocCap nhatxCap nhat = Cap nhatOng nuocCap nhatxCap nhat + Ong nuocCap nhat

        Pipe top = PipePool.get("Pipe"); // Cap nhatOng nuocNhom doi tuongCap nhatLayCap nhat

        // Datx, y, height, typeCap nhat
        top.setAttribute(x, -Constant.TOP_PIPE_LENGTHENING, topHeight + Constant.TOP_PIPE_LENGTHENING,
                Pipe.TYPE_TOP_NORMAL, true);

        Pipe bottom = PipePool.get("Pipe");
        bottom.setAttribute(x, topHeight + VERTICAL_INTERVAL, Constant.FRAME_HEIGHT - topHeight - VERTICAL_INTERVAL,
                Pipe.TYPE_BOTTOM_NORMAL, true);

        pipes.add(top);
        pipes.add(bottom);
    }

    /**
     * ThemCap nhatOng nuoc
     *
     * @param lastPipe Cap nhatOng nuocCap nhatLayxCap nhat
     */
    private void addHoverPipe(Pipe lastPipe) {

        // Ngau nhienTao raOng nuocCap nhat,Cap nhat[1/4,1/6]
        int topHoverHeight = GameUtil.getRandomNumber(Constant.FRAME_HEIGHT / 6, Constant.FRAME_HEIGHT / 4);
        int x = lastPipe.getX() + HORIZONTAL_INTERVAL; // Cap nhatOng nuocCap nhatxCap nhat = Cap nhatOng nuocCap nhatxCap nhat + Ong nuocCap nhat
        int y = GameUtil.getRandomNumber(Constant.FRAME_HEIGHT / 12, Constant.FRAME_HEIGHT / 6); // Ngau nhienOng nuocCap nhatyCap nhat，Cap nhat[1/6,1/12]

        int type = Pipe.TYPE_HOVER_NORMAL;

        // Tao raCap nhatOng nuoc
        Pipe topHover = PipePool.get("Pipe");
        topHover.setAttribute(x, y, topHoverHeight, type, true);

        // Tao raCap nhatOng nuoc
        int bottomHoverHeight = Constant.FRAME_HEIGHT - 2 * y - topHoverHeight - VERTICAL_INTERVAL;
        Pipe bottomHover = PipePool.get("Pipe");
        bottomHover.setAttribute(x, y + topHoverHeight + VERTICAL_INTERVAL, bottomHoverHeight, type, true);

        pipes.add(topHover);
        pipes.add(bottomHover);

    }

    /**
     * ThemDi chuyenCap nhatOng nuoc
     *
     * @param lastPipe Cap nhatOng nuocCap nhatLayxCap nhat
     */
    private void addMovingHoverPipe(Pipe lastPipe) {

        // Ngau nhienTao raOng nuocCap nhat,Cap nhat[1/4,1/6]
        int topHoverHeight = GameUtil.getRandomNumber(Constant.FRAME_HEIGHT / 6, Constant.FRAME_HEIGHT / 4);
        int x = lastPipe.getX() + HORIZONTAL_INTERVAL; // Cap nhatOng nuocCap nhatxCap nhat = Cap nhatOng nuocCap nhatxCap nhat + Ong nuocCap nhat
        int y = GameUtil.getRandomNumber(Constant.FRAME_HEIGHT / 12, Constant.FRAME_HEIGHT / 6); // Ngau nhienOng nuocCap nhatyCap nhat，Cap nhat[1/6,1/12]

        int type = Pipe.TYPE_HOVER_HARD;

        // Tao raCap nhatOng nuoc
        Pipe topHover = PipePool.get("MovingPipe");
        topHover.setAttribute(x, y, topHoverHeight, type, true);

        // Tao raCap nhatOng nuoc
        int bottomHoverHeight = Constant.FRAME_HEIGHT - 2 * y - topHoverHeight - VERTICAL_INTERVAL;
        Pipe bottomHover = PipePool.get("MovingPipe");
        bottomHover.setAttribute(x, y + topHoverHeight + VERTICAL_INTERVAL, bottomHoverHeight, type, true);

        pipes.add(topHover);
        pipes.add(bottomHover);

    }

    /**
     * ThemDi chuyenCap nhatOng nuoc
     *
     * @param lastPipe Cap nhatOng nuocCap nhatLayxCap nhat
     */
    private void addMovingNormalPipe(Pipe lastPipe) {
        int topHeight = GameUtil.getRandomNumber(MIN_HEIGHT, MAX_HEIGHT + 1); // Ngau nhienTao raOng nuocCap nhat
        int x = lastPipe.getX() + HORIZONTAL_INTERVAL; // Cap nhatOng nuocCap nhatxCap nhat = Cap nhatOng nuocCap nhatxCap nhat + Ong nuocCap nhat

        Pipe top = PipePool.get("MovingPipe");
        top.setAttribute(x, -Constant.TOP_PIPE_LENGTHENING, topHeight + Constant.TOP_PIPE_LENGTHENING,
                Pipe.TYPE_TOP_HARD, true);

        Pipe bottom = PipePool.get("MovingPipe");
        bottom.setAttribute(x, topHeight + VERTICAL_INTERVAL, Constant.FRAME_HEIGHT - topHeight - VERTICAL_INTERVAL,
                Pipe.TYPE_BOTTOM_HARD, true);

        pipes.add(top);
        pipes.add(bottom);
    }

    /**
     * Kiem traCap nhatChimCap nhat
     *
     * @param bird Cap nhatDoi tuong chim
     */
    public void isCollideBird(Bird bird) {
        // Cap nhatKiem tra
        if (bird.isDead()) {
            return;
        }
        // Cap nhatOng nuocCap nhat
        for (Pipe pipe : pipes) {
            // Kiem traCap nhat
            if (pipe.getPipeRect().intersects(bird.getBirdCollisionRect())) {
                bird.deadBirdFall();
                return;
            }
        }
    }

    // Dat laiCap nhat
    public void reset() {
        for (Pipe pipe : pipes) {
            PipePool.giveBack(pipe);
        }
        pipes.clear();
    }
}
