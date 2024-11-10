package domain.bridge.notification;

public interface INotificationSender {
    void sendNotification(String recipient, String message);
}