package domain.command;

import java.util.Stack;


public class AppointmentInvoker {
    private final Stack<IAppointmentCommand> commandHistory = new Stack<>();

    public void executeCommand(IAppointmentCommand command) {
        command.execute();
        commandHistory.push(command);
    }

    public void undoLastCommand() {
        if (!commandHistory.isEmpty()) {
            IAppointmentCommand command = commandHistory.pop();
            command.undo();
        }
    }
}
