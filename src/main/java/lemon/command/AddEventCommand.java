package lemon.command;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import lemon.Lemon;
import lemon.exception.InvalidFormatException;
import lemon.task.Event;
import lemon.task.Task;

/**
 * Represent the {@link CommandWithInput} for adding an event
 * @author He Yiheng
 */
public class AddEventCommand extends CommandWithInput {
    /**
     * Constructor for AddEventCommand
     * @param ct stores the enum {@link CommandType}
     * @param input input String that needs to be processed before further execution
     */
    public AddEventCommand(CommandType ct, String input) {
        super(ct, input);
    }

    @Override
    public void run(Lemon lemonInstance) {
        try {
            String[] eventInput = processInput();

            LocalDate from = LocalDate.parse(eventInput[1], DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            LocalDate to = LocalDate.parse(eventInput[2], DateTimeFormatter.ofPattern("dd-MM-yyyy"));

            Task event = new Event(eventInput[0], from, to, false);
            lemonInstance.getTasks().addNewTask(event);
            lemonInstance.getUi().printAddTaskMsg(event.toString(), lemonInstance.getTasks().size());
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
            throw new InvalidFormatException(" Missing description/date of the event task to be added!");
        }

        String[] eventInput = splitInput[1].split(" /from | /to ");
        if (eventInput.length < 3) {
            throw new InvalidFormatException(" Missing/incorrect date format \n"
                    + " please enter /from , /to and format date as dd-mm-yyyy");
        }
        return eventInput;
    }
}
