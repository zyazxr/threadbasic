package com.wkcto.lock.threadpool;

/**
 * @author ：zhaoy
 * @date ：Created in 2021/2/22 16:34
 * @description：
 * @modified By：
 * @version: 1.0
 */
import java.io.Serializable;
import java.util.concurrent.*;

public class ThreadPoolExecutorTest {
	private static int produceTaskSleepTime = 1000;     // 一秒add一个任务
	private static int consumeTaskSleepTime = 60000;   //每个任务停留10秒这样结果更明显清楚
	private static BlockingQueue<Runnable> queue = new ArrayBlockingQueue<Runnable>(2);  //缓存队列数
	private static int  produceTaskMaxNumber = 51;      //总线程数

	private static int corePoolSize = 2;                //线程池大小
	private static int maximumPoolSize = 4;             //
	private static int keepAliveTime = 5;

	public static void main(String[] args) {
		ThreadPoolExecutor threadPoolExcutor = new ThreadPoolExecutor(corePoolSize,
				maximumPoolSize,
				keepAliveTime,
				TimeUnit.SECONDS,
				queue,
				new ThreadPoolExecutor.DiscardPolicy()); //调整策略

		for(int i=1; i< produceTaskMaxNumber;i++){
			try{
				String  work = "Task@ " + i;
				System.out.println("put : " + work);
				threadPoolExcutor.execute(new ThreadPoolTask(work));

				System.out.println("BlockQueue Size is " + queue.size());    //打印出缓存队列中线程数
				//等待一段时间方便看清楚线程处理顺序
				Thread.sleep(produceTaskSleepTime);
			}catch (Exception e){
				e.printStackTrace();
			}
		}

	}

	public static class ThreadPoolTask implements  Runnable, Serializable{
		private static final long serialVersionUID = 0;
		private  Object threadPoolTaskData;

		ThreadPoolTask(Object work){
			this.threadPoolTaskData = work;
		}

		@Override
		public void run() {
			System.out.println("start............" + threadPoolTaskData);  //标记任务开始
			try {
				Thread.sleep(consumeTaskSleepTime);
			}catch (Exception e){
				e.printStackTrace();
			}
			System.out.println("end.............." + threadPoolTaskData);   //标记任务结束
			threadPoolTaskData = null;
		}

		public  Object getTask(){
			return this.threadPoolTaskData;
		}
	}
}