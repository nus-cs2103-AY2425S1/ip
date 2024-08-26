package echo;

/**
 * The Parser class is responsible for interpreting user commands
 * and performing the corresponding actions on the task list.
 */
public class Parser {

    public static void listAllTask(TaskList allTasks) {
        allTasks.listAllTask();
    }

    /**
     * Mark the complete status of a target task from task list.
     *
     * @param cmdParts command user input.
     * @param allTasks task list.
     * @throws IllegalArgumentException If the index is out of bounds.
     */
    public static void mark(String[] cmdParts, TaskList allTasks) throws IllegalArgumentException {
        int markIdx = Integer.parseInt(cmdParts[1]) - 1;
        allTasks.markTask(markIdx);
    }

    /**
     * Unmark the complete status of a target task from task list.
     *
     * @param cmdParts command user input.
     * @param allTasks task list.
     * @throws IllegalArgumentException If the index is out of bounds.
     */
    public static void unmark(String[] cmdParts, TaskList allTasks) throws IllegalArgumentException {
        int unmarkIdx = Integer.parseInt(cmdParts[1]) - 1;
        allTasks.unmarkTask(unmarkIdx);
    }

    /**
     * Deletes a task from task list.
     * Deletes the target task from task list according to the index
     * and print the current list size.
     *
     * @param cmdParts command user input.
     * @param allTasks task list.
     * @throws IllegalArgumentException If the index is out of bounds.
     */
    public static void deleteTask(String[] cmdParts, TaskList allTasks) throws IllegalArgumentException {
        int deleteIdx = Integer.parseInt(cmdParts[1]) - 1;
        allTasks.delete(deleteIdx);
    }

    /**
     * Sends goodbye message to user and exits.
     */
    public static void bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Parses the user input and performs the corresponding action on the task list.
     *
     * @param userInput the input command from the user.
     * @param allTasks the task list to be manipulated based on the command.
     * @return a boolean value indicating whether the application should exit.
     */
    public static boolean parse(String userInput, TaskList allTasks) {
        String[] cmdParts = userInput.split(" ");
        String CMD = cmdParts[0].toUpperCase();
        boolean isExit = false;
        try {
            switch (Command.valueOf(CMD)) {
            case BYE:
                bye();
                return !isExit;
            case LIST:
                listAllTask(allTasks);
                break;
            case MARK:
                mark(cmdParts, allTasks);
                break;
            case UNMARK:
                unmark(cmdParts, allTasks);
                break;
            case DELETE:
                deleteTask(cmdParts, allTasks);
                break;
            default:
                Task task = Task.createTask(userInput);
                allTasks.add(task);
            }
            Storage.setData(allTasks);
        } catch (ClassCastException e) {
                String msg = "Input Error: " + e.getMessage();
                System.out.println(msg);
        } catch (IllegalArgumentException e) {
                System.out.println("Invalid command entered. " + e.getMessage());
        } catch (DukeException e) {
                System.out.println(e.getMessage());
                System.exit(0);
        } catch (Exception e) {
                System.out.println(e.getMessage());
                System.exit(0);
        }
        return isExit;
    }
}
