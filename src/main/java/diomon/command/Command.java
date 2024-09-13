package diomon.command;

import diomon.Storage;
import diomon.Task;
import diomon.TaskList;
import diomon.ui.Ui;

/**
 * The {@code Commands} class represents a collection of commands that can be executed
 * in a task management application. Commands include actions like adding tasks,
 * marking tasks as done or undone, deleting tasks, and more. The class also provides
 * support for handling user inputs and running the appropriate command based on the input.
 */
public abstract class Command {
    protected String input;
    protected boolean canExit;
    protected String response;

    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    public enum Types {
        TODO,
        DEADLINE,
        EVENT,
        LIST,
        MARK,
        UNMARK,
        BYE,
        HELP,
        DELETE,
        FIND,
    }

    /**
     * Initializes a new {@code Commands} object with the exit flag set to false.
     */
    public Command() {
        this.canExit = false;
    }

    /**
     * Checks if the program should exit.
     *
     * @return {@code true} if the exit flag is set, {@code false} otherwise.
     */
    public boolean isCanExit() {
        return canExit;
    }

    public String getResponse() {
        return response;
    }
    protected void setResponse(String res) {
        response = res;
    }


    /**
     * Checks and returns the command type based on the user's input string.
     *
     * @param command The input string representing the command.
     * @return The corresponding {@code Types} enumeration.
     * @throws RuntimeException if the command is not recognized.
     */
    public static Types checkType(String command) {
        for(Types t : Types.values()) {
            if (t.name().equalsIgnoreCase(command)) return t;
        }
        throw new RuntimeException("Nein, the command dont exist");
    }

    /**
     * Executes the given command based on its type and input.
     *
     * @param t The command type.
     * @param input The input associated with the command.
     * @param taskList The task list to operate on.
     * @throws RuntimeException if the command is missing an argument or not implemented.
     */
    public void runCommand(Types t, String input, TaskList taskList) {

        if (input == null) {
            switch (t) {
            case LIST:
                runList(taskList);
                break;
            case BYE:
                runBye();
                break;
            case HELP:
                runHelp();
                break;
            default:
                throw new RuntimeException("Missing argument/ Function not implemented");
            }
        } else {
            switch (t) {
            case TODO:
                runTodo(taskList, input);
                break;
            case DEADLINE:
                runDeadline(taskList, input);
                break;
            case EVENT:
                runEvent(taskList, input);
                break;
            case MARK:
                runMark(taskList, input);
                break;
            case UNMARK:
                runUnmark(taskList, input);
                break;
            case DELETE:
                runDelete(taskList, input);
            case FIND:
                runFind(taskList, input);
                break;
            default:
                throw new RuntimeException("Unknown argument/ Function not implemented yet");
                }
            }
    }

    /**
     * Processes the user input and determines which command to execute.
     *
     * @param input The raw input string entered by the user.
     * @param taskList The task list to operate on.
     */
    public void run(String input, TaskList taskList) {
        // Process input
        String[] inputArray = input.split(" ", 2);
        String inputCommand = inputArray[0];
        String inputContent = inputArray.length == 1 ? null: inputArray[1];
        try {
            runCommand(checkType(inputCommand), inputContent, taskList);
        } catch (RuntimeException e){
            System.out.println("Command don't exist. Please retry. Type 'Help' for help");
        }

    }

    //Command Logic


    /**
     * Adds a new "todo" task to the task list.
     *
     * @param taskList The task list to add the task to.
     * @param input The task description.
     */

    public void runTodo(TaskList taskList, String input) {
        Task newTask = Task.of(input, Task.TaskType.TODO);
        taskList.add(newTask);
        System.out.printf("New diomon.Diomon.Task: [%s] has been added.\n", newTask);
        System.out.print(taskList);
    }

    /**
     * Adds a new "deadline" task to the task list.
     *
     * @param taskList The task list to add the task to.
     * @param input The task description and deadline.
     */
    public void runDeadline(TaskList taskList, String input) {
        try {
            Task newTask = Task.of(input, Task.TaskType.DEADLINE);
            taskList.add(newTask);
            System.out.printf("New diomon.Diomon.Task: [%s] has been added.\n", newTask);
            System.out.print(taskList);
        } catch (Exception e) {
            System.out.println("Incorrect/ missing details given");
        }

    }

    /**
     * Adds a new "event" task to the task list.
     *
     * @param taskList The task list to add the task to.
     * @param input The event description.
     */
    public void runEvent(TaskList taskList, String input) {
        try {
            Task newTask = Task.of(input, Task.TaskType.EVENT);
            taskList.add(newTask);
            System.out.printf("New diomon.Diomon.Task: [%s] has been added.\n", newTask);
            System.out.print(taskList);
        } catch (Exception e) {
            System.out.println("Incorrect/ missing details given");
        }
    }

    /**
     * Lists all tasks in the task list.
     *
     * @param taskList The task list to display.
     */
    public void runList(TaskList taskList) {
        System.out.println("diomon.Diomon.TaskList:");
        System.out.print(taskList);
    }

    /**
     * Marks a task as completed.
     *
     * @param taskList The task list to operate on.
     * @param input The task index to mark.
     */
    public void runMark(TaskList taskList, String input) {
        try {
            int i = Integer.parseInt(input);
            System.out.printf("diomon.Diomon.Task %d: [%s] has been marked", i, taskList.get(i - 1));
            taskList.mark( i- 1);
        } catch (NumberFormatException e) {
            System.out.println("Param given for marking a task is wrong, please try again");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Index out of bound, please try again");
        }
    }

    /**
     * Unmarks a completed task.
     *
     * @param taskList The task list to operate on.
     * @param input The task index to unmark.
     */
    public void runUnmark(TaskList taskList, String input) {
        try {
            int i = Integer.parseInt(input);
            System.out.printf("diomon.Diomon.Task %d: [%s] has been unmarked\n", i, taskList.get(i - 1));
            taskList.unmark( i- 1);
        } catch (NumberFormatException e) {
            System.out.println("Param given for unmarking a task is wrong, please try again");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Index out of bound");
        }
    }

    /**
     * Exits the program.
     */
    public void runBye() {
        String byeMessage = "Bye. Hope to see you again soon!";
        System.out.println(byeMessage);
        this.canExit = true;
    }

    /**
     * Displays help information.
     */
    public void runHelp() {
        String helpMessage = "diomon.Diomon.Commands:\n-TODO\n-DEADLINE\n-EVENT\n-LIST\n-MARK\n-UNMARK\n-BYE\n-HELP";
        System.out.print(helpMessage);
    }

    /**
     * Deletes a task from the task list.
     *
     * @param taskList The task list to operate on.
     * @param input The task index to delete.
     */
    public void runDelete(TaskList taskList, String input) {
        try {
            int i = Integer.parseInt(input);
            System.out.printf("diomon.Diomon.Task %d: [%s] has been deleted", i, taskList.get(i - 1));
            taskList.remove( i- 1);
        } catch (NumberFormatException e) {
            System.out.println("Param given for marking a task is wrong, please try again");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Index out of bound, please try again");
        }
    }

    public void runFind(TaskList taskList, String input) {
        try {
            System.out.println("Here is the search result:");
            System.out.println(taskList.fuzzyFind(input));
        } catch (RuntimeException e) {
            System.out.println("Something went wrong with the search");
        }
    }

}
