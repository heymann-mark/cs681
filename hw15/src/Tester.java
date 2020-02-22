package edu.umb.cs.cs681.hw15;

public class Tester {
	private static void sleep(int sleepTime) {
		try {
			Thread.sleep(sleepTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws InterruptedException {
		AdmissionControl admission = new AdmissionControl();
		Thread entranceThread1 = new Thread(admission.new EntranceHandler());
		Thread entranceThread2 = new Thread(admission.new EntranceHandler());
		Thread entranceThread3 = new Thread(admission.new EntranceHandler());
		entranceThread1.start();
		entranceThread2.start();
		entranceThread3.start();
		Thread monitorThread = new Thread(admission.new MonitorHandler());
		monitorThread.start();
		sleep(200);
		Thread exitThread1 = new Thread(admission.new ExitHandler());
		Thread exitThread2 = new Thread(admission.new ExitHandler());
		exitThread1.start();
		exitThread2.start();
		sleep(200);
		System.out.println("start interrupts");
		entranceThread1.interrupt();
		entranceThread2.interrupt();
		entranceThread3.interrupt();
		monitorThread.interrupt();
		exitThread1.interrupt();
		exitThread2.interrupt();
		admission.setDone();
		System.out.println("set done");
		entranceThread1.join();
		entranceThread2.join();
		entranceThread3.join();
		monitorThread.join();
		exitThread1.join();
		exitThread2.join();
		admission.setDone();
		Thread monitorThread2 = new Thread(admission.new MonitorHandler());
		monitorThread2.start();
		sleep(2000);
		admission.setDone();

	}

}
