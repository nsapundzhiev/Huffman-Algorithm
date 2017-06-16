/**
 * Created by nikolai on 12.06.17.
 */

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;

public class Table {

    private static Thread[] threadWorkers;

    public static void main(String[] args) throws InterruptedException, FileNotFoundException {
        for (int i = 0; i < 2; i++) {
            try {
                runMain();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void runMain() throws IOException, InterruptedException {
        RandomAccessFile file = new RandomAccessFile(
                "/home/nikolai/Desktop/test.txt", "r");

        long start = System.currentTimeMillis();

        run(file, 2);

        long end = System.currentTimeMillis() - start;

        System.out.println("Program time : " + end);

        file.close();
    }

    private static void run(File file, int workers) throws IOException, InterruptedException {
        long start = 0;
        long step = file.length() / workers;
        long end = step;

        threadWorkers = new Thread[workers];

        for (int i = 0; i < workers; i++) {
            threadWorkers[i] = new Thread(new Worker(file, start, end));

            if (i == workers - 1) {
                break;
            }

            start += step;

            if (i == workers - 2) {
                end = inChannel.size();
            } else {
                end += step;
            }
        }

        for (Thread worker : threadWorkers) {
            worker.start();
        }

        for (Thread worker : threadWorkers) {
            worker.join();
        }
    }
}
