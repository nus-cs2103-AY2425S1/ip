package commands;

import exceptions.BrockException;
import storage.Storage;
import task.Task;
import task.TaskList;
import task.ToDo;
import ui.Ui;

/**
 * Represents a todo command entered by the user.
 */
public class TodoCommand extends Command {
    /**
     * Stores the command string associated with todo command.
     *
     * @param command Command string.
     */
    public TodoCommand(String command) {
        super(command);
    }

    /**
     * Creates an {@code ToDo} object encapsulating details about the todo task.
     *
     * @return {@code ToDo} object.
     * @throws BrockException If todo missing description.
     */
    private Task createTodo() throws BrockException {
        String command = super.getCommand();
        String[] commandWords = command.split(" ");
        int commandLength = commandWords.length;

        StringBuilder description = new StringBuilder();
        for (int i = 1; i < commandLength; i++) {
            description.append(commandWords[i])
                    .append(" ");
        }
        description.deleteCharAt(description.length() - 1);

        if (description.isEmpty()) {
            throw new BrockException("Description is missing!");
        }
        return new ToDo(description.toString());
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * Chatbot checks if todo command is valid.
     * If so, it creates a {@code ToDo} object.
     * Adds it to {@code tasks}, writes it to save file.
     * Displays a response indicating it has added the todo task.
     * </p>
     *
     * @throws BrockException If todo command is invalid.
     */
    @Override
    public void execute(Ui ui, Storage storage, TaskList tasks) throws BrockException {
        Task todoTask = createTodo();
        tasks.addToList(todoTask);
        ui.displayResponse("Got it. I've added this task:\n"
                + "  "
                + tasks.getTaskDetails(todoTask)
                + '\n'
                + tasks.getTasksSummary());

        // Update the save file
        storage.writeToFile(tasks.numTasks()
                + ". "
                + tasks.getTaskDetails(todoTask)
                + '\n',
                true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
