package yap.ui;

import yap.storage.Storage;
import yap.task.Task;
import yap.task.TaskList;

public class Ui {
    private Storage storage;
    private TaskList taskList;
    private final String SEPARATOR = "_____________________________________";
    public Ui(Storage storage, TaskList taskList) {
        this.storage = storage;
        this.taskList = taskList;
    }

    /**
     * Reacts to the user's input
     *
     * @param userInput A string containing the user's input
     * @return Returns 0 if user sends bye, 1 otherwise
     * @throws InputException Throws an input exception if the user inputs an invalid command
     */
    public int reactToUserInput(String userInput) throws InputException {
        if (userInput.equalsIgnoreCase("bye")) {
            System.out.println("Bye! It was really nice talking to you, see you soon :)");
            return 0;
        }

        // Mark functionality
        if (userInput.startsWith("mark")) {
            try {
                int taskIndex = Integer.parseInt(userInput.split(" ")[1]) - 1;
                taskList.markTask(taskIndex);
                System.out.println(SEPARATOR);
            } catch (IndexOutOfBoundsException | NumberFormatException exception) {
                throw new InputException("You did not provide a valid task index to mark!");
            }
            return 1;
        }

        // Unmark functionality
        if (userInput.startsWith("unmark")) {
            try {
                int taskIndex = Integer.parseInt(userInput.split(" ")[1]) - 1;
                taskList.unmarkTask(taskIndex);
                System.out.println(SEPARATOR);
            } catch (IndexOutOfBoundsException | NumberFormatException exception) {
                throw new InputException("You did not provide a valid task index to mark!");
            }
            return 1;
        }

        // Delete functionality
        if (userInput.startsWith("delete")) {
            try {
                int taskIndex = Integer.parseInt(userInput.split(" ")[1]) - 1;
                taskList.deleteTask(taskIndex);
                System.out.println(SEPARATOR);
            } catch (IndexOutOfBoundsException | NumberFormatException exception) {
                throw new InputException("You did not provide a valid task index to mark!");
            }
            return 1;
        }

        // If user adds a todo task
        if (userInput.startsWith("todo")) {
            Task task = Parser.parseInputAsToDo(userInput);
            taskList.addTask(task);
            System.out.println(SEPARATOR);
            return 1;
        }

        // If user adds a deadline task
        if (userInput.startsWith("deadline")) {
            Task task = Parser.parseInputAsDeadline(userInput);
            taskList.addTask(task);
            System.out.println(SEPARATOR);
            return 1;
        }

        // If user adds an event task
        if (userInput.startsWith("event")) {
            Task task = Parser.parseInputAsEvent(userInput);
            taskList.addTask(task);
            System.out.println(SEPARATOR);
            return 1;
        }

        // List functionality
        if (userInput.startsWith("list")) {
            taskList.listTasks();
            System.out.println(SEPARATOR);
            return 1;
        }

        if (userInput.startsWith("find")) {
            System.out.println("Test");
            taskList.listMatchingDescriptionTasks(Parser.getStringFromFindCommand(userInput));
            System.out.println(SEPARATOR);
            return 1;
        }

        throw new InputException();
    }
}
