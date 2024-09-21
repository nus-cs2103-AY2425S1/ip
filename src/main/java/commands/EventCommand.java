package commands;

import exceptions.BrockException;
import storage.task.TaskStorage;
import storage.temp.TempStorage;
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
     * Gets the command description.
     *
     * @param commandWords Command words from which to extract description.
     * @return Command description.
     * @throws BrockException If description is missing.
     */
    private String getDescription(String[] commandWords) throws BrockException {
        int commandLength = commandWords.length;
        StringBuilder description = new StringBuilder();
        for (int i = 1; i < commandLength; i++) {
            if (commandWords[i].equalsIgnoreCase("/from")) {
                break;
            }
            description.append(commandWords[i])
                    .append(" ");
        }

        if (description.isEmpty()) {
            throw new BrockException("Description is missing!");
        }
        return description.toString();
    }

    /**
     * Checks the start datetime and end datetime.
     *
     * @param startDateTime Start datetime to be checked.
     * @param endDateTime End datetime to be checked.
     * @throws BrockException If check fails.
     */
    private void validateDateTimes(String startDateTime, String endDateTime) throws BrockException {
        if (startDateTime.isEmpty()) {
            throw new BrockException("Missing start date! Remember it is specified after /from!");
        }
        if (endDateTime.isEmpty()) {
            throw new BrockException("Missing end date! Remember it is specified after /to!");
        }
        if (this.countWords(startDateTime) != this.countWords(endDateTime)) {
            throw new BrockException("Both start and end dates must either include or exclude a time!");
        }
    }

    /**
     * Counts the number of words for each dateTime string.
     *
     * @param dateTime DateTime string of interest.
     * @return Number of words.
     */
    private int countWords(String dateTime) {
        return dateTime.isEmpty() ? 0 : dateTime.split("\\s+").length;
    }

    /**
     * Gets the start dateTime and end dateTime values.
     *
     * @param commandWords Command from which to extract values.
     * @return Start and end values.
     * @throws BrockException If validation of values fail.
     */
    private String[] getStartEndDateTimes(String[] commandWords) throws BrockException {
        StringBuilder startDateTime = new StringBuilder();
        StringBuilder endDateTime = new StringBuilder();
        boolean isSeeingStartDateTime = false;
        boolean isSeeingEndDateTime = false;
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
                startDateTime.append(word)
                        .append(" ");
            }
            if (isSeeingEndDateTime) {
                endDateTime.append(word)
                        .append(" ");
            }
        }
        this.validateDateTimes(startDateTime.toString(),
                endDateTime.toString());

        return new String[]{startDateTime.toString(),
                endDateTime.toString()};
    }

    /**
     * Creates an {@code Event} object encapsulating details about the event task.
     *
     * @return {@code Event} object.
     * @throws BrockException If event missing description, start date or end date.
     *      Or, if start and end dates are invalid.
     */
    private Task createEvent() throws BrockException {
        String[] commandWords = this.processCommand();
        String description = this.getDescription(commandWords);
        String[] startEndDateTimes = this.getStartEndDateTimes(commandWords);
        String[] startValues = CommandUtility.validateDateTime(startEndDateTimes[0],
                CommandUtility.Context.START);
        String[] endValues = CommandUtility.validateDateTime(startEndDateTimes[1],
                CommandUtility.Context.END);

        assert startValues.length == endValues.length : "Both start and end values must be of the same length.";
        if (startValues.length == 1) {
            return new Event(description,
                    startValues[0],
                    endValues[0]);
        } else {
            return new Event(description,
                    startValues[0],
                    startValues[1],
                    endValues[0],
                    endValues[1]);
        }
    }

    /**
     * Updates save file with event task.
     *
     * @param taskStorage Instance that interfaces with save file.
     * @param tasks List of current {@code Task} objects.
     * @param eventTask Event task to be added to save file.
     * @throws BrockException If writing to save file fails.
     */
    private void updateSaveFile(TaskStorage taskStorage, TaskList tasks, Task eventTask) throws BrockException {
        taskStorage.writeToFile(tasks.numTasks() + ". "
                        + tasks.getTaskDetails(eventTask) + '\n',
                true);
    }

    /**
     * Gets chatbot response to event command.
     *
     * @param tasks List of current {@code Task} objects.
     * @param eventTask Event task created.
     * @return Chatbot response.
     */
    private String getResponse(TaskList tasks, Task eventTask) {
        return "Got it. I've added this task:\n"
                + "  " + tasks.getTaskDetails(eventTask) + '\n'
                + tasks.getTasksSummary()
                + this.getQuirkyResponse();
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
    public String execute(TaskStorage taskStorage, TempStorage tempStorage, TaskList tasks) throws BrockException {
        Task eventTask = this.createEvent();
        tasks.addToList(eventTask);

        tempStorage.setLastCreatedTaskNum(tasks.numTasks());
        this.updateSaveFile(taskStorage, tasks, eventTask);
        return this.getResponse(tasks, eventTask);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getCommandType() {
        return "event";
    }
}
