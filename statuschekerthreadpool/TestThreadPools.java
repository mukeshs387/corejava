package statuschekerthreadpool;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;

public class TestThreadPools {
	public static void main(String[] args) {
		ExecutorService service = Executors.newFixedThreadPool(10);
		BlockingQueue<String> bQueue = new LinkedBlockingQueue<>();
		File file = new File("C:/Users/msi109/Dropbox/ACE PROGRAM JAVA INTERVIEW/Interview/test");
		String[] files = file.list();
		System.out.println("Total Available Files " + files.length);

		for (String fileStr : files) {
			try {
				bQueue.put(fileStr);
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		int totalFiles = bQueue.size();
		List<Future<Integer>> listFuture = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			FileProcessor processor = new FileProcessor(bQueue);
			listFuture.add(service.submit(processor));
		}

		service.shutdown();
		int totalProcessFiles = 0;
		// while (bQueue.size() > 0)
		int remaineder = 0;
		do {
			remaineder = bQueue.size();
			totalProcessFiles = totalFiles - remaineder;
			System.out.println(totalProcessFiles + " files Processed ");
			// System.out.println(bQueue.size() + " Available files to be Processed ");
			/*
			 * try { Thread.sleep(1000 * 2); } catch (InterruptedException e) { // TODO
			 * Auto-generated catch block e.printStackTrace(); }
			 */
		} while (remaineder > 0);
		System.out.println("exit total files  has been processed Now ");

	}
}
