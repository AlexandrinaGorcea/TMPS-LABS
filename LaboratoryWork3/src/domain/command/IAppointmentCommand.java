package domain.command;

public interface IAppointmentCommand {
    void execute();
    void undo();
}