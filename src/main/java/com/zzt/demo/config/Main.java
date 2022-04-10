package com.zzt.demo.config;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Learn Java from https://www.liaoxuefeng.com/
 * 
 * @author liaoxuefeng
 */
public class Main {
	public static void main(String[] args) throws Exception {
		ExecutorService es = Executors.newFixedThreadPool(3);
		String[] users = new String[] { "Bob", "Alice", "Tim", "Mike", "Lily", "Jack", "Bush" };
		for (String user : users) {
			es.submit(new Task(user));
		}
		//当使用awaitTermination时，主线程会处于一种等待的状态，等待线程池中所有的线程都运行完毕后才继续运行。
		/**
		 * 第一个参数指定的是时间，第二个参数指定的是时间单位(当前是秒)。返回值类型为boolean型。

		 1️⃣如果等待的时间超过指定的时间，但是线程池中的线程运行完毕，那么awaitTermination()返回true。

		 2️⃣如果等待的时间超过指定的时间，但是线程池中的线程未运行完毕，那么awaitTermination()返回false。

		 3️⃣如果等待时间没有超过指定时间，等待！
		 */

		boolean b = es.awaitTermination(24, TimeUnit.SECONDS);
		//shutdown方法：平滑的关闭ExecutorService，当此方法被调用时，ExecutorService停止接收新的任务并且等待已经提交的任务（包含提交正在执行和提交未执行）执行完成。
		// 当所有提交任务执行完毕，线程池即被关闭。
		System.out.println(b);
		es.shutdown();
	}
}

class UserContext implements AutoCloseable {
	private static final ThreadLocal<String> userThreadLocal = new ThreadLocal<>();

	public UserContext(String name) {
		userThreadLocal.set(name);
		System.out.printf("[%s] init user %s...\n", Thread.currentThread().getName(), UserContext.getCurrentUser());
	}

	public static String getCurrentUser() {
		return userThreadLocal.get();
	}

	@Override
	public void close() {
		System.out.printf("[%s] cleanup for user %s...\n", Thread.currentThread().getName(),
				UserContext.getCurrentUser());
		userThreadLocal.remove();
	}
}

class Task implements Runnable {

	final String username;

	public Task(String username) {
		this.username = username;
	}

	@Override
	public void run() {

		try (UserContext userContext = new UserContext(this.username)) {
			new Task1().process();
			new Task2().process();
			new Task3().process();
		}
	}
}

class Task1 {
	public void process() {
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
		}
		System.out.printf("[%s] check user %s...\n", Thread.currentThread().getName(), UserContext.getCurrentUser());
	}
}

class Task2 {
	public void process() {
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
		}
		System.out.printf("[%s] %s registered ok.\n", Thread.currentThread().getName(), UserContext.getCurrentUser());
	}
}

class Task3 {
	public void process() {
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
		}
		System.out.printf("[%s] work of %s has done.\n", Thread.currentThread().getName(),
				UserContext.getCurrentUser());
	}
}
