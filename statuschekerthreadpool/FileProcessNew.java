package statuschekerthreadpool;

import java.io.File;
import java.util.concurrent.atomic.AtomicInteger;

public class FileProcessNew implements Runnable {
	AtomicInteger atomicInt;

	String[] files;

	public FileProcessNew(AtomicInteger integer, String[] atrFiles) {
		// TODO Auto-generated constructor stub
		this.atomicInt = integer;
		this.files = atrFiles;
	}

	public void run() {

		for (String file : files) {
			atomicInt.incrementAndGet();

			try {
				Thread.sleep(1000 * 4);
			}
			catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}
}
