package statuschekerthreadpool;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class TestNew {
	public static String[] copy(final String[] arr, int startPoint, int endPoint) {
		String copy[] = new String[endPoint - startPoint];
		System.out.println(copy.length + "  array Created startPoint " + startPoint + " endPoint " + endPoint);
		int copiedCounter = startPoint;
		for (int i = 0; i < copy.length; i++) {
			copy[i] = arr[copiedCounter];
			copiedCounter = copiedCounter + 1;
		}
		System.out.println("copiedCounter=" + copiedCounter);
		return copy;
	}

	public static List<String[]> partArray(String[] arr, int partNo) {
		// String[][] string = new String[arr.length][arr.length];
		List<String[]> list = new ArrayList<>();
		int partitionSize = arr.length / partNo;
		int size = partitionSize;
		int i = 0;
		while (i != arr.length) {
			String[] files = copy(arr, i, size);
			System.out.println(files.length + " copied ");
			list.add(files);
			i = size;
			size = size + partitionSize;
		}

		return list;

	}

	public static void main(String[] args) {
		AtomicInteger atomicInt = new AtomicInteger(0);

		File file = new File("C:/Users/msi109/Dropbox/ACE PROGRAM JAVA INTERVIEW/Interview/test");
		String[] files = file.list();
		
		List<String> list = new ArrayList<>();
		System.out.println("Total Available Files " + files.length);
		
		List<String[]> listOfFiles = partArray(files, 10);
		System.out.println("Total partition files " + listOfFiles.size());
		
		ExecutorService service = Executors.newFixedThreadPool(10);
		
		
		for (String[] arr : listOfFiles) 
		{
			
			FileProcessNew task = new FileProcessNew(atomicInt, arr);
			service.submit(task);
			System.out.println("task submitted");
			
		}

		while (!service.isTerminated()) {
			System.out.println(atomicInt.get() + " processed ");
			try {
				Thread.sleep(1000 * 1);
			}
			catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
