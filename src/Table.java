/**
 * Created by nikolai on 12.06.17.
 */

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

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

    private static void run(RandomAccessFile file, int workers) throws IOException, InterruptedException {
        FileChannel inChannel = file.getChannel();
        long start = 0;
        long step = inChannel.size() / workers;
        long end = step;

        threadWorkers = new Thread[workers];

        for (int i = 0; i < workers; i++) {
            MappedByteBuffer buffer = inChannel.map(FileChannel.MapMode.READ_ONLY, start, step);
            threadWorkers[i] = new Thread(new Worker(buffer, start, end));

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
