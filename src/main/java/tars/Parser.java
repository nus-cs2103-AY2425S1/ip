package tars;

/**
 * Processes User inputs and executes commands based on inputs
 * Has method to also list all Tasks added by User
 *
 * @author csk
 * @version 1
 */

public class Parser {
    static String LINE = "    _____________________________________________";

    public Parser(){

    }

    /**
     * Checks user inputs after being taken as string and string[] variables
     * Chooses appropriate methods to call based on input given or throws error message
     * depending on incorrect input received
     *
     * @param entryParts
     * @param entry
     * @param tasks
     */
    public void checkEntry(String[] entryParts, String entry, TaskList tasks){
        try {
            if (entry.equals("todo") || entry.equals("deadLINE") || entry.equals("event")) {
                throw new TarsException(LINE + "\n" + "    OOPS! Describe the task/event/deadLINE/todo or list"
                        + "\n" + LINE);
            } else if (entryParts[0].equals("list")) {
                this.listPrint(tasks);
            } else {
                throw new TarsException(LINE + "\n" + "    OOPS! Only accept a task/event/deadLINE/todo as input"
                        + "\n" + LINE);
            }
        } catch(TarsException e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Prints all the Tasks in the list at the current state of storage
     *
     * @param tasks
     */
    public void listPrint(TaskList tasks) {
        if (tasks.getList().size() == 0) {
            System.out.println("No tasks added to list. Please add events/deadLINE/todos!");
        } else {
            System.out.println(LINE + "\n" + "    Here are the tasks in your list:");
            for (int i = 0; i < tasks.getList().size(); i++) {
                System.out.println("    " + (i + 1) + ". " + tasks.getList().get(i));
            }
            System.out.println(LINE);
        }
    }
}
