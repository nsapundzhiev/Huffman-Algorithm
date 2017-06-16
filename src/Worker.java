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
        
        for (long i = 0; i < bufferLenght; i++) {
            int intCharacter = 0;
            
            try {
                intCharacter = buffer.read();
            } catch (IOException e) {
                e.printStackTrace();
            }
            
            char character = (char) intCharacter;
            collectDataInMap(character);
        }
        
        System.out.println(map.toString());
    }    
    
    private void collectDataInMap(char character) {        
        if (!map.containsKey(character)) {
            map.put(character, 1);
        } else {
            int n = map.get(character);
            map.put(character, ++n);
        }
    }
}
