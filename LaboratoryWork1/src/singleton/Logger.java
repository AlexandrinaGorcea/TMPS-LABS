package singleton;

public class Logger {
    private static Logger instance;
    private StringBuilder logs;

    private Logger() {
        logs = new StringBuilder();
    }

    public static synchronized Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    public void log(String message) {
        logs.append(String.format("[%s] %s\n",
                java.time.LocalDateTime.now(), message));
    }

    public String getLogs() {
        return logs.toString();
    }
}