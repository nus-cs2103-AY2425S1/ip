package commands;

import exceptions.BrockException;
import storage.TaskStorage.TaskStorage;
import task.Task;
import task.TaskList;
import task.ToDo;

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

    private String[] processCommand() {
        String command = super.getCommand();
        return command.split(" ");
    }

    private String getDescription(String[] commandWords) throws BrockException {
        int commandLength = commandWords.length;
        StringBuilder description = new StringBuilder();
        for (int i = 1; i < commandLength; i++) {
            description.append(commandWords[i])
                    .append(" ");
        }

        if (description.isEmpty()) {
            throw new BrockException("Description is missing!");
        }
        return description.toString();
    }


    /**
     * Creates an {@code ToDo} object encapsulating details about the todo task.
     *
     * @return {@code ToDo} object.
     * @throws BrockException If todo missing description.
     */
    private Task createTodo() throws BrockException {
        String[] commandWords = this.processCommand();
        String description = this.getDescription(commandWords);
        return new ToDo(description);
    }

    private void updateSaveFile(TaskStorage taskStorage, TaskList tasks, Task todoTask) throws BrockException {
        taskStorage.writeToFile(tasks.numTasks() + ". "
                        + tasks.getTaskDetails(todoTask) + '\n',
                true);
    }

    private String getResponse(TaskList tasks, Task todoTask) {
        return "Got it. I've added this task:\n"
                + "  " + tasks.getTaskDetails(todoTask) + '\n'
                + tasks.getTasksSummary();
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * Chatbot checks if todo command is valid.
     * If so, it creates a {@code ToDo} object.
     * Adds it to {@code tasks}, writes it to save file.
     * Returns a response indicating it has added the todo task.
     * </p>
     *
     * @throws BrockException If todo command is invalid.
     */
    @Override
    public String execute(TaskStorage taskStorage, TaskList tasks) throws BrockException {
        Task todoTask = this.createTodo();
        tasks.addToList(todoTask);

        this.updateSaveFile(taskStorage, tasks, todoTask);
        return this.getResponse(tasks, todoTask);
    }
}
