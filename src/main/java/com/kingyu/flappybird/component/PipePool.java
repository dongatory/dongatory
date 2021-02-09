package com.kingyu.flappybird.component;

import java.util.ArrayList;
import java.util.List;

import com.kingyu.flappybird.util.Constant;

/**
 * Ong nuocNhom doi tuong
 * Cap nhatTaoCap nhatHuyCap nhat，Cap nhatNhom doi tuongCap nhatTaoCap nhat，Cap nhatNhom doi tuongCap nhat，Cap nhat
 * 
 * @author Kingyu
 *
 */
public class PipePool {
	private static final List<Pipe> pool = new ArrayList<>(); // Cap nhat
	private static final List<MovingPipe> movingPool = new ArrayList<>(); // Cap nhat
	public static final int MAX_PIPE_COUNT = 30; // Nhom doi tuongCap nhat，Cap nhat
	public static final int FULL_PIPE = (Constant.FRAME_WIDTH
			/ (Pipe.PIPE_HEAD_WIDTH + GameElementLayer.HORIZONTAL_INTERVAL) + 2) * 2;

	static {
		for (int i = 0; i < PipePool.FULL_PIPE; i++) {
			pool.add(new Pipe());
		}
		for (int i = 0; i < PipePool.FULL_PIPE; i++) {
			movingPool.add(new MovingPipe());
		}
	}

	/**
	 * Cap nhatNhom doi tuongCap nhatLayCap nhat
	 * 
	 * @return Cap nhat，Cap nhatKiem traCap nhatNhom doi tuongCap nhatLay
	 */
	public static Pipe get(String className) {
		if ("Pipe".equals(className)) {
			int size = pool.size();
			if (size > 0) {
				return pool.remove(size - 1); // Cap nhat
			} else {
				return new Pipe(); // Cap nhatNhom doi tuong，Cap nhat
			}
		} else {
			int size = movingPool.size();
			if (size > 0) {
				return movingPool.remove(size - 1); // Cap nhat
			} else {
				return new MovingPipe(); // Cap nhatNhom doi tuong，Cap nhat
			}
		}
	}

	/**
	 * Cap nhat
	 */
	public static void giveBack(Pipe pipe) {
		//Kiem traCap nhat
		if(pipe.getClass() == Pipe.class) {
			if (pool.size() < MAX_PIPE_COUNT) {
				pool.add(pipe);
			}
		}else {
			if (movingPool.size() < MAX_PIPE_COUNT) {
				movingPool.add((MovingPipe)pipe);
			}
		}
	}
}
