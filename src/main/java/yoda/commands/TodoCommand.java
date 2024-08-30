package yoda.commands;

import yoda.TaskList;
import yoda.exceptions.InvalidInputException;
import yoda.tasks.ToDo;

/**
 * Represents a command to add a to-do task to the task list.
 */
public class TodoCommand extends Command {
    private TaskList taskList;
    private String input;

    /**
     * Constructs a TodoCommand using the task list and input.
     *
     * @param taskList Task list where the new task will be added to.
     * @param input User input containing the task description.
     */
    public TodoCommand(TaskList taskList, String input) {
        this.taskList = taskList;
        this.input = input;
    }

    /**
     * Executes a TodoCommand and adds a new to-do task to the list.
     *
     * @throws InvalidInputException if input format is invalid.
     */
    public void run() throws InvalidInputException {
        if (!hasValidFormat()) {
            throw new InvalidInputException("A todo must have a description, no...?");
        }
        String[] splitInput = input.split(" ", 2);
        String task = splitInput[1];
        ToDo newTask = new ToDo(task);
        taskList.add(newTask);
        System.out.println("Added task:\n" + newTask + "\n"
                + String.format("Now you have %d tasks in the list", taskList.getLength()));
    }

    /**
     * Checks if input format is valid.
     *
     * @return true if input format is valid.
     */
    public boolean hasValidFormat() {
        String[] splitInput = input.split(" ", 2);
        return splitInput.length == 2;
    }

}
