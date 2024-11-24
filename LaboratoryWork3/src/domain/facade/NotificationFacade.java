package domain.facade;

import domain.bridge.notification.EmailSender;
import domain.bridge.notification.SMSSender;
import domain.bridge.notification.INotificationSender;
import service.NotificationService;

public class NotificationFacade {
    private final NotificationService notificationService;
    private final EmailSender emailSender;
    private final SMSSender smsSender;

    public NotificationFacade() {
        this.emailSender = new EmailSender();
        this.smsSender = new SMSSender();
        this.notificationService = new NotificationService(emailSender);
    }

    public void sendAppointmentReminder(String recipient, String appointmentDetails) {
        notificationService.sendAppointmentNotification(recipient, appointmentDetails, emailSender);
    }
}
