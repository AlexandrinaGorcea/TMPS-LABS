package util.singleton;

public class IdGenerator {
    private static IdGenerator instance;
    private long counter = 0;

    private IdGenerator() {}

    public static synchronized IdGenerator getInstance() {
        if (instance == null) {
            instance = new IdGenerator();
        }
        return instance;
    }

    public synchronized String generateId(String prefix) {
        return prefix + (++counter);
    }
}
