package statuschekerthreadpool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;

public class FileProcessor implements Callable<Integer> {
	BlockingQueue<String> queue;

	public FileProcessor(BlockingQueue<String> bQueue) {
		this.queue = bQueue;
	}

	@Override
	public Integer call() throws Exception {
		// TODO Auto-generated method stub

		while (true) {
			String file = queue.take();
			Thread.sleep(500);
			//System.out.println(file +" processed ");
			// File Processing Logic.
			
		}
	}

}
