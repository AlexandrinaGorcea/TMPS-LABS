package util.singleton;

import java.util.ArrayList;
import java.util.List;

public class Logger {
    private static Logger instance;
    private List<String> logs;

    private Logger() {
        logs = new ArrayList<>();
    }

    public static synchronized Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    public void log(String message) {
        logs.add(String.format("[%s] %s", System.currentTimeMillis(), message));
    }

    public String getLogs() {
        return String.join("\n", logs);
    }
}