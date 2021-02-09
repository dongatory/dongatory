package com.kingyu.flappybird.util;

import java.awt.Color;
import java.awt.Font;

/**
 * Lop hang so
 * 
 * @author Kingyu Cap nhat，Cap nhat
 */

public class Constant {
	// Cap nhat
	public static final int FRAME_WIDTH = 420;
	public static final int FRAME_HEIGHT = 640;

	// Tieu de tro choi
	public static final String GAME_TITLE = "Flappy Bird written by Kingyu";

	// Vi tri cua so
	public static final int FRAME_X = 600;
	public static final int FRAME_Y = 100;

	// Cap nhat
	public static final String BG_IMG_PATH = "resources/img/background.png"; // NenHinh anh

	// ChimHinh anh
	public static final String[][] BIRDS_IMG_PATH = {
			{ "resources/img/0.png", "resources/img/1.png", "resources/img/2.png", "resources/img/3.png",
					"resources/img/4.png", "resources/img/5.png", "resources/img/6.png", "resources/img/7.png" },
			{ "resources/img/up.png", "resources/img/up.png", "resources/img/up.png", "resources/img/up.png",
					"resources/img/up.png", "resources/img/up.png", "resources/img/up.png", "resources/img/up.png" },
			{ "resources/img/down_0.png", "resources/img/down_1.png", "resources/img/down_2.png",
					"resources/img/down_3.png", "resources/img/down_4.png", "resources/img/down_5.png",
					"resources/img/down_6.png", "resources/img/down_7.png" },
			{ "resources/img/dead.png", "resources/img/dead.png", "resources/img/dead.png", "resources/img/dead.png",
					"resources/img/dead.png", "resources/img/dead.png", "resources/img/dead.png",
					"resources/img/dead.png", } };

	// Cap nhatHinh anh
	public static final String[] CLOUDS_IMG_PATH = { "resources/img/cloud_0.png", "resources/img/cloud_1.png" };

	// Ong nuocHinh anh
	public static final String[] PIPE_IMG_PATH = { "resources/img/pipe.png", "resources/img/pipe_top.png",
			"resources/img/pipe_bottom.png" };

	public static final String TITLE_IMG_PATH = "resources/img/title.png";
	public static final String NOTICE_IMG_PATH = "resources/img/start.png";
	public static final String SCORE_IMG_PATH = "resources/img/score.png";
	public static final String OVER_IMG_PATH = "resources/img/over.png";
	public static final String AGAIN_IMG_PATH = "resources/img/again.png";

	public static final String SCORE_FILE_PATH = "resources/score"; // Diem soCap nhat

	// Toc do tro choi（Ong nuocCap nhatNenCap nhatDi chuyenToc do）
	public static final int GAME_SPEED = 4;

	// Cap nhatNenCap nhat
	public static final Color BG_COLOR = new Color(0x4bc4cf);

	// Tan so lam moi tro choi
	public static final int FPS = 1000 / 30;

	// Cap nhat
	public static final int TOP_BAR_HEIGHT = 20;

	// Mat datCap nhat
	public static final int GROUND_HEIGHT = 35;

	// Cap nhat
	public static final int TOP_PIPE_LENGTHENING = 100;

	public static final int CLOUD_BORN_PERCENT = 6; // Cap nhatTao raCap nhat，Cap nhat
	public static final int CLOUD_IMAGE_COUNT = 2; // Cap nhatHinh anhCap nhat
	public static final int MAX_CLOUD_COUNT = 7; // Cap nhat

	public static final Font CURRENT_SCORE_FONT = new Font("Cap nhat", Font.BOLD, 32);// Cap nhat
	public static final Font SCORE_FONT = new Font("Cap nhat", Font.BOLD, 24);// Cap nhat

}
