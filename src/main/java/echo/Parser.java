package echo;

public class Parser {

    public static void listAllTask(TaskList allTasks) {
        allTasks.listAllTask();
    }

    public static void mark(String[] cmdParts, TaskList allTasks)
            throws IndexOutOfBoundsException {
        int markIdx = Integer.parseInt(cmdParts[1]) - 1;
        allTasks.markTask(markIdx);
    }

    public static void unmark(String[] cmdParts, TaskList allTasks)
            throws IndexOutOfBoundsException {
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
    public static void deleteTask(String[] cmdParts, TaskList allTasks)
            throws IndexOutOfBoundsException {
        int deleteIdx = Integer.parseInt(cmdParts[1]) - 1;
        allTasks.delete(deleteIdx);
    }

    /**
     * Sends goodbye message to user and exits.
     *
     */
    public static void bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static boolean parse(String userInput, TaskList allTasks) {
        // parse the command
        String[] cmdParts = userInput.split(" ");
        String CMD = cmdParts[0].toUpperCase();

        // set isExit flag to indicate bot exit
        boolean isExit = false;

        try {
            switch (Command.valueOf(CMD)) {
            case BYE:
                bye();
                // When the user input "bye", the bot exits
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
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid index entered. " + e.getMessage());
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
