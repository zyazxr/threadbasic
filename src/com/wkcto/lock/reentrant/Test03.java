package com.wkcto.lock.reentrant;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用Lock锁同步不同方法中的同步代码块
 */
public class Test03 {
	static Lock lock = new ReentrantLock();         //定义锁对象

	public static void sm1() {
		//经常在try代码块中获得Lock锁, 在finally子句中释放锁
		lock.lock();        //获得锁
		try {
			System.out.println(Thread.currentThread().getName() + "-- method 1 -- " + System.currentTimeMillis());
			Thread.sleep(new Random().nextInt(1000));
			System.out.println(Thread.currentThread().getName() + "-- method 1 -- " + System.currentTimeMillis());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();          //释放锁
		}
	}

	public static void sm2() {
		lock.lock();        //获得锁
		try {
			System.out.println(Thread.currentThread().getName() + "-- method 22 -- " + System.currentTimeMillis());
			Thread.sleep(new Random().nextInt(1000));
			System.out.println(Thread.currentThread().getName() + "-- method 22 -- " + System.currentTimeMillis());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();          //释放锁
		}
	}

	public static void main(String[] args) {
		Runnable r1 = () -> sm1();
		Runnable r2 = () -> sm2();

		new Thread(r1).start();
		new Thread(r1).start();
		new Thread(r1).start();
		new Thread(r2).start();
		new Thread(r2).start();
		new Thread(r2).start();
	}
}
