package com.wkcto.lock.mine;

/**
 * @author ：zhaoy
 * @date ：Created in 2021/2/22 20:10
 * @description：
 * @modified By：
 * @version: 1.0
 */
public class ThreadStopTest {
	public static void main(String[] args) throws InterruptedException {
		StopThread thread = new StopThread();
		thread.start();
		// 休眠1秒，确保线程进入运行
		Thread.sleep(1000);
		// 暂停线程
//		thread.stop();
      thread.interrupt();
		// 确保线程已经销毁
		while (thread.isAlive()) { }
		// 输出结果
		thread.print();
	}

	private static class StopThread extends Thread {

		private int x = 0; private int y = 0;

		@Override
		public void run() {
			// 这是一个同步原子操作
			synchronized (this) {
				++x;
				try {
					// 休眠3秒,模拟耗时操作
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				++y;
			}
		}

		public void print() {
			System.out.println("x=" + x + " y=" + y);
		}
	}
}
