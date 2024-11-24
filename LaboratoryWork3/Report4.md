# Behavioral Design Patterns

----
## Author: Gorcea Alexandrina, FAF-223

----

## Objectives
1. Study and understand the Behavioral Design Patterns.

2. As a continuation of the previous laboratory work, think about what communication between software entities might be involed in your system.

3. Implement some additional functionalities using behavioral design patterns.

## Used Design Patterns:

* Command
* Observer


## Implementation

In the following implementation, two **behavioral design patterns** are applied: **Command** and **Observer**, in order to optimize communication between different components, improve dynamic workflows, and manage task execution and notification features in the __Hospital Management System__.

__Command__ is a behavioral design pattern which turns a request into a stand-alone object that contains all information about the request. This transformation lets you pass requests as a method arguments, delay or queue a request’s execution, and support undoable operations.
The __command__ directory contains __IAppointmentCommand__, __CreateAppointmentCommand__, __RescheduleAppointmentCommand__ and __AppointmentInvoker__.

* __IAppointmentCommand__: Defines the interface for all appointment-related commands. It ensures each command has __execute__ and __undo__ functionalities, enforcing a standard behavior for all commands.

 ```java 
 package domain.command;

public interface IAppointmentCommand {
    void execute();
    void undo();
}
```
* __CreateAppointmentCommand__: Implements the creation of a new appointment. This command schedules the appointment in the hospital system and notifies all observers about the new appointment using the Observer pattern.
 ```java 
 @Override
public void execute() {
        hospitalSystem.scheduleAppointment(appointment);
        // Notify observers about the newly scheduled appointment
        appointmentSubject.notifyObservers(appointment,
        "New appointment scheduled for " + appointment.getDateTime());
        }
@Override
public void undo() {
        hospitalSystem.cancelAppointment(appointment.getAppointmentId());
        } 
   ```
1. **`execute`**:  Schedules the appointment and notifies observers.
2. **`undo`**: Cancels the scheduled appointment if needed.

* __AppointmentInvoker__: Acts as the invoker for commands. It provides the ability to execute commands and supports undo functionality to reverse the last executed command. It maintains a history of executed commands using a stack.
 ```java 
// Executes a command and stores it in the command history
public void executeCommand(IAppointmentCommand command) {
    command.execute();
    commandHistory.push(command);
}

// Undoes the last executed command
public void undoLastCommand() {
    if (!commandHistory.isEmpty()) {
        IAppointmentCommand command = commandHistory.pop();
        command.undo();
    }
}
 ```

* __RescheduleAppointmentCommand__: Handles rescheduling an existing appointment. This command updates the appointment time in the system and notifies observers of the change. The undo functionality reverts the appointment to its previous time.

1. **`execute`**:  Updates the appointment time and notifies observers of the change.
2. **`undo`**: Restores the original appointment time and notifies observers.

__Observer__ is a behavioral design pattern that lets you define a subscription mechanism to notify multiple objects about any events that happen to the object they’re observing.
The __observer__ directory contains: __AppointmentSubject__, __IAppointmentObserver__, __DoctorObserver__ and __PatientObserver__.

* __AppointmentSubject__: Acts as the subject in the Observer pattern, managing a list of observers and notifying them of changes in the state of appointments. Observers can attach or detach dynamically.
 ```java 
// Attaches an observer to the subject
public void attach(IAppointmentObserver observer) {
        observers.add(observer);
        }

// Detaches an observer from the subject
public void detach(IAppointmentObserver observer) {
        observers.remove(observer);
        }

// Notifies all observers about changes
public void notifyObservers(IAppointment appointment, String message) {
        for (IAppointmentObserver observer : observers) {
        observer.update(appointment, message);
        }
        }
 ```
This class is responsible for maintaining the observer list and broadcasting updates (e.g., appointment notifications) to all registered observers.
* __IAppointmentObserver__: Defines the interface for all observers in the system. Observers must implement the update method to respond to notifications from the subject.
 ```java 
public interface IAppointmentObserver {
    void update(IAppointment appointment, String message);
}
 ```
Provides a consistent structure for observers, ensuring each can respond to updates with the relevant appointment details and message.
* __DoctorObserver__: Represents a doctor as an observer. This observer listens for changes related to the doctor’s appointments and sends notifications (e.g., reminders) to the doctor using the NotificationFacade.
 ```java 
@Override
public void update(IAppointment appointment, String message) {
    if (appointment.getDoctorId().equals(doctor.getId())) { // Match appointment with this doctor
        String notificationMessage = String.format("Doctor Notification: %s - Appointment %s",
                message,
                appointment.getDateTime());
        
        if (doctor.getContactNumber() != null) {
            notificationFacade.sendAppointmentReminder(doctor.getContactNumber(), notificationMessage);
        }
    }
}
 ```
The __update__ method ensures that the doctor receives a notification only for relevant appointments. The message includes both the notification content and the appointment time.
* __PatientObserver__: Represents a patient as an observer. It listens for changes related to the patient’s appointments and sends reminders or notifications via the NotificationFacade.
 ```java 
@Override
public void update(IAppointment appointment, String message) {
    if (appointment.getPatientId().equals(patient.getId())) { // Match appointment with this patient
        String notificationMessage = String.format("Patient Notification: %s - Appointment %s",
                message,
                appointment.getDateTime());
        
        notificationFacade.sendAppointmentReminder(patient.getContactNumber(), notificationMessage);
    }
}
 ```

