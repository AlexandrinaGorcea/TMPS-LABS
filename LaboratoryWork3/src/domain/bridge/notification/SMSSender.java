package domain.bridge.notification;

public class SMSSender implements INotificationSender {
    @Override
    public void sendNotification(String recipient, String message) {
        System.out.println("Sending SMS to " + recipient + ": " + message);
    }
}