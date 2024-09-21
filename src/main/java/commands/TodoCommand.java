package commands;

import exceptions.BrockException;
import storage.task.TaskStorage;
import storage.temp.TempStorage;
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

    /**
     * Updates the save file with the todo task.
     *
     * @param taskStorage Instance that interfaces with save file.
     * @param tasks List of current {@code Task} objects.
     * @param todoTask Todo task to be added.
     * @throws BrockException If writing to save file fails.
     */
    private void updateSaveFile(TaskStorage taskStorage, TaskList tasks, Task todoTask) throws BrockException {
        taskStorage.writeToFile(tasks.numTasks() + ". "
                + tasks.getTaskDetails(todoTask) + '\n',
                true);
    }

    /**
     * Gets the chatbot response to todo command.
     *
     * @param tasks List of current {@code Task} objects.
     * @param todoTask Todo task created.
     * @return Chatbot response.
     */
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
    public String execute(TaskStorage taskStorage, TempStorage tempStorage, TaskList tasks) throws BrockException {
        Task todoTask = this.createTodo();
        tasks.addToList(todoTask);

        tempStorage.setLastCreatedTaskNum(tasks.numTasks());
        this.updateSaveFile(taskStorage, tasks, todoTask);
        return this.getResponse(tasks, todoTask);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getCommandType() {
        return "todo";
    }
}