Commands like __CreateAppointmentCommand__ or __RescheduleAppointmentCommand__ notify the __AppointmentSubject__, which in turn updates observers (e.g., __DoctorObserver, PatientObserver__).
Both observers leverage the __NotificationFacade__ to send notifications, streamlining the notification process.
## Conclusions / Screenshots / Results
The Output:
 ```java 
Hospital Management System

1. Adding Staff Members:
------------------------
Added Dr. Smith (Cardiology) - ID: DOC_efb983c7
Added Dr. Johnson (Pediatrics) - ID: DOC_3a485d51
Added Nurse Williams (General) - ID: NUR_94fa0ce7

2. Adding Patients:
------------------
Added John Doe (35 years) - ID: PAT1732488112444
Insurance: BlueCross (Policy: POL123)
Added Jane Smith (28 years) - ID: PAT1732488112453
Insurance: HealthCare Plus (Policy: POL456)

3. Setting up Patient-Doctor Relationships (Observer Pattern):
--------------------------------------------------------
Dr. Smith is now observing John Doe's appointments
Dr. Johnson is now observing Jane Smith's appointments

4. Creating New Appointments (Command Pattern):
-------------------------------------------
→ New Appointment Created:
Patient: John Doe
Doctor: Dr. Smith
Date/Time: 2024-11-25 10:00
Appointment ID: APT1732488112459
[Observer Notification: Dr. Smith notified of new appointment]

→ New Appointment Created:
Patient: Jane Smith
Doctor: Dr. Johnson
Date/Time: 2024-11-26 14:30
Appointment ID: APT1732488112467
[Observer Notification: Dr. Johnson notified of new appointment]

5. Testing Notification System:
-----------------------------
Sending email to 1234567890: Appointment Details: Appointment scheduled for 2024-11-25 10:00 with Dr. Dr. Smith
✓ Regular notification sent to John Doe and Dr. Smith
Sending SMS to 0987654321: Appointment Details: URGENT: Appointment scheduled for 2024-11-26 14:30 with Dr. Dr. Johnson
✓ Urgent notification sent to Jane Smith and Dr. Johnson

6. Rescheduling Appointment (Command Pattern):
------------------------------------------
→ Original Appointment:
Patient: John Doe
Doctor: Dr. Smith
Old Date/Time: 2024-11-25 10:00
→ Appointment Rescheduled:
New Date/Time: 2024-11-27 11:00
[Observer Notification: Dr. Smith notified of appointment change]

7. Testing Undo Operation (Command Pattern):
----------------------------------------
→ Undoing last rescheduling operation
Appointment restored to original time: 2024-11-25 10:00
[Observer Notification: Dr. Smith notified of appointment restoration]

8. Generating Comprehensive Reports:
--------------------------------

Detailed Report for John Doe:
Patient Visit Report

Patient Information:
Name: John Doe
ID: PAT1732488112444
Appointment Information:
ID: APT1732488112459
Date/Time: 2024-11-25 10:00

Status: Regular


Detailed Report for Jane Smith:
Patient Visit Report

Patient Information:
Name: Jane Smith
ID: PAT1732488112453
Appointment Information:
ID: APT1732488112467
Date/Time: 2024-11-26 14:30

Status: Regular


9. System Activity Logs:
---------------------
[nov. 25, 2024 12:41 a.m.] New staff member added: DOC_efb983c7
[nov. 25, 2024 12:41 a.m.] New staff member added: DOC_3a485d51
[nov. 25, 2024 12:41 a.m.] New staff member added: NUR_94fa0ce7
[nov. 25, 2024 12:41 a.m.] Created new patient: PAT1732488112444
[nov. 25, 2024 12:41 a.m.] New patient added: PAT1732488112444
[nov. 25, 2024 12:41 a.m.] Created new patient: PAT1732488112453
[nov. 25, 2024 12:41 a.m.] New patient added: PAT1732488112453
[nov. 25, 2024 12:41 a.m.] New appointment scheduled: APT1732488112459
[nov. 25, 2024 12:41 a.m.] New appointment scheduled: APT1732488112467
[nov. 25, 2024 12:41 a.m.] Updated appointment: APT1732488112459
[nov. 25, 2024 12:41 a.m.] Updated appointment: APT1732488112459
 ```
To sum up the system's actions based on the output which I provided, here is what is happening step by step:
- John Doe, a 35-year-old patient, is added to the system with insurance details and a unique ID `PAT1732488112444`.
- Dr. Smith is set up as an observer for John Doe’s appointments, meaning Dr. Smith will be notified of any changes or updates to John Doe's appointments.
- An appointment is created for John Doe with Dr. Smith for `2024-11-25 10:00`, and Dr. Smith receives a notification about this scheduled appointment.
- An SMS reminder for John Doe’s appointment is sent to Dr. Smith, and an urgent SMS is sent to Jane Smith about her appointment.
- John Doe’s appointment with Dr. Smith is rescheduled to `2024-11-27 11:00`, and Dr. Smith receives a notification about the change.
- After rescheduling John Doe’s appointment, the system performs an undo operation, reverting the appointment to its original date of `2024-11-25 10:00`, and Dr. Smith is notified.
- The system generates a detailed report for John Doe’s appointment, showing the patient’s information and the scheduled appointment details.
- The system logs the addition of Dr. Smith and John Doe to the system, as well as the creation and update of their appointment.

In conclusion, the integration of **Command** and **Observer** design patterns into the Hospital Management System has enhanced its functionality because the **Command** Pattern effectively manages appointment scheduling, rescheduling, and undoing operations, providing a clear separation of concerns and ensuring flexibility in handling different appointment-related actions, and the **Observer** Pattern enables communication between doctors, patients, and the system, ensuring real-time notifications for appointment changes.
