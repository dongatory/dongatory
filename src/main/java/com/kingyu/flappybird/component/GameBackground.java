package com.kingyu.flappybird.component;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.kingyu.flappybird.util.Constant;
import com.kingyu.flappybird.util.GameUtil;

/**
 * Cap nhatNenCap nhat，Cap nhatNenCap nhatVe
 * 
 * @author Kingyu
 *
 */
public class GameBackground {

	private static final BufferedImage BackgroundImg;// NenHinh anh

	private final int speed; // NenCap nhatToc do
	private int layerX; // NenCap nhat

	public static final int GROUND_HEIGHT;

	static {
		BackgroundImg = GameUtil.loadBufferedImage(Constant.BG_IMG_PATH);
		assert BackgroundImg != null;
		GROUND_HEIGHT = BackgroundImg.getHeight() / 2;
	}

	// Khoi tao trong constructor
	public GameBackground() {
		this.speed = Constant.GAME_SPEED;
		this.layerX = 0;
	}

	// VeCap nhat
	public void draw(Graphics g, Bird bird) {
		// VeNenCap nhat
		g.setColor(Constant.BG_COLOR);
		g.fillRect(0, 0, Constant.FRAME_WIDTH, Constant.FRAME_HEIGHT);

		// Cap nhatNenHinh anhCap nhat
		int imgWidth = BackgroundImg.getWidth();
		int imgHeight = BackgroundImg.getHeight();

		int count = Constant.FRAME_WIDTH / imgWidth + 2; // Cap nhatChieu rong cua soCap nhatHinh anhCap nhatVeCap nhat
		for (int i = 0; i < count; i++) {
			g.drawImage(BackgroundImg, imgWidth * i - layerX, Constant.FRAME_HEIGHT - imgHeight, null);
		}
		
		if(bird.isDead()) {  //ChimCap nhatVe
			return;
		}
		movement();
	}

	// NenCap nhat
	private void movement() {
		layerX += speed;
		if (layerX > BackgroundImg.getWidth())
			layerX = 0;
	}
}
