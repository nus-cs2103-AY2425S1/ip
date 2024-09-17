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


    private String[] processCommand() {
        String command = super.getCommand();
        return command.split(" ");
    }

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

    private void updateSaveFile(TaskStorage taskStorage, TaskList tasks, Task deadlineTask) throws BrockException {
        taskStorage.writeToFile(tasks.numTasks() + ". "
                        + tasks.getTaskDetails(deadlineTask) + '\n',
                true);
    }

    private String getResponse(TaskList tasks, Task deadlineTask) {
        return "Got it. I've added this task:\n"
                + "  " + tasks.getTaskDetails(deadlineTask) + '\n'
                + tasks.getTasksSummary();
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

        tempStorage.setPreviousCommand("deadline");
        tempStorage.setLastCreatedTaskNum(tasks.numTasks());

        this.updateSaveFile(taskStorage, tasks, deadlineTask);
        return this.getResponse(tasks, deadlineTask);
    }
}
