package commands;

import exceptions.BrockException;
import storage.Storage;
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
     * Creates a {@code Deadline} object encapsulating details about the deadline task.
     *
     * @return {@code Deadline} object.
     * @throws BrockException If deadline missing description or due date.
     */
    private Task createDeadline() throws BrockException {
        String command = super.getCommand();
        String[] commandWords = command.split(" ");
        int commandLength = commandWords.length;

        StringBuilder description = new StringBuilder();
        for (int i = 1; i < commandLength; i++) {
            if (commandWords[i].equalsIgnoreCase("/by")) {
                break;
            }
            description.append(commandWords[i])
                    .append(" ");
        }

        StringBuilder dateTime = new StringBuilder();
        boolean isSeeingDateTime = false;
        int dateTimeWords = 0;
        for (String word : commandWords) {
            if (isSeeingDateTime) {
                dateTimeWords += 1;
                dateTime.append(word)
                        .append(" ");
            }
            if (word.equalsIgnoreCase("/by")) {
                isSeeingDateTime = true;
            }
        }

        if (description.isEmpty()) {
            throw new BrockException("Description is missing!");
        }
        if (dateTime.isEmpty()) {
            throw new BrockException("Missing due date! Remember it is specified after /by!");
        }

        String[] dateTimeValues = CommandUtility.validateDateTime(dateTime.toString(),
                dateTimeWords, CommandUtility.Context.DUE);
        if (dateTimeWords == 1) {
            return new Deadline(description.toString(),
                    dateTimeValues[0]);
        } else {
            return new Deadline(description.toString(),
                    dateTimeValues[0],
                    dateTimeValues[1]);
        }
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
    public String execute(Storage storage, TaskList tasks) throws BrockException {
        Task deadlineTask = this.createDeadline();
        tasks.addToList(deadlineTask);

        // Update the save file
        storage.writeToFile(tasks.numTasks() + ". "
                + tasks.getTaskDetails(deadlineTask) + '\n',
                true);

        return "Got it. I've added this task:\n"
                + "  " + tasks.getTaskDetails(deadlineTask) + '\n'
                + tasks.getTasksSummary();
    }
}
