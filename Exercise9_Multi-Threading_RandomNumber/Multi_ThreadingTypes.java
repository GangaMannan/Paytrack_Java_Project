package Practical;
//ThreadTypesDemo.java
import java.util.concurrent.*;

public class Multi_ThreadingTypes {
	//1. Extend Thread
	static class MyThread extends Thread{
		public void run() {
			System.out.println("MyThread running: " + Thread.currentThread().getName());
		}
	}
	//2. Implement Runnable
	static class MyRunnable implements Runnable{
		public void run() {
			System.out.println("MyRunnable running: " + Thread.currentThread().getName());
		}
	}
public static void main(String[] args) throws InterruptedException, ExecutionException{
	// Using Thread subclass
	Thread t1 = new MyThread();
	t1.setName("Thread-Subclass");
	t1.start();
	t1.join();
	//Using Runnable
	  Thread t2 = new Thread(new MyRunnable(), "Runnable-Thread");
      t2.start();
      t2.join();

      ExecutorService pool = Executors.newFixedThreadPool(2);

      Future<String> f = pool.submit(() ->
              "Callable result from " + Thread.currentThread().getName()
      );

      System.out.println(f.get());

      pool.shutdown();
      pool.awaitTermination(3, TimeUnit.SECONDS);

      System.out.println("Main finished.");
  }
}