package domain.decorator.notification;

import domain.bridge.notification.INotificationSender;

public class UrgentNotificationDecorator extends NotificationDecorator {
    public UrgentNotificationDecorator(INotificationSender sender) {
        super(sender);
    }

    @Override
    public void sendNotification(String recipient, String message) {
        String urgentMessage = "URGENT: " + message;
        super.sendNotification(recipient, urgentMessage);
    }
}