import java.io.File;

import java.io.Reader;
import java.io.IOException;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.FileNotFoundException;

import java.nio.charset.Charset;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JTextArea;

/**
 * Author Nikolai.
 */
public class Worker implements Runnable {
    private File file;
    private Map<Integer, Integer> map;
    private long start;
    private long end;
    private JTextArea messages;

    public Worker(File file, long start, long end, JTextArea messages) {
        this.file = file;
        this.start = start;
        this.end = end;
        this.map = new HashMap<>();
        this.messages = messages;
    }

    @Override
    public void run() {
        buildFrequencyMap();
    }

    private void buildFrequencyMap() {
        if (messages != null) {
            messages.append("Thread" + Thread.currentThread().getName() + " started.\n");
        }

        long startTime = System.currentTimeMillis();

        long bufferLength = end - start;

        Reader bufferReader = createBufferReader(file);

        for (long i = 0; i < bufferLength; i++) {
            readByte(bufferReader);
        }

        long endTime = System.currentTimeMillis() - startTime;

        if (messages != null) {
            messages.append("Thread" + Thread.currentThread().getName() + " stopped.\n");
            messages.append("Thread" + Thread.currentThread().getName() + " execution time was (millis): " + endTime + "\n");
        }

        printFrequencyMap(map);
    }

    private void readByte(Reader bufferReader) {
        int intCharacter = 0;

        try {
            intCharacter = bufferReader.read();
        } catch (IOException e) {
            e.printStackTrace();
        }

        collectDataInMap(intCharacter);
    }

    private Reader createBufferReader(File file) {
        InputStream inputStream = null;

        try {
            inputStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Reader reader = new InputStreamReader(inputStream, Charset.defaultCharset());
        Reader buffer = new BufferedReader(reader);

        try {
            buffer.skip(start);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return buffer;
    }

    private void collectDataInMap(int intByte) {
        if (!map.containsKey(intByte)) {
            map.put(intByte, 1);
        } else {
            int n = map.get(intByte);
            map.put(intByte, ++n);
        }
    }

    private void printFrequencyMap(Map<Integer, Integer> map) {
        for (Map.Entry<Integer, Integer> characterIntegerEntry : map.entrySet()) {
            if (messages != null) {
                messages.append(characterIntegerEntry.toString() + "\n");
            } else {
                System.out.println(characterIntegerEntry.toString());
            }
        }
    }
}
