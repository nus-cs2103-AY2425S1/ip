package lemon.command;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import lemon.Lemon;
import lemon.exception.InvalidFormatException;
import lemon.task.Deadline;
import lemon.task.Task;

/**
 * Represent the {@link CommandWithInput} for adding a deadline
 * @author He Yiheng
 */
public class AddDeadlineCommand extends CommandWithInput {

    /**
     * Constructor for AddDeadlineCommand
     * @param ct stores the enum {@link CommandType}
     * @param input input String that needs to be processed before further execution
     */
    public AddDeadlineCommand(CommandType ct, String input) {
        super(ct, input);
    }

    @Override
    public void run(Lemon lemonInstance) {
        try {
            String[] deadlineInput = processInput();

            LocalDate by = LocalDate.parse(deadlineInput[1], DateTimeFormatter.ofPattern("dd-MM-yyyy"));

            Task deadline = new Deadline(deadlineInput[0], by, false);
            lemonInstance.getTasks().addNewTask(deadline);
            lemonInstance.getUi().printAddTaskMsg(deadline.toString(), lemonInstance.getTasks().size());
        } catch (InvalidFormatException e) {
            lemonInstance.getUi().printException(e);
        } catch (DateTimeParseException e) {
            lemonInstance.getUi().printException(" Incorrect date format, make sure the format is dd-mm-yyyy");
        }
    }

    @Override
    public String[] processInput() throws InvalidFormatException {
        String[] splitInput = input.split(" ", 2);
        if (splitInput.length < 2) {
            throw new InvalidFormatException(" Missing description/date of the deadline task to be added!");
        }

        String[] deadlineInput = splitInput[1].split(" /by ", 2);
        if (deadlineInput.length < 2) {
            throw new InvalidFormatException(" Missing/incorrect date format \n"
                    + " please enter /by and format date as dd-mm-yyyy");
        }
        return deadlineInput;
    }
}
