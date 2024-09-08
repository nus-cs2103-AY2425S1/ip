package echo;


/**
 * The Parser class is responsible for interpreting user commands
 * and performing the corresponding actions on the task list.
 */
public class Parser {

    public static String listAllTask(TaskList allTasks) {
        return allTasks.listAllTask();
    }

    /**
     * Mark the complete status of a target task from task list.
     *
     * @param cmdParts command user input.
     * @param allTasks task list.
     * @return A string confirming that the task has been marked as done.
     * @throws IllegalArgumentException If the index is out of bounds.
     */
    public static String mark(String[] cmdParts, TaskList allTasks)
            throws IndexOutOfBoundsException {
        int markIdx = Integer.parseInt(cmdParts[1]) - 1;
        return allTasks.markTask(markIdx);
    }

    /**
     * Unmark the complete status of a target task from task list.
     *
     * @param cmdParts command user input.
     * @param allTasks task list.
     * @return A string confirming that the task has been marked as not done.
     * @throws IllegalArgumentException If the index is out of bounds.
     */
    public static String unmark(String[] cmdParts, TaskList allTasks)
            throws IndexOutOfBoundsException {
        int unmarkIdx = Integer.parseInt(cmdParts[1]) - 1;
        return allTasks.unmarkTask(unmarkIdx);
    }

    /**
     * Deletes a task from task list.
     * Deletes the target task from task list according to the index
     * and print the current list size.
     *
     * @param cmdParts command user input.
     * @param allTasks task list.
     * @return A string confirming that the task has been deleted, along with the updated task list size.
     * @throws IllegalArgumentException If the index is out of bounds.
     */
    public static String deleteTask(String[] cmdParts, TaskList allTasks)
            throws IndexOutOfBoundsException {
        int deleteIdx = Integer.parseInt(cmdParts[1]) - 1;
        return allTasks.delete(deleteIdx);
    }

    /**
     * Prints tasks in the task list with provided keywords.
     *
     * @param cmdParts command user input.
     * @param allTasks task list.
     * @return A string containing the list of tasks that match the search keywords.
     */
    public static String findTask(String[] cmdParts, TaskList allTasks) {
        String keywords = cmdParts[1];
        return allTasks.find(keywords);
    }

    /**
     * Parses the user input and performs the corresponding action on the task list.
     *
     * @param userInput the input command from the user.
     * @param allTasks the task list to be manipulated based on the command.
     * @return A string response based on the command execution.
     */
    public static String parse(String userInput, TaskList allTasks) throws DukeException {
        // parse the command
        String[] commandArray = userInput.split(" ", 2);
        assert commandArray.length <= 2 : "Command array should be at most 2 in length";
        String command = commandArray[0].toUpperCase();

        // set isExit flag to indicate bot exit
        // boolean isExit = false;

        try {
            switch (Command.valueOf(command)) {
            case BYE:
                return "Bye. Hope to see you again soon!";
            case LIST:
                return listAllTask(allTasks);
            case MARK:
                return mark(commandArray, allTasks);
            case UNMARK:
                return unmark(commandArray, allTasks);
            case DELETE:
                return deleteTask(commandArray, allTasks);
            case FIND:
                return findTask(commandArray, allTasks);
            default:
                Task task = Task.createTask(userInput);
                return allTasks.add(task);
            }
        } catch (ClassCastException e) {
            throw new DukeException("Input Error: " + "\n" + e.getMessage());
        } catch (IllegalArgumentException e) {
            throw new DukeException("Invalid command entered. " + "\n" + e.getMessage());
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Invalid index entered. " + "\n" + e.getMessage());
        } catch (AssertionError e) {
            throw new DukeException(e.getMessage());
        }
    }
}
