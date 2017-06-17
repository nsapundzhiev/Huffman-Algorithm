import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by nikolai on 12.06.17.
 */
public class Worker implements Runnable {
    private File file;
    private Map<Character, Integer> map;
    private long start;
    private long end;

    public Worker(File file, long start, long end) {
        this.file = file;
        this.start = start;
        this.end = end;
        this.map = new HashMap<>();
    }

    @Override
    public void run() {
        long bufferLength = end - start;

        Reader bufferReader = createBufferReader(file);

        for (long i = 0; i < bufferLength; i++) {
            readCharacter(bufferReader);
        }

        printFrequencyMap(map);
    }

    private void readCharacter(Reader bufferReader) {
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

    private void collectDataInMap(int intCharacter) {
        char character = (char) intCharacter;

        if (!map.containsKey(character)) {
            map.put(character, 1);
        } else {
            int n = map.get(character);
            map.put(character, ++n);
        }
    }

    private void printFrequencyMap(Map<Character, Integer> map) {
        for (Map.Entry<Character, Integer> characterIntegerEntry : map.entrySet()) {
            System.out.print(characterIntegerEntry.toString() + "  ");
        }
    }
}
