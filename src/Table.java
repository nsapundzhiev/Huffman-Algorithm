import java.io.IOException;
import java.io.File;

/**
 * Created by nikolai on 12.06.17.
 */
public class Table {
    private static Thread[] threadWorkers;
    private static File file = new File(
            "/home/nikolai/Desktop/ttt.txt");

    public static void main(String[] args) throws InterruptedException, IOException {
        long start = System.currentTimeMillis();

        run(file, 1);

        long end = System.currentTimeMillis() - start;

        System.out.println("Program time : " + end);
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
                end = file.length();
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
