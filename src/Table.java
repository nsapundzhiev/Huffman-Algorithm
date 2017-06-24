import javax.swing.*;
import java.io.File;

/**
 * Created by nikolai on 12.06.17.
 */
public class Table {

    public void run(String fileName, int workers, JTextArea messages) {
        long startTime = System.currentTimeMillis();

        File file = new File(fileName);

        long start = 0;
        long step = file.length() / workers;
        long end = step;

        Thread[] threadWorkers = new Thread[workers];

        for (int i = 0; i < workers; i++) {
            threadWorkers[i] = new Thread(new Worker(file, start, end, messages));

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
            try {
                worker.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (messages != null) {
            messages.append("Threads used in current run: " + workers + "\n");
            messages.append("Total execution time for current run (millis): " +
                    Long.toString(System.currentTimeMillis() - startTime) + "\n");
        } else {
            System.out.println("Total execution time for current run (millis): " +
                    Long.toString(System.currentTimeMillis() - startTime));
        }
    }
}
