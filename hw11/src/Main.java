package edu.umb.cs.cs681.hw11;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
	static RequestHandler request;
	static Thread t1;
	static Thread t2;
	static Thread t3;
	static Thread t4;
	static Thread t5;
	static Thread t6;
	static Thread t7;
	static Thread t8;
	static Thread t9;
	static Thread t10;
	static Thread t11;

	public static void main(String[] args) throws InterruptedException {
		Path path1 = Paths.get("src/a.html");
		AccessCounter access = AccessCounter.getInstance();
		request = new RequestHandler(access, path1);

		t1 = new Thread(request);
		t2 = new Thread(request);
		t3 = new Thread(request);
		t4 = new Thread(request);
		t5 = new Thread(request);
		t6 = new Thread(request);
		t7 = new Thread(request);
		t8 = new Thread(request);
		t9 = new Thread(request);
		t10 = new Thread(request);
		t11 = new Thread(request);

		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
		t6.start();
		t7.start();
		t8.start();
		t9.start();
		t10.start();
		t11.start();

		request.setDone();// step 1

		t1.interrupt();// step 2
		t2.interrupt();
		t3.interrupt();
		t4.interrupt();
		t5.interrupt();
		t6.interrupt();
		t7.interrupt();
		t8.interrupt();
		t9.interrupt();
		t10.interrupt();
		t11.interrupt();

	}
}
