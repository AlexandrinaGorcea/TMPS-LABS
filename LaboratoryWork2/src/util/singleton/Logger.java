package util.singleton;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Logger {
    private static Logger instance;
    private List<String> logs;
    private DateTimeFormatter formatter;

    private Logger() {
        logs = new ArrayList<>();
        // Format: "Oct 28, 2024 2:30 PM"
        formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy h:mm a");
    }

    public static Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    public void log(String message) {
        String timestamp = LocalDateTime.now().format(formatter);
        logs.add(String.format("[%s] %s", timestamp, message));
    }

    public void logError(String message) {
        String timestamp = LocalDateTime.now().format(formatter);
        logs.add(String.format("[%s] ERROR: %s", timestamp, message));
    }

    public void logWarning(String message) {
        String timestamp = LocalDateTime.now().format(formatter);
        logs.add(String.format("[%s] WARNING: %s", timestamp, message));
    }

    public String getLogs() {
        return String.join("\n", logs);
    }

    public void clearLogs() {
        logs.clear();
    }
}