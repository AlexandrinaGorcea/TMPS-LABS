package service;

import domain.bridge.notification.INotificationSender;
import domain.bridge.message.AppointmentMessage;
import util.singleton.Logger;

public class NotificationService {
    private final Logger logger;
    private final INotificationSender defaultSender;

    public NotificationService(INotificationSender defaultSender) {
        this.logger = Logger.getInstance();
        this.defaultSender = defaultSender;
    }

    public void sendAppointmentNotification(String recipient, String appointmentDetails, INotificationSender sender) {
        INotificationSender actualSender = sender != null ? sender : defaultSender;
        new AppointmentMessage(actualSender, appointmentDetails).send(recipient);
        logger.log("Sent appointment notification to " + recipient);
    }
}