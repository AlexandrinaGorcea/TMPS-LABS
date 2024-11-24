package domain.bridge.message;

import domain.bridge.notification.INotificationSender;

public abstract class AbstractMessage {
    protected INotificationSender sender;

    public AbstractMessage(INotificationSender sender) {
        this.sender = sender;
    }

    public abstract void send(String recipient);
}