package domain.decorator.notification;

import domain.bridge.notification.INotificationSender;

public abstract class NotificationDecorator implements INotificationSender {
    protected INotificationSender wrapped;

    public NotificationDecorator(INotificationSender sender) {
        this.wrapped = sender;
    }

    @Override
    public void sendNotification(String recipient, String message) {
        wrapped.sendNotification(recipient, message);
    }
}