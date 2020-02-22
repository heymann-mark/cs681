package edu.umb.cs.cs681.hw07;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.locks.ReentrantLock;

public class File {
	boolean changed = false;
	private ReentrantLock changedLock = new ReentrantLock();

	public void change() {
		changedLock.lock();
		try {
			changed = true;
		} finally {
			changedLock.unlock();
		}
	}

	public void save() {
		changedLock.lock();
		try {

			if (!changed) {
				System.out.println("not changed");
				return;
			}
  	
  			Date dnow = new Date();
  			SimpleDateFormat ft = 
  					new SimpleDateFormat("E yyyy.MM.dd 'at' hh:mm:ss a zzz");
  			System.out.println("Changed and saved: " + ft.format(dnow));
			changed = false;
		} finally {
			changedLock.unlock();
		}
	}
	public static void main(String[] args) {
		Editor editor = new Editor();
		AutoSaver autosaver = new AutoSaver();
		Thread editorThread = new Thread(editor);
		Thread autoSaverThread = new Thread(autosaver);
		
		autoSaverThread.start();
		editorThread.start();
		try {
			Thread.sleep(20000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		editor.setDone();
		autosaver.setDone();
		try {
			editorThread.join();
			autoSaverThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
}}
