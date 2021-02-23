package com.wkcto.lock.mine;

import static java.lang.Thread.currentThread;

/**
 * @author ：zhaoy
 * @date ：Created in 2021/2/22 20:23
 * @description：
 * @modified By：
 * @version: 1.0
 */
public class ThreadTest {

	public static void main(String[] args) {
		Thread t = new Thread(new Task("1"));
		System.out.println("1");
		t.start();
		System.out.println("2");
//		t.interrupt();
		System.out.println("3");
	}

	static class Task implements Runnable {
		String name;

		public Task(String name) {
			this.name = name;
		}

		@Override
		public void run() {
			System.out.println("4");
			System.out.println("first 1:" + currentThread().isInterrupted());
			System.out.println("first 1:" + Thread.activeCount());
			System.out.println("first :" + Thread.interrupted());
			System.out.println("5");
			System.out.println("second 2:" +currentThread().isInterrupted() );
			System.out.println("second:" + Thread.interrupted());
			System.out.println("second:" + Thread.interrupted());
			System.out.println("second:" + Thread.interrupted());
			System.out.println("6");
			System.out.println("task " + name + " is over");
			System.out.println("7");
		}
	}
}
