package domain.bridge.notification;

public class EmailSender implements INotificationSender {
    @Override
    public void sendNotification(String recipient, String message) {
        System.out.println("Sending email to " + recipient + ": " + message);
    }
}