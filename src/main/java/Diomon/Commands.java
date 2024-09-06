package Diomon;

/**
 * The {@code Commands} class represents a collection of commands that can be executed
 * in a task management application. Commands include actions like adding tasks,
 * marking tasks as done or undone, deleting tasks, and more. The class also provides
 * support for handling user inputs and running the appropriate command based on the input.
 */
public class Commands {
    private boolean exit;
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
    }

    /**
     * Initializes a new {@code Commands} object with the exit flag set to false.
     */
    public Commands() {
        this.exit = false;
    }

    /**
     * Checks if the program should exit.
     *
     * @return {@code true} if the exit flag is set, {@code false} otherwise.
     */
    public boolean isExit() {
        return exit;
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
        throw new RuntimeException();
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
                list(taskList);
                return;
            case BYE:
                bye();
                return;
            case HELP:
                help();
                return;
            default:
                throw new RuntimeException("Missing argument/ Function not implemented");
            }
        } else {
            switch (t) {
            case TODO:
                todo(taskList, input);
                return;
            case DEADLINE:
                deadline(taskList, input);
                return;
            case EVENT:
                event(taskList, input);
                return;
            case MARK:
                mark(taskList, input);
                return;
            case UNMARK:
                unmark(taskList, input);
                return;
            case DELETE:
                delete(taskList, input);
                return;
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
    public void todo(TaskList taskList, String input) {
        Task newTask = Task.of(input, Task.TaskType.TODO);
        taskList.add(newTask);
        System.out.printf("New Diomon.Task: [%s] has been added.\n", newTask);
        System.out.print(taskList);
    }

    /**
     * Adds a new "deadline" task to the task list.
     *
     * @param taskList The task list to add the task to.
     * @param input The task description and deadline.
     */
    public void deadline(TaskList taskList, String input) {
        try {
            Task newTask = Task.of(input, Task.TaskType.DEADLINE);
            taskList.add(newTask);
            System.out.printf("New Diomon.Task: [%s] has been added.\n", newTask);
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
    public void event(TaskList taskList, String input) {
        try {
            Task newTask = Task.of(input, Task.TaskType.EVENT);
            taskList.add(newTask);
            System.out.printf("New Diomon.Task: [%s] has been added.\n", newTask);
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
    public void list(TaskList taskList) {
        System.out.println("Diomon.TaskList:");
        System.out.print(taskList);
    }

    /**
     * Marks a task as completed.
     *
     * @param taskList The task list to operate on.
     * @param input The task index to mark.
     */
    public void mark(TaskList taskList, String input) {
        try {
            int i = Integer.parseInt(input);
            System.out.printf("Diomon.Task %d: [%s] has been marked", i, taskList.get(i - 1));
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
    public void unmark(TaskList taskList, String input) {
        try {
            int i = Integer.parseInt(input);
            System.out.printf("Diomon.Task %d: [%s] has been unmarked\n", i, taskList.get(i - 1));
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
    public void bye() {
        String byeMessage = "Bye. Hope to see you again soon!";
        System.out.println(byeMessage);
        this.exit = true;
    }

    /**
     * Displays help information.
     */
    public void help() {
        String helpMessage = "Diomon.Commands:\n-TODO\n-DEADLINE\n-EVENT\n-LIST\n-MARK\n-UNMARK\n-BYE\n-HELP";
        System.out.print(helpMessage);
    }

    /**
     * Deletes a task from the task list.
     *
     * @param taskList The task list to operate on.
     * @param input The task index to delete.
     */
    public void delete(TaskList taskList, String input) {
        try {
            int i = Integer.parseInt(input);
            System.out.printf("Diomon.Task %d: [%s] has been deleted", i, taskList.get(i - 1));
            taskList.remove( i- 1);
        } catch (NumberFormatException e) {
            System.out.println("Param given for marking a task is wrong, please try again");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Index out of bound, please try again");
        }
    }
}
