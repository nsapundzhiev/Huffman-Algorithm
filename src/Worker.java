import java.nio.MappedByteBuffer;
import java.util.HashMap;
import java.util.Map;

public class Worker implements Runnable {
    private MappedByteBuffer buffer;
    private Map<Character, Integer> map;
    private long start;
    private long end;

    public Worker(MappedByteBuffer buffer, long start, long end) {
        this.buffer = buffer;
        this.start = start;
        this.end = end;
        this.map = new HashMap<>();
    }

    @Override
    public void run() {
        for (long i = start; i < end; i++) {
            byte b = buffer.get();
            char ch = (char) b;

            if (!map.containsKey(ch)) {
                map.put(ch, 1);
            } else {
                int n = map.get(ch);
                map.put(ch, ++n);
            }
        }

//        System.out.println(map.toString());
    }
}
