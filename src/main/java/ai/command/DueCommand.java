package ai.command;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import ai.TaskList;
import ai.Ui;
import ai.exception.WrongFormatAiException;


/**
 * Executes the command that prints out the tasks that are due on the user specified date.
 */
public class DueCommand extends Command {
    private LocalDate date;

    /**
     * Stores the date to be queried and found in the TaskList.
     * @param arguments the date to be parsed.
     * @throws WrongFormatAiException if date format is not in the form of {YYYY-MM-DD}.
     */
    public DueCommand(String arguments) throws WrongFormatAiException {
        try {
            this.date = LocalDate.parse(arguments);
        } catch (DateTimeParseException e) {
            throw new WrongFormatAiException("You tried to input wrong date format :(((", "due 2024-12-30");
        }
    }

    @Override
    public String execute(TaskList tasks, Ui ui) {
        String dueList = tasks.getDueTasks(date);
        if (dueList == "") {
            return "Hmphh... you have no tasks due that day... hang w me instead???";
        }
        return "Here ya go...\n\n"
                + tasks.getDueTasks(date) + "\n"
                + "You better finish the tasks... hehe :p\n";

    }

    @Override
    public boolean isExit() {
        return false;
    }
}
