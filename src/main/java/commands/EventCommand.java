package commands;

import exceptions.BrockException;
import storage.Storage;
import task.Event;
import task.Task;
import task.TaskList;
import utility.CommandUtility;

/**
 * Represents an event command entered by the user.
 */
public class EventCommand extends Command {
    /**
     * Stores the command string associated with event command.
     *
     * @param command Command string.
     */
    public EventCommand(String command) {
        super(command);
    }

    /**
     * Creates an {@code Event} object encapsulating details about the event task.
     *
     * @return {@code Event} object.
     * @throws BrockException If event missing description, start date or end date.
     *      Or, if start and end dates are invalid.
     */
    private Task createEvent() throws BrockException {
        String command = super.getCommand();
        String[] commandWords = command.split(" ");
        int commandLength = commandWords.length;

        StringBuilder description = new StringBuilder();
        for (int i = 1; i < commandLength; i++) {
            if (commandWords[i].equalsIgnoreCase("/from")) {
                break;
            }
            description.append(commandWords[i])
                    .append(" ");
        }
        description.deleteCharAt(description.length() - 1);

        StringBuilder startDateTime = new StringBuilder();
        StringBuilder endDateTime = new StringBuilder();
        boolean isSeeingStartDateTime = false;
        boolean isSeeingEndDateTime = false;
        int startDateTimeWords = 0;
        int endDateTimeWords = 0;
        for (String word : commandWords) {
            if (word.equalsIgnoreCase("/from")) {
                isSeeingStartDateTime = true;
                continue;
            }
            if (word.equalsIgnoreCase("/to")) {
                isSeeingStartDateTime = false;
                isSeeingEndDateTime = true;
                continue;
            }
            if (isSeeingStartDateTime) {
                startDateTimeWords += 1;
                startDateTime.append(word)
                        .append(" ");
            }
            if (isSeeingEndDateTime) {
                endDateTimeWords += 1;
                endDateTime.append(word)
                        .append(" ");
            }
        }

        if (description.isEmpty()) {
            throw new BrockException("Description is missing!");
        }
        if (startDateTime.isEmpty()) {
            throw new BrockException("Missing start date! Remember it is specified after /from!");
        }
        if (endDateTime.isEmpty()) {
            throw new BrockException("Missing end date! Remember it is specified after /to!");
        }
        if (startDateTimeWords != endDateTimeWords) {
            throw new BrockException("Both start and end dates must either include or exclude a time!");
        }

        String[] startDateTimeValues = CommandUtility.validateDateTime(startDateTime.toString(),
                startDateTimeWords, CommandUtility.Context.START);
        String[] endDateTimeValues = CommandUtility.validateDateTime(endDateTime.toString(),
                endDateTimeWords, CommandUtility.Context.END);
        if (startDateTimeWords == 1) {
            return new Event(description.toString(),
                    startDateTimeValues[0],
                    endDateTimeValues[0]);
        } else {
            return new Event(description.toString(),
                    startDateTimeValues[0],
                    startDateTimeValues[1],
                    endDateTimeValues[0],
                    endDateTimeValues[1]);
        }
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * Chatbot checks if event command is valid.
     * If so, it creates a {@code Event} object.
     * Adds it to {@code tasks}, writes it to save file.
     * Returns a response indicating it has added the event task.
     * </p>
     *
     * @throws BrockException If event command is invalid
     */
    @Override
    public String execute(Storage storage, TaskList tasks) throws BrockException {
        Task eventTask = this.createEvent();
        tasks.addToList(eventTask);

        // Update the save file
        storage.writeToFile(tasks.numTasks() + ". "
                + tasks.getTaskDetails(eventTask) + '\n',
                true);

        return "Got it. I've added this task:\n"
                + "  " + tasks.getTaskDetails(eventTask) + '\n'
                + tasks.getTasksSummary();
    }
}
