package commands;

import exceptions.BrockException;
import storage.task.TaskStorage;
import storage.temp.TempStorage;
import task.Deadline;
import task.Task;
import task.TaskList;
import utility.CommandUtility;

/**
 * Represents a deadline command entered by the user.
 */
public class DeadlineCommand extends Command {
    /**
     * Stores the command string associated with deadline command.
     *
     * @param command Command string.
     */
    public DeadlineCommand(String command) {
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
            if (commandWords[i].equalsIgnoreCase("/by")) {
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
     * Gets the due datetime for deadline command.
     *
     * @param commandWords Command words from which to extract due datetime.
     * @return Due datetime.
     * @throws BrockException If due datetime is missing.
     */
    private String getDueDateTime(String[] commandWords) throws BrockException {
        StringBuilder dateTime = new StringBuilder();
        boolean isSeeingDateTime = false;
        for (String word : commandWords) {
            if (isSeeingDateTime) {
                dateTime.append(word)
                        .append(" ");
            }
            if (word.equalsIgnoreCase("/by")) {
                isSeeingDateTime = true;
            }
        }

        if (dateTime.isEmpty()) {
            throw new BrockException("Missing due date! Remember it is specified after /by!");
        }
        return dateTime.toString();
    }

    /**
     * Creates a {@code Deadline} object encapsulating details about the deadline task.
     *
     * @return {@code Deadline} object.
     * @throws BrockException If deadline missing description or due date.
     */
    private Task createDeadline() throws BrockException {
        String[] commandWords = this.processCommand();
        String description = this.getDescription(commandWords);
        String dueDateTime = this.getDueDateTime(commandWords);

        String[] dateTimeValues = CommandUtility.validateDateTime(dueDateTime,
                CommandUtility.Context.DUE);
        if (dateTimeValues.length == 1) {
            return new Deadline(description,
                    dateTimeValues[0]);
        } else {
            return new Deadline(description,
                    dateTimeValues[0],
                    dateTimeValues[1]);
        }
    }

    /**
     * Updates the save file with the deadline task.
     *
     * @param taskStorage Instance that interfaces with save file.
     * @param tasks List of current {@code Task} objects.
     * @param deadlineTask Deadline task to be added to save file.
     * @throws BrockException If writing to file fails.
     */
    private void updateSaveFile(TaskStorage taskStorage, TaskList tasks, Task deadlineTask) throws BrockException {
        taskStorage.writeToFile(tasks.numTasks() + ". "
                        + tasks.getTaskDetails(deadlineTask) + '\n',
                true);
    }

    /**
     * Gets the chatbot response to deadline command.
     *
     * @param tasks List of current {@code Task} objects.
     * @param deadlineTask Deadline task created.
     * @return Chatbot response.
     */
    private String getResponse(TaskList tasks, Task deadlineTask) {
        return "Got it. I've added this task:\n"
                + "  " + tasks.getTaskDetails(deadlineTask) + '\n'
                + tasks.getTasksSummary()
                + this.getQuirkyResponse();
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * Chatbot checks if deadline command is valid.
     * If so, it creates a {@code Deadlines} object.
     * Adds it to {@code tasks}, writes it to save file.
     * Returns a response indicating it has added the deadline task.
     * </p>
     *
     * @throws BrockException If deadline command is invalid.
     */
    @Override
    public String execute(TaskStorage taskStorage, TempStorage tempStorage, TaskList tasks) throws BrockException {
        Task deadlineTask = this.createDeadline();
        tasks.addToList(deadlineTask);

        tempStorage.setLastCreatedTaskNum(tasks.numTasks());
        this.updateSaveFile(taskStorage, tasks, deadlineTask);
        return this.getResponse(tasks, deadlineTask);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getCommandType() {
        return "deadline";
    }
}
