package com.kingyu.flappybird.component;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import com.kingyu.flappybird.util.Constant;
import com.kingyu.flappybird.util.GameUtil;

/**
 * Canh truocCap nhat，Cap nhatTao raCap nhatVeCap nhat
 *
 * @author Kingyu
 */
public class GameForeground {
    private final List<Cloud> clouds; // Cap nhat
    private final BufferedImage[] cloudImages; // Hinh anhCap nhat
    private long time; // Cap nhat
    public static final int CLOUD_INTERVAL = 100; //Cap nhat

    public GameForeground() {
        clouds = new ArrayList<>(); //Cap nhat
        // Cap nhatHinh anhCap nhat
        cloudImages = new BufferedImage[Constant.CLOUD_IMAGE_COUNT];
        for (int i = 0; i < Constant.CLOUD_IMAGE_COUNT; i++) {
            cloudImages[i] = GameUtil.loadBufferedImage(Constant.CLOUDS_IMG_PATH[i]);
        }
        time = System.currentTimeMillis(); // LayCap nhat，Cap nhat
    }

    // VeCap nhat
    public void draw(Graphics g, Bird bird) {
        cloudBornLogic();
        for (Cloud cloud : clouds) {
            cloud.draw(g, bird);
        }
    }

    // Cap nhat
    private void cloudBornLogic() {
        // 100msCap nhat
        if (System.currentTimeMillis() - time > CLOUD_INTERVAL) {
            time = System.currentTimeMillis(); // Dat laitime
            // Cap nhat，Cap nhatNgau nhienThemCap nhat
            if (clouds.size() < Constant.MAX_CLOUD_COUNT) {
                try {
                    if (GameUtil.isInProbability(Constant.CLOUD_BORN_PERCENT, 100)) { // Cap nhatThemCap nhat
                        int index = GameUtil.getRandomNumber(0, Constant.CLOUD_IMAGE_COUNT); // Ngau nhienCap nhatHinh anh

                        // Cap nhat
                        int x = Constant.FRAME_WIDTH; // Cap nhatBat dauCap nhat
                        // yCap nhatNgau nhienCap nhat1/3Cap nhat
                        int y = GameUtil.getRandomNumber(Constant.TOP_BAR_HEIGHT, Constant.FRAME_HEIGHT / 3);

                        //Cap nhatThemCap nhat
                        Cloud cloud = new Cloud(cloudImages[index], x, y);
                        clouds.add(cloud);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } // ThemCap nhat

            // Cap nhat
            for (int i = 0; i < clouds.size(); i++) {
                // Cap nhat
                Cloud tempCloud = clouds.get(i);
                if (tempCloud.isOutFrame()) {
                    clouds.remove(i);
                    i--;
                }
            }
        }
    }
}
