import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by nikolai on 24.06.17.
 */
public class ReadAllFiles {

    public static void main(String[] args) {
        Map<Integer, Integer> map = new HashMap<>();

        String fileName = "/home/nikolai/Desktop/ttt.txt";

        long startTime = System.currentTimeMillis();
        try {
            File file = new File(fileName);

            FileInputStream stream = new FileInputStream(file);

            int intByte;
            while ((intByte = stream.read()) != -1) {
                if (!map.containsKey(intByte)) {
                    map.put(intByte, 1);
                } else {
                    int n = map.get(intByte);
                    map.put(intByte, ++n);
                }
            }

            stream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        long endTime = System.currentTimeMillis() - startTime;

        for (Map.Entry<Integer, Integer> characterIntegerEntry : map.entrySet()) {
            System.out.println(characterIntegerEntry.toString());
        }

        System.out.println("Reading time : " + endTime);
    }
}
