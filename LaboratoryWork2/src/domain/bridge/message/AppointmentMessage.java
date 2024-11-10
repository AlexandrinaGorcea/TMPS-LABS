package domain.bridge.message;

import domain.bridge.notification.INotificationSender;

public class AppointmentMessage extends AbstractMessage {
    private String appointmentDetails;

    public AppointmentMessage(INotificationSender sender, String appointmentDetails) {
        super(sender);
        this.appointmentDetails = appointmentDetails;
    }

    @Override
    public void send(String recipient) {
        String message = "Appointment Details: " + appointmentDetails;
        sender.sendNotification(recipient, message);
    }
}